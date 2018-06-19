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

public class DataManager {

	public static String[] getPlayer(String filename) {
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\players\\" + filename + ".player");
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[9];
			for (int i = 0; i < playerData.length; i++) { // reads all lines of player file
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

	public static void changeProperty(String player, String attribute, String value) { // changes the given attribute
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
}
