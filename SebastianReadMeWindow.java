package project;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMeWindow extends JFrame {
	JTextArea textArea;
	JScrollPane scrollPane;
	JPanel panel = new JPanel();

	public ReadMeWindow() {
		String text = "das";
		try {
			FileReader fr = new FileReader(
					"D:\\Users\\BKU\\SebastianMichaelis\\Desktop\\Notes Stammesrat 18.05.23.txt");
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
		System.out.println(text);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel.add(new JScrollPane(new JTextArea(text)));
		this.getContentPane().add(panel);
	}
}