package de.fredooo.janigma;

/**
 * 
 * @author Frederik Dennig
 * @since 2011-06-01
 */
public final class Data {
	
	/*
	 * Compile time constants, for distinct program behavior.
	 */
	
	public static final boolean CLEAR_CONSOLE = false;
	
	/*
	 * General program information.
	 */

	public static final String NAME = "Janigma";
	public static final String VERSION = "0.0.3-SNAPSHOT";
	public static final String AUTHOR = "Frederik Dennig";
	public static final String CONTACT = "fdennig@gmx.net";
		
	/**
	 * Prints the title screen on the console.
	 */
	public static void title() {
		System.out.println("+--------------------------------------+");
		System.out.println("|                                      |");
		System.out.println("|               " + NAME + "                |");
		System.out.println("|    The Java Enigma Cipher Machine    |");
		System.out.println("|                                      |");
		System.out.println("|              " + VERSION + "              |");
		System.out.println("|                                      |");
		System.out.println("+--------------------------------------+");
		System.out.println();
	}
	
	/**
	 * Prints the help screen on the console.
	 */
	public static void showHelp() {
		System.out.println();
		System.out.println("File-Mode:\t--file or -f");
		System.out.println("Use this Mode to de- or encrypt a file.");
		System.out.println();
		System.out.println("GUI-Mode:\t--gui  or -g");
		System.out.println("This mode displays an Enigma machine on the terminal.");
		System.out.println();
		System.out.println("Everything else will show up this help page.");
	}

}

