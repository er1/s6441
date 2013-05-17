package Email;

/**
 * Object to represent a Message
 *
 * @author chanman
 */
public interface Message {

    /**
     * Set the content of the message body
     *
     * @param content
     */
    void setContent(String content);

    /**
     * Get just the content of the message body
     *
     * @return content
     */
    String getContent();

    /**
     * get one header value in the message
     *
     * @param key
     * @return value
     */
    String getHeader(String key);

    /**
     * set one header value in the message
     *
     * @param key
     * @param value
     */
    void setHeader(String key, String value);
    // FIXME: Would these style on Message make sense?
    /*
     String[] getFrom();
     String[] getTo();
     String[] getCC();
     String[] getBCC();
     */
}
