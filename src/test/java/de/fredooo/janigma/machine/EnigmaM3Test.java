package de.fredooo.janigma.machine;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the EnigmaM3 class. 
 * @author Frederik Dennig
 * @since 2015-05-28
 * @version 0.0.1 (last edited 2016-03-02)
 */
public class EnigmaM3Test extends TestCase {

	/**
	 * Creates the test cases.
	 * @param testName Name of the test cases
	 */
	public EnigmaM3Test(String testName) {
		super(testName);
	}

	/**
	 * Creates a TestSuite for the test cases.
	 * @return Returns the suite of the tests
	 */
	public static Test suite() {
		return new TestSuite(EnigmaM3Test.class);
	}
	
	/**
	 * Test the Enigma M3 machine with an original message from WKII.
	 * Source: http://cryptocellar.web.cern.ch/cryptocellar/enigma/EMsg1930.html
	 * @throws NoSuchSymbolException 
	 */
	public void testOriginalMessage() throws NoSuchSymbolException {
		EnigmaM3 enigma = new EnigmaM3();
		Assert.assertEquals("Wrong Enigma machine!", "Enigma M3", enigma.toString());
		enigma.setReflector(Reflector.createReflector(Reflector.M3_A));
		Assert.assertEquals("Wrong reflector in machine configuration!", "A", enigma.getReflector().toString());
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getLeftRotor().setOffset(23);
		enigma.getMiddleRotor().setOffset(12);
		enigma.getRightRotor().setOffset(21);
		enigma.getLeftRotor().setPostion(5);
		enigma.getMiddleRotor().setPostion(14);
		enigma.getRightRotor().setPostion(11);
		enigma.getPlugboard().addCable('A', 'M');
		enigma.getPlugboard().addCable('F', 'I');
		enigma.getPlugboard().addCable('N', 'V');
		enigma.getPlugboard().addCable('P', 'S');
		enigma.getPlugboard().addCable('T', 'U');
		enigma.getPlugboard().addCable('W', 'Z');
		String messageKey = enigma.use("PKPJXI");
		Assert.assertEquals("Decrypted key does not match expected!", "ABLABL", messageKey);
		enigma.getLeftRotor().setPostion(Original.toInt('A'));
		enigma.getMiddleRotor().setPostion(Original.toInt('B'));
		enigma.getRightRotor().setPostion(Original.toInt('L'));
		final String message =
				"GCDSE AHUGW TQGRK VLFGX UCALX VYMIG" +
				"MMNMF DXTGN VHVRM MEVOU YFZSL RHDRR" +
				"XFJWC FHUHM UNZEF RDISI KBGPM YVXUZ";
		final String expected =
				"FEIND LIQEI NFANT ERIEK OLONN EBEOB" +
				"AQTET XANFA NGSUE DAUSG ANGBA ERWAL" +
				"DEXEN DEDRE IKMOS TWAER TSNEU STADT";
		final String result = enigma.use(message.replaceAll(" ", ""));
		Assert.assertEquals("Decrypted message does not match expected!", expected.replaceAll(" ", ""), result);
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(EnigmaM3Test.class);
	}

}
