package project;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMeWindow extends JFrame {

	public ReadMeWindow() {
		String text = "";
		try {
			FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\project\\ReadMe");
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
		// System.out.println(text);
		JPanel middlePanel = new JPanel();
		// middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Display Area"));

		// create the middle panel components

		JTextArea display = new JTextArea(16, 58);
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
