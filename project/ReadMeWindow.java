package project;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMeWindow {
	private JFrame f; // Main frame
	private JTextArea ta; // Text area
	private JScrollPane sbrText; // Scroll pane for text area
	private JButton btnClose; // Close Frame

	public ReadMeWindow() { // Constructor
		// Create Frame
		f = new JFrame("ReadMe");
		f.getContentPane().setLayout(new FlowLayout());

		// Get Content from ReadMeFile
		String text = "";
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir")
					+ "\\src\\project\\ReadMe");
			BufferedReader br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				text = text + "" + sCurrentLine + "\n";
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create Scrolling Text Area
		ta = new JTextArea(text, 16, 26);
		ta.setLineWrap(true);

		ta.setEditable(false);
		sbrText = new JScrollPane(ta);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Create Close Button
		btnClose = new JButton("Zurück");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		this.launchFrame();
	}

	public void launchFrame() { // Create Layout
		// Add text area and button to frame
		f.getContentPane().add(sbrText);
		f.getContentPane().add(btnClose);

		// Close when the close button is clicked
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display Frame
		f.pack(); // Adjusts frame to size of components
		f.setVisible(true);
	}

	// public static void main(String args[]) {
	// ReadMe2 gui = new ReadMe2();
	// gui.launchFrame();
	// }
}