package project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/***
 * This class represents a custom designed button, and is used for the fancy
 * "Start Game" Button in the main window.
 * 
 * @author Enes Akgümus, Simon Becht, Alexander Dreher, Emma Falldorf, Sebastian
 *         Michaelis, Tobias Rothley
 *
 */
public class RoundCornerButton extends JButton {

	private static final long serialVersionUID = 1L;
	private static final int ARC_WIDTH = 15;
	private static final int ARC_HEIGHT = 15;
	private static final int FOCUS_STROKE = 4;
	// private Color TRACTION_COLOR = new Color(126, 141, 180);
	private Font font = new Font("Helvetica", Font.ROMAN_BASELINE, 22);
	// private Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
	// private int state = 0;

	/**
	 * The constructor of this class.
	 * 
	 * @param text
	 *            The displayed text of the button.
	 * @param dimension
	 *            The preferred size of the button.
	 */
	public RoundCornerButton(String text, Dimension dimension) {
		super("<html> <center>" + text.replaceAll("\n", "<br/>") + "</center> </html>");
		this.setPreferredSize(dimension);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setMargin(new Insets(0, 0, 0, 0));
		setFont(font);
	}

	@Override
	public void updateUI() {
		super.updateUI();
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBackground(Color.BLACK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		int buttonWidth = getWidth() - 1;
		int buttonHeight = getHeight() - 1;
		Shape shape = new RoundRectangle2D.Float(0, 0, buttonWidth, buttonHeight, ARC_WIDTH, ARC_HEIGHT);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.fill(shape);
		g2.dispose();
		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		int buttonWidth = getWidth() - 1;
		int buttonHeight = getHeight() - 1;
		int xPos = 3;
		Shape border = new RoundRectangle2D.Float(xPos, xPos, buttonWidth - xPos, buttonHeight - xPos, ARC_WIDTH,
				ARC_HEIGHT);
		Rectangle rectangle = border.getBounds();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(3f));
		rectangle.grow(FOCUS_STROKE / 2, FOCUS_STROKE / 2);
		Path2D.Double lb = new Path2D.Double();
		lb.moveTo(rectangle.x + rectangle.width, rectangle.y + 5);
		lb.lineTo(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
		lb.lineTo(rectangle.x + 5, rectangle.y + rectangle.height);
		lb.closePath();
		g2.setColor(Color.BLACK);
		g2.setClip(lb);
		g2.draw(border);
		g2.setColor(Color.DARK_GRAY);
		Area area = new Area(rectangle);
		area.subtract(new Area(lb));
		g2.setClip(area);
		g2.draw(border);
		// For shadow
		Shape border3 = new RoundRectangle2D.Float(xPos - 3, xPos - 3, getWidth(), getHeight(), 25, 25);
		Rectangle ractangle3 = border3.getBounds();
		ractangle3.grow(5, 5);
		Path2D.Double lb3 = new Path2D.Double();
		lb3.moveTo((ractangle3.x + ractangle3.width), (ractangle3.y));
		lb3.lineTo((ractangle3.x), (ractangle3.y));
		lb3.lineTo(ractangle3.x, (ractangle3.y + ractangle3.height));
		lb3.closePath();
		g2.setStroke(new BasicStroke(3f));
		g2.setColor(Color.BLACK);
		g2.setClip(lb3);
		g2.draw(border3);

		g2.dispose();
	}

}
