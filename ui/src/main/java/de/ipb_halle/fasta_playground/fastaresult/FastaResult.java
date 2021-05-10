package de.ipb_halle.fasta_playground.fastaresult;

public class FastaResult {
	// fa_bits
	private double bitScore;

	// fa_expect
	// doubles will be problematic in tests, because the value range is quite large
	// and a delta needs to be defined
	private String expectationValue;

	// sw_score
	private int smithWatermanScore;

	// sw_ident
	private double identity;

	// EBI FASTA calls this "positives"
	// sw_sim
	private double similarity;

	// sw_overlap
	private int overlap;

	// first ">" in result
	private String querySequenceName;

	// same line
	private String querySequenceDescription;

	// first sq_len
	private int querySequenceLength;

	// first al_start
	private int queryAlignmentStart;

	// first al_stop
	private int queryAlignmentStop;

	// first al_display_start
	private int queryAlignmentDisplayStart;

	// lines after line al_display_start until next ";" or ">"
	private String queryAlignmentLine;

	// second ">" in result
	private String subjectSequenceName;

	// same line
	private String subjectSequenceDescription;

	// second sq_len
	private int subjectSequenceLength;

	// second al_start
	private int subjectAlignmentStart;

	// second al_stop
	private int subjectAlignmentStop;

	// second al_display_start
	private int subjectAlignmentDisplayStart;

	// lines after "line al_display_start" until next line starting with ";" or ">"
	private String subjectAlignmentLine;

	// lines after "al_cons" until next line starting with ";" or ">"
	private String consensusLine;

	public double getBitScore() {
		return bitScore;
	}

	public String getExpectationValue() {
		return expectationValue;
	}

	public int getSmithWatermanScore() {
		return smithWatermanScore;
	}

	public double getIdentity() {
		return identity;
	}

	public double getSimilarity() {
		return similarity;
	}

	public int getOverlap() {
		return overlap;
	}

	public String getQuerySequenceName() {
		return querySequenceName;
	}

	public String getQuerySequenceDescription() {
		return querySequenceDescription;
	}

	public int getQuerySequenceLength() {
		return querySequenceLength;
	}

	public int getQueryAlignmentStart() {
		return queryAlignmentStart;
	}

	public int getQueryAlignmentStop() {
		return queryAlignmentStop;
	}

	public int getQueryAlignmentDisplayStart() {
		return queryAlignmentDisplayStart;
	}

	public String getQueryAlignmentLine() {
		return queryAlignmentLine;
	}

	public String getSubjectSequenceName() {
		return subjectSequenceName;
	}

	public String getSubjectSequenceDescription() {
		return subjectSequenceDescription;
	}

	public int getSubjectSequenceLength() {
		return subjectSequenceLength;
	}

	public int getSubjectAlignmentStart() {
		return subjectAlignmentStart;
	}

	public int getSubjectAlignmentStop() {
		return subjectAlignmentStop;
	}

	public int getSubjectAlignmentDisplayStart() {
		return subjectAlignmentDisplayStart;
	}

	public String getSubjectAlignmentLine() {
		return subjectAlignmentLine;
	}

	public String getConsensusLine() {
		return consensusLine;
	}
}