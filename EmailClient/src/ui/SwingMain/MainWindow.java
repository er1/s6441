package ui.SwingMain;

import javax.swing.*;

import java.awt.Container;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame implements ActionListener {

    ToolRibbon toolRibbon = new ToolRibbon();
    MenuMain menuBar = new MenuMain();

    public MainWindow() {
        super("Email Client");
        Container contentPane = this.getContentPane();
        
        contentPane.setLayout(new MigLayout("fill"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar tool_bar = toolRibbon.toolbar();
        JMenuBar mainmenu = menuBar.menumail();
        TreeExplorer inboxTree = new TreeExplorer();
        EmailListDisplayPane emailListDisplayPane = new EmailListDisplayPane();
        EmailContentPane emailContentPane = new EmailContentPane();

        this.setJMenuBar(mainmenu);

        contentPane.add(tool_bar, "north");
        contentPane.add(inboxTree, "west");
        //TODO Figure out why this isn't filling the full screen width
        contentPane.add(emailListDisplayPane, "grow,wrap");
        contentPane.add(emailContentPane, "grow");

        this.setSize(1000, 600);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
