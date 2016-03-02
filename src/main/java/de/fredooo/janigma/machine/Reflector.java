package de.fredooo.janigma.machine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Provides the reflectors of the Enigma M3 and M4 machine.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2016-03-02)
 */
public class Reflector {
	
	/*
	 * All available reflector types
	 */
	
	public static final int M3_A = 0;
	public static final int M3_B = 1;
	public static final int M3_C = 2;
	
	public static final int M4_THIN_B = 3;
	public static final int M4_THIN_C = 4;
	
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

	@JsonProperty("type")
	private final int type;
	
	@JsonIgnore
	private final String name;
	
	@JsonIgnore
	private final int[] wiring;
	
	/**
	 * Constructs a reflector with a given type, name and wiring.
	 * @param type the type of the reflector
	 * @param wiring a given wiring
	 */
	private Reflector(int type, String name, int[] wiring) {
		this.type = type;
		this.name = name;
		this.wiring = wiring;
	}
	
	/**
	 * Returns the type of the reflector.
	 * @return the type
	 */
	public int type() {
		return type;
	}
	
	
	/**
	 * Returns the name of the reflector. 
	 * @return the name
	 */
	public String name() {
		return name;
	}
	
	/**
	 * This method returns the corresponding character of an input character.
	 * @param input a character as a number
	 * @return the corresponding character as a number
	 */
	public int outputOf(int input) {
		return wiring[input]; 
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Creates a reflector of a given type.
	 * @param type the type of the reflector to create
	 * @return a new reflector of the given ID
	 */
	@JsonCreator
	public static Reflector createReflector(@JsonProperty("type") int type) {
		if (type < M3_A || type > M4_THIN_C) {
			throw new IllegalArgumentException("No such reflector!");
		}
		if (type < M4_THIN_B) {
			return new Reflector(type, M3_REFLECTOR_LABELS[type], M3_REFLECTOR_WIRINGS[type]);
		} else {
			return new Reflector(type, M4_THIN_REFLECTOR_LABELS[type - M4_THIN_B], M4_THIN_REFLECTOR_WIRINGS[type - M4_THIN_B]);
		}
	}
	
}
