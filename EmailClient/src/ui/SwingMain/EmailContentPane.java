package ui.SwingMain;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * What does this do?
 */
public class EmailContentPane extends JPanel {

    /**
     * Constructor
     */
    public EmailContentPane() {
        JTextArea emailContent = new JTextArea("Content of email");
        this.setLayout(new BorderLayout());
        emailContent.setEditable(false);
        add(emailContent);
        //setBorder(new LineBorder(Color.BLACK));

    }
}
