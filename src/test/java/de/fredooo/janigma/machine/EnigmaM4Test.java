package de.fredooo.janigma.machine;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the EnigmaM4 class. 
 * @author Frederik Dennig
 * @since 2015-05-27
 * @version 0.0.1 (last edited 2016-03-02)
 */
public class EnigmaM4Test extends TestCase {
	
	/**
	 * Creates the test cases.
	 * @param testName Name of the test cases
	 */
	public EnigmaM4Test(String testName) {
		super(testName);
	}

	/**
	 * Creates a TestSuite for the test cases.
	 * @return Returns the suite of the tests
	 */
	public static Test suite() {
		return new TestSuite(EnigmaM4Test.class);
	}
	
	/**
	 * Test the Enigma M4 machine with an original message from WKII.
	 * Source: http://www.cryptomuseum.com/crypto/enigma/msg/p1030681.htm
	 * @throws NoSuchSymbolException
	 */
	public void testOriginalMessage() throws NoSuchSymbolException {
		EnigmaM4 enigma = new EnigmaM4();
		Assert.assertEquals("Wrong Enigma machine!", "Enigma M4", enigma.toString());
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_C));
		Assert.assertEquals("Wrong reflector in machine configuration!", "C", enigma.getThinReflector().toString());
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_BETA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_V));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_VIII));
		enigma.getGreekRotor().setOffset(Original.toInt('E'));
		enigma.getLeftRotor().setOffset(Original.toInt('P'));
		enigma.getMiddleRotor().setOffset(Original.toInt('E'));
		enigma.getRightRotor().setOffset(Original.toInt('L'));
		enigma.getGreekRotor().setPostion(Original.toInt('N'));
		enigma.getLeftRotor().setPostion(Original.toInt('A'));
		enigma.getMiddleRotor().setPostion(Original.toInt('E'));
		enigma.getRightRotor().setPostion(Original.toInt('M'));
		enigma.getPlugboard().addCable('A', 'E');
		enigma.getPlugboard().addCable('B', 'F');
		enigma.getPlugboard().addCable('C', 'M');
		enigma.getPlugboard().addCable('D', 'Q');
		enigma.getPlugboard().addCable('H', 'U');
		enigma.getPlugboard().addCable('J', 'N');
		enigma.getPlugboard().addCable('L', 'X');
		enigma.getPlugboard().addCable('P', 'R');
		enigma.getPlugboard().addCable('S', 'Z');
		enigma.getPlugboard().addCable('V', 'W');
		String messageKey = enigma.use("QEOB");
		Assert.assertEquals("Decrypted key does not match expected!", "CDSZ", messageKey);
		enigma.getGreekRotor().setPostion(Original.toInt('C'));
		enigma.getLeftRotor().setPostion(Original.toInt('D'));
		enigma.getMiddleRotor().setPostion(Original.toInt('S'));
		enigma.getRightRotor().setPostion(Original.toInt('Z'));
		final String message =
				"LANO TCTO UARB BFPM HPHG CZXT DYGA HGUF XGEW KBLK GJWL QXXT" +
				"GPJJ AVTO CKZF SLPP QIHZ FXOE BWII EKFZ LCLO AQJU LJOY HSSM BBGW HZAN" +
				"VOII PYRB RTDJ QDJJ OQKC XWDN BBTY VXLY TAPG VEAT XSON PNYN QFUD BBHH" +
				"VWEP YEYD OHNL XKZD NWRH DUWU JUMW WVII WZXI VIUQ DRHY MNCY EFUA PNHO" +
				"TKHK GDNP SAKN UAGH JZSM JBMH VTRE QEDG XHLZ WIFU SKDQ VELN MIMI THBH" +
				"DBWV HDFY HJOQ IHOR TDJD BWXE MEAY XGYQ XOHF DMYU XXNO JAZR SGHP LWML" +
				"RECW WUTL RTTV LBHY OORG LGOW UXNX HMHY FAAC QEKT HSJW";
		final String expected =
				"KRKRALLEXXFOLGENDESISTSOFORTBEKANNTZUGEBENXXICHHABEFOLGELNBEBEFEHLERH" +
				"ALTENXXJANSTERLEDESBISHERIGXNREICHSMARSCHALLSJGOERINGJSETZTDERFUEHRER" +
				"SIEYHVRRGRZSSADMIRALYALSSEINENNACHFOLGEREINXSCHRIFTLSCHEVOLLMACHTUNTE" +
				"RWEGSXABSOFORTSOLLENSIESAEMTLICHEMASSNAHMENVERFUEGENYDIESICHAUSDERGEG" +
				"ENWAERTIGENLAGEERGEBENXGEZXREICHSLEITEIKKTULPEKKJBORMANNJXXOBXDXMMMDU" +
				"RNHFKSTXKOMXADMXUUUBOOIEXKP"; 
		final String result = enigma.use(message.replaceAll(" ", ""));
		Assert.assertEquals("Decrypted message does not match expected!", expected, result);
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(RotorTest.class);
	}

}
