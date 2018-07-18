package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

//import java.awt.desktop.AppHiddenListener;

public class GameOverWindow extends JFrame {

	// public static AppHiddenListener readMeActionListener; // For Reusing
	// Action Listener
	// public static MainWindow mainWindow;
	// public static GameWindow gameWindow;

	// Create the frame.
	public GameOverWindow(String winner, Player p1, Player p2) {
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
				GameWindow.dispose();
				dispose();
				MainWindow.toFront2();
			}
		});

		JButton btnRevanche = new JButton("REVANCHE");
		btnRevanche.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRevanche.setBounds(364, 152, 132, 46);
		getContentPane().add(btnRevanche);
		btnRevanche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent backToGame) {
				try {
					GameWindow.dispose();
					GameWindow.start(p1, p2);
					dispose();
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			};
		});

		// Sieger Animation
		JLabel lblAnimation = new JLabel("Animation");
		lblAnimation.setBackground(Color.BLACK);
		lblAnimation.setIcon(new ImageIcon("systemPictures"+ File.separator +"baerchen-sieger.gif"));
		lblAnimation.setBounds(55, 74, 253, 166);
		getContentPane().add(lblAnimation);
	}
}