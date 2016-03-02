package de.fredooo.janigma.ui.graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import de.fredooo.janigma.io.FileIo;
import de.fredooo.janigma.machine.Enigma;
import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.EnigmaM4;
import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Provides the main window of the application.
 * @author Frederik Dennig
 * @since 2013-12-13
 * @version 0.0.2 (last revised 2016-03-02)
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {
	
	private static final FileNameExtensionFilter JSON = new FileNameExtensionFilter("JSON File", "json");
	
	private static MainWindow instance;
	
	private Enigma enigma;
	private boolean m4Active;
	
	private JLabel machineName;
	
	private JButton btnRot1Up;
	private JButton btnRot2Up;
	private JButton btnRot3Up;
	private JButton btnRot4Up;
	
	private JTextField rot1Text;
	private JTextField rot2Text;
	private JTextField rot3Text;
	private JTextField rot4Text;
	
	private JButton btnRot1Down;
	private JButton btnRot2Down;
	private JButton btnRot3Down;
	private JButton btnRot4Down;
	
	private JTextArea inputText;
	private JTextArea outputText;
	
	private JButton btnEnDecrypt;
	private JButton btnConfig;
	
	private JMenuItem m3Item;
	private JMenuItem m4Item;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem aboutItem;

	/**
	 * Creates the main window of the program.
	 */
	public MainWindow() {
		this.enigma = new EnigmaM3();
		
		this.setTitle("Janigma");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(290, 525);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(createMenuBar());
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(panel);
		
		machineName = new JLabel("- Enigma M3 -");
		machineName.setFont(new Font("Tahoma", Font.BOLD, 12));
		machineName.setBounds(12, 0, 130, 30);
		panel.add(machineName);
				
		btnRot1Up = new JButton("\u25B2");
		btnRot1Up.setBounds(7, 30, 63, 23);
		panel.add(btnRot1Up);
		btnRot1Up.addActionListener(this);
		
		btnRot2Up = new JButton("\u25B2");
		btnRot2Up.setBounds(80, 30, 60, 23);
		panel.add(btnRot2Up);
		btnRot2Up.addActionListener(this);

		btnRot3Up = new JButton("\u25B2");
		btnRot3Up.setBounds(150, 30, 60, 23);
		panel.add(btnRot3Up);
		btnRot3Up.addActionListener(this);

		btnRot4Up = new JButton("\u25B2");
		btnRot4Up.setBounds(220, 30, 60, 23);
		panel.add(btnRot4Up);
		btnRot4Up.addActionListener(this);
		
		rot1Text = new JTextField();
		rot1Text.setEditable(false);
		rot1Text.setHorizontalAlignment(SwingConstants.CENTER);
		rot1Text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot1Text.setBounds(10, 60, 60, 60);
		panel.add(rot1Text);
		rot1Text.setEditable(false);
		
		rot2Text = new JTextField();
		rot2Text.setEditable(false);
		rot2Text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot2Text.setHorizontalAlignment(SwingConstants.CENTER);
		rot2Text.setBounds(80, 60, 60, 60);
		panel.add(rot2Text);
		
		rot3Text = new JTextField();
		rot3Text.setEditable(false);
		rot3Text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot3Text.setHorizontalAlignment(SwingConstants.CENTER);
		rot3Text.setBounds(150, 60, 60, 60);
		panel.add(rot3Text);
		
		rot4Text = new JTextField();
		rot4Text.setEditable(false);
		rot4Text.setFont(new Font("Tahoma", Font.BOLD, 40));
		rot4Text.setHorizontalAlignment(SwingConstants.CENTER);
		rot4Text.setBounds(220, 60, 60, 60);
		panel.add(rot4Text);
				
		btnRot1Down = new JButton("\u25BC");
		btnRot1Down.setBounds(10, 128, 60, 23);
		panel.add(btnRot1Down);
		btnRot1Down.addActionListener(this);

		btnRot2Down = new JButton("\u25BC");
		btnRot2Down.setBounds(80, 128, 60, 23);
		panel.add(btnRot2Down);
		btnRot2Down.addActionListener(this);

		btnRot3Down = new JButton("\u25BC");
		btnRot3Down.setBounds(150, 128, 60, 23);
		panel.add(btnRot3Down);
		btnRot3Down.addActionListener(this);
		
		btnRot4Down = new JButton("\u25BC");
		btnRot4Down.setBounds(220, 128, 60, 23);
		panel.add(btnRot4Down);
		btnRot4Down.addActionListener(this);
		
		JLabel input = new JLabel("Input:");
		input.setBounds(12, 168, 46, 14);
		panel.add(input);
		
		inputText = new JTextArea();
		inputText.setLineWrap(true);
		inputText.setBounds(10, 188, 270, 110);
		panel.add(inputText);
		
		JLabel output = new JLabel("Output:");
		output.setBounds(12, 308, 46, 14);
		panel.add(output);
		
		outputText = new JTextArea();
		outputText.setEditable(false);
		outputText.setLineWrap(true);
		outputText.setBounds(10, 328, 270, 110);
		panel.add(outputText);
		
		btnEnDecrypt = new JButton("En-/Decrypt");
		btnEnDecrypt.setBounds(10, 448, 100, 23);
		panel.add(btnEnDecrypt);
		btnEnDecrypt.addActionListener(this);
		
		btnConfig = new JButton("Change Configuration");
		btnConfig.setBounds(120, 448, 160, 23);
		panel.add(btnConfig);
		btnConfig.addActionListener(this);
			
		updateRotors();
	}
	
	/**
	 * Returns the MainWindow singleton.
	 * @return the single instance of the MainWindow class
	 */
	public static MainWindow instance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
	
	/**
	 * Updates the rotor positions.
	 */
	private void updateRotors() {
		if (m4Active) {
			rot1Text.setText(String.valueOf(Original.toChar(((EnigmaM4) enigma).getGreekRotor().getPosition())));
			btnRot1Up.setEnabled(true);
			btnRot1Down.setEnabled(true);
			machineName.setText("- Enigma M4 -");
		} else {
			rot1Text.setText("");
			btnRot1Up.setEnabled(false);
			btnRot1Down.setEnabled(false);
		}
		rot2Text.setText(String.valueOf(Original.toChar(enigma.getLeftRotor().getPosition())));
		rot3Text.setText(String.valueOf(Original.toChar(enigma.getMiddleRotor().getPosition())));
		rot4Text.setText(String.valueOf(Original.toChar(enigma.getRightRotor().getPosition())));
	}
	
	private void updateMachine() {
		m4Active = enigma instanceof EnigmaM4;
		if (m4Active) {
			machineName.setText("- Enigma M4 -");
			btnRot1Up.setEnabled(true);
			btnRot1Down.setEnabled(true);
		} else {
			machineName.setText("- Enigma M3 -");
			btnRot1Up.setEnabled(false);
			btnRot1Down.setEnabled(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {	
		// Up buttons
		if (a.getSource().equals(btnRot1Up)) {
			((EnigmaM4) enigma).getGreekRotor().incrementPosition();
		}
		else if (a.getSource().equals(btnRot2Up)) {
			enigma.getLeftRotor().incrementPosition();
		}
		else if (a.getSource().equals(btnRot3Up)) {
			enigma.getMiddleRotor().incrementPosition();
		}
		else if (a.getSource().equals(btnRot4Up)) {
			enigma.getRightRotor().incrementPosition();
		}
		
		// Down buttons
		else if (a.getSource().equals(btnRot1Down)) {
			((EnigmaM4) enigma).getGreekRotor().decrementPosition();
		}
		else if (a.getSource().equals(btnRot2Down)) {
			enigma.getLeftRotor().decrementPosition();
		}
		else if (a.getSource().equals(btnRot3Down)) {
			enigma.getMiddleRotor().decrementPosition();
		}
		else if (a.getSource().equals(btnRot4Down)) {
			enigma.getRightRotor().decrementPosition();
		}
		
		// En-/Decrypt button
		else if (a.getSource().equals(btnEnDecrypt)) {
			final String input = inputText.getText();
			if (Original.isValidString(input)) {
				try {
					outputText.setText(enigma.use(input));
				} catch (NoSuchSymbolException e) {
					// This block should never be reached
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"<html>Invalid character!<br>Please check your input text.<br>Allowed characters are A-Z and a-z.</html>",
						"Invalid character!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// Menu bar items
		else if (a.getSource().equals(m3Item)) {
			enigma = new EnigmaM3();
			updateMachine();
		}
		else if (a.getSource().equals(m4Item)) {
			enigma = new EnigmaM4();
			updateMachine();
		}
		else if (a.getSource().equals(save)) {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Save Configuration");
			fc.setFileFilter(JSON);
			int fcReturn = fc.showSaveDialog(this);
			if (fcReturn == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if (!file.getName().endsWith(".json")) {
					file = new File(file + ".json");
				}
				FileIo.saveEnigmaMachine(file, enigma);
			}
		}
		else if (a.getSource().equals(load)) {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Load Configuration");
			fc.setFileFilter(JSON);
			int fcReturn = fc.showOpenDialog(this);
			if (fcReturn == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				enigma = FileIo.loadEnigmaMachine(file);
				updateMachine();
			}
		}
		else if (a.getSource().equals(aboutItem)) {
			AboutDialog frame = new AboutDialog();
			frame.setVisible(true);
		}
		
		// Configuration button
		else if (a.getSource().equals(btnConfig)) {
			ConfigDialog frame = new ConfigDialog(m4Active, enigma);
			frame.setVisible(true);
		}
		
		// Always update rotor positions
		updateRotors();
	}
	
	/**
	 * Creates the menu bar.
	 * @return the created menu bar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu machine = new JMenu("Machine");
		menuBar.add(machine);
		
		m3Item = new JMenuItem("Enigma M3");
		m3Item.addActionListener(this);
		machine.add(m3Item);
		
		m4Item = new JMenuItem("Enigma M4");
		m4Item.addActionListener(this);
		machine.add(m4Item);
		
		JMenu config = new JMenu("Configuration");
		menuBar.add(config);
		
		save = new JMenuItem("Save");
		save.addActionListener(this);
		config.add(save);
		
		load = new JMenuItem("Load");
		load.addActionListener(this);
		config.add(load);
		
		JMenu help = new JMenu("Help");
		menuBar.add(help);
		
		aboutItem = new JMenuItem("About Janigma");
		aboutItem.addActionListener(this);
		help.add(aboutItem);
		
		return menuBar;
	}
	
}
