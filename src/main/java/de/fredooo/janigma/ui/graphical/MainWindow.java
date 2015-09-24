package de.fredooo.janigma.ui.graphical;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import de.fredooo.janigma.machine.Enigma;
import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.EnigmaM4;
import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;

/**
 * Implements the main window.
 * @author Frederik Dennig
 * @since 2013-12-13
 * @version 0.1a (last revised 2013-12-15)
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {
	
	private Enigma e;
	private boolean m4active;
	
	private JPanel contentpane;
	private JLabel mname;
	private JButton rot1btnu;
	private JButton rot2btnu;
	private JButton rot3btnu;
	private JButton rot4btnu;
	private JTextField rot1text;
	private JTextField rot2text;
	private JTextField rot3text;
	private JTextField rot4text;
	private JButton rot1btnd;
	private JButton rot2btnd;
	private JButton rot3btnd;
	private JButton rot4btnd;
	private JTextArea in;
	private JTextArea out;
	private JButton btned;
	private JButton btnconf;
	private JMenuItem itemm3;
	private JMenuItem itemm4;
	private JMenuItem about;

	/**
	 * Create the frame.
	 */
	public MainWindow(boolean m4active) {
		
		this.m4active = m4active;
		if (m4active) {
			e = (Enigma) EnigmaM4.getEnigmaM4();
			
		} else {
			e = (Enigma) EnigmaM3.getEnigmaM3();
		}
		
		setTitle("Janigma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 295, 551);
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBounds(0, 0, 294, 21);
		contentpane.add(menubar);
		
		JMenu machine = new JMenu("Machine");
		menubar.add(machine);
		
		itemm3 = new JMenuItem("Enigma M3");
		machine.add(itemm3);
		itemm3.addActionListener(this);
		
		itemm4 = new JMenuItem("Enigma M4");
		machine.add(itemm4);
		itemm4.addActionListener(this);
		
		JMenu help = new JMenu("Help");
		menubar.add(help);
		
		about = new JMenuItem("About Janigma");
		help.add(about);
		about.addActionListener(this);
		
		mname = new JLabel("- Enigma M3 -");
		mname.setFont(new Font("Tahoma", Font.BOLD, 11));
		mname.setBounds(10, 32, 130, 14);
		contentpane.add(mname);
				
		rot1btnu = new JButton("\u25B2");
		rot1btnu.setBounds(7, 57, 63, 23);
		contentpane.add(rot1btnu);
		rot1btnu.addActionListener(this);
		
		rot2btnu = new JButton("\u25B2");
		rot2btnu.setBounds(80, 57, 60, 23);
		contentpane.add(rot2btnu);
		rot2btnu.addActionListener(this);

		rot3btnu = new JButton("\u25B2");
		rot3btnu.setBounds(150, 57, 60, 23);
		contentpane.add(rot3btnu);
		rot3btnu.addActionListener(this);

		rot4btnu = new JButton("\u25B2");
		rot4btnu.setBounds(220, 57, 60, 23);
		contentpane.add(rot4btnu);
		rot4btnu.addActionListener(this);
		
		rot1text = new JTextField();
		rot1text.setEditable(false);
		rot1text.setHorizontalAlignment(SwingConstants.CENTER);
		rot1text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot1text.setBounds(10, 91, 60, 60);
		contentpane.add(rot1text);
		rot1text.setEditable(false);
		
		rot2text = new JTextField();
		rot2text.setEditable(false);
		rot2text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot2text.setHorizontalAlignment(SwingConstants.CENTER);
		rot2text.setBounds(80, 91, 60, 60);
		contentpane.add(rot2text);
		
		rot3text = new JTextField();
		rot3text.setEditable(false);
		rot3text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot3text.setHorizontalAlignment(SwingConstants.CENTER);
		rot3text.setBounds(150, 91, 60, 60);
		contentpane.add(rot3text);
		
		rot4text = new JTextField();
		rot4text.setEditable(false);
		rot4text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot4text.setHorizontalAlignment(SwingConstants.CENTER);
		rot4text.setBounds(220, 91, 60, 60);
		contentpane.add(rot4text);
				
		rot1btnd = new JButton("\u25BC");
		rot1btnd.setBounds(10, 162, 60, 23);
		contentpane.add(rot1btnd);
		rot1btnd.addActionListener(this);

		rot2btnd = new JButton("\u25BC");
		rot2btnd.setBounds(80, 162, 60, 23);
		contentpane.add(rot2btnd);
		rot2btnd.addActionListener(this);

		rot3btnd = new JButton("\u25BC");
		rot3btnd.setBounds(150, 162, 60, 23);
		contentpane.add(rot3btnd);
		rot3btnd.addActionListener(this);
		
		rot4btnd = new JButton("\u25BC");
		rot4btnd.setBounds(220, 162, 60, 23);
		contentpane.add(rot4btnd);
		rot4btnd.addActionListener(this);
		
		JLabel input = new JLabel("Input:");
		input.setBounds(10, 196, 46, 14);
		contentpane.add(input);
		
		in = new JTextArea();
		in.setLineWrap(true);
		in.setBounds(10, 216, 270, 110);
		contentpane.add(in);
		
		JLabel output = new JLabel("Output:");
		output.setBounds(10, 337, 46, 14);
		contentpane.add(output);
		
		out = new JTextArea();
		out.setEditable(false);
		out.setLineWrap(true);
		out.setBounds(10, 357, 270, 110);
		contentpane.add(out);
		
		btned = new JButton("En-/Decrypt");
		btned.setBounds(10, 478, 100, 23);
		contentpane.add(btned);
		btned.addActionListener(this);
		
		btnconf = new JButton("Configuration");
		btnconf.setBounds(155, 478, 120, 23);
		contentpane.add(btnconf);
		btnconf.addActionListener(this);
			
		updateRotors();
	}
	
	private void updateRotors() {
		if (m4active) {
			rot1text.setText(String.valueOf(Original.toChar(((EnigmaM4) e).getGreekRotor().getPosition())));
			rot1btnu.setEnabled(true);
			rot1btnd.setEnabled(true);
			mname.setText("- Enigma M4 -");

		} else {
			rot1text.setText("");
			rot1btnu.setEnabled(false);
			rot1btnd.setEnabled(false);

		}
		rot2text.setText(String.valueOf(Original.toChar(e.getLeftRotor().getPosition())));
		rot3text.setText(String.valueOf(Original.toChar(e.getMiddleRotor().getPosition())));
		rot4text.setText(String.valueOf(Original.toChar(e.getRightRotor().getPosition())));
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
				
		// Up buttons
		if (a.getSource().equals(rot1btnu)) {
			((EnigmaM4) e).getGreekRotor().incrementPosition();
		}
		else if (a.getSource().equals(rot2btnu)) {
			e.getLeftRotor().incrementPosition();
		}
		else if (a.getSource().equals(rot3btnu)) {
			e.getMiddleRotor().incrementPosition();
		}
		else if (a.getSource().equals(rot4btnu)) {
			e.getRightRotor().incrementPosition();
		}
		
		// Down buttons
		else if (a.getSource().equals(rot1btnd)) {
			((EnigmaM4) e).getGreekRotor().decrementPosition();
		}
		else if (a.getSource().equals(rot2btnd)) {
			e.getLeftRotor().decrementPosition();
		}
		else if (a.getSource().equals(rot3btnd)) {
			e.getMiddleRotor().decrementPosition();
		}
		else if (a.getSource().equals(rot4btnd)) {
			e.getRightRotor().decrementPosition();
		}
		
		// En-/Decrypt button
		else if (a.getSource().equals(btned)) {
			try {
				out.setText(e.use(in.getText()));
			} catch (NoSuchSymbolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// Menubar
		else if (a.getSource().equals(itemm3)) {
			e = (Enigma) EnigmaM3.getEnigmaM3();
			m4active = false;
			mname.setText("- Enigma M3 -");
			rot1btnu.setEnabled(false);
			rot1btnd.setEnabled(false);
		}
		else if (a.getSource().equals(itemm4)) {
			e = (Enigma) EnigmaM4.getEnigmaM4();
			m4active = true;
			mname.setText("- Enigma M4 -");
			rot1btnu.setEnabled(true);
			rot1btnd.setEnabled(true);
		}
		else if (a.getSource().equals(about)) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						AboutWindow frame = new AboutWindow();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		// Configuration button
		else if (a.getSource().equals(btnconf)) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ConfigWindow frame = new ConfigWindow(m4active);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			dispose();
		}
		updateRotors();		
	}
	
}
