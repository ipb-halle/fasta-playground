package de.ipb_halle.fasta_playground.fastaresult;

public class FastaResultBuilder {
	private double bitScore;
	private String expectationValue;
	private int smithWatermanScore;
	private double identity;
	private double similarity;
	private int overlap;
	private String querySequenceName;
	private String querySequenceDescription;
	private int querySequenceLength;
	private int queryAlignmentStart;
	private int queryAlignmentStop;
	private int queryAlignmentDisplayStart;
	private String queryAlignmentLine;
	private String subjectSequenceName;
	private String subjectSequenceDescription;
	private int subjectSequenceLength;
	private int subjectAlignmentStart;
	private int subjectAlignmentStop;
	private int subjectAlignmentDisplayStart;
	private String subjectAlignmentLine;
	private String consensusLine;

	protected FastaResultBuilder() {
	}

	public FastaResult build() {
		return new FastaResult(this);
	}

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

	public FastaResultBuilder bitScore(double bitScore) {
		this.bitScore = bitScore;
		return this;
	}

	public FastaResultBuilder expectationValue(String expectationValue) {
		this.expectationValue = expectationValue;
		return this;
	}

	public FastaResultBuilder smithWatermanScore(int smithWatermanScore) {
		this.smithWatermanScore = smithWatermanScore;
		return this;
	}

	public FastaResultBuilder identity(double identity) {
		this.identity = identity;
		return this;
	}

	public FastaResultBuilder similarity(double similarity) {
		this.similarity = similarity;
		return this;
	}

	public FastaResultBuilder overlap(int overlap) {
		this.overlap = overlap;
		return this;
	}

	public FastaResultBuilder querySequenceName(String querySequenceName) {
		this.querySequenceName = querySequenceName;
		return this;
	}

	public FastaResultBuilder querySequenceDescription(String querySequenceDescription) {
		this.querySequenceDescription = querySequenceDescription;
		return this;
	}

	public FastaResultBuilder querySequenceLength(int querySequenceLength) {
		this.querySequenceLength = querySequenceLength;
		return this;
	}

	public FastaResultBuilder queryAlignmentStart(int queryAlignmentStart) {
		this.queryAlignmentStart = queryAlignmentStart;
		return this;
	}

	public FastaResultBuilder queryAlignmentStop(int queryAlignmentStop) {
		this.queryAlignmentStop = queryAlignmentStop;
		return this;
	}

	public FastaResultBuilder queryAlignmentDisplayStart(int queryAlignmentDisplayStart) {
		this.queryAlignmentDisplayStart = queryAlignmentDisplayStart;
		return this;
	}

	public FastaResultBuilder queryAlignmentLine(String queryAlignmentLine) {
		this.queryAlignmentLine = queryAlignmentLine;
		return this;
	}

	public FastaResultBuilder subjectSequenceName(String subjectSequenceName) {
		this.subjectSequenceName = subjectSequenceName;
		return this;
	}

	public FastaResultBuilder subjectSequenceDescription(String subjectSequenceDescription) {
		this.subjectSequenceDescription = subjectSequenceDescription;
		return this;
	}

	public FastaResultBuilder subjectSequenceLength(int subjectSequenceLength) {
		this.subjectSequenceLength = subjectSequenceLength;
		return this;
	}

	public FastaResultBuilder subjectAlignmentStart(int subjectAlignmentStart) {
		this.subjectAlignmentStart = subjectAlignmentStart;
		return this;
	}

	public FastaResultBuilder subjectAlignmentStop(int subjectAlignmentStop) {
		this.subjectAlignmentStop = subjectAlignmentStop;
		return this;
	}

	public FastaResultBuilder subjectAlignmentDisplayStart(int subjectAlignmentDisplayStart) {
		this.subjectAlignmentDisplayStart = subjectAlignmentDisplayStart;
		return this;
	}

	public FastaResultBuilder subjectAlignmentLine(String subjectAlignmentLine) {
		this.subjectAlignmentLine = subjectAlignmentLine;
		return this;
	}

	public FastaResultBuilder consensusLine(String consensusLine) {
		this.consensusLine = consensusLine;
		return this;
	}
}