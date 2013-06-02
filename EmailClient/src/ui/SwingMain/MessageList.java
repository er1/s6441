package ui.SwingMain;

import Email.MessageController;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ui.SwingMessage.MessageEditor;

/**
 * Email list Display
 */
public class MessageList extends JTable {

    MessageController controller;
    Content content;
    ToolRibbon toolribbon;
    FolderTableModel model;

    /**
     * Constructor
     *
     * @param controller
     * @param content
     */
    public MessageList(MessageController controller, Content content, ToolRibbon toolribbon) {
        this.controller = controller;
        this.content = content;
        this.toolribbon = toolribbon;
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
                if (SwingUtilities.isRightMouseButton(me)) {
                    makeMenu(me.getPoint());
                }

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

    void makeMenu(Point mouseposition) {

        // get row
        int row = this.rowAtPoint(mouseposition);
        // get id of row
        String id = (String) this.model.getValueAt(row, -1);
        JPopupMenu menu = new MessageMenu(id);
        menu.show(this, mouseposition.x, mouseposition.y);
    }

    /**
     * Function to display folder given the folder Id
     *
     * @param folderId
     */
    public void displayFolder(String folderId) {

        model = new FolderTableModel(controller, folderId);
        this.setModel(model);

        toolribbon.setSelectedFolder(folderId);
    }

    private void changemessage() {
        int selected = this.getSelectedRow();
        String messageid = model.getMessageId(selected);
        content.showMessage(messageid);

        toolribbon.setSelectedMessage(messageid);

    }

    private void openmessage() {
        int selected = this.getSelectedRow();
        String messageid = model.getMessageId(selected);

        MessageEditor editor = new MessageEditor(messageid, MessageEditor.Type.COMPOSE);
        editor.init();
        editor.setVisible(true);
    }
}
