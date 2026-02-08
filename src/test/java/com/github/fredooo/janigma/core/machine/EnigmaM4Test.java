package com.github.fredooo.janigma.core.machine;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the EnigmaM4 class. 
 * @author Frederik Dennig
 * @since 2015-05-27
 * @version 0.0.6
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
	 * Test the Enigma M4 machine with an original message from WKII (C_thin, beta).
	 * Source: http://www.cryptomuseum.com/crypto/enigma/msg/p1030681.htm
	 * @throws NoSuchSymbolException should never be thrown
	 */
	public void testOperationBarbarossa() throws NoSuchSymbolException {
		EnigmaM4 enigma = new EnigmaM4();
		Assert.assertEquals("Wrong Enigma machine!", "Enigma M4", enigma.toString());
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_C));
		assertEquals("Wrong reflector in machine configuration!", "C", enigma.getThinReflector().toString());
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_BETA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_V));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_VIII));
		enigma.getGreekRotor().setOffset(Original.toInt('E'));
		enigma.getLeftRotor().setOffset(Original.toInt('P'));
		enigma.getMiddleRotor().setOffset(Original.toInt('E'));
		enigma.getRightRotor().setOffset(Original.toInt('L'));
		enigma.getGreekRotor().setPosition(Original.toInt('N'));
		enigma.getLeftRotor().setPosition(Original.toInt('A'));
		enigma.getMiddleRotor().setPosition(Original.toInt('E'));
		enigma.getRightRotor().setPosition(Original.toInt('M'));
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
		enigma.getGreekRotor().setPosition(Original.toInt('C'));
		enigma.getLeftRotor().setPosition(Original.toInt('D'));
		enigma.getMiddleRotor().setPosition(Original.toInt('S'));
		enigma.getRightRotor().setPosition(Original.toInt('Z'));
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
	 * Tests encryption reciprocity: enigma.use(enigma.use(msg)) == msg
	 */
	public void testEncryptionReciprocity() throws NoSuchSymbolException {
		// Test config 1: basic M4 setup with beta
		EnigmaM4 enigma = new EnigmaM4();
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_B));
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_BETA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getGreekRotor().setPosition(0);
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);
		enigma.getPlugboard().addCable('A', 'B');

		String plaintext = "HELLO";
		String encrypted = enigma.use(plaintext);

		enigma.getGreekRotor().setPosition(0);
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);
		String decrypted = enigma.use(encrypted);

		Assert.assertEquals("Reciprocity failed for config 1!", plaintext, decrypted);

		// Test config 2: gamma rotor with offsets
		enigma = new EnigmaM4();
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_C));
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_GAMMA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_IV));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_V));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma.getGreekRotor().setOffset(2);
		enigma.getLeftRotor().setOffset(5);
		enigma.getMiddleRotor().setOffset(10);
		enigma.getRightRotor().setOffset(15);
		enigma.getGreekRotor().setPosition(1);
		enigma.getLeftRotor().setPosition(3);
		enigma.getMiddleRotor().setPosition(7);
		enigma.getRightRotor().setPosition(11);
		enigma.getPlugboard().addCable('E', 'Z');
		enigma.getPlugboard().addCable('M', 'Q');

		plaintext = "TESTMESSAGE";
		encrypted = enigma.use(plaintext);

		enigma.getGreekRotor().setPosition(1);
		enigma.getLeftRotor().setPosition(3);
		enigma.getMiddleRotor().setPosition(7);
		enigma.getRightRotor().setPosition(11);
		decrypted = enigma.use(encrypted);

		Assert.assertEquals("Reciprocity failed for config 2!", plaintext, decrypted);
	}

	/**
	 * Tests double-stepping mechanism explicitly
	 */
	public void testDoubleSteppingMechanism() {
		EnigmaM4 enigma = new EnigmaM4();
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_B));
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_BETA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));    // notch at Q (16)
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II)); // notch at E (4)
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III)); // notch at V (21)

		// Set middle rotor one position before its notch
		enigma.getGreekRotor().setPosition(0);
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(3);  // D, one before E (notch)
		enigma.getRightRotor().setPosition(21);  // V, at notch

		// First step
		try {
			enigma.use("A");
		} catch (NoSuchSymbolException e) {
			Assert.fail("Unexpected exception!");
		}
		Assert.assertEquals("Right rotor should step from V!", 22, enigma.getRightRotor().getPosition());
		Assert.assertEquals("Middle rotor should step to E (notch)!", 4, enigma.getMiddleRotor().getPosition());
		Assert.assertEquals("Left rotor should not step yet!", 0, enigma.getLeftRotor().getPosition());
		Assert.assertEquals("Greek rotor should not step!", 0, enigma.getGreekRotor().getPosition());

		// Second step: double-stepping
		try {
			enigma.use("A");
		} catch (NoSuchSymbolException e) {
			Assert.fail("Unexpected exception!");
		}
		Assert.assertEquals("Right rotor should step!", 23, enigma.getRightRotor().getPosition());
		Assert.assertEquals("Middle rotor should double-step!", 5, enigma.getMiddleRotor().getPosition());
		Assert.assertEquals("Left rotor should now step!", 1, enigma.getLeftRotor().getPosition());
		Assert.assertEquals("Greek rotor should remain static!", 0, enigma.getGreekRotor().getPosition());
	}

	/**
	 * Tests case insensitive handling
	 */
	public void testCaseInsensitive() throws NoSuchSymbolException {
		EnigmaM4 enigma = new EnigmaM4();
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_B));
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_BETA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getGreekRotor().setPosition(0);
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);

		String upperResult = enigma.use("HELLO");

		enigma.getGreekRotor().setPosition(0);
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);
		String lowerResult = enigma.use("hello");

		Assert.assertEquals("Lowercase should equal uppercase!", upperResult, lowerResult);
	}

	/**
	 * Tests invalid input throws NoSuchSymbolException
	 */
	public void testInvalidInput() {
		EnigmaM4 enigma = new EnigmaM4();
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_B));
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_BETA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));

		try {
			enigma.use("HELLO123");
			Assert.fail("Should throw NoSuchSymbolException for non-A-Z chars!");
		} catch (NoSuchSymbolException e) {
			// Expected
		}

		try {
			enigma.use("HELLO WORLD");
			Assert.fail("Should throw NoSuchSymbolException for space!");
		} catch (NoSuchSymbolException e) {
			// Expected
		}
	}

	/**
	 * Tests configuration cloning produces identical output
	 */
	public void testConfigurationCloning() throws NoSuchSymbolException {
		EnigmaM4 enigma1 = new EnigmaM4();
		enigma1.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_C));
		enigma1.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_GAMMA));
		enigma1.setLeftRotor(Rotor.createRotor(Rotor.M3_IV));
		enigma1.setMiddleRotor(Rotor.createRotor(Rotor.M3_V));
		enigma1.setRightRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma1.getGreekRotor().setOffset(2);
		enigma1.getLeftRotor().setOffset(5);
		enigma1.getMiddleRotor().setOffset(10);
		enigma1.getRightRotor().setOffset(15);
		enigma1.getGreekRotor().setPosition(1);
		enigma1.getLeftRotor().setPosition(3);
		enigma1.getMiddleRotor().setPosition(7);
		enigma1.getRightRotor().setPosition(11);
		enigma1.getPlugboard().addCable('A', 'M');
		enigma1.getPlugboard().addCable('F', 'I');

		// Clone config
		EnigmaM4 enigma2 = new EnigmaM4();
		enigma2.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_C));
		enigma2.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_GAMMA));
		enigma2.setLeftRotor(Rotor.createRotor(Rotor.M3_IV));
		enigma2.setMiddleRotor(Rotor.createRotor(Rotor.M3_V));
		enigma2.setRightRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma2.getGreekRotor().setOffset(2);
		enigma2.getLeftRotor().setOffset(5);
		enigma2.getMiddleRotor().setOffset(10);
		enigma2.getRightRotor().setOffset(15);
		enigma2.getGreekRotor().setPosition(1);
		enigma2.getLeftRotor().setPosition(3);
		enigma2.getMiddleRotor().setPosition(7);
		enigma2.getRightRotor().setPosition(11);
		enigma2.getPlugboard().addCable('A', 'M');
		enigma2.getPlugboard().addCable('F', 'I');

		String message = "TESTMESSAGE";
		String result1 = enigma1.use(message);
		String result2 = enigma2.use(message);

		Assert.assertEquals("Identical configs should produce identical output!", result1, result2);
	}

	/**
	 * Tests state persistence across multiple messages
	 */
	public void testStatePersistence() throws NoSuchSymbolException {
		EnigmaM4 enigma = new EnigmaM4();
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_B));
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_BETA));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getGreekRotor().setPosition(0);
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);

		String msg1 = enigma.use("HELLO");
		int pos1 = enigma.getRightRotor().getPosition();

		String msg2 = enigma.use("WORLD");
		int pos2 = enigma.getRightRotor().getPosition();

		// Position should advance from first message
		Assert.assertEquals("Position should advance by 5 after HELLO!", 5, pos1);
		Assert.assertEquals("Position should advance by 5 more after WORLD!", 10, pos2);
		Assert.assertEquals("Greek rotor should remain at 0!", 0, enigma.getGreekRotor().getPosition());

		// Decrypt by resetting position
		enigma.getGreekRotor().setPosition(0);
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);
		String decrypted1 = enigma.use(msg1);
		Assert.assertEquals("First message should decrypt correctly!", "HELLO", decrypted1);

		String decrypted2 = enigma.use(msg2);
		Assert.assertEquals("Second message should decrypt correctly!", "WORLD", decrypted2);
	}

	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(EnigmaM4Test.class);
	}

}
