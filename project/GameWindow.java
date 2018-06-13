package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow {

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
	private static JScrollPane scrollPane;

	private static int currentPlayer = 1;
	private static int height = 7;
	private static int length = 7;

	public static boolean gameIsOver = false;

	private static int[][] logicField = new int[height][length];
	private static GameCell[][] cellField = new GameCell[height][length];

	private static Color red = Color.RED;
	private static Color yellow = Color.YELLOW;

	public static void main(String[] args) throws AWTException { // not necessary, just for test case @Simon : Nach
																	// Fertigstellung Löschen

		initializeWindow();

		mainWindow.setVisible(true);

		loadField();

		log("PLAYER RED'S TURN");

	}

	public static void start() throws AWTException {

		initializeWindow();

		mainWindow.setVisible(true);

		loadField();

		log("PLAYER RED'S TURN");

	}

	private static void initializeWindow() {
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(1250, 700);
		mainWindow.setLocation(100, 100);
		mainWindow.setTitle("Vier Gewinnt!");
		// mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// mainWindow.setUndecorated(true);
		mainWindow.setResizable(false);

		optionsMenu = new JMenu("Optionen");
		newGameMenuItem = new JMenuItem("Neues Spiel");
		closeMenuItem = new JMenuItem("Beenden");
		closeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.dispose();
			}
		});
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
		infoTopPanel.setPreferredSize(new Dimension(475, 300));
		infoBottomPanel = new JPanel();

		GridLayout layout = new GridLayout(8, 1);
		infoTopPanel.setLayout(layout);

		JLabel playerOneLabel = new JLabel("Spieler 1:");
		JTextField playerOneName = new JTextField();
		playerOneName.setFont(new Font("Arial Bold", 14, 14));
		playerOneName.setBackground(red);
		playerOneName.setText("@ALEX Player 1");
		playerOneName.setEditable(false);
		JLabel playerOneVictoryLabel = new JLabel("Siege:");
		JLabel playerOneVictoryCount = new JLabel("0");

		JLabel playerTwoLabel = new JLabel("Spieler 2:");
		JTextField playerTwoName = new JTextField();
		playerTwoName.setFont(new Font("Arial Bold", 14, 14));
		playerTwoName.setBackground(yellow);
		playerTwoName.setText("@ALEX Player 2");
		playerTwoName.setEditable(false);
		JLabel playerTwoVictoryLabel = new JLabel("Siege");
		JLabel playerTwoVictoryCount = new JLabel("0");

		console = new JTextArea();
		scrollPane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(475, 300));
		console.setEditable(false);

		infoTopPanel.add(playerOneLabel);
		infoTopPanel.add(playerOneName);
		infoTopPanel.add(playerOneVictoryLabel);
		infoTopPanel.add(playerOneVictoryCount);
		infoTopPanel.add(playerTwoLabel);
		infoTopPanel.add(playerTwoName);
		infoTopPanel.add(playerTwoVictoryLabel);
		infoTopPanel.add(playerTwoVictoryCount);

		infoBottomPanel.add(scrollPane);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(infoTopPanel);
		splitPane.setBottomComponent(infoBottomPanel);
		// splitPane.setDividerLocation(1200);
		splitPane.setEnabled(false);

		infoPanel.add(splitPane);
	}

	private static void designGamePanel() {
		GridLayout layout = new GridLayout(height, length);
		gamePanel.setLayout(layout);
		GameCell currentField = null;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				// last row
				if (i == height - 1) {
					currentField = new GameCell(true, 3, Color.BLUE, i, j);
					logicField[i][j] = 3;
					currentField.fill();
				} else {
					currentField = new GameCell(false, 0, Color.WHITE, i, j);
				}
				currentField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameCell cField = (GameCell) e.getSource();
						evaluateClick(cField);
					}
				});
				gamePanel.add(currentField);
				cellField[i][j] = currentField;
			}
		}
	}

	private static void evaluateClick(GameCell field) {
		if (gameIsOver == false) {
			int x_coordinate = field.getX_Coordinate();
			int y_coordinate = field.getY_Coordinate();
			if (field.isFilled() == false) {
				if (isValid(x_coordinate, y_coordinate) && logicField[x_coordinate + 1][y_coordinate] != 0) {
					fillField(field, x_coordinate, y_coordinate);
					updateAfterTurn(x_coordinate, y_coordinate);
				} else {
					gravityFunction(field, x_coordinate, y_coordinate);
				}
			} else {
				System.out.println("Feld belegt!");
				log("Feld belegt!");
			}
		}
	}

	private static void gravityFunction(GameCell field, int x, int y) {
		for (int i = x; i < logicField.length; i++) {
			if (isValid(i, y) && logicField[i + 1][y] != 0) {
				fillField(cellField[i][y], i, y);
				updateAfterTurn(i, y);
				return;
			} else {

			}
		}
	}

	private static void fillField(GameCell field, int x, int y) {
		logicField[x][y] = currentPlayer;
		field.setValue(currentPlayer);
		field.fill();
		switch (currentPlayer) {
		case 1:
			field.setBackground(red);
			break;
		case 2:
			field.setBackground(yellow);
			break;
		}
	}

	public static void updateAfterTurn(int x, int y) {
		checkHorizontalWin(x, y);
		checkVerticalWin(x, y);
		checkDiagonalRightWin(x, y);
		checkDiagonalLeftWin(x, y);
		if (gameIsOver == false) {
			switch (currentPlayer) {
			case 1:
				currentPlayer = 2;
				log("PLAYER YELLOW'S TURN");
				break;
			case 2:
				currentPlayer = 1;
				log("PLAYER RED'S TURN");
				break;
			default:
				break;
			}
			if (currentPlayer == 2) {
				computerAI();
			}
		}
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
		gameIsOver = true;
		log("----------");
		if (currentPlayer == 1) {
			log("YOU HAVE WON, PLAYER RED");
		}
		if (currentPlayer == 2) {
			log("YOU HAVE WON, PLAYER YELLOW");
		}
		log("----------");
	}

	private static boolean isValid(int x, int y) {
		if ((x >= 0 && x < logicField.length) && (y >= 0 && y < logicField[0].length)) {
			return true;
		} else {
			return false;
		}
	}

	private static void log(String s) {
		console.append(" " + s + "\n");
	}

	private static void loadField() throws AWTException {
		Robot mouse = new Robot();
		for (int i = 0; i < cellField.length; i++) {
			for (int j = 0; j < cellField[i].length; j++) {
				mouse.mouseMove(120 + cellField[i][j].getX(), 160 + cellField[i][j].getY());
				try {
					Thread.sleep(1);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	private static int randomNrFrom0Ton(int n) {
		double rand = Math.random();
		int rnd = (int) (rand * n);
		return rnd;
	}

	private static void computerAI() {
		int x = 0;
		int y = randomNrFrom0Ton(6);
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		if (cellField[x][y].isFilled() == false) {
			evaluateClick(cellField[x][y]);
		} else {
			computerAI();
		}
	}
}
