package de.fredooo.janigma.io;

import java.io.File;

import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.EnigmaM4;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the FileIo class. 
 * @author Frederik Dennig
 * @since 2016-03-02
 * @version 0.0.1 (last edited 2016-03-02)
 */
public class FileIoTest extends TestCase {
	
	private static String TEST_FILE_PATH = "./janigma_test.json";
	
	/**
	 * Creates the test cases.
	 * @param testName Name of the test cases
	 */
	public FileIoTest(String testName) {
		super(testName);
	}

	/**
	 * Creates a TestSuite for the test cases.
	 * @return Returns the suite of the tests
	 */
	public static Test suite() {
		return new TestSuite(FileIoTest.class);
	}
	
	/**
	 * Tests the save and load methods with an Enigma M3.
	 */
	public static void testSaveAndLoadWithEnigmaM3() {
		final File testFile = new File(TEST_FILE_PATH);
		final EnigmaM3 enigma = new EnigmaM3();
		FileIo.saveEnigmaMachine(testFile, enigma);
		FileIo.loadEnigmaMachine(testFile);
		testFile.delete();
	}
	
	/**
	 * Tests the save and load methods with an Enigma M4.
	 */
	public static void testSaveAndLoadWithEnigmaM4() {
		final File testFile = new File(TEST_FILE_PATH);
		final EnigmaM4 enigma = new EnigmaM4();
		FileIo.saveEnigmaMachine(testFile, enigma);
		FileIo.loadEnigmaMachine(testFile);
		testFile.delete();
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(FileIoTest.class);
	}

}
