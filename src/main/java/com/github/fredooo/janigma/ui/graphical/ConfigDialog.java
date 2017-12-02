package com.github.fredooo.janigma.ui.graphical;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.github.fredooo.janigma.core.machine.EnigmaM3;
import com.github.fredooo.janigma.core.machine.EnigmaM4;
import com.github.fredooo.janigma.core.machine.Reflector;
import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import com.github.fredooo.janigma.core.machine.Enigma;
import com.github.fredooo.janigma.core.machine.Rotor;
import javafx.util.Pair;

/**
 * Provides the "Configuration" dialog.
 * @author Frederik Dennig
 * @since 2013-12-14
 * @version 0.0.4 (last revised 2017-12-02)
 */
@SuppressWarnings("serial")
public class ConfigDialog extends JDialog {

    private static final int NUM_PLUGS = 13;
    private static final int NUM_PLUGS_LEFT = (NUM_PLUGS + 1) / 2;
    private static final int NUM_PLUGS_RIGHT = (NUM_PLUGS - 1) / 2;
    private static final int NUM_SYMBOLS = 26;
	private static final char UNSELECTED = '-';
	
	private boolean m4Active;

	private Enigma lastEnigma;
    private Enigma currEnigma;

    private JRadioButton m3Radio;
    private JRadioButton m4Radio;

    private JComboBox<Reflector> reflectorList;
	
	private JComboBox<Rotor> rotor1List;
	private JComboBox<Rotor> rotor2List;
	private JComboBox<Rotor> rotor3List;
	private JComboBox<Rotor> rotor4List;
	
	private JComboBox<String> offset1List;
	private JComboBox<String> offset2List;
	private JComboBox<String> offset3List;
	private JComboBox<String> offset4List;

	private List<Pair<JComboBox<Character>, JComboBox<Character>>> plugboardList;

    /**
	 * Creates the "Configuration" dialog.
	 * @param m4Active whether or not the the Enigma M4 is used
	 * @param enigma the used Enigma machine
	 */
	private ConfigDialog(boolean m4Active, Enigma enigma) {
		super(MainWindow.instance(), "Configuration", true);
		this.m4Active = m4Active;
		this.lastEnigma = enigma;
        this.currEnigma = enigma;
		this.plugboardList = new ArrayList<Pair<JComboBox<Character>, JComboBox<Character>>>(NUM_PLUGS);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
        this.setContentPane(createMainPanel());
        this.pack();
		this.setLocation(MainWindow.instance().getLocation());
        this.loadConfiguration(lastEnigma);
    }

    private Box createMainPanel() {
        Box box = Box.createVerticalBox();
        box.setBorder(new EmptyBorder(10, 10, 10, 10));
        box.add(createMachinePanel());
        box.add(Box.createVerticalStrut(5));
        box.add(createReflectorPanel());
        box.add(Box.createVerticalStrut(5));
        addRegularLabel(box, "Rotors:");
        box.add(createRotorPanel());
        box.add(Box.createVerticalStrut(5));
        addRegularLabel(box, "Offset:");
        box.add(createOffsetPanel());
        box.add(Box.createVerticalStrut(5));
        addRegularLabel(box, "Plugboard:");
        box.add(createPlugboardPanel());
        box.add(Box.createVerticalStrut(2));
        box.add(createButtonPanel());
        return box;
    }

