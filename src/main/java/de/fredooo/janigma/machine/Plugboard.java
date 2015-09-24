package de.fredooo.janigma.machine;

/**
 * Implements the plugboard of an Enigma machine.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2015-09-21)
 */
public class Plugboard {

	private int[] plugs;
	
	/**
	 * Constructs a plugboard without any cabels attached.
	 */
	public Plugboard() {
		plugs = new int[26];
		removeAllCabels();
	}
	
	/**
	 * Removes all cabels from a plugboard.
	 */
	public void removeAllCabels() {
		for (int i = 0; i < 26; i++) {
			plugs[i] = i;
		}
	}
	
	/**
	 * Adds a cabel to the plugboard.
	 * @param firstsymbol The character you want to swap with the
	 * second character. 
	 * @param secondsymbol The character you want to swap with the
	 * first character.
	 */
	public void addCabel(int firstsymbol, int secondsymbol) {
		plugs[firstsymbol] = secondsymbol;
		plugs[secondsymbol] = firstsymbol;
	}
	
	/**
	 * Removes the cabel from a character. The corresponding character
	 * will also be unplugged. 
	 * @param symbol The character you want to unplug.
	 */
	public void removeCabel(int symbol) {
		int othersymbol = plugs[symbol];
		plugs[othersymbol] = othersymbol;
		plugs[symbol] = symbol;
	}
	
	/**
	 * Checks if a Character is swapped with another one.
	 * @param symbol The character you want to check.
	 * @return Returns true if the character is swapped with another,
	 * otherwise false.
	 */
	public boolean isPlugged(int symbol) {
		return (plugs[symbol] != symbol);
	}
	
	/**
	 * Looks for the corresponding character of a character.
	 * @param symbol The character from which you want the
	 * corresponding character.
	 * @return Returns the corresponding character as an int value.
	 */
	public int swappedWith(int symbol) {
		return plugs[symbol];
	}
	
}
