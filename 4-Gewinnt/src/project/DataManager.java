package project;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.util.*;

import exceptions.DataManagerErrorException;
import exceptions.PlayerAlreadyExistsException;

public class DataManager {
	private static final String PLAYER_DIRECTORY = "players";
	private static final String PLAYER_PICTURES_DIRECTORY = "profilePictures";
	private static final String RESOURCE_DIRECTORY = "resources"; //@Simon @Tobi das wird doch nicht mehr verwendet?

	public static boolean saveImage(String path, String filename) throws IOException {
		checkDirectory("profilePictures"); 
		Path source = Paths.get(path);
		File directory = new File(PLAYER_PICTURES_DIRECTORY + File.separator + filename);
		if (directory.exists())
			// In case that user imports an image with a name, which is
			// already used for an other image in file "profilePictures"
			return false;
		else {
		Path destination = Paths.get(PLAYER_PICTURES_DIRECTORY + File.separator + filename);
		FileChannel srcChannel = new FileInputStream(source.toString()).getChannel();
		FileChannel destChannel = new FileOutputStream(destination.toString()).getChannel();
		try {
			srcChannel.transferTo(0, srcChannel.size(), destChannel);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (srcChannel != null)
				srcChannel.close();
			if (destChannel != null)
				destChannel.close();
		}
		}
	}

	public static void checkDirectory(String name) {
		if (name.equals(PLAYER_DIRECTORY)) {
			// Check if directory exists
			File directory = new File(PLAYER_DIRECTORY);
			if (!directory.exists()) {
				directory.mkdir(); // if not, create the directory
			}

		} else if (name.equals(RESOURCE_DIRECTORY)) {
			try {
				// Check if directory exists
				File directory = new File(RESOURCE_DIRECTORY);
				if (!directory.exists()) {
					directory.mkdir(); // if not, create the directory

					String resourcePath = RESOURCE_DIRECTORY + File.separator + "ReadMe.txt";
					File file = new File(resourcePath);
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					// Always wrap FileWriter in BufferedWriter.
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(ReadMe.README);
					bw.close();
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (name.equals(PLAYER_PICTURES_DIRECTORY)) {
			File directory = new File(PLAYER_PICTURES_DIRECTORY);
			if (!directory.exists()) {
				directory.mkdir(); // if not, create the directory
			}
		}
	}

	public static ArrayList getAllPlayerNames() {
		checkDirectory(PLAYER_DIRECTORY);
		final File folder = new File(PLAYER_DIRECTORY);
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
																			// Ãœberladung
		checkDirectory(PLAYER_DIRECTORY);
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

	public static Player getPlayerObj(String filename) {
		try {
			FileReader fr = new FileReader(PLAYER_DIRECTORY + File.separator + filename + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[8];
			for (int i = 0; i < playerData.length; i++) { 
				// reads all lines of player file
				playerData[i] = br.readLine();
			}
			br.close();
			return new Player(playerData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] getPlayer(String filename) {
		try {
			FileReader fr = new FileReader(PLAYER_DIRECTORY + File.separator + filename + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[8];
			for (int i = 0; i < playerData.length; i++) { // reads all lines of
															// player file
				playerData[i] = br.readLine();
			}
			br.close();
			return playerData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean addPlayer(String name, String color) throws PlayerAlreadyExistsException {
		String resourcePath = PLAYER_DIRECTORY + File.separator + name + ".player";
		// Check if directory exists
		checkDirectory(PLAYER_DIRECTORY);
		File file = new File(resourcePath);
		try {
			if (!checkPlayerExists(name, false)) {
				// Assume default encoding.
				FileWriter fw = new FileWriter(file.getAbsolutePath());

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
				bw.write(color); // Selected color
				bw.close();
				return true;
			} else {
				throw new PlayerAlreadyExistsException(name, "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean checkPlayerExists(String name, boolean checkCase) {
		ArrayList<String> players = getAllPlayerNames();
		if (checkCase) {
			for (String player : players) {
				if (name.equals(player)) {
					return true;
				}
			}
		} else if (!checkCase) {
			for (String player : players) {
				if (name.equalsIgnoreCase(player)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void changeProperty(String player, String attribute, String value)
			throws PlayerAlreadyExistsException { // changes the addressed
		String resourcePath = PLAYER_DIRECTORY + File.separator + player + ".player"; // attribute
		try {
			if (checkPlayerExists(value, true) & attribute.equals("name") & !player.equals(value)) {
				throw new PlayerAlreadyExistsException(value, "Couldn't save changes!");
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
					File oldfile = new File((PLAYER_DIRECTORY + File.separator + player + ".player")); // renames file
																										// //
																										// file
					File newfile = new File((PLAYER_DIRECTORY + File.separator + value + ".player"));
					oldfile.renameTo(newfile);
				}
			}
		} // end try
		catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			ErrorWindow.start("Datei ist leer - bitte überprüfen!");
		}
	}// end change property

	public static String getProperty(String player, String attribute) {
		String value = "test";
		try {
			FileReader fr = new FileReader(PLAYER_DIRECTORY + File.separator + player + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[8];
			for (int i = 0; i < playerData.length; i++) { // reads all lines of
															// player file
				playerData[i] = br.readLine();
	
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
			} else {
				throw new DataManagerErrorException("");
			}
			//return value;
		} catch (IOException e) {
			e.printStackTrace();
		}catch(DataManagerErrorException e1) {
			ErrorWindow.start(e1.getMessage());
		}
		return null;
	}// end get property

	public static void deletePlayer(String playerName) {
		Path path = Paths.get(PLAYER_DIRECTORY + File.separator + playerName + ".player");
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorWindow.start("Player could not be deleted!");
			e.printStackTrace();
		}
	}
}



