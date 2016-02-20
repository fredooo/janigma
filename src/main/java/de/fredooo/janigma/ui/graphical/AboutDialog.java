package de.fredooo.janigma.ui.graphical;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import de.fredooo.janigma.Info;

/**
 * Provides the "About" dialog.
 * @author Frederik Dennig
 * @since 2013-12-14
 * @version 0.0.1 (last revised 2015-02-20)
 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog implements ActionListener {

	/**
	 * Creates the "About" dialog.
	 */
	public AboutDialog() {
		super(MainWindow.instance());
		this.setTitle("About");
		this.setModal(true);
		this.setResizable(false);
		this.setLocation(MainWindow.instance().getLocation());
		
		JLabel name = new JLabel(Info.NAME);
		name.setFont(new Font("Tahoma", Font.BOLD, 32));
		name.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel description = new JLabel(Info.DESCRIPTION);
		description.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel version = new JLabel("Version: " + Info.VERSION);
		version.setAlignmentX(CENTER_ALIGNMENT);

		JLabel author = new JLabel("Author: " + Info.AUTHOR);
		author.setAlignmentX(CENTER_ALIGNMENT);
			
		JLabel contact = new JLabel("Contact: " + Info.CONTACT);
		contact.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setContentPane(panel);
		
		panel.add(name);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(description);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(new JSeparator(JSeparator.HORIZONTAL));
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(version);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(author);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(contact);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(btnClose);
		
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();		
	}

}
