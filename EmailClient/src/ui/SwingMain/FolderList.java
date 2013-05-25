package ui.SwingMain;

import Email.MessageController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * FolderList class displays tree hierarchy dynamically
 *
 */
public class FolderList extends JTree {

    MessageController store;
    MessageList list;
    FolderTreeModel model;

    private class FolderNode {

        String id;
        String name;

        public FolderNode(MessageController controller, String id) {
            this.id = id;
            this.name = controller.getFolderName(id);
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

    private class FolderTreeModel implements TreeModel {

        MessageController controller;

        public FolderTreeModel(MessageController store) {
            controller = store;
        }

        @Override
        public Object getRoot() {
            return new FolderNode(controller, controller.getRootFolderId());
        }

        @Override
        public Object getChild(Object folderid, int index) {
            String id = ((FolderNode) folderid).getId();
            String[] folders = controller.getFolderList(id);
            return new FolderNode(controller, folders[index]);
        }

        @Override
        public int getChildCount(Object folderid) {
            String id = ((FolderNode) folderid).getId();
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
            String parentid = ((FolderNode) parent).getId();
            String childid = ((FolderNode) child).getId();
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

    /**
     * constructor
     *
     * @param controller
     * @param messagelist
     */
    public FolderList(MessageController controller, MessageList messagelist) {
        super();
        store = controller;
        list = messagelist;
        init();
        store.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object o1) {
                refresh();
            }
        });
    }

    private void init() {
        this.setLayout(new BorderLayout());

        Dimension size = new Dimension(200, 200); // can be arbitrarily changed

        model = new FolderTreeModel(store);

        this.setMaximumSize(size);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    makeMenu(e.getPoint());
                }
            }
        });

        this.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                changefolder();
            }
        });

        this.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent tee) {
                sizechanged();
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent tee) {
                sizechanged();
            }
        });
    }

    void sizechanged() {
        // TODO: have the layout resize
        this.doLayout();
    }

    void makeMenu(Point mouseposition) {
        TreePath path = this.getPathForLocation(mouseposition.x, mouseposition.y);
        FolderNode temp = (FolderNode) path.getLastPathComponent();
        String last = temp.getId();

        JPopupMenu menu = new FolderMenu(last);
        menu.show(this, mouseposition.x, mouseposition.y);
    }

    /**
     * Function to refresh the Tree Hierarchy
     */
    public void refresh() {
        model = new FolderTreeModel(store);
        this.setModel(model);
    }

    private void changefolder() {
        try {
            TreePath path = this.getSelectionPath();
            String id = ((FolderNode) path.getLastPathComponent()).getId();
            list.displayFolder(id);
        } catch (NullPointerException e) {
            // FIXME
        }
    }
}
