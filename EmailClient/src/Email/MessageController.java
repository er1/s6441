package Email;

public interface MessageController {
    
    FolderID[] getFolderList(FolderID folder);

    MessageID[] getEmailList(FolderID folder);

    String getEmailContent(MessageID message);

    void setEmailContent(MessageID message, String content);

    Header getEmailSummary(MessageID message); // FIXME: 

    void setEmailHeader(MessageID message, String key, String value);

    void markRead(MessageID message);

    void markUnread(MessageID message);

    void delete(MessageID message);

    void moveTo(MessageID message, FolderID folder);

    MessageID compose();

    MessageID reply(MessageID originalMessage);
}
