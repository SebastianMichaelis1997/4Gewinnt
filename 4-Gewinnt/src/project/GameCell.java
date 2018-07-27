package project;

import java.awt.Color;
import javax.swing.JButton;

/***
 * This custom class extends the button class in a way, to make it usable for
 * the game. It contains coordinate attributes within the logical game array.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
public class GameCell extends JButton {

	private static final long serialVersionUID = 1L;
	private boolean isFilled;
	private int value;
	private int x_coordinate;
	private int y_coordinate;

	/**
	 * The constructor of this class.
	 * 
	 * @param isFilled
	 *            This defines, whether this is already taken, or not.
	 * @param value
	 *            The players value, e.g. 1 or 2.
	 * @param color
	 *            The background color of this button.
	 * @param x
	 *            The x coordinate in the array.
	 * @param y
	 *            The y coordinate in the array.
	 */
	public GameCell(boolean isFilled, int value, Color color, int x, int y) {
		this.isFilled = isFilled;
		this.value = value;
		this.setBackground(color);
		this.x_coordinate = x;
		this.y_coordinate = y;
	}

	/*
	 * public void paint(Graphics g) {
	 * 
	 * }
	 */

	public boolean isFilled() {
		return this.isFilled;
	}

	public void fill() {
		this.isFilled = true;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getX_Coordinate() {
		return this.x_coordinate;
	}

	public int getY_Coordinate() {
		return this.y_coordinate;
	}

}
