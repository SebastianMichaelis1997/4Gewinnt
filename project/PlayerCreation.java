package project;

import java.awt.EventQueue;

import javax.swing.*;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PlayerCreation {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	//for testing @Simon
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerCreation window = new PlayerCreation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JFileChooser chooser = new JFileChooser();	//For Icon choosing

		JButton btnDelete = new JButton("Choose Icon");	//For Icon choosing
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
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameField.getText()!= ("Enter Name")) {			//File for a new player gets added to folder "players"
					DataManager.addPlayer(nameField.getText());		//Adds Standard Values for players
					DataManager.getPlayer(nameField.getText());		//Just for directly testing @Simon
				}
			}
		});
		frame.getContentPane().add(nameField);
		

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(303, 218, 117, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		frame.setVisible(true);
		
				
		
		
		ImageIcon icon = createImageIcon("sonne.jpg");							//Parameter ändern @Simon
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		label1.setBounds(255,84,110, 110);
		
		frame.getContentPane().add(btnCancel);
		frame.getContentPane().add(label1);
	}
}
