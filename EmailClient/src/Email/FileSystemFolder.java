/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Persist.PersistentStorage;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;

/**
 * Implementation of File System
 *
 * @author anasalkhatib
 */
public class FileSystemFolder implements Folder {

    private FileSystemFolder parent;
    ArrayList<Message> messages;
    ArrayList<Folder> folders;
    String name = new String();
    PersistentStorage persistStore = PersistentStorage.getInstance();
    static final Logger logger = Logger.getLogger(FileSystemFolder.class.getName());

    /**
     * Constructor for initialization
     *
     * @param id
     */
    public FileSystemFolder(String name, FileSystemFolder parent) {
        this.name = name;
        this.parent = parent;

        this.folders = Util.newArrayList();
        this.messages = Util.newArrayList();

        this.sync();
    }

    @Override
    public String getId() {
        return this.getPath();
    }

    @Override
    public void setId(String id) {
        // FIXME; should never be called;
        throw new NullPointerException();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ArrayList<Message> getMessages() {

        ArrayList<String> messageList = persistStore.loadMessageListFromFolder(this.getPath());
        ArrayList<Message> messageNames = Util.newArrayList();
        if (null != messageList) {
            for (String messagePath : messageList) {
                String filecontent = persistStore.loadMessage(messagePath);
                PlainTextMessage msg = PlainTextMessage.parse(filecontent);
                if (msg != null) {
                    msg.setId(messagePath);
                    messageNames.add(msg);
                }
            }
        }
        this.messages = messageNames;

        return messageNames;
    }

    @Override
    public ArrayList<Folder> getSubfolders() {
        ArrayList<String> subFolderList = persistStore.loadSubfolders(this.getPath());
        ArrayList<Folder> subFolders = Util.newArrayList();
        if (null != subFolderList) {
            for (String subfolder : subFolderList) {

                // FIXME:
                String[] sep = subfolder.split(File.separator);
                String last = sep[sep.length - 1];
                FileSystemFolder folder = new FileSystemFolder(last, this);
                //FIXME populate ArrayList in folder? Or In constructor?
                //Or does the folder do it as needed.
                // TODO: proxy pattern might work here   -e
                subFolders.add(folder);

            }
        }
        this.folders = subFolders;

        return subFolders;
    }

    @Override
    public void addMessage(Message msg) {
        PlainTextMessage m = (PlainTextMessage) msg;
        String id = m.getId();
        String fn = id.substring(id.lastIndexOf(File.separator) + 1);
        String newid = this.getPath() + File.separator + fn;
        msg.setId(newid);
        persistStore.newMessage(m.getId());
        persistStore.saveMessage(m.getId(), m.serialize());
        this.messages.add(m);
    }

    @Override
    public void addMessageCopy(Message msg) {
        //FIXME: Currenlty Assuming this is save to existing message
        //TODO check that toString is implemented
        persistStore.saveMessage(msg.getId(), msg.toString());
    }

    @Override
    public void deleteMessage(Message msg) {
        persistStore.deleteMessage(msg.getId());
        this.messages.remove(msg);
    }

    @Override
    public void addFolder(Folder folder) {
        persistStore.newFolderInMailbox(folder.getId());
        this.folders.add(folder);
    }

    @Override
    public void deleteFolder(Folder folder) {
        persistStore.deleteFolderAndAllContents(folder.getId());
        this.folders.remove(folder);
    }

    @Override
    public void sync() {
        //TODO Maybe this is where we can re-read from FileSystem and repopulate
        //the arraylists for messageNames and folders?
        // TODO: yes it is   -e
        this.getMessages();
        this.getSubfolders();
    }

    @Override
    public void moveFolder(Folder destination) {
        FileSystemFolder fsdest;
        fsdest = (FileSystemFolder) destination;
        persistStore.moveFolder(this.getPath(), fsdest.getPath());
    }

    public void setParent(FileSystemFolder parent) {
        this.parent = parent;
    }

    public FileSystemFolder getParent() {
        return parent;
    }

    public String getPath() {
        return parent.getPath() + File.separator + this.getName();
    }

    @Override
    public void createFolder(String name) {
        persistStore.newFolder(this.getPath() + File.separator + name);
    }
}
