package project;

import javax.swing.*;
import java.awt.*;

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
	 * Constructor, which returns a new Player object, necessary for initializing
	 * the main game.
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

	public Color getColor() {
		return color;
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