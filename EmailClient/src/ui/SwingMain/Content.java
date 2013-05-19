package ui.SwingMain;

import Email.MessageController;
import javax.swing.JTextArea;

public class Content extends JTextArea {

    MessageController store;

    /**
     * Constructor
     */
    public Content(MessageController controller) {
        this.store = controller;
        this.setEditable(false);
    }
    
    public void showMessage(String messageid) {
        String content = store.getEmailContent(messageid);
        this.setText(content);
    }
}
