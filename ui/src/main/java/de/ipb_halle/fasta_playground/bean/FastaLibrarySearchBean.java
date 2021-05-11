package de.ipb_halle.fasta_playground.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import java.util.StringJoiner;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import de.ipb_halle.fasta_playground.fastaresult.FastaResult;
import de.ipb_halle.fasta_playground.fastaresult.FastaResultParser;
import de.ipb_halle.fasta_playground.fastaresult.FastaResultParserException;

@Named
@RequestScoped
public class FastaLibrarySearchBean {
	private static final String FASTA_PROGRAM = "/path/to/fasta36/bin/fasta36";

	@NotNull
	private String library;

	@NotNull
	private String query = ">query1 my query sequence\nSAVQQKLAALEKSSGGRLGVALIDTADNTQVLYRGDERFPMCSTSKVMAA";

	private List<FastaResult> results;

	private String fastaOutput;

	/*
	 * Library search
	 */
	public void actionSearch() throws IOException, FastaResultParserException {
		File libraryFile = writeToTempFile("FastaLibrary", ".fasta", library);
		File queryFile = writeToTempFile("FastaQuery", ".fasta", query);

		// execute fasta
		fastaOutput = execFastaProgram(libraryFile, queryFile);

		// clean up
		libraryFile.delete();
		queryFile.delete();

		results = new FastaResultParser(new StringReader(fastaOutput)).parse();
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
		ProcessBuilder builder = new ProcessBuilder(FASTA_PROGRAM, "-q", "-m 10", queryFile.getAbsolutePath(),
				libraryFile.getAbsolutePath());
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
	 * Example libraries
	 */
	public void actionInsertProteinLibrary() throws IOException {
		library = readInputStreamToString(
				FastaLibrarySearchBean.class.getResourceAsStream("example_protein_library.fasta"));
	}

	public void actionInsertDNALibrary() {
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

	public List<FastaResult> getResults() {
		return results;
	}

	public void setResults(List<FastaResult> results) {
		this.results = results;
	}
}