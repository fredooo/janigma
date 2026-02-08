package com.github.fredooo.janigma.core.machine;

import java.util.Arrays;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the Rotor class. 
 * @author Frederik Dennig
 * @since 2015-05-25
 * @version 0.0.6
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
		rotor.setPosition(1);
		assertEquals("Setting rotor position test failed!", 1, rotor.getPosition());
		rotor.setPosition(0);
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
	 * Tests signal path inverse property: outwardsOutputOf(inwardsOutputOf(x)) == x
	 */
	public void testSignalPathInverse() {
		for (int rotorType = 0; rotorType < 10; rotorType++) {
			Rotor rotor = Rotor.createRotor(rotorType);
			for (int position = 0; position < 26; position++) {
				rotor.setPosition(position);
				for (int offset = 0; offset < 26; offset++) {
					rotor.setOffset(offset);
					for (int input = 0; input < 26; input++) {
						int inwards = rotor.inwardsOutputOf(input);
						int outwards = rotor.outwardsOutputOf(inwards);
						Assert.assertEquals("Signal path not inverse at rotor " + rotorType + " pos " + position + " offset " + offset, input, outwards);
					}
				}
			}
		}
	}

	/**
	 * Tests boundary positions (0, 1, 24, 25, wraparound)
	 */
	public void testBoundaryPositions() {
		Rotor rotor = Rotor.createRotor(Rotor.M3_I);

		// Test position 0
		rotor.setPosition(0);
		Assert.assertEquals("Position 0 failed!", 0, rotor.getPosition());
		rotor.decrementPosition();
		Assert.assertEquals("Decrement from 0 failed!", 25, rotor.getPosition());

		// Test position 1
		rotor.setPosition(1);
		Assert.assertEquals("Position 1 failed!", 1, rotor.getPosition());
		rotor.decrementPosition();
		Assert.assertEquals("Decrement from 1 failed!", 0, rotor.getPosition());

		// Test position 24
		rotor.setPosition(24);
		Assert.assertEquals("Position 24 failed!", 24, rotor.getPosition());
		rotor.incrementPosition();
		Assert.assertEquals("Increment from 24 failed!", 25, rotor.getPosition());

		// Test position 25
		rotor.setPosition(25);
		Assert.assertEquals("Position 25 failed!", 25, rotor.getPosition());
		rotor.incrementPosition();
		Assert.assertEquals("Increment from 25 (wraparound) failed!", 0, rotor.getPosition());

		// Test wraparound in reverse
		rotor.setPosition(0);
		rotor.decrementPosition();
		Assert.assertEquals("Decrement wraparound failed!", 25, rotor.getPosition());
	}

	/**
	 * Tests offset interaction with position
	 */
	public void testOffsetInteraction() {
		Rotor rotor = Rotor.createRotor(Rotor.M3_I);

		// Test that different offset affects output for same position/input
		rotor.setPosition(0);
		rotor.setOffset(0);
		int output1 = rotor.inwardsOutputOf(0);

		rotor.setPosition(0);
		rotor.setOffset(5);
		int output2 = rotor.inwardsOutputOf(0);

		Assert.assertFalse("Offset should affect output!", output1 == output2);

		// Test that position+offset combination affects signal path
		rotor.setPosition(10);
		rotor.setOffset(5);
		int output3 = rotor.inwardsOutputOf(0);

		rotor.setPosition(5);
		rotor.setOffset(10);
		int output4 = rotor.inwardsOutputOf(0);

		// Different pos/offset combos should give different results
		Assert.assertFalse("Different position/offset combos should differ!", output3 == output4);
	}

	/**
	 * Tests rotor equality contract
	 */
	public void testEquals() {
		Rotor rotor1 = Rotor.createRotor(Rotor.M3_I);
		Rotor rotor2 = Rotor.createRotor(Rotor.M3_I);
		Rotor rotor3 = Rotor.createRotor(Rotor.M3_II);

		Assert.assertTrue("Same type rotors should be equal!", rotor1.equals(rotor2));
		Assert.assertFalse("Different type rotors should not be equal!", rotor1.equals(rotor3));
		Assert.assertFalse("Null should not equal rotor!", rotor1.equals(null));
		Assert.assertTrue("Rotor should equal itself!", rotor1.equals(rotor1));

		// Note: equality only compares type/name, not position/offset
		rotor1.setPosition(10);
		rotor2.setPosition(5);
		Assert.assertTrue("Position difference should not affect equality!", rotor1.equals(rotor2));
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(RotorTest.class);
	}

}
