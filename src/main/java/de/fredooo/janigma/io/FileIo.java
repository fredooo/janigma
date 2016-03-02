package de.fredooo.janigma.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.fredooo.janigma.machine.Enigma;

/**
 * Provides simple file input and output actions.
 * @author Frederik Dennig
 * @since 2016-03-01
 * @version 0.0.1 (last revised 2016-03-02)
 */
public class FileIo {
	
	/**
	 * Stores an Enigma machine to a given file.
	 * @param file the file to write to
	 * @param enigma the Enigma machine
	 */
	public static void saveEnigmaMachine(File file, Enigma enigma) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(file, enigma);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Loads an Enigma machine from a given file.
	 * @param file the file to load from
	 */
	public static Enigma loadEnigmaMachine(File file) {
		Enigma enigma = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			enigma = mapper.readValue(file, Enigma.class);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return enigma;
	}
	
}
