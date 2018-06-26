package project;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class MainWindow extends JFrame {

	private JTextField txtPoints;
	private JTextField txtWins;
	private JTextField txtLoses;
	private JTextField textField_3;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public static ActionListener readMeActionListener; // For Reusing Action
														// Listener

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		ExceptionWindow test = new ExceptionWindow("Test Emma");
        
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// MainWindowOwn frame = new MainWindowOwn();
					// frame.setVisible(true);
					MainWindow mainWindow = new MainWindow();
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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

		tabbedPane
				.addTab("Game                                                                                          ",
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
		JComboBox comboPlayer1 = new JComboBox(DataManager.getAllPlayerNames().toArray());
		comboPlayer1.setBounds(213, 214, 156, 31);
		gameTabPanel.add(comboPlayer1);

		JLabel label = new JLabel("Player Number Two:");
		label.setFont(new Font("Tahoma", Font.ITALIC, 16));
		label.setBounds(842, 135, 156, 29);
		gameTabPanel.add(label);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(550, 80, 1, 73);
		gameTabPanel.add(separator_2);

		JComboBox comboPlayer2 = new JComboBox(DataManager.getAllPlayerNames().toArray());
		comboPlayer2.setBounds(842, 214, 156, 31);
		gameTabPanel.add(comboPlayer2);

		JSeparator separator = new JSeparator();
		separator.setBounds(59, 163, 961, 2);
		gameTabPanel.add(separator);

		RoundCornerButton roundCornerButton = new RoundCornerButton(
				"<html> <center>Start Game</center> </html>", new Dimension(
						105, 65));
		roundCornerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Player test1 = new Player("Alex", null, Color.GREEN);
					Player test2 = new Player("Benne", null, null);
					GameWindow.start(test1, test2);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
		});
		roundCornerButton.setForeground(Color.WHITE);
		roundCornerButton.setBounds(419, 347, 215, 84);
		gameTabPanel.add(roundCornerButton);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Human");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(63, 214, 98, 21);
		gameTabPanel.add(rdbtnNewRadioButton);

		JRadioButton radioButton = new JRadioButton("Computer");
		buttonGroup.add(radioButton);
		radioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioButton.setBounds(63, 297, 98, 21);
		gameTabPanel.add(radioButton);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(213, 295, 156, 31);
		gameTabPanel.add(comboBox_1);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(842, 303, 156, 31);
		gameTabPanel.add(comboBox_2);

		JRadioButton radioButton_1 = new JRadioButton("Human");
		buttonGroup_1.add(radioButton_1);
		radioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioButton_1.setBounds(738, 219, 98, 21);
		gameTabPanel.add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("Computer");
		buttonGroup_1.add(radioButton_2);
		radioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioButton_2.setBounds(738, 305, 98, 21);
		gameTabPanel.add(radioButton_2);

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		//
		// Tab 2 (Player Tab)
		//
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------

		tabbedPane
				.addTab("Player                                                                                              ",
						playerTabPanel);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerCreation playerCreation = new PlayerCreation();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.ITALIC, 25));
		btnCreate.setBounds(70, 80, 125, 32);
		playerTabPanel.add(btnCreate);
		
		Panel panelInPanel3 = new Panel();
		panelInPanel3.setBackground(Color.WHITE);
		panelInPanel3.setForeground(Color.WHITE);
		panelInPanel3.setBounds(287, 63, 733, 413);
		panelInPanel3.setLayout(null);
		playerTabPanel.add(panelInPanel3);
		
		JComboBox selectEditPlayer = new JComboBox(DataManager.getAllPlayerNames().toArray());
		selectEditPlayer.setBounds(371, 38, 326, 21);
		panelInPanel3.add(selectEditPlayer);
		selectEditPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent showPlayer) {
				String selectedPlayer = selectEditPlayer.getSelectedItem().toString();
				String selectedScore = DataManager.getProperty(selectedPlayer, "score");
				System.out.println(selectedScore);
				txtPoints.setText(selectedScore);
				String selectedWins = DataManager.getProperty(selectedPlayer, "wins");
				System.out.println(selectedWins);
				txtWins.setText(selectedWins);
				String selectedLosses = DataManager.getProperty(selectedPlayer, "losses");
				System.out.println(selectedLosses);
				txtLoses.setText(selectedLosses);				
			}
		});
		
		JButton buttonEdit = new JButton("Edit");
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent editPlayer) {
				EditPlayer.start(selectEditPlayer.getSelectedItem());
			}
		});
		buttonEdit.setFont(new Font("Tahoma", Font.ITALIC, 25));
		buttonEdit.setBounds(70, 220, 125, 32);
		playerTabPanel.add(buttonEdit);

		JButton buttonDelete = new JButton("Delete");
		buttonDelete.setFont(new Font("Tahoma", Font.ITALIC, 25));
		buttonDelete.setBounds(70, 375, 125, 32);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent deletePlayer) {
				DeletePlayer.start(selectEditPlayer.getSelectedItem().toString());
			}
		});
		playerTabPanel.add(buttonDelete);

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

		txtPoints = new JTextField();
		txtPoints.setHorizontalAlignment(SwingConstants.CENTER);
		txtPoints.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPoints.setText("");
		txtPoints.setBounds(371, 156, 96, 19);
		txtPoints.setColumns(10);
		txtPoints.setEditable(false);
		panelInPanel3.add(txtPoints);

		txtWins = new JTextField();
		txtWins.setHorizontalAlignment(SwingConstants.CENTER);
		txtWins.setText("");
		txtWins.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtWins.setColumns(10);
		txtWins.setBounds(371, 230, 96, 19);
		txtWins.setEditable(false);
		panelInPanel3.add(txtWins);

		txtLoses = new JTextField();
		txtLoses.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoses.setText("");
		txtLoses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLoses.setColumns(10);
		txtLoses.setBounds(371, 309, 96, 19);
		txtLoses.setEditable(false);
		panelInPanel3.add(txtLoses);

		JLabel lblSelectPlayer = new JLabel("Select Player:");
		lblSelectPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectPlayer.setBounds(55, 29, 156, 30);
		panelInPanel3.add(lblSelectPlayer);

		// -----------------------------------------------------------------------------------------------------------------------------------------------------------
		//
		// Tab 3 (Options Tab)
		//
		// -----------------------------------------------------------------------------------------------------------------------------------------------------------

		tabbedPane
				.addTab("Options                                                                                             ",
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
		slider.setSnapToTicks(true); // slider spr�nge zu Ticks aktiviert
		slider.setMajorTickSpacing(10); // Slider Ticks Gr��e definiert
		optionsTabPanel.add(slider);

	}
}