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