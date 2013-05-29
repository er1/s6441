package Email;

import java.util.ArrayList;

/**
 * Means of holding Messages
 *
 * @author chanman
 */
public interface Folder {

    /**
     * Get the ID of a Folder
     *
     * @return id the unique ID for this folder
     */
    public String getId();

    /**
     * Set the ID of a Folder
     *
     * @param id the unique ID for this folder
     */
    public void setId(String id);

    /**
     * Get the name of a Folder
     *
     * @return name the display name for this folder
     */
    public String getName();

    /**
     * Set this name of a Folder
     *
     * @param name the display name for this folder
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
     * Add message to this folder
     *
     * @param msg the Message to add to this Folder
     */
    void addMessage(Message msg);

    /**
     * Copy the given message to this folder.
     *
     * @param msg
     */
    void addMessageCopy(Message msg);

    /**
     * Delete a Message from this folder
     *
     * @param msg the Message to delete
     */
    void deleteMessage(Message msg);

    /**
     * Add folder as a sub-folder of this folder
     *
     * If this folder already exists then it will be moved to this folder
     *
     * @param folder the folder to be added to this one
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

    /**
     * Creates a new folder
     *
     * @param name
     */
    public void createFolder(String name);

    /**
     * Moves a folder to specified destination folder
     *
     * @param dest
     */
    public void moveFolder(Folder dest);
}
