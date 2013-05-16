package ui.SwingMessage;

import Email.Message;
import javax.swing.*;
import java.awt.TextArea;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class ComposeMail extends JFrame implements ActionListener {
    // This message

    Message message;
    JTextField subjectField;
    JTextField toField;
    TextArea messageContentTextArea;

    public ComposeMail(Message msg) {
        super("Email");

        message = msg;



    }

    public void writeMail() {
        JPanel composeMailPanel = new JPanel(new MigLayout());
        
        JLabel toLabel = new JLabel("To", JLabel.LEFT);
        JLabel subjectLabel = new JLabel("Subject");
        
        toField = new JTextField();
        subjectField = new JTextField();
        messageContentTextArea = new TextArea("Enter the email");

        JButton sendMailButton = new JButton("Send");
        sendMailButton.setToolTipText("Sends mail to the corresponding To information");

        JButton saveDraftButton = new JButton("Draft");
        saveDraftButton.setToolTipText("Save mail in Draft folder, Does not send mail");

        JButton cancelMailButton = new JButton("Cancel");
        cancelMailButton.setToolTipText("Delete the mail and move it to recycle bin draft");

        composeMailPanel.add(toLabel);
        composeMailPanel.add(toField, "span,grow,wrap");
        composeMailPanel.add(subjectLabel);
        composeMailPanel.add(subjectField, "span,grow,wrap");
        composeMailPanel.add(messageContentTextArea, "span,grow,wrap");
        composeMailPanel.add(sendMailButton);
        composeMailPanel.add(saveDraftButton);
        composeMailPanel.add(cancelMailButton);
        this.add(composeMailPanel);
        this.setSize(650, 380);
        
        refresh();
        
        this.setVisible(true);
    }
    
    public void refresh() {
        toField.setText(message.getHeader("To"));
        subjectField.setText(message.getHeader("Subject"));
        messageContentTextArea.setText(message.getContent());
    }
    
    public void actionPerformed(ActionEvent e) {
    }
}
