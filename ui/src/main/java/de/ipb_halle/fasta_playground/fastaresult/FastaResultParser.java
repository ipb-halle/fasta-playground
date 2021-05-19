package de.ipb_halle.fasta_playground.fastaresult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FastaResultParser {
	private final BufferedReader reader;

	public FastaResultParser(Reader in) {
		reader = new BufferedReader(in);
	}

	/*
	 * Examples: "  1>>>query1 first query sequence - 50 aa" or "  1>>> - 50 aa"
	 */
	private static final Pattern QUERY_START_PATTERN = Pattern
			.compile("[ \\d]+[>]{3}[\\w\\W]*[ ][-][ ][\\d]*[ ][a]{2}");

	/**
	 * 
	 * @return list of results, not necessarily sorted by score
	 * @throws IOException
	 * @throws FastaResultParserException
	 */
	public List<FastaResult> parse() throws IOException, FastaResultParserException {
		List<FastaResult> results = new ArrayList<>();
		String line;
		String querySequenceName = null;
		String querySequenceDescription = null;
		String subjectSequenceName = null;
		boolean parsedHeader = false;
		boolean parsedGlobalParameters = false;
		boolean inQueryBlock = false;
		boolean inSubjectBlock = false;
		boolean inSequence = false;
		StringBuilder sequenceBuilder = null;
		FastaResultBuilder builder = null;

		int lineNumber = 0;
		while ((line = reader.readLine()) != null) {
			lineNumber++;

			/*
			 * Query header: matches for example
			 * "  1>>>query1 first query sequence - 50 aa".
			 */
			if (!parsedHeader && QUERY_START_PATTERN.matcher(line).matches()) {
				// gives "query1 first query sequence"
				String queryString = line.split(">>>")[1].split("-")[0].trim();
				int firstSpace = queryString.indexOf(" ");
				if (firstSpace < 0) {
					querySequenceName = queryString;
					querySequenceDescription = "";
				} else {
					querySequenceName = queryString.substring(0, firstSpace);
					querySequenceDescription = queryString.substring(firstSpace + 1);
				}
				parsedHeader = true;
			}

			/*
			 * Parameters header of this query: matches for example
			 * ">>>query1, 50 aa vs data.fasta library".
			 */
			else if (parsedHeader && !parsedGlobalParameters && line.startsWith(">>>" + querySequenceName)) {
				// nothing to parse here
				parsedGlobalParameters = true;
			}

			/*
			 * End of the result list for a query. This terminates the alignment consensus
			 * string and the result itself.
			 */
			else if (">>><<<".equals(line)) {
				if ((builder != null) && inSequence) {
					builder.consensusLine(sequenceBuilder.toString());
					inSequence = false;
				}

				// save the previous result
				if (builder != null) {
					results.add(builder.build());
					builder = null;
				}

				break;
			}

			/*
			 * Start of a result with a result header: matches for example
			 * ">>gb|AAF72530.1|AF252622_1 beta-lactamase CTX-M-14 (plasmid) [Escherichia coli]"
			 * . This also terminates the alignment consensus string of the previous result
			 * and the previous result itself.
			 */
			else if (parsedGlobalParameters && !inQueryBlock && line.startsWith(">>")) {
				if ((builder != null) && inSequence) {
					builder.consensusLine(sequenceBuilder.toString());
					inSequence = false;
				}

				// save the previous result
				if (builder != null) {
					results.add(builder.build());
					builder = null;
				}

				// begin new result
				builder = FastaResult.builder();
				inQueryBlock = false;
				inSubjectBlock = false;

				String subjectString = line.split(">>")[1];
				int firstSpace = subjectString.indexOf(" ");
				if (firstSpace < 0) {
					subjectSequenceName = subjectString;
					builder.subjectSequenceDescription("");
				} else {
					subjectSequenceName = subjectString.substring(0, firstSpace);
					builder.subjectSequenceDescription(subjectString.substring(firstSpace + 1));
				}
				builder.subjectSequenceName(subjectSequenceName);
			}

			/*
			 * Start of another hit result from the same subject sequence. The matching line
			 * is ">--". This also terminates the alignment consensus string of the previous
			 * result and the previous result itself.
			 */
			else if (parsedGlobalParameters && !inQueryBlock && inSequence && ">--".equals(line) && (builder != null)) {
				builder.consensusLine(sequenceBuilder.toString());
				inSequence = false;

				// save the previous result
				results.add(builder.build());
				String subjectSequenceDescription = builder.getSubjectSequenceDescription();

				// begin new result
				builder = FastaResult.builder();
				inQueryBlock = false;
				inSubjectBlock = false;

				builder.subjectSequenceName(subjectSequenceName);
				builder.subjectSequenceDescription(subjectSequenceDescription);
			}

			/*
			 * BitScore: matches for example "; fa_bits: 96.5".
			 */
			else if ((builder != null) && line.startsWith("; fa_bits:")) {
				builder.bitScore(Double.parseDouble(line.split(":")[1].trim()));
			}

			/*
			 * E()-value: matches for example "; fa_expect: 5.2e-25".
			 */
			else if ((builder != null) && line.startsWith("; fa_expect:")) {
				builder.expectationValue(Double.parseDouble(line.split(":")[1].trim()));
			}

			/*
			 * Smith-Waterman score: matches for example "; sw_score: 313".
			 */
			else if ((builder != null) && line.startsWith("; sw_score:")) {
				builder.smithWatermanScore(Integer.parseInt(line.split(":")[1].trim()));
			}

			/*
			 * Identity: matches for example "; sw_ident: 1.000".
			 */
			else if ((builder != null) && line.startsWith("; sw_ident:")) {
				builder.identity(Double.parseDouble(line.split(":")[1].trim()));
			}

			/*
			 * Similarity: matches for example "; sw_sim: 1.000".
			 */
			else if ((builder != null) && line.startsWith("; sw_sim:")) {
				builder.similarity(Double.parseDouble(line.split(":")[1].trim()));
			}

			/*
			 * Overlap: matches for example "; sw_overlap: 50".
			 */
			else if ((builder != null) && line.startsWith("; sw_overlap:")) {
				builder.overlap(Integer.parseInt(line.split(":")[1].trim()));
			}

			/*
			 * Start of the query block: matches for example ">query1 ..".
			 */
			else if ((builder != null) && !inQueryBlock && !inSubjectBlock
					&& line.equals(">" + querySequenceName + " ..")) {
				inQueryBlock = true;
				builder.querySequenceName(querySequenceName).querySequenceDescription(querySequenceDescription);
			}

			/*
			 * Start of the subject block: matches for example
			 * ">gb|AAF72530.1|AF252622_1 ..". This also terminates the query alignment
			 * string and the query block.
			 */
			else if ((builder != null) && inQueryBlock && !inSubjectBlock
					&& line.equals(">" + subjectSequenceName + " ..")) {
				if (inSequence) {
					builder.queryAlignmentLine(sequenceBuilder.toString());
					inSequence = false;
					sequenceBuilder = null;
				}

				inQueryBlock = false;
				inSubjectBlock = true;
			}

			/*
			 * Sequence length: matches for example "; sq_len: 50".
			 */
			else if ((builder != null) && line.startsWith("; sq_len:")) {
				int value = Integer.parseInt(line.split(":")[1].trim());

				if (inQueryBlock && !inSubjectBlock) {
					builder.querySequenceLength(value);
				} else if (!inQueryBlock && inSubjectBlock) {
					builder.subjectSequenceLength(value);
				} else {
					throw new FastaResultParserException("Line " + lineNumber
							+ ": Sequence length (sq_len) detected outside of query or subject block.");
				}
			}

			/*
			 * Alignment start: matches for example "; al_start: 1".
			 */
			else if ((builder != null) && line.startsWith("; al_start:")) {
				int value = Integer.parseInt(line.split(":")[1].trim());

				if (inQueryBlock && !inSubjectBlock) {
					builder.queryAlignmentStart(value);
				} else if (!inQueryBlock && inSubjectBlock) {
					builder.subjectAlignmentStart(value);
				} else {
					throw new FastaResultParserException("Line " + lineNumber
							+ ": Alignment start (al_start) detected outside of query or subject block.");
				}
			}

			/*
			 * Alignment stop: matches for example "; al_stop: 50".
			 */
			else if ((builder != null) && line.startsWith("; al_stop:")) {
				int value = Integer.parseInt(line.split(":")[1].trim());

				if (inQueryBlock && !inSubjectBlock) {
					builder.queryAlignmentStop(value);
				} else if (!inQueryBlock && inSubjectBlock) {
					builder.subjectAlignmentStop(value);
				} else {
					throw new FastaResultParserException("Line " + lineNumber
							+ ": Alignment stop (al_stop) detected outside of query or subject block.");
				}
			}

			/*
			 * Alignment display start: matches for example "; al_display_start: 1". The
			 * lines following this line make the alignment string.
			 */
			else if ((builder != null) && line.startsWith("; al_display_start:")) {
				int value = Integer.parseInt(line.split(":")[1].trim());

				if (inQueryBlock && !inSubjectBlock) {
					builder.queryAlignmentDisplayStart(value);
				} else if (!inQueryBlock && inSubjectBlock) {
					builder.subjectAlignmentDisplayStart(value);
				} else {
					throw new FastaResultParserException("Line " + lineNumber
							+ ": Alignment display start (al_display_start) detected outside of query or subject block.");
				}

				/*
				 * It is not possible to optimize the capacity of this StringBuilder, because
				 * the length of the alignment that is printed is not known (PS: One could
				 * certainly check the source code of the FASTA program to find this ...). Going
				 * for querySequenceLength or subjectSequenceLength could be overkill.
				 */
				sequenceBuilder = new StringBuilder();
				inSequence = true;
			}

			/*
			 * Start of alignment consensus: matches "; al_cons:". The lines following this
			 * line make the consensus string. This also terminates the subject alignment
			 * string and the subject block.
			 */
			else if ((builder != null) && "; al_cons:".equals(line)) {
				if (inSequence) {
					builder.subjectAlignmentLine(sequenceBuilder.toString());
					inSequence = false;
					sequenceBuilder = null;
				}
				inSubjectBlock = false;

				// start the alignment string
				sequenceBuilder = new StringBuilder();
				inSequence = true;
			}

			/*
			 * Line of a sequence.
			 */
			else if ((builder != null) && inSequence) {
				sequenceBuilder.append(line);
			}
		}

		return results;
	}
}