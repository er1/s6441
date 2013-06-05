package ui.SwingMain;

import Email.MessageController;
import Email.MessageController.UpdateType;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

/**
 * FolderList class displays tree hierarchy dynamically
 *
 */
public class FolderList extends JTree {

    MessageController controller;
    MessageList list;
    FolderTreeModel model;

    /**
     * constructor
     *
     * @param controller
     * @param messagelist
     */
    public FolderList(MessageList messagelist) {
        super();
        controller = MessageController.getInstance();
        list = messagelist;
        init();
        controller.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object otype) {
                UpdateType type = (UpdateType) otype;
                if (type == UpdateType.FOLDERS_AND_MESSAGES) {
                    refresh();
                }
            }
        });
    }

    private void init() {
        this.setLayout(new BorderLayout());

        Dimension size = new Dimension(200, 200); // can be arbitrarily changed

        model = new FolderTreeModel();

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

    }

    void makeMenu(Point mouseposition) {
        TreePath path = this.getPathForLocation(mouseposition.x, mouseposition.y);
        FolderTreeModel.FolderNode temp = (FolderTreeModel.FolderNode) path.getLastPathComponent();
        String last = temp.getId();

        JPopupMenu menu = new FolderMenu(last);
        menu.show(this, mouseposition.x, mouseposition.y);
    }

    /**
     * Function to refresh the Tree Hierarchy
     */
    public void refresh() {
        TreePath p = this.getSelectionPath();
        model = new FolderTreeModel();
        this.setModel(model);
    }

    private void changefolder() {
        try {
            TreePath path = this.getSelectionPath();
            String id = ((FolderTreeModel.FolderNode) path.getLastPathComponent()).getId();
            list.displayFolder(id);
        } catch (NullPointerException e) {
            // FIXME
        }
    }
}
