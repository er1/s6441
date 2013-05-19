package Email;

import java.util.ArrayList;
import util.Util;

/**
 * An empty folder with no backing store for testing
 *
 * @author chanman
 */
public class TemporaryFolder implements Folder {

    ArrayList<Message> messages;
    ArrayList<Folder> folders;
    String name = "Folder";
    // ID used the the messagecontroller for bookeeping
    String id = new String();

    public TemporaryFolder(String n) {
        this.folders = Util.newArrayList();
        this.messages = Util.newArrayList();
        name = n;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public ArrayList<Folder> getSubfolders() {
        return folders;
    }

    @Override
    public void addMessage(Message msg) {
        messages.add(msg);
    }

    @Override
    public void addMessageCopy(Message msg) {
        messages.add(msg);
    }

    @Override
    public void deleteMessage(Message msg) {
        messages.remove(msg);
    }

    @Override
    public void addFolder(Folder folder) {
        folders.add(folder);
    }

    @Override
    public void deleteFolder(Folder folder) {
        folders.remove(folder);
    }

    @Override
    public void sync() {
        for (Folder f : folders) {
            f.sync();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String n) {
        name = n;
    }
}
