/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;

import java.util.Set;

/**
 *
 * @author anasalkhatib
 */
public abstract class PersistentStorage {

    /**
     * Static Factory method to request a File System Persistent store
     *
     * @return A Persistent Storage that uses the File System as the back end
     */
    public static PersistentStorage getFileSystemStorage(String mailBoxID) {
        FileSystemStorage fileSystemStorage = new FileSystemStorage(mailBoxID);
        return fileSystemStorage;
    }

    abstract public boolean newFolder(String fullPath);

    abstract public boolean newFolderInMailbox(String newFolderPath);
    abstract public Set<String> loadMessageListFromFolder(String folder);
    abstract public Set<String> loadSubfolders(String folder);
    abstract public boolean deleteFolder(String folder);

    abstract public boolean moveMessageToFolder(String message, String folder);

    abstract public boolean newMessage(String folder);

    abstract public boolean saveMessage(String message, String content);
    abstract public String loadMessage(String message);
    abstract public boolean deleteMessage(String message);

    abstract public boolean moveFolder(String folderToMove, String destinationFolder);
}
