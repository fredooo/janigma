package de.fredooo.janigma.machine;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

/**
 * Implements the plugboard of an Enigma machine.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2016-02-20)
 */
public class Plugboard {

	private int[] plugs;
	
	/**
	 * Constructs a plugboard without any cables attached.
	 */
	public Plugboard() {
		this.plugs = new int[26];
		removeAllCables();
	}
	
	/**
	 * Removes all cables from a plugboard.
	 */
	public void removeAllCables() {
		for (int i = 0; i < 26; i++) {
			plugs[i] = i;
		}
	}
	
	/**
	 * Adds a cable to the plugboard.
	 * @param firstSymbol the character to swap with the
	 * second character as an integer
	 * @param secondSymbol the character to swap with the
	 * first character as an integer
	 */
	public void addCable(int firstSymbol, int secondSymbol) {
		plugs[firstSymbol] = secondSymbol;
		plugs[secondSymbol] = firstSymbol;
	}
	
	/**
	 * Adds a cable to the plugboard.
	 * @param firstSymbol the character to swap with the
	 * second character as a character
	 * @param secondSymbol the character to swap with the
	 * first character as a character
	 */
	public void addCable(char firstSymbol, char secondSymbol) throws NoSuchSymbolException {
		addCable(Original.toInt(firstSymbol), Original.toInt(secondSymbol));
	}
	
	/**
	 * Removes the cable from a character. The corresponding character
	 * will also be unplugged. 
	 * @param symbol the character to unplug
	 */
	public void removeCable(int symbol) {
		int othersymbol = plugs[symbol];
		plugs[othersymbol] = othersymbol;
		plugs[symbol] = symbol;
	}
	
	/**
	 * Checks if a Character is swapped with another one.
	 * @param symbol the character to check
	 * @return true, if the character is swapped with another,
	 * otherwise false
	 */
	public boolean isPlugged(int symbol) {
		return (plugs[symbol] != symbol);
	}
	
	/**
	 * Looks for the corresponding character of a character.
	 * @param symbol the character from which to get the
	 * corresponding character
	 * @return the corresponding character as an integer value
	 */
	public int swappedWith(int symbol) {
		return plugs[symbol];
	}
	
}
