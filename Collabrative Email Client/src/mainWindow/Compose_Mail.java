package mainWindow;

import javax.swing.*;

import java.awt.TextArea;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class Compose_Mail extends JFrame implements ActionListener {

    public JFrame writeMail() {
        JFrame cmwindow = new JFrame("New Mail");

        JPanel composeMailPanel = new JPanel(new MigLayout());
        JLabel toLabel = new JLabel("To", JLabel.LEFT);

        JTextField toField = new JTextField("Mail Address", 40);
        JLabel subjectLabel = new JLabel("Subject");

        JTextField subjectField = new JTextField("Subject", 40);
        TextArea messageContentTextArea = new TextArea("Enter the email");

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
        cmwindow.add(composeMailPanel);
        cmwindow.setSize(650, 380);
        cmwindow.setVisible(true);
        return cmwindow;
    }

    public void actionPerformed(ActionEvent e) {
    }
}
