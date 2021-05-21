package de.ipb_halle.fasta_playground.bean;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.biojava.nbio.data.sequence.FastaSequence;
import org.biojava.nbio.data.sequence.SequenceUtil;
import org.omnifaces.util.Faces;

import de.ipb_halle.fasta_playground.fastaresult.FastaResultParser;
import de.ipb_halle.fasta_playground.fastaresult.FastaResultParserException;

@Named
//@RequestScoped
// needs to be ViewScoped because of the fasta file download capabilities
@ViewScoped
public class FastaLibrarySearchBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String FASTA_PROGRAM = "/path/to/fasta36/bin/fasta36";

	private FastaResultDisplayConfig conf;

	private int[] maxResultSelectItems = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 250, 500, 750, 1000 };

	public enum SortItem {
		SUBJECTNAME("Sequence Name (alph.)", Comparator.comparing(f -> f.getFastaResult().getSubjectSequenceName()),
				false),
		LENGTH("Hit Length (desc.)", Comparator.comparing(f -> f.getFastaResult().getSubjectSequenceLength()), true),
		BITSCORE("Bit Score (desc.)", Comparator.comparing(f -> f.getFastaResult().getBitScore()), true),
		EVALUE("E()-Value (asc.)", Comparator.comparing(f -> f.getFastaResult().getExpectationValue()), false),
		SMITHWATERMANSCORE("Smith-Waterman Score (desc.)",
				Comparator.comparing(f -> f.getFastaResult().getSmithWatermanScore()), true),
		IDENTITY("Identity (desc.)", Comparator.comparing(f -> f.getFastaResult().getIdentity()), true),
		SIMILARITY("Similarity/Positives (desc.)", Comparator.comparing(f -> f.getFastaResult().getSimilarity()), true);

		private final String label;

		private final Comparator<FastaResultDisplayWrapper> comp;

		private SortItem(String label, Comparator<FastaResultDisplayWrapper> comp, boolean reverse) {
			this.label = label;

			// could not reverse the Comparator in the constructor calls :(
			if (reverse) {
				this.comp = comp.reversed();
			} else {
				this.comp = comp;
			}
		}

		public String getLabel() {
			return label;
		}

		public Comparator<FastaResultDisplayWrapper> getComp() {
			return comp;
		}
	}

	private SortItem[] sortByItems = SortItem.values();

	/*
	 * data supplied by user
	 */
	@NotNull
	private String library = "";

	@NotNull
	private String query = "";

	// @Min(1)
	private int maxResults = maxResultSelectItems[4];

	private SortItem sortBy = SortItem.EVALUE;

	/*
	 * calculated data
	 */
	private List<FastaResultDisplayWrapper> results;

	private String fastaOutput = "";

	private List<FastaSequence> sequences;

	@PostConstruct
	public void init() {
		conf = new FastaResultDisplayConfig();
		conf.setPrefixSpaces(2);
		conf.setSuffixSpaces(2);
		conf.setLineLength(60);
	}

	/*
	 * Library search
	 */
	public void actionSearch() throws IOException, FastaResultParserException {
		try (InputStream in = new ByteArrayInputStream(library.getBytes())) {
			sequences = SequenceUtil.readFasta(in);
		}

		try {
			File libraryFile = writeToTempFile("FastaLibrary", ".fasta", library);
			File queryFile = writeToTempFile("FastaQuery", ".fasta", query);

			// execute fasta program
			fastaOutput = execFastaProgram(libraryFile, queryFile);

			// clean up
			libraryFile.delete();
			queryFile.delete();

			// collect results from the program's output
			results = new ArrayList<>();
			new FastaResultParser(new StringReader(fastaOutput)).parse()
					.forEach(r -> results.add(new FastaResultDisplayWrapper(r).config(conf)));
			sortResults();
		} catch (Exception e) {
			// TODO: properly log exception
			System.out.println(fastaOutput);
			e.printStackTrace();
		}
	}

	private File writeToTempFile(String prefix, String suffix, String content) throws IOException {
		// create a file in /tmp
		File file = File.createTempFile(prefix, suffix);
		// just in case we forget to delete it
		file.deleteOnExit();

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(content);
		}

		return file;
	}

	private String execFastaProgram(File libraryFile, File queryFile) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(FASTA_PROGRAM, "-q", "-m", "10", "-d", Integer.toString(maxResults),
				"-b", Integer.toString(maxResults), queryFile.getAbsolutePath(), libraryFile.getAbsolutePath());
		Process process = builder.start();

		StringJoiner sj = new StringJoiner("\n");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sj.add(line);
			}
		}

		return sj.toString();
	}

	/*
	 * Examples
	 */
	public void actionProteinExample() throws IOException {
		library = readInputStreamToString(
				FastaLibrarySearchBean.class.getResourceAsStream("example_protein_library.fasta"));
		query = ">query1 my query sequence\nSAVQQKLAALEKSSGGRLGVALIDTADNTQVLYRGDERFPMCSTSKVMAA";
	}

	public void actionDNAExample() {
	}

	public void actionRNAExample() {
	}

	private String readInputStreamToString(InputStream in) throws IOException {
		if (in == null) {
			return "";
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		StringJoiner sj = new StringJoiner("\n");
		while ((line = reader.readLine()) != null) {
			sj.add(line);
		}
		return sj.toString();
	}

	/*
	 * FASTA file download
	 */
	public void actionDownloadResultAsFasta(FastaResultDisplayWrapper item, int index) throws IOException {
		if (sequences == null) {
			return;
		}

		// find the right sequence in the sequences list
		FastaSequence fastaSequence = findFastaSequence(sequences, item.getFastaResult().getSubjectSequenceName() + " "
				+ item.getFastaResult().getSubjectSequenceDescription());

		// Sending out file to the client is pretty easy thanks to OmniFaces.
		if (fastaSequence != null) {
			/*
			 * Please note that the 'fasta' file extension should be registered as
			 * mime-mapping to text/plain in web.xml.
			 */
			Faces.sendFile(generateFastaString(fastaSequence).getBytes(), "result_" + index + ".fasta", true);
		}
	}

	private static FastaSequence findFastaSequence(List<FastaSequence> list, String id) {
		return list.stream().filter(seq -> seq.getId().equals(id)).findFirst().orElse(null);
	}

	// Why do I have to do this? BioJava is such a sh**ty library!
	private static String generateFastaString(FastaSequence sequence) {
		StringBuilder sb = new StringBuilder(sequence.getId().length() + sequence.getLength() + 3);
		sb.append(">").append(sequence.getId()).append("\n").append(sequence.getFormatedSequence(80));
		return sb.toString();
	}

	public void actionDownloadAllResultsAsFasta() throws IOException {
		if ((sequences == null) || (results == null)) {
			return;
		}

		/*
		 * Find the right sequences in the sequences list. The LinkedHashSet guarantees
		 * (1) ordering and (2) duplicate removal (FastaSequence has proper equals() and
		 * hashCode()).
		 */
		Collection<FastaSequence> fastaSequences = new LinkedHashSet<>();
		results.forEach(
				res -> fastaSequences.add(findFastaSequence(sequences, res.getFastaResult().getSubjectSequenceName()
						+ " " + res.getFastaResult().getSubjectSequenceDescription())));

		if (!fastaSequences.isEmpty()) {
			Faces.sendFile(generateFastaString(fastaSequences).getBytes(), "results.fasta", true);
		}
	}

	private static String generateFastaString(Collection<FastaSequence> sequences) {
		StringJoiner sj = new StringJoiner("\n");
		sequences.forEach(seq -> sj.add(generateFastaString(seq)));
		return sj.toString();
	}

	/*
	 * Sorting the results
	 */
	public void actionOnChangeSortBy() {
		if (results != null) {
			sortResults();
		}
	}

	private void sortResults() {
		results.sort(sortBy.getComp());
	}

	/*
	 * Getters/Setters
	 */
	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int[] getMaxResultSelectItems() {
		return maxResultSelectItems;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public SortItem[] getSortByItems() {
		return sortByItems;
	}

	public SortItem getSortBy() {
		return sortBy;
	}

	public void setSortBy(SortItem sortBy) {
		this.sortBy = sortBy;
	}

	public List<FastaResultDisplayWrapper> getResults() {
		return results;
	}

	public String getFastaOutput() {
		return fastaOutput;
	}
}