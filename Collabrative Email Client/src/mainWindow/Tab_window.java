package mainWindow;

import javax.swing.*;
import java.text.*;
import java.awt.*;

public class Tab_window {

    JTabbedPane sentmail = new javax.swing.JTabbedPane();
    JTabbedPane tab_menu = new javax.swing.JTabbedPane();
    JTabbedPane inbox = new javax.swing.JTabbedPane();
    JTabbedPane draft = new javax.swing.JTabbedPane();

    public JTabbedPane tabmenu() {
        tab_menu.addTab("Sent Mail", sentmail);
        tab_menu.addTab("Draft", draft);
        tab_menu.addTab("Inbox", inbox);
        return tab_menu;
    }
}
