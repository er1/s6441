package Email;

/**
 * Object to represent a Message
 *
 * @author chanman
 */
public interface Message {

    /**
     * Get id
     *
     * @return id
     */
    public String getId();

    /**
     * Set id
     *
     * @param id
     */
    public void setId(String id);

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
    String getHeaderValue(String key);

    /**
     * set one header value in the message
     *
     * @param key
     * @param value
     */
    void setHeader(String key, String value);

    /**
     * Append to header (Comma separated list)
     */
    void appendToHeader(String key, String addValue);
}
