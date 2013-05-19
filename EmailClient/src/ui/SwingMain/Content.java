package ui.SwingMain;

import Email.MessageController;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Content extends JTextArea {

    MessageController store;

    /**
     * Constructor
     */
    public Content(MessageController controller) {
        this.store = controller;
        this.setEditable(false);
        this.setBorder(new LineBorder(Color.BLACK));
    }
    
    public void showMessage(String messageid) {
        String content = store.getEmailContent(messageid);
        this.setText(content);
    }
}
