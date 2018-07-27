package project;

import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.EventQueue;
//import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/***
 * This class represents the highscore window, in which the players are shown
 * their respective scores based on their played games.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
public class HighscoreWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private static HighscoreWindow frame;

	/**
	 * This method creates a new highscore window and show it.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HighscoreWindow();
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
	 * The constructor of this class.
	 */
	public HighscoreWindow() {
		// TableHeaders: Platz Name Score Games Wins losses Ties
		// table_1 = new JTable();
		// getContentPane().add(table_1, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		// initalize TableFilling Arrays with correct size
		String[] colunmns = { "Platz", "Name", "Score", "Games", "Wins", "Losses", "Ties" };
		Object[][] data = new Object[DataManager.getAllPlayerNames().size()][colunmns.length];
		// filling data-Array
		Object[] names = DataManager.getAllPlayerNames().toArray();
		for (int i = 0; i < data.length; i++) {
			Player p = DataManager.getPlayerObj(names[i].toString());
			data[i][1] = p.getName();
			data[i][2] = p.getScore();
			data[i][3] = p.getNrOfGames();
			data[i][4] = p.getWins();
			data[i][5] = p.getLosses();
			data[i][6] = p.getTies();
		}
		// Sortiere Array nach Score
		data = sortArray(data);
		// füge Platz hinzu
		for (int i = 0; i < data.length; i++) {
			data[i][0] = i + 1;
		}
		table = new JTable(data, colunmns);
		table.setBounds(100, 100, 450, 300);
		table.setFillsViewportHeight(true);
		contentPane.add(table.getTableHeader(), BorderLayout.PAGE_START);
		contentPane.add(table, BorderLayout.CENTER);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		contentPane.add(btnClose, BorderLayout.SOUTH);
	}

	/**
	 * This simple sorting algorithm helps sorting the results of the player scores.
	 * 
	 * @param toSort
	 *            The two-dimensional table with the score in the third entry.
	 * @return The sorted highscore array, which can then be used in a table.
	 */
	private static Object[][] sortArray(Object[][] toSort) {
		int marker = toSort.length - 1;
		while (marker > 0) {
			int idxMax = 0;
			for (int i = 1; i <= marker; i++) {
				if ((int) toSort[i][2] < (int) toSort[idxMax][2]) {
					idxMax = i;
				}
			}
			swap(toSort, idxMax, marker);
			marker--;
		}
		return toSort;
	}

	/**
	 * This is an auxiliary method for the sorting algorithm.
	 * 
	 * @param data
	 *            The array.
	 * @param idx1
	 *            The first row to be swapped.
	 * @param idx2
	 *            The second row to be swapped.
	 * @return The data array with swapped columns.
	 */
	private static Object[][] swap(Object[][] data, int idx1, int idx2) {
		Object[] temp = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = temp;
		return data;
	}
}
