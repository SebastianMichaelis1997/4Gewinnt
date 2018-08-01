package project;

import java.awt.EventQueue;

import exceptions.IllegalNameException;
//import exceptions.PlayerAlreadyExistsException;
import exceptions.PlayerAlreadyExistsException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;

/***
 * This class represents the window, in which players can edit their attributes
 * to a certain degree.
 * 
 * @author Enes Akg�mus, Simon Becht, Alexander Dreher, Emma Falldorf,
 *         Sebastian Michaelis, Tobias Rothley
 *
 */
public class EditPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtChangeName;
	private ImageIcon icon;

	/**
	 * This method creates a new window and shows it.
	 */
	public static void start(Object player) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditPlayer frame = new EditPlayer(player);
					frame.setTitle("Edit Player");
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * The constructor of this class.
	 *
	 */
	public EditPlayer(Object player) {
		setTitle("Edit Player");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		txtChangeName = new JTextField();
		txtChangeName.setBounds(188, 26, 168, 43);
		txtChangeName.setHorizontalAlignment(SwingConstants.CENTER);
		txtChangeName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtChangeName.setText(player.toString());
		txtChangeName.setEditable(false);
		contentPane.add(txtChangeName);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClose.setBounds(352, 293, 140, 50);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnClose);

		JLabel picture = new JLabel();
		picture.setBounds(82, 171, 100, 100);
		picture.setHorizontalAlignment(SwingConstants.CENTER);

		picture.setIcon(
				DataManager
						.resizeIcon(
								new ImageIcon(System.getProperty("user.dir") + File.separator + "profilePictures"
										+ File.separator + DataManager.getProperty(player.toString(), "icon")),
								99, 92));
		picture.setVisible(true);
		// the icon of the player gets shown
		contentPane.add(picture);

		JFileChooser chooser = new JFileChooser(); // For Icon choosing
		JButton btnEditIcon = new JButton("Edit Icon");
		btnEditIcon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditIcon.setBounds(59, 103, 150, 42);
		btnEditIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent editAction) {
				chooser.showOpenDialog(null);
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
					icon = new ImageIcon(chooser.getSelectedFile().toString());
					// picture is not saved jet, its just for giving the user
					// the feedback which icon he has chosen
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
		contentPane.add(btnEditIcon);

		JLabel lblEditColor = new JLabel("Edit Color");
		lblEditColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEditColor.setBounds(341, 109, 140, 36);
		contentPane.add(lblEditColor);

		// Edit C
		JComboBox<String> colorComboBox = new JComboBox<String>();
		colorComboBox.setEditable(true);
		colorComboBox.setSelectedItem("Select Color");
		colorComboBox.setEditable(false);
		colorComboBox.addItem("red");
		colorComboBox.addItem("blue");
		colorComboBox.addItem("green");
		colorComboBox.setBounds(310, 156, 212, 36);
		contentPane.add(colorComboBox);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(59, 293, 150, 50);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent save) {
				// If player name was changed, this gets saved
				try {
					if (txtChangeName.getText().equals("Select Player")) {
						throw new IllegalNameException("Select Player");
					}
					try {
						DataManager.changeProperty(player.toString(), "name", txtChangeName.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IllegalNameException e1) {
					ErrorWindow.start(e1.getMessage());
				} finally {
					MainWindow.refreshPlayerComboBox();
					dispose();
				}
				// If player icon was changed, this gets saved
				if (icon != null) {
					try {
						try {
							// At first, the old picture gets deleted
							try {
								DataManager.deleteImage(DataManager.getProperty(player.toString(), "icon"));
							} catch (Exception e) {
								// Player had no picture
							}
							// and the new icon gets saved...
							DataManager.saveImage(chooser.getSelectedFile().toString(),
									chooser.getSelectedFile().getName());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// ..and the old icon-name in the player properties gets overwritten by the new
						// one
						DataManager.changeProperty(txtChangeName.getText(), "icon",
								chooser.getSelectedFile().getName());
					} catch (PlayerAlreadyExistsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// If player color was changed, this gets saved
				if (colorComboBox.getSelectedItem() != "Edit Color") {
					try {
						DataManager.changeProperty(txtChangeName.getText(), "color",
								colorComboBox.getSelectedItem().toString());
					} catch (PlayerAlreadyExistsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnSave);
	}
}
