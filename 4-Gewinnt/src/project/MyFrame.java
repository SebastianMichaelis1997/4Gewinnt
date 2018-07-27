package project;

import java.awt.*;
import javax.swing.*;

/***
 * Is this class maybe deprecated?
 *
 */
class MyFrame extends JFrame

{
	JButton bChange; // reference to the button object

	MyFrame(String title) {
		super(title);
		setLayout(new FlowLayout());
		add(new RoundCornerButton("<html> <center>" + "Button".replaceAll("\n", "<br/>") + "</center> </html>",
				new Dimension(105, 65)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.lightGray);
	}
}
