
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HighscoreWindow extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private static HighscoreWindow frame;

	/**
	 * Launch the application.
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

	// TableHeaders: Platz Name Score Games Wins losses Ties
	/**
	 * Create the frame.
	 */
	public HighscoreWindow() {
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

	private static Object[][] swap(Object[][] data, int idx1, int idx2) {
		Object[] temp = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = temp;
		return data;
	}
}
