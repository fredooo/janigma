package de.fredooo.janigma;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.ui.console.TerminalUI;
import de.fredooo.janigma.ui.graphical.MainWindow;
import de.fredooo.janigma.ui.graphical.Theme;

/**
 * Contains the main method of the program.
 * @author Frederik Dennig
 * @since 2011-06-01
 * @version 0.0.2 (last revised 2016-02-20)
 */
public final class Main {
	
	/**
	 * Starts the program.
	 * @param args not used
	 */
	public static void main(String[] args) throws NoSuchSymbolException {
		if (args.length == 0) {
			Theme.changeLookAndFeel();	
			MainWindow frame = MainWindow.instance();
			frame.setVisible(true);
		}
		else if (args[0].equals("-g")) {
			TerminalUI terminal = new TerminalUI();
			terminal.run();
		}
	}
	
	/**
     * This class is not instantiable.
     */
    private Main() { throw new IllegalStateException("Non-instantiable class!"); }

}
