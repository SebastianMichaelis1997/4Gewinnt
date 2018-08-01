import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ThinLineFrame {
    private JFrame frame = new JFrame();
    private JScrollPane scrollPane;
    private JPanel panel = new JPanel();
    private JPanel panelNorth = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelCenterCh1 = new JPanel();
    private JPanel panelCenterCh2 = new JPanel();
    private JPanel panelCenterCh3 = new JPanel();
    private JPanel panelCenterCh4 = new JPanel();
    private JPanel panelCenterCh5 = new JPanel();
    private JPanel panelSouth = new JPanel();

    public ThinLineFrame() {
        panelNorth.setBackground(Color.red.darker());
        panelNorth.setPreferredSize(new Dimension(80, 30));
        //
        panelCenter.setBackground(Color.darkGray);
        panelCenter.setLayout(new GridLayout(5, 1, 2, 2));
        //
        panelCenterCh1.setLayout(new BorderLayout());
        JButton panelCenterCh1Button = new JButton();
        panelCenterCh1.add(panelCenterCh1Button, BorderLayout.CENTER);
        //
        JButton panelCenterCh2Button1 = new JButton();
        JButton panelCenterCh2Button2 = new JButton();
        panelCenterCh2.setLayout(new GridLayout(2, 1, 2, 2));
        panelCenterCh2.add(panelCenterCh2Button1);
        panelCenterCh2.add(panelCenterCh2Button2);
        //
        JButton panelCenterCh3Button1 = new JButton();
        JButton panelCenterCh3Button2 = new JButton();
        panelCenterCh3.setLayout(new GridLayout(2, 1, 2, 2));
        panelCenterCh3.add(panelCenterCh3Button1);
        panelCenterCh3.add(panelCenterCh3Button2);
        //
        JButton panelCenterCh4Button1 = new JButton();
        JButton panelCenterCh4Button2 = new JButton();
        panelCenterCh4.setLayout(new GridLayout(2, 1, 2, 2));
        panelCenterCh4.add(panelCenterCh4Button1);
        panelCenterCh4.add(panelCenterCh4Button2);
        //
        panelCenterCh5.setLayout(new BorderLayout());
        JButton panelCenterCh5Button = new JButton();
        panelCenterCh5.add(panelCenterCh5Button, BorderLayout.CENTER);
        //
        panelCenter.add(panelCenterCh1);
        panelCenter.add(panelCenterCh2);
        panelCenter.add(panelCenterCh3);
        panelCenter.add(panelCenterCh4);
        panelCenter.add(panelCenterCh5);
        //
        panelSouth.setBackground(Color.red.darker());
        panelSouth.setPreferredSize(new Dimension(80, 30));
        //
        panel.setLayout(new BorderLayout(2, 2));
        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(panelCenter, BorderLayout.CENTER);
        panel.add(panelSouth, BorderLayout.SOUTH);
        //
        scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(80, 600));
        frame.setLocation(100, 150);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ThinLineFrame dlg = new ThinLineFrame();
            }
        });
    }
}