package de.fredooo.janigma.ui.console;

import de.fredooo.janigma.fileio.Reader;
import de.fredooo.janigma.fileio.Writer;
import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.Reflector;
import de.fredooo.janigma.machine.Rotor;

import java.util.Scanner;

import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

/**
 * This class handles all important user input and
 * shows options and state information on the terminal. 
 * @author Frederik Dennig
 * @since 05/06/11
 */
@Deprecated
public class ConsoleUI {
	
	/**
	 * Reads a line from the terminal.
	 * @return Return the read line as a string.
	 */
	@Deprecated
	public static String getInput() {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		in.close();
		return str;
	}
	
	/**
	 * Initializes an Enigma M3 or M4 (user choice) and calls the specific
	 * show Method. 
	 */
	@Deprecated
	public static void start() {
		System.out.println();
		
		// System.out.println("Enigma M3\t[1]");
		// System.out.println("Enigma M4\t[2]");
		// System.out.print("Choose machine: ");
		// String answer = getInput();
		
		// if(answer.equals("1")) {
			
		EnigmaM3 enigma = new EnigmaM3(Rotor.createNormalRotors(), Reflector.createNormalReflectors());
						
		// } else if(answer.equals("2")) {
			
			// M4 enigma = new M4(Rotor.buildM4Rotors(), Reflector.buildM4Reflectors());
			
			show(enigma);
		// }
	}
	
	/**
	 * Prints the current state of the Enigma M3 machine, the already processed
	 * characters and an input prompt.
	 * @param enigma An Einigma M3 machine (normally the with standard configuration).
	 */
	@Deprecated
	public static void show(EnigmaM3 enigma) {
		
		boolean exit = false;
		
		String intext = "";
		String outtext = "";
		
		while(exit == false) {
			
			System.out.println("Options [1]\t\t\tExit [0]");
			System.out.println();
			System.out.println("+--------------------------------------+");
			System.out.println("|              Enigma M3               |");
			System.out.println("|                                      |");
			System.out.println("|       +---+ +---+ +---+              |");
			System.out.println("|       | " + Original.toChar((enigma.getLeftRotor().getPosition())) + " | | " +
					Original.toChar((enigma.getMiddleRotor().getPosition())) + " | | " +
					Original.toChar((enigma.getRightRotor().getPosition())) +" |              |");
			System.out.println("|       +---+ +---+ +---+              |");
			System.out.println("|                                      |");
			System.out.println("+--------------------------------------+");
			System.out.println();
			System.out.println("Input:\t" + intext);
			System.out.println("Output:\t" + outtext);
			System.out.print("Current input: " );
			String input = getInput();
			
			if(input.equals("1")) {
				
				options(enigma);
				
			} else if(input.equals("0")) {
				
				exit = true;
				
			} else {
				
				if(input.length() > 0) {
					
					try {
						
						for(int i = 0; i < input.length(); i++) {
							int output = enigma.use(Original.toInt(input.charAt(i)));
							outtext = outtext + Original.toChar(output);
							intext = intext + input.charAt(i);
						}
						
					} catch (NoSuchSymbolException ex) {
						ex.printMessage();
					}
					
				} else {
					
					System.out.println();
					System.out.println("No input to process!");
					System.out.println();
				}
			}
		}
	}
	
	/**
	 * Prints the options menu. 
	 * @param enigma An Enigma M3 machine. 
	 */
	@Deprecated
	public static void options(EnigmaM3 enigma) {
		
		boolean exit = false;
		
		while(exit == false) {
			
			System.out.println();
			System.out.println("Reflector/Rotors\t\t[1]");
			System.out.println("Plugboard\t\t\t[2]");
			System.out.println("Quick setup (from File-Mode)\t[3]");
			System.out.println("Exit\t\t\t\t[0]");
			System.out.print("Choose option: ");
			String answer = getInput();
			System.out.println();
		
			if(answer.equals("1")) {
				
				rotorAndReflectorConfig(enigma);
				
			} else if(answer.equals("2")) {
				
				plugboardConfig(enigma);
				
			} else if(answer.equals("3")) {
				
				quickSetup(enigma);
				
			} else if(answer.equals("0")) {
				
				exit = true;
				
			} else {
				System.out.println();
				System.out.println("Invalid input!");
			}
		}
	}
	
