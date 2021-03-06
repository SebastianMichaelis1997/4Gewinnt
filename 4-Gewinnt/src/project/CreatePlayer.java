package project;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.IllegalNameException;
//import exceptions.PlayerAlreadyExistsException;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Color;

/***
 * This class represents the player creation window, in which the player can
 * enter his name, a color and desired icon. The saving mechanics are handled in
 * DataManager.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf,
 *         Sebastian Michaelis, Tobias Rothley
 *
 */
public class CreatePlayer {

	private JFrame frame;

	public String currentName;

	/**
	 * The constructor of this class.
	 */
	public CreatePlayer() {
		initialize();
	}

	/**
	 * This method initializes the window and its components.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("Create Player");
		frame.setBounds(100, 100, 628, 389);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JComboBox<String> colorComboBox = new JComboBox<String>(new String[] { "red", "blue", "green" });
		colorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		colorComboBox.setBounds(327, 185, 220, 35);
		frame.getContentPane().add(colorComboBox);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSave.setBounds(73, 284, 117, 29);
		frame.getContentPane().add(btnSave);

		JLabel lblUsername = new JLabel("Enter Player Name");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(84, 25, 180, 35);
		frame.getContentPane().add(lblUsername);

		JFileChooser chooser = new JFileChooser(); // For Icon choosing

		JButton btnChooser = new JButton("Choose Icon"); // For Icon choosing
		btnChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChooser.setBounds(76, 100, 154, 29);
		btnChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif", "png");
				chooser.setFileFilter(filter);
				chooser.showOpenDialog(null);
			}
		});
		frame.getContentPane().add(btnChooser);

		JTextField nameField = new JTextField("Enter Name");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setVisible(true);
		nameField.setBounds(327, 22, 220, 41);
		nameField.selectAll();

		JLabel picture = new JLabel("");
		picture.setBackground(new Color(64, 64, 64));
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setBounds(97, 156, 100, 100);
		frame.getContentPane().add(picture);

		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent iconChoosen) {
				currentName = nameField.getText();
			}
		});
		chooser.addAncestorListener(new AncestorListener() {
			// Listener for showing the Icon when chosen via chooser
			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
				// A new image has been added using the file chooser
				// Now we want to display this image in the screen
				if (chooser.getSelectedFile().toString() != null) {
					picture.setText("");
					try {

						ImageIcon icon = new ImageIcon(chooser.getSelectedFile().toString());
						icon = DataManager.resizeIcon(icon, 99, 92);

						// picture is not saved jet, its just for giving the user
						// the feedback which icon he has chosen
						picture.setIcon(icon);
					} catch (Exception e) {
						System.out.println("IOException!");
					}
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
					if (nameField.getText().equals("Enter Name") || nameField.getText().equals("Select Player")) {
						throw new IllegalNameException(nameField.getText());
					}
					try {
						if (DataManager.addPlayer(nameField.getText(), colorComboBox.getSelectedItem().toString())
								&& (chooser.getSelectedFile() != null)) {
							// Adds standard values for players
							// Just if a player was added successfully and an
							// icon was selected, the icon
							// gets fetched
							ImageIcon icon = createImageIcon(chooser.getSelectedFile().toString(),
									chooser.getSelectedFile().getName());
							if (icon != null)
								try {
									DataManager.changeProperty(nameField.getText(), "icon",
											chooser.getSelectedFile().getName());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							else
								System.out.println(
										"Error while saving File: An image with this name already exists. Please rename it!");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IllegalNameException e2) {
					ErrorWindow.start(e2.getMessage());
				} finally {
					MainWindow.refreshPlayerComboBox();
					frame.dispose(); // closes the window
				}
			}
		});
		frame.getContentPane().add(nameField);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(427, 284, 117, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose(); // closes the window
			}
		});
		frame.setVisible(true);
		frame.getContentPane().add(btnCancel);

		JLabel lblChooseColor = new JLabel("Choose Color");
		lblChooseColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseColor.setBounds(375, 97, 133, 35);
		frame.getContentPane().add(lblChooseColor);

		JSeparator separator = new JSeparator();
		separator.setBounds(25, 78, 556, 11);
		frame.getContentPane().add(separator);

		JLabel lblOr = new JLabel("or");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setBounds(279, 110, 45, 13);
		frame.getContentPane().add(lblOr);
	}

	/**
	 * This auxiliary method creates an easier constructor for ImageIcon.
	 * 
	 * @param path
	 *            The Path of the image-file.
	 * @param fileName
	 *            The name under which it should be saved.
	 * @return An ImageIcon object, with the desired image.
	 */
	protected ImageIcon createImageIcon(String path, String fileName) {
		try {
			if (DataManager.saveImage(path, fileName)) {

			} else
				// if an image is already named like "filename"
				return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(path, fileName);
	}

}
