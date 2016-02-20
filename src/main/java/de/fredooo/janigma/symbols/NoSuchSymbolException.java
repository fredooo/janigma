package de.fredooo.janigma.symbols;

/**
 * A special exception thrown, if an unsupported character is used.
 * @author Frederik Dennig
 * @since 2011-06-06
 * @version 0.0.3 (last revised 2016-02-19)
 */
@SuppressWarnings("serial")
public class NoSuchSymbolException extends Exception {

	private char character;
	
	/**
	 * Constructs a new NoSuchSymbolException.
	 * @param character the causing character
	 */
	public NoSuchSymbolException(char character) {
		super("Inalid input: -" + character + "- !");
		this.character = character;		
	}
	
	/**
	 * Returns the character, that caused the exception.
	 * @return the causing character
	 */
	public char getCharacter() {
		return character;
	}

}
