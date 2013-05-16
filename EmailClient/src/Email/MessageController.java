package Email;

public interface MessageController {    

    FID[] getFolderList(FID);

    MID[] getEmailList(FID);

    String getEmailContent(MID);

    void setEmailContent(MID, String);

    Summary getEmailSummary(MID);

    setEmailHeader (MID, String key, String value);

    void markRead(MID);

    void markUnread(MID);

    void delete(MID);

    void moveTo(MID, FID);

    MID compose();

    MID reply(MID);
}
