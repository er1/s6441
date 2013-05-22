package ui.SwingMessage;

import Email.MessageController;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import ui.LabeledTextField;

/**
 * Compose Mail Window
 */
public class ComposeMail extends JFrame implements ActionListener {

    LabeledTextField subjectField;
    LabeledTextField toField;
    JTextArea messageContentTextArea;
    JButton cancelMailButton;
    JButton sendMailButton;
    JButton saveDraftButton;

    /**
     * Constructor for composing mail
     */
    public ComposeMail() {
        super("Email");

        // Contruct fields
        subjectField = new LabeledTextField("Subject");
        toField = new LabeledTextField("To");
        messageContentTextArea = new JTextArea();

        sendMailButton = new JButton("Send");
        sendMailButton.setToolTipText("Sends mail to the corresponding To information");
        sendMailButton.addActionListener(this);

        saveDraftButton = new JButton("Draft");
        saveDraftButton.setToolTipText("Save mail in Draft folder, Does not send mail");
        saveDraftButton.addActionListener(this);

        cancelMailButton = new JButton("Cancel");
        cancelMailButton.setToolTipText("Delete the mail and move it to recycle bin draft");
        cancelMailButton.addActionListener(this);

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

        this.setVisible(true);
    }

    /**
     * Refresh display of window
     */
    public void refresh() {
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
        if (e.getSource() == sendMailButton) {
            MessageController.getInstance().compose(
                    toField.getText(),
                    subjectField.getText(),
                    messageContentTextArea.getText());
            this.setVisible(false);
            this.dispose();
        }
    }
}
