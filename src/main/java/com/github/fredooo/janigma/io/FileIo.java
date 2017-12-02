package com.github.fredooo.janigma.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.github.fredooo.janigma.core.symbols.NoSuchSymbolException;
import com.github.fredooo.janigma.core.symbols.Original;
import com.github.fredooo.janigma.core.machine.Enigma;
import com.github.fredooo.janigma.ui.graphical.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Provides simple file input and output actions.
 * @author Frederik Dennig
 * @since 2016-03-01
 * @version 0.0.4 (last revised 2017-12-02)
 */
public class FileIo {

	private static final FileNameExtensionFilter JSON = new FileNameExtensionFilter("JSON File", "json");

	public static void saveEnigmaMachine(Enigma enigma) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Configuration");
		fileChooser.setFileFilter(JSON);
		int ret = fileChooser.showSaveDialog(MainWindow.instance());
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (!file.getName().endsWith(".json")) {
				file = new File(file + ".json");
			}
			FileIo.writeEnigmaMachineToFile(file, enigma);
		}
	}

	/**
	 * Writes an Enigma machine to a given file.
	 * @param file the file to write to
	 * @param enigma the Enigma machine
	 */
	public static void writeEnigmaMachineToFile(File file, Enigma enigma) {
		if (enigma == null) { throw new IllegalArgumentException("Can't serialize null!"); }
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(file, enigma);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Enigma loadEnigmaMachine() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Load Configuration");
		fileChooser.setFileFilter(JSON);
		int ret = fileChooser.showOpenDialog(MainWindow.instance());
		if (ret == JFileChooser.APPROVE_OPTION) {
			return readEnigmaMachineFromFile(fileChooser.getSelectedFile());
		}
		return null;
	}
	
	/**
	 * Reads an Enigma machine from a given file.
	 * @param file the file to load from
	 * @return the loaded Enigma machine
	 */
	public static Enigma readEnigmaMachineFromFile(File file) {
		Enigma enigma = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			enigma = mapper.readValue(file, Enigma.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return enigma;
	}
	
	/**
	 * Reads the content of a text file and returns it as a string.
	 * @param textFile the path of the text file
	 * @return the content of the text file or an empty string, if an error
	 * occurred, in the latter case a error message is printed
	 */
	private static String readTextFile(File textFile) {
		String result = "";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFile));
			String line;
			while ((line = reader.readLine()) != null) {
				result += line + "\n";
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	/**
	 * Writes a string to a file.
	 * @param text the content of the text file
	 * @param file the file to write to
	 */
	private static void writeTextFile(String text, File file) {
		try {
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.print(text);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Encrypts or decrypts a file and writes the result to another file.
	 * @param inputPath the input file path
	 * @param outputPath the output file path
	 * @param configPath the configuration of the Enigma machine
	 */
	public static void encryptDecryptFile(String inputPath, String outputPath, String configPath) {
		Enigma enigma = FileIo.readEnigmaMachineFromFile(new File(configPath));
		String input = readTextFile(new File(inputPath));
		input = input.trim();
		if (enigma != null) {
			if (Original.isValidString(input)) {
				try {
					String output = enigma.use(input);
					writeTextFile(output + "\n", new File(outputPath));
				} catch (NoSuchSymbolException e) {
					// This block should never be reached
					e.printStackTrace();
				}
			} else {
				System.out.println("The input file contains an invalid symbol!");
			}
		}
	}
	
}
