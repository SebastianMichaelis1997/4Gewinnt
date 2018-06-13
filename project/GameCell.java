package project;

import javax.swing.*;
import java.awt.*;
public class GameCell extends JButton{
	
		private static final long serialVersionUID = 1L;
		private boolean isFilled;
		private int value;
		private int x_coordinate;
		private int y_coordinate;

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

