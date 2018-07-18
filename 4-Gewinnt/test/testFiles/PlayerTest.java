package testFiles;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.ImageIcon;

import org.junit.Test;

import project.Player;

public class PlayerTest {

	@Test
	public void constructorShouldReturnPlayerAndChangeProperties() {
		ImageIcon image = new ImageIcon("");
		Color color = new Color(255, 0, 0);
		Player test1 = new Player("Test", image, color);
		Player test2 = new Player("Test", image, color);
		test1.changeName("Changed Name");
		assertFalse("Names should not be equal after changing!", test1.getName().equals(test2.getName()));
	}

	@Test
	public void calculateScoreIsCorrect() {
		ImageIcon image = new ImageIcon("");
		Color color = new Color(255, 0, 0);
		Player test1 = new Player("test", 10, 6, 4, 0, 0, image, color);
		test1.calculateScore();
		assertEquals("Score should be 14(6*3+4*-1)", 14, test1.getScore());
	}
}
