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

    public String getEmailContent(String id) {
        Message message = getMessageFromID(id);
        return message.getContent();
    }

    public void setEmailContent(String id, String content) {
        Message message = getMessageFromID(id);
        message.setContent(content);
    }

    public Summary getEmailSummary(String id) {
        Message message = getMessageFromID(id);
        Summary summary = new Summary();

        summary.Date(message.getHeader("Date")).
                From(message.getHeader("From")).
                To(message.getHeader("To")).
                CC(message.getHeader("Cc")).
                BCC(message.getHeader("Bcc")).
                Subject(message.getHeader("Subject")).
                Read(message.getHeader("X-Read").length() > 0);

        return summary;
    }

    public void setEmailHeader(String messageId, String key, String value) {
        Message msg = getMessageFromID(messageId);
        msg.setHeader(key, value);
    }

    public String getEmailHeader(String messageId, String key) {
        Message msg = getMessageFromID(messageId);
        return msg.getHeader(key);
    }

    public void markRead(String messageId) {
        setEmailHeader(messageId, "X-Read", "FIXME: Set to NOW()");
    }

    public void markUnread(String messageId) {
        setEmailHeader(messageId, "X-Read", null);
    }

    public void delete(String messageId) {
        // FIXME;
    }

    public void moveTo(String messageId, String folder) {
        Message msg = getMessageFromID(messageId);
        Folder fldr = getFolderFromID(folder);
        fldr.addMessage(msg);
    }

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

        String to = original.getHeader("From");
        String subject = original.getHeader("subject");

        if (!"RE:".equals(subject.substring(0, 3).toUpperCase())) {
            subject = "RE: " + subject;
        }

        replymsg.setContent(replyContent);
        replymsg.setHeader("To", to);
        replymsg.setHeader("Subject", subject);

        return replyid;
    }
}
