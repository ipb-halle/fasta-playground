package de.ipb_halle.fasta_playground.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import de.ipb_halle.fasta_playground.fastaresult.FastaResult;

public class FastaResultDisplayWrapper {
	private final FastaResult result;
	private FastaResultDisplayConfig config = new FastaResultDisplayConfig();

	public FastaResultDisplayWrapper(FastaResult result) {
		this.result = result;
	}

	public FastaResult getFastaResult() {
		return result;
	}

	public FastaResultDisplayConfig getConfig() {
		return config;
	}

	public FastaResultDisplayWrapper config(FastaResultDisplayConfig config) {
		this.config = config;
		return this;
	}

	static class AlignmentLine {
		private final String line;
		private final int startIndex;
		private final int stopIndex;

		public AlignmentLine(String line, int startIndex, int stopIndex) {
			this.line = line;
			this.startIndex = startIndex;
			this.stopIndex = stopIndex;
		}

		public int getStartIndex() {
			return startIndex;
		}

		public int getStopIndex() {
			return stopIndex;
		}

		public String getLine() {
			return line;
		}
	}

	public String getAlignments() {
		String queryAlignmentLine = replaceLeadingChars(result.getQueryAlignmentLine(), '-', ' ');
		String subjectAlignmentLine = replaceLeadingChars(result.getSubjectAlignmentLine(), '-', ' ');

		List<AlignmentLine> queryLines = createAlignmentLines(queryAlignmentLine,
				result.getQueryAlignmentDisplayStart(), config.getLineLength());
		List<AlignmentLine> subjectLines = createAlignmentLines(subjectAlignmentLine,
				result.getSubjectAlignmentDisplayStart(), config.getLineLength());
		List<String> consenusLines = segmentString(result.getConsensusLine(), config.getLineLength());

		int maxPrefixLength = Integer.toString(maxIndex(l -> l.getStartIndex(), queryLines, subjectLines)).length();
		int maxSuffixLength = Integer.toString(maxIndex(l -> l.getStopIndex(), queryLines, subjectLines)).length();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Math.max(queryLines.size(), subjectLines.size()); i++) {
			if (i != 0) {
				sb.append("\n\n");
			}

			// query alignment line
			sb.append("Query");
			if (i < queryLines.size()) {
				AlignmentLine line = queryLines.get(i);

				if (!StringUtils.isBlank(line.getLine())) {
					// leading spaces
					sb.append(StringUtils.leftPad("", "Subject".length() - "Query".length() + 1));

					// start index of this line
					sb.append(StringUtils.leftPad(Integer.toString(line.getStartIndex()), maxPrefixLength));

					// fill up with spaces till sequence start
					sb.append(StringUtils.leftPad("", config.getPrefixSpaces()));

					// sequence filled up with spaces
					int lengthOfSubjectLine = (i < subjectLines.size()) ? subjectLines.get(i).getLine().length() : 0;
					sb.append(StringUtils.rightPad(line.getLine(),
							Math.max(line.getLine().length(), lengthOfSubjectLine) + config.getSuffixSpaces()));

					// stop index of this line
					sb.append(StringUtils.leftPad(Integer.toString(line.getStopIndex()), maxSuffixLength));
				}
			}
			sb.append("\n");

			// consensus line
			if (i < consenusLines.size()) {
				String line = consenusLines.get(i);
				if (!StringUtils.isBlank(line)) {
					// leading spaces
					sb.append(StringUtils.leftPad("",
							"Subject".length() + 1 + maxPrefixLength + config.getPrefixSpaces()));

					// consensus
					sb.append(line);
				}
			}
			sb.append("\n");

			// subject alignment line
			sb.append("Subject");
			if (i < subjectLines.size()) {
				AlignmentLine line = subjectLines.get(i);

				if (!StringUtils.isBlank(line.getLine())) {
					// leading space
					sb.append(" ");

					// start index of this line
					sb.append(StringUtils.leftPad(Integer.toString(line.getStartIndex()), maxPrefixLength));

					// fill up with spaces till sequence start
					sb.append(StringUtils.leftPad("", config.getPrefixSpaces()));

					// sequence filled up with spaces
					int lengthOfQueryLine = (i < queryLines.size()) ? queryLines.get(i).getLine().length() : 0;
					sb.append(StringUtils.rightPad(line.getLine(),
							Math.max(line.getLine().length(), lengthOfQueryLine) + config.getSuffixSpaces()));

					// stop index of this line
					sb.append(StringUtils.leftPad(Integer.toString(line.getStopIndex()), maxSuffixLength));
				}
			}
		}

		return sb.toString();
	}

	static String replaceLeadingChars(String input, char oldChar, char newChar) {
		char[] chars = input.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == oldChar) {
				chars[i] = newChar;
			} else {
				break;
			}
		}

		return new String(chars);
	}

	static List<String> segmentString(String input, int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length cannot be less than 1");
		}
		List<String> results = new ArrayList<>();

		for (int i = 0; i < input.length(); i += length) {
			// StringUtils.substring is a bit more greedy than String.subString
			String segment = StringUtils.substring(input, i, i + length);
			results.add(segment);
		}

		return results;
	}

	static List<AlignmentLine> createAlignmentLines(String sequence, int start, int lineLength) {
		List<AlignmentLine> results = new ArrayList<>();
		int startIndex = start;
		int endIndex;

		for (String segment : segmentString(sequence, lineLength)) {
			endIndex = startIndex - 1 + segment.length() - StringUtils.countMatches(segment, ' ')
					- StringUtils.countMatches(segment, '-');
			results.add(new AlignmentLine(segment, startIndex, endIndex));
			startIndex = endIndex + 1;
		}

		return results;
	}

	@SafeVarargs
	private static int maxIndex(Function<AlignmentLine, Integer> function, List<AlignmentLine>... lists) {
		int max = Integer.MIN_VALUE;

		for (List<AlignmentLine> list : lists) {
			for (AlignmentLine line : list) {
				int value = function.apply(line);
				if (value > max) {
					max = value;
				}
			}
		}

		return max;
	}
}