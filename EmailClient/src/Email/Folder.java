package Email;

import java.util.ArrayList;

/**
 * Means of holding Messages
 *
 * @author chanman
 */
public interface Folder {

    /**
     * Get the name of a Folder
     *
     * @return
     */
    public String getName();

    /**
     * Set this name of a Folder
     *
     * @param name
     */
    public void setName(String name);

    /**
     * Get the set of messages in this folder
     *
     * @return list of Messages
     */
    ArrayList<Message> getMessages();

    /**
     * Get the set of folders directly inside this folder
     *
     * @return list of Folders
     */
    ArrayList<Folder> getSubfolders();

    /**
     * Add msg to this folder
     *
     * @param msg
     */
    void addMessage(Message msg);

    /**
     * Copy the given message to this folder.
     *
     * @param msg
     */
    void addMessageCopy(Message msg);

    /**
     * Remove msg from this folder
     *
     * @param msg
     */
    void deleteMessage(Message msg);

    /**
     * Add folder as a subfolder of this folder
     *
     * If this folder already exists then it will be moved to this folder
     *
     * @param folder
     */
    void addFolder(Folder folder);

    /**
     * Remove folder permanently
     *
     * @param folder
     */
    void deleteFolder(Folder folder);

    /**
     * Sync up this folder
     *
     * This may be overloaded for special cases such as Inbox and Outbox
     */
    void sync();
}
