package Email;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author chanman
 */
public class FileSystemFolderProxy implements Folder {

    private FileSystemFolder parent;
    String name = new String();
    private FileSystemFolder instance;

    FileSystemFolderProxy(String name, FileSystemFolder parent) {
        this.name = name;
        this.parent = parent;
        this.instance = null;
    }

    @Override
    public String getId() {
        if (instance == null) {
            return parent.getPath() + File.separator + name;
        }
        return instance.getId();
    }

    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Message> getMessages() {
        inst();
        return instance.getMessages();
    }

    @Override
    public ArrayList<Folder> getSubfolders() {
        inst();
        return instance.getSubfolders();
    }

    @Override
    public void addMessage(Message msg) {
        inst();
        instance.addMessage(msg);
    }

    @Override
    public void addMessageCopy(Message msg) {
        inst();
        instance.addMessageCopy(msg);
    }

    @Override
    public void deleteMessage(Message msg) {
        inst();
        instance.deleteMessage(msg);
    }

    @Override
    public void addFolder(Folder folder) {
        inst();
        instance.addFolder(folder);
    }

    @Override
    public void deleteFolder(Folder folder) {
        inst();
        instance.deleteFolder(folder);
    }

    @Override
    public void sync() {
        inst();
        instance.sync();
    }

    @Override
    public void createFolder(String name) {
        inst();
        instance.createFolder(name);
    }

    @Override
    public void moveFolder(Folder dest) {
        inst();
        instance.moveFolder(dest);
    }

    private void inst() {
        if (instance == null) {
            instance = new FileSystemFolder(name, parent);
        }
    }
}
