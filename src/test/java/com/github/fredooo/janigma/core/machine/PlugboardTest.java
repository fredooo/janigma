package com.github.fredooo.janigma.core.machine;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the Plugboard class. 
 * @author Frederik Dennig
 * @since 2015-05-29
 * @version 0.0.6
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
		pb.addCable(0, 1);
		pb.addCable(2, 3);
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(0));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(1));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(2));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(3));
		pb.removeCable(0);
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(0));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(1));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(2));
		Assert.assertTrue("Symbol not plugged!", pb.isPlugged(3));
		pb.removeCable(3);
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(0));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(1));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(2));
		Assert.assertFalse("Symbol plugged!", pb.isPlugged(3));
	}

	/**
	 * Tests max cables (10)
	 */
	public void testMaxCables() {
		Plugboard pb = new Plugboard();
		// Add 10 cables (max for Enigma)
		pb.addCable(0, 1);   // A-B
		pb.addCable(2, 3);   // C-D
		pb.addCable(4, 5);   // E-F
		pb.addCable(6, 7);   // G-H
		pb.addCable(8, 9);   // I-J
		pb.addCable(10, 11); // K-L
		pb.addCable(12, 13); // M-N
		pb.addCable(14, 15); // O-P
		pb.addCable(16, 17); // Q-R
		pb.addCable(18, 19); // S-T

		// Verify all 20 symbols are plugged
		for (int i = 0; i < 20; i++) {
			Assert.assertTrue("Symbol " + i + " should be plugged!", pb.isPlugged(i));
		}

		// Verify remaining 6 symbols are not plugged
		for (int i = 20; i < 26; i++) {
			Assert.assertFalse("Symbol " + i + " should not be plugged!", pb.isPlugged(i));
		}

		// Verify correct swapping
		Assert.assertEquals("A should swap to B!", 1, pb.swappedWith(0));
		Assert.assertEquals("B should swap to A!", 0, pb.swappedWith(1));
		Assert.assertEquals("S should swap to T!", 19, pb.swappedWith(18));
		Assert.assertEquals("T should swap to S!", 18, pb.swappedWith(19));
	}

	/**
	 * Tests all 26 letters
	 */
	public void testAllLetters() {
		Plugboard pb = new Plugboard();

		// Test that all letters start unplugged
		for (int i = 0; i < 26; i++) {
			Assert.assertFalse("Letter " + i + " should start unplugged!", pb.isPlugged(i));
			Assert.assertEquals("Letter " + i + " should map to itself!", i, pb.swappedWith(i));
		}

		// Plug every even letter with next odd letter
		for (int i = 0; i < 26; i += 2) {
			if (i + 1 < 26) {
				pb.addCable(i, i + 1);
			}
		}

		// Verify swapping
		for (int i = 0; i < 25; i += 2) {
			Assert.assertEquals("Even letter should swap to odd!", i + 1, pb.swappedWith(i));
			Assert.assertEquals("Odd letter should swap to even!", i, pb.swappedWith(i + 1));
		}
	}

	/**
	 * Tests char overload addCable(char, char)
	 */
	public void testCharOverload() throws NoSuchSymbolException {
		Plugboard pb = new Plugboard();

		// Test char overload
		pb.addCable('A', 'M');
		pb.addCable('F', 'I');
		pb.addCable('N', 'V');

		Assert.assertTrue("A should be plugged!", pb.isPlugged(0));
		Assert.assertTrue("M should be plugged!", pb.isPlugged(12));
		Assert.assertTrue("F should be plugged!", pb.isPlugged(5));
		Assert.assertTrue("I should be plugged!", pb.isPlugged(8));
		Assert.assertTrue("N should be plugged!", pb.isPlugged(13));
		Assert.assertTrue("V should be plugged!", pb.isPlugged(21));

		// Verify correct swapping
		Assert.assertEquals("A should swap to M!", 12, pb.swappedWith(0));
		Assert.assertEquals("M should swap to A!", 0, pb.swappedWith(12));
		Assert.assertEquals("F should swap to I!", 8, pb.swappedWith(5));
		Assert.assertEquals("I should swap to F!", 5, pb.swappedWith(8));
	}

	/**
	 * Tests cable order independence
	 */
	public void testCableOrderIndependence() {
		Plugboard pb1 = new Plugboard();
		pb1.addCable(0, 1);
		pb1.addCable(2, 3);
		pb1.addCable(4, 5);

		Plugboard pb2 = new Plugboard();
		pb2.addCable(4, 5);
		pb2.addCable(0, 1);
		pb2.addCable(2, 3);

		// Both plugboards should be equal
		Assert.assertTrue("Cable order should not affect plugboard!", pb1.equals(pb2));

		// Test output for all letters
		for (int i = 0; i < 26; i++) {
			Assert.assertEquals("Output should be same regardless of cable order!", pb1.swappedWith(i), pb2.swappedWith(i));
		}
	}

	/**
	 * Tests symmetric swap property: swappedWith(swappedWith(x)) == x
	 */
	public void testSymmetricSwap() {
		Plugboard pb = new Plugboard();
		pb.addCable(0, 5);
		pb.addCable(10, 15);
		pb.addCable(20, 25);

		// Test double swap returns original
		for (int i = 0; i < 26; i++) {
			int swapped = pb.swappedWith(i);
			int doubleSwapped = pb.swappedWith(swapped);
			Assert.assertEquals("Double swap should return original!", i, doubleSwapped);
		}
	}

	/**
	 * Tests plugboard equality contract
	 */
	public void testEquals() {
		Plugboard pb1 = new Plugboard();
		pb1.addCable(0, 1);
		pb1.addCable(2, 3);

		Plugboard pb2 = new Plugboard();
		pb2.addCable(0, 1);
		pb2.addCable(2, 3);

		Plugboard pb3 = new Plugboard();
		pb3.addCable(0, 1);

		Assert.assertTrue("Same cables should be equal!", pb1.equals(pb2));
		Assert.assertFalse("Different cables should not be equal!", pb1.equals(pb3));
		Assert.assertFalse("Null should not equal plugboard!", pb1.equals(null));
		Assert.assertTrue("Plugboard should equal itself!", pb1.equals(pb1));
	}

	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(PlugboardTest.class);
	}

}
