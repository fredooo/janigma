package de.fredooo.janigma.machine;

/**
 * Implements an Enigma M3.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2016-02-19)
 */
public final class EnigmaM3 extends Enigma {
	
	private static EnigmaM3 instance;
	
	private Reflector[] m3Reflectors;
	private Reflector reflector;
	
	/**
	 * Constructs an Enigma M3 machine with all available rotors,
	 * reflectors, an empty plugboard and rotor I assigned to the
	 * left position, rotor II assigned to the middle position and
	 * rotor III assigned to the right position. The default
	 * reflector is reflector B.
	 * @param m3Rotors all available rotors for this machine type
	 * @param m3Reflectors all available rotors for this machine type
	 */
	public EnigmaM3(Rotor[] m3Rotors, Reflector[] m3Reflectors) {
		super(m3Rotors);
		this.m3Reflectors = m3Reflectors;
		this.reflector = m3Reflectors[1];
	}
	
	/**
	 * Returns the EnigmaM3 singleton.
	 * @return the single instance of the EnigmaM3 class
	 */
	public static EnigmaM3 instance() {
		if (instance == null) {
			instance = new EnigmaM3(Rotor.createNormalRotors(), Reflector.createNormalReflectors());
		}
		return instance;
	}
	
	/**
	 * Returns all reflectors of this Enigma M3 machine.
	 * @return all reflectors of this machine
	 */
	public Reflector[] getM3Reflectors() {
		return m3Reflectors;
	}
	
	/**
	 * Returns the reflector of this Enigma machine.
	 * @return the right reflector
	 */
	public Reflector getReflector() {
		return reflector;
	}
	
	/**
	 * Sets the reflector of the this Enigma machine to a given one.
	 * @param a given reflector
	 */
	public void setReflector(Reflector reflector) {
		this.reflector = reflector;
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
		input = carryOver(input - leftRotor.getPosition() + leftRotor.getOffset());
		
		input = reflector.getOutput(input);
		
		input = carryOver(input + leftRotor.getPosition() - leftRotor.getOffset());
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
		return "Enigma M3";
	}
	
}
