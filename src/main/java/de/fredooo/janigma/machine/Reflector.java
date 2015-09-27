package de.fredooo.janigma.machine;

/**
 * Provides the reflectors of the Enigma M3 and M4 machine.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.2.1 (last revised 2015-09-26)
 */
public class Reflector {
	
	/*
	 * Enigma M3 reflector information
	 */
	
	public static final String[] M3_REFLECTOR_LABELS = { "A", "B", "C" };
	
	public static final int[][] M3_REFLECTOR_WIRINGS = {
			{ 4, 9, 12, 25, 0, 11, 24, 23, 21, 1, 22, 5, 2, 17, 16, 20, 14, 13, 19, 18, 15, 8, 10, 7, 6, 3 },
			{ 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 },
			{ 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 }
		};
	
	/*
	 * Enigma M4 thin reflector information
	 */
	
	public static final String[] M4_THIN_REFLECTOR_LABELS = { "B", "C" };
	
	public static final int[][] M4_THIN_REFLECTOR_WIRINGS = {
			{ 4, 13, 10, 16, 0, 20, 24, 22, 9, 8, 2, 14, 15, 1, 11, 12, 3, 23, 25, 21, 5, 19, 7, 17, 6, 18 },
			{ 17, 3, 14, 1, 9, 13, 19, 10, 21, 4, 7, 12, 11, 5, 2, 22, 25, 0, 23, 6, 24, 8, 15, 18, 20, 16 }
		};
	
	/*
	 * Class variables
	 */

	private String name;
	private int[] wiring;
	
	/**
	 * Constructs a reflector from a given wiring.
	 * @param wiring a given wiring
	 */
	protected Reflector(String name, int[] wiring) {
		this.name = name;
		this.wiring = wiring;
	}
	
	/**
	 * This method returns the corresponding character of an input character.
	 * @param input a character as a number
	 * @return the corresponding character as a number
	 */
	public int getOutput(int input) {
		return wiring[input]; 
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Builds all reflectors for the Enigma M3 machine.
	 * @return the built reflectors
	 */
	public static Reflector[] createNormalReflectors() {
		Reflector[] reflectors = new Reflector[M3_REFLECTOR_LABELS.length];
		for (int i = 0; i < reflectors.length; i++) {
			reflectors[i] = new Reflector(M3_REFLECTOR_LABELS[i], M3_REFLECTOR_WIRINGS[i]);
		}
		return reflectors;
	}
	
	/**
	 * Builds all thin reflectors for the Enigma M4 machine.
	 * @return the built thin reflectors
	 */
	public static Reflector[] createThinReflectors() {
		Reflector[] thinReflectors = new Reflector[M4_THIN_REFLECTOR_LABELS.length];
		for (int i = 0; i < thinReflectors.length; i++) {
			thinReflectors[i] = new Reflector(M4_THIN_REFLECTOR_LABELS[i], M4_THIN_REFLECTOR_WIRINGS[i]);
		}
		return thinReflectors;
	}
	
}
