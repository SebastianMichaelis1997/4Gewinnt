package project;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class ExceptionWindow extends JFrame {

	public static ActionListener readMeActionListener; // For Reusing Action Listener
	public static MainWindow mainWindow;
	public static GameWindow gameWindow;
	public static ExceptionWindow gow; 
	private JTextField txtExceptionMessage;
		
	public ExceptionWindow(String message) {
		getContentPane().setBackground(Color.WHITE);

		setTitle("Exception");
		setBounds(200, 100, 550, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		
		txtExceptionMessage = new JTextField();
		txtExceptionMessage.setText(message);
		txtExceptionMessage.setBounds(320, 35, 192, 136);
		getContentPane().add(txtExceptionMessage);
		txtExceptionMessage.setColumns(10);

		JButton btn1 = new JButton("Button1");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn1.setBounds(81, 194, 132, 46);
		getContentPane().add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Repair) {
		
			}
		});
		
		JButton btn2 = new JButton("Button2");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn2.setBounds(329, 194, 132, 46);
		getContentPane().add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent close) {
				
			};
		});
		
		
		/* Insert "Warning Image" 
		JLabel lblWarning = new JLabel("Warning");
		ImageIcon warning = new ImageIcon(ExceptionWindow.class.getResource("/project/Achtung.jpg"));
		warning.setImage(warning.getImage().getScaledInstance(193,84,Image.SCALE_SMOOTH));
		lblWarning.setIcon(warning);
		lblWarning.setBounds(32, 31, 225, 115);
		getContentPane().add(lblWarning);
		*/
	}
}