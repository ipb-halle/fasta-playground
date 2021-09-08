package de.ipb_halle.fasta_playground.display;

public class TfastxyResultDisplayConfig extends ResultDisplayConfig {
	public TfastxyResultDisplayConfig() {
		super();

		// Protein
		setQueryLineIndexMultiplier(1);
		setQueryAlignmentCanReverse(false);

		// DNA translated to Protein
		setSubjectLineIndexMultiplier(3);
		setSubjectAlignmentCanReverse(true);
	}
}