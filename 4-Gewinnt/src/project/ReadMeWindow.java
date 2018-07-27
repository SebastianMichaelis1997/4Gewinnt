package project;

import javax.swing.*;
//import javax.swing.border.EtchedBorder;
//import javax.swing.border.TitledBorder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/***
 * This class represents the Window for the ReadMe information, while in-game.
 * It is just called via constructor, and automatically set visible.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
public class ReadMeWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String README = "resources";

	/**
	 * The constructor of this class, which automatically shows the window as well.
	 */
	public ReadMeWindow() {
		String text = "";
		try {
			DataManager.checkDirectory(README);
			FileReader fr = new FileReader(README + File.separator + "ReadMe.txt");
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
		JPanel middlePanel = new JPanel();
		// middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));

		// create the middle panel components

		JTextArea display = new JTextArea();
		display.setText(text);
		display.setEditable(false); // set textArea non-editable
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		display.grabFocus();
		display.setCaretPosition(20);

		// Add Textarea in to middle panel
		middlePanel.add(scroll);

		// My code
		JFrame frame = new JFrame();
		frame.add(middlePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
