package ui.SwingMain;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Enumeration;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import Email.TemporaryFolder;

/**
 * Handles when right mouse is clicked on selecting tree folder
 * @author Bargavi
 */
public class MouseRightClickListener extends MouseAdapter implements ActionListener{

	JTree tree;

	JMenuItem deleteFolder = new JMenuItem ( "Delete" );
	JMenuItem newFolder = new JMenuItem ( "New Folder" );
	JMenuItem moveFolder = new JMenuItem ( "Move" );
	
	String folderName; 
	TreePath path;

    /**
     *
     * @param tree The tree element on which the mouse click was detected
     */
    public MouseRightClickListener(JTree tree) {
		this.tree = tree;
	}

    /**
     *
     * @param e Mouse Event Detected
     */
    public void mousePressed(MouseEvent e) {
        if ( SwingUtilities.isRightMouseButton ( e ) )
        {
            path = this.tree.getPathForLocation ( e.getX (), e.getY () );
            System.out.println("Selected TreePath : " + path.toString());
            Rectangle pathBounds = this.tree.getUI ().getPathBounds ( this.tree, path );
            if ( pathBounds != null && pathBounds.contains ( e.getX (), e.getY () ) )
            {
                JPopupMenu menu = new JPopupMenu ();

                menu.add ( deleteFolder );
                menu.add ( newFolder );
                menu.add ( moveFolder );

                deleteFolder.addActionListener(this);
                newFolder.addActionListener(this);
                moveFolder.addActionListener(this);

                menu.show ( this.tree, pathBounds.x, pathBounds.y + pathBounds.height );
            }
        }
    }

    /**
     *
     * @param e Action event Detected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
		if (e.getSource() == newFolder)
		{
			folderName = (String)JOptionPane.showInputDialog(
                    null,
                    "Name:",
                    "Create New Folder",
                    JOptionPane.PLAIN_MESSAGE
                    );
			
			if ((folderName != null) && (folderName.length() > 0)) {
				
				System.out.println("FOLDER NAME :"+ folderName);
				//Search for duplicates
				if(!searchFolder(folderName, path))
				{
					//Insert new node into JTree given the folder and path where to insert the folder
					addFolderIntoTree(new TemporaryFolder(folderName), path);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Folder name already exists",null,
                            JOptionPane.ERROR_MESSAGE);
				}
				return;
            }
		}
		
		if (e.getSource() == deleteFolder)
		{
			int choice = JOptionPane.showConfirmDialog(
					null,
					"Are you sure you want to delete the folder and move all of its contents into Trash?",
					"Delete Folder",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE
					);
			if(choice == JOptionPane.YES_OPTION)
			{
				deleteFolderFromTree();
			}
			return;
		}
		
		if (e.getSource() == moveFolder)
		{
			return;
		}
		

	}
    
    /**
    *
    * @param folderName -> search for this foldername
    * @param path -> in the given path
    */

    private boolean searchFolder(String folderName, TreePath path) {
    	DefaultMutableTreeNode parentFolder = null;
    	System.out.println("Search : Current path selected : "+path.toString());
		if(path != null)
		{
			parentFolder = (DefaultMutableTreeNode)
							(path.getLastPathComponent());
		}
		Enumeration<DefaultMutableTreeNode> e = parentFolder.depthFirstEnumeration();
		while (e.hasMoreElements()) {
	        DefaultMutableTreeNode node = e.nextElement();
	        if (node.toString().equalsIgnoreCase(folderName)) {
	        	System.out.println("node : "+node.toString());
	        	return true;
	        }
	    }
		return false;
	}

	/**
    *
    * @param folder -> new folder to be added to Tree
    * @param path -> where exactly folder gonna be
    */

	private void addFolderIntoTree(TemporaryFolder folder, TreePath path) {
		DefaultMutableTreeNode parentFolder = null;
		System.out.println("Current path selected : "+path.toString());
		if(path != null)
		{
			parentFolder = (DefaultMutableTreeNode)
							(path.getLastPathComponent());
		}
		DefaultMutableTreeNode childFolder = new DefaultMutableTreeNode(folder.getName());
		DefaultTreeModel treeModel = (DefaultTreeModel) this.tree.getModel();
		treeModel.insertNodeInto(childFolder, parentFolder, parentFolder.getChildCount());
		this.tree.scrollPathToVisible(new TreePath(childFolder.getPath()));
	}
	
	/**
    *
    * 
    */
	private void deleteFolderFromTree() {
		
	    DefaultMutableTreeNode node;
	    DefaultTreeModel treeModel = (DefaultTreeModel) (this.tree.getModel());
	    
	    node = (DefaultMutableTreeNode) (path.getLastPathComponent());
	    treeModel.removeNodeFromParent(node);
	}
	
}
