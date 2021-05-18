package de.ipb_halle.fasta_playground.fastaresult;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.junit.Test;

public class FastaResultParserTest {
	// relative error in tests with double values
	private static double delta = 0.001d;

	@Test
	public void testParseResults1() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results1.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();
		double r;

		assertEquals(4, res.size());

		assertEquals(99.1d, r = res.get(0).getBitScore(), r * delta);
		assertEquals(8.7e-26d, r = res.get(0).getExpectationValue(), r * delta);
		assertEquals(313, res.get(0).getSmithWatermanScore());
		assertEquals(1.000d, r = res.get(0).getIdentity(), r * delta);
		assertEquals(1.000d, r = res.get(0).getSimilarity(), r * delta);
		assertEquals(50, res.get(0).getOverlap());
		assertEquals("query1", res.get(0).getQuerySequenceName());
		assertEquals("query sequence", res.get(0).getQuerySequenceDescription());
		assertEquals(50, res.get(0).getQuerySequenceLength());
		assertEquals(1, res.get(0).getQueryAlignmentStart());
		assertEquals(50, res.get(0).getQueryAlignmentStop());
		assertEquals(1, res.get(0).getQueryAlignmentDisplayStart());
		assertEquals("------------------------------SAVQQKLAALEKSSGGRLGV"
		           + "ALIDTADNTQVLYRGDERFPMCSTSKVMAA",
				res.get(0).getQueryAlignmentLine());
		assertEquals("gb|AAF72530.1|AF252622_1", res.get(0).getSubjectSequenceName());
		assertEquals("beta-lactamase CTX-M-14 (plasmid) [Escherichia coli]",
				res.get(0).getSubjectSequenceDescription());
		assertEquals(291, res.get(0).getSubjectSequenceLength());
		assertEquals(31, res.get(0).getSubjectAlignmentStart());
		assertEquals(80, res.get(0).getSubjectAlignmentStop());
		assertEquals(1, res.get(0).getSubjectAlignmentDisplayStart());
		assertEquals("MVTKRVQRMMFAAAACIPLLLGSAPLYAQTSAVQQKLAALEKSSGGRLGV"
				   + "ALIDTADNTQVLYRGDERFPMCSTSKVMAAAAVLKQSETQKQLLNQPVEI"
				   + "KPADLVNYNPIAEKHVNGTM", res.get(0).getSubjectAlignmentLine());
		assertEquals("                              ::::::::::::::::::::"
				   + "::::::::::::::::::::::::::::::",
				res.get(0).getConsensusLine());

		assertEquals(36.7d, r = res.get(1).getBitScore(), r * delta);
		assertEquals(5e-07d, r = res.get(1).getExpectationValue(), r * delta);
		assertEquals(100, res.get(1).getSmithWatermanScore());
		assertEquals(0.400d, r = res.get(1).getIdentity(), r * delta);
		assertEquals(0.733d, r = res.get(1).getSimilarity(), r * delta);
		assertEquals(45, res.get(1).getOverlap());
		assertEquals("query1", res.get(1).getQuerySequenceName());
		assertEquals("query sequence", res.get(1).getQuerySequenceDescription());
		assertEquals(50, res.get(1).getQuerySequenceLength());
		assertEquals(5, res.get(1).getQueryAlignmentStart());
		assertEquals(48, res.get(1).getQueryAlignmentStop());
		assertEquals(1, res.get(1).getQueryAlignmentDisplayStart());
		assertEquals("----------------------SAVQQKLAALEKSSGGRLGVALIDTADN"
				   + "-TQVLYRGDERFPMCSTSKVMAA",
				res.get(1).getQueryAlignmentLine());
		assertEquals("gb|AAP20890.1|", res.get(1).getSubjectSequenceName());
		assertEquals("extended-spectrum beta-lactamase SHV-48 [Acinetobacter baumannii]",
				res.get(1).getSubjectSequenceDescription());
		assertEquals(286, res.get(1).getSubjectSequenceLength());
		assertEquals(27, res.get(1).getSubjectAlignmentStart());
		assertEquals(71, res.get(1).getSubjectAlignmentStop());
		assertEquals(1, res.get(1).getSubjectAlignmentDisplayStart());
		assertEquals("MRYIRLCIISLLATLPLAVHASPQPLEQIKQSESQLSGRVGMIEMDLASG"
				   + "RTLTAWRADERFPMMSTFKVVLCGAVLARVDAGDEQLERKIHYRQQDLVD"
				   + "YSPVSEKHLADGMTVGELCA",
				res.get(1).getSubjectAlignmentLine());
		assertEquals("                          ...   :.. .::.:.  .: :.."
				   + "-: . .:.:::::: :: ::.",
				res.get(1).getConsensusLine());

