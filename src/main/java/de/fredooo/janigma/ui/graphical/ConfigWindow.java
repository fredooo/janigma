package de.fredooo.janigma.ui.graphical;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
 * 
 * @author Frederik Dennig
 * @since 2013-12-14
 * @version 0.1a (last revised 2013-12-15)
 */
@SuppressWarnings("serial")
public class ConfigWindow extends JFrame implements ActionListener {
	
	private static char UNSELECTED = '-';
	
	private boolean m4active;
	private Enigma e;  

	private JPanel contentpane;
	private JComboBox<Reflector> reflist;
	private JComboBox<Rotor> rot1list;
	private JComboBox<Rotor> rot2list;
	private JComboBox<Rotor> rot3list;
	private JComboBox<Rotor> rot4list;
	private JComboBox<String> offs1list;
	private JComboBox<String> offs2list;
	private JComboBox<String> offs3list;
	private JComboBox<String> offs4list;
	private JButton btnapply;
	private JButton btnclear;
	private JButton btnreload;
	private Object pblist;

	/**
	 * Create the frame.
	 */
	public ConfigWindow(boolean m4active) {
		
		this.m4active = m4active;
		if (m4active) {
			e = (Enigma) EnigmaM4.getEnigmaM4();
			
		} else {
			e = (Enigma) EnigmaM3.getEnigmaM3();
		}
		
		setTitle("Configuration");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 412);
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);
		
		JLabel reflector = new JLabel("Reflector:");
		reflector.setBounds(10, 11, 62, 14);
		contentpane.add(reflector);
		
		reflist = new JComboBox<Reflector>();
		reflist.setBounds(74, 8, 51, 20);
		contentpane.add(reflist);
		
		JLabel rotors = new JLabel("Rotors:");
		rotors.setBounds(10, 39, 46, 14);
		contentpane.add(rotors);

		rot1list = new JComboBox<Rotor>();
		rot1list.setBounds(61, 36, 51, 20);
		contentpane.add(rot1list);
		
		rot2list = new JComboBox<Rotor>();
		rot2list.setBounds(122, 36, 51, 20);
		contentpane.add(rot2list);
		
		rot3list = new JComboBox<Rotor>();
		rot3list.setBounds(183, 36, 51, 20);
		contentpane.add(rot3list);
		
		rot4list = new JComboBox<Rotor>();
		rot4list.setBounds(244, 36, 51, 20);
		contentpane.add(rot4list);
		
		JLabel offset = new JLabel("Offset:");
		offset.setBounds(10, 70, 46, 14);
		contentpane.add(offset);
		
		offs1list = new JComboBox<String>();
		offs1list.setBounds(61, 67, 51, 20);
		contentpane.add(offs1list);
		
		offs2list = new JComboBox<String>();
		offs2list.setBounds(122, 67, 51, 20);
		contentpane.add(offs2list);
		
		offs3list = new JComboBox<String>();
		offs3list.setBounds(183, 67, 51, 20);
		contentpane.add(offs3list);
		
		offs4list = new JComboBox<String>();
		offs4list.setBounds(244, 67, 50, 20);
		contentpane.add(offs4list);
		
		JLabel plugboard = new JLabel("Plugboard:");
		plugboard.setBounds(10, 98, 62, 14);
		contentpane.add(plugboard);
				
		JComboBox<Character> p1a = new JComboBox<Character>();
		p1a.setBounds(10, 123, 46, 20);
		contentpane.add(p1a);
				
		JComboBox<Character> p1b = new JComboBox<Character>();
		p1b.setBounds(79, 123, 46, 20);
		contentpane.add(p1b);
			
		JComboBox<Character> p2a = new JComboBox<Character>();
		p2a.setBounds(10, 155, 46, 20);
		contentpane.add(p2a);
		
		JComboBox<Character> p2b = new JComboBox<Character>();
		p2b.setBounds(79, 154, 46, 20);
		contentpane.add(p2b);
				
		JComboBox<Character> p3a = new JComboBox<Character>();
		p3a.setBounds(10, 186, 46, 20);
		contentpane.add(p3a);
		
		JComboBox<Character> p3b = new JComboBox<Character>();
		p3b.setBounds(79, 186, 46, 20);
		contentpane.add(p3b);
				
		JComboBox<Character> p4a = new JComboBox<Character>();
		p4a.setBounds(10, 217, 46, 20);
		contentpane.add(p4a);
		
		JComboBox<Character> p4b = new JComboBox<Character>();
		p4b.setBounds(79, 217, 46, 20);
		contentpane.add(p4b);
		
		JComboBox<Character> p5a = new JComboBox<Character>();
		p5a.setBounds(10, 248, 46, 20);
		contentpane.add(p5a);
				
		JComboBox<Character> p5b = new JComboBox<Character>();
		p5b.setBounds(79, 248, 46, 20);
		contentpane.add(p5b);
		
		JComboBox<Character> p6a = new JComboBox<Character>();
		p6a.setBounds(10, 279, 46, 20);
		contentpane.add(p6a);
		
		JComboBox<Character> p6b = new JComboBox<Character>();
		p6b.setBounds(79, 279, 46, 20);
		contentpane.add(p6b);
		
		JComboBox<Character> p7a = new JComboBox<Character>();
		p7a.setBounds(10, 310, 46, 20);
		contentpane.add(p7a);
		
		JComboBox<Character> p7b = new JComboBox<Character>();
		p7b.setBounds(79, 310, 46, 20);
		contentpane.add(p7b);
		
		JComboBox<Character> p8a = new JComboBox<Character>();
		p8a.setBounds(183, 123, 46, 20);
		contentpane.add(p8a);
		
		JComboBox<Character> p8b = new JComboBox<Character>();
		p8b.setBounds(249, 123, 46, 20);
		contentpane.add(p8b);
		
		JComboBox<Character> p9a = new JComboBox<Character>();
		p9a.setBounds(183, 155, 46, 20);
		contentpane.add(p9a);
		
		JComboBox<Character> p9b = new JComboBox<Character>();
		p9b.setBounds(249, 155, 46, 20);
		contentpane.add(p9b);
		
		JComboBox<Character> p10a = new JComboBox<Character>();
		p10a.setBounds(183, 186, 46, 20);
		contentpane.add(p10a);
		
		JComboBox<Character> p10b = new JComboBox<Character>();
		p10b.setBounds(249, 186, 46, 20);
		contentpane.add(p10b);
		
		JComboBox<Character> p11a = new JComboBox<Character>();
		p11a.setBounds(183, 217, 46, 20);
		contentpane.add(p11a);
		
		JComboBox<Character> p11b = new JComboBox<Character>();
		p11b.setBounds(249, 217, 46, 20);
		contentpane.add(p11b);
		
		JComboBox<Character> p12a = new JComboBox<Character>();
		p12a.setBounds(183, 248, 46, 20);
		contentpane.add(p12a);
		
		JComboBox<Character> p12b = new JComboBox<Character>();
		p12b.setBounds(249, 248, 46, 20);
		contentpane.add(p12b);
		
		JComboBox<Character> p13a = new JComboBox<Character>();
		p13a.setBounds(183, 279, 46, 20);
		contentpane.add(p13a);
		
		JComboBox<Character> p13b = new JComboBox<Character>();
		p13b.setBounds(249, 279, 46, 20);
		contentpane.add(p13b);
		
		pblist = Array.newInstance(p1a.getClass(), 26);
		Array.set(pblist, 0, p1a);
		Array.set(pblist, 1, p1b);
		Array.set(pblist, 2, p2a);
		Array.set(pblist, 3, p2b);
		Array.set(pblist, 4, p3a);
		Array.set(pblist, 5, p3b);
		Array.set(pblist, 6, p4a);
		Array.set(pblist, 7, p4b);
		Array.set(pblist, 8, p5a);
		Array.set(pblist, 9, p5b);
		Array.set(pblist, 10, p6a);
		Array.set(pblist, 11, p6b);
		Array.set(pblist, 12, p7a);
		Array.set(pblist, 13, p7b);
		Array.set(pblist, 14, p8a);
		Array.set(pblist, 15, p8b);
		Array.set(pblist, 16, p9a);
		Array.set(pblist, 17, p9b);
		Array.set(pblist, 18, p10a);
		Array.set(pblist, 19, p10b);
		Array.set(pblist, 20, p11a);
		Array.set(pblist, 21, p11b);
		Array.set(pblist, 22, p12a);
		Array.set(pblist, 23, p12b);
		Array.set(pblist, 24, p13a);
		Array.set(pblist, 25, p13b);
		
		btnapply = new JButton("Apply");
		btnapply.setBounds(23, 341, 89, 23);
		contentpane.add(btnapply);
		btnapply.addActionListener(this);
		
		btnclear = new JButton("Clear plugbord");
		btnclear.setBounds(170, 309, 125, 23);
		contentpane.add(btnclear);
		btnclear.addActionListener(this);
		
		btnreload = new JButton("Reload settings");
		btnreload.setBounds(170, 341, 125, 23);
		contentpane.add(btnreload);
		btnreload.addActionListener(this);
		
		// Draw arrows
		for (int i = 0; i < 7; i++) {
			JLabel lbl = new JLabel("\u2194");
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setBounds(45, 123 + (i * 32), 46, 14);
			contentpane.add(lbl);
		}
		for (int i = 0; i < 6; i++) {
			JLabel label = new JLabel("\u2194");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(216, 123 + (i * 32), 46, 14);
			contentpane.add(label);
		}
		
		loadConfig();
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void loadConfig() {
		
		// Refelector
		if (m4active) {
			Reflector[] refs = ((EnigmaM4) e).getThinReflectors();
			for (Reflector r : refs) {
				 reflist.addItem(r);
			}
			reflist.setSelectedItem(((EnigmaM4) e).getThinReflector());
		} else {
			Reflector[] refs = ((EnigmaM3) e).getM3Reflectors();
			for (Reflector r : refs) {
				 reflist.addItem(r);
			}
			reflist.setSelectedItem(((EnigmaM3) e).getReflector());
		}
		
		// Greek rotor
		if (m4active) {
			Rotor[] rots = ((EnigmaM4) e).getGreekRotors();
			for (Rotor r : rots) {
				 rot1list.addItem(r);
			}
			rot1list.setSelectedItem(((EnigmaM4) e).getGreekRotor());
		} else {
			rot1list.setEnabled(false);
		}
		
		// Left, middle and right rotor
		Rotor[] rots = e.getM3Rotors();
		for (Rotor r : rots) {
			 rot2list.addItem(r);
			 rot3list.addItem(r);
			 rot4list.addItem(r);
		}
		rot2list.setSelectedItem(e.getLeftRotor());
		rot3list.setSelectedItem(e.getMiddleRotor());
		rot4list.setSelectedItem(e.getRightRotor());
		
		// Offset
		if (m4active) {
			for (int i = 1; i <= 26; i++) {
				offs1list.addItem(String.valueOf(i));
			}
		} else {
			offs1list.setEnabled(false);
		}
		for (int i = 1; i <= 26; i++) {
			offs2list.addItem(String.valueOf(i));
			offs3list.addItem(String.valueOf(i));
			offs4list.addItem(String.valueOf(i));
		}
		
		if (m4active) {
			offs1list.setSelectedItem(String.valueOf(((EnigmaM4) e).getGreekRotor().getOffset() + 1));
		}
		offs2list.setSelectedItem(String.valueOf(e.getLeftRotor().getOffset() + 1));
		offs3list.setSelectedItem(String.valueOf(e.getMiddleRotor().getOffset() + 1));
		offs4list.setSelectedItem(String.valueOf(e.getRightRotor().getOffset() + 1));

		// Plugboard
		for (int i = 0; i < 26; i++) {
			((JComboBox<Character>) Array.get(pblist, i)).addItem(UNSELECTED);
			for (char c : Original.original) {
				((JComboBox<Character>) Array.get(pblist, i)).addItem(c);
			}
			((JComboBox<Character>) Array.get(pblist, i)).setSelectedItem(UNSELECTED);
		}
		ArrayList<Integer> noshow = new ArrayList<Integer>();
		int p = 0;
		for (int i = 0; i < 26; i++) {
			if(e.getPlugboard().isPlugged(i) && !noshow.contains(i)) {
				((JComboBox<Character>) Array.get(pblist, p)).setSelectedItem(Original.toChar(i));
				int s = e.getPlugboard().swappedWith(i);
				noshow.add(s);
				((JComboBox<Character>) Array.get(pblist, p + 1)).setSelectedItem(Original.toChar(s));
				p += 2;
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean validateConfig() {
		// Check rotors
		if (rot2list.getSelectedItem().equals(rot3list.getSelectedItem())) {
			return false;
		} else if (rot3list.getSelectedItem().equals(rot4list.getSelectedItem())) {
			return false;
		} else if (rot2list.getSelectedItem().equals(rot4list.getSelectedItem())) {
			return false;
		}
		
		// Check plugboard
		boolean[] v = new boolean[26];
		for (int i = 0; i < 26; i += 2) {
			JComboBox<Character> l1 = (JComboBox<Character>) Array.get(pblist, i);
			JComboBox<Character> l2 = (JComboBox<Character>) Array.get(pblist, i + 1); 
			Character c1 = (Character) l1.getSelectedItem();
			Character c2 = (Character) l2.getSelectedItem();
			if (c1.charValue() != UNSELECTED && c1.equals(c2)) { return false; }
			if (c1.charValue() == UNSELECTED && c2.charValue() != UNSELECTED) {
				return false;
			}
			if (c1.charValue() != UNSELECTED && c2.charValue() == UNSELECTED) {
				return false;
			}	
			try {
				int n = Original.toInt(c1.charValue());
				if (v[n] == true) { return false; }
				else { v[n] = true; }
				n = Original.toInt(c2.charValue());
				if (v[n] == true) { return false; }
				else { v[n] = true; }
			} catch (NoSuchSymbolException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent a) {
		
		// Apply button
		if (a.getSource().equals(btnapply)) {
			if (validateConfig()) {
				if (m4active) {
					((EnigmaM4) e).setThinReflector((Reflector) reflist.getSelectedItem());
					((EnigmaM4) e).setGreekRotor((Rotor) rot1list.getSelectedItem());
				} else {
					((EnigmaM3) e).setReflector((Reflector) reflist.getSelectedItem());
				}
				e.setLeftRotor((Rotor) rot2list.getSelectedItem());
				e.setMiddleRotor((Rotor) rot3list.getSelectedItem());
				e.setRightRotor((Rotor) rot4list.getSelectedItem());
				
				if (m4active) {
					((EnigmaM4) e).getGreekRotor().setOffset(Integer.valueOf((String) offs1list.getSelectedItem() ) - 1);
				}
				e.getLeftRotor().setOffset(Integer.valueOf((String) offs2list.getSelectedItem() ) - 1);
				e.getMiddleRotor().setOffset(Integer.valueOf((String) offs3list.getSelectedItem() ) - 1);
				e.getRightRotor().setOffset(Integer.valueOf((String) offs4list.getSelectedItem() ) - 1);

				e.getPlugboard().removeAllCabels();
				for (int i = 0; i < 26; i += 2) {
					JComboBox<Character> l1 = (JComboBox<Character>) Array.get(pblist, i);
					JComboBox<Character> l2 = (JComboBox<Character>) Array.get(pblist, i + 1); 
					char c1 = ((Character) l1.getSelectedItem()).charValue();
					char c2 = ((Character) l2.getSelectedItem()).charValue();
					try {
						e.getPlugboard().addCabel(Original.toInt(c1), Original.toInt(c2));
					} catch (NoSuchSymbolException e) {
						if (e.getCharacter() != UNSELECTED) {
							e.printStackTrace();
						}
					}
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MainWindow frame = new MainWindow(m4active);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				dispose();
			} else {
				EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ErrorWindow frame = new ErrorWindow("Invalid settings! Check rotor and plugboard selection.");
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			}
		}
		
		// Reset plugboard button
		else if (a.getSource().equals(btnclear)) {
			for (int i = 0; i < 26; i++) {
				((JComboBox<Character>) Array.get(pblist, i)).setSelectedItem(UNSELECTED);
			}
		}
		
		// Reload settings button
		else if (a.getSource().equals(btnreload)) {
			loadConfig();
		}
	}
}
