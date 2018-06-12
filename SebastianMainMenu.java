package project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroupPlayer1 = new ButtonGroup();
	private final ButtonGroup buttonGroupPlayer2 = new ButtonGroup();

	public static void test() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 262);
		contentPane.add(tabbedPane);

		JPanel panelGame = new JPanel();
		tabbedPane.addTab("Game", null, panelGame, null);
		panelGame.setLayout(null);

		JRadioButton radioButton = new JRadioButton("Computer");
		buttonGroupPlayer1.add(radioButton);
		radioButton.setBounds(9, 67, 107, 23);
		panelGame.add(radioButton);

		JRadioButton rdbtnHuman = new JRadioButton("Human");
		rdbtnHuman.setSelected(true);
		buttonGroupPlayer1.add(rdbtnHuman);
		rdbtnHuman.setBounds(9, 41, 107, 23);
		panelGame.add(rdbtnHuman);

		JComboBox dropdownP1C = new JComboBox();
		dropdownP1C.setBounds(122, 68, 83, 20);
		panelGame.add(dropdownP1C);

		JComboBox dropdownP1H = new JComboBox();
		dropdownP1H.setBounds(122, 42, 83, 20);
		panelGame.add(dropdownP1H);

		JComboBox dropdownP2H = new JComboBox();
		dropdownP2H.setBounds(339, 42, 83, 20);
		panelGame.add(dropdownP2H);

		JComboBox dropdownP2C = new JComboBox();
		dropdownP2C.setBounds(339, 71, 83, 20);
		panelGame.add(dropdownP2C);

		JRadioButton radioButton_2 = new JRadioButton("Computer");
		radioButton_2.setSelected(true);
		buttonGroupPlayer2.add(radioButton_2);
		radioButton_2.setBounds(234, 67, 99, 23);
		panelGame.add(radioButton_2);

		JRadioButton radioButton_3 = new JRadioButton("Human");
		buttonGroupPlayer2.add(radioButton_3);
		radioButton_3.setBounds(234, 41, 99, 23);
		panelGame.add(radioButton_3);

		JButton btnStart = new JButton("START!");
		btnStart.setBounds(10, 118, 409, 105);
		panelGame.add(btnStart);

		JLabel lblPlayer_1 = new JLabel("Player 1");
		lblPlayer_1.setBounds(67, 12, 46, 14);
		panelGame.add(lblPlayer_1);

		JLabel lblPlayer_2 = new JLabel("Player 2");
		lblPlayer_2.setBounds(307, 12, 46, 14);
		panelGame.add(lblPlayer_2);

		JPanel panelPlayer = new JPanel();
		tabbedPane.addTab("Player", null, panelPlayer, null);
		panelPlayer.setLayout(null);

		JButton btnCreatePlayer = new JButton("Create Player");
		btnCreatePlayer.setBounds(10, 11, 120, 60);
		panelPlayer.add(btnCreatePlayer);

		JButton btnEditPlayer = new JButton("Edit Player");
		btnEditPlayer.setBounds(10, 87, 120, 60);
		panelPlayer.add(btnEditPlayer);

		JButton btnDeletePlayer = new JButton("Delete Player");
		btnDeletePlayer.setBounds(10, 163, 120, 60);
		panelPlayer.add(btnDeletePlayer);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(247, 11, 172, 20);
		panelPlayer.add(comboBox_4);

		JLabel lblPlayer = new JLabel("Player");
		lblPlayer.setBounds(191, 11, 46, 14);
		panelPlayer.add(lblPlayer);

		JLabel lblWins = new JLabel("Wins");
		lblWins.setBounds(191, 54, 46, 14);
		panelPlayer.add(lblWins);

		JLabel lblLosses = new JLabel("Losses");
		lblLosses.setBounds(191, 107, 46, 14);
		panelPlayer.add(lblLosses);

		JLabel lblTies = new JLabel("Ties");
		lblTies.setBounds(191, 160, 46, 14);
		panelPlayer.add(lblTies);

		JPanel panelSettings = new JPanel();
		tabbedPane.addTab("Settings", null, panelSettings, null);
		panelSettings.setLayout(null);

		JButton btnReadme = new JButton("ReadMe");
		btnReadme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame ReadMeWindow = new ReadMeWindow();
//				ReadMeWindow.setVisible(true);
			}
		});
		btnReadme.setBounds(10, 11, 89, 23);
		panelSettings.add(btnReadme);

		JLabel lblInformationen = new JLabel("INFOR;MATIONEN!!!!!!");
		lblInformationen.setBounds(10, 45, 253, 129);
		panelSettings.add(lblInformationen);
	}
}
