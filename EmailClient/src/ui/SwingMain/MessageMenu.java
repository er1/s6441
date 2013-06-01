package ui.SwingMain;

import Email.MessageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import ui.SwingMessage.MessageEditor;

public class MessageMenu extends JPopupMenu {

    String selected;
    MessageController controller;

    public MessageMenu(String selectedMessage) {
        selected = selectedMessage;
        controller = MessageController.getInstance();

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem moveItem = new JMenuItem("Move to ...");

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                open();
            }
        });
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                delete();
            }
        });
        moveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                move();
            }
        });

        this.add(openItem);
        this.add(deleteItem);
        this.add(moveItem);
    }

    void open() {
        MessageEditor editor = new MessageEditor(selected);
        editor.init();
        editor.setVisible(true);
    }

    void delete() {
        controller.moveMessageToFolder(selected, controller.getTrashFolderId());
    }

    void move() {
//        controller.moveMessageToFolder(selected, selected);
    }
}
