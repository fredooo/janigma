package de.fredooo.janigma.ui.graphical;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.fredooo.janigma.Data;

/**
 * 
 * @author Frederik Dennig
 * @since 2013-12-14
 * @version 0.1a (last revised 2013-12-15)
 */
@SuppressWarnings("serial")
public class AboutWindow extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Creates the "About" window.
	 */
	public AboutWindow() {
		
		setTitle("About");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 190, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name = new JLabel(Data.NAME);
		name.setFont(new Font("Tahoma", Font.BOLD, 32));
		name.setBounds(10, 0, 175, 49);
		contentPane.add(name);
		
		JLabel version = new JLabel("Version: " + Data.VERSION);
		version.setBounds(10, 60, 175, 14);
		contentPane.add(version);
		
		JLabel author = new JLabel("Author: " + Data.AUTHOR);
		author.setBounds(10, 85, 167, 14);
		contentPane.add(author);
			
		JLabel contact = new JLabel("Contact: " + Data.CONTACT);
		contact.setBounds(10, 110, 167, 14);
		contentPane.add(contact);		
		
		JButton btnclose = new JButton("Close");
		btnclose.setBounds(48, 145, 89, 23);
		contentPane.add(btnclose);
		btnclose.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();		
	}

}
