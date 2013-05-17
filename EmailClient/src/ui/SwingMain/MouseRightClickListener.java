package ui.SwingMain;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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
 *
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

                newFolder.addActionListener(this);

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
				
				//Insert new node into JTree given the folder and path where to insert the folder
				addFolder(new TemporaryFolder(folderName), path);
				return;
            }
			
		}
		

	}
    
    /**
    *
    * @param folder -> new folder to be added to Tree
    * @param path -> where exactly folder gonna be
    */

	private void addFolder(TemporaryFolder folder, TreePath path) {
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

}
