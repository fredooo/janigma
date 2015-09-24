package de.fredooo.janigma.ui.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

public class Menus {
	
	public static int selectMachine() {
		System.out.println("Select a machine:");
		System.out.println("[1] Enigma M3");
		System.out.println("[2] Enigma M4");
		System.out.println("[0] Exit program");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 2) {
			i = -1;
		}
		return i;
	}
	
	public static int showEnigmaOptions() {
		System.out.println("Select an option:");
		System.out.println("[1] Configure rotors");
		System.out.println("[2] Change reflector");
		System.out.println("[3] Configure plugboard");
		System.out.println("[0] Abort");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 3) {
			i = -1;
		}
		return i;
	}
	
	public static int showRotorOptions() {
		System.out.println("Select an option:");
		System.out.println("[1] Change order");
		System.out.println("[2] Change positions");
		System.out.println("[3] Change offsets");
		System.out.println("[0] Cancel");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 3) {
			i = -1;
		}
		return i;
	}
	
	public static int selectM3Rotor() {
		System.out.println("Select an option:");
		System.out.println("[1] I");
		System.out.println("[2] II");
		System.out.println("[3] III");
		System.out.println("[4] IV");
		System.out.println("[5] V");
		System.out.println("[6] VI");
		System.out.println("[7] VII");
		System.out.println("[8] VIII");
		System.out.println("[0] Abort");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 8) {
			i = -1;
		}
		return i - 1;
	}
	
	public static int selectM4GreekRotor() {
		System.out.println("Select an option:");
		System.out.println("[1] Beta");
		System.out.println("[2] Gamma");
		System.out.println("[0] Abort");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 2) {
			i = -1;
		}
		return i - 1;
	}
	
	public static int selectM3RotorPosition() {
		System.out.println("Select an option:");
		System.out.println("[1] Left");
		System.out.println("[2] Middle");
		System.out.println("[3] Right");
		System.out.println("[0] Abort");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 3) {
			i = -1;
		}
		return i;
	}
	
	public static int selectM4RotorPosition() {
		System.out.println("Select an option:");
		System.out.println("[1] Greek");
		System.out.println("[2] Left");
		System.out.println("[3] Middle");
		System.out.println("[4] Right");
		System.out.println("[0] Cancel");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 4) {
			i = -1;
		}
		return i;
	}
	
	public static int getOriginalInput() {
		String s = Console.getStringInput();
		Pattern d = Pattern.compile("^[1-9]+$");
		Pattern w = Pattern.compile("^[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]$");
		Matcher md = d.matcher(s);
		Matcher mw = w.matcher(s);
		if (md.matches()) {
			int i = Integer.parseInt(s);
			return (i > 0 && i < 27) ? i - 1 : -1;
		}
		if (mw.matches()) {
			if (Original.isValidString(s)) {
				try {
					return Original.toInt(s.charAt(0));
				} catch (NoSuchSymbolException ex) {
					// Checked before, never executed.
					ex.printMessage();
				}
			}
		}
		return -1;
	}
	
	public static int selectRotorPosition() {
		System.out.print("To which position should this rotor be set? (1-26, a-z, A-Z) ");
		return getOriginalInput();
	}
	
	public static int selectRotorOffset() {
		System.out.print("To which offset should this rotor be set? (1-26, a-z, A-Z) ");
		return getOriginalInput();
	}
	
	public static int showReflectorOptions() {
		System.out.println("Select an option:");
		System.out.println("[1] Change reflector");
		System.out.println("[0] Abort");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 1) {
			i = -1;
		}
		return i;
	}
	
	public static int selectM3Reflector() {
		System.out.println("Select a reflector:");
		System.out.println("[1] A");
		System.out.println("[2] B");
		System.out.println("[3] C");
		System.out.println("[0] Cancel");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 3) {
			i = -1;
		}
		return i;
	}
	
	public static int selectM4Reflector() {
		System.out.println("Select a thin reflector:");
		System.out.println("[1] A");
		System.out.println("[2] B");
		System.out.println("[0] Cancel");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 2) {
			i = -1;
		}
		return i;
	}
	
	public static int selectFirstPlug() {
		System.out.print("First letter: (1-26, a-z, A-Z) ");
		return getOriginalInput();
	}
	
	public static int selectSecondPlug() {
		System.out.print("\nSecond letter:? (1-26, a-z, A-Z) ");
		return getOriginalInput();
	}
	
	public static int addCable() {
		System.out.println("Which letters should be swapped?");
		return selectFirstPlug();
	}
	
	public static int removeCable() {
		System.out.println("Which cable should be removed?");
		return selectFirstPlug();
	}
	
	public static int showPlugboardOptions() {
		System.out.println("Select an option:");
		System.out.println("[1] Add a cable");
		System.out.println("[2] Remove a cable");
		System.out.println("[3] Remove all cables");
		System.out.println("[0] Close");
		System.out.print(" ");
		int i = Console.getIntInput();
		if (i < 0 || i > 3) {
			i = -1;
		}
		return i;
	}
	
	public static int removeAllPlugs() {
		System.out.print("Removing all cabels! Are you sure? (y/n) ");
		String i = Console.getStringInput();
		if (i.equals("y")) { return 1; }
		if (i.equals("n")) { return 0; }
		return -1;	
	}

}
