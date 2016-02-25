package de.fredooo.janigma.ui.graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.fredooo.janigma.machine.Enigma;
import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.EnigmaM4;
import de.fredooo.janigma.machine.Reflector;
import de.fredooo.janigma.machine.Rotor;
import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

import javax.swing.SwingConstants;

/**
 * Provides the "Configuration" dialog.
 * @author Frederik Dennig
 * @since 2013-12-14
 * @version 0.0.1 (last revised 2016-02-20)
 */
@SuppressWarnings("serial")
public class ConfigDialog extends JDialog implements ActionListener {
	
	private static char UNSELECTED = '-';
	
	private boolean m4Active;
	private Enigma enigma;  

	private JComboBox<Reflector> refList;
	
	private JComboBox<Rotor> rot1List;
	private JComboBox<Rotor> rot2List;
	private JComboBox<Rotor> rot3List;
	private JComboBox<Rotor> rot4List;
	
	private JComboBox<String> offs1List;
	private JComboBox<String> offs2List;
	private JComboBox<String> offs3List;
	private JComboBox<String> offs4List;
	
	private JButton btnApply;
	private JButton btnClear;
	private JButton btnReset;
	
	private List<JComboBox<Character>> plugboardLists;

	/**
	 * Creates the "Configuration" dialog.
	 * @param m4Active whether or not the the Enigma M4 is used
	 */
	public ConfigDialog(boolean m4Active) {
		super(MainWindow.instance());
		
		this.m4Active = m4Active;
		this.enigma = m4Active ? EnigmaM4.instance() : EnigmaM3.instance();		
		
		this.setTitle("Configuration");
		this.setModal(true);
		this.setSize(307, 400);
		this.setResizable(false);
		this.setLocation(MainWindow.instance().getLocation());
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(panel);
		
		JLabel reflector = new JLabel("Reflector:");
		reflector.setBounds(10, 11, 62, 14);
		panel.add(reflector);
		
		refList = new JComboBox<Reflector>();
		refList.setBounds(74, 8, 51, 20);
		panel.add(refList);
		
		JLabel rotors = new JLabel("Rotors:");
		rotors.setBounds(10, 39, 46, 14);
		panel.add(rotors);

		rot1List = new JComboBox<Rotor>();
		rot1List.setBounds(61, 36, 51, 20);
		panel.add(rot1List);
		
		rot2List = new JComboBox<Rotor>();
		rot2List.setBounds(122, 36, 51, 20);
		panel.add(rot2List);
		
		rot3List = new JComboBox<Rotor>();
		rot3List.setBounds(183, 36, 51, 20);
		panel.add(rot3List);
		
		rot4List = new JComboBox<Rotor>();
		rot4List.setBounds(244, 36, 51, 20);
		panel.add(rot4List);
		
		JLabel offset = new JLabel("Offset:");
		offset.setBounds(10, 70, 46, 14);
		panel.add(offset);
		
		offs1List = new JComboBox<String>();
		offs1List.setBounds(61, 67, 51, 20);
		panel.add(offs1List);
		
		offs2List = new JComboBox<String>();
		offs2List.setBounds(122, 67, 51, 20);
		panel.add(offs2List);
		
		offs3List = new JComboBox<String>();
		offs3List.setBounds(183, 67, 51, 20);
		panel.add(offs3List);
		
		offs4List = new JComboBox<String>();
		offs4List.setBounds(244, 67, 50, 20);
		panel.add(offs4List);
		
		JLabel plugboard = new JLabel("Plugboard:");
		plugboard.setBounds(10, 98, 100, 14);
		panel.add(plugboard);
				
		JComboBox<Character> p1a = new JComboBox<Character>();
		p1a.setBounds(10, 123, 46, 20);
		panel.add(p1a);
				
		JComboBox<Character> p1b = new JComboBox<Character>();
		p1b.setBounds(79, 123, 46, 20);
		panel.add(p1b);
			
		JComboBox<Character> p2a = new JComboBox<Character>();
		p2a.setBounds(10, 155, 46, 20);
		panel.add(p2a);
		
		JComboBox<Character> p2b = new JComboBox<Character>();
		p2b.setBounds(79, 154, 46, 20);
		panel.add(p2b);
				
		JComboBox<Character> p3a = new JComboBox<Character>();
		p3a.setBounds(10, 186, 46, 20);
		panel.add(p3a);
		
		JComboBox<Character> p3b = new JComboBox<Character>();
		p3b.setBounds(79, 186, 46, 20);
		panel.add(p3b);
				
		JComboBox<Character> p4a = new JComboBox<Character>();
		p4a.setBounds(10, 217, 46, 20);
		panel.add(p4a);
		
		JComboBox<Character> p4b = new JComboBox<Character>();
		p4b.setBounds(79, 217, 46, 20);
		panel.add(p4b);
		
		JComboBox<Character> p5a = new JComboBox<Character>();
		p5a.setBounds(10, 248, 46, 20);
		panel.add(p5a);
				
		JComboBox<Character> p5b = new JComboBox<Character>();
		p5b.setBounds(79, 248, 46, 20);
		panel.add(p5b);
		
		JComboBox<Character> p6a = new JComboBox<Character>();
		p6a.setBounds(10, 279, 46, 20);
		panel.add(p6a);
		
		JComboBox<Character> p6b = new JComboBox<Character>();
		p6b.setBounds(79, 279, 46, 20);
		panel.add(p6b);
		
		JComboBox<Character> p7a = new JComboBox<Character>();
		p7a.setBounds(10, 310, 46, 20);
		panel.add(p7a);
		
		JComboBox<Character> p7b = new JComboBox<Character>();
		p7b.setBounds(79, 310, 46, 20);
		panel.add(p7b);
		
		JComboBox<Character> p8a = new JComboBox<Character>();
		p8a.setBounds(183, 123, 46, 20);
		panel.add(p8a);
		
		JComboBox<Character> p8b = new JComboBox<Character>();
		p8b.setBounds(249, 123, 46, 20);
		panel.add(p8b);
		
		JComboBox<Character> p9a = new JComboBox<Character>();
		p9a.setBounds(183, 155, 46, 20);
		panel.add(p9a);
		
		JComboBox<Character> p9b = new JComboBox<Character>();
		p9b.setBounds(249, 155, 46, 20);
		panel.add(p9b);
		
		JComboBox<Character> p10a = new JComboBox<Character>();
		p10a.setBounds(183, 186, 46, 20);
		panel.add(p10a);
		
		JComboBox<Character> p10b = new JComboBox<Character>();
		p10b.setBounds(249, 186, 46, 20);
		panel.add(p10b);
		
		JComboBox<Character> p11a = new JComboBox<Character>();
		p11a.setBounds(183, 217, 46, 20);
		panel.add(p11a);
		
		JComboBox<Character> p11b = new JComboBox<Character>();
		p11b.setBounds(249, 217, 46, 20);
		panel.add(p11b);
		
		JComboBox<Character> p12a = new JComboBox<Character>();
		p12a.setBounds(183, 248, 46, 20);
		panel.add(p12a);
		
		JComboBox<Character> p12b = new JComboBox<Character>();
		p12b.setBounds(249, 248, 46, 20);
		panel.add(p12b);
		
		JComboBox<Character> p13a = new JComboBox<Character>();
		p13a.setBounds(183, 279, 46, 20);
		panel.add(p13a);
		
		JComboBox<Character> p13b = new JComboBox<Character>();
		p13b.setBounds(249, 279, 46, 20);
		panel.add(p13b);
		
		plugboardLists = new ArrayList<JComboBox<Character>>(26);
		plugboardLists.add(p1a);
		plugboardLists.add(p1b);
		plugboardLists.add(p2a);
		plugboardLists.add(p2b);
		plugboardLists.add(p3a);
		plugboardLists.add(p3b);
		plugboardLists.add(p4a);
		plugboardLists.add(p4b);
		plugboardLists.add(p5a);
		plugboardLists.add(p5b);
		plugboardLists.add(p6a);
		plugboardLists.add(p6b);
		plugboardLists.add(p7a);
		plugboardLists.add(p7b);
		plugboardLists.add(p8a);
		plugboardLists.add(p8b);
		plugboardLists.add(p9a);
		plugboardLists.add(p9b);
		plugboardLists.add(p10a);
		plugboardLists.add(p10b);
		plugboardLists.add(p11a);
		plugboardLists.add(p11b);
		plugboardLists.add(p12a);
		plugboardLists.add(p12b);
		plugboardLists.add(p13a);
		plugboardLists.add(p13b);
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(23, 345, 89, 23);
		panel.add(btnApply);
		btnApply.addActionListener(this);
		
		btnClear = new JButton("Clear Plugbord");
		btnClear.setBounds(170, 313, 125, 23);
		panel.add(btnClear);
		btnClear.addActionListener(this);
		
		btnReset = new JButton("Reset Settings");
		btnReset.setBounds(170, 345, 125, 23);
		panel.add(btnReset);
		btnReset.addActionListener(this);
		
		// Draw arrows
		for (int i = 0; i < 7; i++) {
			JLabel lbl = new JLabel("\u2194");
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setBounds(45, 123 + (i * 32), 46, 14);
			panel.add(lbl);
		}
		for (int i = 0; i < 6; i++) {
			JLabel label = new JLabel("\u2194");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(216, 123 + (i * 32), 46, 14);
			panel.add(label);
		}
		
		loadConfig();
	}
	
