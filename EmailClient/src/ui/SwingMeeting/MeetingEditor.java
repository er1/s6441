package ui.SwingMeeting;

import Email.MessageController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import ui.LabeledTextField;

/**
 * Edit Meeting Window
 */
public class MeetingEditor extends JFrame {

    String messageId;
    LabeledTextField dateField;
    LabeledTextField startTimeField;
    LabeledTextField endTimeField;
    LabeledTextField subjectField;
    LabeledTextField toField;
    JTextArea meetingContentTextArea;

    public MeetingEditor(String messageId) {
        super("Meeting");
        this.messageId = messageId;
    }

    public void init() {
        subjectField = new LabeledTextField("Subject");
        dateField = new LabeledTextField("Date");
        startTimeField = new LabeledTextField("Start Time");
        endTimeField = new LabeledTextField("End Time");
        toField = new LabeledTextField("To");
        meetingContentTextArea = new JTextArea();

        JButton sendMeeting;

        sendMeeting = new JButton("Send");
        sendMeeting.setToolTipText("Send meeting to the recepients");
        sendMeeting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                send();
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
        headerPanel.add(dateField);
        headerPanel.add(startTimeField);
        headerPanel.add(endTimeField);

        // Make footer
        JPanel footerPanel = new JPanel();
        BoxLayout footerLayout = new BoxLayout(footerPanel, BoxLayout.X_AXIS);
        footerPanel.setLayout(footerLayout);

        footerPanel.add(sendMeeting);
        this.setLayout(new BorderLayout());

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(meetingContentTextArea);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setSize(650, 380);
    }

    private void send() {
        MessageController controller = MessageController.getInstance();
        controller.setEmailHeader(messageId, "Subject", subjectField.getText());
        controller.setEmailHeader(messageId, "To", toField.getText());
        controller.setEmailHeader(messageId, "MeetingDate", dateField.getText());
        controller.setEmailHeader(messageId, "MeetingStartTime", startTimeField.getText());
        controller.setEmailHeader(messageId, "MeetingEndTime", endTimeField.getText());
        controller.setEmailContent(messageId, meetingContentTextArea.getText());
        controller.updateDate(messageId);
        controller.moveMessageToFolder(messageId, controller.getMeetingsFolderId());
    }
}

