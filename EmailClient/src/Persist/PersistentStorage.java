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

    public static PersistentStorage getFileSystemStorage() {
        FileSystemStorage fileSystemStorage = new FileSystemStorage();
        return fileSystemStorage;
    }

    abstract public void save();

    abstract public void load();
}
