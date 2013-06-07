package ui.SwingMessage;

import Email.MessageController;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import ui.LabeledTextField;

/**
 * Compose Mail Window
 */
public class MessageEditor extends JFrame {

    String messageId;
    LabeledTextField subjectField;
    LabeledTextField toField;
    LabeledTextField fromField;
    JTextArea messageContentTextArea;

    /**
     * Enum types for viewing windows
     */
    public enum Type {

        /**
         * Type for composing message
         */
        COMPOSE,
        /**
         * Type for viewing message
         */
        VIEW
    };
    Type type;

    /**
     * Constructor for composing mail
     *
     * @param messageId
     * @param type  
     */
    public MessageEditor(String messageId, Type type) {
        super("Email");
        this.messageId = messageId;
        this.type = type;
    }

    /**
     * Function to initialize message window
     */
    public void init() {
        // Contruct fields
        subjectField = new LabeledTextField("Subject").tooltip("The subject of this message");
        toField = new LabeledTextField("To").tooltip("This message is for");
        fromField = new LabeledTextField("From").tooltip("This message is from");
        messageContentTextArea = new JTextArea();

        JButton cancelMailButton;
        JButton sendMailButton;
        JButton saveDraftButton;

        JButton saveTemplateButton;

        sendMailButton = new JButton("Send");
        sendMailButton.setToolTipText("Sends mail to the corresponding To information");
        sendMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                send();
                setVisible(false);
                dispose();
            }
        });

        saveDraftButton = new JButton("Save as Draft");
        saveDraftButton.setToolTipText("Save this message in the Draft Folder");
        saveDraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                save();
                setVisible(false);
                dispose();
            }
        });

        cancelMailButton = new JButton("Close");
        cancelMailButton.setToolTipText("Close this message");
        cancelMailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
            }
        });

        saveTemplateButton = new JButton("Save as Template");
        saveTemplateButton.setToolTipText("Save this message as a template");
        saveTemplateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                saveTemplate();
                setVisible(false);
                dispose();
            }
        });


        if (type == Type.VIEW) {
            subjectField.setEditable(false);
            toField.setEditable(false);
            fromField.setEditable(false);
            messageContentTextArea.setEditable(false);
        }

        // Make header
        JPanel headerPanel = new JPanel();
        BoxLayout headerLayout = new BoxLayout(headerPanel, BoxLayout.Y_AXIS);
        headerPanel.setLayout(headerLayout);

        if (type == Type.VIEW) {
            headerPanel.add(fromField);
        } else {
            headerPanel.add(toField);
        }

        headerPanel.add(subjectField);

        // Make footer
        JPanel footerPanel = new JPanel();
        BoxLayout footerLayout = new BoxLayout(footerPanel, BoxLayout.X_AXIS);
        footerPanel.setLayout(footerLayout);

        footerPanel.add(sendMailButton);
        footerPanel.add(saveDraftButton);
        footerPanel.add(cancelMailButton);

        footerPanel.add(saveTemplateButton);
        
        this.setLayout(new BorderLayout());

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(messageContentTextArea);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setSize(650, 380);

        this.refresh();
    }

    /**
     * Refresh display of window
     */
    private void refresh() {
        MessageController controller = MessageController.getInstance();
        subjectField.setText(controller.getEmailHeader(messageId, "Subject"));
        toField.setText(controller.getEmailHeader(messageId, "To"));
        fromField.setText(controller.getEmailHeader(messageId, "From"));
        messageContentTextArea.setText(controller.getEmailContent(messageId));
    }

    void save() {
        MessageController controller = MessageController.getInstance();
        controller.setEmailHeader(messageId, "Subject", subjectField.getText());
        controller.setEmailHeader(messageId, "To", toField.getText());
        controller.setEmailContent(messageId, messageContentTextArea.getText());
        controller.setEmailHeader(messageId, "From", controller.getRootFolderId());
        controller.updateDate(messageId);
        controller.moveMessageToFolder(messageId, controller.getDraftsFolderId());
    }

    void saveTemplate() {
        MessageController controller = MessageController.getInstance();
        controller.setEmailHeader(messageId, "Subject", subjectField.getText());
        controller.setEmailHeader(messageId, "To", toField.getText());
        controller.setEmailContent(messageId, messageContentTextArea.getText());
        controller.setEmailHeader(messageId, "From", controller.getRootFolderId());
        controller.updateDate(messageId);
        controller.moveMessageToFolder(messageId, controller.getTemplateFolderId());
    }

    void send() {
        MessageController controller = MessageController.getInstance();
        controller.setEmailHeader(messageId, "Subject", subjectField.getText());
        controller.setEmailHeader(messageId, "To", toField.getText());
        controller.setEmailContent(messageId, messageContentTextArea.getText());
        controller.setEmailHeader(messageId, "From", controller.getRootFolderId());
        controller.updateDate(messageId);
        controller.moveMessageToFolder(messageId, controller.getOutboxFolderId());
    }
}
