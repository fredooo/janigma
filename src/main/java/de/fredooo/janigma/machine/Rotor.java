package de.fredooo.janigma.machine;

/**
 * Provides the rotors of an Enigma M3 and M4 machine. 
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.2 (last edited 2016-02-20)
 */
public class Rotor {

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
	
	public static final String[] M4_ROTOR_LABELS = { "\u03b2", "\u03b3" };

	public static final int[][] M4_GREEK_ROTOR_WIRINGS = {
			{ 11, 4, 24, 9, 21, 2, 13, 8, 23, 22, 15, 1, 16, 12, 3, 17, 19, 0, 10, 25, 6, 5, 20, 7, 14, 18 },
			{ 5, 18, 14, 10, 0, 13, 20, 4, 17, 7, 12, 1, 19, 8, 24, 2, 22, 11, 16, 15, 25, 23, 21, 6, 9, 3 }
		};

	/*
	 * Class variables
	 */

	private String name;
	private int position;
	private int[] inwardsWiring;
	private int[] outwardsWiring;
	private int[] transferNotches;
	private int offset;

	/**
	 * Constructs a rotor with a given name, a given wiring and given transfer
	 * notches, initialized at position 0 with offset 0.
	 * @param name a given name
	 * @param wiring a given wiring
	 * @param transferNotches the given transfer notches
	 */
	private Rotor(String name, int[] wiring, int[] transferNotches) {
		this.name = name;
		this.position = 0;
		this.inwardsWiring = wiring;
		this.outwardsWiring = inverseWiring(wiring);
		this.transferNotches = transferNotches;
		this.offset = 0;
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
	 * direction of the rotor wiring.
	 * @param input the input character as a number
	 * @return the corresponding output.
	 */
	public int getInwardsOutput(int input) {
		return inwardsWiring[input];
	}

	/**
	 * Returns the corresponding output of a character in the outwards
	 * direction of the rotor wiring.
	 * @param input the input character as a number
	 * @return the corresponding output
	 */
	public int getOutwardsOutput(int input) {
		return outwardsWiring[input];
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

	@Override
	public String toString() {
		return name;
	}

	/**
	 * Inverses a given wiring.
	 * Example: 3, 4, 0, 1, 2 => 2, 3, 4, 0, 1
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
	 * Builds all 8 normal rotors for the Enigma M3 machine.
	 * @return the built rotors
	 */
	public static Rotor[] createNormalRotors() {
		Rotor[] rotors = new Rotor[M3_ROTOR_LABELS.length];
		for (int i = 0; i < rotors.length ; i++) {
			rotors[i] = new Rotor(M3_ROTOR_LABELS[i], M3_ROTOR_WIRINGS[i],  M3_ROTOR_TRANSFERNOTCHES[i]);
		}
		return rotors;
	}

	/**
	 * Builds beta and gamma rotor for the Enigma M4 machine.
	 * @return the greek rotors
	 */
	public static Rotor[] createGreekRotors() {
		Rotor[]  greekRotors = new Rotor[M4_ROTOR_LABELS.length];
		for (int i = 0; i < greekRotors.length; i++) {
			// Greek rotors are not moved by their neighbor, hence transfer notch set to -1
			greekRotors[i] = new Rotor(M4_ROTOR_LABELS[i], M4_GREEK_ROTOR_WIRINGS[i], new int[]{ -1 });
		}
		return greekRotors;
	}

}