    private Box createMachinePanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        box.add(new JLabel("Machine:"));
        box.add(Box.createHorizontalStrut(11));
        m3Radio = new JRadioButton("Enigma M3");
        m3Radio.addActionListener(e -> changeMachineType(false));
        box.add(m3Radio);
        box.add(Box.createHorizontalStrut(5));
        m4Radio = new JRadioButton("Enigma M4");
        m4Radio.addActionListener(e -> changeMachineType(true));
        box.add(m4Radio);
        createRadioButtonGroup();
        return box;
    }

    private void createRadioButtonGroup() {
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(m3Radio);
        radioGroup.add(m4Radio);
    }

    private Box createReflectorPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        box.add(new JLabel("Reflector:"));
        box.add(Box.createHorizontalStrut(10));
        reflectorList = new JComboBox<Reflector>();
        reflectorList.setMaximumSize(new Dimension(80, 32));
        box.add(reflectorList);
        return box;
    }

    private Box createRotorPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        rotor1List = new JComboBox<Rotor>(Rotor.createM4GreekRotors());
        rotor1List.setMaximumSize(new Dimension(80, 32));
        box.add(rotor1List);
        box.add(Box.createHorizontalStrut(5));
        Rotor[] m3rotors = Rotor.createM3Rotors();
        rotor2List = new JComboBox<Rotor>(m3rotors);
        rotor2List.setMaximumSize(new Dimension(80, 32));
        box.add(rotor2List);
        box.add(Box.createHorizontalStrut(5));
        rotor3List = new JComboBox<Rotor>(m3rotors);
        rotor3List.setMaximumSize(new Dimension(80, 32));
        box.add(rotor3List);
        box.add(Box.createHorizontalStrut(5));
        rotor4List = new JComboBox<Rotor>(m3rotors);
        rotor4List.setMaximumSize(new Dimension(80, 32));
        box.add(rotor4List);
        return box;
    }

    private Box createOffsetPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        offset1List = createOffsetDropdown();
        offset1List.setMaximumSize(new Dimension(80, 32));
        box.add(offset1List);
        box.add(Box.createHorizontalStrut(5));
        offset2List = createOffsetDropdown();
        offset2List.setMaximumSize(new Dimension(80, 32));
        box.add(offset2List);
        box.add(Box.createHorizontalStrut(5));
        offset3List = createOffsetDropdown();
        offset3List.setMaximumSize(new Dimension(80, 32));
        box.add(offset3List);
        box.add(Box.createHorizontalStrut(5));
        offset4List = createOffsetDropdown();
        offset4List.setMaximumSize(new Dimension(80, 32));
        box.add(offset4List);
        return box;
    }

    private Box createPlugboardPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        box.add(createPlugboardList(NUM_PLUGS_LEFT));
        box.add(Box.createHorizontalStrut(5));
        box.add(createPlugboardList(NUM_PLUGS_RIGHT));
        return box;
    }

	private Box createPlugboardList(int numPlugs) {
        Box box = Box.createVerticalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        for (int i = 0; i < numPlugs; i++) {
            box.add(createPlugboardDropdownPair());
            box.add(Box.createVerticalStrut(3));
        }
        if (numPlugs == NUM_PLUGS_RIGHT) { box.add(Box.createVerticalStrut(28)); }
        return box;
    }

    private Box createPlugboardDropdownPair() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        JComboBox<Character> left = createPlugboardDropdown();
        left.setMaximumSize(new Dimension(70, 32));
        box.add(left);
        box.add(Box.createHorizontalStrut(3));
        box.add(new JLabel("\u2194"));
        box.add(Box.createHorizontalStrut(3));
        JComboBox<Character> right = createPlugboardDropdown();
        right.setMaximumSize(new Dimension(70, 32));
        plugboardList.add(new Pair<JComboBox<Character>, JComboBox<Character>>(left, right));
        box.add(right);
        return box;
    }

    private Box createButtonPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> applyChanges());
        box.add(applyButton);
        box.add(Box.createHorizontalStrut(5));
        JButton clearButton = new JButton("Clear Plugboard");
        clearButton.addActionListener(e -> clearPlugboardConfiguration());
        box.add(clearButton);
        box.add(Box.createHorizontalStrut(5));
        JButton resetButton = new JButton("Reset Settings");
        resetButton.addActionListener(e -> loadConfiguration(lastEnigma));
        box.add(resetButton);
        return box;
    }

    /**
	 * Loads the configuration of an Enigma machine.
	 */
	private void loadConfiguration(Enigma enigma) {
        loadMachineConfiguration(enigma);
        loadReflectorConfiguration(enigma);
        loadRotorConfiguration(enigma);
        loadOffsetConfiguration(enigma);
        loadPlugboardConfiguration(enigma);
	}

	private void loadMachineConfiguration(Enigma enigma) {
        m4Active = enigma instanceof EnigmaM4;
        reflectorList.removeAllItems();
        if (m4Active) { updateReflectorList(Reflector.buildM4ThinReflectors()); }
        else { updateReflectorList(Reflector.createM3Reflectors()); }
        currEnigma = enigma;
        SwingUtilities.invokeLater(() -> {
            m3Radio.setSelected(!m4Active);
            m4Radio.setSelected(m4Active);
        });
    }

    private void updateReflectorList(Reflector[] reflectors) {
        for (final Reflector reflector : reflectors) {
            SwingUtilities.invokeLater(() -> reflectorList.addItem(reflector));
        }
    }

    private void loadReflectorConfiguration(Enigma enigma) {
        SwingUtilities.invokeLater(() -> {
            if (m4Active) { reflectorList.setSelectedItem(((EnigmaM4) enigma).getThinReflector()); }
            else { reflectorList.setSelectedItem(((EnigmaM3) enigma).getReflector()); }
        });
    }

    private void loadRotorConfiguration(final Enigma enigma) {
        SwingUtilities.invokeLater(() -> {
            if (m4Active) {
                rotor1List.setSelectedItem(((EnigmaM4) enigma).getGreekRotor());
                rotor1List.setEnabled(true);
            } else {
                rotor1List.setSelectedItem(null);
                rotor1List.setEnabled(false);
            }
            rotor2List.setSelectedItem(enigma.getLeftRotor());
            rotor3List.setSelectedItem(enigma.getMiddleRotor());
            rotor4List.setSelectedItem(enigma.getRightRotor());
        });
    }

    private void loadOffsetConfiguration(final Enigma enigma) {
        SwingUtilities.invokeLater(() -> {
            if (m4Active) {
                offset1List.setSelectedIndex(((EnigmaM4) enigma).getGreekRotor().getOffset());
                offset1List.setEnabled(true);
            } else {
                offset1List.setSelectedIndex(-1);
                offset1List.setEnabled(false);
            }
            offset2List.setSelectedIndex(enigma.getLeftRotor().getOffset());
            offset3List.setSelectedIndex(enigma.getMiddleRotor().getOffset());
            offset4List.setSelectedIndex(enigma.getRightRotor().getOffset());
        });
    }

    private void loadPlugboardConfiguration(Enigma enigma) {
        clearPlugboardConfiguration();
        List<Integer> alreadyPlugged = new ArrayList<Integer>(NUM_SYMBOLS);
        int pos = 0;
        for (int l = 0; l < NUM_SYMBOLS; l++) {
            if (enigma.getPlugboard().isPlugged(l) && !alreadyPlugged.contains(l)) {
                Pair<JComboBox<Character>, JComboBox<Character>> p = plugboardList.get(pos);
                int r = enigma.getPlugboard().swappedWith(l);
                loadSpecificPlug(p, Original.toChar(l), Original.toChar(r));
                alreadyPlugged.add(r);
                pos++;
            }
        }
    }

    private void loadSpecificPlug(final Pair<JComboBox<Character>, JComboBox<Character>> p,
                                  final char l,
                                  final char r) {
        SwingUtilities.invokeLater(() -> {
            p.getKey().setSelectedItem(l);
            p.getValue().setSelectedItem(r);
        });
    }

    private void clearPlugboardConfiguration() {
        for (Pair<JComboBox<Character>, JComboBox<Character>> p : plugboardList) {
            loadSpecificPlug(p, UNSELECTED, UNSELECTED);
        }
    }

    private void applyChanges() {
        if (validateConfiguration()) { storeChanges(); }
        else { showWarningForInvalidConfiguration(); }
    }

    private void showWarningForInvalidConfiguration() {
        JOptionPane.showMessageDialog(this,
                "<html>Invalid configuration!"
                        + "<br>Please check your rotor selection"
                        + "<br>and plugboard configuration.</html>",
                "Invalid configuration!",
                JOptionPane.WARNING_MESSAGE);
    }

    private void storeChanges() {
        storeReflectorConfiguration();
        storeRotorConfiguration();
        storeOffsetConfiguration();
        storePlugboardConfiguration();
        MainWindow.instance().updateMachine(currEnigma);
        SwingUtilities.invokeLater(this::dispose);
    }

    private void storeReflectorConfiguration() {
        if (m4Active) { ((EnigmaM4) currEnigma).setThinReflector((Reflector) reflectorList.getSelectedItem()); }
        else { ((EnigmaM3) currEnigma).setReflector((Reflector) reflectorList.getSelectedItem()); }
    }

    private void storeRotorConfiguration() {
        if (m4Active) { ((EnigmaM4) currEnigma).setGreekRotor((Rotor) rotor1List.getSelectedItem()); }
        currEnigma.setLeftRotor((Rotor) rotor2List.getSelectedItem());
        currEnigma.setMiddleRotor((Rotor) rotor3List.getSelectedItem());
        currEnigma.setRightRotor((Rotor) rotor4List.getSelectedItem());
    }

    private void storeOffsetConfiguration() {
        if (m4Active) {
            ((EnigmaM4) currEnigma).getGreekRotor().setOffset(extractPositionFromOffsetDropdown(offset1List));
        }
        currEnigma.getLeftRotor().setOffset(extractPositionFromOffsetDropdown(offset2List));
        currEnigma.getMiddleRotor().setOffset(extractPositionFromOffsetDropdown(offset3List));
        currEnigma.getRightRotor().setOffset(extractPositionFromOffsetDropdown(offset4List));
    }

    private void storePlugboardConfiguration() {
        currEnigma.getPlugboard().removeAllCables();
        for (Pair<JComboBox<Character>, JComboBox<Character>> p : plugboardList) {
            char l = (char) p.getKey().getSelectedItem();
            char r = (char) p.getValue().getSelectedItem();
            if (l == UNSELECTED && r == UNSELECTED) { continue; }
            try { currEnigma.getPlugboard().addCable(Original.toInt(l), Original.toInt(r)); }
            catch (NoSuchSymbolException e) { e.printStackTrace(); }
        }
    }

    /**
	 * Tests whether or not the configuration is valid.
	 * @return true, if the configuration is valid, otherwise false
	 */
	private boolean validateConfiguration() {
        return validateRotorConfiguration() && validatePlugboard();
    }

    private boolean validateRotorConfiguration() {
        if (rotor2List.getSelectedItem().equals(rotor3List.getSelectedItem())) { return false; }
        else if (rotor3List.getSelectedItem().equals(rotor4List.getSelectedItem())) { return false; }
        else if (rotor2List.getSelectedItem().equals(rotor4List.getSelectedItem())) { return false; }
        return true;
    }

    private boolean validatePlugboard() {
        boolean[] symbols = new boolean[NUM_SYMBOLS];
        for (Pair<JComboBox<Character>, JComboBox<Character>> p : plugboardList) {
            char l = (char) p.getKey().getSelectedItem();
            char r = (char) p.getValue().getSelectedItem();
            if (l == UNSELECTED && r == UNSELECTED) { continue; }
            if (l == UNSELECTED || r == UNSELECTED) { return false; }
            if (l == r) { return false; }
            if (!validatePlugThatSymbolIsOnlyUsedOnce(symbols, l)) { return false; }
            if (!validatePlugThatSymbolIsOnlyUsedOnce(symbols, r)) { return false; }
        }
        return true;
    }

    private boolean validatePlugThatSymbolIsOnlyUsedOnce(boolean[] symbols, char c) {
        try {
            int n = Original.toInt(c);
            if (symbols[n]) { return false; }
            symbols[n] = true;
        } catch (NoSuchSymbolException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void changeMachineType(final boolean m4Active) {
		this.m4Active = m4Active;
		if (m4Active) { currEnigma = new EnigmaM4(); }
		else { currEnigma = new EnigmaM3(); }
		loadConfiguration(currEnigma);
	}

    private static void addRegularLabel(Box box, String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        box.add(label);
    }

    private static JComboBox<String> createOffsetDropdown() {
        JComboBox<String> dropdown = new JComboBox<String>();
        for (int i = 0; i < NUM_SYMBOLS; i++) { dropdown.addItem(Original.toChar(i) + " - " + (i + 1)); }
        return dropdown;
    }

	private static JComboBox<Character> createPlugboardDropdown() {
        JComboBox<Character> dropdown = new JComboBox<Character>();
		dropdown.addItem(UNSELECTED);
		for (char c : Original.ORIGINAL) { dropdown.addItem(c); }
        return dropdown;
	}

	private static int extractPositionFromOffsetDropdown(JComboBox<String> dropdown) {
        int result = -1;
        try {
            result = Original.toInt(((String) dropdown.getSelectedItem()).charAt(0));
        } catch (NoSuchSymbolException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void showDialog(final boolean m4Active, final Enigma enigma) {
		SwingUtilities.invokeLater(() -> new ConfigDialog(m4Active, enigma).setVisible(true));
	}
	
}
