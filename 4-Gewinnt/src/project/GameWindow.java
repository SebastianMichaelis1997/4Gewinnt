package project;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

	private static Player firstPlayer;
	private static Player secondPlayer;
	private static JLabel lblFirstPlayerIcon;
	private static JLabel lblSecondPlayerIcon;

	public static void main(String[] args) throws AWTException { // not
																	// necessary,
																	// just for
																	// test case
																	// @Simon :
																	// Nach
																	// Fertigstellung
																	// Löschen

		firstPlayer = new Player("test1", null, Color.DARK_GRAY);
		secondPlayer = new Player("test2", null, Color.BLUE);

		initializeWindow();

		mainWindow.setVisible(true);

		loadField();

		log("PLAYER RED'S TURN");

	}

	public static void start(Player player1, Player player2) throws AWTException {

		currentPlayer = 1;
		firstPlayer = player1;
		secondPlayer = player2;

		initializeWindow();

		mainWindow.setVisible(true);

		loadField();

		log("Player " + player1.getName() + "'s turn!");

	}

	private static void initializeWindow() {
		// reset operations
		gameIsOver = false;
		for (int i = 0; i < cellField.length; i++) {
			for (int j = 0; j < cellField[i].length; j++) {
				cellField[i][j] = null;
				logicField[i][j] = 0;
			}
		}
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		mainWindow.getContentPane().add(splitPane);
	}

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
		/*JLabel playerOneVictoryLabel = new JLabel("Siege:");
		playerOneVictoryLabel.setBounds(10, 229, 121, 37);
		JLabel playerOneVictoryCount = new JLabel("0");
		playerOneVictoryCount.setBounds(132, 74, 242, 37);*/

		JLabel playerTwoLabel = new JLabel("Spieler 2:");
		playerTwoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerTwoLabel.setBounds(43, 165, 121, 37);
		JTextField playerTwoName = new JTextField();
		playerTwoName.setBounds(43, 209, 191, 37);
		playerTwoName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerTwoName.setBackground(secondPlayer.getColor());
		playerTwoName.setText(secondPlayer.getName());
		playerTwoName.setEditable(false);
		/*JLabel playerTwoVictoryLabel = new JLabel("Siege");
		playerTwoVictoryLabel.setBounds(10, 74, 242, 37);
		JLabel playerTwoVictoryCount = new JLabel("0");
		playerTwoVictoryCount.setBounds(132, 229, 121, 37);*/

		console = new JTextArea();
		scrollPane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(475, 300));
		console.setEditable(false);
		infoTopPanel.setLayout(null);

		infoTopPanel.add(playerOneLabel);
		infoTopPanel.add(playerOneName);
		//infoTopPanel.add(playerOneVictoryLabel);
		//infoTopPanel.add(playerOneVictoryCount);
		infoTopPanel.add(playerTwoLabel);
		infoTopPanel.add(playerTwoName);
		//infoTopPanel.add(playerTwoVictoryLabel);
		//infoTopPanel.add(playerTwoVictoryCount);

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

	public static void updateAfterTurn(int x, int y) {
		checkHorizontalWin(x, y);
		checkVerticalWin(x, y);
		checkDiagonalRightWin(x, y);
		checkDiagonalLeftWin(x, y);
		if (gameIsOver == false) {
			switch (currentPlayer) {
			case 1:
				currentPlayer = 2;
				log("Player " + secondPlayer.getName() + "'s turn!");
				break;
			case 2:
				currentPlayer = 1;
				log("Player " + firstPlayer.getName() + "'s turn!");
				break;
			default:
				break;
			}
			// if (currentPlayer == 2) {
			// computerAI();
			// }
		}
	}

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
		try {
			DataManager.changeProperty(firstPlayer.getName(), "wins", firstPlayer.getWins() + "");
			DataManager.changeProperty(firstPlayer.getName(), "nrOfGames", firstPlayer.getNrOfGames() + "");
			DataManager.changeProperty(firstPlayer.getName(), "losses", firstPlayer.getLosses() + "");
			DataManager.changeProperty(firstPlayer.getName(), "score", firstPlayer.getScore() + "");
			DataManager.changeProperty(secondPlayer.getName(), "wins", secondPlayer.getWins() + "");
			DataManager.changeProperty(secondPlayer.getName(), "nrOfGames", secondPlayer.getNrOfGames() + "");
			DataManager.changeProperty(secondPlayer.getName(), "losses", secondPlayer.getLosses() + "");
			DataManager.changeProperty(secondPlayer.getName(), "score", secondPlayer.getScore() + "");
		} catch (PlayerAlreadyExistsException e) {
			System.out.println(e.getMessage() + " ALEX TEST");
		}
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

	private static void EasyComputerAI() {
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
			EasyComputerAI();
		}
	}

	public static void dispose() {
		mainWindow.dispose();
	}
}
