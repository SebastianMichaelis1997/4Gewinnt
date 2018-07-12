package testFiles;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import project.DataManager;
import java.util.ArrayList;
import project.PlayerAlreadyExistsException;

public class DataManagerTest {

	@Test
	public void correctImageShouldReturnTrue() {
		String filename = "download.png";
		try {
			assertEquals("Save Image Error: Return should be true.", true, DataManager.saveImage("profilePictures"+File.separator+filename, "testImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			Path path = Paths.get("profilePictures"+File.separator+"testImage.png");
			//just for clearing to run the test for another time - otherwise the image to save would already exist and would always return false
			try {
				System.out.println(path); 
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void getPlayerObjectShouldReturnPlayerObject() {
		assertNotNull("Get Player Object should return Player Object.", DataManager.getPlayerObj("Player1"));
	}
	
	
	/*@Test
	public void createAndDeletPlayerShouldReturnTrue() {
		try {
			ArrayList players = DataManager.getAllPlayerNames();
			assertSame(players.size(), DataManager.getAllPlayerNames().size());
			//assertEquals("Adding a player should be successfull.", true, DataManager.addPlayer("testPlayer"));
			DataManager.deletePlayer("testPlayer");
		} catch (PlayerAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void changeAllPropertys() {
		String[]player = DataManager.getPlayer("Player1");
		String[]properties = { "karl", "3", "2", "1", "4", "10" }; 
		//String[]propertyNames = { "name", "nrOfGames", "wins", "losses", "ties", "score", icon, };
		//for (int i = 0; i < propertyNames.length; i++) {
			
		//}
		
	}
	

}