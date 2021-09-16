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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.ipb_halle.fasta_playground.display.FastaResultDisplayConfig;
import de.ipb_halle.fasta_playground.display.FastxyResultDisplayConfig;
import de.ipb_halle.fasta_playground.display.TfastxyResultDisplayConfig;
import de.ipb_halle.fasta_playground.search.factories.DNADNASearchFactory;
import de.ipb_halle.fasta_playground.search.factories.DNAProteinSearchFactory;
import de.ipb_halle.fasta_playground.search.factories.ProteinDNASearchFactory;
import de.ipb_halle.fasta_playground.search.factories.ProteinProteinSearchFactory;

public class SearchFactoriesTest {
	@Test
	public void testDNADNASearchFactory() {
		SearchFactory factory = new DNADNASearchFactory.Builder().build();
		assertTrue(factory instanceof DNADNASearchFactory);

		assertEquals("fasta36", factory.getProgramName());
		assertArrayEquals(new String[] { "-n" }, factory.getParams());

		assertSame(factory, factory.withTranslationTable(TranslationTable.ALTERNATIVE_YEAST_NUCLEAR));

		assertTrue(factory.getDisplayConfig() instanceof FastaResultDisplayConfig);
	}

	@Test
	public void testProteinProteinSearchFactory() {
		SearchFactory factory = new ProteinProteinSearchFactory.Builder().build();
		assertTrue(factory instanceof ProteinProteinSearchFactory);

		assertEquals("fasta36", factory.getProgramName());
		assertArrayEquals(new String[] { "-p" }, factory.getParams());

		assertSame(factory, factory.withTranslationTable(TranslationTable.BACTERIAL_AND_PLANT_PLASTID));

		assertTrue(factory.getDisplayConfig() instanceof FastaResultDisplayConfig);
	}

	@Test
	public void testDNAProteinSearchFactory() {
		SearchFactory factory = new DNAProteinSearchFactory.Builder().build();
		assertTrue(factory instanceof DNAProteinSearchFactory);

		assertEquals("fastx36", factory.getProgramName());
		// expect standard translation table
		assertArrayEquals(new String[] { "-n", "-t 1" }, factory.getParams());

		assertSame(factory, factory.withTranslationTable(TranslationTable.ASCIDIAN_MITOCHONDRIAL));
		assertArrayEquals(new String[] { "-n", "-t 13" }, factory.getParams());

		assertTrue(factory.getDisplayConfig() instanceof FastxyResultDisplayConfig);
	}

	@Test
	public void testProteinDNASearchFactory() {
		SearchFactory factory = new ProteinDNASearchFactory.Builder().build();
		assertTrue(factory instanceof ProteinDNASearchFactory);

		assertEquals("tfastx36", factory.getProgramName());
		// expect standard translation table
		assertArrayEquals(new String[] { "-p", "-t 1" }, factory.getParams());

		assertSame(factory, factory.withTranslationTable(TranslationTable.SCENEDESMUS_OBLIQUUS_MITOCHONDRIAL));
		assertArrayEquals(new String[] { "-p", "-t 22" }, factory.getParams());

		assertTrue(factory.getDisplayConfig() instanceof TfastxyResultDisplayConfig);
	}
}