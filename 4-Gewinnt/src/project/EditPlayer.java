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
					frame.setTitle("Edit Player");
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
		setTitle("Edit Player");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSave.setBounds(69, 293, 150, 50);
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
		contentPane.setLayout(null);
		contentPane.add(btnSave);

		txtChangeName = new JTextField();
		txtChangeName.setBounds(188, 26, 168, 43);
		txtChangeName.setHorizontalAlignment(SwingConstants.CENTER);
		txtChangeName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtChangeName.setText(player.toString());
		contentPane.add(txtChangeName);
		txtChangeName.setColumns(10);
		txtChangeName.selectAll();

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
		picture.setText("No image used!");
		picture.setIcon(new ImageIcon(System.getProperty("user.dir")
				+ File.separator + "profilePictures" + File.separator
				+ DataManager.getProperty(player.toString(), "icon")));
		picture.setVisible(true);
		contentPane.add(picture);
		
		JButton btnEditIcon = new JButton("Edit Icon");
		btnEditIcon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditIcon.setBounds(59, 103, 150, 42);
		contentPane.add(btnEditIcon);
		
		JLabel lblEditColor = new JLabel("Edit Color");
		lblEditColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEditColor.setBounds(341, 109, 140, 36);
		contentPane.add(lblEditColor);
		
		//Edit C
		JComboBox colorComboBox = new JComboBox(new String[]{"red","blue","green"});
		colorComboBox.setBounds(310, 156, 212, 36);
		contentPane.add(colorComboBox);
	}
}
