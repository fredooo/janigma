package de.fredooo.janigma.machine;

/**
 * Implements an Enigma M4.
 * @author Frederik Dennig
 * @since 2011-06-04
 * @version 0.0.3 (last revised 2016-02-19)
 */
public final class EnigmaM4 extends Enigma {
 	
	private static EnigmaM4 instance;
	
	private Rotor[] greekRotors;
	private Reflector[] thinReflectors;
	private Rotor greekRotor; // greek/leftmost rotor
	private Reflector thinReflector;
	
	/**
	 * Constructs an Enigma M4 machine with all available rotors,
	 * reflectors, an empty plugboard and rotor I assigned to the
	 * left position, rotor II assigned to the middle position,
	 * rotor III assigned to the right position and rotor β assigned
	 * to the leftmost (static greek rotor) position. The default
	 * reflector is reflector B "thin".
	 * @param m3Rotors all available normal rotors
	 * @param greekRotors all available greek rotors
	 * @param thinReflectors all available thin reflectors
	 */
	private EnigmaM4(Rotor[] m3Rotors, Rotor[] greekRotors, Reflector[] thinReflectors) {
		super(m3Rotors);
		this.greekRotors = greekRotors;
		this.thinReflectors = thinReflectors;
		this.greekRotor = greekRotors[0];
		this.thinReflector = thinReflectors[0];
	}
	
	/**
	 * Returns the EnigmaM4 singleton.
	 * @return the single instance of the EnigmaM4 class
	 */
	public static EnigmaM4 instance() {
		if (instance == null) {
			instance = new EnigmaM4(Rotor.createNormalRotors(), Rotor.createGreekRotors(), Reflector.createThinReflectors());
		}
		return instance;
	}
	
	/**
	 * Returns all greek rotors of this Enigma M4 machine.
	 * @return all greek rotors of this machine
	 */
	public Rotor[] getGreekRotors() {
		return greekRotors;
	}

	/**
	 * Returns all thin reflectors of this Enigma M4 machine.
	 * @return all reflectors off this machine
	 */
	public Reflector[] getThinReflectors() {
		return thinReflectors;
	}
	
	/**
	 * Returns the greek/leftmost rotor of this Enigma machine.
	 * @return the greek/leftmost rotor
	 */
	public Rotor getGreekRotor() {
		return greekRotor;
	}
	
	/**
	 * Sets the greek/leftmost rotor of the this Enigma machine to a given one.
	 * @param rotor a given greek rotor
	 */
	public void setGreekRotor(Rotor rotor) {
		greekRotor = rotor;
	}
	
	/**
	 * Returns the current thin reflector of this Enigma machine.
	 * @return the right reflector.
	 */
	public Reflector getThinReflector() {
		return thinReflector;
	}

	/**
	 * Sets the current thin reflector of the this Enigma machine to a given one.
	 * @param thinReflector a given thin reflector
	 */
	public void setThinReflector(Reflector thinReflector) {
		this.thinReflector = thinReflector;
	}

	@Override
	public int use(int input) {
		actuateSteppingMechanism();
		
		// Following circuitry to get the ciphered input
		input = plugboard.swappedWith(input);
		
		input = carryOver(input + rightRotor.getPosition() - rightRotor.getOffset());
		input = rightRotor.getInwardsOutput(input);
		input = carryOver(input - rightRotor.getPosition() + rightRotor.getOffset()
				+ middleRotor.getPosition() - middleRotor.getOffset());
		input = middleRotor.getInwardsOutput(input);
		input = carryOver(input - middleRotor.getPosition() + middleRotor.getOffset()
				+ leftRotor.getPosition() - leftRotor.getOffset());
		input = leftRotor.getInwardsOutput(input);
		input = carryOver(input - leftRotor.getPosition() + leftRotor.getOffset()
				+ greekRotor.getPosition() - greekRotor.getOffset());
		input = greekRotor.getInwardsOutput(input);
		input = carryOver(input - greekRotor.getPosition() + greekRotor.getOffset());
		
		input = thinReflector.getOutput(input);
		
		input = carryOver(input + greekRotor.getPosition() - greekRotor.getOffset());
		input = greekRotor.getOutwardsOutput(input);
		input = carryOver(input + leftRotor.getPosition() - leftRotor.getOffset()
				- greekRotor.getPosition() + greekRotor.getOffset());
		input = leftRotor.getOutwardsOutput(input);
		input = carryOver(input + middleRotor.getPosition() - middleRotor.getOffset()
				- leftRotor.getPosition() + leftRotor.getOffset());
		input = middleRotor.getOutwardsOutput(input);
		input = carryOver(input + rightRotor.getPosition() - rightRotor.getOffset()
				- middleRotor.getPosition() + middleRotor.getOffset());
		input = rightRotor.getOutwardsOutput(input);
		input = carryOver(input - rightRotor.getPosition() + rightRotor.getOffset());
		
		input = plugboard.swappedWith(input);
		
		return input;		
	}
	
	@Override
	public String toString() {
		return "Enigma M4";
	}
	
}
