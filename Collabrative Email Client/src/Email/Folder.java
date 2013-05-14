package Email;

/**
 * Means of holding Messages
 * 
 * @author chanman
 */
public interface Folder {
    /**
     * Get the set of messages in this folder
     * @return list of Messages
     */
    MessageSet getMessages();
    /**
     * Get the set of folders directly inside this folder
     * @return list of Folders
     */
    FolderSet getSubfolders();
    /**
     * Sync up this folder
     * 
     * This may be overloaded for special cases such as Inbox and Outbox
     */
    void sync();
}
