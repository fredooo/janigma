package de.fredooo.janigma.io;

/**
 * Provides simple file input and output actions.
 * @author Frederik Dennig
 * @since 2016-03-01
 * @version 0.0.1 (last revised 2016-03-02)
 */
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.fredooo.janigma.machine.Enigma;

public class FileIo {
	
	public static void saveEnigmaMachine(File file, Enigma enigma) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(file, enigma);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
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
