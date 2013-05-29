package Email;

import Persist.PersistentStorage;
import java.util.HashMap;

/**
 * File System Mailbox retrieves the respective messages or sub folders for the
 * folders from the persistent storage system
 *
 * @author anasalkhatib
 */
public class FileSystemMailbox extends FileSystemFolder implements Mailbox {

    private HashMap<String, FileSystemFolder> topLevelFolders;
    private PersistentStorage storage = PersistentStorage.getInstance();

    /**
     * Constructor create a Mailbox object to represent a mailbox on the file
     * system.
     *
     * @param mailboxID
     */
    public FileSystemMailbox(String mailboxID) {
        super(mailboxID, null);
        this.topLevelFolders = new HashMap<String, FileSystemFolder>();
    }

    private FileSystemFolder getFolder(String folderName) {
        if (topLevelFolders.containsKey(folderName)) {
            return topLevelFolders.get(folderName);
        }

        // ???
        FileSystemFolder fsFolder = new FileSystemFolder(folderName, this);
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

    @Override
    public void setParent(FileSystemFolder parent) {
    }

    @Override
    public FileSystemFolder getParent() {
        return null;
    }

    @Override
    public String getPath() {
        return this.getName();
    }
}
