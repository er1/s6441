package ui.SwingMain;

import Email.DummyStore;
import Email.Folder;
import Email.Message;
import Email.MessageStore;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author anasalkhatib
 */
public class TreeExplorer extends JPanel {
    //TODO: See http://www.java2s.com/Code/Java/Swing-JFC/FileTreewithPopupMenu.htm

    public TreeExplorer() {
        super();
        init();
    }

    private void init() {
        this.setLayout(new MigLayout("wrap"));

        refresh(new DummyStore()); // FIXME

        setSize(20, 20);
        setBorder(BorderFactory.createTitledBorder("Folders"));

    }

    /**
     *
     * @param ms
     */
    public void refresh(MessageStore ms) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mailbox");
        populatefolder(root, ms);
        DefaultTreeModel messageTreeModel = new DefaultTreeModel(root);
        JTree messageTree = new JTree(messageTreeModel);
        this.removeAll();
        this.add(messageTree);
        messageTree.addMouseListener(new MouseRightClickListener(messageTree));
    }

    /**
     *
     * @param node
     * @param folder
     */
    private void populatefolder(DefaultMutableTreeNode node, Folder folder) {
        for (Folder f : folder.getSubfolders()) {
            DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(f.getName());
            node.add(subnode);
            populatefolder(subnode, f);
        }
        
        // Remove this maybe
        for (Message m : folder.getMessages()) {
            DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(m.getHeader("From"));
            node.add(subnode);
        }
    }
}
