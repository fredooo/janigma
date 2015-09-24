package de.fredooo.janigma.ui.console;

import java.util.Scanner;

import de.fredooo.janigma.Data;

public class Console {
	
	public static void clear() {
		if (Data.CLEAR_CONSOLE) {
			for (int i = 0; i < 79; i++) {
				System.out.println();
			}
		}
	}
	
	public static int getIntInput() {
		int i = -1;
		Scanner s = new Scanner(System.in);
		if (s.hasNextInt()) { i = s.nextInt(); }
		s.close();
		return i;
	}
	
	public static String getStringInput() {
		String s = "";
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextLine()) { s = sc.nextLine(); }
		sc.close();
		return s;
	}

}
