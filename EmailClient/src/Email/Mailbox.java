package Email;

/**
 * Root folder of all messages allowing direct lookup of special purpose folder
 *
 * @author chanman
 */
public interface Mailbox extends Folder {

    /**
     * Get user id
     * @return id
     */
    String getUserId();

    /**
     * Get the Drafts folder
     *
     * @return Drafts
     */
    Folder getDrafts();

    /**
     * Get the Inbox folder
     *
     * @return Inbox
     */
    Folder getInbox();

    /**
     * get the Outbox folder
     *
     * @return Outbox
     */
    Folder getOutbox();

    /**
     * get the Sent Messages folder
     *
     * @return SentMessages
     */
    Folder getSentMessages();

    /**
     * get the Trash folder
     *
     * @return Trash
     */
    Folder getTrash();

    /**
     *
     * @return
     */
    Folder getMeetings();

    /**
     *
     * @return
     */
    Folder getTemplates();
}