		assertEquals(36.7d, r = res.get(2).getBitScore(), r * delta);
		assertEquals(5e-07d, r = res.get(2).getExpectationValue(), r * delta);
		assertEquals(100, res.get(2).getSmithWatermanScore());
		assertEquals(0.400d, r = res.get(2).getIdentity(), r * delta);
		assertEquals(0.733d, r = res.get(2).getSimilarity(), r * delta);
		assertEquals(45, res.get(2).getOverlap());
		assertEquals("query1", res.get(2).getQuerySequenceName());
		assertEquals("query sequence", res.get(2).getQuerySequenceDescription());
		assertEquals(50, res.get(2).getQuerySequenceLength());
		assertEquals(5, res.get(2).getQueryAlignmentStart());
		assertEquals(48, res.get(2).getQueryAlignmentStop());
		assertEquals(1, res.get(2).getQueryAlignmentDisplayStart());
		assertEquals("----------------------SAVQQKLAALEKSSGGRLGVALIDTADN"
				   + "-TQVLYRGDERFPMCSTSKVMAA",
				res.get(2).getQueryAlignmentLine());
		assertEquals("gb|AAP20889.1|", res.get(2).getSubjectSequenceName());
		assertEquals("extended-spectrum beta-lactamase SHV-12 [Acinetobacter baumannii]",
				res.get(2).getSubjectSequenceDescription());
		assertEquals(286, res.get(2).getSubjectSequenceLength());
		assertEquals(27, res.get(2).getSubjectAlignmentStart());
		assertEquals(71, res.get(2).getSubjectAlignmentStop());
		assertEquals(1, res.get(2).getSubjectAlignmentDisplayStart());
		assertEquals("MRYIRLCIISLLATLPLAVHASPQPLEQIKQSESQLSGRVGMIEMDLASG"
				   + "RTLTAWRADERFPMMSTFKVVLCGAVLARVDAGDEQLERKIHYRQQDLVD"
				   + "YSPVSEKHLADGMTVGELCA",
				res.get(2).getSubjectAlignmentLine());
		assertEquals("                          ...   :.. .::.:.  .: :.."
				   + "-: . .:.:::::: :: ::.",
				res.get(2).getConsensusLine());

