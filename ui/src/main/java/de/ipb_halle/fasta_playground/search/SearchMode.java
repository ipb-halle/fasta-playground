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
package de.ipb_halle.fasta_playground.search;

import de.ipb_halle.fasta_playground.search.factories.DNADNASearchFactory;
import de.ipb_halle.fasta_playground.search.factories.DNAProteinSearchFactory;
import de.ipb_halle.fasta_playground.search.factories.ProteinDNASearchFactory;
import de.ipb_halle.fasta_playground.search.factories.ProteinProteinSearchFactory;

public enum SearchMode {
	PROTEIN_PROTEIN("Protein query -> Protein library (fasta36 -p)", new ProteinProteinSearchFactory.Builder()),
//	RNA_RNA("RNA query -> RNA library (fasta36 -U)", new RNARNASearchFactory.Builder()),
	DNA_DNA("DNA query -> DNA library (fasta36 -n)", new DNADNASearchFactory.Builder()),
	DNA_PROTEIN("DNA query -> Protein library (fastx36 -n)", new DNAProteinSearchFactory.Builder()),
	PROTEIN_DNA("Protein query -> DNA library (tfastx36 -p)", new ProteinDNASearchFactory.Builder());

	private final String label;

	private final SearchFactory.Builder searchFactoryBuilder;

	SearchMode(String label, SearchFactory.Builder searchFactoryBuilder) {
		this.label = label;
		this.searchFactoryBuilder = searchFactoryBuilder;
	}

	public String getLabel() {
		return label;
	}

	public SearchFactory getSearchFactory() {
		return searchFactoryBuilder.build();
	}
}