	/**
	 * Loads the configuration of a Enigma machine
	 */
	private void loadConfig() {		
		// Refelector
		if (m4Active) {
			Reflector[] refs = ((EnigmaM4) enigma).getThinReflectors();
			for (Reflector r : refs) {
				 refList.addItem(r);
			}
			refList.setSelectedItem(((EnigmaM4) enigma).getThinReflector());
		} else {
			Reflector[] refs = ((EnigmaM3) enigma).getM3Reflectors();
			for (Reflector r : refs) {
				 refList.addItem(r);
			}
			refList.setSelectedItem(((EnigmaM3) enigma).getReflector());
		}
		
		// Greek rotor
		if (m4Active) {
			Rotor[] rots = ((EnigmaM4) enigma).getGreekRotors();
			for (Rotor r : rots) {
				 rot1List.addItem(r);
			}
			rot1List.setSelectedItem(((EnigmaM4) enigma).getGreekRotor());
		} else {
			rot1List.setEnabled(false);
		}
		
		// Left, middle and right rotor
		Rotor[] rots = enigma.getM3Rotors();
		for (Rotor r : rots) {
			 rot2List.addItem(r);
			 rot3List.addItem(r);
			 rot4List.addItem(r);
		}
		rot2List.setSelectedItem(enigma.getLeftRotor());
		rot3List.setSelectedItem(enigma.getMiddleRotor());
		rot4List.setSelectedItem(enigma.getRightRotor());
		
		// Offset
		if (m4Active) {
			for (int i = 1; i <= 26; i++) {
				offs1List.addItem(String.valueOf(i));
			}
		} else {
			offs1List.setEnabled(false);
		}
		for (int i = 1; i <= 26; i++) {
			offs2List.addItem(String.valueOf(i));
			offs3List.addItem(String.valueOf(i));
			offs4List.addItem(String.valueOf(i));
		}
		
		if (m4Active) {
			offs1List.setSelectedItem(String.valueOf(((EnigmaM4) enigma).getGreekRotor().getOffset() + 1));
		}
		offs2List.setSelectedItem(String.valueOf(enigma.getLeftRotor().getOffset() + 1));
		offs3List.setSelectedItem(String.valueOf(enigma.getMiddleRotor().getOffset() + 1));
		offs4List.setSelectedItem(String.valueOf(enigma.getRightRotor().getOffset() + 1));

		// Plugboard
		for (int i = 0; i < 26; i++) {
			plugboardLists.get(i).addItem(UNSELECTED);
			for (char c : Original.ORIGINAL) {
				plugboardLists.get(i).addItem(c);
			}
			plugboardLists.get(i).setSelectedItem(UNSELECTED);
		}
		ArrayList<Integer> noShow = new ArrayList<Integer>();
		int p = 0;
		for (int i = 0; i < 26; i++) {
			if (enigma.getPlugboard().isPlugged(i) && !noShow.contains(i)) {
				plugboardLists.get(p).setSelectedItem(Original.toChar(i));
				int s = enigma.getPlugboard().swappedWith(i);
				noShow.add(s);
				plugboardLists.get(p + 1).setSelectedItem(Original.toChar(s));
				p += 2;
			}
		}
	}
	
