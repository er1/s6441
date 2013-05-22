/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.SwingMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 * FolderMenu which is an extention of right click menu in tree bar 
 * @author chanman
 */
public class FolderMenu extends JPopupMenu {

    String selected;

    FolderMenu(String selectedFolder) {
        selected = selectedFolder;

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

        JOptionPane.showMessageDialog(null, "Delete " + selected.toString(), null, JOptionPane.ERROR_MESSAGE);
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
        // selected -> folderID
        // controller.newfolder(folderIO,name)
        // update tree

        JOptionPane.showMessageDialog(null, "New", null, JOptionPane.ERROR_MESSAGE);
    }
}
