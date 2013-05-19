package ui.SwingMain;

import Email.MessageController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FolderList extends JPanel {

    MessageController store;
    MessageList list;

    public FolderList(MessageController controller, MessageList messagelist) {
        super();
        store = controller;
        list = messagelist;
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout());

        Dimension size = new Dimension(200, 200); // can be arbitrarily changed
        setSize(size);
        setMinimumSize(size);

    }

    public void refresh() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mailbox");
        populatefolder(root, store.getRootFolderId());
        DefaultTreeModel messageTreeModel = new DefaultTreeModel(root);
        JTree messageTree = new JTree(messageTreeModel);
        this.removeAll();
        this.add(messageTree);
        messageTree.addMouseListener(new MouseRightClickListener(messageTree));
        //JScrollPane scrollPane = new JScrollPane(messageTree);
        //this.add(scrollPane);

        messageTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                changefolder();
            }
        });
    }

    /**
     *
     * @param node
     * @param folder
     */
    private void populatefolder(DefaultMutableTreeNode node, String folderid) {



        for (String subfolder : store.getFolderList(folderid)) {
            String name = store.getFolderName(subfolder);
            DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(name); // get name from subfolder id
            node.add(subnode);
            populatefolder(subnode, subfolder);
        }

    }
    
    private void changefolder() {
        // get selected folder
        // list.change to selected folder
        String id = "inbox";
        list.displayFolder(id);
    }
    
}
