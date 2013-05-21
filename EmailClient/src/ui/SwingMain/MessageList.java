package ui.SwingMain;

import Email.MessageController;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Email list Display
 */
public class MessageList extends JTable {

    MessageController controller;
    Content content;
    FolderTableModel model;

    /**
     * Constructor
     * @param controller
     * @param content  
     */
    public MessageList(MessageController controller, Content content) {
        this.controller = controller;
        this.content = content;
        ListSelectionModel lsm = this.getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                changemessage();
            }
        });
    }

    /**
     * Function to display folder given the folder Id
     * @param folderId
     */
    public void displayFolder(String folderId) {

        model = new FolderTableModel(controller, folderId);
        this.setModel(model);
    }

    private void changemessage() {
        int selected = this.getSelectedRow();
        String messageid = model.getMessageId(selected);
        content.showMessage(messageid);
    }
}
