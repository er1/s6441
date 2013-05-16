package ui.SwingMain;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

/**
 *
 * @author Bargavi
 */
public class MouseRightClickListener extends MouseAdapter implements ActionListener{
	
	JTree tree;
	
	JMenuItem deleteFolder = new JMenuItem ( "Delete" );
	JMenuItem newFolder = new JMenuItem ( "New Folder" );
	JMenuItem moveFolder = new JMenuItem ( "Move" );
	
	ui.CreateNewFolder newFolderObj = new ui.CreateNewFolder();
	
	public MouseRightClickListener(JTree tree)
	{
		this.tree = tree;
	}
	
	public void mousePressed ( MouseEvent e )
    {
        if ( SwingUtilities.isRightMouseButton ( e ) )
        {
            TreePath path = this.tree.getPathForLocation ( e.getX (), e.getY () );
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newFolder)
		{
			
			newFolderObj.newFolder();
			
			System.out.println("Folder name " + newFolderObj.getFolderName());
			
		}
		
	}

}
