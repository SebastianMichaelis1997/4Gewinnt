package testFiles;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import exceptions.PlayerAlreadyExistsException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import project.DataManager;
import java.util.ArrayList;

public class DataManagerTest {

	@Test
	public void correctImageShouldReturnTrue() {
		String filename = "error.jpg";
		try {
			assertEquals("Save Image Error: Return should be true.", true, DataManager.saveImage("systemPictures"+File.separator+filename, "testImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			Path path = Paths.get("profilePictures"+File.separator+"testImage.png");
			//just for clearing to run the test for another time - otherwise the image to save would already exist and would always return false
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
		}
	}
	
	
	
	@Test
	public void createAndDeletPlayerShouldReturnTrue() {
		ArrayList players = DataManager.getAllPlayerNames();
		assertSame(players.size(), DataManager.getAllPlayerNames().size());
		try {
			assertEquals("Adding a player should be successfull.", true, DataManager.addPlayer("testPlayer","green"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataManager.deletePlayer("testPlayer");
	}
	
	@Test
	public void getPlayerObjectShouldReturnPlayerObject() {
		try {
			DataManager.addPlayer("testPlayer", "red");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull("Get Player Object should return Player Object.", DataManager.getPlayerObj("testPlayer"));
		DataManager.deletePlayer("testPlayer");
	}
	
	@Test
	public void changeAllPropertys() {
		String filename = "download.png";
		try {
			DataManager.addPlayer("testPlayer3", "red");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//a new player gets created
		String[]player = DataManager.getPlayer("testPlayer3");
		String[]properties = { "testPlayer3", "3", "2", "1", "4", "10", ("profilePictures"+File.separator+filename), "green" }; 
		String[]propertyNames = { "name", "nrOfGames", "wins", "losses", "ties", "score", "icon", "color" };
		for (int i = 0; i < propertyNames.length; i++) {
			//we change every property of the player
			if(i == 0){
			try {
				DataManager.changeProperty("testPlayer3", propertyNames[i], properties[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			else {
				try {
					DataManager.changeProperty("testPlayer3", propertyNames[i], properties[i]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		player = DataManager.getPlayer("testPlayer3");
		for (int i = 0; i < propertyNames.length; i++) {
			//and check if our changes were successful
			assertEquals("Error while changing a propertie of player!",player[i],properties[i]);
		}	
		DataManager.deletePlayer("testPlayer3");
	}
	

}