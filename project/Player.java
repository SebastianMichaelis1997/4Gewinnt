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

	public void setName(String name) {
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

	public void win() {
		this.nrOfGames++;
		this.wins++;
		this.calculateScore();
	}

	public void lose() {
		this.nrOfGames++;
		this.losses++;
		this.calculateScore();
	}

	public void tie() {
		this.nrOfGames++;
		this.ties++;
		this.calculateScore();
	}

	private void calculateScore() {
		this.score = this.wins * 3 + this.losses * -1;
	}
}