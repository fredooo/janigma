package de.fredooo.janigma.machine;

/**
 * Implements a reflector of the Enigma machine.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2015-09-23)
 */
public class Reflector {
	
	/*
	 * Enigma M3 reflector information.
	 */
	
	private static final int[] M3_REFLECTOR_A = {4,9,12,25,0,11,24,23,21,1,22,5,2,17,16,20,14,13,19,18,15,8,10,7,6,3};
	private static final int[] M3_REFLECTOR_B = {24,17,20,7,16,18,11,3,15,23,13,6,14,10,12,8,4,1,5,25,2,22,21,9,0,19};
	private static final int[] M3_REFLECTOR_C = {5,21,15,9,8,0,14,24,4,3,17,25,23,22,6,2,19,10,20,16,18,1,13,12,7,11};
	
	/*
	 * Enigma M4 thin reflector informations.
	 */
	
	private static final int[] M4_REFLECTOR_B = {4,13,10,16,0,20,24,22,9,8,2,14,15,1,11,12,3,23,25,21,5,19,7,17,6,18};
	private static final int[] M4_REFLECTOR_C = {17,3,14,1,9,13,19,10,21,4,7,12,11,5,2,22,25,0,23,6,24,8,15,18,20,16};
	
	/*
	 * Class defines 3 monolithic Reflector objects.
	 */
	
	private static Reflector[] reflectors;
	
	/*
	 * Class defines 2 monolithic ThinReflector objects.
	 */
	
	private static Reflector[] thinreflectors;
	
	/*
	 * Instance variables.
	 */

	private String name;
	private int[] wiring;
	
	/**
	 * Constructs a reflector from a given wiring.
	 * @param wiring The given wiring.
	 */
	protected Reflector(String name, int[] wiring) {
		this.name = name;
		this.wiring = wiring;
	}
	
	/**
	 * This method returns the corresponding character of an
	 * input character.
	 * @param input A character as an int.
	 * @return Returns the corresponding character of an input
	 * character, as an int.
	 */
	public int getOutput(int input) {
		return wiring[input]; 
	}
	
	
	/**
	 * Overrides java.lang.Object.toString() and returns
	 * the name of the reflector.
	 * @return Returns the name of the reflector.
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Builds all reflectors for the Enigma M3 and returns them. 
	 * @return Returns built reflectors.
	 */
	public static Reflector[] getReflectors() {
		if (reflectors == null) {
			reflectors = new Reflector[3];
			reflectors[0] = new Reflector("A", M3_REFLECTOR_A);
			reflectors[1] = new Reflector("B", M3_REFLECTOR_B);
			reflectors[2] = new Reflector("C", M3_REFLECTOR_C);
		}
		return reflectors;
	}
	
	/**
	 * Builds all thin reflectors for the Enigma M4 and returns them.
	 * @return Returns built thin reflectors.
	 */
	public static Reflector[] getThinReflectors() {
		if (thinreflectors == null) {
			thinreflectors = new Reflector[2];
			thinreflectors[0] = new Reflector("B", M4_REFLECTOR_B);
			thinreflectors[1] = new Reflector("C", M4_REFLECTOR_C);
		}
		return thinreflectors;
	}
	
}
