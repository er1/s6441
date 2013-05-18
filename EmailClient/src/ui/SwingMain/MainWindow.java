package ui.SwingMain;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * Main Email Client window
 */
public class MainWindow extends JFrame implements ActionListener {

    ToolRibbon toolRibbon = new ToolRibbon();
    MenuMain menuBar = new MenuMain();

    /**
     * Constructor
     */
    public MainWindow() {
        super("Email Client");

        // Create the main layout
        this.setLayout(new BorderLayout());

        // Contruct widgets
        JToolBar tool_bar = toolRibbon.toolbar();
        JMenuBar mainmenu = menuBar.menumail();
        TreeExplorer inboxTree = new TreeExplorer();
        inboxTree.refresh();
        EmailListDisplayPane emailListDisplayPane = new EmailListDisplayPane();
        EmailContentPane emailContentPane = new EmailContentPane();

        // Create a layout for the center
        JPanel center = new JPanel();
        GridLayout centerLayout = new GridLayout();
        centerLayout.setColumns(1);
        centerLayout.setRows(2);
        center.setLayout(centerLayout);

        // Add elements
        center.add(emailListDisplayPane);
        center.add(emailContentPane);

        this.add(center);
        this.add(tool_bar, BorderLayout.NORTH);
        this.add(inboxTree, BorderLayout.WEST);

        // Window details
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(mainmenu);
        this.setSize(1000, 600);
    }

    /**
     *
     * @param e Action Performed by user
     */
    public void actionPerformed(ActionEvent e) {
    }
}
