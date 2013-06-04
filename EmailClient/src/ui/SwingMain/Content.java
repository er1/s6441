package ui.SwingMain;

import Email.MessageController;
import javax.swing.JTextArea;

/**
 * Content shows the Message for the Inbox, Outbox, Drafts, Sent Mail using persistent Storage
 * @author KarthikKrishnan
 */
public class Content extends JTextArea {

    MessageController controller;

    /**
     * Constructor
     * @param controller 
     */
    public Content() {
        this.controller = MessageController.getInstance();
        this.setEditable(false);
    }
    
    /**
     * Function to show the message for given message id
     * @param messageid
     */
    public void showMessage(String messageid) {
        String content = controller.getEmailContent(messageid);
        this.setText(content);
    }
}
