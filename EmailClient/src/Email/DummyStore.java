/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

/**
 *
 * @author chanman
 */
public class DummyStore implements MessageStore {

    @Override
    public Folder getInbox() {
        return new EmptyFolder();
    }

    @Override
    public Folder getOutbox() {
        return new EmptyFolder();
    }

    @Override
    public Folder getSentMessages() {
        return new EmptyFolder();
    }

    @Override
    public MessageSet getMessages() {
        return new MessageSet();
    }

    @Override
    public FolderSet getSubfolders() {
        return new FolderSet();
    }

    @Override
    public void addMessage(Message msg) {
    }

    @Override
    public void addMessageCopy(Message msg) {
    }

    @Override
    public void deleteMessage(Message msg) {
    }

    @Override
    public void addFolder(Folder folder) {
    }

    @Override
    public void deleteFolder(Folder folder) {
    }

    @Override
    public void sync() {
    }
}
