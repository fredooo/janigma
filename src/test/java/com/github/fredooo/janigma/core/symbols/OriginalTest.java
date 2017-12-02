package com.github.fredooo.janigma.core.symbols;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the Original class. 
 * @author Frederik Dennig
 * @since 2015-05-28
 * @version 0.0.1 (last edited 2015-09-28)
 */
public class OriginalTest extends TestCase {

	/**
	 * Creates the test cases.
	 * @param testName Name of the test cases
	 */
	public OriginalTest(String testName) {
		super(testName);
	}

	/**
	 * Creates a TestSuite for the test cases.
	 * @return Returns the suite of the tests
	 */
	public static Test suite() {
		return new TestSuite(OriginalTest.class);
	}
	
	/**
	 * Test the toInt method.
	 */
	public void testToInt() {
		char c = 'a';
		try {
			for (int v = 0; v < 26; v++) {
				Assert.assertTrue("Invalid translation of a symbol!", Original.toInt(c) == v);
				Assert.assertTrue("Invalid translation of a symbol!", Original.toInt(Character.toUpperCase(c)) == v);
				c++;
			}
		} catch (NoSuchSymbolException e) {
			Assert.fail("Valid character causes an exception!");
		}
		try {
			Original.toInt(' ');
			Assert.fail("Invalid character accepted!");
		} catch (NoSuchSymbolException e) {
			Assert.assertTrue("Exception has wrong character!", e.symbol() == ' ');
		}
		try {
			Original.toInt('-');
			Assert.fail("Invalid character accepted!");
		} catch (NoSuchSymbolException e) {
			Assert.assertTrue("Exception has wrong character!", e.symbol() == '-');
		}
	}
	
	/**
	 * Tests the isValidString method.
	 */
	public void testIsValidString() {
		final String valid = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
		Assert.assertTrue("Valid string was determined as invalid!", Original.isValidString(valid));
		Assert.assertFalse("Invalid string was determined as valid!", Original.isValidString("ABC-"));
		Assert.assertFalse("Invalid string was determined as valid!", Original.isValidString("abcd ABCD"));
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(OriginalTest.class);
	}

}
