package Email;

/**
 * An empty folder with no backing store for testing
 *
 * @author chanman
 */
public class TemporaryFolder implements Folder {
    MessageSet messages = new MessageSet();
    FolderSet folders = new FolderSet();

    @Override
    public MessageSet getMessages() {
        return messages;
    }

    @Override
    public FolderSet getSubfolders() {
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
}
