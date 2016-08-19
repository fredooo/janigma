package de.fredooo.janigma.machine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Provides the rotors of an Enigma M3 and M4 machine. 
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last edited 2016-03-02)
 */
public class Rotor {
	
	/*
	 * All available rotor types
	 */

	public static final int M3_I = 0;
	public static final int M3_II = 1;
	public static final int M3_III = 2;
	public static final int M3_IV = 3;
	public static final int M3_V = 4;
	public static final int M3_VI = 5;
	public static final int M3_VII = 6;
	public static final int M3_VIII = 7;

	public static final int M4_GREEK_BETA = 8;
	public static final int M4_GREEK_GAMMA = 9;
	
	/*
	 * Enigma M3 rotor information
	 */
	
	public static final String[] M3_ROTOR_LABELS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII" };
	
	public static final int[][] M3_ROTOR_WIRINGS = {
			{ 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 },
			{ 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 },
			{ 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 },
			{ 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 },
			{ 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 },
			{ 9, 15, 6, 21, 14, 20, 12, 5, 24, 16, 1, 4, 13, 7, 25, 17, 3, 10, 0, 18, 23, 11, 8, 2, 19, 22 },
			{ 13, 25, 9, 7, 6, 17, 2, 23, 12, 24, 18, 22, 1, 14, 20, 5, 0, 8, 21, 11, 15, 4, 10, 16, 3, 19 },
			{ 5, 10, 16, 7, 19, 11, 23, 14, 2, 1, 9, 18, 15, 3, 25, 17, 0, 12, 4, 22, 13, 8, 20, 24, 6, 21 }
		};
	
	public static final int[][] M3_ROTOR_TRANSFERNOTCHES = {
			{ 16 }, { 4 }, { 21 }, { 9 }, { 25 }, { 25, 12 }, { 25, 12 }, { 25, 12 }
		};

	/*
	 * Enigma M4 greek rotor information
	 */
	
	public static final String[] M4_GREEK_ROTOR_LABELS = { "\u03b2", "\u03b3" };

	public static final int[][] M4_GREEK_ROTOR_WIRINGS = {
			{ 11, 4, 24, 9, 21, 2, 13, 8, 23, 22, 15, 1, 16, 12, 3, 17, 19, 0, 10, 25, 6, 5, 20, 7, 14, 18 },
			{ 5, 18, 14, 10, 0, 13, 20, 4, 17, 7, 12, 1, 19, 8, 24, 2, 22, 11, 16, 15, 25, 23, 21, 6, 9, 3 }
		};

	/*
	 * Class variables
	 */
	
	@JsonProperty("type")
	private final int type;

	@JsonIgnore
	private final String name;
	
	@JsonProperty("position")
	private int position;
	
	@JsonIgnore
	private final int[] inwardsWiring;
	
	@JsonIgnore
	private final int[] outwardsWiring;
	
	@JsonIgnore
	private final int[] transferNotches;
	
	@JsonProperty("offset")
	private int offset;

	/**
	 * Constructs a rotor with a given type, name, wiring and transfer
	 * notches, initialized at position 0 with offset 0.
	 * @param type the type of the rotor
	 * @param name a given name
	 * @param wiring a given wiring
	 * @param transferNotches the given transfer notches
	 */
	private Rotor(int type, String name, int[] wiring, int[] transferNotches) {
		this.type = type;
		this.name = name;
		this.position = 0;
		this.inwardsWiring = wiring;
		this.outwardsWiring = inverseWiring(wiring);
		this.transferNotches = transferNotches;
		this.offset = 0;
	}

	/**
	 * Returns the type of the rotor.
	 * @return the type
	 */
	public int type() {
		return type;
	}
	
	/**
	 * Returns the name of the rotor
	 * @return the name
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Returns the current position of a rotor.
	 * @return the position of the rotor
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Changes the position of rotor.
	 * @param position the new position
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
	 * Returns the corresponding output of a character in the inwards
	 * direction of the rotor.
	 * @param input the input character as a number
	 * @return the corresponding output.
	 */
	public int inwardsOutputOf(int input) {
		input = carryOver(input + this.getPosition() - this.getOffset());
		input = this.inwardsWiring[input];
		return carryOver(input - this.getPosition() + this.getOffset());
	}

	/**
	 * Returns the corresponding output of a character in the outwards
	 * direction of the rotor.
	 * @param input the input character as a number
	 * @return the corresponding output
	 */
	public int outwardsOutputOf(int input) {
		input = carryOver(input + this.getPosition() - this.getOffset());
		input = this.outwardsWiring[input];
		return carryOver(input - this.getPosition() + this.getOffset());
	}

	/**
	 * Returns the position of the transfer notches of the rotor.
	 * @return the transfer notch position
	 */
	public int[] getTransferNotches() {
		return transferNotches;
	}

	/**
	 * Returns the current offset of a rotor.
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset of a rotor to a given value.
	 * @param offset the offset to set the rotor to
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * This method handles the carry over operations of the calculations in {@link #use}.
	 * @param number the number from which to remove the carry over
	 * @return a number between 0 and 25
	 */
	private static int carryOver(int number) {
		if (number < 0) {
			while (number < 0) {
				number += 26;
			}
			return number;
		} else {
			return number % 26;
		}
	}

	/**
	 * Inverses a given wiring.
	 * Example: 3, 4, 0, 1, 2 =&gt; 2, 3, 4, 0, 1
	 * @param wiring the given wiring
	 * @return the inverted wiring
	 */
	public static int[] inverseWiring(final int[] wiring) {
		int[] invertedWiring =  new int[wiring.length];
		for (int i = 0; i < wiring.length; i++) {
			for (int j = 0; j < wiring.length; j++) {
				if (wiring[j] == i) {
					invertedWiring[i] = j;
				}
			}
		}
		return invertedWiring;
	}
	
	/**
	 * Creates a rotor of a given type.
	 * @param type the type of the rotor to create
	 * @return a new rotor of the given type
	 */
	public static Rotor createRotor(int type) {
		if (type < M3_I || type > M4_GREEK_GAMMA) {
			throw new IllegalArgumentException("No such rotor!");
		}
		if (type < M4_GREEK_BETA) {
			return new Rotor(type, M3_ROTOR_LABELS[type], M3_ROTOR_WIRINGS[type], M3_ROTOR_TRANSFERNOTCHES[type]);
		} else {
			return new Rotor(type, M4_GREEK_ROTOR_LABELS[type - M4_GREEK_BETA], M4_GREEK_ROTOR_WIRINGS[type - M4_GREEK_BETA], new int[]{ -1 });
		}
	}
	
	/**
	 * Creates a rotor with a given type, position and offset.
	 * @param type the type of the rotor to create
	 * @param postion the position of the rotor
	 * @param offset the offset of the rotor
	 * @return the new rotor with the given configuration
	 */
	@JsonCreator
	public static Rotor createRotor(
			@JsonProperty("type") int type,
			@JsonProperty("position") int postion,
			@JsonProperty("offset") int offset) {
		Rotor rotor = createRotor(type);
		rotor.position = postion;
		rotor.offset = offset;
		return rotor;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) { return false; }
	    if (other == this) { return true; }
	    if (!(other instanceof Rotor)) { return false; }
	    final Rotor otherRotor = (Rotor) other;
	    if (otherRotor.type != this.type) { return false; }
	    if (!otherRotor.name.equals(this.name)) { return false; }
	    if (otherRotor.position != this.position) { return false; }
	    if (otherRotor.offset != this.offset) { return false; }
	    // The inwardsWiring, outwardsWiring and transferNotches fields
	    // are not tested, type and name are enough
	    return true;
	}

}
