package Email;

/**
 *
 * @author chanman
 */
class Summary {

    String date;
    String to;
    String from;
    String cc;
    String bcc;
    String subject;
    boolean read;

    public Summary Date(String date) {
        this.date = date;
        return this;
    }

    public Summary To(String to) {
        this.to = to;
        return this;
    }

    public Summary From(String from) {
        this.from = from;
        return this;
    }

    public Summary CC(String cc) {
        this.cc = cc;
        return this;
    }

    public Summary BCC(String bcc) {
        this.bcc = bcc;
        return this;
    }

    public Summary Subject(String subject) {
        this.subject = subject;
        return this;
    }
    
    public Summary Read(boolean isRead) {
        this.read = isRead;
        return this;
    }

    public String getDate() {
        return date;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getCc() {
        return cc;
    }

    public String getBcc() {
        return bcc;
    }

    public String getSubject() {
        return subject;
    }
    
    public boolean isRead() {
        return read;
    }
}
