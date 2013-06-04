package ui.SwingMain;

import Email.MessageController;
import java.util.Arrays;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

class FolderTreeModel implements TreeModel {

    MessageController controller;

    public class FolderNode {

        String id;
        String name;

        public FolderNode(String id) {
            this.id = id;
            this.name = MessageController.getInstance().getFolderName(id);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public FolderTreeModel() {
        controller = MessageController.getInstance();
    }

    @Override
    public Object getRoot() {
        return new FolderTreeModel.FolderNode(controller.getRootFolderId());
    }

    @Override
    public Object getChild(Object folderid, int index) {
        String id = ((FolderTreeModel.FolderNode) folderid).getId();
        String[] folders = controller.getFolderList(id);
        return new FolderTreeModel.FolderNode(folders[index]);
    }

    @Override
    public int getChildCount(Object folderid) {
        String id = ((FolderTreeModel.FolderNode) folderid).getId();
        String[] folders = controller.getFolderList(id);
        return folders.length;
    }

    @Override
    public boolean isLeaf(Object o) {
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        String parentid = ((FolderTreeModel.FolderNode) parent).getId();
        String childid = ((FolderTreeModel.FolderNode) child).getId();
        String[] folders = controller.getFolderList(parentid);
        return Arrays.asList(folders).indexOf(childid);
    }

    @Override
    public void addTreeModelListener(TreeModelListener tl) {
        // FIXME
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tl) {
        // FIXME
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}