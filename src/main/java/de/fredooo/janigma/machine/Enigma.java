package de.fredooo.janigma.machine;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

/**
 * Implements the common functionalities of an Enigma M3 and M4.
 * @author Frederik Dennig
 * @since 2011-11-18
 * @version 0.0.3 (last revised 2015-09-21)
 */
public abstract class Enigma {
	
	protected Rotor[] m3Rotors;
	protected Rotor rightRotor;
	protected Rotor middleRotor;
	protected Rotor leftRotor;
	protected Plugboard plugboard;
	protected boolean doubleStep;
	
	/**
	 * Constructs a basic Enigma object, that only consists of a plugboard.
	 */
	private Enigma() {
		plugboard = new Plugboard();
		doubleStep = false;
	}
	
	/**
	 * Constructs a basic Enigma object, that only consists of a
	 * plugboard and rotors.
	 * @param m3Rotors all available rotors for a Enigma machine
	 * @see EnigmaM3
	 */
	protected Enigma(Rotor[] m3Rotors) {
		this();
		this.m3Rotors = m3Rotors;
		rightRotor = m3Rotors[2];
		middleRotor = m3Rotors[1];
		leftRotor = m3Rotors[0];
	}

	/**
	 * Returns all rotors of this Enigma M3 machine.
	 * @return the M3 rotors of this machine
	 */
	public Rotor[] getM3Rotors() {
		return m3Rotors;
	}
	
	/**
	 * Returns the right rotor of this Enigma machine.
	 * @return the current right rotor 
	 */
	public Rotor getRightRotor() {
		return rightRotor;
	}
	
	/**
	 * Sets the right rotor of the this Enigma machine to another one.
	 * @param rotor a given rotor
	 */
	public void setRightRotor(Rotor rotor) {
		rightRotor = rotor;
	}
	
	/**
	 * Returns the middle rotor of this Enigma machine.
	 * @return the current middle rotor 
	 */
	public Rotor getMiddleRotor() {
		return middleRotor;
	}

	/**
	 * Sets the middle rotor of the this Enigma machine to a given one.
	 * @param rotor A given rotor.
	 */
	public void setMiddleRotor(Rotor rotor) {
		middleRotor = rotor;
	}

	/**
	 * Returns the left rotor of this Enigma machine.
	 * @return Returns the left Rotor. 
	 */
	public Rotor getLeftRotor() {
		return leftRotor;
	}
	
	/**
	 * Sets the left rotor of the this Enigma machine to a given one.
	 * @param rotor A given rotor.
	 */
	public void setLeftRotor(Rotor rotor) {
		leftRotor = rotor;
	}

	/**
	 * Returns the plugboard of this Enigma M3 machine.
	 * @return Returns the plugboard.
	 */
	public Plugboard getPlugboard() {
		return plugboard;
	}
	
	/**
	 * This method converts a string to an encrypted output or vice versa. 
	 * @param input The input to convert.
	 * @return Returns the encrypted or decrypted input.
	 * @throws NoSuchSymbolException
	 */
	public String use(String input) throws NoSuchSymbolException {
		String s = "";
		for(int i = 0; i < input.length(); i++) {
			s += use(input.charAt(i));
		}
		return s;
	}
	
	
	/**
	 * This method converts one single char input to an encrypted output or
	 * vice versa.
	 * @param input The input to convert.
	 * @return Returns the encrypted or decrypted input.
	 * @throws NoSuchSymbolException 
	 */
	public char use(char input) throws NoSuchSymbolException {
		return Original.toChar(use(Original.toInt(input)));
	}
	
	/**
	 * This method converts many ints to an encrypted output or vice versa.
	 * @param input The input to convert.
	 * @return Returns the encrypted or decrypted input.
	 */
	public int[] use(int[] input) {
		int[] output = new int[input.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = use(input[i]);
		}
		return output;
	}

	/**
	 * This method converts one single int input to an encrypted output or
	 * vice versa.
	 * @param input The input to convert.
	 * @return Returns the encrypted or decrypted input.
	 */
	public abstract int use(int input);
	
	/**
	 * This method handles the carry over operations of the calculations in {@link #use}. 
	 * @param number The number from which you want to remove the carry over.
	 * @return Returns a number between 0 and 25.
	 */
	protected static int carryOver(int number) {
		if (number < 0) {
			while (number < 0) {
				number += 26;
			}
			return number;
		} else {
			return number % 26;
		}
	}
	
}
