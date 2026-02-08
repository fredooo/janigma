package com.github.fredooo.janigma.core.machine;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the EnigmaM3 class. 
 * @author Frederik Dennig
 * @since 2015-05-28
 * @version 0.0.6
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
	 * Test the Enigma M3 machine with an original message from WKII (reflector A).
	 * Source: http://cryptocellar.web.cern.ch/cryptocellar/enigma/EMsg1930.html
	 * @throws NoSuchSymbolException should never be thrown
	 */
	public void testOperationScharnhorst() throws NoSuchSymbolException {
		EnigmaM3 enigma = new EnigmaM3();
		assertEquals("Wrong Enigma machine!", "Enigma M3", enigma.toString());
		enigma.setReflector(Reflector.createReflector(Reflector.M3_A));
		assertEquals("Wrong reflector in machine configuration!", "A", enigma.getReflector().toString());
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getLeftRotor().setOffset(23);
		enigma.getMiddleRotor().setOffset(12);
		enigma.getRightRotor().setOffset(21);
		enigma.getLeftRotor().setPosition(5);
		enigma.getMiddleRotor().setPosition(14);
		enigma.getRightRotor().setPosition(11);
		enigma.getPlugboard().addCable('A', 'M');
		enigma.getPlugboard().addCable('F', 'I');
		enigma.getPlugboard().addCable('N', 'V');
		enigma.getPlugboard().addCable('P', 'S');
		enigma.getPlugboard().addCable('T', 'U');
		enigma.getPlugboard().addCable('W', 'Z');
		String messageKey = enigma.use("PKPJXI");
		Assert.assertEquals("Decrypted key does not match expected!", "ABLABL", messageKey);
		enigma.getLeftRotor().setPosition(Original.toInt('A'));
		enigma.getMiddleRotor().setPosition(Original.toInt('B'));
		enigma.getRightRotor().setPosition(Original.toInt('L'));
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
	 * Tests encryption reciprocity: enigma.use(enigma.use(msg)) == msg
	 */
	public void testEncryptionReciprocity() throws NoSuchSymbolException {
		// Test config 1: basic setup
		EnigmaM3 enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_A));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);
		enigma.getPlugboard().addCable('A', 'B');

		String plaintext = "HELLO";
		String encrypted = enigma.use(plaintext);

		// Reset to same position for decryption
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);
		String decrypted = enigma.use(encrypted);

		Assert.assertEquals("Reciprocity failed for config 1!", plaintext, decrypted);

		// Test config 2: with offsets
		enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_B));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_IV));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_V));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma.getLeftRotor().setOffset(5);
		enigma.getMiddleRotor().setOffset(10);
		enigma.getRightRotor().setOffset(15);
		enigma.getLeftRotor().setPosition(3);
		enigma.getMiddleRotor().setPosition(7);
		enigma.getRightRotor().setPosition(11);
		enigma.getPlugboard().addCable('E', 'Z');
		enigma.getPlugboard().addCable('M', 'Q');

		plaintext = "TESTMESSAGE";
		encrypted = enigma.use(plaintext);

		enigma.getLeftRotor().setPosition(3);
		enigma.getMiddleRotor().setPosition(7);
		enigma.getRightRotor().setPosition(11);
		decrypted = enigma.use(encrypted);

		Assert.assertEquals("Reciprocity failed for config 2!", plaintext, decrypted);

		// Test config 3: max cables
		enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_C));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_VII));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_VIII));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.getLeftRotor().setPosition(20);
		enigma.getMiddleRotor().setPosition(15);
		enigma.getRightRotor().setPosition(10);
		enigma.getPlugboard().addCable('A', 'B');
		enigma.getPlugboard().addCable('C', 'D');
		enigma.getPlugboard().addCable('E', 'F');
		enigma.getPlugboard().addCable('G', 'H');
		enigma.getPlugboard().addCable('I', 'J');
		enigma.getPlugboard().addCable('K', 'L');
		enigma.getPlugboard().addCable('M', 'N');
		enigma.getPlugboard().addCable('O', 'P');
		enigma.getPlugboard().addCable('Q', 'R');
		enigma.getPlugboard().addCable('S', 'T');

		plaintext = "UVWXYZ";
		encrypted = enigma.use(plaintext);

		enigma.getLeftRotor().setPosition(20);
		enigma.getMiddleRotor().setPosition(15);
		enigma.getRightRotor().setPosition(10);
		decrypted = enigma.use(encrypted);

		Assert.assertEquals("Reciprocity failed for config 3!", plaintext, decrypted);
	}

	/**
	 * Tests double-stepping mechanism explicitly
	 */
	public void testDoubleSteppingMechanism() {
		EnigmaM3 enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_A));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));    // notch at Q (16)
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II)); // notch at E (4)
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III)); // notch at V (21)

		// Set middle rotor one position before its notch
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(3);  // D, one before E (notch)
		enigma.getRightRotor().setPosition(21);  // V, at notch

		// First step: right rotor steps, triggers middle rotor to step
		try {
			enigma.use("A");
		} catch (NoSuchSymbolException e) {
			Assert.fail("Unexpected exception!");
		}
		Assert.assertEquals("Right rotor should step from V!", 22, enigma.getRightRotor().getPosition());
		Assert.assertEquals("Middle rotor should step to E (notch)!", 4, enigma.getMiddleRotor().getPosition());
		Assert.assertEquals("Left rotor should not step yet!", 0, enigma.getLeftRotor().getPosition());

		// Second step: middle rotor double-steps (steps again) and triggers left
		try {
			enigma.use("A");
		} catch (NoSuchSymbolException e) {
			Assert.fail("Unexpected exception!");
		}
		Assert.assertEquals("Right rotor should step!", 23, enigma.getRightRotor().getPosition());
		Assert.assertEquals("Middle rotor should double-step!", 5, enigma.getMiddleRotor().getPosition());
		Assert.assertEquals("Left rotor should now step!", 1, enigma.getLeftRotor().getPosition());
	}

	/**
	 * Tests case insensitive handling
	 */
	public void testCaseInsensitive() throws NoSuchSymbolException {
		EnigmaM3 enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_A));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getLeftRotor().setPosition(0);
		enigma.getMiddleRotor().setPosition(0);
		enigma.getRightRotor().setPosition(0);

		String upperResult = enigma.use("HELLO");

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
		EnigmaM3 enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_A));
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
		EnigmaM3 enigma1 = new EnigmaM3();
		enigma1.setReflector(Reflector.createReflector(Reflector.M3_A));
		enigma1.setLeftRotor(Rotor.createRotor(Rotor.M3_IV));
		enigma1.setMiddleRotor(Rotor.createRotor(Rotor.M3_V));
		enigma1.setRightRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma1.getLeftRotor().setOffset(5);
		enigma1.getMiddleRotor().setOffset(10);
		enigma1.getRightRotor().setOffset(15);
		enigma1.getLeftRotor().setPosition(3);
		enigma1.getMiddleRotor().setPosition(7);
		enigma1.getRightRotor().setPosition(11);
		enigma1.getPlugboard().addCable('A', 'M');
		enigma1.getPlugboard().addCable('F', 'I');

		// Clone config
		EnigmaM3 enigma2 = new EnigmaM3();
		enigma2.setReflector(Reflector.createReflector(Reflector.M3_A));
		enigma2.setLeftRotor(Rotor.createRotor(Rotor.M3_IV));
		enigma2.setMiddleRotor(Rotor.createRotor(Rotor.M3_V));
		enigma2.setRightRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma2.getLeftRotor().setOffset(5);
		enigma2.getMiddleRotor().setOffset(10);
		enigma2.getRightRotor().setOffset(15);
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
		EnigmaM3 enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_A));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_I));
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_III));
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

		// Decrypt by resetting position
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
		TestRunner.run(EnigmaM3Test.class);
	}

}
