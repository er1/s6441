/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.SwingMain;

import Email.MessageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import ui.LabeledTextField;
import static ui.SwingMain.FolderMenu.logger;

/**
 * FolderMenu which is an extention of right click menu in tree bar
 *
 * @author chanman
 */
public class FolderMenu extends JPopupMenu {

    // FIXME: find a better solution for this
    static String pickedFolderId;
    MessageController controller;
    static final Logger logger = Logger.getLogger(FolderMenu.class.getName());

    static {
        logger.setParent(Logger.getLogger(FolderMenu.class.getPackage().getName()));
    }
    String selected;
    LabeledTextField nameFolder;
    JButton cancelName;
    JButton okName;

    FolderMenu(String selectedFolder) {
        selected = selectedFolder;
        controller = MessageController.getInstance();

        JMenuItem deleteFolder = new JMenuItem("Delete");
        JMenuItem newFolder = new JMenuItem("New Folder");
        JMenuItem moveFolder = new JMenuItem("Move to ...");
        
        if (pickedFolderId != null) {
            moveFolder.setText("Move to " + controller.getFolderName(pickedFolderId));
        }
        
        JMenuItem pickFolder = new JMenuItem("Pick this folder");

        deleteFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                deleteFolder();
            }
        });

        newFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                newFolder();
            }
        });

        moveFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                moveFolder();
            }
        });

        pickFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pickFolder();
            }
        });


        // if (selected) {
        this.add(deleteFolder);
        this.add(newFolder);
        this.add(moveFolder);
        this.add(pickFolder);
        //       } else {
        //       }
    }

    private void deleteFolder() {
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete the folder?",
                "Delete Folder",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            MessageController.getInstance().deletefolder(selected);
        }
    }

    private void moveFolder() {
        MessageController.getInstance().moveFolder(selected, pickedFolderId);
    }

    private void pickFolder() {
        pickedFolderId = selected;
    }

    public String getName(String title) {
        Object result;
        result = JOptionPane.showInputDialog(this, "Name:", title, JOptionPane.QUESTION_MESSAGE, null, null, "name");
        String newName = (String) result;
        return newName;
    }

    private void newFolder() {

        // get name
        String newName = getName("Create New Folder");

        logger.log(Level.INFO, selected);

        if (newName.matches("^[\\w ]+$")) {
            MessageController.getInstance().newFolder(selected, newName);
        } else {
            logger.log(Level.WARNING, "{0} is not alphanumeric", newName);
        }
    }
}
