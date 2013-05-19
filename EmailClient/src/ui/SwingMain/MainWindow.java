package ui.SwingMain;

import Email.DummyStore;
import Email.MessageController;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * Main Email Client window
 */
public class MainWindow extends JFrame implements ActionListener {

    JMenuBar mainmenu;
    JToolBar tool_bar;
    FolderList folders;
    MessageList messages;
    Content content;

    /**
     * Constructor
     */
    public MainWindow() {
        super("Email Client");

        MessageController controller = new MessageController(new DummyStore());

        this.mainmenu = new Menu();
        this.tool_bar = new ToolRibbon();
        this.content = new Content(controller);
        this.messages = new MessageList(controller, this.content);
        this.messages.displayFolder(controller.getInboxFolderId());
        this.folders = new FolderList(controller, this.messages);


        // Create the main layout
        this.setLayout(new BorderLayout());

        // Contruct widgets
        folders.refresh();

        // Create a layout for the center
        JPanel center = new JPanel();
        GridLayout centerLayout = new GridLayout();
        centerLayout.setColumns(1);
        centerLayout.setRows(2);
        center.setLayout(centerLayout);

        // Add elements
        center.add(messages);
        center.add(content);

        this.add(center);
        this.add(tool_bar, BorderLayout.NORTH);
        this.add(folders, BorderLayout.WEST);

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
