package de.fredooo.janigma.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 * Implements a simple file writer.
 * @author Frederik Dennig
 * @since 05/06/11
 * @version 0.0.2 (last revised 2015-09-22)
 */
public class Writer {
	
	private Formatter writer;
	private File file;
	
	/**
	 * Creates a simple file at the given path.
	 * @param path the file path
	 */
	public Writer(String path) {
		file = new File(path);
	}
	
	/**
	 * Opens the file.
	 */
	public void openFile() {
		try {
			writer = new Formatter(file);
		} catch (FileNotFoundException ex) {
			System.err.println("ERROR: Couldn't open " + file.getAbsolutePath() + "!");
		}
	}
	
	/**
	 * Writes a string to the file.
	 * @param string the string to write
	 */
	public void writeToFile(String string) {
		writer.format(string);
	}
	
	/**
	 * Closes the file.
	 */
	public void closeFile() {
		writer.close();
	}	

}
