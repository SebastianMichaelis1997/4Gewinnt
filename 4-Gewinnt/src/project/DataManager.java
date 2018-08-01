package project;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
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

import javax.swing.ImageIcon;

import exceptions.DataManagerErrorException;
import exceptions.PlayerAlreadyExistsException;

/***
 * This class should represent all possible interactions with file systems, like
 * loading player data as well as icons and such.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
public class DataManager {
	private static final String PLAYER_DIRECTORY = "players";
	private static final String PLAYER_PICTURES_DIRECTORY = "profilePictures";
	private static final String RESOURCE_DIRECTORY = "resources";

	/**
	 * This method saves a selected image during player creation in the resources
	 * folder.
	 * 
	 * @param path
	 *            Path of the selected image.
	 * @param filename
	 *            Name, under which it will be saved,
	 * @return True, if successful, false otherwise.
	 * @throws IOException
	 *             If the file could not be read, or could not be saved.
	 */
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
			try (FileChannel srcChannel = new FileInputStream(source.toString()).getChannel();
					FileChannel destChannel = new FileOutputStream(destination.toString()).getChannel();) {
				srcChannel.transferTo(0, srcChannel.size(), destChannel);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * This method deletes a player image in the profilePictures folder.
	 * 
	 * @param filename
	 *            Name, under which it was saved,
	 * @return True, if successful, false otherwise.
	 * @throws IOException
	 *             If the file could not be read, or could not be saved.
	 */
	public static void deleteImage(String filename) throws IOException {
		checkDirectory("profilePictures");
		Path path = Paths.get(PLAYER_PICTURES_DIRECTORY + File.separator + filename);
		Files.delete(path);
	}

	/**
	 * This method checks, if the 3 big directories for the game are already
	 * created, and if not creates them. This method also restores the folder
	 * structure, if deleted by accident.
	 * 
	 * @param name
	 *            One of the names of the directories, PLAYER, RESOURCE or
	 *            PLAYER_PRICTURES.
	 */
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
					bw.write(resourcePath);
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

	/**
	 * This method returns a list of all names, which are contained within the
	 * player folder.
	 * 
	 * @return A list of all player names.
	 */
	public static ArrayList<String> getAllPlayerNames() {
		checkDirectory(PLAYER_DIRECTORY);
		final File folder = new File(PLAYER_DIRECTORY);
		ArrayList<String> players = new ArrayList<String>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				getAllPlayerNames(fileEntry);
			} else {
				players.add(fileEntry.getName().substring(0, fileEntry.getName().indexOf('.')));
				// adds just the file name ->cuts the ending off
			}
		}
		return players;
	}

	/**
	 * This is an overloaded method for recursion.
	 * 
	 * @param folder
	 *            The name of the folder, from which teh names should be taken.
	 * @return A list of all player names.
	 */
	public static ArrayList<String> getAllPlayerNames(final File folder) {
		// for recursive method call -> Überladung
		checkDirectory(PLAYER_DIRECTORY);
		ArrayList<String> players = new ArrayList<String>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				getAllPlayerNames(fileEntry);
			} else {
				players.add(fileEntry.getName().substring(0, fileEntry.getName().indexOf('.')));
				// adds just the file name ->cuts the ending off
			}
		}
		return players;
	}

	/**
	 * This method creates a player object directly by searching the file, and
	 * calling the constructor with all information.
	 * 
	 * @param filename
	 *            The name of the player file.
	 * @return The player object with all the information.
	 */
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

	/**
	 * This method indirectly creates a player, as that it gather all the
	 * information from the file, and writes it into an array, which can be used for
	 * a certain player constructor.
	 * 
	 * @param filename
	 *            The name of the player file.
	 * @return An array, with the information: name, nrOfGames, wins, losses, ties,
	 *         color and icon.
	 */
	public static String[] getPlayer(String filename) {
		try {
			FileReader fr = new FileReader(PLAYER_DIRECTORY + File.separator + filename + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[8];
			for (int i = 0; i < playerData.length; i++) {
				// reads all lines of player file
				playerData[i] = br.readLine();
			}
			br.close();
			return playerData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method creates a new file with the chosen name and sets the color
	 * attribute inside it. It sets all other values like wins and such to 0.
	 * 
	 * @param name
	 *            The name of the file.
	 * @param color
	 *            The chosen color of the player.
	 * @return True, if it was successful, false if not.
	 * @throws PlayerAlreadyExistsException
	 *             If the filename is already taken.
	 */
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
		} catch (PlayerAlreadyExistsException ex) {
			ErrorWindow.start(ex.getMessage());
		}
		return false;
	}

	/**
	 * This method checks for duplicate filenames, before problems with renaming
	 * occur.
	 * 
	 * @param name
	 *            The name of the file.
	 * @param checkCase
	 *            Case-sensitivity true or false.
	 * @return True, if file already exists, false otherwise.
	 */
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

	/**
	 * This method changes a line in a player file, specifically the given player
	 * and attribute, to a certain value.
	 * 
	 * @param player
	 *            The name of the player.
	 * @param attribute
	 *            The attribute to change. Choices are: name, nrOfGames, wins,
	 *            losses, ties, color, icon
	 * @param value
	 *            The new value of the attribute.
	 * @throws PlayerAlreadyExistsException
	 *             If the renaming of the file was not possible.
	 */
	public static void changeProperty(String player, String attribute, String value)
			throws PlayerAlreadyExistsException {
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
	}

	/**
	 * This method returns the chosen property from the player file.
	 * 
	 * @param player
	 *            The name of the player.
	 * @param attribute
	 *            The chosen attribute. Choices are: name, nrOfGames, wins, losses,
	 *            ties, score, icon, color
	 * @return
	 */
	public static String getProperty(String player, String attribute) {
		try {
			FileReader fr = new FileReader(PLAYER_DIRECTORY + File.separator + player + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[8];
			for (int i = 0; i < playerData.length; i++) {
				// reads all lines of player file
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
			// return value;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DataManagerErrorException e1) {
			ErrorWindow.start(e1.getMessage());
		}
		return null;
	}

	/**
	 * This method deletes a file with the given name in the player folder.
	 * 
	 * @param playerName
	 *            The name of the file to be deleted.
	 */
	public static void deletePlayer(String playerName) {
		try {
			DataManager.deleteImage(DataManager.getProperty(playerName, "icon"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}
		Path path = Paths.get(PLAYER_DIRECTORY + File.separator + playerName + ".player");
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorWindow.start("Player could not be deleted!");
		}
	}
	
	public static ImageIcon resizeIcon(ImageIcon icon, int width, int height){
		java.awt.Image image = icon.getImage(); // transform it
		java.awt.Image newimg = image.getScaledInstance(99, 92,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		return new ImageIcon(newimg);  // transform it back
	}
	
	
	// This method resizes the BufferedImage to specified width and height.
		// Returns an ImageIcon object.
		public static ImageIcon resizeImage(BufferedImage image, int width, int height) {

			// image - BufferedImage object of your file selected
			// width - Width of your JLabel
			// height - Height of your JLabel

			// Creating a temporary Image of your desired size.
			BufferedImage tempImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = tempImg.createGraphics();

			g.drawImage(image, 0, 0, width, height, null); // Draw the selected image on the tempImage from co-ordinates (0,
															// 0) to (width, height) of the tempImage.
			g.dispose(); // Clear resources.

			return new ImageIcon(tempImg);
		}
}