	@Deprecated
	public static void rotorAndReflectorConfig(EnigmaM3 enigma) {
		
		boolean exit = false;
		
		while(exit == false) {
			
			System.out.println();
			System.out.println("Reflector:\tRotors:");
			System.out.println();
			System.out.println(enigma.getReflector() + "\t\t" + enigma.getLeftRotor() +
					"\t" + enigma.getMiddleRotor() + "\t" + enigma.getRightRotor());
			System.out.println();
		
			System.out.println("Available Rotors: ");
			System.out.println();
			for(int i = 0; i < enigma.getM3Rotors().length; i++) {
				if(enigma.getM3Rotors()[i].equals(enigma.getRightRotor()) || 
						enigma.getM3Rotors()[i].equals(enigma.getMiddleRotor()) ||
						enigma.getM3Rotors()[i].equals(enigma.getLeftRotor())) {
					// Do nothing.
				} else {
					System.out.println(enigma.getM3Rotors()[i] + "\t[" + (i + 1) + "]");	
				}
			}
			
			// System.out.println();
			// System.out.println("Exit\t[0]");
			// System.out.println();
		
			System.out.print("Choose rotor: ");
			String rotor = getInput();
			System.out.print("Asign to postion: ");
			String position = getInput();
			if(position.equals("1")) {
				enigma.setLeftRotor(enigma.getM3Rotors()[Integer.valueOf(rotor) - 1]);
			} else if(position.equals("2")) {
				enigma.setMiddleRotor(enigma.getM3Rotors()[Integer.valueOf(rotor) - 1]);
			} else if(position.equals("3")) {
				enigma.setRightRotor(enigma.getM3Rotors()[Integer.valueOf(rotor) - 1]);
			}
		}
	}
	
	/**
	 * 
	 * @param enigma
	 */
	@Deprecated
	public static void plugboardConfig(EnigmaM3 enigma) {
		
		boolean exit = false;
		
		while(exit == false) {
		
			System.out.println();			
			System.out.println("Plugs:");
			int[] noshow = new int[0];
			for(int i = 0; i < 26; i++) {
				if(contains(noshow, i) == false) {
					if(enigma.getPlugboard().isPlugged(i)) {
						noshow = add(noshow, enigma.getPlugboard().swappedWith(i));
						System.out.println(Original.toChar(i) + " -=====- " + Original.toChar(enigma.getPlugboard().swappedWith(i)));		
					}		
				}
			}
			
			System.out.println();
			System.out.println("Add cabel\t[1]");
			System.out.println("Remove cabel\t[2]");
			System.out.println("Exit\t\t[0]");
			System.out.print("Choose option: ");
			String answer = getInput();
			System.out.println();
				
			if(answer.equals("1")) {
				
				System.out.print("From character: ");
				String from = getInput();
				System.out.print("To character: ");
				String to = getInput();
				
				if(from.length() > 1 || to.length() > 1) {
					
					System.out.println();
					System.out.println("Invalid input!");
					System.out.println();
					
				} else {
					
					try {
						
						int plug1 = Original.toInt(from.charAt(0));
						int plug2 = Original.toInt(to.charAt(0));
						
						if(enigma.getPlugboard().isPlugged(plug1) || enigma.getPlugboard().isPlugged(plug2)) {
							
							System.out.println();
							System.out.println(Original.toChar(plug1) + " or " + Original.toChar(plug2) + " is " +
									"already plugged!");
							System.out.println();
							
						} else {
							enigma.getPlugboard().addCabel(plug1, plug2);
						}
						
					} catch (NoSuchSymbolException ex) {
						ex.printMessage();
					} catch (StringIndexOutOfBoundsException ex) {
						System.out.println("Invalid input!");
					}
				}
			
			} else if(answer.equals("2")) {
				
				System.out.print("From character: ");
				String from = getInput();
				System.out.print("To character: ");
				String to = getInput();
			
				if(from.length() > 1 || to.length() > 1) {
					
					System.out.println();
					System.out.println("Invalid input!");
					System.out.println();
					
				} else {
					
					try {
						
						int plug1 = Original.toInt(from.charAt(0));
						int plug2 = Original.toInt(to.charAt(0));
						
						if(enigma.getPlugboard().swappedWith(plug1) == plug2) {
							enigma.getPlugboard().removeCabel(plug1);
						
						} else {
							System.out.println();
							System.out.println("No cable between " + Original.toChar(plug1) + " and " +
									Original.toChar(plug2) + ". No cable removed!");
							System.out.println();
						}
					} catch (NoSuchSymbolException ex) {
						ex.printMessage();
					} catch (StringIndexOutOfBoundsException ex) {
						System.out.println();
						System.out.println("Invalid input!");
						System.out.println();
					}
				}

			} else if(answer.equals("0")) {
				
				exit = true;
				
			} else {
				
				System.out.println();
				System.out.println("Invalid input.");
				System.out.println();
			}
		}
	}
	
