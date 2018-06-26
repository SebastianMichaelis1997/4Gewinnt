package project;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerCreation {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public PlayerCreation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	};

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(45, 218, 117, 29);
		frame.getContentPane().add(btnSave);

		JLabel lblUsername = new JLabel("Enter Player Name:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(10, 25, 180, 35);
		frame.getContentPane().add(lblUsername);

		JFileChooser chooser = new JFileChooser(); // For Icon choosing

		JButton btnDelete = new JButton("Choose Icon"); // For Icon choosing
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(45, 125, 117, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser.showOpenDialog(null);
			}
		});
		frame.getContentPane().add(btnDelete);

		JTextField nameField = new JTextField("Enter Name");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setVisible(true);
		nameField.setBounds(200, 22, 220, 41);
		nameField.selectAll();
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (nameField.getText() != ("Enter Name")) { // File for a new
																	// player gets
																	// added to
																	// folder
																	// "players"
						DataManager.addPlayer(nameField.getText()); // Adds Standard
																	// Values for
																	// players
						DataManager.getPlayer(nameField.getText()); // Just for
																	// directly
																	// testing
																	// @Simon
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

		ImageIcon icon = createImageIcon("sonne.jpg"); // Parameter ändern
														// @Simon
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		label1.setBounds(255, 84, 110, 110);

		frame.getContentPane().add(btnCancel);
		frame.getContentPane().add(label1);
	}
}
