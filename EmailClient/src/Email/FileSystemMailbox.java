/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Persist.PersistentStorage;
import java.util.HashMap;

/**
 * File System Mailbox retrieves the respective messages or sub folders
 * for the folders from the persistent storage system
 * @author anasalkhatib
 */
public class FileSystemMailbox extends FileSystemFolder implements Mailbox {
    private HashMap<String, FileSystemFolder> topLevelFolders;
    private PersistentStorage storage = PersistentStorage.getInstance();

    public FileSystemMailbox(String mailboxID) {
        //This is where we get the mailboxID
        //But this applies only to the main mailBoxFolder
        //How should the other folder classes get mailBoxId?
        super(mailboxID);
        this.topLevelFolders = new HashMap<String, FileSystemFolder>();
    }

    private FileSystemFolder getFolder(String folderName) {
        if (topLevelFolders.containsKey(folderName)) {
            return topLevelFolders.get(folderName);
        }

        FileSystemFolder fsFolder = new FileSystemFolder(folderName);
        topLevelFolders.put(folderName, fsFolder);
        return fsFolder;
    }

    @Override
    public FileSystemFolder getDrafts() {
        String folderName = "Drafts";
        return getFolder(folderName);
    }

    @Override
    public Folder getInbox() {
        String folderName = "Inbox";
        return getFolder(folderName);
    }

    @Override
    public Folder getOutbox() {
        String folderName = "Outbox";
        return getFolder(folderName);
    }

    @Override
    public Folder getSentMessages() {
        String folderName = "Sent";
        return getFolder(folderName);
    }

    @Override
    public Folder getTrash() {
        String folderName = "Trash";
        return getFolder(folderName);
    }
}
