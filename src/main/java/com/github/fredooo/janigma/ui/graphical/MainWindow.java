package com.github.fredooo.janigma.ui.graphical;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.github.fredooo.janigma.io.FileIo;
import com.github.fredooo.janigma.core.machine.Enigma;
import com.github.fredooo.janigma.core.machine.EnigmaM3;
import com.github.fredooo.janigma.core.machine.EnigmaM4;
import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;

/**
 * Provides the main window of the application.
 * @author Frederik Dennig
 * @since 2013-12-13
 * @version 0.0.4 (last revised 2017-12-02)
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private static MainWindow instance;
	
	private Enigma enigma;
	private boolean m4Active;
	
	private JLabel machineLabel;
	
	private JButton rotor1UpButton;
	private JButton rotor2UpButton;
	private JButton rotor3UpButton;
	private JButton rotor4UpButton;
	
	private JTextField rotor1Field;
	private JTextField rotor2Field;
	private JTextField rotor3Field;
	private JTextField rotor4Field;
	
	private JButton rotor1DownButton;
	private JButton rotor2DownButton;
	private JButton rotor3DownButton;
	private JButton rotor4DownButton;
	
	private JTextArea inputText;
	private JTextArea outputText;
	
	private JButton enDecryptButton;

	/**
	 * Creates the main window of the program.
	 */
	public MainWindow() {
		this.setTitle("Janigma");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		this.setJMenuBar(createMenuBar());
        this.setContentPane(createMainPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.updateMachine(new EnigmaM3());
	}

    private Box createMainPanel() {
        Box box = Box.createVerticalBox();
        box.setBorder(new EmptyBorder(10, 10, 10, 10));
        box.add(createMachineLabel());
        box.add(Box.createVerticalStrut(5));
        box.add(createRotorUpButtonPanel());
        box.add(createRotorFieldPanel());
        box.add(createRotorDownButtonPanel());
        box.add(Box.createVerticalStrut(5));
        addRegularLabel(box, "Input:");
        box.add(createInputTextField());
        box.add(Box.createVerticalStrut(5));
        addRegularLabel(box, "Output:");
        box.add(createOutputTextField());
        box.add(Box.createVerticalStrut(5));
        box.add(createActionButtonPanel());
        return box;
    }

    private JLabel createMachineLabel() {
        machineLabel = new JLabel("- Enigma M3 -");
        machineLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        machineLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        return machineLabel;
    }

    private Box createRotorUpButtonPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        box.add(Box.createHorizontalGlue());
        rotor1UpButton = new JButton("\u25B2");
        rotor1UpButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor1UpButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor1UpButton);
        box.add(Box.createHorizontalStrut(5));
        rotor2UpButton = new JButton("\u25B2");
        rotor2UpButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor2UpButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor2UpButton);
        box.add(Box.createHorizontalStrut(5));
        rotor3UpButton = new JButton("\u25B2");
        rotor3UpButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor3UpButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor3UpButton);
        box.add(Box.createHorizontalStrut(5));
        rotor4UpButton = new JButton("\u25B2");
        rotor4UpButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor4UpButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor4UpButton);
        box.add(Box.createHorizontalGlue());
        return box;
	}

    private Box createRotorFieldPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        rotor1Field = new JTextField();
        rotor1Field.setEditable(false);
        rotor1Field.setHorizontalAlignment(JTextField.CENTER);
        rotor1Field.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        rotor1Field.setMaximumSize(new Dimension(60, 60));
        box.add(rotor1Field);
        box.add(Box.createHorizontalStrut(5));
        rotor2Field = new JTextField();
        rotor2Field.setEditable(false);
        rotor2Field.setHorizontalAlignment(JTextField.CENTER);
        rotor2Field.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        rotor2Field.setMaximumSize(new Dimension(60, 60));
        box.add(rotor2Field);
        box.add(Box.createHorizontalStrut(5));
        rotor3Field = new JTextField();
        rotor3Field.setEditable(false);
        rotor3Field.setHorizontalAlignment(JTextField.CENTER);
        rotor3Field.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        rotor3Field.setMaximumSize(new Dimension(60, 60));
        box.add(rotor3Field);
        box.add(Box.createHorizontalStrut(5));
        rotor4Field = new JTextField();
        rotor4Field.setEditable(false);
        rotor4Field.setHorizontalAlignment(JTextField.CENTER);
        rotor4Field.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        rotor4Field.setMaximumSize(new Dimension(60, 60));
        box.add(rotor4Field);
        return box;
    }

	private Box createRotorDownButtonPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        box.add(Box.createHorizontalGlue());
        rotor1DownButton = new JButton("\u25BC");
        rotor1DownButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor1DownButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor1DownButton);
        box.add(Box.createHorizontalStrut(5));
        rotor2DownButton = new JButton("\u25BC");
        rotor2DownButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor2DownButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor2DownButton);
        box.add(Box.createHorizontalStrut(5));
        rotor3DownButton = new JButton("\u25BC");
        rotor3DownButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor3DownButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor3DownButton);
        box.add(Box.createHorizontalStrut(5));
        rotor4DownButton = new JButton("\u25BC");
        rotor4DownButton.addActionListener(this::performActionOnEnigmaMachine);
        rotor4DownButton.setPreferredSize(new Dimension(60, 30));
        box.add(rotor4DownButton);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    private JScrollPane createInputTextField() {
        inputText = new JTextArea();
        inputText.setLineWrap(true);
        final Font oldFont = inputText.getFont();
        inputText.setFont(new Font(oldFont.getFamily(), oldFont.getStyle(), 12));
        JScrollPane inputScrollPane = new JScrollPane(inputText);
        inputScrollPane.setPreferredSize(new Dimension(250, 150));
        inputScrollPane.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
        return inputScrollPane;
    }

    private JScrollPane createOutputTextField() {
        outputText = new JTextArea();
        outputText.setEditable(false);
        outputText.setLineWrap(true);
        final Font oldFont = outputText.getFont();
        outputText.setFont(new Font(oldFont.getFamily(), oldFont.getStyle(), 12));
        JScrollPane outputScrollPane = new JScrollPane(outputText);
        outputScrollPane.setPreferredSize(new Dimension(250, 150));
        outputScrollPane.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
        return outputScrollPane;
    }

    private Box createActionButtonPanel() {
        Box box = Box.createHorizontalBox();
        box.setAlignmentX(Box.LEFT_ALIGNMENT);
        enDecryptButton = new JButton("En-/Decrypt");
        enDecryptButton.addActionListener(this::performActionOnEnigmaMachine);
        box.add(enDecryptButton);
        box.add(Box.createHorizontalGlue());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearInputAndOutputFields());
        box.add(clearButton);
        return box;
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
		SwingUtilities.invokeLater(() -> {
			if (m4Active) {
				int position = ((EnigmaM4) enigma).getGreekRotor().getPosition();
				rotor1Field.setText(String.valueOf(Original.toChar((position))));
			}
			rotor2Field.setText(String.valueOf(Original.toChar(enigma.getLeftRotor().getPosition())));
			rotor3Field.setText(String.valueOf(Original.toChar(enigma.getMiddleRotor().getPosition())));
			rotor4Field.setText(String.valueOf(Original.toChar(enigma.getRightRotor().getPosition())));
		});
	}
	
	/**
	 * Changes the Enigma machine to a given one.
	 * @param enigma the Enigma machine
	 */
	public void updateMachine(Enigma enigma) {
        if (enigma == null) { return; }
		this.enigma = enigma;
		this.m4Active = enigma instanceof EnigmaM4;
		this.enableEnigmaM4(this.m4Active);
        this.updateRotors();
    }

	private void enableEnigmaM4(final boolean enabled) {
		SwingUtilities.invokeLater(() -> {
			this.machineLabel.setText(enabled ? "- Enigma M4 -" : "- Enigma M3 -");
			this.rotor1UpButton.setEnabled(enabled);
            if (!enabled) { this.rotor1Field.setText(""); }
            this.rotor1DownButton.setEnabled(enabled);
		});
	}

	private void performActionOnEnigmaMachine(ActionEvent e) {
		// Up buttons
		if (e.getSource().equals(rotor1UpButton)) { ((EnigmaM4) enigma).getGreekRotor().incrementPosition(); }
		else if (e.getSource().equals(rotor2UpButton)) { enigma.getLeftRotor().incrementPosition(); }
		else if (e.getSource().equals(rotor3UpButton)) { enigma.getMiddleRotor().incrementPosition(); }
		else if (e.getSource().equals(rotor4UpButton)) { enigma.getRightRotor().incrementPosition(); }
		// Down buttons
		else if (e.getSource().equals(rotor1DownButton)) { ((EnigmaM4) enigma).getGreekRotor().decrementPosition(); }
		else if (e.getSource().equals(rotor2DownButton)) { enigma.getLeftRotor().decrementPosition(); }
		else if (e.getSource().equals(rotor3DownButton)) { enigma.getMiddleRotor().decrementPosition(); }
		else if (e.getSource().equals(rotor4DownButton)) { enigma.getRightRotor().decrementPosition(); }
		// En-/Decrypt button
		else if (e.getSource().equals(enDecryptButton)) { checkInputAndPerformEnDecryption(); }
		// Always update rotor positions
		updateRotors();
	}

	private void checkInputAndPerformEnDecryption() {
		final String input = inputText.getText();
		if (Original.isValidString(input)) {
			performEnDecryption(input);
		} else {
			showWarningForInvalidInput();
		}
	}

	private void performEnDecryption(String input) {
		try {
			final String result = enigma.use(input);
            SwingUtilities.invokeLater(() -> outputText.setText(result));
        } catch (NoSuchSymbolException e) {
            e.printStackTrace();
        }
	}

	private void showWarningForInvalidInput() {
		JOptionPane.showMessageDialog(this,
                "<html>Invalid character!"
                        + "<br>Please check your input text."
                        + "<br>Allowed characters are A-Z and a-z.</html>",
                "Invalid character!",
                JOptionPane.WARNING_MESSAGE);
	}

	private void clearInputAndOutputFields() {
		SwingUtilities.invokeLater(() -> {
			inputText.setText("");
			outputText.setText("");
		});
	}

	/**
	 * Creates the menu bar.
	 * @return the created menu bar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createConfigurationSubMenu());
		menuBar.add(createHelpSubMenu());
		return menuBar;
	}

	private JMenu createConfigurationSubMenu() {
		JMenu configMenu = new JMenu("Configuration");
		JMenuItem openConfigItem = new JMenuItem("Open");
		openConfigItem.addActionListener(e -> ConfigDialog.showDialog(m4Active, enigma));
		configMenu.add(openConfigItem);
		JMenuItem saveConfigItem = new JMenuItem("Save");
		saveConfigItem.addActionListener(e -> FileIo.saveEnigmaMachine(enigma));
		configMenu.add(saveConfigItem);
		JMenuItem loadConfigItem = new JMenuItem("Load");
		loadConfigItem.addActionListener(e -> updateMachine(FileIo.loadEnigmaMachine()));
        configMenu.add(loadConfigItem);
		return configMenu;
	}

	private JMenu createHelpSubMenu() {
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutDialogItem = new JMenuItem("About");
		aboutDialogItem.addActionListener(e -> AboutDialog.showDialog());
		helpMenu.add(aboutDialogItem);
		return helpMenu;
	}

	private static void addRegularLabel(Box box, String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        box.add(label);
    }

    public static void showWindow() {
        SwingUtilities.invokeLater(() -> MainWindow.instance().setVisible(true));
    }

}
