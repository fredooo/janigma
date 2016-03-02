package de.fredooo.janigma.machine;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the Reflector class. 
 * @author Frederik Dennig
 * @since 2015-09-26
 * @version 0.0.3 (last edited 2016-03-02)
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
			Assert.assertEquals("Invalid type!", reflector.type(), i);
			Assert.assertEquals("Invalid name!", reflector.name(), Reflector.M3_REFLECTOR_LABELS[i]);
		}
		for (int i = 0; i < Reflector.M4_THIN_REFLECTOR_LABELS.length; i++) {
			int type = i + Reflector.M4_THIN_B;
			Reflector reflector = Reflector.createReflector(type);
			Assert.assertEquals("Invalid type!", reflector.type(), type);
			Assert.assertEquals("Invalid name!", reflector.name(), Reflector.M4_THIN_REFLECTOR_LABELS[i]);
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
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(ReflectorTest.class);
	}
	
}
