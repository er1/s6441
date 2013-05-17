package ui.SwingMessage;

import Email.Message;
import javax.swing.*;
import java.awt.TextArea;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

/**
 * Compose Mail Window
 */
public class ComposeMail extends JFrame implements ActionListener {

    Message message;
    JTextField subjectField;
    JTextField toField;
    TextArea messageContentTextArea;

	JButton cancelMailButton;
    JPanel composeMailPanel;

    /**
     *
     * @param msg The message object used to populate fields
     */
    public ComposeMail(Message msg) {
        super("Email");

        message = msg;
    }

    /**
     * Populate Fields //TODO Rename function
     */
    public void writeMail() {

        composeMailPanel = new JPanel(new MigLayout());
        JLabel toLabel = new JLabel("To", JLabel.LEFT);
        JLabel subjectLabel = new JLabel("Subject");

        toField = new JTextField();
        subjectField = new JTextField();
        messageContentTextArea = new TextArea("Enter the email");

        JButton sendMailButton = new JButton("Send");
        sendMailButton.setToolTipText("Sends mail to the corresponding To information");

        JButton saveDraftButton = new JButton("Draft");
        saveDraftButton.setToolTipText("Save mail in Draft folder, Does not send mail");

        cancelMailButton = new JButton("Cancel");
        cancelMailButton.setToolTipText("Delete the mail and move it to recycle bin draft");
        cancelMailButton.addActionListener(this);


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

    /**
     * Refresh display of window
     */
    public void refresh() {
        toField.setText(message.getHeaderValue("To"));
        subjectField.setText(message.getHeaderValue("Subject"));
        messageContentTextArea.setText(message.getContent());
    }

    /**
     *
     * @param e Action performed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelMailButton) {
            this.setVisible(false);
            this.dispose();
        }
    }
}
