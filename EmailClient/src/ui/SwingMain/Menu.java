package ui.SwingMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import ui.NewMail;

/**
 * Main menu
 */
public class Menu extends JMenuBar implements ActionListener {

    JMenuItem get_Mail = new JMenuItem("Get Mail");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem delete_menu = new JMenuItem("Delete Mail");
    JMenuItem inbox_menu = new JMenuItem("Inbox");
    JMenuItem refresh_menu = new JMenuItem("Refresh");
    JMenuItem sent_menu = new JMenuItem("Sent Mail");
    JMenuItem reply_menu = new JMenuItem("Reply Mail");

    /**
     * Constructor for creating menu bar
     */
    public Menu() {
        JMenu file = new JMenu("File", true);
        JMenu edit = new JMenu("Edit", true);
        get_Mail.addActionListener(this);
        exit.addActionListener(this);
        inbox_menu.addActionListener(this);
        file.add(get_Mail);
        file.add(exit);
        edit.add(refresh_menu);
        edit.add(inbox_menu);
        edit.add(sent_menu);
        edit.add(reply_menu);
        edit.add(delete_menu);
        this.add(file);
        this.add(edit);
    }

    /**
     *
     * @param e Action performed and detected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0); // does this make sense?
        }
    }
}
