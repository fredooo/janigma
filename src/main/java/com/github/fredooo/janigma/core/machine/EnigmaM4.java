package com.github.fredooo.janigma.core.machine;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implements an Enigma M4.
 * @author Frederik Dennig
 * @since 2011-06-04
 * @version 0.0.6
 */
public final class EnigmaM4 extends Enigma {
	
	@JsonProperty("greekRotor")
	private Rotor greekRotor; // greek/leftmost rotor
	
	@JsonProperty("thinReflector")
	private Reflector thinReflector;
	
	/**
	 * Constructs an Enigma M4 machine with all available rotors,
	 * reflectors, an empty plugboard and rotor I assigned to the
	 * left position, rotor II assigned to the middle position,
	 * rotor III assigned to the right position and rotor β assigned
	 * to the leftmost (static greek rotor) position. The default
	 * reflector is reflector B "thin".
	 */
	public EnigmaM4() {
		this.greekRotor = Rotor.createRotor(Rotor.M4_GREEK_BETA);
		this.thinReflector = Reflector.createReflector(Reflector.M4_THIN_B);
	}
	
	/**
	 * Returns the greek/leftmost rotor of this Enigma machine.
	 * @return the greek/leftmost rotor
	 */
	public Rotor getGreekRotor() {
		return greekRotor;
	}
	
	/**
	 * Sets the greek/leftmost rotor of this Enigma machine to a given one.
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
	 * Sets the current thin reflector of this Enigma machine to a given one.
	 * @param thinReflector a given thin reflector
	 */
	public void setThinReflector(Reflector thinReflector) {
		this.thinReflector = thinReflector;
	}

	@Override
	public int use(int input) {
		actuateSteppingMechanism();
		input = plugboard.swappedWith(input);
		input = rightRotor.inwardsOutputOf(input);
		input = middleRotor.inwardsOutputOf(input);
		input = leftRotor.inwardsOutputOf(input);
		input = greekRotor.inwardsOutputOf(input);
		input = thinReflector.outputOf(input);
		input = greekRotor.outwardsOutputOf(input);
		input = leftRotor.outwardsOutputOf(input);
		input = middleRotor.outwardsOutputOf(input);
		input = rightRotor.outwardsOutputOf(input);
		input = plugboard.swappedWith(input);
		return input;
	}
	
	@Override
	public String toString() {
		return "Enigma M4";
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) { return false; }
	    if (other == this) { return true; }
	    if (!(other instanceof EnigmaM4)) { return false; }
	    final EnigmaM4 otherEnigmaM4 = (EnigmaM4) other;
	    if (!otherEnigmaM4.greekRotor.equals(this.greekRotor)) { return false; }
	    if (!otherEnigmaM4.thinReflector.equals(this.thinReflector)) { return false; }
	    if (!otherEnigmaM4.leftRotor.equals(this.leftRotor)) { return false; }
	    if (!otherEnigmaM4.middleRotor.equals(this.middleRotor)) { return false; }
	    if (!otherEnigmaM4.rightRotor.equals(this.rightRotor)) { return false; }
	    if (!otherEnigmaM4.plugboard.equals(this.plugboard)) { return false; }
	    return true;
	}
	
}
