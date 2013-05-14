package Email;

/**
 * An empty folder with no backing store for testing
 *
 * @author chanman
 */
public class EmptyFolder implements Folder {

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
