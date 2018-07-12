package project;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * This class contains all the information about players, like their stats and
 * settings.
 * 
 * @author Alexander Dreher, Enes Akgümüs, Tobias Rothley, Emma Falldorf, Simon
 *         Becht, Sebastian Michaelis
 *
 */
public class Player {

	private String name;
	private int nrOfGames;
	private int wins;
	private int losses;
	private int ties;
	private int score;
	private ImageIcon icon;
	private Color color;

	/**
	 * Constructor, which returns a new Player object directly at creation.
	 * 
	 * @param name
	 *            The name of the player.
	 * @param image
	 *            A chosen Image Icon.
	 * @param color
	 *            The "preferred" color (May change at conflicts).
	 */
	public Player(String name, ImageIcon image, Color color) {
		this.name = name;
		this.nrOfGames = 0;
		this.wins = 0;
		this.losses = 0;
		this.ties = 0;
		this.score = 0;
		this.icon = image;
		this.changeColor(color);
	}

	/**
	 * This constructor should be called, if we are loading all information from the
	 * text files.
	 * 
	 * @param name
	 *            The name of the player.
	 * @param nrOfGames
	 *            The number of played games.
	 * @param wins
	 *            The number of wins.
	 * @param losses
	 *            The number of losses.
	 * @param ties
	 *            The number of ties.
	 * @param score
	 *            The player score.
	 * @param image
	 *            The chosen image.
	 * @param color
	 *            The chosen color.
	 */
	public Player(String name, int nrOfGames, int wins, int losses, int ties, int score, ImageIcon image, Color color) {
		this.name = name;
		this.nrOfGames = nrOfGames;
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
		this.score = score;
		this.icon = image;
		this.color = color;
	}

	public Player(String[] playerInfo) {
		this.name = playerInfo[0];
		this.nrOfGames = Integer.parseInt(playerInfo[1]);
		this.wins = Integer.parseInt(playerInfo[2]);
		this.losses = Integer.parseInt(playerInfo[3]);
		this.ties = Integer.parseInt(playerInfo[4]);
		this.score = Integer.parseInt(playerInfo[5]);
		if (playerInfo[6] != null || !playerInfo[6].equals("")) {
			//this.icon = new ImageIcon(GameOverWindow.class.getResource("/project/" + playerInfo[6]));
		} else {
			// this.color = new Color(Integer.parseInt(playerInfo[7]));
			//new Color();
		}
		// this.color = new Color(Integer.parseInt(playerInfo[7]));
	}

	public String getName() {
		return name;
	}

	/**
	 * This method renames the player, but does not check if the name is already
	 * taken!
	 * 
	 * @param name
	 *            The new name.
	 */
	public void changeName(String name) {
		this.name = name;
	}

	public int getNrOfGames() {
		return nrOfGames;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public int getTies() {
		return ties;
	}

	public int getScore() {
		return score;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * This method replaces the current icon with a new one.
	 * 
	 * @param icon
	 *            The new icon.
	 */
	public void changeIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public Color getColor() {
		return color;
	}

	/**
	 * This method changes the preferred color of the user, but BLUE is reserved for
	 * the bottom line. Also standard color is RED.
	 * 
	 * @param color
	 *            The new color.
	 */
	public void changeColor(Color color) {
		if (color != null) {
			if (color == Color.BLUE) {
				this.color = Color.CYAN;
			} else {
				this.color = color;
			}
		} else {
			this.color = Color.RED;
		}
	}

	/*
	 * This method adds a win to the player and recalculates the score.
	 */
	public void win() {
		this.nrOfGames++;
		this.wins++;
		this.calculateScore();
	}

	/**
	 * This method adds a loss to the player and recalculates the score.
	 */
	public void lose() {
		this.nrOfGames++;
		this.losses++;
		this.calculateScore();
	}

	/**
	 * This method adds a tie to the players stats.
	 */
	public void tie() {
		this.nrOfGames++;
		this.ties++;
	}

	/**
	 * This method calculates the score of a player, which is used for the highscore
	 * board.
	 */
	private void calculateScore() {
		this.score = this.wins * 3 + this.losses * -1;
	}
}