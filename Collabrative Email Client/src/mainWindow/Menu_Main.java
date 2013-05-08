package mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class Menu_Main extends JFrame implements ActionListener {

    JMenuItem new_ID = new JMenuItem("New Mail ID");
    JMenuItem get_Mail = new JMenuItem("Get Mail");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem delete_menu = new JMenuItem("Delete Mail");
    JMenuItem inbox_menu = new JMenuItem("Inbox");
    JMenuItem refresh_menu = new JMenuItem("Refresh");
    JMenuItem sent_menu = new JMenuItem("Sent Mail");
    JMenuItem reply_menu = new JMenuItem("Reply Mail");
    mainWindow.NewMail obj_1 = new mainWindow.NewMail();
    mainWindow.Tab_window obj_4 = new mainWindow.Tab_window();

    public JMenuBar menumail() {
        JMenuBar mainmenu = new JMenuBar();
        JMenu file = new JMenu("File", true);
        JMenu edit = new JMenu("Edit", true);
        new_ID.addActionListener(this);
        get_Mail.addActionListener(this);
        exit.addActionListener(this);
        inbox_menu.addActionListener(this);
        file.add(new_ID);
        file.add(get_Mail);
        file.add(exit);
        edit.add(refresh_menu);
        edit.add(inbox_menu);
        edit.add(sent_menu);
        edit.add(reply_menu);
        edit.add(delete_menu);
        mainmenu.add(file);
        mainmenu.add(edit);
        //menu_Panel.add(mainmenu);
        return mainmenu;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == new_ID) {
            JFrame nmwindow = new JFrame();
            nmwindow = obj_1.newmail();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == inbox_menu) {
            JTabbedPane inbox_new = obj_4.inbox;
            inbox_new.setSelectedComponent(inbox_new);
            //.setSelectedComponent(inbox_new);
        }
    }
}
