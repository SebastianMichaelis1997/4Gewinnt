package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Viergewinnt {

	private static JFrame mainWindow;
	private static JMenuBar mainMenuBar;
	private static JMenu optionsMenu;
	private static JMenu helpMenu;
	private static JMenuItem newGameMenuItem;
	private static JMenuItem closeMenuItem;
	private static JMenuItem helpMenuItem;
	private static JPanel gamePanel;
	private static JPanel infoPanel;
	private static JPanel infoTopPanel;
	private static JPanel infoBottomPanel;
	private static JSplitPane splitPane;
	private static JTextArea console;

	private static int currentPlayer = 1;
	private static int height = 7;
	private static int length = 7;

	private static int[][] logicField = new int[height][length];
	private static Field[][] cellField = new Field[height][length];

	private static Color red = Color.RED;
	private static Color yellow = Color.YELLOW;

	public static void main(String[] args) {

		initializeWindow();

		mainWindow.setVisible(true);
		mainWindow.paintComponents(mainWindow.getGraphics());

	}

	private static void initializeWindow() {
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(1200, 700);
		mainWindow.setLocation(100, 100);
		mainWindow.setTitle("Vier Gewinnt!");
		mainWindow.setResizable(false);

		optionsMenu = new JMenu("Optionen");
		newGameMenuItem = new JMenuItem("Neues Spiel");
		closeMenuItem = new JMenuItem("Beenden");
		optionsMenu.add(newGameMenuItem);
		optionsMenu.add(closeMenuItem);

		helpMenu = new JMenu("Hilfe");
		helpMenuItem = new JMenuItem("Hilfe");
		helpMenu.add(helpMenuItem);

		mainMenuBar = new JMenuBar();
		mainMenuBar.add(optionsMenu);
		mainMenuBar.add(helpMenu);

		mainWindow.setJMenuBar(mainMenuBar);

		infoPanel = new JPanel();
		gamePanel = new JPanel();

		designInfoPanel();
		designGamePanel();

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setRightComponent(infoPanel);
		splitPane.setLeftComponent(gamePanel);
		splitPane.setDividerLocation(700);
		splitPane.setEnabled(false);

		mainWindow.add(splitPane);
	}

	private static void designInfoPanel() {

		infoTopPanel = new JPanel();
		infoBottomPanel = new JPanel();

		GridLayout layout = new GridLayout(8, 1);
		infoTopPanel.setLayout(layout);

		JLabel playerOneLabel = new JLabel("Spieler 1");
		JTextField playerOneName = new JTextField();
		JLabel playerOneVictoryLabel = new JLabel("Siege:");
		JLabel playerOneVictoryCount = new JLabel("0");

		JLabel playerTwoLabel = new JLabel("Spieler 2");
		JTextField playerTwoName = new JTextField();
		JLabel playerTwoVictoryLabel = new JLabel("Siege");
		JLabel playerTwoVictoryCount = new JLabel("0");

		console = new JTextArea();

		infoTopPanel.add(playerOneLabel);
		infoTopPanel.add(playerOneName);
		infoTopPanel.add(playerOneVictoryLabel);
		infoTopPanel.add(playerOneVictoryCount);
		infoTopPanel.add(playerTwoLabel);
		infoTopPanel.add(playerTwoName);
		infoTopPanel.add(playerTwoVictoryLabel);
		infoTopPanel.add(playerTwoVictoryCount);

		infoBottomPanel.add(console);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(infoTopPanel);
		splitPane.setBottomComponent(infoBottomPanel);
		splitPane.setDividerLocation(1200);
		splitPane.setEnabled(false);

		infoPanel.add(splitPane);
	}

	private static void designGamePanel() {
		GridLayout layout = new GridLayout(height, length);
		gamePanel.setLayout(layout);
		Field currentField = null;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				// last row
				if (i == height - 1) {
					currentField = new Field(true, 3, Color.BLUE, i, j);
					logicField[i][j] = 3;
					currentField.fill();
				} else {
					currentField = new Field(false, 0, Color.WHITE, i, j);
				}
				currentField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Field cField = (Field) e.getSource();
						evaluateClick(cField);
					}
				});
				gamePanel.add(currentField);
				cellField[i][j] = currentField;
			}
		}
	}

	private static void evaluateClick(Field field) {
		int x_coordinate = field.getX();
		int y_coordinate = field.getY();
		if (field.isFilled() == false) {
			if (isValid(x_coordinate, y_coordinate) && logicField[x_coordinate + 1][y_coordinate] != 0) {
				fillField(field, x_coordinate, y_coordinate);
				updateAfterTurn(x_coordinate, y_coordinate);
			} else {
				gravityFunction(field, x_coordinate, y_coordinate);
			}
		} else {
			System.out.println("Feld belegt!");
		}
	}

	private static void gravityFunction(Field field, int x, int y) {
		for (int i = x; i < logicField.length; i++) {
			if (isValid(i, y) && logicField[i + 1][y] != 0) {
				fillField(cellField[i][y], i, y);
				updateAfterTurn(i, y);
				return;
			} else {

			}
		}
	}

	private static void fillField(Field field, int x, int y) {
		logicField[x][y] = currentPlayer;
		field.setValue(currentPlayer);
		field.fill();
		switch (currentPlayer) {
		case 1:
			field.setBackground(red);
			currentPlayer = 2;
			break;
		case 2:
			field.setBackground(yellow);
			currentPlayer = 1;
			break;
		}
	}

	public static void updateAfterTurn(int x, int y) {
		checkHorizontalWin(x, y);
		checkVerticalWin(x, y);
		checkDiagonalRightWin(x, y);
		checkDiagonalLeftWin(x, y);
	}

	private static void checkHorizontalWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;
		int x_new = x;

		int y_new = y - 1;
		// linke reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
		}
		y_new = y + 1;
		// rechte reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	private static void checkVerticalWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;
		int y_new = y;

		int x_new = x - 1;
		// obere reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			x_new = x_new - 1;
		}
		x_new = x + 1;
		// untere reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			x_new = x_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	private static void checkDiagonalRightWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;

		int y_new = y + 1;
		int x_new = x - 1;
		// oben rechte reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
			x_new = x_new - 1;
		}
		y_new = y - 1;
		x_new = x + 1;
		// unten linke reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
			x_new = x_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	private static void checkDiagonalLeftWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;

		int y_new = y - 1;
		int x_new = x - 1;
		// oben linke reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
			x_new = x_new - 1;
		}
		y_new = y + 1;
		x_new = x + 1;
		// unten rechte reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
			x_new = x_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	private static void win() {
		if (currentPlayer == 1) {
			System.out.println("YOU HAVE WON, PLAYER YELLOW");
		}
		if (currentPlayer == 2) {
			System.out.println("YOU HAVE WON, PLAYER RED");
		}

	}

	private static boolean isValid(int x, int y) {
		if ((x >= 0 && x < logicField.length) && (y >= 0 && y < logicField[0].length)) {
			return true;
		} else {
			return false;
		}
	}

}
