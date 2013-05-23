package ui.SwingMessage;

import Email.MessageController;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import ui.LabeledTextField;

/**
 * Compose Mail Window
 */
public class ComposeMail extends JFrame {

    String messageId;
    LabeledTextField subjectField;
    LabeledTextField toField;
    JTextArea messageContentTextArea;
    JButton cancelMailButton;
    JButton sendMailButton;
    JButton saveDraftButton;

    /**
     * Constructor for composing mail
     */
    public ComposeMail(String messageId) {
        super("Email");

        this.messageId = messageId;

        // Contruct fields
        subjectField = new LabeledTextField("Subject");
        toField = new LabeledTextField("To");
        messageContentTextArea = new JTextArea();

        sendMailButton = new JButton("Send");
        sendMailButton.setToolTipText("Sends mail to the corresponding To information");
        sendMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                send();
            }
        });

        saveDraftButton = new JButton("Save");
        saveDraftButton.setToolTipText("Save this message");
        saveDraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                save();
            }
        });

        cancelMailButton = new JButton("Close");
        cancelMailButton.setToolTipText("Move this message to the trash");
        cancelMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
            }
        });

        // Make header
        JPanel headerPanel = new JPanel();
        BoxLayout headerLayout = new BoxLayout(headerPanel, BoxLayout.Y_AXIS);
        headerPanel.setLayout(headerLayout);

        headerPanel.add(toField);
        headerPanel.add(subjectField);

        // Make footer
        JPanel footerPanel = new JPanel();
        BoxLayout footerLayout = new BoxLayout(footerPanel, BoxLayout.X_AXIS);
        footerPanel.setLayout(footerLayout);

        footerPanel.add(sendMailButton);
        footerPanel.add(saveDraftButton);
        footerPanel.add(cancelMailButton);

        this.setLayout(new BorderLayout());

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(messageContentTextArea);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setSize(650, 380);

        this.refresh();

        this.setVisible(true);
    }

    /**
     * Refresh display of window
     */
    private void refresh() {
        MessageController controller = MessageController.getInstance();
        subjectField.setText(controller.getEmailHeader(messageId, "Subject"));
        toField.setText(controller.getEmailHeader(messageId, "To"));
        messageContentTextArea.setText(controller.getEmailContent(messageId));
    }

    void save() {
        MessageController controller = MessageController.getInstance();
        controller.setEmailHeader(messageId, "Subject", subjectField.getText());
        controller.setEmailHeader(messageId, "To", toField.getText());
        controller.setEmailContent(messageId, messageContentTextArea.getText());
        controller.updateDate(messageId);
    }

    void send() {
        save();
        MessageController controller = MessageController.getInstance();
        controller.moveMessageToFolder(messageId, controller.getOutboxFolderId());
    }
}
