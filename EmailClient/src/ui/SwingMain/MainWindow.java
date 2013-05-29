package ui.SwingMain;

import Email.DummyStore;
import Email.FileSystemFolder;
import Email.FileSystemMailbox;
import Email.MessageController;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.GridLayout;

/**
 * Main Email Client window
 */
public class MainWindow extends JFrame {

    JMenuBar windowMenu;
    ToolRibbon toolbar;
    FolderList folders;
    MessageList messages;
    Content content;

    /**
     * Constructor
     */
    public MainWindow() {
        super("Email Client");
        String mailBoxID = Persist.PersistentStorage.getInstance().getMailboxID();
        MessageController controller = MessageController.getInstance(new FileSystemMailbox(mailBoxID));

        //this.windowMenu = new Menu();
        this.toolbar = new ToolRibbon();
        this.content = new Content(controller);
        this.messages = new MessageList(controller, this.content, this.toolbar);
        this.messages.displayFolder(controller.getInboxFolderId());
        this.folders = new FolderList(controller, this.messages);


        // Create the main layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Contruct widgets
        folders.refresh();

        // Create a layout for the center
        JPanel center = new JPanel();
        GridLayout centerLayout = new GridLayout();
        centerLayout.setColumns(1);
        centerLayout.setRows(2);
        center.setLayout(centerLayout);

        layout.setHgap(2);
        centerLayout.setVgap(2);

        // create scroll pane for elements that need it
        JScrollPane foldersPane = new JScrollPane(folders);
        JScrollPane messagesPane = new JScrollPane(messages);
        JScrollPane contentPane = new JScrollPane(content);

        // border everthing
        foldersPane.setBorder(BorderFactory.createLoweredBevelBorder());
        messagesPane.setBorder(BorderFactory.createLoweredBevelBorder());
        contentPane.setBorder(BorderFactory.createLoweredBevelBorder());

        // Add elements
        center.add(messagesPane);
        center.add(contentPane);

        this.add(center);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(foldersPane, BorderLayout.WEST);

        // Window details
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(windowMenu);
        this.setSize(1000, 600);
    }
}
