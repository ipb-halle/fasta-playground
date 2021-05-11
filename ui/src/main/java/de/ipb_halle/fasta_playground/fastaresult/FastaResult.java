package de.ipb_halle.fasta_playground.fastaresult;

public class FastaResult {
	// fa_bits
	private double bitScore;

	// fa_expect
	// doubles will be problematic in tests, because the value range is quite large
	// and a delta needs to be defined
	private final String expectationValue;

	// sw_score
	private final int smithWatermanScore;

	// sw_ident
	private final double identity;

	// EBI FASTA calls this "positives"
	// sw_sim
	private final double similarity;

	// sw_overlap
	private final int overlap;

	// first ">" in result
	private final String querySequenceName;

	// same line
	private final String querySequenceDescription;

	// first sq_len
	private final int querySequenceLength;

	// first al_start
	private final int queryAlignmentStart;

	// first al_stop
	private final int queryAlignmentStop;

	// first al_display_start
	private final int queryAlignmentDisplayStart;

	// lines after line al_display_start until next ";" or ">"
	private final String queryAlignmentLine;

	// second ">" in result
	private final String subjectSequenceName;

	// same line
	private final String subjectSequenceDescription;

	// second sq_len
	private final int subjectSequenceLength;

	// second al_start
	private final int subjectAlignmentStart;

	// second al_stop
	private final int subjectAlignmentStop;

	// second al_display_start
	private final int subjectAlignmentDisplayStart;

	// lines after "line al_display_start" until next line starting with ";" or ">"
	private final String subjectAlignmentLine;

	// lines after "al_cons" until next line starting with ";" or ">"
	private final String consensusLine;

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

	public static FastaResultBuilder builder() {
		return new FastaResultBuilder();
	}

	public FastaResult(FastaResultBuilder builder) {
		this.bitScore = builder.getBitScore();
		this.expectationValue = builder.getExpectationValue();
		this.smithWatermanScore = builder.getSmithWatermanScore();
		this.identity = builder.getIdentity();
		this.similarity = builder.getSimilarity();
		this.overlap = builder.getOverlap();
		this.querySequenceName = builder.getQuerySequenceName();
		this.querySequenceDescription = builder.getQuerySequenceDescription();
		this.querySequenceLength = builder.getQuerySequenceLength();
		this.queryAlignmentStart = builder.getQueryAlignmentStart();
		this.queryAlignmentStop = builder.getQueryAlignmentStop();
		this.queryAlignmentDisplayStart = builder.getQueryAlignmentDisplayStart();
		this.queryAlignmentLine = builder.getQueryAlignmentLine();
		this.subjectSequenceName = builder.getSubjectSequenceName();
		this.subjectSequenceDescription = builder.getSubjectSequenceDescription();
		this.subjectSequenceLength = builder.getSubjectSequenceLength();
		this.subjectAlignmentStart = builder.getSubjectAlignmentStart();
		this.subjectAlignmentStop = builder.getSubjectAlignmentStop();
		this.subjectAlignmentDisplayStart = builder.getSubjectAlignmentDisplayStart();
		this.subjectAlignmentLine = builder.getSubjectAlignmentLine();
		this.consensusLine = builder.getConsensusLine();
	}
}