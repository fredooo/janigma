package de.fredooo.janigma.ui.console;

import de.fredooo.janigma.Data;
import de.fredooo.janigma.machine.Enigma;
import de.fredooo.janigma.machine.EnigmaM3;
import de.fredooo.janigma.machine.EnigmaM4;
import de.fredooo.janigma.symbols.NoSuchSymbolException;
import de.fredooo.janigma.symbols.Original;

public class UI {
	
	public static void run() {
		
		Console.clear();
		Data.title();
		
		/*
		 * Machine selection menu.
		 */
		
		Enigma e = null;
		int i = Menus.selectMachine();
		while (i < 0) {
			Console.clear();
			System.out.println("Invalid input!");
			System.out.println();
			i = Menus.selectMachine();
		}
		if (i == 0) {
			System.exit(0);
		} else if (i == 1) {
			e = (Enigma) EnigmaM3.getEnigmaM3();
		} else if (i == 2) {
			e = (Enigma) EnigmaM4.getEnigmaM4();
		}
		
		String in = "";
		String out = "";
		String user = "";
		String msg = "";
		
		while (true) {
			
			/*
			 * Main screen.
			 */
		
			Console.clear();
			System.out.println(msg);
			System.out.println();
			States.showEnigma(e);
			System.out.println("Input:\t" + in);
			System.out.println("Output:\t" + out);
			System.out.print("Current input: " );
			user = Console.getStringInput();
			
			if (user.equals("0")) {
				System.exit(0);
			}
			
			if (user.equals("1")) {
				
				/*
				 * Main menu.
				 */
				
				msg = "";
				Console.clear();
				
				int j = Menus.showEnigmaOptions();
				while (j < 0) {
					Console.clear();
					System.out.println("Invalid input!");
					System.out.println();
					j = Menus.showEnigmaOptions();
				}
				
				if (j == 0) { continue; }
				
				/*
				 * Rotor menu.
				 */
				
				if (j == 1) {
					Console.clear();
					States.showRotors(e);
					System.out.println();
					int k = Menus.showRotorOptions();
					while (k < 0) {
						Console.clear();
						System.out.println("Invalid input!");
						System.out.println();
						States.showRotors(e);
						System.out.println();
						k = Menus.showRotorOptions();
					}
					if (k == 0) { continue; }
					
					/*
					 * Change rotor order.
					 */
					
					if (k == 1) {
						if (e instanceof EnigmaM3) {
							Console.clear();
							int l = Menus.selectM3RotorPosition();
							while (l < 0) {
								System.out.println("Invalid Input!");
								System.out.println();
								l = Menus.selectM3RotorPosition();
							}
							if (l == 0) { continue; }
							
							int m = Menus.selectM3Rotor();
							while (m < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								m = Menus.selectM3Rotor();
							}
							if (m == 0) { continue; }
							
							if (l == 1) {
								e.setLeftRotor(e.getM3Rotors()[m]);
								continue;
							}
							if (l == 2) {
								e.setMiddleRotor(e.getM3Rotors()[m]);
								continue;								
							}
							if (l == 3) {
								e.setRightRotor(e.getM3Rotors()[m]);
								continue;
							}
						} else if (e instanceof EnigmaM4) {
							Console.clear();
							int l = Menus.selectM4RotorPosition();
							while (l < 0) {
								System.out.println("Invalid Input!");
								System.out.println();
								l = Menus.selectM4RotorPosition();
							}
							if (l == 0) { continue; }
							
							int m = -1;
							if (l == 4) {
								m = Menus.selectM4GreekRotor();
								while (m < 0) {
									Console.clear();
									System.out.println("Invalid Input!");
									System.out.println();
									m = Menus.selectM4GreekRotor();
								}
							} else {
								m = Menus.selectM3Rotor();
								while (m < 0) {
									Console.clear();
									System.out.println("Invalid Input!");
									System.out.println();
									m = Menus.selectM3Rotor();
								}
							}
							
							if (m == 0) { continue; }
							
							if (l == 1) {
								e.setLeftRotor(e.getM3Rotors()[m]);
								continue;
							}
							if (l == 2) {
								e.setMiddleRotor(e.getM3Rotors()[m]);
								continue;								
							}
							if (l == 3) {
								e.setRightRotor(e.getM3Rotors()[m]);
								continue;
							}
							if (l == 4) {
								((EnigmaM4) e).setGreekRotor(((EnigmaM4) e).getGreekRotors()[m]);
							}
						} else {
							throw new ClassCastException("Couldn't cast to an IEnigmaM3 or IEnigmaM4 type!");
						}
					}
					
					/*
					 * Change rotor positions. 
					 */
					
					if (k == 2) {
						if (e instanceof EnigmaM3) {
							Console.clear();
							int l = Menus.selectM3RotorPosition();
							while (l < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								l = Menus.selectM3RotorPosition();
							}
							if (l == 0) { continue; }
							
							Console.clear();
							int m = Menus.selectRotorPosition();
							while (m < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								m = Menus.selectRotorPosition();
							}
							
							if (l == 1) {
								e.getLeftRotor().setPostion(m);
								continue;
							}
							if (l == 2) {
								e.getMiddleRotor().setPostion(m);
								continue;
							}
							if (l == 3) {
								e.getRightRotor().setPostion(m);
								continue;
							}
						} else if (e instanceof EnigmaM4) {
							Console.clear();
							int l = Menus.selectM4RotorPosition();
							while (l < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								l = Menus.selectM4RotorPosition();
							}
							
							if (l == 0) { continue; }
							
							Console.clear();
							int m = Menus.selectRotorPosition();
							while (m < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								m = Menus.selectRotorPosition();
							}
							
							if (l == 1) {
								((EnigmaM4) e).getGreekRotor().setPostion(m);
								continue;
							}
							if (l == 2) {
								e.getLeftRotor().setPostion(m);
								continue;
							}
							if (l == 3) {
								e.getMiddleRotor().setPostion(m);
								continue;
							}
							if (l == 4) {
								e.getRightRotor().setPostion(m);
								continue;
							}
						} else {
							throw new ClassCastException("Couldn't cast to an IEnigmaM3 or IEnigmaM4 type!");
						}
					}
					
					/*
					 * Change rotor offsets. 
					 */
					
					if (k == 3) {
						if (e instanceof EnigmaM3) {
							Console.clear();
							int l = Menus.selectM3RotorPosition();
							while (l < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								l = Menus.selectM3RotorPosition();
							}
							if (l == 0) { continue; }
							
							Console.clear();
							int m = Menus.selectRotorOffset();
							while (m < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								m = Menus.selectRotorOffset();
							}
							
							if (l == 1) {
								e.getLeftRotor().setOffset(m);
								continue;
							}
							if (l == 2) {
								e.getMiddleRotor().setOffset(m);
								continue;
							}
							if (l == 3) {
								e.getRightRotor().setOffset(m);
								continue;
							}
						} else if (e instanceof EnigmaM4) {
							Console.clear();
							int l = Menus.selectM4RotorPosition();
							while (l < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								l = Menus.selectM4RotorPosition();
							}
							
							if (l == 0) { continue; }
							
							Console.clear();
							int m = Menus.selectRotorOffset();
							while (m < 0) {
								Console.clear();
								System.out.println("Invalid Input!");
								System.out.println();
								m = Menus.selectRotorOffset();
							}
							
							if (l == 1) {
								((EnigmaM4) e).getGreekRotor().setOffset(m);
								continue;
							}
							if (l == 2) {
								e.getLeftRotor().setOffset(m);
								continue;
							}
							if (l == 3) {
								e.getMiddleRotor().setOffset(m);
								continue;
							}
							if (l == 4) {
								e.getRightRotor().setOffset(m);
								continue;
							}
						} else {
							throw new ClassCastException("Couldn't cast to an IEnigmaM3 or IEnigmaM4 type!");
						}
					}
				}
				
				/*
				 * Reflector menu.
				 */
				
				if (j == 2) {
					Console.clear();
					States.showReflector(e);
					System.out.println();
					int k = Menus.showReflectorOptions();
					while (k < 0) {
						Console.clear();
						System.out.println("Invalid input!");
						System.out.println();
						States.showReflector(e);
						System.out.println();
						k = Menus.showReflectorOptions();
					}
					if (k == 0) { continue; }
					if (k == 1) {
						Console.clear();
						if (e instanceof EnigmaM3) {
							int l = Menus.selectM3Reflector();
							if (l == 0) { continue; }
							if (l == 1) {
								// Set reflector to A.
								((EnigmaM3) e).setReflector(((EnigmaM3) e).getM3Reflectors()[0]);
								continue;
							}
							if (l == 2) {
								// Set reflector to B.
								((EnigmaM3) e).setReflector(((EnigmaM3) e).getM3Reflectors()[1]);
								continue;
							}
							if (l == 3) {
								// Set reflector to C.
								((EnigmaM3) e).setReflector(((EnigmaM3) e).getM3Reflectors()[2]);
								continue;
							}
						} else if (e instanceof EnigmaM4) {
							int l = Menus.selectM4Reflector();
							if (l == 0) { continue; }
							if (l == 1) {
								// Set thin reflector to A.
								((EnigmaM4) e).setThinReflector(((EnigmaM4) e).getThinReflectors()[0]);
								continue;
							}
							if (l == 2) {
								// Set thin reflector to B.
								((EnigmaM4) e).setThinReflector(((EnigmaM4) e).getThinReflectors()[1]);
								continue;
							}
						} else {
							throw new ClassCastException("Couldn't cast to an IEnigmaM3 or IEnigmaM4 type!");
						}
					}
				}
				
				/*
				 * Plugboard menu.
				 */
				
				if (j == 3) {
					Console.clear();
					States.showPlugs(e);
					System.out.println();
					int k = Menus.showPlugboardOptions();
					while (k < 0) {
						Console.clear();
						System.out.println("Invalid input!");
						System.out.println();
						States.showPlugs(e);
						System.out.println();
						k = Menus.showPlugboardOptions();
					}
					if (k == 0) { continue; }
					if (k == 1) {
						// Add a cable.
						Console.clear();
						int l = Menus.addCable();
						int m = Menus.selectSecondPlug();
						while (l < 0 || m < 0 || l == m) {
							Console.clear();
							System.out.println("Invalid input!");
							System.out.println();
							l = Menus.addCable();
							m = Menus.selectSecondPlug();
						}
						e.getPlugboard().addCabel(l, m);
						continue;
					}
					if (k == 2) {
						// Remove a cable.
						Console.clear();
						int l = Menus.removeCable();
						int m = Menus.selectSecondPlug();
						while (l < 0 || m < 0 || l == m) {
							Console.clear();
							System.out.println("Invalid input!");
							System.out.println();
							l = Menus.removeCable();
							m = Menus.selectSecondPlug();
						}
						if (e.getPlugboard().swappedWith(l) == m) {
							e.getPlugboard().removeCabel(l);
						}
						continue;
					}
					if (k == 3) {
						Console.clear();
						int l = Menus.removeAllPlugs();
						while (l < 0) {
							Console.clear();
							System.out.println("Invalid input!");
							System.out.println();
							l = Menus.removeAllPlugs();
						}
						if (l == 0) { continue; }
						if (l == 1) {
							e.getPlugboard().removeAllCabels();
							continue;
						}
					}
				}
			}
			
			/*
			 * Main screen functionality.
			 */
			
			if (user.equals("2")) {
				in = "";
				out = "";
				msg = "";
				continue;
			}
			
			if (user.equals("")) {
				msg = "No input to en- or decrypt!";
				continue;
			}
			
			if (!Original.isValidString(user)) {
				msg = "Invalid input!";
				continue;
			}
			
			in += user;
			try {
				out += e.use(user);
			} catch (NoSuchSymbolException ex) {
				// Checked before, never executed.
				ex.printMessage();
			}
			msg = "";
		}
	}
	
}
