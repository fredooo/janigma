package com.github.fredooo.janigma.io;

import java.io.File;

import com.github.fredooo.janigma.core.machine.EnigmaM3;
import com.github.fredooo.janigma.core.machine.EnigmaM4;
import com.github.fredooo.janigma.core.machine.Reflector;
import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.machine.Enigma;
import com.github.fredooo.janigma.core.machine.Rotor;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Contains the test cases for the FileIo class. 
 * @author Frederik Dennig
 * @since 2016-03-02
 * @version 0.0.6
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
	 * @throws NoSuchSymbolException should never be thrown
	 */
	public static void testSaveAndLoadWithEnigmaM3() throws NoSuchSymbolException {
		final File testFile = new File(TEST_FILE_PATH);
		EnigmaM3 enigma = new EnigmaM3();
		enigma.setReflector(Reflector.createReflector(Reflector.M3_C));
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_VIII));
		enigma.getLeftRotor().setPosition(10);
		enigma.getLeftRotor().setOffset(8);
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_V));
		enigma.getMiddleRotor().setPosition(11);
		enigma.getMiddleRotor().setOffset(9);
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma.getRightRotor().setPosition(12);
		enigma.getRightRotor().setOffset(10);
		enigma.getPlugboard().addCable('A', 'B');
		enigma.getPlugboard().addCable('D', 'F');
		enigma.getPlugboard().addCable('E', 'R');
		enigma.getPlugboard().addCable('G', 'T');
		enigma.getPlugboard().addCable('S', 'L');
		FileIo.writeEnigmaMachineToFile(testFile, enigma);
		Enigma loadedEnigma = FileIo.readEnigmaMachineFromFile(testFile);
		testFile.delete();
		Assert.assertEquals("The loaded Enigma M3 is not equal to the original one!", enigma, loadedEnigma);
	}
	
	/**
	 * Tests the save and load methods with an Enigma M4.
	 * @throws NoSuchSymbolException should never be thrown
	 */
	public static void testSaveAndLoadWithEnigmaM4() throws NoSuchSymbolException {
		final File testFile = new File(TEST_FILE_PATH);
		EnigmaM4 enigma = new EnigmaM4();
		enigma.setThinReflector(Reflector.createReflector(Reflector.M4_THIN_C));
		enigma.setGreekRotor(Rotor.createRotor(Rotor.M4_GREEK_GAMMA));
		enigma.getGreekRotor().setPosition(20);
		enigma.getGreekRotor().setOffset(19);
		enigma.setLeftRotor(Rotor.createRotor(Rotor.M3_III));
		enigma.getLeftRotor().setPosition(11);
		enigma.getLeftRotor().setOffset(10);
		enigma.setMiddleRotor(Rotor.createRotor(Rotor.M3_VI));
		enigma.getMiddleRotor().setPosition(12);
		enigma.getMiddleRotor().setOffset(11);
		enigma.setRightRotor(Rotor.createRotor(Rotor.M3_II));
		enigma.getRightRotor().setPosition(13);
		enigma.getRightRotor().setOffset(12);
		enigma.getPlugboard().addCable('C', 'F');
		enigma.getPlugboard().addCable('W', 'V');
		enigma.getPlugboard().addCable('Z', 'X');
		enigma.getPlugboard().addCable('Y', 'N');
		enigma.getPlugboard().addCable('M', 'O');
		FileIo.writeEnigmaMachineToFile(testFile, enigma);
		Enigma loadedEnigma = FileIo.readEnigmaMachineFromFile(testFile);
		testFile.delete();
		Assert.assertEquals("The loaded Enigma M4 is not equal to the original one!", enigma, loadedEnigma);
	}
	
	/**
	 * Tests the save method with null File object.
	 */
	public static void testSaveWithNullFile() {
		FileIo.writeEnigmaMachineToFile(null, new EnigmaM3());
	}
	
	/**
	 * Tests the save method with null Enigma object.
	 */
	public static void testSaveWithNullEnigma() {
		final File testFile = new File(TEST_FILE_PATH);
		try {
			FileIo.writeEnigmaMachineToFile(testFile, null);
			Assert.fail("No IllegalArgumentException thrown!");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Wrong exception message!", "Can't serialize null!", e.getMessage());
		}
	}
	
	/**
	 * Tests the load method with a non-existing file.
	 */
	public static void testLoadWithNonExistingFile() {
		final File testFile = new File(TEST_FILE_PATH);
		Enigma enigma = FileIo.readEnigmaMachineFromFile(testFile);
		Assert.assertNull("Enigma machine not null!", enigma);
	}
	
	/**
	 * Tests the load method with null File object.
	 */
	public static void testLoadWithNullFile() {
		Enigma enigma = FileIo.readEnigmaMachineFromFile(null);
		Assert.assertNull("Enigma machine not null!", enigma);
	}
	
	/**
	 * Runs all test defined in this class.
	 * @param args not used
	 */
	public static void main(String[] args) {
		TestRunner.run(FileIoTest.class);
	}

}
