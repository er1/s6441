/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        //TODO Handle return, or leave it up to caller?
        return folder.mkdir();
    }
    @Override
    public boolean newFolderInMailbox(String newFolderPath) {
        File folder = new File(mailBoxPath + newFolderPath);
        //TODO Handle return?
        return folder.mkdir();
    }

    @Override
    public Set<String> loadMessageListFromFolder(String folder) {
        File parentFolder = new File(mailBoxPath + folder);
        Set<String> messageSet = null;
        if (!parentFolder.isDirectory() || !parentFolder.exists()) {
            return messageSet;
        };
        File[] allFiles = parentFolder.listFiles();
        for (File file : allFiles) {
            if (file.isFile()) {
                messageSet.add(folder + file.getName() + File.separator);
                logger.log(Level.INFO, messageSet.toString());
            }
        }
        return messageSet;
    }

    @Override
    public Set<String> loadSubfolders(String folder) {
        File parentFolder = new File(mailBoxPath + folder);
        Set<String> folderSet = null;
        if (!parentFolder.isDirectory() || !parentFolder.exists()) {
            return folderSet;
        };
        File[] allFiles = parentFolder.listFiles();
        for (File file : allFiles) {
            if (file.isDirectory()) {
                folderSet.add(folder + file.getName() + File.separator);
                logger.log(Level.INFO, folderSet.toString());
            }
        }
        return folderSet;
    }

    @Override
    public boolean deleteFolder(String folder) {
        return false;
    }

    @Override
    public boolean moveMessageToFolder(String message, String folder) {
        return false;
    }

    @Override
    public boolean newMessage(String folder) {
        return false;
    }

    @Override
    public boolean saveMessage(String message, String content) {
        return false;
    }

    @Override
    public String loadMessage(String message) {
        return null;
    }

    @Override
    public boolean deleteMessage(String message) {
        return false;
    }

    @Override
    public boolean moveFolder(String folderToMove, String destinationFolder) {
        return false;
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