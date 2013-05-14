package Email;

/**
 * Means of holding Messages
 * 
 * @author chanman
 */
public interface Container {
    /**
     * Get the set of messages in this container
     * @return list of Messages
     */
    MessageSet getMessages();
    /**
     * Get the set of containers directly inside this container
     * @return list of Containers
     */
    ContainerSet getSubcontainers();
    
    /**
     * Sync up this container
     * 
     * This may be overloaded for special cases such as Inbox and Outbox
     */
    void sync();
}
