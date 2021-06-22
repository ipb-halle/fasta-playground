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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FastaResultDisplayConfigTest {
	@Test
	public void testDefaults() {
		FastaResultDisplayConfig config = new FastaResultDisplayConfig();
		assertTrue(config.getLineLength() > 0);
		assertTrue(config.getPrefixSpaces() > 0);
		assertTrue(config.getSuffixSpaces() > 0);
	}

	@Test
	public void testSettersAndGetters() {
		FastaResultDisplayConfig config = new FastaResultDisplayConfig();

		config.setLineLength(3489775);
		assertEquals(3489775, config.getLineLength());
		config.setPrefixSpaces(3489775);
		assertEquals(3489775, config.getPrefixSpaces());
		config.setSuffixSpaces(3489775);
		assertEquals(3489775, config.getSuffixSpaces());
	}

	@Test
	public void testExceptions() {
		FastaResultDisplayConfig config = new FastaResultDisplayConfig();

		config.setLineLength(1);
		assertThrows(IllegalArgumentException.class, () -> config.setLineLength(0));
		assertThrows(IllegalArgumentException.class, () -> config.setLineLength(-1));

		config.setPrefixSpaces(1);
		assertThrows(IllegalArgumentException.class, () -> config.setPrefixSpaces(0));
		assertThrows(IllegalArgumentException.class, () -> config.setPrefixSpaces(-1));

		config.setSuffixSpaces(1);
		assertThrows(IllegalArgumentException.class, () -> config.setSuffixSpaces(0));
		assertThrows(IllegalArgumentException.class, () -> config.setSuffixSpaces(-1));
	}
}