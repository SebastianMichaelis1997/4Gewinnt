package project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

/***
 * This class represents the window, in which players can be deleted. A sort of
 * confirmation dialogue, but custom.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
public class DeletePlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * This method creates a new window and shows it.
	 */
	public static void start(String player) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePlayer frame = new DeletePlayer(player);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This is the constructor of the class. It needs its information from a player
	 * object.
	 * 
	 * @param player
	 *            The player to be deleted.
	 */
	public DeletePlayer(String player) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 350, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblQuestion = new JLabel(
				"Do you really want to delete player " + DataManager.getProperty(player, "name") + "?");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuestion.setBounds(0, 33, 444, 59);
		contentPane.add(lblQuestion);

		JButton btnYes = new JButton("Yes");
		btnYes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnYes.setBounds(10, 175, 130, 40);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent deletePlayerYes) {
				DataManager.deletePlayer(player);
				MainWindow.refreshPlayerComboBox();
				dispose();
			}
		});
		contentPane.add(btnYes);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(296, 175, 130, 40);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent deletePlayerCancel) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
	}
}
