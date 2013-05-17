package ui.SwingMain;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 *
 */
public class EmailContentPane extends JPanel {

    /**
     * Constructor
     */
    public EmailContentPane() {
        JTextArea emailContent = new JTextArea("Content of email");
        add(emailContent, "west,grow, span, fill");
        setBorder(new LineBorder(Color.BLACK));

    }
}
