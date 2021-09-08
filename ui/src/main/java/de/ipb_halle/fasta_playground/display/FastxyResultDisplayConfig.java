package de.ipb_halle.fasta_playground.display;

public class FastxyResultDisplayConfig extends ResultDisplayConfig {
	public FastxyResultDisplayConfig() {
		super();

		// DNA translated to Protein
		setQueryLineIndexMultiplier(3);
		setQueryAlignmentCanReverse(true);

		// Protein
		setSubjectLineIndexMultiplier(1);
		setSubjectAlignmentCanReverse(false);
	}
}