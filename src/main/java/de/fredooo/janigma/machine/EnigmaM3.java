package de.fredooo.janigma.machine;

/**
 * Implements an Enigma M3.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2015-09-23)
 */
public class EnigmaM3 extends Enigma {
	
	/*
	 * Class defines a singleton.
	 */
	
	private static EnigmaM3 enigma;

	/*
	 * Class variables
	 */
	
	private Reflector[] m3reflectors;
	private Reflector reflector;
	
	/**
	 * Constructs an Enigma M3 machine with all available rotors,
	 * reflectors, an empty plugboard and rotor I assigned to the
	 * left position, rotor II assigned to the middle position and
	 * rotor III assigned to the right position. The default
	 * reflector is reflector B.
	 * @param m3rotors All available rotors for this machine type.
	 * @param m3reflectors All available rotors for this machine type.
	 */
	public EnigmaM3(Rotor[] m3rotors, Reflector[] m3reflectors) {
		super(m3rotors);
		this.m3reflectors = m3reflectors;
		reflector = m3reflectors[1];
	}
	
	public Rotor[] getM3Rotors() {
		return m3Rotors;
	}
	
	/**
	 * Returns all reflectors of this Enigma M3 machine.
	 * @return Returns all reflectors off this machine.
	 */
	public Reflector[] getM3Reflectors() {
		return m3reflectors;
	}
	
	/**
	 * Returns the reflector of this Enigma machine.
	 * @return Returns the right reflector. 
	 */
	public Reflector getReflector() {
		return reflector;
	}
	
	/**
	 * Sets the reflector of the this Enigma machine to a given one.
	 * @param reflector A given reflector.
	 */
	public void setReflector(Reflector reflector) {
		this.reflector = reflector;
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
		
		// The middle rotor increments its position on its own transfer notch, too.
		
		if (doubleStep) {
			middleRotor.incrementPosition();
			doubleStep = false;
		}
		
		if(middleRotor.getPosition() != r2old) {
	    	for (int i = 0; i < middleRotor.getTransferNotches().length; i++) {
	    		if (middleRotor.getPosition() == middleRotor.getTransferNotches()[i]) {
	    			doubleStep = true;
	    			break;
	    		}
	    	}
	    	for (int i = 0; i < middleRotor.getTransferNotches().length; i++) {
	    		if (middleRotor.getPosition() == middleRotor.getTransferNotches()[i] + 1) {
	    			leftRotor.incrementPosition();
	    			break;
	    		}
	    	}
	    }
	    				
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
	
	/**
	 * Returns the EnigmaM3 singleton.
	 * @return Returns the single instance of EnigmaM3.
	 */
	public static EnigmaM3 getEnigmaM3() {
		if (enigma == null) {
			enigma = new EnigmaM3(Rotor.getRotors(), Reflector.getReflectors());
		}
		return enigma;
	}
	
}