		assertEquals(15.1d, r = res.get(3).getBitScore(), r * delta);
		assertEquals(0.73d, r = res.get(3).getExpectationValue(), r * delta);
		assertEquals(33, res.get(3).getSmithWatermanScore());
		assertEquals(0.417d, r = res.get(3).getIdentity(), r * delta);
		assertEquals(0.583d, r = res.get(3).getSimilarity(), r * delta);
		assertEquals(24, res.get(3).getOverlap());
		assertEquals("query1", res.get(3).getQuerySequenceName());
		assertEquals("query sequence", res.get(3).getQuerySequenceDescription());
		assertEquals(50, res.get(3).getQuerySequenceLength());
		assertEquals(15, res.get(3).getQueryAlignmentStart());
		assertEquals(35, res.get(3).getQueryAlignmentStop());
		assertEquals(1, res.get(3).getQueryAlignmentDisplayStart());
		assertEquals("----------------SAVQQKLAALEKSSGGRLGVALI---DTADNTQV"
				   + "LYRGDERFPMCSTSKVMAA",
				res.get(3).getQueryAlignmentLine());
		assertEquals("gb|AAK07468.1|", res.get(3).getSubjectSequenceName());
		assertEquals("SHV beta-lactamase, partial [Escherichia coli]", res.get(3).getSubjectSequenceDescription());
		assertEquals(139, res.get(3).getSubjectSequenceLength());
		assertEquals(115, res.get(3).getSubjectAlignmentStart());
		assertEquals(138, res.get(3).getSubjectAlignmentStop());
		assertEquals(85, res.get(3).getSubjectAlignmentDisplayStart());
		assertEquals("MVDDRVAGPLIRSVLPAGWFIADKTGASKRGARGIVALLGPNNKAERIVV"
				   + "LYIGX", res.get(3).getSubjectAlignmentLine());
		assertEquals("                              :.:  :::.---. :.   :"
				   + ":: :", res.get(3).getConsensusLine());
	}

	@Test
	public void testParseResults2() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results2.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();
		double r;

		assertEquals(1, res.size());

		assertEquals(6.5d, r = res.get(0).getBitScore(), r * delta);
		assertEquals(0.72d, r = res.get(0).getExpectationValue(), r * delta);
		assertEquals(13, res.get(0).getSmithWatermanScore());
		assertEquals(1.000d, r = res.get(0).getIdentity(), r * delta);
		assertEquals(1.000d, r = res.get(0).getSimilarity(), r * delta);
		assertEquals(1, res.get(0).getOverlap());
		assertEquals("query1", res.get(0).getQuerySequenceName());
		assertEquals("", res.get(0).getQuerySequenceDescription());
		assertEquals(3, res.get(0).getQuerySequenceLength());
		assertEquals(3, res.get(0).getQueryAlignmentStart());
		assertEquals(3, res.get(0).getQueryAlignmentStop());
		assertEquals(1, res.get(0).getQueryAlignmentDisplayStart());
		assertEquals("----------------------------ABC",	res.get(0).getQueryAlignmentLine());
		assertEquals("seq1", res.get(0).getSubjectSequenceName());
		assertEquals("", res.get(0).getSubjectSequenceDescription());
		assertEquals(39, res.get(0).getSubjectSequenceLength());
		assertEquals(31, res.get(0).getSubjectAlignmentStart());
		assertEquals(31, res.get(0).getSubjectAlignmentStop());
		assertEquals(1, res.get(0).getSubjectAlignmentDisplayStart());
		assertEquals("KSSGGRLGVALIDTADNTQVLYRGDERFPMCSTSKVMAA", res.get(0).getSubjectAlignmentLine());
		assertEquals("                              :",	res.get(0).getConsensusLine());
	}

	@Test
	public void testParseResults3() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results3.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();
		double r;

		assertEquals(1, res.size());

		assertEquals(6.3d, r = res.get(0).getBitScore(), r * delta);
		assertEquals(0.76d, r = res.get(0).getExpectationValue(), r * delta);
		assertEquals(13, res.get(0).getSmithWatermanScore());
		assertEquals(1.000d, r = res.get(0).getIdentity(), r * delta);
		assertEquals(1.000d, r = res.get(0).getSimilarity(), r * delta);
		assertEquals(1, res.get(0).getOverlap());
		assertEquals("query1", res.get(0).getQuerySequenceName());
		assertEquals("", res.get(0).getQuerySequenceDescription());
		assertEquals(39, res.get(0).getQuerySequenceLength());
		assertEquals(31, res.get(0).getQueryAlignmentStart());
		assertEquals(31, res.get(0).getQueryAlignmentStop());
		assertEquals(1, res.get(0).getQueryAlignmentDisplayStart());
		assertEquals("KSSGGRLGVALIDTADNTQVLYRGDERFPMCSTSKVMAA", res.get(0).getQueryAlignmentLine());
		assertEquals("seq1", res.get(0).getSubjectSequenceName());
		assertEquals("", res.get(0).getSubjectSequenceDescription());
		assertEquals(3, res.get(0).getSubjectSequenceLength());
		assertEquals(3, res.get(0).getSubjectAlignmentStart());
		assertEquals(3, res.get(0).getSubjectAlignmentStop());
		assertEquals(1, res.get(0).getSubjectAlignmentDisplayStart());
		assertEquals("----------------------------ABC", res.get(0).getSubjectAlignmentLine());
		assertEquals("                              :",	res.get(0).getConsensusLine());
	}

	@Test
	public void testParseResults4() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results4.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();
		double r;

		assertEquals(1, res.size());

		assertEquals(6.5d, r = res.get(0).getBitScore(), r * delta);
		assertEquals(0.73d, r = res.get(0).getExpectationValue(), r * delta);
		assertEquals(13, res.get(0).getSmithWatermanScore());
		assertEquals(1.000d, r = res.get(0).getIdentity(), r * delta);
		assertEquals(1.000d, r = res.get(0).getSimilarity(), r * delta);
		assertEquals(1, res.get(0).getOverlap());
		assertEquals("", res.get(0).getQuerySequenceName());
		assertEquals("", res.get(0).getQuerySequenceDescription());
		assertEquals(3, res.get(0).getQuerySequenceLength());
		assertEquals(3, res.get(0).getQueryAlignmentStart());
		assertEquals(3, res.get(0).getQueryAlignmentStop());
		assertEquals(1, res.get(0).getQueryAlignmentDisplayStart());
		assertEquals("----------------------------ABC", res.get(0).getQueryAlignmentLine());
		assertEquals(">data4.fasta", res.get(0).getSubjectSequenceName());
		assertEquals("", res.get(0).getSubjectSequenceDescription());
		assertEquals(39, res.get(0).getSubjectSequenceLength());
		assertEquals(31, res.get(0).getSubjectAlignmentStart());
		assertEquals(31, res.get(0).getSubjectAlignmentStop());
		assertEquals(1, res.get(0).getSubjectAlignmentDisplayStart());
		assertEquals("KSSGGRLGVALIDTADNTQVLYRGDERFPMCSTSKVMAA", res.get(0).getSubjectAlignmentLine());
		assertEquals("                              :",	res.get(0).getConsensusLine());
	}

	@Test
	public void testParseResults5() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results5.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();

		assertEquals(0, res.size());
	}

	@Test
	public void testParseResults6() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results6.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();

		assertEquals(0, res.size());
	}
}