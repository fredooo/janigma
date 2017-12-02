package com.github.fredooo.janigma.core.symbols;

/**
 * A special exception thrown, if an unsupported symbol is used.
 * @author Frederik Dennig
 * @since 2011-06-06
 * @version 0.0.3 (last revised 2017-12-01)
 */
@SuppressWarnings("serial")
public class NoSuchSymbolException extends Exception {

	private final char symbol;
	
	/**
	 * Constructs a new NoSuchSymbolException.
	 * @param symbol the causing symbol
	 */
	public NoSuchSymbolException(char symbol) {
		super("Invalid input: -" + symbol + "- !");
		this.symbol = symbol;		
	}
	
	/**
	 * Returns the symbol, that caused the exception.
	 * @return the exception causing symbol
	 */
	public char symbol() {
		return symbol;
	}

}
