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
package de.ipb_halle.fasta_playground.fastaresult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class FrameTest {
	@Test
	public void testFromPattern() throws FastaResultParserException {
		assertEquals(Frame.FORWARD, Frame.fromPattern("f"));
		assertEquals(Frame.REVERSE, Frame.fromPattern("r"));
		assertThrows(FastaResultParserException.class, () -> Frame.fromPattern("abc"));
	}

	@Test
	public void testGetName() {
		assertEquals("forward", Frame.FORWARD.getName());
		assertEquals("reverse", Frame.REVERSE.getName());
	}
}