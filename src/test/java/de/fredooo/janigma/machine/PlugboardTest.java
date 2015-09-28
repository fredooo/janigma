package de.fredooo.janigma.machine;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the Plugboard class. 
 * @author Frederik Dennig
 * @since 2015-05-29
 * @version 0.0.1 (last edited 2015-09-29)
 */
public class PlugboardTest extends TestCase {

	/**
	 * Creates the test cases.
	 * @param testName Name of the test cases
	 */
	public PlugboardTest(String testName) {
		super(testName);
	}

	/**
	 * Creates a TestSuite for the test cases.
	 * @return Returns the suite of the tests
	 */
	public static Test suite() {
		return new TestSuite(PlugboardTest.class);
	}
	
	/**
	 * Tests the basic Plugboard methods.
	 */
	public void testAddAndRemoveCable() {
		Plugboard pb = new Plugboard();
		pb.addCabel(0, 1);
		pb.addCabel(2, 3);
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(0));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(1));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(2));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(3));
		pb.removeCabel(0);
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(0));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(1));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(2));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(3));
		pb.removeCabel(3);
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(0));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(1));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(2));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(3));
	}

	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(PlugboardTest.class);
	}

}
