package de.fredooo.janigma.ui.console;

import java.util.ArrayList;

import de.fredooo.janigma.machine.Enigma;
import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.EnigmaM4;
import de.fredooo.janigma.symbols.Original;

public class States {
	
	public static void showEnigma(Enigma e) {
		if (e instanceof EnigmaM3) {
			showEnigma((EnigmaM3) e);
		} else if (e instanceof EnigmaM4) {
			showEnigma((EnigmaM4) e);
		} else {
			throw new ClassCastException("Couldn't cast to an IEnigmaM3 or IEnigmaM4 type!");
		}
	}
	
	public static void showEnigma(EnigmaM3 e) {
		System.out.println("Options\t\t[1]\t\tExit [0]");
		System.out.println("Clear I\\O\t[2]");
		System.out.println();
		System.out.println("+--------------------------------------+");
		System.out.println("|              Enigma M3               |");
		System.out.println("|                                      |");
		System.out.println("|       +---+ +---+ +---+              |");
		System.out.println("|       | " + Original.toChar((e.getLeftRotor().getPosition())) + " | | " +
				Original.toChar((e.getMiddleRotor().getPosition())) + " | | " +
				Original.toChar((e.getRightRotor().getPosition())) +" |              |");
		System.out.println("|       +---+ +---+ +---+              |");
		System.out.println("|                                      |");
		System.out.println("+--------------------------------------+");
		System.out.println();		
	}
	
	public static void showEnigma(EnigmaM4 e) {
		System.out.println("Options\t\t[1]\t\tExit [0]");
		System.out.println("Clear I\\O\t[2]");
		System.out.println();
		System.out.println("+--------------------------------------+");
		System.out.println("|              Enigma M4               |");
		System.out.println("|                                      |");
		System.out.println("| +---+ +---+ +---+ +---+              |");
		System.out.println("| | " + Original.toChar(e.getGreekRotor().getPosition()) + " | | " +
				Original.toChar((e.getLeftRotor().getPosition())) + " | | " +
				Original.toChar((e.getMiddleRotor().getPosition())) + " | | " +
				Original.toChar((e.getRightRotor().getPosition())) +" |              |");
		System.out.println("| +---+ +---+ +---+ +---+              |");
		System.out.println("|                                      |");
		System.out.println("+--------------------------------------+");
		System.out.println();
	}
	
	public static void showRotors(Enigma e) {
		if (e instanceof EnigmaM3) {
			showRotors((EnigmaM3) e);
		} else if (e instanceof EnigmaM4) {
			showRotors((EnigmaM4) e);
		} else {
			throw new ClassCastException("Couldn't cast to an IEnigmaM3 or IEnigmaM4 type!");
		}
	}
	
	public static void showRotors(EnigmaM3 e) {
		System.out.println("Order     |  " + e.getLeftRotor() + "  |  " + e.getMiddleRotor() + "  |  " + e.getRightRotor());
		System.out.println("----------+-----+-----+-----");
		System.out.println("Postion   |  " + e.getLeftRotor().getPosition() + "  |  " + e.getMiddleRotor().getPosition() + "  |  " +
								e.getRightRotor().getPosition());
		System.out.println("Offset    |  " + e.getLeftRotor().getOffset() + "  |  " + e.getMiddleRotor().getOffset() + "  |  " +
				e.getRightRotor().getOffset());
	}
	
	public static void showRotors(EnigmaM4 e) {
		System.out.println("Order     |  " + e.getGreekRotor() + "  |  " + e.getLeftRotor() + "  |  " + e.getMiddleRotor() +
								"  |  " + e.getRightRotor());
		System.out.println("----------+-----+-----+-----+-----");
		System.out.println("Postion   |  " + e.getGreekRotor().getPosition() + "  |  " + e.getLeftRotor().getPosition() +
								"  |  " + e.getMiddleRotor().getPosition() + "  |  " + e.getRightRotor().getPosition());
		System.out.println("Offset    |  " + e.getGreekRotor().getOffset() + "  |  " + e.getLeftRotor().getOffset() +
								"  |  " + e.getMiddleRotor().getOffset() + "  |  " + e.getRightRotor().getOffset());
	}
	
	public static void showReflector(Enigma e) {
		if (e instanceof EnigmaM3) {
			showReflector((EnigmaM3) e);
		} else if (e instanceof EnigmaM4) {
			showReflector((EnigmaM4) e);
		} else {
			throw new ClassCastException("Couldn't cast to an IEnigmaM3 or IEnigmaM4 type!");
		}
	}
	
	public static void showReflector(EnigmaM3 e) {
		System.out.println("Current reflector: " + e.getReflector());
	}
	
	public static void showReflector(EnigmaM4 e) {
		System.out.println("Current thin reflector: " + e.getThinReflector());
	}
	
	public static void showPlugs(Enigma e) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i <= 25; i++) {
			if ( e.getPlugboard().isPlugged(i) &&
					!a.contains(e.getPlugboard().swappedWith(i)) &&
					!a.contains(i) ) {
				a.add(i);
				a.add(e.getPlugboard().swappedWith(i));
			}
		}
		if (a.isEmpty()) {
			System.out.println("No cables plugged in!");
		}
		while (!a.isEmpty()) {
			System.out.println(Original.toChar(a.remove(0)) + " -|=====|- " + Original.toChar(a.remove(0)));
		}
	}
	
}
