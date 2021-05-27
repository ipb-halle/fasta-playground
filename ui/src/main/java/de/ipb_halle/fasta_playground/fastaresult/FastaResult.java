package de.ipb_halle.fasta_playground.fastaresult;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FastaResult {
	/*
	 * fa_frame / sw_frame / fx_frame / tx_frame - frame direction
	 */
	@NotNull
	private final Frame frame;

	/*
	 * fa_bits / sw_bits / fx_bits / tx_bits - bit score
	 */
	private final double bitScore;

	/*
	 * fa_expect / sw_expect / fx_expect / tx_expect - E()-value
	 */
	private final double expectationValue;

	/*
	 * sw_score - Smith-Waterman score (only in protein sequence comparisons)
	 */
	private final int smithWatermanScore;

	/*
	 * sw_ident / bs_ident - identity
	 */
	private final double identity;

	/*
	 * sw_sim / bs_sim - similarity (EBI FASTA calls this "positives")
	 */
	private final double similarity;

	/*
	 * sw_overlap / bs_overlap - overlap
	 */
	private final int overlap;

	/*
	 * Name of the query sequence
	 */
	@NotNull
	private final String querySequenceName;

	/*
	 * Description of the query sequence
	 */
	@NotNull
	private final String querySequenceDescription;

	/*
	 * sq_len - sequence length of the query sequence
	 */
	@Min(1)
	private final int querySequenceLength;

	/*
	 * al_start - alignment start position in the query sequence
	 */
	@Min(1)
	private final int queryAlignmentStart;

	/*
	 * al_stop - alignment stop position in the query sequence
	 */
	@Min(1)
	private final int queryAlignmentStop;

	/*
	 * al_display_start - alignment display start position in the query sequence
	 */
	@Min(1)
	private final int queryAlignmentDisplayStart;

	/*
	 * Query sequence alignment
	 */
	@NotNull
	private final String queryAlignmentLine;

	/*
	 * Name of the subject sequence
	 */
	@NotNull
	private final String subjectSequenceName;

	/*
	 * Description of the subject sequence
	 */
	@NotNull
	private final String subjectSequenceDescription;

	/*
	 * sq_len - sequence length of the subject sequence
	 */
	@Min(1)
	private final int subjectSequenceLength;

	/*
	 * al_start - alignment start position in the subject sequence
	 */
	@Min(1)
	private final int subjectAlignmentStart;

	/*
	 * al_stop - alignment stop position in the subject sequence
	 */
	@Min(1)
	private final int subjectAlignmentStop;

	/*
	 * al_display_start - alignment display start position in the subject sequence
	 */
	@Min(1)
	private final int subjectAlignmentDisplayStart;

	/*
	 * Subject sequence alignment
	 */
	@NotNull
	private final String subjectAlignmentLine;

	/*
	 * Consensus sequence
	 */
	@NotNull
	private final String consensusLine;

	public Frame getFrame() {
		return frame;
	}

	public double getBitScore() {
		return bitScore;
	}

	public double getExpectationValue() {
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

	protected FastaResult(FastaResultBuilder builder) {
		this.frame = builder.getFrame();
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