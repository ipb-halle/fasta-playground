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

		assertEquals(Frame.FORWARD_ZERO, res.get(0).getFrame());
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

		assertEquals(Frame.FORWARD_ZERO, res.get(1).getFrame());
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

		assertEquals(Frame.FORWARD_ZERO, res.get(2).getFrame());
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

		assertEquals(Frame.FORWARD_ZERO, res.get(3).getFrame());
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

		assertEquals(Frame.FORWARD_ZERO, res.get(0).getFrame());
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

		assertEquals(Frame.FORWARD_ZERO, res.get(0).getFrame());
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

		assertEquals(Frame.FORWARD_ZERO, res.get(0).getFrame());
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

	@Test
	public void testParseResults7() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results7.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();
		double r;

		assertEquals(9, res.size());

		assertEquals(Frame.FORWARD_ZERO, res.get(0).getFrame());
		assertEquals(106.5d, r = res.get(0).getBitScore(), r * delta);
		assertEquals(8.6e-28d, r = res.get(0).getExpectationValue(), r * delta);
		assertEquals(313, res.get(0).getSmithWatermanScore());
		assertEquals(1.000d, r = res.get(0).getIdentity(), r * delta);
		assertEquals(1.000d, r = res.get(0).getSimilarity(), r * delta);
		assertEquals(50, res.get(0).getOverlap());
		assertEquals("query", res.get(0).getQuerySequenceName());
		assertEquals("query sequence", res.get(0).getQuerySequenceDescription());
		assertEquals(50, res.get(0).getQuerySequenceLength());
		assertEquals(1, res.get(0).getQueryAlignmentStart());
		assertEquals(50, res.get(0).getQueryAlignmentStop());
		assertEquals(1, res.get(0).getQueryAlignmentDisplayStart());
		assertEquals("------------------------------SAVQQKLAALEKSSGGRLGV"
				   + "ALIDTADNTQVLYRGDERFPMCSTSKVMAA", res.get(0).getQueryAlignmentLine());
		assertEquals("sp|O69395|BLT2_ECOLX", res.get(0).getSubjectSequenceName());
		assertEquals("Beta-lactamase Toho-2 OS=Escherichia coli OX=562 GN=bla PE=3 SV=1", res.get(0).getSubjectSequenceDescription());
		assertEquals(289, res.get(0).getSubjectSequenceLength());
		assertEquals(31, res.get(0).getSubjectAlignmentStart());
		assertEquals(80, res.get(0).getSubjectAlignmentStop());
		assertEquals(1, res.get(0).getSubjectAlignmentDisplayStart());
		assertEquals("MVTKRVQRMMSAAAACIPLLLGSPTLYAQTSAVQQKLAALEKSSGGRLGV"
				   + "ALIDTADNTQVLYRGDERFPMCSTSKVMAAAAVLKQSETQKQLLNQPVEI"
				   + "KPADLVNYNPIAEKHVNGTM", res.get(0).getSubjectAlignmentLine());
		assertEquals("                              ::::::::::::::::::::"
				   + "::::::::::::::::::::::::::::::", res.get(0).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(1).getFrame());
		assertEquals(88.7d, r = res.get(1).getBitScore(), r * delta);
		assertEquals(2e-22d, r = res.get(1).getExpectationValue(), r * delta);
		assertEquals(256, res.get(1).getSmithWatermanScore());
		assertEquals(0.800d, r = res.get(1).getIdentity(), r * delta);
		assertEquals(0.940d, r = res.get(1).getSimilarity(), r * delta);
		assertEquals(50, res.get(1).getOverlap());
		assertEquals("query", res.get(1).getQuerySequenceName());
		assertEquals("query sequence", res.get(1).getQuerySequenceDescription());
		assertEquals(50, res.get(1).getQuerySequenceLength());
		assertEquals(1, res.get(1).getQueryAlignmentStart());
		assertEquals(50, res.get(1).getQueryAlignmentStop());
		assertEquals(1, res.get(1).getQueryAlignmentDisplayStart());
		assertEquals("------------------------------SAVQQKLAALEKSSGGRLGV"
				   + "ALIDTADNTQVLYRGDERFPMCSTSKVMAA", res.get(1).getQueryAlignmentLine());
		assertEquals("sp|O65976|BLC6_SALTM", res.get(1).getSubjectSequenceName());
		assertEquals("Beta-lactamase CTX-M-6 OS=Salmonella typhimurium OX=90371 GN=bla PE=3 SV=1", res.get(1).getSubjectSequenceDescription());
		assertEquals(291, res.get(1).getSubjectSequenceLength());
		assertEquals(31, res.get(1).getSubjectAlignmentStart());
		assertEquals(80, res.get(1).getSubjectAlignmentStop());
		assertEquals(1, res.get(1).getSubjectAlignmentDisplayStart());
		assertEquals("MMTQSIRRSMLTVMATLPLLFSSATLHAQANSVQQQLEALEKSSGGRLGV"
				   + "ALINTADNSQILYVADERFAMCSTSKVMAAAAVLKQSESDKHLLNQRVEI"
				   + "RASDLVNYNPIAEKHVNGTM", res.get(1).getSubjectAlignmentLine());
		assertEquals("                              ..:::.: ::::::::::::"
				   + ":::.::::.:.:: .:::: ::::::::::", res.get(1).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(2).getFrame());
		assertEquals(48.6d, r = res.get(2).getBitScore(), r * delta);
		assertEquals(2.4e-10d, r = res.get(2).getExpectationValue(), r * delta);
		assertEquals(128, res.get(2).getSmithWatermanScore());
		assertEquals(0.449d, r = res.get(2).getIdentity(), r * delta);
		assertEquals(0.735d, r = res.get(2).getSimilarity(), r * delta);
		assertEquals(49, res.get(2).getOverlap());
		assertEquals("query", res.get(2).getQuerySequenceName());
		assertEquals("query sequence", res.get(2).getQuerySequenceDescription());
		assertEquals(50, res.get(2).getQuerySequenceLength());
		assertEquals(2, res.get(2).getQueryAlignmentStart());
		assertEquals(50, res.get(2).getQueryAlignmentStop());
		assertEquals(1, res.get(2).getQueryAlignmentDisplayStart());
		assertEquals("-----------------------------SAVQQKLAALEKSSGGRLGVA"
				   + "LIDTADNTQVLYRGDERFPMCSTSKVMAA", res.get(2).getQueryAlignmentLine());
		assertEquals("sp|P00809|BLAC_BACCE", res.get(2).getSubjectSequenceName());
		assertEquals("Beta-lactamase 1 OS=Bacillus cereus OX=1396 GN=blaY PE=3 SV=1", res.get(2).getSubjectSequenceDescription());
		assertEquals(306, res.get(2).getSubjectSequenceLength());
		assertEquals(50, res.get(2).getSubjectAlignmentStart());
		assertEquals(98, res.get(2).getSubjectAlignmentStop());
		assertEquals(20, res.get(2).getSubjectAlignmentDisplayStart());
		assertEquals("LSITSLEAFTGESLQVEAKEKTGQVKHKNQATHKEFSQLEKKFDARLGVY"
				   + "AIDTGTNQTISYRPNERFAFASTYKALAAGVLLQQNSIDSLNEVITYTKE"
				   + "DLVDYSPVTEKHVDTGMKLG", res.get(2).getSubjectAlignmentLine());
		assertEquals("                              :...... :::.  .:::: "
				   + " :::. :  . :: .::: . :: :..::", res.get(2).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(3).getFrame());
		assertEquals(19.5d, r = res.get(3).getBitScore(), r * delta);
		assertEquals(0.14d, r = res.get(3).getExpectationValue(), r * delta);
		assertEquals(35, res.get(3).getSmithWatermanScore());
		assertEquals(0.357d, r = res.get(3).getIdentity(), r * delta);
		assertEquals(0.786d, r = res.get(3).getSimilarity(), r * delta);
		assertEquals(14, res.get(3).getOverlap());
		assertEquals("query", res.get(3).getQuerySequenceName());
		assertEquals("query sequence", res.get(3).getQuerySequenceDescription());
		assertEquals(50, res.get(3).getQuerySequenceLength());
		assertEquals(16, res.get(3).getQueryAlignmentStart());
		assertEquals(29, res.get(3).getQueryAlignmentStop());
		assertEquals(1, res.get(3).getQueryAlignmentDisplayStart());
		assertEquals("---------------SAVQQKLAALEKSSGGRLGVALIDTADNTQVLYRG"
				   + "DERFPMCSTSKVMAA", res.get(3).getQueryAlignmentLine());
		assertEquals("sp|P00809|BLAC_BACCE", res.get(3).getSubjectSequenceName());
		assertEquals("Beta-lactamase 1 OS=Bacillus cereus OX=1396 GN=blaY PE=3 SV=1", res.get(3).getSubjectSequenceDescription());
		assertEquals(306, res.get(3).getSubjectSequenceLength());
		assertEquals(139, res.get(3).getSubjectAlignmentStart());
		assertEquals(152, res.get(3).getSubjectAlignmentStop());
		assertEquals(109, res.get(3).getSubjectAlignmentDisplayStart());
		assertEquals("SLNEVITYTKEDLVDYSPVTEKHVDTGMKLGEIAEAAVRSSDNTAGNILF"
				   + "NKIGGPKGYEKALRHMGDRITMSNRFETELNEAIPGDIRDTSTAKAIATN"
				   + "LKAFTVGNALPAEKRKILTE", res.get(3).getSubjectAlignmentLine());
		assertEquals("                              :... : . ..:::", res.get(3).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(4).getFrame());
		assertEquals(43.0d, r = res.get(4).getBitScore(), r * delta);
		assertEquals(1.3e-08d, r = res.get(4).getExpectationValue(), r * delta);
		assertEquals(110, res.get(4).getSmithWatermanScore());
		assertEquals(0.408d, r = res.get(4).getIdentity(), r * delta);
		assertEquals(0.714d, r = res.get(4).getSimilarity(), r * delta);
		assertEquals(49, res.get(4).getOverlap());
		assertEquals("query", res.get(4).getQuerySequenceName());
		assertEquals("query sequence", res.get(4).getQuerySequenceDescription());
		assertEquals(50, res.get(4).getQuerySequenceLength());
		assertEquals(2, res.get(4).getQueryAlignmentStart());
		assertEquals(50, res.get(4).getQueryAlignmentStop());
		assertEquals(1, res.get(4).getQueryAlignmentDisplayStart());
		assertEquals("-----------------------------SAVQQKLAALEKSSGGRLGVA"
				   + "LIDTADNTQVLYRGDERFPMCSTSKVMAA", res.get(4).getQueryAlignmentLine());
		assertEquals("sp|Q03680|BLA1_STRCI", res.get(4).getSubjectSequenceName());
		assertEquals("Beta-lactamase 1 OS=Streptomyces cacaoi OX=1898 GN=blaL PE=1 SV=1", res.get(4).getSubjectSequenceDescription());
		assertEquals(325, res.get(4).getSubjectSequenceLength());
		assertEquals(52, res.get(4).getSubjectAlignmentStart());
		assertEquals(100, res.get(4).getSubjectAlignmentStop());
		assertEquals(22, res.get(4).getSubjectAlignmentDisplayStart());
		assertEquals("LVACGQASGSESGQQPGLGGCGTSAHGSADAHEKEFRALEKKFDAHPGVY"
				   + "AIDTRDGQEITHRADERFAYGSTFKALQAGAILAQVLRDGREVRRGAEAD"
				   + "GMDKVVHYGQDAILPNSPVT", res.get(4).getSubjectAlignmentLine());
		assertEquals("                              : .... ::::.  .. :: "
				   + " ::: :. .. .:.:::: . :: :.. :", res.get(4).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(5).getFrame());
		assertEquals(20.5d, r = res.get(5).getBitScore(), r * delta);
		assertEquals(0.078d, r = res.get(5).getExpectationValue(), r * delta);
		assertEquals(38, res.get(5).getSmithWatermanScore());
		assertEquals(0.700d, r = res.get(5).getIdentity(), r * delta);
		assertEquals(0.800d, r = res.get(5).getSimilarity(), r * delta);
		assertEquals(10, res.get(5).getOverlap());
		assertEquals("query", res.get(5).getQuerySequenceName());
		assertEquals("query sequence", res.get(5).getQuerySequenceDescription());
		assertEquals(50, res.get(5).getQuerySequenceLength());
		assertEquals(14, res.get(5).getQueryAlignmentStart());
		assertEquals(23, res.get(5).getQueryAlignmentStop());
		assertEquals(1, res.get(5).getQueryAlignmentDisplayStart());
		assertEquals("-----------------SAVQQKLAALEKSSGGRLGVALIDTADNTQVLY"
				   + "RGDERFPMCSTSKVMAA", res.get(5).getQueryAlignmentLine());
		assertEquals("sp|Q03680|BLA1_STRCI", res.get(5).getSubjectSequenceName());
		assertEquals("Beta-lactamase 1 OS=Streptomyces cacaoi OX=1898 GN=blaL PE=1 SV=1", res.get(5).getSubjectSequenceDescription());
		assertEquals(325, res.get(5).getSubjectSequenceLength());
		assertEquals(248, res.get(5).getSubjectAlignmentStart());
		assertEquals(257, res.get(5).getSubjectAlignmentStop());
		assertEquals(218, res.get(5).getSubjectAlignmentDisplayStart());
		assertEquals("FAEDLRAFAVEDGEKAALAPNDREQLNDWMSGSRTGDALIRAGVPKDWKV"
				   + "EDKSGQVKYGTRNDIAVVRPPGRAPIVVSVMSHGDTQDAEPHDELVAEAG"
				   + "LVVADGLK", res.get(5).getSubjectAlignmentLine());
		assertEquals("                              ::.: : :::", res.get(5).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(6).getFrame());
		assertEquals(38.9d, r = res.get(6).getBitScore(), r * delta);
		assertEquals(1.9e-07d, r = res.get(6).getExpectationValue(), r * delta);
		assertEquals(97, res.get(6).getSmithWatermanScore());
		assertEquals(0.354d, r = res.get(6).getIdentity(), r * delta);
		assertEquals(0.729d, r = res.get(6).getSimilarity(), r * delta);
		assertEquals(48, res.get(6).getOverlap());
		assertEquals("query", res.get(6).getQuerySequenceName());
		assertEquals("query sequence", res.get(6).getQuerySequenceDescription());
		assertEquals(50, res.get(6).getQuerySequenceLength());
		assertEquals(1, res.get(6).getQueryAlignmentStart());
		assertEquals(48, res.get(6).getQueryAlignmentStop());
		assertEquals(1, res.get(6).getQueryAlignmentDisplayStart());
		assertEquals("--------------------SAVQQKLAALEKSSGGRLGVALIDTADNTQ"
				   + "VLYRGDERFPMCSTSKVMAA", res.get(6).getQueryAlignmentLine());
		assertEquals("sp|P00807|BLAC_STAAU", res.get(6).getSubjectSequenceName());
		assertEquals("Beta-lactamase OS=Staphylococcus aureus OX=1280 GN=blaZ PE=1 SV=1", res.get(6).getSubjectSequenceDescription());
		assertEquals(281, res.get(6).getSubjectSequenceLength());
		assertEquals(21, res.get(6).getSubjectAlignmentStart());
		assertEquals(68, res.get(6).getSubjectAlignmentStop());
		assertEquals(1, res.get(6).getSubjectAlignmentDisplayStart());
		assertEquals("MKKLIFLIVIALVLSACNSNSSHAKELNDLEKKYNAHIGVYALDTKSGKE"
				   + "VKFNSDKRFAYASTSKAINSAILLEQVPYNKLNKKVHINKDDIVAYSPIL"
				   + "EKYVGKDITLKALIEASMTY", res.get(6).getSubjectAlignmentLine());
		assertEquals("                    :.  ..:  :::. ....::  .:: .. ."
				   + ": . .:.:: . ::::..", res.get(6).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(7).getFrame());
		assertEquals(35.2d, r = res.get(7).getBitScore(), r * delta);
		assertEquals(2.8e-06d, r = res.get(7).getExpectationValue(), r * delta);
		assertEquals(85, res.get(7).getSmithWatermanScore());
		assertEquals(0.340d, r = res.get(7).getIdentity(), r * delta);
		assertEquals(0.638d, r = res.get(7).getSimilarity(), r * delta);
		assertEquals(47, res.get(7).getOverlap());
		assertEquals("query", res.get(7).getQuerySequenceName());
		assertEquals("query sequence", res.get(7).getQuerySequenceDescription());
		assertEquals(50, res.get(7).getQuerySequenceLength());
		assertEquals(3, res.get(7).getQueryAlignmentStart());
		assertEquals(49, res.get(7).getQueryAlignmentStop());
		assertEquals(1, res.get(7).getQueryAlignmentDisplayStart());
		assertEquals("---------------------SAVQQKLAALEKSSGGRLGVALIDTADNT"
				   + "QVLYRGDERFPMCSTSKVMAA", res.get(7).getQueryAlignmentLine());
		assertEquals("sp|Q9K9L8|GLSA1_BACHD", res.get(7).getSubjectSequenceName());
		assertEquals("Glutaminase 1 OS=Bacillus halodurans (strain ATCC BAA-125 / DSM 18197 / FERM 7344 / JCM 9153 / C-125) OX=272558 GN=glsA1 PE=3 SV=1", res.get(7).getSubjectSequenceDescription());
		assertEquals(308, res.get(7).getSubjectSequenceLength());
		assertEquals(24, res.get(7).getSubjectAlignmentStart());
		assertEquals(70, res.get(7).getSubjectAlignmentStop());
		assertEquals(1, res.get(7).getSubjectAlignmentDisplayStart());
		assertEquals("MWKQDETLEQIVLECKKYTEEGTVASYIPALAKADVSTLGIAIYRGGDEQ"
				   + "VIAGDADEKFTLQSISKVIALALALLDVGEEAVFSKVGMEPTGDPFNSIS"
				   + "KLETSVPSKPLNPMINAGAL", res.get(7).getSubjectAlignmentLine());
		assertEquals("                       : . . :: :.. . ::.:.   .:. "
				   + " .   .::.: . : :::.:", res.get(7).getConsensusLine());

		assertEquals(Frame.FORWARD_ZERO, res.get(8).getFrame());
		assertEquals(34.2d, r = res.get(8).getBitScore(), r * delta);
		assertEquals(8.5e-06d, r = res.get(8).getExpectationValue(), r * delta);
		assertEquals(82, res.get(8).getSmithWatermanScore());
		assertEquals(0.394d, r = res.get(8).getIdentity(), r * delta);
		assertEquals(0.758d, r = res.get(8).getSimilarity(), r * delta);
		assertEquals(33, res.get(8).getOverlap());
		assertEquals("query", res.get(8).getQuerySequenceName());
		assertEquals("query sequence", res.get(8).getQuerySequenceDescription());
		assertEquals(50, res.get(8).getQuerySequenceLength());
		assertEquals(15, res.get(8).getQueryAlignmentStart());
		assertEquals(46, res.get(8).getQueryAlignmentStop());
		assertEquals(1, res.get(8).getQueryAlignmentDisplayStart());
		assertEquals("---SAVQQKLAALEKSSGGR-LGVALIDTADNTQVLYRGDERFPMCSTSK"
				   + "VMAA", res.get(8).getQueryAlignmentLine());
		assertEquals("sp|Q8CDJ3|BAKOR_MOUSE", res.get(8).getSubjectSequenceName());
		assertEquals("Beclin 1-associated autophagy-related key regulator OS=Mus musculus OX=10090 GN=Atg14 PE=1 SV=1", res.get(8).getSubjectSequenceDescription());
		assertEquals(492, res.get(8).getSubjectSequenceLength());
		assertEquals(18, res.get(8).getSubjectAlignmentStart());
		assertEquals(50, res.get(8).getSubjectAlignmentStop());
		assertEquals(1, res.get(8).getSubjectAlignmentDisplayStart());
		assertEquals("MASPSGKGSWTPEAPGFGPRALARDLVDSVDDAEGLYVAVERCPLCNTTR"
				   + "RRLTCAKCVQSGDFVYFDGRDRERFIDKKERLSQLKNKQEEFQKEVLKAM"
				   + "EGKRLTDQLRWKIMSCKMRI", res.get(8).getSubjectAlignmentLine());
		assertEquals("                 : :-:.  :.:..:... :: . :: :.:.:..", res.get(8).getConsensusLine());
	}

	@Test
	public void testParseResults8() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results8.txt"));
		List<FastaResult> res = new FastaResultParser(reader).parse();
		double r;

		assertEquals(11, res.size());

		assertEquals(Frame.FORWARD_ZERO, res.get(0).getFrame());
		assertEquals(132.7d, r = res.get(0).getBitScore(), r * delta);
		assertEquals(7.6e-35d, r = res.get(0).getExpectationValue(), r * delta);
		assertEquals(0, res.get(0).getSmithWatermanScore());
		assertEquals(1.000d, r = res.get(0).getIdentity(), r * delta);
		assertEquals(1.000d, r = res.get(0).getSimilarity(), r * delta);
		assertEquals(99, res.get(0).getOverlap());
		assertEquals("query", res.get(0).getQuerySequenceName());
		assertEquals("query sequence", res.get(0).getQuerySequenceDescription());
		assertEquals(99, res.get(0).getQuerySequenceLength());
		assertEquals(1, res.get(0).getQueryAlignmentStart());
		assertEquals(99, res.get(0).getQueryAlignmentStop());
		assertEquals(1, res.get(0).getQueryAlignmentDisplayStart());
		assertEquals("------------------------------CCTGCCGATCTGGTTAACTA"
				   + "CAATCCGATTGCCGAAAAACACGTCAACGGCACAATGACGCTGGCAGAAC"
				   + "TGAGCGCGGCCGCTTTGCAGTACAGCGAC", res.get(0).getQueryAlignmentLine());
		assertEquals("ENA|BAA28282|BAA28282.1", res.get(0).getSubjectSequenceName());
		assertEquals("Escherichia coli beta-lactamase",
				res.get(0).getSubjectSequenceDescription());
		assertEquals(870, res.get(0).getSubjectSequenceLength());
		assertEquals(304, res.get(0).getSubjectAlignmentStart());
		assertEquals(402, res.get(0).getSubjectAlignmentStop());
		assertEquals(274, res.get(0).getSubjectAlignmentDisplayStart());
		assertEquals("CAGCTGCTTAATCAGCCTGTCGAGATCAAGCCTGCCGATCTGGTTAACTA"
				   + "CAATCCGATTGCCGAAAAACACGTCAACGGCACAATGACGCTGGCAGAAC"
				   + "TGAGCGCGGCCGCTTTGCAGTACAGCGACAATACCGCCATGAACAAATTG"
				   + "ATTGCCCAGCTCGGTGGCCCGGGAGGCGTG", res.get(0).getSubjectAlignmentLine());
		assertEquals("                              ::::::::::::::::::::"
				   + "::::::::::::::::::::::::::::::::::::::::::::::::::"
				   + ":::::::::::::::::::::::::::::", res.get(0).getConsensusLine());

		// TODO: other 10 results

		assertEquals(Frame.REVERSE_ZERO, res.get(5).getFrame());
	}
}