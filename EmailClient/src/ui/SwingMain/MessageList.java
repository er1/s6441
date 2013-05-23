package ui.SwingMain;

import Email.MessageController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ui.SwingMessage.ComposeMail;

/**
 * Email list Display
 */
public class MessageList extends JTable {

    MessageController controller;
    Content content;
    FolderTableModel model;

    /**
     * Constructor
     *
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
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() >= 2) {
                    openmessage();
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    /**
     * Function to display folder given the folder Id
     *
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

    private void openmessage() {
        int selected = this.getSelectedRow();
        String messageid = model.getMessageId(selected);
        (new ComposeMail(messageid)).setVisible(true);
        
    }
}
