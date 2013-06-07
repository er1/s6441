package Meeting;

import Email.Message;

/**
 * MeetingSummary Class is for getting/setting fields like
 * Date,From,To,CC,BCC,Subject * from/to the message
 *
 * @author chanman
 */
public class MeetingSummary {

    String date;
    String to;
    String from;
    String cc;
    String bcc;
    String subject;
    boolean read;
    private String startTime;
    private String endTime;
    private String meetingDate;
    private String meetingId;

    /**
     *
     * @param message
     */
    public MeetingSummary(Message message) {

        this.Date(message.getHeaderValue("Date")).
                From(message.getHeaderValue("From")).
                To(message.getHeaderValue("To")).
                CC(message.getHeaderValue("Cc")).
                BCC(message.getHeaderValue("Bcc")).
                Subject(message.getHeaderValue("Subject")).
                StartTime(message.getHeaderValue("MeetingStartTime")).
                EndTime(message.getHeaderValue("MeetingEndTime")).
                MeetingDate(message.getHeaderValue("MeetingDate")).
                MeetingID(message.getHeaderValue("X-MeetingId")).
                Read(message.getHeaderValue("X-Read").length() > 0);
    }

    /**
     * Set Date
     *
     * @param date
     * @return MeetingSummary object with updated date
     */
    public MeetingSummary Date(String date) {
        this.date = date;
        return this;
    }

    /**
     * Set To
     *
     * @param to
     * @return MeetingSummary object with updated To
     */
    public MeetingSummary To(String to) {
        this.to = to;
        return this;
    }

    /**
     * Set From
     *
     * @param from
     * @return MeetingSummary object with updated From
     */
    public MeetingSummary From(String from) {
        this.from = from;
        return this;
    }

    /**
     * Set Cc
     *
     * @param cc
     * @return MeetingSummary object with updated cc
     */
    public MeetingSummary CC(String cc) {
        this.cc = cc;
        return this;
    }

    /**
     * Set Bcc
     *
     * @param bcc
     * @return MeetingSummary object with updated bcc
     */
    public MeetingSummary BCC(String bcc) {
        this.bcc = bcc;
        return this;
    }

    /**
     * Set subject
     *
     * @param subject
     * @return MeetingSummary object with updated subject
     */
    public MeetingSummary Subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Set read
     *
     * @param isRead
     * @return MeetingSummary object with updated isRead field
     */
    public MeetingSummary Read(boolean isRead) {
        this.read = isRead;
        return this;
    }

    /**
     * Get Date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Get To
     *
     * @return To
     */
    public String getTo() {
        return to;
    }

    /**
     * Get From
     *
     * @return From
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get CC
     *
     * @return cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * Get Bcc
     *
     * @return bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Get subject
     *
     * @return subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Get starting time
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Get ending time
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Get meeting date
     * @return date
     */
    public String getMeetingDate() {
        return meetingDate;
    }

    /**
     * Get the meeting id
     * @return id
     */
    public String getMeetingId() {
        return meetingId;
    }
    /**
     * Is message read
     *
     * @return true/false
     */
    public boolean isRead() {
        return read;
    }

    MeetingSummary StartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    MeetingSummary EndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    MeetingSummary MeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
        return this;
    }

    MeetingSummary MeetingID(String meetingId) {
        this.meetingId = meetingId;
        return this;
    }
}
