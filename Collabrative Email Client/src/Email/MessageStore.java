package Email;

/**
 * Root container of all messages allowing direct lookup of
 * special purpose containers
 * 
 * @author chanman
 */
public interface MessageStore extends Container {
    /**
     * Get the Inbox container
     * @return Inbox
     */
    Container getInbox();
    /**
     * get the Outbox container
     * @return Outbox
     */
    Container getOutbox();
    /**
     * get the Sent Messages container
     * @return SentMessages
     */
    Container getSentMessages();    
}
