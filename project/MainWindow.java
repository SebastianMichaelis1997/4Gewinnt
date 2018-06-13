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

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		getContentPane().add(tabbedPane);
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);

		tabbedPane.addTab(
				"Player                                                                                           ",
				panel2);

		JLabel label_1 = new JLabel("Select Players:");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_1.setBounds(445, 39, 145, 31);
		panel2.add(label_1);

		JLabel label_2 = new JLabel("Player Number One:");
		label_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 16));
		label_2.setBounds(90, 134, 156, 31);
		panel2.add(label_2);

		// create an empty combo box with items of type String
		JComboBox<String> comboPlayer1 = new JComboBox<String>();

		// add items to the combo box
		comboPlayer1.addItem("Select Player One");
		comboPlayer1.addItem("Dummy1");
		comboPlayer1.addItem("Dummy2");
		comboPlayer1.setBounds(213, 214, 156, 31);
		panel2.add(comboPlayer1);

		JLabel label = new JLabel("Player Number Two:");
		label.setFont(new Font("Tahoma", Font.ITALIC, 16));
		label.setBounds(842, 135, 156, 29);
		panel2.add(label);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(550, 80, 1, 73);
		panel2.add(separator_2);

		JComboBox<String> comboPlayer2 = new JComboBox<String>();
		comboPlayer2.setBounds(842, 214, 156, 31);
		comboPlayer2.addItem("Select Player Two");
		comboPlayer2.addItem("Dummy1");
		comboPlayer2.addItem("Dummy2");
		panel2.add(comboPlayer2);

		JSeparator separator = new JSeparator();
		separator.setBounds(59, 163, 961, 2);
		panel2.add(separator);

		RoundCornerButton roundCornerButton = new RoundCornerButton("<html> <center>Start Game</center> </html>",
				new Dimension(105, 65));
		roundCornerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GameWindow.start();
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
		});
		roundCornerButton.setForeground(Color.WHITE);
		roundCornerButton.setBounds(419, 347, 215, 84);
		panel2.add(roundCornerButton);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Human");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(63, 214, 98, 21);
		panel2.add(rdbtnNewRadioButton);

		JRadioButton radioButton = new JRadioButton("Computer");
		buttonGroup.add(radioButton);
		radioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioButton.setBounds(63, 297, 98, 21);
		panel2.add(radioButton);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(213, 295, 156, 31);
		panel2.add(comboBox_1);

		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(842, 303, 156, 31);
		panel2.add(comboBox_2);

		JRadioButton radioButton_1 = new JRadioButton("Human");
		buttonGroup_1.add(radioButton_1);
		radioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioButton_1.setBounds(738, 219, 98, 21);
		panel2.add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("Computer");
		buttonGroup_1.add(radioButton_2);
		radioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radioButton_2.setBounds(738, 305, 98, 21);
		panel2.add(radioButton_2);

		tabbedPane.addTab(
				"Settings                                                                                              ",
				panel3);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.ITALIC, 25));
		btnCreate.setBounds(70, 80, 125, 32);
		panel3.add(btnCreate);

		JButton button = new JButton("Edit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("Tahoma", Font.ITALIC, 25));
		button.setBounds(70, 220, 125, 32);
		panel3.add(button);

		JButton button_1 = new JButton("Delete");
		button_1.setFont(new Font("Tahoma", Font.ITALIC, 25));
		button_1.setBounds(70, 375, 125, 32);
		panel3.add(button_1);

		Panel panelInPanel3 = new Panel();
		panelInPanel3.setBackground(Color.WHITE);
		panelInPanel3.setForeground(Color.WHITE);
		panelInPanel3.setBounds(287, 63, 733, 413);
		panelInPanel3.setLayout(null);
		panel3.add(panelInPanel3);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(371, 38, 326, 21);
		panelInPanel3.add(comboBox);

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

		JLabel label_4 = new JLabel("Loses:");
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
		textField.setText("310");
		textField.setBounds(371, 156, 96, 19);
		textField.setColumns(10);
		textField.setEditable(false);
		panelInPanel3.add(textField);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("13");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(371, 230, 96, 19);
		textField_1.setEditable(false);
		panelInPanel3.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("3");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(371, 309, 96, 19);
		textField_2.setEditable(false);
		panelInPanel3.add(textField_2);

		JLabel lblSelectPlayer = new JLabel("Select Player:");
		lblSelectPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectPlayer.setBounds(55, 29, 156, 30);
		panelInPanel3.add(lblSelectPlayer);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);

		tabbedPane.addTab(
				"Game                                                                                              ",
				panel1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(526, 72, 1, 382);
		panel1.add(separator_1);

		JButton btnReadMe = new JButton("Read me!");
		btnReadMe.setFont(new Font("Tahoma", Font.ITALIC, 25));
		btnReadMe.setBounds(29, 37, 189, 51);
		btnReadMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame readMeWindow = new ReadMeWindow();
			}
		});
		panel1.add(btnReadMe);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(29, 98, 1005, 2);
		panel1.add(separator_5);

		JLabel lblVersionsinfo = new JLabel("Version:");
		lblVersionsinfo.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblVersionsinfo.setBounds(29, 135, 155, 34);
		panel1.add(lblVersionsinfo);

		JLabel label_5 = new JLabel("Volume:");
		label_5.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_5.setBounds(29, 262, 155, 34);
		panel1.add(label_5);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setText("10.4.1");
		textField_3.setBounds(257, 147, 714, 22);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		panel1.add(textField_3);

		JSlider slider = new JSlider(); // Slider
		slider.setBounds(257, 274, 714, 22);
		slider.setPaintTicks(true); // Slider Ticks aktiviert
		slider.setSnapToTicks(true); // slider sprünge zu Ticks aktiviert
		slider.setMajorTickSpacing(10); // Slider Ticks Größe definiert
		panel1.add(slider);

	}
}
