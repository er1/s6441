/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;

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
    public static PersistentStorage getFileSystemStorage() {
        FileSystemStorage fileSystemStorage = new FileSystemStorage();
        return fileSystemStorage;
    }

    abstract public void save();

    abstract public void load();
}
