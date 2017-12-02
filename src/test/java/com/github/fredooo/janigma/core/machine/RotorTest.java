package com.github.fredooo.janigma.core.machine;

import java.util.Arrays;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the Rotor class. 
 * @author Frederik Dennig
 * @since 2015-05-25
 * @version 0.0.2 (last edited 2016-03-02)
 */
public class RotorTest extends TestCase {
	
	private static final String[] TEST_M3_ROTOR_WIRINGS = {
			"EKMFLGDQVZNTOWYHXUSPAIBRCJ",
			"AJDKSIRUXBLHWTMCQGZNPYFVOE",
			"BDFHJLCPRTXVZNYEIWGAKMUSQO",
			"ESOVPZJAYQUIRHXLNFTGKDCMWB",
			"VZBRGITYUPSDNHLXAWMJQOFECK",
			"JPGVOUMFYQBENHZRDKASXLICTW",
			"NZJHGRCXMYSWBOUFAIVLPEKQDT",
			"FKQHTLXOCBJSPDZRAMEWNIUYGV"
		};
	
	private static final String[] TEST_M4_GREEK_ROTOR_WIRINGS = {
			"LEYJVCNIXWPBQMDRTAKZGFUHOS",
			"FSOKANUERHMBTIYCWLQPZXVGJD"
	};

	/**
	 * Creates the test cases.
	 * @param testName Name of the test cases
	 */
	public RotorTest(String testName) {
		super(testName);
	}

	/**
	 * Creates a TestSuite for the test cases.
	 * @return Returns the suite of the tests
	 */
	public static Test suite() {
		return new TestSuite(RotorTest.class);
	}

	/**
	 * Tests the M3 rotor wirings.
	 */
	public void testM3RotorWirings() {
		for (int rotorNum = 0; rotorNum < TEST_M3_ROTOR_WIRINGS.length; rotorNum++) {
			String wiringChars = TEST_M3_ROTOR_WIRINGS[rotorNum];
			int[] wiringNumbers = Rotor.M3_ROTOR_WIRINGS[rotorNum];
			Assert.assertTrue("M3 rotor wiring of unequal length!", wiringChars.length() == wiringNumbers.length);
			for (int pos = 0; pos < wiringNumbers.length; pos++) {
				char test = wiringChars.charAt(pos);
				try {
					Assert.assertTrue("Invalid M3 rotor wiring!", Original.toInt(test) == wiringNumbers[pos]);
				} catch (NoSuchSymbolException e) {
					Assert.fail("Invalid character tested!");
				}
			}
		}
	}
	
	/**
	 * Tests the M4 greek rotor wirings.
	 */
	public void testM4GreekRotorWirings() {
		for (int rotorNum = 0; rotorNum < TEST_M4_GREEK_ROTOR_WIRINGS.length; rotorNum++) {
			String wiringChars = TEST_M4_GREEK_ROTOR_WIRINGS[rotorNum];
			int[] wiringNumbers = Rotor.M4_GREEK_ROTOR_WIRINGS[rotorNum];
			Assert.assertTrue("M4 greek rotor wiring of unequal length!", wiringChars.length() == wiringNumbers.length);
			for (int pos = 0; pos < wiringNumbers.length; pos++) {
				char test = wiringChars.charAt(pos);
				try {
					Assert.assertTrue("Invalid M4 greek rotor wiring!", Original.toInt(test) == wiringNumbers[pos]);
				} catch (NoSuchSymbolException e) {
					Assert.fail("Invalid character tested!");
				}
			}
		}
	}
	
	/**
	 * Tests incrementing, decrementing and setting the rotor position.
	 */
	public void testIncrementAndDecrementPosition() {
		Rotor rotor = Rotor.createRotor(Rotor.M3_I);
		for (int i = 0; i < 52; i++) {
			assertEquals("Increment rotor position test failed!", i % 26, rotor.getPosition());
			rotor.incrementPosition();
		}
		rotor.setPostion(1);
		assertEquals("Setting rotor position test failed!", 1, rotor.getPosition());
		rotor.setPostion(0);
		assertEquals("Setting rotor position test failed!", 0, rotor.getPosition());
		for (int i = 52; i > 0; i--) {
			assertEquals("Decrement rotor position test failed!", i % 26, rotor.getPosition());
			rotor.decrementPosition();
		}
	}

	/**
	 * Tests the inverseWiring method.
	 */
	public void testInverseWiring() {
		final int[] in = { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
		final int[] out = { 20, 22, 24, 6, 0, 3, 5, 15, 21, 25, 1, 4, 2, 10, 12, 19, 7, 23, 18, 11, 17, 8, 13, 16, 14, 9 };
		Assert.assertTrue("The inversion of a wiring failed!", Arrays.equals(Rotor.inverseWiring(in), out));
	}

	/**
	 * Tests the type and name fields.
	 */
	public void testTypeAndName() {
		for (int i = 0; i < Rotor.M3_ROTOR_LABELS.length; i++) {
			Rotor rotor = Rotor.createRotor(i);
			assertEquals("Invalid type!", rotor.type(), i);
			assertEquals("Invalid name!", rotor.name(), Rotor.M3_ROTOR_LABELS[i]);
		}
		for (int i = 0; i < Rotor.M4_GREEK_ROTOR_LABELS.length; i++) {
			int type = i + Rotor.M4_GREEK_BETA;
			Rotor rotor = Rotor.createRotor(type);
			assertEquals("Invalid type!", rotor.type(), type);
			assertEquals("Invalid name!", rotor.name(), Rotor.M4_GREEK_ROTOR_LABELS[i]);
		}
	}
	
	/**
	 * Tests the arguments of the {@link Rotor#createRotor(int)} method.
	 */
	public void testCreateReflectorArguments() {
		for (int i = 0; i <= Rotor.M4_GREEK_GAMMA; i++) {
			Rotor rotor = Rotor.createRotor(i);
			Assert.assertNotNull("null is not a valid rotor!", rotor);
		}
		try {
			Rotor.createRotor(-1);
			Assert.fail("No IllegalArgumentException thrown!");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Wrong exception message!", "No such rotor!", e.getMessage());
		}	
		try {
			Rotor.createRotor(10);
			Assert.fail("No IllegalArgumentException thrown!");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Wrong exception message!", "No such rotor!", e.getMessage());

		}
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(RotorTest.class);
	}

}
