package Email;

/**
 * Summary Class is for getting/setting fields like Date,From,To,CC,BCC,Subject from/to the message
 * @author chanman
 */
public class Summary {

    String date;
    String to;
    String from;
    String cc;
    String bcc;
    String subject;
    boolean read;

    Summary(Message message) {

        this.Date(message.getHeaderValue("Date")).
                From(message.getHeaderValue("From")).
                To(message.getHeaderValue("To")).
                CC(message.getHeaderValue("Cc")).
                BCC(message.getHeaderValue("Bcc")).
                Subject(message.getHeaderValue("Subject")).
                Read(message.getHeaderValue("X-Read").length() > 0);
    }

    /**
     * Set Date
     * @param date
     * @return Summary object with updated date 
     */
    public Summary Date(String date) {
        this.date = date;
        return this;
    }

    /**
     * Set To
     * @param to
     * @return Summary object with updated To
     */
    public Summary To(String to) {
        this.to = to;
        return this;
    }

    /**
     * Set From
     * @param from
     * @return Summary object with updated From
     */
    public Summary From(String from) {
        this.from = from;
        return this;
    }

    /**
     * Set Cc
     * @param cc
     * @return Summary object with updated cc
     */
    public Summary CC(String cc) {
        this.cc = cc;
        return this;
    }

    /**
     * Set Bcc
     * @param bcc
     * @return Summary object with updated bcc
     */
    public Summary BCC(String bcc) {
        this.bcc = bcc;
        return this;
    }

    /**
     * Set subject
     * @param subject
     * @return Summary object with updated subject
     */
    public Summary Subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Set read 
     * @param isRead
     * @return Summary object with updated isRead field
     */
    public Summary Read(boolean isRead) {
        this.read = isRead;
        return this;
    }

    /**
     * Get Date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Get To
     * @return To
     */
    public String getTo() {
        return to;
    }

    /**
     * Get From
     * @return From
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get CC
     * @return cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * Get Bcc
     * @return bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Get subject
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Is message read
     * @return true/false
     */
    public boolean isRead() {
        return read;
    }
}
