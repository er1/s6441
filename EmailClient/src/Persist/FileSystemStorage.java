/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;

/**
 *
 * @author anasalkhatib
 */
class FileSystemStorage extends PersistentStorage {
    static final Logger logger = Logger.getLogger(FileSystemStorage.class.getName()); 
    static {
        logger.setParent(Logger.getLogger(FileSystemStorage.class.getPackage().getName()));
    }
    
    String mailBoxPath;
    
    FileSystemStorage(String mailBoxID){
        String path =  getHomeFolderPathWithSeparator() + mailBoxID + File.separator;
        mailBoxPath = path;
        logger.log(Level.INFO, "mailBoxPath set to {0}", mailBoxPath);
        newFolder(mailBoxPath);
        initialiseMailboxFolder();
    }
    
    private final String getHomeFolderPathWithSeparator(){
        return System.getProperty("user.home") + File.separator;
    }
    
    @Override
    public boolean newFolder(String fullPath)
    {
        File folder = new File(fullPath);
        //TODO Handle return?
        return folder.mkdir();
    }
    @Override
    public boolean newFolderInMailbox(String newFolderPath) {
        File folder = new File(mailBoxPath + newFolderPath);
        //TODO Handle return?
        return folder.mkdir();
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

    private void initialiseMailboxFolder() {
        List<String> initialFolders = Util.newArrayList();
        initialFolders.add("Inbox");
        initialFolders.add("Drafts");
        initialFolders.add("Outbox");
        initialFolders.add("Sent");
        initialFolders.add("Trash");
 
        for (String folder : initialFolders)
        {
            newFolderInMailbox(folder);
        }
    }
}