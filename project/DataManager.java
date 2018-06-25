package project;

import java.io.BufferedReader;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.util.*;

public class DataManager {
	private static final String DIRECTORYNAME = "players";

	public static void checkDirectory() {
		// Check if directory exists
		File directory = new File(DIRECTORYNAME);
		if (!directory.exists()) {
			directory.mkdir(); // if not, create the directory
		}
	}

	public static ArrayList getAllPlayerNames() {
		checkDirectory();
		final File folder = new File(DIRECTORYNAME);
		ArrayList players = new ArrayList();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				getAllPlayerNames(fileEntry);
			} else {
				players.add(fileEntry.getName().substring(0, fileEntry.getName().indexOf('.'))); // adds just the
																									// file
																									// name ->cuts the
																									// ending off
			}
		}
		return players;
	}

	public static ArrayList<String> getAllPlayerNames(final File folder) { // for
																			// recursive
																			// method
																			// call
																			// ->
																			// �berladung
		checkDirectory();
		ArrayList<String> players = new ArrayList<String>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				getAllPlayerNames(fileEntry);
			} else {
				players.add(fileEntry.getName().substring(0, fileEntry.getName().indexOf('.'))); // adds just the
																									// file
																									// name ->cuts the
																									// ending off
			}
		}
		return players;
	}

	public static String[] getPlayer(String filename) {
		try {
			FileReader fr = new FileReader(DIRECTORYNAME + File.separator + filename + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[8];
			for (int i = 0; i < playerData.length; i++) { // reads all lines of
															// player file
				playerData[i] = br.readLine();
				System.out.println(playerData[i]);
			}
			br.close();
			return playerData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addPlayer(String name) {
		String resourcePath = DIRECTORYNAME + File.separator + name + ".player";
		// Check if directory exists
		checkDirectory();
		File file = new File(resourcePath);
		try {
			if (!checkPlayerExists(name)) {
				// Assume default encoding.
				FileWriter fw = new FileWriter(file.getAbsoluteFile());

				// Always wrap FileWriter in BufferedWriter.
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(name);
				bw.newLine();
				for (int i = 0; i < 5; i++) { // Standard values
					bw.write("0");
					bw.newLine();
				}
				bw.write("null"); // Standard value
				bw.newLine();
				bw.write("red"); // Standard value
				bw.close();
			} else {
				System.out.println("Player already exists!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkPlayerExists(String name) {
		ArrayList<String> players = getAllPlayerNames();
		for (String player : players) {
			if (name.equals(player)) {
				return true;
			}
		}
		return false;
	}

	public static void changeProperty(String player, String attribute, String value) { // changes the addressed
		String resourcePath = DIRECTORYNAME + File.separator + player + ".player"; // attribute
		try {
			if (checkPlayerExists(value) & attribute.equals("name") & !player.equals(value)) {
				System.out.println("Player already exists! Couldn't save changes!");
			} else {
				String[] playerData = getPlayer(player);
				File file = new File(resourcePath);
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				for (int i = 0; i < playerData.length; i++) {
					if (attribute.equals("name") && (i == 0)) {
						bw.write(value);
						bw.newLine();
					} else if (attribute.equals("nrOfGames") && (i == 1)) {
						bw.write(value);
						bw.newLine();
					} else if (attribute.equals("wins") && (i == 2)) {
						bw.write(value);
						bw.newLine();
					} else if (attribute.equals("losses") && (i == 3)) {
						bw.write(value);
						bw.newLine();
					} else if (attribute.equals("ties") && (i == 4)) {
						bw.write(value);
						bw.newLine();
					} else if (attribute.equals("score") && (i == 5)) {
						bw.write(value);
						bw.newLine();
					} else if (attribute.equals("icon") && (i == 6)) {
						bw.write(value);
						bw.newLine();
					} else if (attribute.equals("color") && (i == 7)) {
						bw.write(value);
						bw.newLine();
					} else {
						bw.write(playerData[i]);
						bw.newLine();
					}
				} // end for
				bw.close();
				if (attribute.equals("name")) {
					File oldfile = new File((DIRECTORYNAME + File.separator + player + ".player")); // renames file //
																									// file
					File newfile = new File((DIRECTORYNAME + File.separator + value + ".player"));
					oldfile.renameTo(newfile);
				}
			}
		} // end try
		catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Datei ist leer - bitte �berpr�fen!");
		}
	}// end change property

	public static String getProperty(String player, String attribute) {
		String value = "test";
		try {
			FileReader fr = new FileReader(DIRECTORYNAME + File.separator + player + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[9];
			for (int i = 0; i < playerData.length; i++) { // reads all lines of
															// player file
				playerData[i] = br.readLine();
				System.out.println(playerData[i]); // @Simon �ndern
			}
			br.close();
			if (attribute == "name") {
				return playerData[0];
			} else if (attribute == "nrOfGames") {
				return playerData[1];
			} else if (attribute == "wins") {
				return playerData[2];
			} else if (attribute == "losses") {
				return playerData[3];
			} else if (attribute == "ties") {
				return playerData[4];
			} else if (attribute == "score") {
				return playerData[5];
			} else if (attribute == "icon") {
				return playerData[6];
			} else if (attribute == "color") {
				return playerData[7];
			} else if (attribute == "color") {
				return playerData[8];
			} else {
				System.out.println("Error DataManager");
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}// end get property

	public static void deletePlayer(String playerName) {
		Path path = Paths.get(DIRECTORYNAME + File.separator + playerName + ".player");
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