	/**
	 * 
	 * @param enigma
	 * @return
	 */
	@Deprecated
	public static EnigmaM3 quickSetup(EnigmaM3 enigma) {
		
		boolean right = false;
		
		while(right == false) {
			System.out.println();
			
			// Select reflector.
			
			String in = "";
			boolean ask = true; 
			while(ask) {
				System.out.print("Reflector: ");
				in = getInput();
				ask = (in.equals("A") || in.equals("B") || in.equals("C")) == false;
				if(ask) {
					System.out.println("Invalid input!");
				}
			}
			if(in.equals("A")) {
				enigma.setReflector(enigma.getM3Reflectors()[0]);
			} else if(in.equals("B")) {
				enigma.setReflector(enigma.getM3Reflectors()[1]);
			} else {
				enigma.setReflector(enigma.getM3Reflectors()[2]);
			}
			System.out.println();

			// Select rotors.
			
			in = "";
			ask = true;
			while(ask) {
				System.out.print("Rotor Order: ");
				in = getInput();
				
				ask = in.length() != 3 || in.charAt(0) == in.charAt(1) ||
						in.charAt(1) == in.charAt(2) ||	in.charAt(0) == in.charAt(2) ||
						
						(in.charAt(0) == '1' || in.charAt(0) == '2' || in.charAt(0) == '3' ||
						in.charAt(0) == '4' || in.charAt(0) == '5' || in.charAt(0) == '6' ||
						in.charAt(0) == '7' || in.charAt(0) == '8') == false ||
						
						(in.charAt(1) == '1' || in.charAt(1) == '2' || in.charAt(1) == '3' ||
						in.charAt(1) == '4' || in.charAt(1) == '5' || in.charAt(1) == '6' ||
						in.charAt(1) == '7' || in.charAt(1) == '8') == false ||
				
						(in.charAt(2) == '1' || in.charAt(2) == '2' || in.charAt(2) == '3' ||
						in.charAt(2) == '4' || in.charAt(2) == '5' || in.charAt(2) == '6' ||
						in.charAt(2) == '7' || in.charAt(2) == '8') == false;
						
				if(ask) {
					System.out.println("Invalid input!");
				}
			}
			enigma.setLeftRotor(enigma.getM3Rotors()[Character.getNumericValue(in.charAt(0)) - 1]);
			enigma.setMiddleRotor(enigma.getM3Rotors()[Character.getNumericValue(in.charAt(1)) - 1]);
			enigma.setRightRotor(enigma.getM3Rotors()[Character.getNumericValue(in.charAt(2)) - 1]);
			System.out.println();
			
			// Set offset of the rotors.
			
			in = "";
			ask = true;
			while(ask) {
				System.out.print("Rotor Offset: ");
				in = getInput();
				ask = in.length() != 3 || (Original.isValidString(in) == false);	
				if(ask) {
					System.out.println("Invalid input!");
				}
			}
			try {
				enigma.getLeftRotor().setOffset(Original.toInt(in.charAt(0)));
				enigma.getMiddleRotor().setOffset(Original.toInt(in.charAt(1)));
				enigma.getRightRotor().setOffset(Original.toInt(in.charAt(2)));
			} catch (NoSuchSymbolException ex) {
				// Not needed, is checked above.
				// ex.message();
			}
			System.out.println();
			
			// Set position off the rotors.
			
			in = "";
			ask = true;
			while(ask) {
				
				System.out.print("Rotor Position: ");
				in = getInput();
				ask = in.length() != 3 || (Original.isValidString(in) == false);
					if(ask) {
						System.out.println("Invalid input!");
					}
				}
				try {
					enigma.getLeftRotor().setPostion(Original.toInt(in.charAt(0)));
					enigma.getMiddleRotor().setPostion(Original.toInt(in.charAt(1)));
					enigma.getRightRotor().setPostion(Original.toInt(in.charAt(2)));
				} catch (NoSuchSymbolException ex) {
					// Not needed, is checked above.
					// ex.message();
				}
				System.out.println();
			
				// Add Plugs.
			
				in = "";
				ask = true;
				while(ask) {
					System.out.print("Plugs: ");
					in = getInput();
					ask = in.length() % 2 != 0 || (Original.isValidString(in) == false);
					// Check for duplicates.
					if(in.equals("") == false) {
						for(int i = 0; i < in.length(); i++) {
							for(int j = i + 1; j < in.length(); j++) {
								if(in.charAt(i) == in.charAt(j)) {
									ask = true;
									break;
								}
							}
						}
					}
					if(ask) {
						System.out.println("Invalid input!");
					}	
				}
				if(in.equals("") == false) {
					for(int i = 0; i < in.length(); i = i + 2) {
						try {
							enigma.getPlugboard().addCabel(Original.toInt(in.charAt(i)), Original.toInt(in.charAt(i + 1)));
						} catch (NoSuchSymbolException ex) {
							// Not need, is checked above.
							// ex.message();
						}	
					}	
				}
				System.out.println();

				// Final status screen.
			
				System.out.println("Reflector/Rotors:");
				System.out.println(enigma.getReflector() + "\t" + enigma.getLeftRotor() + "\t" + enigma.getMiddleRotor()
						+ "\t" + enigma.getRightRotor());
				System.out.println("Rotor Offset:");
				System.out.println("\t" + Original.toChar(enigma.getLeftRotor().getOffset()) +
						"\t" + Original.toChar(enigma.getMiddleRotor().getOffset()) + "\t" + Original.toChar(enigma.getRightRotor().getOffset()));
				System.out.println("Rotor Position:");
				System.out.println("\t" + Original.toChar(enigma.getLeftRotor().getPosition()) +
						"\t" + Original.toChar(enigma.getMiddleRotor().getPosition()) + "\t" + Original.toChar(enigma.getRightRotor().getPosition()));
				System.out.println("Plugs:");
				int[] noshow = new int[0];
				for(int i = 0; i < 26; i++) {
					if(contains(noshow, i) == false) {
						if(enigma.getPlugboard().isPlugged(i)) {
							noshow = add(noshow, enigma.getPlugboard().swappedWith(i));
							System.out.println(Original.toChar(i) + " -=====- " + Original.toChar(enigma.getPlugboard().swappedWith(i)));		
						}		
					}
				}
			
				in = "";
				System.out.println();
				System.out.print("Setting correct? [y/n]: ");
				in = getInput();
				if(in.charAt(0) == 'y') {
					right = true;
				} else {
					// Do nothing.
				}
			}
		
		return enigma;
	}
	
