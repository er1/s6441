/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.BorderLayout;
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
public class TreeExplorer extends JPanel{
    //TODO: See http://www.java2s.com/Code/Java/Swing-JFC/FileTreewithPopupMenu.htm

    JTree inboxTree;
    JTree outboxTree;
    DefaultTreeModel inboxTreeModel;
    DefaultTreeModel outboxTreeModel;

    public TreeExplorer() {
        super();
        init();
    }

    private void init() {
        this.setLayout(new MigLayout("wrap"));
        //Demo Inbox
        DefaultMutableTreeNode inbox = new DefaultMutableTreeNode("Inbox");
        DefaultMutableTreeNode subFolder1 = new DefaultMutableTreeNode("SubFolder1");
        DefaultMutableTreeNode subFolder2 = new DefaultMutableTreeNode("SubFolder2");
        DefaultMutableTreeNode subFolder3 = new DefaultMutableTreeNode("SubFolder3");
         
        inboxTreeModel = new DefaultTreeModel(inbox);
        inboxTree = new JTree(inboxTreeModel);
        inbox.add(subFolder1);
        subFolder1.add(subFolder2);
        inbox.add(subFolder3);
        this.add(inboxTree);

        //Demo Outbox
        DefaultMutableTreeNode outbox = new DefaultMutableTreeNode("Outbox");
        outboxTreeModel =  new DefaultTreeModel(outbox);
        outboxTree = new JTree(outboxTreeModel);
        this.add(outboxTree);

        setSize(20, 20);
        setBorder(BorderFactory.createTitledBorder("Folders"));

    }
    
    
}
