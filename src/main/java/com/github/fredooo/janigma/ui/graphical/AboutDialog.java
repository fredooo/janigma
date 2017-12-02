package com.github.fredooo.janigma.ui.graphical;

import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.github.fredooo.janigma.Constants;

/**
 * Provides the "About" dialog.
 * @author Frederik Dennig
 * @since 2013-12-14
 * @version 0.0.2 (last revised 2017-12-01)
 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	/**
	 * Creates the "About" dialog.
	 */
	private AboutDialog() {
		super(MainWindow.instance(), "About", true);
		this.setResizable(false);
		this.setContentPane(createMainPanel());
		this.pack();
		this.setLocation(MainWindow.instance().getLocation());
	}

	private Box createMainPanel() {
		Box box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(10, 10, 10, 10));
		box.add(createTitleLabel());
		box.add(Box.createVerticalStrut(10));
		addRegularLabel(box, Constants.DESCRIPTION);
		box.add(Box.createVerticalStrut(10));
		box.add(new JSeparator(JSeparator.HORIZONTAL));
		box.add(Box.createVerticalStrut(10));
		addRegularLabel(box, "Version: " + Constants.VERSION);
		box.add(Box.createVerticalStrut(10));
		addRegularLabel(box, "Author: " + Constants.AUTHOR);
		box.add(Box.createVerticalStrut(10));
		addRegularLabel(box, "Contact: " + Constants.CONTACT);
		box.add(Box.createVerticalStrut(10));
		box.add(createCloseButton());
		return box;
	}

	private JLabel createTitleLabel() {
		JLabel label = new JLabel(Constants.NAME);
		label.setAlignmentX(Box.CENTER_ALIGNMENT);
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
		return label;
	}

	private JButton createCloseButton() {
		JButton closeButton = new JButton("Close");
		closeButton.setAlignmentX(Box.CENTER_ALIGNMENT);
		closeButton.addActionListener(e -> SwingUtilities.invokeLater(this::dispose));
		return closeButton;
	}

	private static void addRegularLabel(Box box, String text) {
		JLabel label = new JLabel(text);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		box.add(label);
	}

	public static void showDialog() {
		SwingUtilities.invokeLater(() -> new AboutDialog().setVisible(true));
	}

}
