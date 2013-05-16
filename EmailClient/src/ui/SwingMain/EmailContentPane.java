package ui.SwingMain;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 *
 * @author anasalkhatib
 */
public class EmailContentPane extends JPanel {

    public EmailContentPane() {
        //setSize(20, 20);
        JTextArea emailContent = new JTextArea("Content of email");
        add(emailContent, "west,grow, span, fill");
        setBorder(new LineBorder(Color.BLACK));

    }
}
