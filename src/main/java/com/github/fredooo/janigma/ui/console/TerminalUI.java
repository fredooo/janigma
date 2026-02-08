package com.github.fredooo.janigma.ui.console;

import java.util.ArrayList;

import com.github.fredooo.janigma.core.machine.Enigma;
import com.github.fredooo.janigma.core.machine.EnigmaM3;
import com.github.fredooo.janigma.core.machine.EnigmaM4;
import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Provides the console based UI of the application.
 * @author Frederik Dennig
 * @since 2013-02-21
 * @version 0.0.6
 */
public class TerminalUI {
	
	private static final char[][] SYMBOLS = {
			{ 'Q', 'W', 'E', 'R' ,'T', 'Z', 'U', 'I', 'O' },
			{ 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K' },
			{ 'P', 'Y', 'X', 'C', 'V', 'B', 'N', 'M', 'L' }
		};
	
	private Enigma enigma;
	private boolean m4Active;
	
	private Screen screen;
	private boolean running;
	private char inputChar;
	private char outputChar;
	private String inputText;
	private String outputText;
	private boolean config;
	
	/**
	 * Create the TerminalUI object.
	 */
	public TerminalUI() {
		this.enigma = new EnigmaM3();

		try {
			DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
			Terminal terminal = terminalFactory.createTerminal();
			this.screen = new TerminalScreen(terminal);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create terminal", e);
		}

		this.running = true;
		this.inputText = "";
		this.outputText = "";
		this.config = false;
	}
	
	/**
	 * Starts the console based UI.
	 */
	public void run() {
		try {
			screen.startScreen();
			updateScreen();
			while (running) {
				KeyStroke key = screen.readInput();
				if (key != null && key.getCharacter() != null) {
					char c = key.getCharacter();
					if (config) {
						configKeyAction(c);
					} else {
						mainViewKeyAction(c);
					}
					updateScreen();
				}
			}
			screen.stopScreen();
		} catch (Exception e) {
			throw new RuntimeException("Terminal error", e);
		}
	}

	/**
	 * Invokes actions based on a character of a keypress. 
	 * @param c the character of the keypress
	 */
	private void mainViewKeyAction(char c) {
		switch (c) {
		case '1':
			config = true;
			break;
		case '2':
			inputText = "";
			outputText = "";
			break;
		case '3':
			running = false;
			break;
		default:
			if (inputText.length() >= 210) {
				inputText = "";
				outputText = "";
			}			
			if (Original.isValidChar(c)) {
				inputChar = Character.toUpperCase(c);
				inputText += inputChar;
				try {
					outputChar = enigma.use(c);
					outputText += outputChar;
				} catch (NoSuchSymbolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
	}
	
	private void configKeyAction(char c) {
		switch (c) {
		case '1':
			changeEnigmaMachine();
			break;
		case '2':

			break;
		case '3':

			break;
		case '4':

			break;
		case '5':

			break;
		case '6':

			break;
		case '7':
			config = false;
			break;
		default:
			
			break;
		}
	}
	
	private void updateScreen() throws java.io.IOException {
		screen.clear();
		if (config) {
			showConfig();
		} else {
			showMainView();
		}
		screen.refresh();
	}
	
	private void showMainView() {
		TextGraphics tg = screen.newTextGraphics();
		tg.setForegroundColor(TextColor.ANSI.WHITE);
		tg.setBackgroundColor(TextColor.ANSI.BLACK);

		tg.putString(1, 0, "[1] Configuration [2] Clear [3] Exit");
		drawMachineBorders();
		tg.putString(3, 2, enigma.toString());
		if (m4Active) {
			showM4MainView();
		} else {
			showM3MainView();
		}

		printSymbols(8, outputChar);
		printSymbols(12, inputChar);

		tg.putString(26, 2, "Input:");
		tg.putString(26, 9, "Output:");

		String[] formattedInput = formatInputOutput(inputText);
		String[] formattedOutput = formatInputOutput(outputText);
		for (int i = 0; i < formattedInput.length; i++) {
			tg.putString(26, 3 + i, formattedInput[i]);
			tg.putString(26, 10 + i, formattedOutput[i]);
		}
	}
	
	private void drawMachineBorders() {
		TextGraphics tg = screen.newTextGraphics();
		tg.setForegroundColor(TextColor.ANSI.WHITE);
		tg.setBackgroundColor(TextColor.ANSI.BLACK);

		for (int i = 2; i < 15; i++) {
			tg.putString(1, i, "|");
			tg.putString(24, i, "|");
		}
		tg.putString(1, 1, "+----------------------+");
		tg.putString(1, 7, "+----------------------+");
		tg.putString(1, 11, "+----------------------+");
		tg.putString(1, 15, "+----------------------+");
	}
	
	private void printSymbols(int line, char active) {
		TextGraphics tg = screen.newTextGraphics();

		for (int i = 0; i < 3; i++) {
			int x = i % 2 == 0 ? 4 : 5;
			for (char c : SYMBOLS[i]) {
				if (c == active) {
					tg.setForegroundColor(TextColor.ANSI.BLACK);
					tg.setBackgroundColor(TextColor.ANSI.WHITE);
				} else {
					tg.setForegroundColor(TextColor.ANSI.WHITE);
					tg.setBackgroundColor(TextColor.ANSI.BLACK);
				}
				tg.putString(x, line + i, String.valueOf(c));
				x += 2;
			}
		}
	}
	
	private void showM3MainView() {
		TextGraphics tg = screen.newTextGraphics();
		tg.setForegroundColor(TextColor.ANSI.WHITE);
		tg.setBackgroundColor(TextColor.ANSI.BLACK);

		EnigmaM3 m3 = (EnigmaM3) enigma;
		char leftRotor = Original.toChar(m3.getLeftRotor().getPosition());
		char middleRotor = Original.toChar(m3.getMiddleRotor().getPosition());
		char rightRotor = Original.toChar(m3.getRightRotor().getPosition());
		tg.putString(8, 4, "+---++---++---+");
		tg.putString(8, 5, "| " + leftRotor + " || " + middleRotor + " || " + rightRotor + " |");
		tg.putString(8, 6, "+---++---++---+");
	}
	
	private void showM4MainView() {
		TextGraphics tg = screen.newTextGraphics();
		tg.setForegroundColor(TextColor.ANSI.WHITE);
		tg.setBackgroundColor(TextColor.ANSI.BLACK);

		EnigmaM4 m4 = (EnigmaM4) enigma;
		char greekRotor = Original.toChar(m4.getGreekRotor().getPosition());
		char leftRotor = Original.toChar(m4.getLeftRotor().getPosition());
		char middleRotor = Original.toChar(m4.getMiddleRotor().getPosition());
		char rightRotor = Original.toChar(m4.getRightRotor().getPosition());
		tg.putString(3, 4, "+---++---++---++---+");
		tg.putString(3, 5, "| " + greekRotor + " || " + leftRotor + " || " + middleRotor + " || " + rightRotor + " |");
		tg.putString(3, 6, "+---++---++---++---+");
	}
	
	private void changeEnigmaMachine() {
		m4Active = !m4Active;
		if (m4Active) {
			enigma = new EnigmaM4();
		} else {
			enigma = new EnigmaM3();
		}
	}
	
	private static String[] formatInputOutput(String text) {
		StringBuilder stringBuilder = new StringBuilder(text);
		int pos = 5;
		int i = 0;
		while (pos < text.length()) {
			stringBuilder.insert(pos + i, ' ');	
			pos += 5;
			i++;
		}		
		pos = 41;
		while (pos < stringBuilder.length()) {
			stringBuilder.replace(pos, pos + 1, "-");	
			pos += 42;
		}		
		return stringBuilder.toString().split("-");
	}
	
	private void showConfig() {
		TextGraphics tg = screen.newTextGraphics();
		tg.setForegroundColor(TextColor.ANSI.WHITE);
		tg.setBackgroundColor(TextColor.ANSI.BLACK);

		tg.putString(1, 1, "[1] Change Machine");
		tg.putString(1, 2, "[2] Set Reflector");
		tg.putString(1, 3, "[3] Set Rotor");
		tg.putString(1, 4, "[4] Set Rotor Offset");
		tg.putString(1, 5, "[5] Add Cable");
		tg.putString(1, 6, "[6] Remove Cable");
		tg.putString(1, 8, "[7] Close");

		String reflector = m4Active ? ((EnigmaM4) enigma).getThinReflector().toString() : ((EnigmaM3) enigma).getReflector().toString();
		String greekRotor = m4Active ? ((EnigmaM4) enigma).getGreekRotor() + " " : "";

		int leftOffset = enigma.getLeftRotor().getOffset() + 1;
		int middleOffset = enigma.getMiddleRotor().getOffset() + 1;
		int rightOffset = enigma.getRightRotor().getOffset() + 1;
		tg.putString(30, 1, "Machine: " + enigma);
		tg.putString(30, 2, "Reflector: " + reflector);
		tg.putString(30, 3, "Rotors: " + greekRotor + enigma.getLeftRotor() + " " + enigma.getMiddleRotor() + " " + enigma.getRightRotor());
		tg.putString(30, 4, "Offset: " + leftOffset + " " + middleOffset + " " + rightOffset);
		tg.putString(30, 5, "Plugboard:");

		ArrayList<Integer> noShow = new ArrayList<Integer>();
		int line = 5;
		for (int i = 0; i < 26; i++) {
			if (enigma.getPlugboard().isPlugged(i) && !noShow.contains(i)) {
				int s = enigma.getPlugboard().swappedWith(i);
				noShow.add(s);
				char c1 = Original.toChar(i);
				char c2 = Original.toChar(enigma.getPlugboard().swappedWith(i));
				tg.putString(12, line, c1 + " -===- " + c2);
				line++;
			}
		}
	}
	
}
