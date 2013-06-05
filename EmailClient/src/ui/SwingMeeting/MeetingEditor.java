package ui.SwingMeeting;

import Email.MessageController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import ui.LabeledTextField;

/**
 * Edit Meeting Window
 */
public class MeetingEditor extends JFrame {

    String messageId;
    JLabel dateLabel;
    JFormattedTextField dateField;
    JLabel startTimeLabel;
    JFormattedTextField startTimeField;
    JLabel endTimeLabel;
    JFormattedTextField endTimeField;
    LabeledTextField subjectField;
    LabeledTextField toField;
    JTextArea meetingContentTextArea;

    public MeetingEditor(String messageId) {
        super("Meeting");
        this.messageId = messageId;
    }

    public void init() {
        subjectField = new LabeledTextField("Subject");

        //FIXME Use JSpinner here and for time
        dateLabel = new JLabel("Date (dd/mm/yyyy):");
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        dateField = new JFormattedTextField(dateformat);
        Date now = new Date();
        dateField.setValue(now);
        dateLabel.setLabelFor(dateField);

        startTimeLabel = new JLabel("Start Time (HH:MM):");
        startTimeField = new JFormattedTextField(new SimpleDateFormat("HH:mm"));
        startTimeField.setValue(now);
        endTimeLabel = new JLabel("End Time (HH:MM):");
        endTimeField = new JFormattedTextField(new SimpleDateFormat("HH:mm"));
        endTimeField.setValue(now);

        toField = new LabeledTextField("To");
        meetingContentTextArea = new JTextArea();

        JButton sendMeeting;
        JButton chooseDate;
        //UtilDateModel model = new UtilDateModel();
        //JDatePickerImpl datePanel = new JDatePickerImpl(new JDatePanelImpl(model));

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

        JPanel datePanel = new JPanel();
        BoxLayout dateLayout = new BoxLayout(datePanel, BoxLayout.X_AXIS);
        datePanel.add(dateLabel);
        datePanel.add(dateField);
        datePanel.setLayout(dateLayout);

        JPanel startTimePanel = new JPanel();
        BoxLayout startTimeLayout = new BoxLayout(startTimePanel, BoxLayout.X_AXIS);
        startTimePanel.add(startTimeLabel);
        startTimePanel.add(startTimeField);
        startTimePanel.setLayout(startTimeLayout);

        JPanel endTimePanel = new JPanel();
        BoxLayout endTimeLayout = new BoxLayout(endTimePanel, BoxLayout.X_AXIS);
        endTimePanel.add(endTimeLabel);
        endTimePanel.add(endTimeField);
        endTimePanel.setLayout(endTimeLayout);

        headerPanel.add(toField);
        headerPanel.add(subjectField);
        headerPanel.add(datePanel);
        headerPanel.add(startTimePanel);
        headerPanel.add(endTimePanel);

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
        //TODO Verify input is valid
        //Date is in future, Start Time < End Time
        MessageController controller = MessageController.getInstance();
        controller.setEmailHeader(messageId, "Subject", subjectField.getText());
        controller.setEmailHeader(messageId, "To", toField.getText());
        controller.setEmailHeader(messageId, "MeetingDate", dateField.getText());
        controller.setEmailHeader(messageId, "MeetingStartTime", startTimeField.getText());
        controller.setEmailHeader(messageId, "MeetingEndTime", endTimeField.getText());
        controller.setEmailHeader(messageId, "From", controller.getRootFolderId());

        //UID to identify meeting across different clients
        UUID meetingId = UUID.randomUUID();
        controller.setEmailHeader(messageId,"X-MeetingId",meetingId.toString());

        controller.setEmailContent(messageId, meetingContentTextArea.getText());
        controller.updateDate(messageId);
        controller.sendMeeting(messageId);
    }
}

