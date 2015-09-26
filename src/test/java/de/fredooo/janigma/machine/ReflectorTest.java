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
 * @since 2011-06-01
 * @version 0.0.1 (last edited 2015-09-26)
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
		return new TestSuite(RotorTest.class);
	}
	
	/**
	 * Tests the M3 reflector wirings.
	 */
	public void testM3ReflecorWirings() {
		for (int reflectorNum = 0; reflectorNum < TEST_M3_REFLECTOR_WIRINGS.length; reflectorNum++) {
			String[] wiringChars = TEST_M3_REFLECTOR_WIRINGS[reflectorNum].split("");
			int[] wiringNumbers = Reflector.M3_REFLECTOR_WIRINGS[reflectorNum];
			Assert.assertTrue("M3 refelctor wiring of unequal lenght!", wiringChars.length == wiringNumbers.length);
			for (int pos = 0; pos < wiringNumbers.length; pos++) {
				char test = wiringChars[pos].charAt(0);
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
			String[] wiringChars = TEST_M4_THIN_REFLECTOR_WIRINGS[reflectorNum].split("");
			int[] wiringNumbers = Reflector.M4_THIN_REFLECTOR_WIRINGS[reflectorNum];
			Assert.assertTrue("M4 thin refelctor wiring of unequal lenght!", wiringChars.length == wiringNumbers.length);
			for (int pos = 0; pos < wiringNumbers.length; pos++) {
				char test = wiringChars[pos].charAt(0);
				try {
					Assert.assertTrue("Invalid M4 thin reflector wiring!", Original.toInt(test) == wiringNumbers[pos]);
				} catch (NoSuchSymbolException e) {
					Assert.fail("Invalid character tested!");
				}
			}
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
