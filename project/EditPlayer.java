package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPlayer extends JFrame {
	private JPanel contentPane;
	private JTextField txtChangeName;

	/**
	 * Launch the application.
	 */
	public static void start(Object player) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditPlayer frame = new EditPlayer(player);
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
	public EditPlayer(Object player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(40, 193, 150, 50);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent save) {
				DataManager.changeProperty(player.toString(), "name", txtChangeName.getText());
			}
		});
		contentPane.add(btnSave);

		txtChangeName = new JTextField();
		txtChangeName.setHorizontalAlignment(SwingConstants.CENTER);
		txtChangeName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtChangeName.setText("Change Name");
		txtChangeName.setBounds(153, 27, 168, 43);
		contentPane.add(txtChangeName);
		txtChangeName.setColumns(10);

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(286, 193, 140, 50);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnClose);
	}
}
