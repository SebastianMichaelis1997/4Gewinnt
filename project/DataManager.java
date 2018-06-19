package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.Iterator;
import java.util.*;

public class DataManager {
	
	public static ArrayList getAllPlayerNames() {
		final File folder = new File((System.getProperty("user.dir") + "\\src\\players\\"));
		ArrayList players = new ArrayList();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	getAllPlayerNames(fileEntry);
	        } else {
	            players.add(fileEntry.getName().substring(0,fileEntry.getName().indexOf('.'))); //adds just the file name ->cuts the ending off
	        }
	    }
	    return players;
	}
	
	public static ArrayList getAllPlayerNames(final File folder) { 	//for recursive method call -> Polymorphie
		ArrayList players = new ArrayList();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	getAllPlayerNames(fileEntry);
	        } else {
	            players.add(fileEntry.getName().substring(0,fileEntry.getName().indexOf('.'))); //adds just the file name ->cuts the ending off
	        }
	    }
	    return players;
	}


	public static String[] getPlayer(String filename) {
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\players\\" + filename + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[9];
			for (int i = 0; i < playerData.length; i++) { //reads all lines of player file
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
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\src\\players\\" + name + ".player");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(name);
			bw.newLine();
			for (int i = 0; i < 5; i++) { // Standard values
				bw.write(0);
				bw.newLine();
			}
			bw.write("null"); // Standard value
			bw.newLine();
			bw.write("red"); // Standard value
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeProperty(String player, String attribute, String value) { // changes the addressed attribute
		try {
			String[] playerData = getPlayer(player);
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\src\\players\\" + player + ".player"); 																									// file
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < playerData.length; i++) {
				if ((attribute == "name") && (i == 0)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "nrOfGames") && (i == 1)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "wins") && (i == 2)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "losses") && (i == 3)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "ties") && (i == 4)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "score") && (i == 5)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "icon") && (i == 6)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "color") && (i == 7)) {
					bw.write(value);
					bw.newLine();
				} else if ((attribute == "color") && (i == 8)) {
					bw.write(value);
					bw.newLine();
				} else {
					bw.write(playerData[i]);
					bw.newLine();
				}
			} // end for
			bw.close();
			if (attribute == "name") {
				File oldfile = new File((System.getProperty("user.dir") + "\\src\\players\\" + player + ".player")); //renames the file
				File newfile = new File((System.getProperty("user.dir") + "\\src\\players\\" + value + ".player"));
				oldfile.renameTo(newfile);
			}
		} // end try
		catch (IOException e) {
			e.printStackTrace();
		}
	}// end change property
	
	public static String getProperty(String player, String attribute) {
		String value = "test";
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\players\\" + player + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[9];
			for (int i = 0; i < playerData.length; i++) { // reads all lines of player file
				playerData[i] = br.readLine();
				System.out.println(playerData[i]);
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
			} else if (attribute == "ties"){
				return playerData[4];
			} else if (attribute == "score"){
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
}
