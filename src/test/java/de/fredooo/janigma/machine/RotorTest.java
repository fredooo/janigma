package de.fredooo.janigma.machine;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class RotorTest extends TestCase {

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
     * Tests a rotor.
     */
    public void testWirings() {
    	Assert.assertTrue(true);
    }

	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(RotorTest.class);
	}

}