	/**
	 * Tests, whether or not the configuration is valid
	 * @return true, if the configuration is valid, otherwise false
	 */
	private boolean validateConfig() {
		// Check rotors
		if (rot2List.getSelectedItem().equals(rot3List.getSelectedItem())) {
			return false;
		} else if (rot3List.getSelectedItem().equals(rot4List.getSelectedItem())) {
			return false;
		} else if (rot2List.getSelectedItem().equals(rot4List.getSelectedItem())) {
			return false;
		}
		
		// Check plugboard
		boolean[] v = new boolean[26];
		for (int i = 0; i < 26; i += 2) {
			JComboBox<Character> l1 = plugboardLists.get(i);
			JComboBox<Character> l2 = plugboardLists.get(i + 1); 
			char c1 = (char) l1.getSelectedItem();
			char c2 = (char) l2.getSelectedItem();
			if (c1 != UNSELECTED && c1 == c2) { return false; }
			if (c1 == UNSELECTED && c2 != UNSELECTED) {
				return false;
			}
			if (c1 != UNSELECTED && c2 == UNSELECTED) {
				return false;
			}
			if (c1 != UNSELECTED && c2 != UNSELECTED) {
				try {
					int n = Original.toInt(c1);
					if (v[n] == true) { return false; }
					else { v[n] = true; }
					n = Original.toInt(c2);
					if (v[n] == true) { return false; }
					else { v[n] = true; }
				} catch (NoSuchSymbolException e) {
					// This block should never be reached
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// Apply button
		if (a.getSource().equals(btnApply)) {
			if (validateConfig()) {
				if (m4Active) {
					((EnigmaM4) enigma).setThinReflector((Reflector) refList.getSelectedItem());
					((EnigmaM4) enigma).setGreekRotor((Rotor) rot1List.getSelectedItem());
				} else {
					((EnigmaM3) enigma).setReflector((Reflector) refList.getSelectedItem());
				}
				enigma.setLeftRotor((Rotor) rot2List.getSelectedItem());
				enigma.setMiddleRotor((Rotor) rot3List.getSelectedItem());
				enigma.setRightRotor((Rotor) rot4List.getSelectedItem());
				
				if (m4Active) {
					((EnigmaM4) enigma).getGreekRotor().setOffset(Integer.valueOf((String) offs1List.getSelectedItem() ) - 1);
				}
				enigma.getLeftRotor().setOffset(Integer.valueOf((String) offs2List.getSelectedItem() ) - 1);
				enigma.getMiddleRotor().setOffset(Integer.valueOf((String) offs3List.getSelectedItem() ) - 1);
				enigma.getRightRotor().setOffset(Integer.valueOf((String) offs4List.getSelectedItem() ) - 1);

				enigma.getPlugboard().removeAllCables();
				for (int i = 0; i < 26; i += 2) {
					JComboBox<Character> l1 = plugboardLists.get(i);
					JComboBox<Character> l2 = plugboardLists.get(i + 1); 
					char c1 = (Character) l1.getSelectedItem();
					char c2 = (Character) l2.getSelectedItem();
					try {
						enigma.getPlugboard().addCable(Original.toInt(c1), Original.toInt(c2));
					} catch (NoSuchSymbolException e) {
						if (e.getCharacter() != UNSELECTED) {
							e.printStackTrace();
						}
					}
				}
				dispose();
			} else {
				JOptionPane.showMessageDialog(this,
						"<html>Invalid configuration!<br>Please check your rotor selection<br>and plugboard configuration.</html>",
						"Invalid configuration!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// Reset plugboard button
		else if (a.getSource().equals(btnClear)) {
			for (int i = 0; i < 26; i++) {
				plugboardLists.get(i).setSelectedItem(UNSELECTED);
			}
		}
		
		// Reload settings button
		else if (a.getSource().equals(btnReset)) {
			loadConfig();
		}
	}
	
}
