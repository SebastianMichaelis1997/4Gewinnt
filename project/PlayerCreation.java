package project;

import java.awt.EventQueue;

import javax.swing.*;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.io.FileReader;
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(45, 218, 117, 29);
		frame.getContentPane().add(btnNewButton);

		JLabel lblUsername = new JLabel("Enter Player Name:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(10, 25, 180, 35);
		frame.getContentPane().add(lblUsername);
		
		JFileChooser chooser = new JFileChooser();	//For Icon choosing

		JButton btnDelete = new JButton("Add Icon");	//For Icon choosing
		btnDelete.setBounds(174, 218, 117, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser.showOpenDialog(null);
			}	
		});
		frame.getContentPane().add(btnDelete);

		JTextField nameField = new JTextField("test");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setVisible(true);
		nameField.setBounds(200, 22, 220, 41);
		frame.getContentPane().add(nameField);
		

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(303, 218, 117, 29);
		
		JButton button = new JButton("Choose Icon");
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.setBounds(10, 96, 117, 29);
		frame.getContentPane().add(button);
		frame.setVisible(true);
		
		
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"\\src\\project\\Capture.PNG");
		JLabel label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
		
		frame.getContentPane().add(btnCancel);
		frame.getContentPane().add(label1);
	}
}

