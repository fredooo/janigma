package de.fredooo.janigma;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.ui.graphical.MainWindow;

/**
 * 
 * @author Frederik Dennig
 * @since 2011-06-01
 */
public final class Main {
	
	public static void main(String[] args) throws NoSuchSymbolException {
				
		// Nimbus L&F
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	//System.out.println(info.getName());
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			System.err.println("Could not set L&F!");
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*
		UI.run();
		
		MainWindow.changeLookAndFeel();
		MainWindow w = new MainWindow();
		
		try {
			
			if (args[0].equals("--gui") || args[0].equals("-g")) {
				Data.title();
				ConsoleUI.start();
				
			} else if (args[0].equals("--file") || args[0].equals("-f")) {
				Data.title();
				EnigmaM3 enigma = new EnigmaM3(Data.buildM3Rotors(), Data.buildM3Reflectors());
				ConsoleUI.quickSetup(enigma);
				ConsoleUI.useFile(enigma);
				
			} else {
				Data.showHelp();
			}
			
		} catch (ArrayIndexOutOfBoundsException ex) {
			Data.showHelp();
		}
		ConsoleUI.start();
		*/
	}

}
