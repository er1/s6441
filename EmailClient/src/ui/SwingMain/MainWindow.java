package ui.SwingMain;

import Email.FileSystemMailbox;
import Email.MessageController;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import ui.LabeledTextField;

/**
 * Main Email Client window
 */
public class MainWindow extends JFrame {

    JMenuBar windowMenu;
    ToolRibbon toolbar;
    FolderList folders;
    MessageList messages;
    Content content;
    JTextField searchBar;
    SearchFolderTableModel search;
    JButton searchButton;
    MessageController controller;

    /**
     * Constructor
     */
    public MainWindow() {
        super("Email Client");
        String mailBoxID = Persist.PersistentStorage.getInstance().getMailboxID();
        controller = MessageController.getInstance(new FileSystemMailbox(mailBoxID));
        controller.loadRules();
        //this.windowMenu = new Menu();
        this.toolbar = new ToolRibbon();
        this.content = new Content();
        this.messages = new MessageList(this.content, this.toolbar);
        this.messages.displayFolder(controller.getInboxFolderId());
        this.folders = new FolderList(this.messages);

        this.searchBar = new JTextField();
        this.searchButton = new JButton("Search");

        searchButton.setToolTipText("To search Mail (Alt + S)");

        // Action listner
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doSearch(searchBar.getText());
            }
        });
        searchButton.setMnemonic(KeyEvent.VK_S);
        
        // Create the main layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Contruct widgets
        folders.refresh();

        // Create the layout for the search bar
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());     
        
        JPanel searchCenterPanel = new JPanel();
        searchCenterPanel.setLayout(new BorderLayout());       

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
        
        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchBar);
        searchPanel.add(searchButton, BorderLayout.EAST);
        
        searchCenterPanel.add(searchPanel, BorderLayout.NORTH);
        searchCenterPanel.add(center);
        
        this.add(searchCenterPanel);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(foldersPane, BorderLayout.WEST);

        // Window details
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(windowMenu);
        this.setSize(1000, 600);

    }

    public void doSearch(String searchText) {
        search = new SearchFolderTableModel(controller.getRootFolderId(), searchText);
        messages.setModel(search);
    }
}
