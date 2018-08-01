package project;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//import exceptions.PlayerAlreadyExistsException;

/***
 * This class represents the board of the game, with the actual field to play
 * on, as well as secondary information and a output console.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
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
	private static int maximumStones = (height - 1) * length;
	private static int setStones = 0;

	public static boolean gameIsOver = false;

	private static int[][] logicField = new int[height][length];
	private static int[][] simulatedField = new int[height][length];
	private static GameCell[][] cellField = new GameCell[height][length];

	private static Player firstPlayer;
	private static Player secondPlayer;
	private static JLabel lblFirstPlayerIcon;
	private static JLabel lblSecondPlayerIcon;

	private static SoundManager ambient;

	/**
	 * This method initializes a new board with the given Players.
	 * 
	 * @param player1
	 *            The first player.
	 * @param player2
	 *            The second player.
	 * @throws AWTException
	 *             If no mouse is detected, to load the field.
	 * @wbp.parser.entryPoint
	 */
	public static void start(Player player1, Player player2) throws AWTException {

		startMusic("ambientGame");
		currentPlayer = 1;
		firstPlayer = player1;
		secondPlayer = player2;

		initializeWindow();

		mainWindow.setVisible(true);

		loadField();

		initComputers();

	}

	/**
	 * This method creates the whole window, with the first part being the actual
	 * game field, and the second part being the player information, and the
	 * console.
	 */
	private static void initializeWindow() {
		// reset operations
		gameIsOver = false;
		for (int i = 0; i < cellField.length; i++) {
			for (int j = 0; j < cellField[i].length; j++) {
				cellField[i][j] = null;
				logicField[i][j] = 0;
				setStones = 0;
			}
		}
		try { // setzen des Look-And-Feel
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			// die Oberfläche wird mit dem neuen LAF angezeigt
		}catch (Exception e){
			
		}
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainWindow.setSize(1250, 700);
		mainWindow.setLocation(100, 100);
		mainWindow.setTitle("Vier Gewinnt!");
		mainWindow.setResizable(false);

		optionsMenu = new JMenu("Optionen");
		newGameMenuItem = new JMenuItem("Neues Spiel");
		closeMenuItem = new JMenuItem("Beenden");
		closeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.dispose();
				MainWindow.toFront2();
			}
		});
		optionsMenu.add(newGameMenuItem);
		optionsMenu.add(closeMenuItem);

		helpMenu = new JMenu("Hilfe");
		helpMenuItem = new JMenuItem("Read Me!");
		helpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReadMeWindow();
			}
		});
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

		mainWindow.getContentPane().add(splitPane);
	}

	/**
	 * This method creates the right part of the frame, with the player information.
	 */
	private static void designInfoPanel() {

		infoTopPanel = new JPanel();
		infoTopPanel.setPreferredSize(new Dimension(475, 300));
		infoBottomPanel = new JPanel();

		JLabel playerOneLabel = new JLabel("Spieler 1:");
		playerOneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerOneLabel.setBounds(43, 46, 121, 37);
		JTextField playerOneName = new JTextField();
		playerOneName.setBounds(43, 88, 191, 37);
		playerOneName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerOneName.setBackground(firstPlayer.getColor());
		playerOneName.setText(firstPlayer.getName());

		JLabel playerTwoLabel = new JLabel("Spieler 2:");
		playerTwoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerTwoLabel.setBounds(43, 165, 121, 37);
		JTextField playerTwoName = new JTextField();
		playerTwoName.setBounds(43, 209, 191, 37);
		playerTwoName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerTwoName.setBackground(secondPlayer.getColor());
		playerTwoName.setText(secondPlayer.getName());
		playerTwoName.setEditable(false);

		console = new JTextArea();
		scrollPane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(475, 300));
		console.setEditable(false);
		infoTopPanel.setLayout(null);

		infoTopPanel.add(playerOneLabel);
		infoTopPanel.add(playerOneName);
		infoTopPanel.add(playerTwoLabel);
		infoTopPanel.add(playerTwoName);

		infoBottomPanel.add(scrollPane);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(infoTopPanel);

		lblFirstPlayerIcon = new JLabel("Icon1");
		lblFirstPlayerIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstPlayerIcon.setIcon(firstPlayer.getIcon());
		lblFirstPlayerIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstPlayerIcon.setBounds(341, 35, 105, 102);
		infoTopPanel.add(lblFirstPlayerIcon);

		lblSecondPlayerIcon = new JLabel("Icon2");
		lblSecondPlayerIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSecondPlayerIcon.setIcon(secondPlayer.getIcon());
		lblSecondPlayerIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondPlayerIcon.setBounds(341, 165, 105, 102);
		infoTopPanel.add(lblSecondPlayerIcon);

		splitPane.setBottomComponent(infoBottomPanel);
		// splitPane.setDividerLocation(1200);
		splitPane.setEnabled(false);

		infoPanel.add(splitPane);
	}

	/**
	 * THis method creates the game field in the left side of the frame.
	 */
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

	/**
	 * This method takes place after a click, and decides what it should do.
	 * 
	 * @param field
	 *            The clicked cell.
	 */
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
				log("Feld belegt!");
			}
		}
	}

	/**
	 * This function lets tokens slide to the bottom of the board.
	 * 
	 * @param field
	 *            The clicked cell to be filled.
	 * @param x
	 *            The x coordinate of the field.
	 * @param y
	 *            The y coordinate of the field.
	 */
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

	/**
	 * This method acts as the logical "filling" of a field, by invisibly advancing
	 * the logic array with the players value, and also visibly filling in the color
	 * or icon of the respective player on the board
	 * 
	 * @param field
	 *            The field to be filled.
	 * @param x
	 *            The x coordinate of the field.
	 * @param y
	 *            The y coordinate of the field.
	 */
	private static void fillField(GameCell field, int x, int y) {
		logicField[x][y] = currentPlayer;
		field.setValue(currentPlayer);
		log("Filled " + x + " " + y);
		field.fill();
		setStones++;
		switch (currentPlayer) {
		case 1:
			if (firstPlayer.getIcon() != null) {
				field.setIcon(firstPlayer.getIcon());
			} else {
				if (firstPlayer.getColor() != null) {
					field.setBackground(firstPlayer.getColor());
				} else {
					field.setBackground(Color.RED);
				}
			}
			break;
		case 2:
			if (secondPlayer.getIcon() != null) {
				field.setIcon(secondPlayer.getIcon());
			} else {
				if (secondPlayer.getColor() != null) {
					field.setBackground(secondPlayer.getColor());
				} else {
					field.setBackground(Color.GREEN);
				}
			}
			break;
		}
	}

	/**
	 * This method handles the switch of a player, a fter he had his turn. It first
	 * checks for any win condition, and then swaps to the other player. Also the
	 * AI-logic should be called here.
	 * 
	 * @param x
	 *            The x coordinate of the last filled cell.
	 * @param y
	 *            The y coordinate of the last filled cell.
	 */
	public static void updateAfterTurn(int x, int y) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {

		}
		if (setStones == maximumStones) {
			draw();
		}
		checkHorizontalWin(x, y);
		checkVerticalWin(x, y);
		checkDiagonalRightWin(x, y);
		checkDiagonalLeftWin(x, y);
		if (gameIsOver == false) {
			switch (currentPlayer) {
			case 1:
				currentPlayer = 2;
				if (secondPlayer.getName().equals("EasyComputerKI")) {
					EasyComputerAI();
					log("EasyComputerKI's turn!");
				} else if (secondPlayer.getName().equals("HardComputerKI")) {
					HardComputerAI();
					log("HardComputerKI's turn!");
				} else {
					log("Player " + secondPlayer.getName() + "'s turn!");
					break;
				}
			case 2:
				currentPlayer = 1;
				if (firstPlayer.getName().equals("EasyComputerKI")) {
					EasyComputerAI();
					log("EasyComputerKI's turn!");
				} else if (firstPlayer.getName().equals("HardComputerKI")) {
					HardComputerAI();
					log("HardComputerKI's turn!");
				} else {
					log("Player " + firstPlayer.getName() + "'s turn!");
					break;
				}
			default:
				break;
			}
		}

	}

	/**
	 * This methods ties the game and finishes it.
	 */
	private static void draw() {
		gameIsOver = true;
		log("----------");
		log("Unentschieden!");
		log("----------");
		updatePlayersDraw();
	}

	/**
	 * This method checks for a horizontal win, e.g. 4 or more cells have the same
	 * value, starting from the last filled cell.
	 * 
	 * @param x
	 *            The x coordinate of the last filled cell.
	 * @param y
	 *            The y coordinate of the last filled cell.
	 */
	private static void checkHorizontalWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;
		int x_new = x;

		int y_new = y - 1;
		// linke reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
		}
		y_new = y + 1;
		// rechte reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	/**
	 * This method checks for a vertical win, e.g. 4 or more cells have the same
	 * value, starting from the last filled cell.
	 * 
	 * @param x
	 *            The x coordinate of the last filled cell.
	 * @param y
	 *            The y coordinate of the last filled cell.
	 */
	private static void checkVerticalWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;
		int y_new = y;

		int x_new = x - 1;
		// obere reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			x_new = x_new - 1;
		}
		x_new = x + 1;
		// untere reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			x_new = x_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	/**
	 * This method checks for a diagonally win from bottom to top, e.g. 4 or more
	 * cells have the same value, starting from the last filled cell.
	 * 
	 * @param x
	 *            The x coordinate of the last filled cell.
	 * @param y
	 *            The y coordinate of the last filled cell.
	 */
	private static void checkDiagonalRightWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;

		int y_new = y + 1;
		int x_new = x - 1;
		// oben rechte reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
			x_new = x_new - 1;
		}
		y_new = y - 1;
		x_new = x + 1;
		// unten linke reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
			x_new = x_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	/**
	 * This method checks for a diagonally win from top to bottom, e.g. 4 or more
	 * cells have the same value, starting from the last filled cell.
	 * 
	 * @param x
	 *            The x coordinate of the last filled cell.
	 * @param y
	 *            The y coordinate of the last filled cell.
	 */
	private static void checkDiagonalLeftWin(int x, int y) {
		int value = logicField[x][y];
		int counter = 1;

		int y_new = y - 1;
		int x_new = x - 1;
		// oben linke reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
			x_new = x_new - 1;
		}
		y_new = y + 1;
		x_new = x + 1;
		// unten rechte reichweite
		while (isValid(x_new, y_new) && logicField[x_new][y_new] != 0 && logicField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
			x_new = x_new + 1;
		}
		// wincheck
		if (counter >= 4) {
			win();
		}
	}

	/**
	 * This method is called after a game is over, and handles the logic for the
	 * winning window.
	 */
	public static void gameOver() {
		String winner = "";
		if (currentPlayer == 1) {
			winner = firstPlayer.getName();
		}
		if (currentPlayer == 2) {
			winner = secondPlayer.getName();
		}
		GameOverWindow gow = new GameOverWindow(winner, firstPlayer, secondPlayer);
		gow.setVisible(true);
	}

	/**
	 * This method is called, after a win condition is fullfilled, and handles the
	 * logic of printing the winner on console and window. It also updates the
	 * player Objects, and writes them back into the files.
	 */
	private static void win() {
		gameIsOver = true;
		gameOver();
		log("----------");
		if (currentPlayer == 1) {
			log("Player " + firstPlayer.getName() + " has won!");
			firstPlayer.win();
			secondPlayer.lose();
		}
		if (currentPlayer == 2) {
			log("Player " + secondPlayer.getName() + " has won!");
			firstPlayer.lose();
			secondPlayer.win();
		}
		log("----------");
		updatePlayersWin();
	}

	/**
	 * This method updates the player files in case of a win.
	 */
	private static void updatePlayersWin() {
		if (firstPlayer.getName() != "EasyComputerKI" && firstPlayer.getName() != "HardComputerKI") {
			try {
				DataManager.changeProperty(firstPlayer.getName(), "wins", firstPlayer.getWins() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(firstPlayer.getName(), "nrOfGames", firstPlayer.getNrOfGames() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(firstPlayer.getName(), "losses", firstPlayer.getLosses() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(firstPlayer.getName(), "score", firstPlayer.getScore() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (secondPlayer.getName() != "EasyComputerKI" && secondPlayer.getName() != "HardComputerKI") {
			try {
				DataManager.changeProperty(secondPlayer.getName(), "wins", secondPlayer.getWins() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(secondPlayer.getName(), "nrOfGames", secondPlayer.getNrOfGames() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(secondPlayer.getName(), "losses", secondPlayer.getLosses() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(secondPlayer.getName(), "score", secondPlayer.getScore() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method updates the player file in case of a tie.
	 */
	private static void updatePlayersDraw() {
		firstPlayer.tie();
		secondPlayer.tie();
		if (firstPlayer.getName() != "EasyComputerKI" && firstPlayer.getName() != "HardComputerKI") {
			try {
				DataManager.changeProperty(firstPlayer.getName(), "nrOfGames", firstPlayer.getNrOfGames() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(firstPlayer.getName(), "ties", firstPlayer.getTies() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (secondPlayer.getName() != "EasyComputerKI" && secondPlayer.getName() != "HardComputerKI") {
			try {
				DataManager.changeProperty(secondPlayer.getName(), "nrOfGames", secondPlayer.getNrOfGames() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DataManager.changeProperty(secondPlayer.getName(), "ties", secondPlayer.getTies() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method checks, if a fields coordinates are still in range of the field.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @return True, if the coordinates are both in bounds, False otherwise.
	 */
	private static boolean isValid(int x, int y) {
		if ((x >= 0 && x < logicField.length) && (y >= 0 && y < logicField[0].length)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method prints a message on the console.
	 * 
	 * @param s
	 */
	private static void log(String s) {
		console.append(" " + s + "\n");
	}

	/**
	 * This funny method moves the mouse cursor over the entire field to order Java
	 * forcibly to load the components. If omitted, the field may or may not load,
	 * depending on Java mood today.
	 * 
	 * @throws AWTException
	 *             If no mouse is found.
	 */
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

	/**
	 * This method returns a random integer from 0 to the given parameter.
	 * 
	 * @param n
	 *            The maximum number.
	 * @return An integer from 0 to n.
	 */
	private static int randomNrFrom0Ton(int n) {
		double rand = Math.random();
		int rnd = (int) (rand * n);
		return rnd;
	}

	/**
	 * This method initializes the first turn, if player one is a computer.
	 */
	private static void initComputers() {
		if (firstPlayer.getName().equals("EasyComputerKI")) {
			EasyComputerAI();
		}
		if (firstPlayer.getName().equals("HardComputerKI")) {
			HardComputerAI();
		}
	}

	/**
	 * This method chooses a random cell, and simulates a click on it.
	 */
	private static void EasyComputerAI() {
		int x = 0;
		int y = randomNrFrom0Ton(6);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {

		}
		if (cellField[x][y].isFilled() == false) {
			evaluateClick(cellField[x][y]);
		} else {
			EasyComputerAI();
		}
	}

	/**
	 * This method chooses a nearly optimal cell with calculating outputs for one
	 * turn into the future, and simulates a click on it.
	 */
	private static void HardComputerAI() {
		int x = 0;
		int y = 0;
		int rating;
		// Check the optimal turn for winning
		for (int i = 0; i < length; i++) {
			refreshSimulatedField();
			int height = simulateClick(i, currentPlayer);
			rating = rateField(height, i);
			if (rating >= 4) {
				evaluateClick(cellField[x][i]);
				return;
			}
		}
		// Check the optimal turn for blocking
		int enemy = 0;
		if (currentPlayer == 1) {
			enemy = 2;
		} else {
			enemy = 1;
		}
		for (int i = 0; i < length; i++) {
			refreshSimulatedField();
			int height = simulateClick(i, enemy);
			rating = rateField(height, i);
			if (rating >= 4) {
				evaluateClick(cellField[x][i]);
				return;
			}

		}
		// Choose the longest streak, and build at it
		int[] ratings = new int[7];
		for (int i = 0; i < length; i++) {
			refreshSimulatedField();
			int height = simulateClick(i, currentPlayer);
			ratings[i] = rateField(height, i);
		}
		y = getMaxIndex(ratings);

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {

		}
		evaluateClick(cellField[x][y]);
	}

	/**
	 * This method prepares the simulated logic-array for the next simulated click.
	 */
	private static void refreshSimulatedField() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < height; j++) {
				simulatedField[i][j] = logicField[i][j];
			}
		}
	}

	/**
	 * This auxiliary method works exactly like evaluate, but only for the simulated
	 * field.
	 * 
	 * @param column
	 *            The column, on which top most part is simulated.
	 * @param playerValue
	 *            The players value, e.g. 1 for player 1, 2 for player 2.
	 * @return The height of the affected cell after gravity function.
	 */
	private static int simulateClick(int column, int playerValue) {
		for (int i = height - 1; i >= 0; i--) {
			if (simulatedField[i][column] == 0) {
				simulatedField[i][column] = playerValue;
				return i;
			}
		}
		return 0;
	}

	/**
	 * This method calculates the rating of a future field, depending on where the
	 * next stone is set. The rating shows, how many stones there will be in a row.
	 * 
	 * @param x
	 *            The x coordinate of the cell.
	 * @param y
	 *            The y coordinate of the cell.
	 * @return The maximum possible rating, which can be achieved from this turn.
	 */
	private static int rateField(int x, int y) {
		int[] ratings = new int[4];
		ratings[0] = rateHorizontally(x, y);
		ratings[1] = rateVertically(x, y);
		ratings[2] = rateDiagonalRight(x, y);
		ratings[3] = rateDiagonalLeft(x, y);
		return getMax(ratings);
	}

	/**
	 * This auxiliary method calculates the rating horizontally.
	 * 
	 * @param x
	 *            The x coordinate of the newly set cell.
	 * @param y
	 *            The y coordinate of the newly set cell.
	 * @return The longest possible horizontal streak.
	 */
	private static int rateHorizontally(int x, int y) {
		int value = simulatedField[x][y];
		int counter = 1;
		int x_new = x;

		int y_new = y - 1;
		// linke reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
		}
		y_new = y + 1;
		// rechte reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
		}
		return counter;
	}

	/**
	 * This auxiliary method calculates the rating vertically.
	 * 
	 * @param x
	 *            The x coordinate of the newly set cell.
	 * @param y
	 *            The y coordinate of the newly set cell.
	 * @return The longest possible vertical streak.
	 */
	private static int rateVertically(int x, int y) {
		int value = simulatedField[x][y];
		int counter = 1;
		int y_new = y;

		int x_new = x - 1;
		// obere reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			x_new = x_new - 1;
		}
		x_new = x + 1;
		// untere reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			x_new = x_new + 1;
		}
		return counter;
	}

	/**
	 * This auxiliary method calculates the rating diagonally, from bottom right to left right.
	 * 
	 * @param x
	 *            The x coordinate of the newly set cell.
	 * @param y
	 *            The y coordinate of the newly set cell.
	 * @return The longest possible diagonal streak.
	 */
	private static int rateDiagonalRight(int x, int y) {
		int value = simulatedField[x][y];
		int counter = 1;

		int y_new = y + 1;
		int x_new = x - 1;
		// oben rechte reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
			x_new = x_new - 1;
		}
		y_new = y - 1;
		x_new = x + 1;
		// unten linke reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
			x_new = x_new + 1;
		}
		return counter;
	}

	/**
	 * This auxiliary method calculates the rating diagonally, from bottom left to top right.
	 * 
	 * @param x
	 *            The x coordinate of the newly set cell.
	 * @param y
	 *            The y coordinate of the newly set cell.
	 * @return The longest possible diagonal streak.
	 */
	private static int rateDiagonalLeft(int x, int y) {
		int value = simulatedField[x][y];
		int counter = 1;

		int y_new = y - 1;
		int x_new = x - 1;
		// oben linke reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new - 1;
			x_new = x_new - 1;
		}
		y_new = y + 1;
		x_new = x + 1;
		// unten rechte reichweite
		while (isValid(x_new, y_new) && simulatedField[x_new][y_new] != 0 && simulatedField[x_new][y_new] == value) {
			counter = counter + 1;
			y_new = y_new + 1;
			x_new = x_new + 1;
		}
		return counter;
	}

	/**
	 * This auxiliary method finds the largest value in an array.
	 * 
	 * @param values
	 *            The array to be searched.
	 * @return The largest value.
	 */
	private static int getMax(int[] values) {
		int index = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[index] < values[i]) {
				index = i;
			}
		}
		return values[index];
	}

	/**
	 * This auxiliary method finds the index of the largest value in the array.
	 * 
	 * @param values
	 *            The array to be searched.
	 * @return The index of the largest value.
	 */
	private static int getMaxIndex(int[] values) {
		int index = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[index] < values[i]) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * This method simply disposes of the window, similar to the normal close button
	 * action.
	 */
	public static void dispose() {
		mainWindow.dispose();
	}

	/**
	 * This method stops all playing music, and starts the music from the given
	 * file.
	 * 
	 * @param filename
	 *            The name of the track (as a .wav).
	 */
	public static void startMusic(String filename) {
		MainWindow.getAmbient().stopMusic();
		try {
			ambient = new SoundManager(filename, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static SoundManager getAmbient() {
		return ambient;
	}

}
