/*
 * fasta-playground
 * Copyright 2021 Leibniz-Institut für Pflanzenbiochemie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package de.ipb_halle.fasta_playground.bean;

import java.util.Comparator;

import de.ipb_halle.fasta_playground.display.FastaResultDisplayWrapper;

public enum SortItem {
	SUBJECTNAME("Sequence Name (alph.)", Comparator.comparing(f -> f.getFastaResult().getSubjectSequenceName())),
	LENGTH("Hit Length (desc.)",
			Comparator.comparing(f -> f.getFastaResult().getSubjectSequenceLength(), Comparator.reverseOrder())),
	BITSCORE("Bit Score (desc.)",
			Comparator.comparing(f -> f.getFastaResult().getBitScore(), Comparator.reverseOrder())),
	EVALUE("E()-Value (asc.)", Comparator.comparing(f -> f.getFastaResult().getExpectationValue())),
	SMITHWATERMANSCORE("Smith-Waterman Score (desc.)",
			Comparator.comparing(f -> f.getFastaResult().getSmithWatermanScore(), Comparator.reverseOrder())),
	IDENTITY("Identity (desc.)",
			Comparator.comparing(f -> f.getFastaResult().getIdentity(), Comparator.reverseOrder())),
	SIMILARITY("Similarity/Positives (desc.)",
			Comparator.comparing(f -> f.getFastaResult().getSimilarity(), Comparator.reverseOrder()));

	private final String label;

	private final Comparator<FastaResultDisplayWrapper> comparator;

	private SortItem(String label, Comparator<FastaResultDisplayWrapper> comparator) {
		this.label = label;
		this.comparator = comparator;
	}

	public String getLabel() {
		return label;
	}

	public Comparator<FastaResultDisplayWrapper> getComparator() {
		return comparator;
	}
}