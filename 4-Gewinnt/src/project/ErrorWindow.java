package project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

/***
 * This class represents an error window, in which we can show the player
 * different problems and errors, which have occured during execution.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
public class ErrorWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private static ErrorWindow frame;

	/**
	 * This method creates a new error window and shows it.
	 * 
	 * @param errorMessage
	 *            The contained error message.
	 */
	public static void start(String errorMessage) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ErrorWindow(errorMessage);
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This is the constructor of the class.
	 */
	public ErrorWindow(String errorMessage) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel(errorMessage);
		lblNewLabel.setIcon(new ImageIcon("systemPictures" + File.separator + "error.jpg"));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		contentPane.add(btnClose, BorderLayout.SOUTH);
	}
}