package Email;

/**
 * Object to represent a Message
 * 
 * @author chanman
 */
public interface Message {
    /**
     * Set the raw content of the message
     * @param content 
     */
    void setContent(String content);
    /**
     * Get the raw content of the message
     * @return content
     */
    String getContent();
    
    /**
     * get one header value in the message
     * @param key
     * @return value
     */
    String getHeader(String key);
    /**
     * set one header value in the message
     * @param key
     * @param value
     */
    void setHeader(String key, String value);

}
