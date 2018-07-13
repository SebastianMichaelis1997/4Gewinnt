package project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exceptions.ExceptionNoPlayerSelected;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private static JTextField textField, textField_1, textField_2, textField_3;
	private static JComboBox comboPlayer1, comboPlayer2, selectEditPlayer;
	private static final ButtonGroup buttonGroup = new ButtonGroup();
	private static final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private static MainWindow mainWindow;
	public static ActionListener readMeActionListener; 
	// For Reusing Action Listener

	private static SoundManager ambient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {

		try {
			ambient = new SoundManager("ambient", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// MainWindowOwn frame = new MainWindowOwn();
					// frame.setVisible(true);
					mainWindow = new MainWindow();
					mainWindow.setVisible(true);
					mainWindow.setResizable(false);
					mainWindow.setBackground(Color.blue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {

		setTitle("MainWindow");
		setBounds(200, 100, 1100, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT); // Creates Tab Panel
		getContentPane().add(tabbedPane);

		JPanel gameTabPanel = new JPanel(); // Three tabs get created
		gameTabPanel.setLayout(null);
		JPanel playerTabPanel = new JPanel();
		playerTabPanel.setLayout(null);
		JPanel optionsTabPanel = new JPanel();
		optionsTabPanel.setLayout(null);

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		//
		// Tab 1 (Game Tab)
		//
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------

		tabbedPane.addTab(
				"Game                                                                                          ",
				gameTabPanel);

		JLabel label_1 = new JLabel("Select Players:");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_1.setBounds(445, 39, 145, 31);
		gameTabPanel.add(label_1);

		JLabel label_2 = new JLabel("Player Number One:");
		label_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		label_2.setBounds(90, 134, 156, 31);
		gameTabPanel.add(label_2);

		// create an empty combo box with items of type String
		// creates combo box with all player names for choosing player 1
		comboPlayer1 = new JComboBox(DataManager.getAllPlayerNames().toArray());
		comboPlayer1.setBounds(213, 214, 156, 31);
		gameTabPanel.add(comboPlayer1);

		JLabel label = new JLabel("Player Number Two:");
		label.setFont(new Font("Tahoma", Font.ITALIC, 16));
		label.setBounds(842, 135, 156, 29);
		gameTabPanel.add(label);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(550, 80, 1, 73);
		gameTabPanel.add(separator_2);

		// creates combo box with all player names for choosing player 2
		comboPlayer2 = new JComboBox(DataManager.getAllPlayerNames().toArray());
		comboPlayer2.setBounds(842, 214, 156, 31);
		gameTabPanel.add(comboPlayer2);

		JSeparator separator = new JSeparator();
		separator.setBounds(59, 163, 961, 2);
		gameTabPanel.add(separator);

		RoundCornerButton roundCornerButton = new RoundCornerButton("<html> <center>Start Game</center> </html>",
				new Dimension(105, 65));
		roundCornerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboPlayer1.getSelectedItem() == null || comboPlayer2.getSelectedItem() == null) {
						throw new ExceptionNoPlayerSelected();
					}
					if (comboPlayer1.getSelectedItem().toString().equals(comboPlayer2.getSelectedItem().toString())) {
						throw new ExceptionPvP();
					}
					Player player1 = DataManager.getPlayerObj(comboPlayer1.getSelectedItem().toString());
					Player player2 = DataManager.getPlayerObj(comboPlayer2.getSelectedItem().toString());

					if (player1.getIcon() != null && player1.getIcon().equals(player2.getIcon())) {
						ErrorWindow.start("Icon conflict: Please choose different icons!");
					}
					else if (player1.getIcon() == null && player2.getIcon() == null && player1.getColor() != null && player1.getColor().equals(player2.getColor())) {
						ErrorWindow.start("Color conflict: Please choose different colors!");
					}
					else {GameWindow.start(player1, player2);
					}
				} catch (AWTException e1) {
					e1.printStackTrace();
				} catch (ExceptionPvP e2) {
					ErrorWindow.start(e2.getMessage());
				} catch (ExceptionNoPlayerSelected e3) {
					ErrorWindow.start(e3.getMessage());
				}
			}
		});
		roundCornerButton.setForeground(Color.WHITE);
		roundCornerButton.setBounds(419, 347, 215, 84);
		gameTabPanel.add(roundCornerButton);

		JRadioButton rdbtnHuman1 = new JRadioButton("Human");
		buttonGroup.add(rdbtnHuman1);
		rdbtnHuman1.setSelected(true);
		rdbtnHuman1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnHuman1.setBounds(63, 214, 98, 21);
		gameTabPanel.add(rdbtnHuman1);

		JRadioButton rdbtnComputer1 = new JRadioButton("Computer");
		buttonGroup.add(rdbtnComputer1);
		rdbtnComputer1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnComputer1.setBounds(63, 297, 98, 21);
		gameTabPanel.add(rdbtnComputer1);

		JComboBox comboComputer1 = new JComboBox();
		comboComputer1.setBounds(213, 295, 156, 31);
		gameTabPanel.add(comboComputer1);

		JComboBox comboComputer2 = new JComboBox();
		comboComputer2.setBounds(842, 303, 156, 31);
		gameTabPanel.add(comboComputer2);

		JRadioButton rdbtnHuman2 = new JRadioButton("Human");
		buttonGroup_1.add(rdbtnHuman2);
		rdbtnHuman2.setSelected(true);
		rdbtnHuman2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnHuman2.setBounds(738, 219, 98, 21);
		gameTabPanel.add(rdbtnHuman2);

		JRadioButton rdbtnComputer2 = new JRadioButton("Computer");
		buttonGroup_1.add(rdbtnComputer2);
		rdbtnComputer2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnComputer2.setBounds(738, 305, 98, 21);
		gameTabPanel.add(rdbtnComputer2);

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		//
		// Tab 2 (Player Tab)
		//
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------

		tabbedPane.addTab(
				"Player                                                                                              ",
				playerTabPanel);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreatePlayer();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.ITALIC, 25));
		btnCreate.setBounds(70, 63, 125, 32);
		playerTabPanel.add(btnCreate);

		Panel panelInPanel3 = new Panel();
		panelInPanel3.setBackground(Color.WHITE);
		panelInPanel3.setForeground(Color.WHITE);
		panelInPanel3.setBounds(287, 63, 733, 413);
		panelInPanel3.setLayout(null);
		playerTabPanel.add(panelInPanel3);

		// Combo box for choosing player which shall get edited
		selectEditPlayer = new JComboBox(DataManager.getAllPlayerNames().toArray());
		selectEditPlayer.setBounds(371, 38, 326, 21);
		selectEditPlayer.setEditable(true);
		selectEditPlayer.setSelectedItem("Select Player");
		selectEditPlayer.setEditable(false);
		panelInPanel3.add(selectEditPlayer);

		// Show score from Selected Player
		selectEditPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent showPlayer) {
				if (!selectEditPlayer.getSelectedItem().toString().equals("Select Player")) {

					String selectedPlayer = selectEditPlayer.getSelectedItem().toString();
					String selectedScore = DataManager.getProperty(selectedPlayer, "score");
					textField.setText(selectedScore);
					String selectedWins = DataManager.getProperty(selectedPlayer, "wins");
					textField_1.setText(selectedWins);
					String selectedLosses = DataManager.getProperty(selectedPlayer, "losses");
					textField_2.setText(selectedLosses);
				}
			}
		});

		JButton buttonEdit = new JButton("Edit");
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent editPlayer) {
				try {
					if (selectEditPlayer.getSelectedItem() == null
							|| selectEditPlayer.getSelectedItem().toString().equals("Select Player")) {
						throw new ExceptionNoPlayerSelected();
					}
					EditPlayer.start(selectEditPlayer.getSelectedItem());
				} catch (ExceptionNoPlayerSelected e) {
					ErrorWindow.start(e.getMessage());
				}
			}
		});
		buttonEdit.setFont(new Font("Tahoma", Font.ITALIC, 25));
		buttonEdit.setBounds(70, 190, 125, 32);
		playerTabPanel.add(buttonEdit);

		JButton buttonDelete = new JButton("Delete");
		buttonDelete.setFont(new Font("Tahoma", Font.ITALIC, 25));
		buttonDelete.setBounds(70, 317, 125, 32);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent deletePlayer) {
				try {
					if (selectEditPlayer.getSelectedItem() == null
							|| selectEditPlayer.getSelectedItem().toString().equals("Select Player")) {
						throw new ExceptionNoPlayerSelected();
					}
					DeletePlayer.start(selectEditPlayer.getSelectedItem().toString());
				} catch (ExceptionNoPlayerSelected e) {
					ErrorWindow.start(e.getMessage());
				}
			}
		});
		playerTabPanel.add(buttonDelete);

		JButton btnHighScore = new JButton("Highscores");
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighscoreWindow.start();
			}
		});
		btnHighScore.setFont(new Font("Tahoma", Font.ITALIC, 19));
		btnHighScore.setBounds(70, 444, 125, 32);
		playerTabPanel.add(btnHighScore);

		JLabel lblPoints = new JLabel("Points:");
		lblPoints.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoints.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblPoints.setBounds(55, 146, 101, 30);
		panelInPanel3.add(lblPoints);

		JLabel label_3 = new JLabel("Wins:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_3.setBounds(55, 220, 101, 30);
		panelInPanel3.add(label_3);

		JLabel label_4 = new JLabel("Losses:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_4.setBounds(55, 299, 101, 30);
		panelInPanel3.add(label_4);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(55, 180, 634, 2);
		panelInPanel3.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(55, 260, 634, 2);
		panelInPanel3.add(separator_4);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setText("");
		textField.setBounds(371, 156, 96, 19);
		textField.setColumns(10);
		textField.setEditable(false);
		panelInPanel3.add(textField);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(371, 230, 96, 19);
		textField_1.setEditable(false);
		panelInPanel3.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(371, 309, 96, 19);
		textField_2.setEditable(false);
		panelInPanel3.add(textField_2);

		JLabel lblSelectPlayer = new JLabel("Select Player:");
		lblSelectPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectPlayer.setBounds(55, 29, 156, 30);
		panelInPanel3.add(lblSelectPlayer);

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		//
		// Tab 3 (Options Tab)
		//
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------

		tabbedPane.addTab(
				"Options                                                                                             ",
				optionsTabPanel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(526, 72, 1, 382);
		optionsTabPanel.add(separator_1);

		JButton btnReadMe = new JButton("Read me!");
		btnReadMe.setFont(new Font("Tahoma", Font.ITALIC, 25));
		btnReadMe.setBounds(29, 37, 189, 51);
		readMeActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReadMeWindow();
			}
		};
		btnReadMe.addActionListener(readMeActionListener);
		optionsTabPanel.add(btnReadMe);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(29, 98, 1005, 2);
		optionsTabPanel.add(separator_5);

		JLabel lblVersionsinfo = new JLabel("Version:");
		lblVersionsinfo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblVersionsinfo.setBounds(29, 135, 155, 34);
		optionsTabPanel.add(lblVersionsinfo);

		JLabel label_5 = new JLabel("Volume:");
		label_5.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_5.setBounds(29, 262, 155, 34);
		optionsTabPanel.add(label_5);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setText("10.4.1");
		textField_3.setBounds(257, 147, 714, 22);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		optionsTabPanel.add(textField_3);

		JSlider slider = new JSlider(); // Slider
		slider.setBounds(257, 274, 714, 22);
		slider.setPaintTicks(true); // Slider Ticks aktiviert
		slider.setSnapToTicks(true); // slider sprünge zu Ticks aktiviert
		slider.setMajorTickSpacing(10); // Slider Ticks Größe definiert
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int fps = (int) source.getValue();
					ambient.setVolume(fps);
				}
			}

		});
		optionsTabPanel.add(slider);

	}

	public static void refreshPlayerComboBox() {
		comboPlayer1.removeAllItems();
		comboPlayer2.removeAllItems();
		// Fix of a bug where selectEditPlayer.removeAllItems(); ran into a
		// NullPointerException
		// when it was its first call with arguments inside selectedPlayer
		try {
			selectEditPlayer.removeAllItems();
		} catch (Exception e) {
			selectEditPlayer.removeAllItems();
		} finally {
			selectEditPlayer.removeAllItems();
		}
		Object[] players = DataManager.getAllPlayerNames().toArray();
		for (int i = 0; i < players.length; i++) {
			;
			comboPlayer1.addItem(players[i].toString());
			comboPlayer2.addItem(players[i].toString());
			selectEditPlayer.addItem(players[i].toString());
		}

	}

	public static void toFront2() {
		mainWindow.toFront();
	}

}
