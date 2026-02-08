package com.github.fredooo.janigma.core.machine;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the Reflector class. 
 * @author Frederik Dennig
 * @since 2015-09-26
 * @version 0.0.6
 */
public class ReflectorTest extends TestCase {
	
	private static final String[] TEST_M3_REFLECTOR_WIRINGS = {
			"EJMZALYXVBWFCRQUONTSPIKHGD",
			"YRUHQSLDPXNGOKMIEBFZCWVJAT",
			"FVPJIAOYEDRZXWGCTKUQSBNMHL"
		};
	
	private static final String[] TEST_M4_THIN_REFLECTOR_WIRINGS = {
			"ENKQAUYWJICOPBLMDXZVFTHRGS",
			"RDOBJNTKVEHMLFCWZAXGYIPSUQ"
	};

	/**
	 * Creates the test cases.
	 * @param testName Name of the test cases
	 */
	public ReflectorTest(String testName) {
		super(testName);
	}

	/**
	 * Creates a TestSuite for the test cases.
	 * @return Returns the suite of the tests
	 */
	public static Test suite() {
		return new TestSuite(ReflectorTest.class);
	}
	
	/**
	 * Tests the M3 reflector wirings.
	 */
	public void testM3ReflecorWirings() {
		for (int reflectorNum = 0; reflectorNum < TEST_M3_REFLECTOR_WIRINGS.length; reflectorNum++) {
			String wiringChars = TEST_M3_REFLECTOR_WIRINGS[reflectorNum];
			int[] wiringNumbers = Reflector.M3_REFLECTOR_WIRINGS[reflectorNum];
			Assert.assertTrue("M3 reflector wiring of unequal length!", wiringChars.length() == wiringNumbers.length);
			for (int pos = 0; pos < wiringNumbers.length; pos++) {
				char test = wiringChars.charAt(pos);
				try {
					Assert.assertTrue("Invalid M3 reflector wiring!", Original.toInt(test) == wiringNumbers[pos]);
				} catch (NoSuchSymbolException e) {
					Assert.fail("Invalid character tested!");
				}
			}
		}
	}
	
	/**
	 * Tests the M4 thin reflector wirings.
	 */
	public void testM4ThinReflecorWirings() {
		for (int reflectorNum = 0; reflectorNum < TEST_M4_THIN_REFLECTOR_WIRINGS.length; reflectorNum++) {
			String wiringChars = TEST_M4_THIN_REFLECTOR_WIRINGS[reflectorNum];
			int[] wiringNumbers = Reflector.M4_THIN_REFLECTOR_WIRINGS[reflectorNum];
			Assert.assertTrue("M4 thin reflector wiring of unequal length!", wiringChars.length() == wiringNumbers.length);
			for (int pos = 0; pos < wiringNumbers.length; pos++) {
				char test = wiringChars.charAt(pos);
				try {
					Assert.assertTrue("Invalid M4 thin reflector wiring!", Original.toInt(test) == wiringNumbers[pos]);
				} catch (NoSuchSymbolException e) {
					Assert.fail("Invalid character tested!");
				}
			}
		}
	}
	
	/**
	 * Tests the type and name fields.
	 */
	public void testTypeAndName() {
		for (int i = 0; i < Reflector.M3_REFLECTOR_LABELS.length; i++) {
			Reflector reflector = Reflector.createReflector(i);
			assertEquals("Invalid type!", reflector.type(), i);
			assertEquals("Invalid name!", reflector.name(), Reflector.M3_REFLECTOR_LABELS[i]);
		}
		for (int i = 0; i < Reflector.M4_THIN_REFLECTOR_LABELS.length; i++) {
			int type = i + Reflector.M4_THIN_B;
			Reflector reflector = Reflector.createReflector(type);
			assertEquals("Invalid type!", reflector.type(), type);
			assertEquals("Invalid name!", reflector.name(), Reflector.M4_THIN_REFLECTOR_LABELS[i]);
		}
	}
	
	/**
	 * Tests the arguments of the {@link Reflector#createReflector(int)} method.
	 */
	public void testCreateReflectorArguments() {
		for (int i = 0; i <= Reflector.M4_THIN_C; i++) {
			Reflector reflector = Reflector.createReflector(i);
			Assert.assertNotNull("null is not a valid reflector!", reflector);
		}
		try {
			Reflector.createReflector(-1);
			Assert.fail("No IllegalArgumentException thrown!");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Wrong exception message!", "No such reflector!", e.getMessage());
		}
		try {
			Reflector.createReflector(5);
			Assert.fail("No IllegalArgumentException thrown!");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Wrong exception message!", "No such reflector!", e.getMessage());

		}
	}

	/**
	 * Tests all reflector variants (M3 B, C and M4 B_thin)
	 */
	public void testAllReflectorVariants() {
		// Test M3 reflector B
		Reflector refB = Reflector.createReflector(Reflector.M3_B);
		Assert.assertNotNull("M3_B reflector should not be null!", refB);
		Assert.assertEquals("M3_B reflector type mismatch!", Reflector.M3_B, refB.type());
		Assert.assertEquals("M3_B reflector name mismatch!", "B", refB.name());

		// Test M3 reflector C
		Reflector refC = Reflector.createReflector(Reflector.M3_C);
		Assert.assertNotNull("M3_C reflector should not be null!", refC);
		Assert.assertEquals("M3_C reflector type mismatch!", Reflector.M3_C, refC.type());
		Assert.assertEquals("M3_C reflector name mismatch!", "C", refC.name());

		// Test M4 thin reflector B
		Reflector refBThin = Reflector.createReflector(Reflector.M4_THIN_B);
		Assert.assertNotNull("M4_THIN_B reflector should not be null!", refBThin);
		Assert.assertEquals("M4_THIN_B reflector type mismatch!", Reflector.M4_THIN_B, refBThin.type());
		Assert.assertEquals("M4_THIN_B reflector name mismatch!", "B", refBThin.name());
	}

	/**
	 * Tests reflector equality contract
	 */
	public void testEquals() {
		Reflector ref1 = Reflector.createReflector(Reflector.M3_A);
		Reflector ref2 = Reflector.createReflector(Reflector.M3_A);
		Reflector ref3 = Reflector.createReflector(Reflector.M3_B);

		Assert.assertTrue("Same type reflectors should be equal!", ref1.equals(ref2));
		Assert.assertFalse("Different type reflectors should not be equal!", ref1.equals(ref3));
		Assert.assertFalse("Null should not equal reflector!", ref1.equals(null));
		Assert.assertTrue("Reflector should equal itself!", ref1.equals(ref1));
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(ReflectorTest.class);
	}
	
}
