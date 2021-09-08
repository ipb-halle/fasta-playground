package de.ipb_halle.fasta_playground.display;

public class FastaResultDisplayConfig extends ResultDisplayConfig {
	public FastaResultDisplayConfig() {
		super();

		// DNA untranslated or Protein
		setQueryLineIndexMultiplier(1);
		setQueryAlignmentCanReverse(true);

		// DNA untranslated or Protein
		setSubjectLineIndexMultiplier(1);
		setSubjectAlignmentCanReverse(false);
	}
}