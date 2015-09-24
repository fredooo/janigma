package de.fredooo.janigma.machine;

/**
 * Provides the rotors of an Enigma machine.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2015-09-23)
 */
public class Rotor {
	
	/*
	 * Enigma M3 rotor informations.
	 */
	
	private static final int[] M3_ROTOR_I_WIRING = {4,10,12,5,11,6,3,16,21,25,13,19,14,22,24,7,23,20,18,15,0,8,1,17,2,9};
	private static final int[] M3_ROTOR_II_WIRING = {0,9,3,10,18,8,17,20,23,1,11,7,22,19,12,2,16,6,25,13,15,24,5,21,14,4};
	private static final int[] M3_ROTOR_III_WIRING = {1,3,5,7,9,11,2,15,17,19,23,21,25,13,24,4,8,22,6,0,10,12,20,18,16,14};
	private static final int[] M3_ROTOR_IV_WIRING = {4,18,14,21,15,25,9,0,24,16,20,8,17,7,23,11,13,5,19,6,10,3,2,12,22,1};
	private static final int[] M3_ROTOR_V_WIRING = {21,25,1,17,6,8,19,24,20,15,18,3,13,7,11,23,0,22,12,9,16,14,5,4,2,10};
	private static final int[] M3_ROTOR_VI_WIRING = {9,15,6,21,14,20,12,5,24,16,1,4,13,7,25,17,3,10,0,18,23,11,8,2,19,22};
	private static final int[] M3_ROTOR_VII_WIRING =  {13,25,9,7,6,17,2,23,12,24,18,22,1,14,20,5,0,8,21,11,15,4,10,16,3,19};
	private static final int[] M3_ROTOR_VIII_WIRING = {5,10,16,7,19,11,23,14,2,1,9,18,15,3,25,17,0,12,4,22,13,8,20,24,6,21};
	
	private static final int[] M3_ROTOR_I_TRANSFERNOTCHES = {16};
	private static final int[] M3_ROTOR_II_TRANSFERNOTCHES = {4};
	private static final int[] M3_ROTOR_III_TRANSFERNOTCHES = {21};
	private static final int[] M3_ROTOR_IV_TRANSFERNOTCHES = {9};
	private static final int[] M3_ROTOR_V_TRANSFERNOTCHES = {25};
	private static final int[] M3_ROTOR_VI_TRANSFERNOTCHES = {25,12};
	private static final int[] M3_ROTOR_VII_TRANSFERNOTCHES = {25,12};
	private static final int[] M3_ROTOR_VIII_TRANSFERNOTCHES = {25,12};
	
	/*
	 * Enigma M4 greek rotor informations.
	 */
	
	private static final int[] M4_GREEK_ROTOR_BETA = {11,4,24,9,21,2,13,8,23,22,15,1,16,12,3,17,19,0,10,25,6,5,20,7,14,18};
	private static final int[] M4_GREEK_ROTOR_GAMMA = {5,18,14,10,0,13,20,4,17,7,12,1,19,8,24,2,22,11,16,15,25,23,21,6,9,3};
	
	/*
	 * Class defines 8 monolithic Rotor objects.
	 */
	
	private static Rotor[] rotors;
	
	/*
	 * Class defines 2 monolithic GreekRotor objects.
	 */
	
	private static Rotor[] greekrotors;
	
	/*
	 * Class variables.
	 */

	private String name;
	private int position;
	private int[] inwardswiring;
	private int[] outwardswiring;
	private int[] transfernotches;
	private int offset;
	
	/**
	 * Constructs a rotor , with a given name, a given wiring,
	 * with position and offset 0, but without a transfer notch.
	 * @param name A given name.
	 * @param wiring A given wiring.
	 */
	@Deprecated
	protected Rotor(String name, int[] wiring) {
		this.name = name;
		position = 0;
		inwardswiring = wiring;
		outwardswiring = inverseWiring(wiring);
		offset = 0;
	}
	
	/**
	 * Constructs a rotor, with a given name, a given wiring and given transfer
	 * notches, initialized at position 0 with offset 0.
	 * @param name A given name.
	 * @param wiring A given wiring.
	 * @param transfernotches Given transfer notches.
	 */
	private Rotor(String name, int[] wiring, int[] transfernotches) {
		this.name = name;
		position = 0;
		inwardswiring = wiring;
		outwardswiring = inverseWiring(wiring);
		this.transfernotches = transfernotches;
		offset = 0;
	}
	
