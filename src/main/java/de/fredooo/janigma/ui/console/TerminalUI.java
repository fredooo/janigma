package de.fredooo.janigma.ui.console;

import java.util.ArrayList;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;

import de.fredooo.janigma.machine.Enigma;
import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.EnigmaM4;
import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

/**
 * Provides the console based UI of the application.
 * @author Frederik Dennig
 * @since 2013-02-21
 * @version 0.0.1 (last revised 2016-02-25)
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
		this.enigma = EnigmaM3.instance();
		Terminal terminal = TerminalFacade.createUnixTerminal();
		this.screen = TerminalFacade.createScreen();
		this.running = true;
		this.inputText = "";
		this.outputText = "";
		this.config = false;
	}
	
	/**
	 * Starts the console based UI.
	 */
	public void run() {
		screen.startScreen();
		updateScreen();
		while (running) {
			Key key = screen.readInput();
			if (key != null) {
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
	
	private void updateScreen() {
		screen.clear();
		if (config) {
			showConfig();
		} else {
			showMainView();
		}
		screen.refresh();
	}
	
	private void showMainView() {
		screen.putString(1, 0, "[1] Configuration [2] Clear [3] Exit", Color.WHITE, Color.BLACK);
		drawMachineBorders();
		screen.putString(3, 2, enigma.toString(), Color.WHITE, Color.BLACK);
		if (m4Active) {
			showM4MainView();
		} else {
			showM3MainView();
		}
		
		printSymbols(8, outputChar);
		printSymbols(12, inputChar);
		
		screen.putString(26, 2, "Input:", Color.WHITE, Color.BLACK);
		screen.putString(26, 9, "Output:", Color.WHITE, Color.BLACK);
		
		String[] formattedInput = formatInputOutput(inputText);
		String[] formattedOutput = formatInputOutput(outputText);
		for (int i = 0; i < formattedInput.length; i++) {
			screen.putString(26, 3 + i, formattedInput[i], Color.WHITE, Color.BLACK);
			screen.putString(26, 10 + i, formattedOutput[i], Color.WHITE, Color.BLACK);
		}		
	}
	
	private void drawMachineBorders() {
		for (int i = 2; i < 15; i++) {
			screen.putString(1, i, "|", Color.WHITE, Color.BLACK);
			screen.putString(24, i, "|", Color.WHITE, Color.BLACK);
		}
		screen.putString(1, 1, "+----------------------+", Color.WHITE, Color.BLACK);
		screen.putString(1, 7, "+----------------------+", Color.WHITE, Color.BLACK);
		screen.putString(1, 11, "+----------------------+", Color.WHITE, Color.BLACK);
		screen.putString(1, 15, "+----------------------+", Color.WHITE, Color.BLACK);
	}
	
	private void printSymbols(int line, char active) {
		for (int i = 0; i < 3; i++) {
			int x = i % 2 == 0 ? 4 : 5;
			for (char c : SYMBOLS[i]) {
				if (c == active) {
					screen.putString(x, line + i, String.valueOf(c), Color.BLACK, Color.WHITE);
				} else {
					screen.putString(x, line + i, String.valueOf(c), Color.WHITE, Color.BLACK);
				}
				x += 2;
			}
		}
	}
	
	private void showM3MainView() {
		EnigmaM3 m3 = (EnigmaM3) enigma;
		char leftRotor = Original.toChar(m3.getLeftRotor().getPosition());
		char middleRotor = Original.toChar(m3.getMiddleRotor().getPosition());
		char rightRotor = Original.toChar(m3.getRightRotor().getPosition());
		screen.putString(8, 4, "+---++---++---+", Color.WHITE, Color.BLACK);
		screen.putString(8, 5, "| " + leftRotor + " || " + middleRotor + " || " + rightRotor + " |", Color.WHITE, Color.BLACK);
		screen.putString(8, 6, "+---++---++---+", Color.WHITE, Color.BLACK);
	}
	
	private void showM4MainView() {
		EnigmaM4 m4 = (EnigmaM4) enigma;
		char greekRotor = Original.toChar(m4.getGreekRotor().getPosition());
		char leftRotor = Original.toChar(m4.getLeftRotor().getPosition());
		char middleRotor = Original.toChar(m4.getMiddleRotor().getPosition());
		char rightRotor = Original.toChar(m4.getRightRotor().getPosition());
		screen.putString(3, 4, "+---++---++---++---+", Color.WHITE, Color.BLACK);
		screen.putString(3, 5, "| " + greekRotor + " || " + leftRotor + " || " + middleRotor + " || " + rightRotor + " |", Color.WHITE, Color.BLACK);
		screen.putString(3, 6, "+---++---++---++---+", Color.WHITE, Color.BLACK);
	}
	
	private void changeEnigmaMachine() {
		m4Active = !m4Active;
		if (m4Active) {
			enigma = EnigmaM4.instance();
		} else {
			enigma = EnigmaM3.instance();
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
		screen.putString(1, 1, "[1] Change Machine", Color.WHITE, Color.BLACK);
		screen.putString(1, 2, "[2] Set Reflector", Color.WHITE, Color.BLACK);
		screen.putString(1, 3, "[3] Set Rotor", Color.WHITE, Color.BLACK);
		screen.putString(1, 4, "[4] Set Rotor Offset", Color.WHITE, Color.BLACK);
		screen.putString(1, 5, "[5] Add Cable", Color.WHITE, Color.BLACK);
		screen.putString(1, 6, "[6] Remove Cable", Color.WHITE, Color.BLACK);
		screen.putString(1, 8, "[7] Close", Color.WHITE, Color.BLACK);
		
		String reflector = m4Active ? ((EnigmaM4) enigma).getThinReflector().toString() : ((EnigmaM3) enigma).getReflector().toString();
		String greekRotor = m4Active ? ((EnigmaM4) enigma).getGreekRotor() + " " : "";
		
		int leftOffset = enigma.getLeftRotor().getOffset() + 1;
		int middleOffset = enigma.getMiddleRotor().getOffset() + 1;
		int rightOffset = enigma.getRightRotor().getOffset() + 1;
		screen.putString(30, 1, "Machine: " + enigma, Color.WHITE, Color.BLACK);
		screen.putString(30, 2, "Reflector: " + reflector, Color.WHITE, Color.BLACK);
		screen.putString(30, 3, "Rotors: " + greekRotor + enigma.getLeftRotor() + " " + enigma.getMiddleRotor() + " " + enigma.getRightRotor(), Color.WHITE, Color.BLACK);
		screen.putString(30, 4, "Offset: " + leftOffset + " " + middleOffset + " " + rightOffset, Color.WHITE, Color.BLACK);
		screen.putString(30, 5, "Plugboard:", Color.WHITE, Color.BLACK);
		
		ArrayList<Integer> noShow = new ArrayList<Integer>();
		int line = 5;
		for (int i = 0; i < 26; i++) {
			if (enigma.getPlugboard().isPlugged(i) && !noShow.contains(i)) {
				int s = enigma.getPlugboard().swappedWith(i);
				noShow.add(s);
				char c1 = Original.toChar(i);
				char c2 = Original.toChar(enigma.getPlugboard().swappedWith(i));
				screen.putString(12, line, c1 + " -===- " + c2 , Color.WHITE, Color.BLACK);
				line++;
			}
		}
	}
	
}
