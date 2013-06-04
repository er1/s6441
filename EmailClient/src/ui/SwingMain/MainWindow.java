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
    LabeledTextField searchBar;
    SearchFolderTableModel search;
    JButton searchButton;
    MessageController controller;

    /**
     * Constructor
     */
    public MainWindow() {
        super("Email Client");
        this.searchButton = new JButton("Search");
        String mailBoxID = Persist.PersistentStorage.getInstance().getMailboxID();
        controller = MessageController.getInstance(new FileSystemMailbox(mailBoxID));
        controller.loadRules();
        //this.windowMenu = new Menu();
        this.toolbar = new ToolRibbon();
        this.content = new Content();
        this.messages = new MessageList(this.content, this.toolbar);
        this.messages.displayFolder(controller.getInboxFolderId());
        this.folders = new FolderList(this.messages);
        this.searchBar = new LabeledTextField("");
       
 
        // Create the main layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        // Contruct widgets
        folders.refresh();

        // Create the layout for the search bar
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        
        // Grid layout search Bar
        JPanel buttonSearch = new JPanel();
        BorderLayout searchLayout = new BorderLayout();
        buttonSearch.setLayout(searchLayout);
        //searchLayout.getVgap();
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
        searchButton.setSize(45,20);
        searchButton.setToolTipText("To search Mail (Alt + S)");
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        messagesPane.setBorder(BorderFactory.createLoweredBevelBorder());
        contentPane.setBorder(BorderFactory.createLoweredBevelBorder());

        // Action listner
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doSearch(searchBar.getText());
            }
        });
        searchButton.setMnemonic(KeyEvent.VK_S);

        
        // Add elements        
        center.add(messagesPane);
        center.add(contentPane);
        buttonSearch.add(searchBar, BorderLayout.CENTER);
        buttonSearch.add(searchButton, BorderLayout.EAST);
        searchPanel.add(buttonSearch, BorderLayout.NORTH);
        searchPanel.add(center);
        this.add(searchPanel);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(foldersPane, BorderLayout.WEST);

        // Window details
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(windowMenu);
        this.setSize(1000, 600);
        
    }
    
    public void doSearch(String searchText)
    {
        System.out.println("Input Text -----> " + searchText);
        search = new SearchFolderTableModel(controller, controller.getRootFolderId(), searchText);
    }
}
