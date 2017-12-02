package com.github.fredooo.janigma.ui.graphical;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Contains theme related methods.
 * @author Frederik Dennig
 * @since 2016-02-19
 * @version 0.0.2 (last revised 2017-12-01)
 */
public class Theme {
	
	/**
	 * Adapt theme to the used system.
	 */
	public static void changeLookAndFeel() {
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) { System.out.println("Couldn't set look and feel! " + e.getMessage()); }
	}

	/**
	 * This class is not instantiable.
	 */
	private Theme() { throw new IllegalStateException("Non-instantiable class!"); }

}
