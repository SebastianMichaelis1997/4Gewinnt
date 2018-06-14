package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataManager {

	public static String[] getPlayer(String filename) {
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\players\\" + filename + ".player"); //@Simon noch zu bearbeiten
			BufferedReader br = new BufferedReader(fr);
			String[] playerData = new String[8];
			for (int i = 0; i < playerData.length; i++) {	//reads all lines of player file
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

	public static void addPlayer(String text){
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\src\\players\\" + text + ".player");
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(text);
			bw.newLine();
			for (int i = 0; i < 5; i++) {	//Standard values
				bw.write(0);
				bw.newLine();
			}
			bw.write("null");		//Standard value
			bw.newLine();
			bw.write("red");		//Standard value
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
