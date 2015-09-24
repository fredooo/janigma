package de.fredooo.janigma.fileio;

import de.fredooo.janigma.symbols.Original;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implements a simple file reader.
 * @author Frederik Dennig
 * @since 05/06/11
 * @version 0.0.2 (last revised 2015-09-23)
 */
public class Reader {
	
	private Scanner reader;
	private File file;
	
	/**
	 * Constructs a simple file reader.
	 * @param path the file to be read
	 */
	public Reader(String path) {
		file = new File(path);
	}
	
	/**
	 * Opens a file.
	 */
	public void openFile() {
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Couldn't open " + file.getAbsolutePath() + "!");
		} 
	}

	/**
	 * Checks if a file is a valid file.
	 * @return true if the file is valid else false
	 */
	public boolean validateFile() {
		while (reader.hasNext()) {
			if (Original.isValidString(reader.next()) == false) {
				reader.close();
				return false;
			}
		}
		reader.close();
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException ex) {
			// Nothing to do, file exists
		}
		return true;
	}
	
	/**
	 * Reads a file and eliminates all white spaces.
	 * @return the contents of a file as string
	 */
	public String readFromFile() {
		String out = "";
		while (reader.hasNext()) {
			out = out + reader.next();
		}
		return out;
	}
		
	/**
	 * Closes the file.
	 */
	public void closeFile() {
		reader.close();
	}
	
}
