package ui.SwingMain;

import Email.DummyStore;
import Email.Folder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeExplorer extends JPanel {
    //TODO: See http://www.java2s.com/Code/Java/Swing-JFC/FileTreewithPopupMenu.htm

    /**
     *
     */
    public TreeExplorer() {
        super();
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout());

        setSize(200, 200);
        setMinimumSize(new Dimension(200, 200));
        setBorder(BorderFactory.createTitledBorder("Folders"));
    }

    /**
     *
     * @param ms
     */
    public void refresh() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mailbox");
        populatefolder(root, new DummyStore());
        DefaultTreeModel messageTreeModel = new DefaultTreeModel(root);
        JTree messageTree = new JTree(messageTreeModel);
        this.removeAll();
        this.add(messageTree);
        messageTree.addMouseListener(new MouseRightClickListener(messageTree));
        //JScrollPane scrollPane = new JScrollPane(messageTree);
        //this.add(scrollPane);
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

    }
}
