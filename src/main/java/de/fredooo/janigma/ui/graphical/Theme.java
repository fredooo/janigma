package de.fredooo.janigma.ui.graphical;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Contains theme related methods.
 * @author Frederik Dennig
 * @since 2016-02-19
 * @version 0.0.1 (last revised 2016-02-20)
 */
public class Theme {
	
	/**
	 * Changes the theme to the "Nimbus" themes.
	 */
	public static void changeLookAndFeel() {
		// Use the Nimbus L&F
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (info.getName().equals("Nimbus")) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			System.out.println("Couldn't set look and feel!");
		}
	}

}
