package mainWindow;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
public class Menu extends JFrame implements ActionListener {
	
     mainWindow.NewMail obj_1 = new mainWindow.NewMail();	
     mainWindow.Tool_bar obj_2 = new mainWindow.Tool_bar();  
     mainWindow.Menu_Main obj_3 = new mainWindow.Menu_Main();
     Tab_window obj_4 = new Tab_window();
         
    public Menu()
	{
			 JFrame window = new JFrame("Email Client");
			// JFrame tab = new JFrame();
			 JToolBar tool_bar = new javax.swing.JToolBar();
			 JTabbedPane tab_menu = new javax.swing.JTabbedPane();   			  
			 JMenuBar mainmenu = new JMenuBar();
			//JSplitPane divide_window = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			 tool_bar = obj_2.toolbar() ;
			 mainmenu = obj_3.menumail();
			 tab_menu = obj_4.tabmenu();
		     window.getContentPane().add(tool_bar,BorderLayout.NORTH);
	       	 window.setJMenuBar(mainmenu);
	       	 window.getContentPane().add(tab_menu, BorderLayout.CENTER);
	       	//window.add(divide_window);
			 window.setSize(500, 500);
			 window.setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Menu();
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
}
