package project;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.event.DocumentListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

		JButton btnChooser = new JButton("Choose Icon"); // For Icon choosing
		btnChooser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChooser.setBounds(45, 125, 117, 29);
		btnChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser.showOpenDialog(null);
			}
		});
		frame.getContentPane().add(btnChooser);

		JTextField nameField = new JTextField("Enter Name");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setVisible(true);
		nameField.setBounds(200, 22, 220, 41);
		nameField.selectAll();
		
		JLabel picture = new JLabel("Picture");
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setBounds(261, 91, 100, 100);
		frame.getContentPane().add(picture);

		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent iconChoosen) {
				currentName = nameField.getText();
			}
		});
		chooser.addAncestorListener(new AncestorListener() {
			//Listener for showing the Icon when chosen via chooser
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
				// A new image has been added using the file chooser
				// Now we want to display this image in the screen
				if (chooser.getSelectedFile().toString() != null) {
					picture.setText("");
					ImageIcon icon = new ImageIcon(chooser.getSelectedFile().toString());
					//picture is not saved jet, its just for giving the user the feedback which icon he has chosen
					picture.setIcon(icon);	
				}

			}

			@Override
			public void ancestorMoved(AncestorEvent arg0) {
				// do nothing

			}

			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				// do nothing

			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent save) {
				try {
					// File for a new player gets added to folder "players"
					if (nameField.getText() != ("Enter Name")) {
						if (DataManager.addPlayer(nameField.getText())
								&& (chooser.getSelectedFile().toString() != null)) {
							// Adds standard values for players
							// Just if a player was added successfully and an icon was selected, the icon
							// gets fetched
							ImageIcon icon = createImageIcon(chooser.getSelectedFile().toString(),
									chooser.getSelectedFile().getName());
							if (icon != null)
								DataManager.changeProperty(nameField.getText(), "icon",
										chooser.getSelectedFile().getName());
							else
								System.out.println(
										"Error while saving File: An image with this name already exists. Please rename it!");
						}
					}
				} catch (PlayerAlreadyExistsException exception) {
					System.out.println(exception.getMessage());
				} finally {
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