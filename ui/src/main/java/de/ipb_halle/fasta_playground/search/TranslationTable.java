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

public enum TranslationTable {
	/*
	 * See https://github.com/wrpearson/fasta36/blob/v36.3.8/src/faatran.c for the
	 * list of available translation tables in fasta36. The names were taken from
	 * https://www.ncbi.nlm.nih.gov/Taxonomy/Utils/wprintgc.cgi and
	 * https://www.ebi.ac.uk/seqdb/confluence/display/JDSAT/FASTA+Help+and+Documentation
	 */
	STANDARD(1, "Standard"),
	VERTEBRATE_MITOCHONDRIAL(2, "Vertebrate Mitochondrial"),
	YEAST_MITOCHONDRIAL(3, "Yeast Mitochondrial"),
	MOLD_MITOCHONDRIAL_PROTOZOAN_MITOCHONDRIAL_COELENTERATE(4, "Mold, Protozoan, and Coelenterate Mitochondrial and Mycoplasma/Spiroplasma"),
	INVERTEBRATE_MITOCHONDRIAL(5, "Invertebrate Mitochondrial"),
	CILIATE_NUCLEAR_DASYCLADACEAN_NUCLEAR_HEXAMITA_NUCLEAR(6, "Ciliate, Dasycladacean and Hexamita Nuclear"),
	ECHINODERM_MITOCHONDRIAL_FLATWORM_MITOCHONDRIAL(9, "Echinoderm and Flatworm Mitochondrial"),
	EUPLOTID_NUCLEAR(10, "Euplotid Nuclear"),
	BACTERIAL_AND_PLANT_PLASTID(11, "Bacterial, Archaeal and Plant Plastid"),
	ALTERNATIVE_YEAST_NUCLEAR(12, "Alternative Yeast Nuclear"),
	ASCIDIAN_MITOCHONDRIAL(13, "Ascidian Mitochondrial"),
	ALTERNATIVE_FLATWORM_MITOCHONDRIAL(14, "Alternative Flatworm Mitochondrial"),
	BLEPHARISMA_MACRONUCLEAR(15, "Blepharisma Macronuclear"),
	CHLOROPHYCEAN_MITOCHONDRIAL(16, "Chlorophycean Mitochondrial"),
	TREMATODE_MITOCHONDRIAL(21, "Trematode Mitochondrial"),
	SCENEDESMUS_OBLIQUUS_MITOCHONDRIAL(22, "Scenedesmus obliquus Mitochondrial"),
	THRAUSTOCHYTRIUM_MITOCHONDRIAL(23, "Thraustochytrium Mitochondrial");

	private final int id;
	private final String name;

	TranslationTable(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}