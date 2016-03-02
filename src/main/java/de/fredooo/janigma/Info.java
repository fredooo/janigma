package de.fredooo.janigma;

/**
 * Static container of information about this program.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.4 (last revised 2016-03-02)
 */
public final class Info {
	
	public static final String NAME = "Janigma";
	public static final String DESCRIPTION = "Java Enigma Cipher Machine";
	public static final String VERSION = "0.0.4-SNAPSHOT";
	public static final String YEAR = "2016";
	public static final String AUTHOR = "Frederik Dennig";
	public static final String CONTACT = "fdennig@gmx.net";
	
    /**
     * This class is not instantiable.
     */
    private Info() { throw new IllegalStateException("Non-instantiable class!"); }

}

