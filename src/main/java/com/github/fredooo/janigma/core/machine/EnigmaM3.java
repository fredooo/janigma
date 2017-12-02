package com.github.fredooo.janigma.core.machine;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implements an Enigma M3.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.4 (last revised 2016-03-02)
 */
public final class EnigmaM3 extends Enigma {
	
	@JsonProperty("reflector")
	private Reflector reflector;
	
	/**
	 * Constructs an Enigma M3 machine with all available rotors,
	 * reflectors, an empty plugboard and rotor I assigned to the
	 * left position, rotor II assigned to the middle position and
	 * rotor III assigned to the right position. The default
	 * reflector is reflector B.
	 */
	public EnigmaM3() {
		this.reflector = Reflector.createReflector(Reflector.M3_B);
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
	 * @param reflector a given reflector
	 */
	public void setReflector(Reflector reflector) {
		this.reflector = reflector;
	}
	
	@Override
	public int use(int input) {
		actuateSteppingMechanism();
		// Following circuitry to get the ciphered input
		input = plugboard.swappedWith(input);
		input = rightRotor.inwardsOutputOf(input);
		input = middleRotor.inwardsOutputOf(input);
		input = leftRotor.inwardsOutputOf(input);
		input = reflector.outputOf(input);
		input = leftRotor.outwardsOutputOf(input);
		input = middleRotor.outwardsOutputOf(input);
		input = rightRotor.outwardsOutputOf(input);
		input = plugboard.swappedWith(input);
		return input;
	}
	
	@Override
	public String toString() {
		return "Enigma M3";
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) { return false; }
	    if (other == this) { return true; }
	    if (!(other instanceof EnigmaM3)) { return false; }
	    final EnigmaM3 otherEnigmaM3 = (EnigmaM3) other;
	    if (!otherEnigmaM3.reflector.equals(this.reflector)) { return false; }
	    if (!otherEnigmaM3.leftRotor.equals(this.leftRotor)) { return false; }
	    if (!otherEnigmaM3.middleRotor.equals(this.middleRotor)) { return false; }
	    if (!otherEnigmaM3.rightRotor.equals(this.rightRotor)) { return false; }
	    if (!otherEnigmaM3.plugboard.equals(this.plugboard)) { return false; }
	    return true;
	}
	
}
