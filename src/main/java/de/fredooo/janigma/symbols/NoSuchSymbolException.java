package de.fredooo.janigma.symbols;

/**
 * A special exception used if an unsupported character is used.
 * @author Frederik Dennig
 * @since 2011-06-06
 * @version 0.0.3 (last revised 2015-09-23)
 */
@SuppressWarnings("serial")
public class NoSuchSymbolException extends Exception {

	private char character;
	
	/**
	 * Construcs a new NoSuchSymbolException.
	 * @param character The causing character.
	 */
	public NoSuchSymbolException(char character) {
		super("ERROR: Inalid input: -" + character + "- !");
		this.character = character;		
	}
	
	/**
	 * Returns the character, that caused the exception.
	 * @return The causing character.
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * Shows an error message on the console, with the information
	 * which character caused an error.
	 */
	public void printMessage() {
		System.out.println();
		System.out.println("Inalid input: -" + character + "- !");
	}

}
