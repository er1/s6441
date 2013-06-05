package Persist;

import java.util.ArrayList;

/**
 * Persistent Storage is for requesting File System store
 * @author anasalkhatib
 */
public abstract class PersistentStorage {

    static private PersistentStorage instance;

    /**
     * Static Factory method to request a File System Persistent store
     *
     * @param mailBoxID 
     * @return A Persistent Storage that uses the File System as the back end
     */
    public static PersistentStorage getFileSystemStorage(String mailBoxID) {
        if (instance == null);
        {
            FileSystemStorage fileSystemStorage = new FileSystemStorage(mailBoxID);
            instance = fileSystemStorage;
            return fileSystemStorage;
        }
    }
    /**
     * Get instance of Persistent Storage
     * @return instance 
     */
    public static PersistentStorage getInstance() {
        return instance;
    }

    /**
     * Get the id of mail box
     * @return MailBoxID 
     */
    abstract public String getMailboxID();

    /**
     * New Folder will be created in a given path
     * @param fullPath
     * @return true/false 
     */
    abstract public boolean newFolder(String fullPath);

    /**
     * Create a new folder in MailBox
     * @param newFolderPath
     * @return true/false
     */
    abstract public boolean newFolderInMailbox(String newFolderPath);
    /**
     * Create new rule file in mailbox to store list of rules
     * @param path
     * @return true/false
     */
    abstract public boolean newRuleFileInMailbox(String path);
    /**
     * Load all messages from the folder
     * @param folder
     * @return ArrayList of messages
     */
    abstract public ArrayList<String> loadMessageListFromFolder(String folder);
    /**
     * Load all the sub folders from a directory
     * @param folder
     * @return ArrayList of sub folders in a directory
     */
    abstract public ArrayList<String> loadSubfolders(String folder);
    /**
     * Delete the folder and its contents 
     * @param folder
     * @return true/false
     */
    abstract public boolean deleteFolderAndAllContents(String folder);

    /**
     * Move a message to a given folder
     * @param message
     * @param folder
     * @return true/false
     */
    abstract public boolean moveMessageToFolder(String message, String folder);

    /**
     * Add a new message file to a given folder
     * @param folder
     * @return true/false
     */
    abstract public boolean newMessage(String folder);

    /**
     * Save message file with the given content
     * @param message
     * @param content
     * @return true/false
     */
    abstract public boolean saveMessage(String message, String content);
    /**
     * Get content of a given messagePath
     * @param message
     * @return message
     */
    abstract public String loadMessage(String message);
    /**
     * Delete message file
     * @param message
     * @return true/false
     */
    abstract public boolean deleteMessage(String message);

    /**
     * Move a folder to another folder
     * @param folderToMove
     * @param destinationFolder
     * @return true/false
     */
    abstract public boolean moveFolder(String folderToMove, String destinationFolder);
}
