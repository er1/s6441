package Email;

/**
 * Root folder of all messages allowing direct lookup of special purpose folder
 *
 * @author chanman
 */
public interface MessageStore extends Folder {

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
}
