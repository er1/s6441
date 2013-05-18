/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;

/**
 *
 * @author anasalkhatib
 */
class FileSystemStorage extends PersistentStorage {

    @Override
    public void newFolder(String newFolderName, String parentFolder) {
    }

    @Override
    public String[] loadMessageListFromFolder(String folder) {
        return null;
    }

    @Override
    public String[] loadSubfolders(String folder) {
        return null;
    }

    @Override
    public void deleteFolder(String folder) {
    }

    @Override
    public void moveMessageToFolder(String message, String folder) {
    }

    @Override
    public void newMessage(String folder) {
    }

    @Override
    public void saveMessage(String message, String content) {
    }

    @Override
    public String loadMessage(String message) {
        return null;
    }

    @Override
    public void deleteMessage(String message) {
    }

    @Override
    public void moveFolder(String folderToMove, String destinationFolder) {
    }
}
