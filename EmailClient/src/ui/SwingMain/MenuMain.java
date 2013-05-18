package ui.SwingMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Main menu
 */
public class MenuMain extends JFrame implements ActionListener {

    JMenuItem new_ID = new JMenuItem("New Mail ID");
    JMenuItem get_Mail = new JMenuItem("Get Mail");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem delete_menu = new JMenuItem("Delete Mail");
    JMenuItem inbox_menu = new JMenuItem("Inbox");
    JMenuItem refresh_menu = new JMenuItem("Refresh");
    JMenuItem sent_menu = new JMenuItem("Sent Mail");
    JMenuItem reply_menu = new JMenuItem("Reply Mail");
    ui.NewMail obj_1 = new ui.NewMail();

    /**
     *
     * @return A Swing MenuBar
     */
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

    /**
     *
     * @param e Action performed and detected
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == new_ID) {
            JFrame nmwindow = new JFrame();
            nmwindow = obj_1.newmail();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}
