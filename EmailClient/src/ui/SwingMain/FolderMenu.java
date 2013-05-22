/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.SwingMain;

import Email.MessageController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 * FolderMenu which is an extention of right click menu in tree bar 
 * @author chanman
 */
public class FolderMenu extends JPopupMenu {

    static final Logger logger = Logger.getLogger(FolderMenu.class.getName());
    static {
        logger.setParent(Logger.getLogger(FolderMenu.class.getPackage().getName()));
    }
    String selected;

    FolderMenu(String selectedFolder) {
        selected = selectedFolder;
        //String path = pathFromString(selectedFolder);
        JMenuItem deleteFolder = new JMenuItem("Delete");
        JMenuItem newFolder = new JMenuItem("New Folder");
        JMenuItem moveFolder = new JMenuItem("Move");

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

        this.add(deleteFolder);
        this.add(newFolder);
        this.add(moveFolder);
    }

    private void deleteFolder() {
        // selected -> folderID
        // controller.delete(folderIO)
        // update tree

        JOptionPane.showMessageDialog(null, "Delete " + selected, null, JOptionPane.ERROR_MESSAGE);
    }

    private void moveFolder() {
        // get destination (a foldeid
        // selected -> folderID
        // controller.move(...)
        // update tree

        JOptionPane.showMessageDialog(null, "Move", null, JOptionPane.ERROR_MESSAGE);
    }

    private void newFolder() {
        // get name
        logger.log(Level.INFO, selected);
        // selected -> folderID
        MessageController.getInstance().newfolder(selected+File.separator+"test");
        // update tree

        JOptionPane.showMessageDialog(null, "New", null, JOptionPane.ERROR_MESSAGE);
    }
}