	/**
	 * 
	 * @param enigma
	 */
	@Deprecated
	public static void useFile(EnigmaM3 enigma) {
		System.out.println();
		
		System.out.print("Filepath: ");
		String path = getInput();
		
		Reader r = new Reader(path);
		r.openFile();

		if((r.validateFile())) {
			String in = r.readFromFile();
			r.closeFile();
			char[] input = in.toCharArray();
			System.out.println();
			System.out.println("Input:");
			System.out.println(formatMessage(input));
			
			char[] out = new char[input.length];
			for(int i = 0; i < input.length; i++) {
				try {
					out[i] = Original.toChar(enigma.use(Original.toInt(input[i])));
				} catch (NoSuchSymbolException ex) {
					// Not needed, is checked above.
					// ex.message();
				}
			}
			
			System.out.println();
			System.out.println("Output:");
			System.out.println(formatMessage(out));
			
			Writer w = new Writer(path + "_output");
			w.openFile();
			w.writeToFile(formatMessage(out));
			w.closeFile();
		
		} else {
			System.out.println("Invalid file! Maybe unknown characters.");
		}
	}
	
	/**
	 * Adds an given number to a given array.
	 * @param array The array which should be extended.
	 * @param number The number which should be added.
	 * @return Returns the array extended by the given number.
	 */
	@Deprecated
	private static int[] add(int[] array, int number) {
		int[] newarray = new int[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			newarray[i] = array[i];
		}
		newarray[array.length] = number;
		return newarray;
	}
	
	/**
	 * Returns true if the array contains the given int, else false. 
	 * @param array An array of ints.
	 * @param number An int.
	 * @return Returns true if the array contains the the given number else false.
	 */
	@Deprecated
	private static boolean contains(int[] array, int number) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == number) { return true; }
		}
		return false;
	}

	/**
	 * Formats a massage to five letters per group and ten groups per line.   
	 * @param message An message as character array.
	 * @return The message as string, formatted as described above.
	 */
	@Deprecated
	private static String formatMessage(char[] message) {
		String formatted = "";
		for(int i = 0; i < message.length; i++) {
			if((i + 1) % 50 == 0) {
				formatted = formatted + message[i] + '\n'; 
			} else if((i + 1) % 5 == 0) {
				formatted = formatted + message[i] + ' '; 
			} else {
				formatted = formatted + message[i];
			}
		}
		return formatted;
	}
		
}
