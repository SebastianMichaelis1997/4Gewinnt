package project;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

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
	 * 
	 * @throws MalformedURLException
	 */
	public EditPlayer(Object player) throws MalformedURLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(40, 193, 150, 50);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent save) {
				try {
					if (txtChangeName.getText().equals("Select Player")) {
						throw new IllegalNameException("Select Player");
					}
					DataManager.changeProperty(player.toString(), "name",
							txtChangeName.getText());
				} catch (PlayerAlreadyExistsException e) {
					ErrorWindow.start(e.getMessage());
				} catch (IllegalNameException e1) {
					ErrorWindow.start(e1.getMessage());
				} finally {
					MainWindow.refreshPlayerComboBox();
					dispose();
				}
			}
		});
		contentPane.add(btnSave);

		txtChangeName = new JTextField();
		txtChangeName.setHorizontalAlignment(SwingConstants.CENTER);
		txtChangeName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtChangeName.setText(player.toString());
		txtChangeName.setBounds(153, 27, 168, 43);
		contentPane.add(txtChangeName);
		txtChangeName.setColumns(10);
		txtChangeName.selectAll();

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(286, 193, 140, 50);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnClose);

		JLabel picture = new JLabel();
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setBounds(188, 80, 100, 100);
		picture.setText("No image used!");
		picture.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ File.separator + "profilePictures" + File.separator
				+ DataManager.getProperty(player.toString(), "icon")));
		picture.setVisible(true);
		contentPane.add(picture);
	}
}
