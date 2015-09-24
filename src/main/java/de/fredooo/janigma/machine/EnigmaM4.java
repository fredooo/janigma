package de.fredooo.janigma.machine;

/**
 * Implements an Enigma M4.
 * @author Frederik Dennig
 * @since 2011-06-04
 * @version 0.0.3 (last revised 2015-09-03)
 */
public class EnigmaM4 extends Enigma {
 
	/*
	 * Class defines a singleton.
	 */
	
	private static EnigmaM4 enigma;
	
	/*
	 * Class variables.
	 */
	
	private Rotor[] greekrotors;
	private Reflector[] thinreflectors;
	private Rotor greekrotor; // greek/leftmost rotor
	private Reflector thinreflector;
	
	/**
	 * Constructs an Enigma M4 machine with all available rotors,
	 * reflectors, an empty plugboard and rotor I assigned to the
	 * left position, rotor II assigned to the middle position,
	 * rotor III assigned to the right position and rotor β assigned
	 * to the leftmost (static greek rotor) position. The default
	 * reflector is reflector B "thin".
	 * @param m3rotors All available normal rotors.
	 * @param greekrotors All available greek rotors.
	 * @param thinreflectors All available thin reflectros.
	 */
	private EnigmaM4(Rotor[] m3rotors, Rotor[] greekrotors, Reflector[] thinreflectors) {
		super(m3rotors);
		this.greekrotors = greekrotors;
		this.thinreflectors = thinreflectors;
		greekrotor = greekrotors[0];
		thinreflector = thinreflectors[0];
	}
	
	/**
	 * Returns all greek rotors of this Enigma M4 machine.
	 * @return Returns all greek rotors of this machine.
	 */
	public Rotor[] getGreekRotors() {
		return greekrotors;
	}

	/**
	 * Returns all thin reflectors of this Enigma M4 machine.
	 * @return Returns all reflectors off this machine.
	 */
	public Reflector[] getThinReflectors() {
		return thinreflectors;
	}
	
	/**
	 * Returns the greek/leftmost rotor of this Enigma machine.
	 * @return Returns the greek/leftmost Rotor. 
	 */
	public Rotor getGreekRotor() {
		return greekrotor;
	}
	
	/**
	 * Sets the greek/leftmost rotor of the this Enigma machine to a given one.
	 * @param rotor A given greek rotor.
	 */
	public void setGreekRotor(Rotor rotor) {
		greekrotor = rotor;
	}
	
	/**
	 * Returns the current thin reflector of this Enigma machine.
	 * @return Returns the right reflector. 
	 */
	public Reflector getThinReflector() {
		return thinreflector;
	}

	/**
	 * Sets the current thin reflector of the this Enigma machine to a given one.
	 * @param thinreflector A given thin reflector.
	 */
	public void setThinReflector(Reflector thinreflector) {
		this.thinreflector = thinreflector;
	}

	@Override
	public int use(int input) {
		
		// If the machine is used, the rotor position increments.
		
		int r2old = middleRotor.getPosition();
		
		for (int i = 0; i < rightRotor.getTransferNotches().length; i++) {
			if (rightRotor.getPosition() == rightRotor.getTransferNotches()[i]) {
				middleRotor.incrementPosition();
				break;
			}
		}
		rightRotor.incrementPosition();
		
		if (middleRotor.getPosition() != r2old) {
			for (int i = 0; i < middleRotor.getTransferNotches().length; i++) {
				if (middleRotor.getPosition() == middleRotor.getTransferNotches()[i] + 1) {
					leftRotor.incrementPosition();
					break;
				}
			}
		}
		
		// The greek/leftmost rotor is static and doesn't need to be rotated.
				
		// Following circuitry to get the ciphered input, as output.
		
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
				+ greekrotor.getPosition() - greekrotor.getOffset());
		input = greekrotor.getInwardsOutput(input);
		input = carryOver(input - greekrotor.getPosition() + greekrotor.getOffset());
		
		input = thinreflector.getOutput(input);
		
		input = carryOver(input + greekrotor.getPosition() - greekrotor.getOffset());
		input = greekrotor.getOutwardsOutput(input);
		input = carryOver(input + leftRotor.getPosition() - leftRotor.getOffset()
				- greekrotor.getPosition() + greekrotor.getOffset());
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

	/**
	 * Returns the EnigmaM4 singleton.
	 * @return Returns the single instance of EnigmaM4.
	 */
	public static EnigmaM4 getEnigmaM4() {
		if (enigma == null) {
			enigma = new EnigmaM4(Rotor.getRotors(), Rotor.getGreekRotors(), Reflector.getThinReflectors());
		}
		return enigma;
	}
	
}
