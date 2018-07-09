package project;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.IllegalCharsetNameException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreatePlayer {

	private JFrame frame;

	private String currentName;

	/**
	 * Create the application.
	 */
	public CreatePlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(45, 218, 117, 29);
		frame.getContentPane().add(btnSave);

		JLabel lblUsername = new JLabel("Enter Player Name");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(10, 25, 180, 35);
		frame.getContentPane().add(lblUsername);

		JFileChooser chooser = new JFileChooser(); // For Icon choosing
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		JButton btnDelete = new JButton("Choose Icon"); // For Icon choosing
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(45, 125, 117, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser.showOpenDialog(null);
				// @Simon chooser.getSelectedFile().getAbsolutePath() gibt den
				// absoluten Pfad der ausgesuchten Datei wieder
			}
		});
		frame.getContentPane().add(btnDelete);

		JTextField nameField = new JTextField("Enter Name");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setVisible(true);
		nameField.setBounds(200, 22, 220, 41);
		nameField.selectAll();

		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent iconChoosen) {
				currentName = nameField.getText();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// File for a new player gets added to folder "players"
					if (nameField.getText().equals("Enter Name")) {
						throw new IllegalNameException("Enter Name");
					}
						if (DataManager.addPlayer(nameField.getText())
								&& (chooser.getSelectedFile().toString() != null)) {
							// Adds standard values for players
							// Just if a player was added successfully and an
							// icon was selected, the icon
							// gets fetched
							ImageIcon icon = createImageIcon(chooser.getSelectedFile().toString(),
									chooser.getSelectedFile().getName());
							if (icon != null)
								DataManager.changeProperty(nameField.getText(), "icon",
										chooser.getSelectedFile().getName());
							else
								throw new ImageAlreadyExistsException("");
						}
				} catch (PlayerAlreadyExistsException exception) {
					System.out.println(exception.getMessage());
				} catch (ImageAlreadyExistsException e1) {
					ErrorWindow.start(e1.getMessage());
				}catch (IllegalNameException e2) {
					ErrorWindow.start(e2.getMessage());
				}finally {
					MainWindow.refreshPlayerComboBox();
					frame.dispose(); // closes the window
				}
			}
		});
		frame.getContentPane().add(nameField);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(303, 218, 117, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose(); // closes the window
			}
		});
		frame.setVisible(true);
		frame.getContentPane().add(btnCancel);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String fileName) {
		try {
			if (DataManager.saveImage(path, fileName)) {

			} else
				// if an image is already name like "filename"
				return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(path, fileName);
	}
}
