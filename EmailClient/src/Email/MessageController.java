package Email;

import java.util.ArrayList;

/**
 * Message controller class
 */
public class MessageController {

    private Message getMessageFromID(String messageId) {
        return new PlainTextMessage() {
        }; // FIXME
    }

    private Folder getFolderFromID(String folderId) {
        return new TemporaryFolder(""); // FIXME
    }

    private String getIDfromMessage(Message message) {
        return new String(); // FIXME
    }

    private String getIDfromFolder(Folder folder) {
        return new String(); // FIXME
    }

    /**
     *
     * @param folder
     * @return
     */
    public String[] getFolderList(String folderId) {
        Folder fldr = getFolderFromID(folderId);
        ArrayList<Folder> set = fldr.getSubfolders();
        String[] ids = new String[set.size()];

        int index = 0;
        for (Folder subfolder : set) {
            ids[index++] = getIDfromFolder(subfolder);
        }

        return ids;
    }

    /**
     *
     * @param folderId ID of folder
     * @return Array of string id's of all the messages inside our folder
     */
    public String[] getEmailList(String folderId) {
        Folder fldr = getFolderFromID(folderId);
        ArrayList<Message> set = fldr.getMessages();
        String[] ids = new String[set.size()];

        int index = 0;
        for (Message message : set) {
            ids[index++] = getIDfromMessage(message);
        }

        return ids;
    }

    /**
     *
     * @param id
     * @return
     */
    public String getEmailContent(String id) {
        Message message = getMessageFromID(id);
        return message.getContent();
    }

    /**
     *
     * @param id
     * @param content
     */
    public void setEmailContent(String id, String content) {
        Message message = getMessageFromID(id);
        message.setContent(content);
    }

    /**
     *
     * @param messageID ID of Message
     * @return Summary of the Message
     */
    public Summary getEmailSummary(String messageID) {
        Message message = getMessageFromID(messageID);
        Summary summary = new Summary(message);

        return summary;
    }

    /**
     *
     * @param messageId Message ID
     * @param key Key
     * @param value Value
     */
    public void setEmailHeader(String messageId, String key, String value) {
        Message msg = getMessageFromID(messageId);
        msg.setHeader(key, value);
    }

    /**
     *
     * @param messageId Message ID
     * @param key Key for which we want a value
     * @return Value of particular key within message header
     */
    public String getEmailHeader(String messageId, String key) {
        Message msg = getMessageFromID(messageId);
        return msg.getHeaderValue(key);
    }

    /**
     *
     * @param messageId Message ID
     */
    public void markRead(String messageId) {
        setEmailHeader(messageId, "X-Read", "FIXME: Set to NOW()");
    }

    /**
     *
     * @param messageId Message ID
     */
    public void markUnread(String messageId) {
        setEmailHeader(messageId, "X-Read", null);
    }

    /**
     *
     * @param messageId Message ID
     */
    public void delete(String messageId) {
        // FIXME;
    }

    /**
     *
     * @param messageId Message ID
     * @param destinationFolderId Destination Folder ID
     */
    public void moveMessageToFolder(String messageId, String destinationFolderId) {
        Message msg = getMessageFromID(messageId);
        Folder destination = getFolderFromID(destinationFolderId);
        destination.addMessage(msg);
    }

    //FIXME Not sure what this does
    public String compose() {
        return new String();
    }

    String reply(String originalMessage) {
        Message original = getMessageFromID(originalMessage);

        String replyid = compose();
        Message replymsg = getMessageFromID(replyid);

        String replyContent = original.getContent();
        replyContent = "\r\n\r\n" + replyContent;
        replyContent = replyContent.replaceAll("\n", "\n> ");

        String to = original.getHeaderValue("From");
        String subject = original.getHeaderValue("subject");

        if (!"RE:".equals(subject.substring(0, 3).toUpperCase())) {
            subject = "RE: " + subject;
        }

        replymsg.setContent(replyContent);
        replymsg.setHeader("To", to);
        replymsg.setHeader("Subject", subject);

        return replyid;
    }
}
