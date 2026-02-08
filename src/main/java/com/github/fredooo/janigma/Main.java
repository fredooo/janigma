package com.github.fredooo.janigma;

import com.github.fredooo.janigma.io.FileIo;
import com.github.fredooo.janigma.ui.graphical.Theme;
import com.github.fredooo.janigma.ui.console.TerminalUI;
import com.github.fredooo.janigma.ui.graphical.MainWindow;

/**
 * Contains the main method of the program.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.6
 */
public final class Main {
	
	/**
	 * Starts the program.
	 * @param args not used
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			Theme.changeLookAndFeel();
			MainWindow.showWindow();
		}
		else if (args[0].equals("-g")) {
			TerminalUI terminal = new TerminalUI();
			terminal.run();
		}
		else if (args.length == 4 && args[0].equals("-f")) {
			String inputPath = args[1];
			String outputPath = args[2];
			String configPath = args[3];
			FileIo.encryptDecryptFile(inputPath, outputPath, configPath);
		} else {
			printHelp();
		}
	}

	/**
	 * Prints the help of this program.
	 */
	public static void printHelp() {
		String help =
				Constants.NAME + " - " + Constants.DESCRIPTION + "\n" +
						"\n" +
						"Usage: java -jar janigma.jar [OPTIONS]\n" +
						"\n" +
						"  OPTIONS\n" +
						"  -g\tOpens the console UI\n" +
						"  -f\tEncrypt or decrypt a file\n";
		System.out.println(help);
	}
	
	/**
     * This class is not instantiable.
     */
    private Main() { throw new IllegalStateException("Non-instantiable class!"); }

}
