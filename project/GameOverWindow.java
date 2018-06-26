package project;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class GameOverWindow extends JFrame {

	public static ActionListener readMeActionListener; // For Reusing Action Listener
	public static MainWindow mainWindow;
	public static GameWindow gameWindow;
	public static GameOverWindow gow; 
	
	 // Create the frame.
	public static void backToMenu() {
		MainWindow a = new MainWindow();
		a.setVisible(true);	
	}
	
	public GameOverWindow(String winner) {
		getContentPane().setBackground(Color.WHITE);

		setTitle("Game over");
		setBounds(200, 100, 550, 300);
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblPlayerXWins = new JLabel("Player " + winner + " wins game!");
		lblPlayerXWins.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPlayerXWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerXWins.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPlayerXWins.setBounds(26, 11, 481, 46);
		getContentPane().add(lblPlayerXWins);
						
		JButton btnHome = new JButton("HOME");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHome.setBounds(364, 95, 132, 46);
		getContentPane().add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backToMenu) {
				backToMenu();
			}
		});
		
		JButton btnRevanche = new JButton("REVANCHE");
		btnRevanche.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRevanche.setBounds(364, 152, 132, 46);
		getContentPane().add(btnRevanche);
		btnRevanche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backToGame) {
				try {
					Player test1 = new Player("Alex", null, Color.GREEN);
					Player test2 = new Player("Benne", null, null);
					GameWindow.start(test1, test2);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			};
		});
		
		// Sieger Animation		
		JLabel lblAnimation = new JLabel("Animation");
		lblAnimation.setBackground(Color.BLACK);
		lblAnimation.setIcon(new ImageIcon(GameOverWindow.class.getResource("/project/baerchen-sieger.gif")));
		lblAnimation.setBounds(55, 74, 253, 166);
		getContentPane().add(lblAnimation);
	}
}