package project;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

	public MainWindow() {

		setTitle("Hauptmenü");
		setSize(1200, 800);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		getContentPane().add(tabbedPane);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		JLabel label1 = new JLabel();
		label1.setText("You are in area of Game");

		JLabel label2 = new JLabel();
		label2.setText("You are in area of Players");

		JLabel label3 = new JLabel();
		label3.setText("You are in area of Settings");

		panel1.add(label1);
		panel2.add(label2);
		panel3.add(label3);

		tabbedPane.addTab(
				"Game                                                                                          ",
				panel1);
		tabbedPane.addTab(
				"Player                                                                                          ",
				panel2);
		tabbedPane.addTab(
				"Settings                                                                                          ",
				panel3);
	}

	public static void main(String[] args) {

		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);

	}
}