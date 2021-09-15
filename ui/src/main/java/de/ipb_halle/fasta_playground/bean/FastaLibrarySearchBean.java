/*
 * fasta-playground
 * Copyright 2021 Leibniz-Institut für Pflanzenbiochemie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.biojava.nbio.data.sequence.FastaSequence;
import org.biojava.nbio.data.sequence.SequenceUtil;
import org.omnifaces.util.Faces;

import de.ipb_halle.fasta_playground.display.FastaResultDisplayWrapper;
import de.ipb_halle.fasta_playground.display.ResultDisplayConfig;
import de.ipb_halle.fasta_playground.fastaresult.FastaResultParser;
import de.ipb_halle.fasta_playground.search.SearchMode;

@Named
//@RequestScoped
// needs to be ViewScoped because of the fasta file download capabilities
@ViewScoped
public class FastaLibrarySearchBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int[] maxResultSelectItems = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 150, 200, 250, 500, 750, 1000 };

	private SortItem[] sortByItems = SortItem.values();

	private SearchMode[] searchModeItems = SearchMode.values();

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

	private SearchMode searchMode = SearchMode.PROTEIN_PROTEIN;

	/*
	 * calculated data
	 */
	private List<FastaResultDisplayWrapper> results;

	private String fastaOutput = "";

	private List<FastaSequence> sequences;

	/*
	 * Injects
	 */
	@Inject
	private transient Logger logger;

	/*
	 * Library search
	 */
	public void actionSearch() throws IOException {
		try (InputStream in = new ByteArrayInputStream(library.getBytes())) {
			sequences = SequenceUtil.readFasta(in);
		}

		File libraryFile = null;
		File queryFile = null;
		try {
			libraryFile = writeToTempFile("FastaLibrary", ".fasta", library);
			queryFile = writeToTempFile("FastaQuery", ".fasta", query);

			// execute fasta program
			fastaOutput = execFastaProgram(libraryFile, queryFile);

			// display config
			ResultDisplayConfig conf = searchMode.getSearchFactory().getDisplayConfig();
			conf.setPrefixSpaces(2);
			conf.setSuffixSpaces(2);
			conf.setLineLength(60);

			// collect results from the program's output
			results = new ArrayList<>();
			new FastaResultParser(new StringReader(fastaOutput)).parse()
					.forEach(r -> results.add(new FastaResultDisplayWrapper(r).config(conf)));
			sortResults();
		} catch (Exception e) {
			logger.info(fastaOutput);
			logger.severe(ExceptionUtils.getStackTrace(e));
		} finally {
			// clean up
			deleteFileIfNotNull(libraryFile);
			deleteFileIfNotNull(queryFile);
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

	private void deleteFileIfNotNull(File file) {
		if (file != null) {
			file.delete();
		}
	}

	private String execFastaProgram(File libraryFile, File queryFile) throws IOException {
		String[] params = { "-d", Integer.toString(maxResults), "-b", Integer.toString(maxResults) };

		return searchMode.getSearchFactory().execSearch(libraryFile, queryFile, params);
	}

	/*
	 * Examples
	 */
	public void actionProteinExample() throws IOException {
		library = readResourceToString("example_protein_library.fasta");
		query = readResourceToString("example_protein_query.fasta");
		searchMode = SearchMode.PROTEIN_PROTEIN;
	}

	public void actionDNAExample() throws IOException {
		library = readResourceToString("example_DNA_library.fasta");
		query = readResourceToString("example_DNA_query.fasta");
		searchMode = SearchMode.DNA_DNA;
	}

	private String readResourceToString(String name) throws IOException {
		InputStream in = FastaLibrarySearchBean.class.getResourceAsStream(name);
		return readInputStreamToString(in);
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

	/*
	 * Why do I have to do this? SequenceUtil.writeFasta() fails to write correct
	 * FASTA files ... BioJava is such a sh**ty library!
	 */
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
		results.sort(sortBy.getComparator());
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

	public SearchMode[] getSearchModeItems() {
		return searchModeItems;
	}

	public SearchMode getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(SearchMode searchMode) {
		this.searchMode = searchMode;
	}

	public List<FastaResultDisplayWrapper> getResults() {
		return results;
	}

	public String getFastaOutput() {
		return fastaOutput;
	}
}