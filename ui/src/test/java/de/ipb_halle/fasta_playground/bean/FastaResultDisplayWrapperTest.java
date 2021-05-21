package de.ipb_halle.fasta_playground.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.ipb_halle.fasta_playground.bean.FastaResultDisplayWrapper.AlignmentLine;
import de.ipb_halle.fasta_playground.fastaresult.FastaResult;
import de.ipb_halle.fasta_playground.fastaresult.FastaResultParser;
import de.ipb_halle.fasta_playground.fastaresult.FastaResultParserException;
import de.ipb_halle.fasta_playground.fastaresult.FastaResultParserTest;

public class FastaResultDisplayWrapperTest {
	@Test
	public void testGetFastaResult() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results1.txt"));
		FastaResult result = new FastaResultParser(reader).parse().get(0);

		assertEquals(result, new FastaResultDisplayWrapper(result).getFastaResult());
	}

	@Test
	public void testGetAndSetConfig() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results1.txt"));
		FastaResult result = new FastaResultParser(reader).parse().get(0);
		FastaResultDisplayConfig config = new FastaResultDisplayConfig();

		assertEquals(config, new FastaResultDisplayWrapper(result).config(config).getConfig());
	}

	@Test
	public void testReplaceLeadingChars() {
		assertEquals("     ABC--DEF--G", FastaResultDisplayWrapper.replaceLeadingChars("-----ABC--DEF--G", '-', ' '));
	}

	@Test
	public void testSegmentString() {
		assertThrows(IllegalArgumentException.class, () -> FastaResultDisplayWrapper.segmentString("123456789", 0));
		assertEquals(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"),
				FastaResultDisplayWrapper.segmentString("123456789", 1));
		assertEquals(Arrays.asList("12", "34", "56", "78", "9"),
				FastaResultDisplayWrapper.segmentString("123456789", 2));
		assertEquals(Arrays.asList("123", "456", "789"), FastaResultDisplayWrapper.segmentString("123456789", 3));
		assertEquals(Arrays.asList("1234", "5678", "9"), FastaResultDisplayWrapper.segmentString("123456789", 4));
		assertEquals(Arrays.asList("12345", "6789"), FastaResultDisplayWrapper.segmentString("123456789", 5));
		assertEquals(Arrays.asList("123456789"), FastaResultDisplayWrapper.segmentString("123456789", 9));
		assertEquals(Arrays.asList("123456789"), FastaResultDisplayWrapper.segmentString("123456789", 10));
	}

	@Test
	public void testCreateAlignmentLines() {
		List<AlignmentLine> results;

		results = FastaResultDisplayWrapper.createAlignmentLines("123456789", 1, 4);
		assertEquals(3, results.size());
		assertEquals(1, results.get(0).getStartIndex());
		assertEquals(4, results.get(0).getStopIndex());
		assertEquals("1234", results.get(0).getLine());
		assertEquals(5, results.get(1).getStartIndex());
		assertEquals(8, results.get(1).getStopIndex());
		assertEquals("5678", results.get(1).getLine());
		assertEquals(9, results.get(2).getStartIndex());
		assertEquals(9, results.get(2).getStopIndex());
		assertEquals("9", results.get(2).getLine());

		results = FastaResultDisplayWrapper.createAlignmentLines("123456789", 20, 6);
		assertEquals(2, results.size());
		assertEquals(20, results.get(0).getStartIndex());
		assertEquals(25, results.get(0).getStopIndex());
		assertEquals("123456", results.get(0).getLine());
		assertEquals(26, results.get(1).getStartIndex());
		assertEquals(28, results.get(1).getStopIndex());
		assertEquals("789", results.get(1).getLine());

		results = FastaResultDisplayWrapper.createAlignmentLines("    12-3--4567---89", 20, 6);
		assertEquals(4, results.size());
		assertEquals(20, results.get(0).getStartIndex());
		assertEquals(21, results.get(0).getStopIndex());
		assertEquals("    12", results.get(0).getLine());
		assertEquals(22, results.get(1).getStartIndex());
		assertEquals(24, results.get(1).getStopIndex());
		assertEquals("-3--45", results.get(1).getLine());
		assertEquals(25, results.get(2).getStartIndex());
		assertEquals(27, results.get(2).getStopIndex());
		assertEquals("67---8", results.get(2).getLine());
		assertEquals(28, results.get(3).getStartIndex());
		assertEquals(28, results.get(3).getStopIndex());
		assertEquals("9", results.get(3).getLine());
	}

	@Test
	public void testGetAlignments() throws IOException, FastaResultParserException {
		Reader reader = new InputStreamReader(FastaResultParserTest.class.getResourceAsStream("results1.txt"));
		List<FastaResult> results = new FastaResultParser(reader).parse();
		String expected;
		FastaResultDisplayConfig config = new FastaResultDisplayConfig();

		config.setLineLength(50);
		config.setPrefixSpaces(2);
		config.setSuffixSpaces(2);

		// result 0
		expected =
		"Query     1                                SAVQQKLAALEKSSGGRLGV   20\n"+
		"                                           ::::::::::::::::::::\n"+
		"Subject   1  MVTKRVQRMMFAAAACIPLLLGSAPLYAQTSAVQQKLAALEKSSGGRLGV   50\n"+
		"\n"+
		"Query    21  ALIDTADNTQVLYRGDERFPMCSTSKVMAA                       50\n"+
		"             ::::::::::::::::::::::::::::::\n"+
		"Subject  51  ALIDTADNTQVLYRGDERFPMCSTSKVMAAAAVLKQSETQKQLLNQPVEI  100\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject 101  KPADLVNYNPIAEKHVNGTM  120";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(0)).config(config).getAlignments());

		// result 1
		expected =
		"Query     1                        SAVQQKLAALEKSSGGRLGVALIDTADN   28\n"+
		"                                       ...   :.. .::.:.  .: :..\n"+
		"Subject   1  MRYIRLCIISLLATLPLAVHASPQPLEQIKQSESQLSGRVGMIEMDLASG   50\n"+
		"\n"+
		"Query    29  -TQVLYRGDERFPMCSTSKVMAA                              50\n"+
		"             -: . .:.:::::: :: ::.\n"+
		"Subject  51  RTLTAWRADERFPMMSTFKVVLCGAVLARVDAGDEQLERKIHYRQQDLVD  100\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject 101  YSPVSEKHLADGMTVGELCA  120";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(1)).config(config).getAlignments());

		// result 2
		expected =
		"Query     1                        SAVQQKLAALEKSSGGRLGVALIDTADN   28\n"+
		"                                       ...   :.. .::.:.  .: :..\n"+
		"Subject   1  MRYIRLCIISLLATLPLAVHASPQPLEQIKQSESQLSGRVGMIEMDLASG   50\n"+
		"\n"+
		"Query    29  -TQVLYRGDERFPMCSTSKVMAA                              50\n"+
		"             -: . .:.:::::: :: ::.\n"+
		"Subject  51  RTLTAWRADERFPMMSTFKVVLCGAVLARVDAGDEQLERKIHYRQQDLVD  100\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject 101  YSPVSEKHLADGMTVGELCA  120";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(2)).config(config).getAlignments());

		// result 3
		expected =
		"Query     1                  SAVQQKLAALEKSSGGRLGVALI---DTADNTQV   31\n"+
		"                                           :.:  :::.---. :.   :\n"+
		"Subject  85  MVDDRVAGPLIRSVLPAGWFIADKTGASKRGARGIVALLGPNNKAERIVV  134\n"+
		"\n"+
		"Query    32  LYRGDERFPMCSTSKVMAA   50\n"+
		"             :: :\n"+
		"Subject 135  LYIGX                139";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(3)).config(config).getAlignments());

		config.setLineLength(20);
		config.setPrefixSpaces(4);
		config.setSuffixSpaces(6);

		// result 0
		expected =
		"Query\n"+
		"\n"+
		"Subject   1    MVTKRVQRMMFAAAACIPLL       20\n"+
		"\n"+
		"Query     1              SAVQQKLAAL       10\n"+
		"                         ::::::::::\n"+
		"Subject  21    LGSAPLYAQTSAVQQKLAAL       40\n"+
		"\n"+
		"Query    11    EKSSGGRLGVALIDTADNTQ       30\n"+
		"               ::::::::::::::::::::\n"+
		"Subject  41    EKSSGGRLGVALIDTADNTQ       60\n"+
		"\n"+
		"Query    31    VLYRGDERFPMCSTSKVMAA       50\n"+
		"               ::::::::::::::::::::\n"+
		"Subject  61    VLYRGDERFPMCSTSKVMAA       80\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject  81    AAVLKQSETQKQLLNQPVEI      100\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject 101    KPADLVNYNPIAEKHVNGTM      120";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(0)).config(config).getAlignments());

		// result 1
		expected =
		"Query\n"+
		"\n"+
		"Subject   1    MRYIRLCIISLLATLPLAVH       20\n"+
		"\n"+
		"Query     1      SAVQQKLAALEKSSGGRL       18\n"+
		"                     ...   :.. .::.\n"+
		"Subject  21    ASPQPLEQIKQSESQLSGRV       40\n"+
		"\n"+
		"Query    19    GVALIDTADN-TQVLYRGDE       37\n"+
		"               :.  .: :..-: . .:.::\n"+
		"Subject  41    GMIEMDLASGRTLTAWRADE       60\n"+
		"\n"+
		"Query    38    RFPMCSTSKVMAA              50\n"+
		"               :::: :: ::.\n"+
		"Subject  61    RFPMMSTFKVVLCGAVLARV       80\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject  81    DAGDEQLERKIHYRQQDLVD      100\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject 101    YSPVSEKHLADGMTVGELCA      120";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(1)).config(config).getAlignments());

		// result 2
		expected =
		"Query\n"+
		"\n"+
		"Subject   1    MRYIRLCIISLLATLPLAVH       20\n"+
		"\n"+
		"Query     1      SAVQQKLAALEKSSGGRL       18\n"+
		"                     ...   :.. .::.\n"+
		"Subject  21    ASPQPLEQIKQSESQLSGRV       40\n"+
		"\n"+
		"Query    19    GVALIDTADN-TQVLYRGDE       37\n"+
		"               :.  .: :..-: . .:.::\n"+
		"Subject  41    GMIEMDLASGRTLTAWRADE       60\n"+
		"\n"+
		"Query    38    RFPMCSTSKVMAA              50\n"+
		"               :::: :: ::.\n"+
		"Subject  61    RFPMMSTFKVVLCGAVLARV       80\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject  81    DAGDEQLERKIHYRQQDLVD      100\n"+
		"\n"+
		"Query\n"+
		"\n"+
		"Subject 101    YSPVSEKHLADGMTVGELCA      120";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(2)).config(config).getAlignments());

		// result 3
		expected =
		"Query     1                    SAVQ        4\n"+
		"\n"+
		"Subject  85    MVDDRVAGPLIRSVLPAGWF      104\n"+
		"\n"+
		"Query     5    QKLAALEKSSGGRLGVALI-       23\n"+
		"                         :.:  :::.-\n"+
		"Subject 105    IADKTGASKRGARGIVALLG      124\n"+
		"\n"+
		"Query    24    --DTADNTQVLYRGDERFPM       41\n"+
		"               --. :.   ::: :\n"+
		"Subject 125    PNNKAERIVVLYIGX           139\n"+
		"\n"+
		"Query    42    CSTSKVMAA       50\n"+
		"\n"+
		"Subject";
		assertEquals(expected, new FastaResultDisplayWrapper(results.get(3)).config(config).getAlignments());
	}
}