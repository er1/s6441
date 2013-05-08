package mainWindow;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {

    mainWindow.NewMail obj_1 = new mainWindow.NewMail();
    mainWindow.Tool_bar obj_2 = new mainWindow.Tool_bar();
    mainWindow.Menu_Main obj_3 = new mainWindow.Menu_Main();
    Tab_window obj_4 = new Tab_window();

    public Menu() {
        JFrame window = new JFrame("Email Client");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar tool_bar = new javax.swing.JToolBar();
        JTabbedPane tab_menu = new javax.swing.JTabbedPane();
        JMenuBar mainmenu = new JMenuBar();
        tool_bar = obj_2.toolbar();
        mainmenu = obj_3.menumail();
        tab_menu = obj_4.tabmenu();
        window.getContentPane().add(tool_bar, BorderLayout.NORTH);
        window.setJMenuBar(mainmenu);
        window.getContentPane().add(tab_menu, BorderLayout.CENTER);
        window.setSize(1000, 600);
        window.setVisible(true);
    }

    public static void main(String args[]) {
        new Menu();
    }

    public void actionPerformed(ActionEvent e) {
    }
}