	/**
	 * Returns the current position of a rotor.
	 * @return Returns the position.
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Changes the position of rotor.
	 * @param position The position you want the rotor be in.
	 */
	public void setPostion(int position) {
		this.position = position;
	}
	
	/**
	 * Increments the position of a rotor by 1.
	 */
	public void incrementPosition() {
		if (position == 25) {
			position = 0;
		} else {
			position += 1;
		}
	}
	
	/**
	 * Decrements the position of a rotor by 1.
	 */
	public void decrementPosition() {
		if (position == 0) {
			position = 25;
		} else {
			position -= 1;
		}
	}
	
	/**
	 * Returns the corresponding output of a character, in the
	 * inwards direction of the rotor wiring. 
	 * @param input The input character as an int.
	 * @return Returns the corresponding output.
	 */
	public int getInwardsOutput(int input) {
		return inwardswiring[input];
	}
	
	/**
	 * Returns the corresponding output of a character, in the
	 * outwards direction of the rotor wiring. 
	 * @param input The input character as an int.
	 * @return Returns the corresponding output.
	 */
	public int getOutwardsOutput(int input) {
		return outwardswiring[input];
	}
	
	/**
	 * Returns the position of the transfer notches of the rotor.
	 * @return Returns the position of the transfer notch.
	 */
	public int[] getTransferNotches() {
		return transfernotches;
	}
	
	/**
	 * Returns the current offset of a rotor.
	 * @return Returns the offset as an int.
	 */
	public int getOffset() {
		return offset;
	}
	
	/**
	 * Sets the offset of a rotor to a given value.
	 * @param offset The offset you want to set the rotor to.
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Inverses a wiring.
	 * @param wiring The given wiring.
	 * @return The inverted wiring as an int array.
	 */
	private static int[] inverseWiring(int[] wiring) {
		int[] invertedwiring =  new int[26];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (wiring[j] == i) {
					invertedwiring[i] = j;
				}
			}
		}
		return invertedwiring;
	}
	
	/**
	 * Builds all rotors for the Enigma M3 and returns them. 
	 * @return Returns built rotors.
	 */
	public static Rotor[] getRotors() {
		if (rotors == null) {
			rotors = new Rotor[8];
			rotors[0] = new Rotor("I", M3_ROTOR_I_WIRING,  M3_ROTOR_I_TRANSFERNOTCHES);
			rotors[1] = new Rotor("II", M3_ROTOR_II_WIRING,  M3_ROTOR_II_TRANSFERNOTCHES);
			rotors[2] = new Rotor("III", M3_ROTOR_III_WIRING,  M3_ROTOR_III_TRANSFERNOTCHES);
			rotors[3] = new Rotor("IV", M3_ROTOR_IV_WIRING,  M3_ROTOR_IV_TRANSFERNOTCHES);
			rotors[4] = new Rotor("V", M3_ROTOR_V_WIRING,  M3_ROTOR_V_TRANSFERNOTCHES);
			rotors[5] = new Rotor("VI", M3_ROTOR_VI_WIRING,  M3_ROTOR_VI_TRANSFERNOTCHES);
			rotors[6] = new Rotor("VII", M3_ROTOR_VII_WIRING,  M3_ROTOR_VII_TRANSFERNOTCHES);
			rotors[7] = new Rotor("VIII", M3_ROTOR_VIII_WIRING,  M3_ROTOR_VIII_TRANSFERNOTCHES);
		}
		return rotors;
	}
	
	/**
	 * Builds all greek rotos for the Enigma M4 and returns them. 
	 * @return Returns built rotors.
	 */
	public static Rotor[] getGreekRotors() {
		if (greekrotors == null) {
			greekrotors = new Rotor[2];
			greekrotors[0] = new Rotor("\u03b2", M4_GREEK_ROTOR_BETA, new int[]{-1});
			greekrotors[1] = new Rotor("\u03b3", M4_GREEK_ROTOR_GAMMA, new int[]{-1});
		}
		return greekrotors;
	}
	
}
