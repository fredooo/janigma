package de.fredooo.janigma;

import de.fredooo.janigma.io.FileIo;
import de.fredooo.janigma.ui.console.TerminalUI;
import de.fredooo.janigma.ui.graphical.MainWindow;
import de.fredooo.janigma.ui.graphical.Theme;

/**
 * Contains the main method of the program.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.3 (last revised 2016-03-05)
 */
public final class Main {
	
	/**
	 * Starts the program.
	 * @param args not used
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			Theme.changeLookAndFeel();	
			MainWindow frame = MainWindow.instance();
			frame.setVisible(true);
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
			Info.printHelp();
		}
	}
	
	/**
     * This class is not instantiable.
     */
    private Main() { throw new IllegalStateException("Non-instantiable class!"); }

}
