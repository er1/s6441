package Email;

import Meeting.MeetingSummary;
import Persist.MessageTransfer;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.Util;

/**
 * Message controller class
 */
public class MessageController extends Observable {

    private static MessageController instance = null;
    Mailbox store;
    Rules rules;
    HashMap<String, Message> messageLookup = Util.newHashMap();
    HashMap<String, Folder> folderLookup = Util.newHashMap();
    HashMap<String, FilterRule> ruleLookup = Util.newHashMap();
    final DateFormat controllerDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
    final DateFormat meetingDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    /**
     *
     * @param messageId
     */
    public void acceptMeeting(String messageId) {
        Message message = getMessageFromId(messageId);
        store.getMeetings().addMessage(message);
        sendAcceptToSender(message);
    }

    /**
     *
     * @param messageId
     */
    public void declineMeeting(String messageId) {
        Message message = getMessageFromId(messageId);
        store.getTrash().addMessage(message);
        sendDeclineToSender(message);
    }

    /**
     *
     * @param messageid
     * @return
     */
    public boolean isThisAMeeting(String messageid) {
        Message newMsg = getMessageFromId(messageid);
        if (!"".equals(newMsg.getHeaderValue("X-MeetingId"))) {
            return true;
        }
        return false;
    }

    private void sendAcceptToSender(Message original) {
        String replyid = compose();
        updateDate(replyid);
        Message replymsg = getMessageFromId(replyid);
        replymsg.setHeader("X-MeetingId", original.getHeaderValue("X-MeetingId"));
        replymsg.setHeader("To", original.getHeaderValue("From"));

        replymsg.setHeader("MeetingDate", original.getHeaderValue("MeetingDate"));
        replymsg.setHeader("MeetingStartTime", original.getHeaderValue("MeetingStartTime"));
        replymsg.setHeader("MeetingEndTime", original.getHeaderValue("MeetingEndTime"));

        String userId = store.getUserId();
        replymsg.setHeader("Subject", "ACCEPTED: " + original.getHeaderValue("Subject"));
        replymsg.setHeader("X-Accepted", userId);

        replymsg.setContent (original.getContent());

        replymsg.setHeader("X-Response", "ACCEPT");
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);
        moveMessageToFolder(replyid, getOutboxFolderId());
    }

    private void sendDeclineToSender(Message original) {
        String replyid = compose();
        updateDate(replyid);
        Message replymsg = getMessageFromId(replyid);
        replymsg.setHeader("X-MeetingId", original.getHeaderValue("X-MeetingId"));
        replymsg.setHeader("To", original.getHeaderValue("From"));

        replymsg.setHeader("MeetingDate", original.getHeaderValue("MeetingDate"));
        replymsg.setHeader("MeetingStartTime", original.getHeaderValue("MeetingStartTime"));
        replymsg.setHeader("MeetingEndTime", original.getHeaderValue("MeetingEndTime"));

        String userId = store.getUserId();
        replymsg.setHeader("Subject", "DECLINED: " + original.getHeaderValue("Subject"));
        replymsg.setHeader("X-Declined", userId);
        replymsg.setContent (original.getContent());
        replymsg.setHeader("X-Response", "DECLINE");
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);
        moveMessageToFolder(replyid, getOutboxFolderId());
    }

    /**
     * Generate a new ID and return it as string
     */
    private String generateNewId() {
        UUID messageId = UUID.randomUUID();
        return messageId.toString();
    }

    private void handleMeetingResponse(Message newMsg) {
        String id = getMeetingFolderId() + File.separator + newMsg.getHeaderValue("X-MeetingId");

        if (responseToExistingMeeting(id)) {
            String response = newMsg.getHeaderValue("X-Response");
            Message existingMeeting = getMessageFromId(id);
            String userid = "";
            if (response.equals("ACCEPT")) {
                String accepted = getMessageFromId(id).getHeaderValue("X-Accepted");
                if (accepted.isEmpty()) {
                    userid = newMsg.getHeaderValue("X-Accepted");
                    accepted += userid;
                } else {
                    accepted += ", " + userid;
                }
                existingMeeting.setHeader("X-Accepted", accepted);
            } else {
                String declined = getMessageFromId(id).getHeaderValue("X-Declined");
                if (declined.isEmpty()) {
                    userid = newMsg.getHeaderValue("X-Declined");
                    declined += userid;
                } else {
                    declined += ", " + userid;
                }
                existingMeeting.setHeader("X-Declined", declined);
            }
            store.getMeetings().addMessage(existingMeeting);
        }
    }

    private boolean responseToExistingMeeting(String id) {
        String[] meeting_ids = getEmailList(getMeetingsFolderId());
        for (int i = 0; i < meeting_ids.length; i++) {
            if (meeting_ids[i].contains(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Enum UpdateType
     */
    public enum UpdateType {

        /**
         * Variable to update both for folders and messages
         */
        FOLDERS_AND_MESSAGES,
        /**
         * Variable to update only for messages
         */
        MESSAGES
    };

    /**
     * MessageController Constructor
     *
     * @param messagestore
     */
    private MessageController(Mailbox messagestore) {
        store = messagestore;

    }

    /**
     * Get the instance ofMessageController
     *
     * @param messagestore
     * @return instance
     */
    static public MessageController getInstance(Mailbox messagestore) {
        if (instance == null) {
            instance = new MessageController(messagestore);
        }
        return instance;

    }

    /**
     * Get the instance ofMessageController
     *
     * @return instance
     */
    static public MessageController getInstance() {
        return instance;
    }

    private Message getMessageFromId(String messageId) {
        return messageLookup.get(messageId);
    }

    private Folder getFolderFromId(String folderId) {
        return folderLookup.get(folderId);
    }

    private String getIdfromMessage(Message message) {
        String id = message.getId();
        messageLookup.put(id, message);
        return id;
    }

    private String getIdfromFolder(Folder folder) {
        String id = folder.getId();
        folderLookup.put(id, folder);
        return id;
    }

    /**
     * Get sub folders from a folder
     *
     * @param folderId
     * @return Array of string id's of all the sub folders inside our folder
     */
    public String[] getFolderList(String folderId) {
        Folder fldr = getFolderFromId(folderId);
        ArrayList<Folder> set = fldr.getSubfolders();
        String[] ids = new String[set.size()];

        int index = 0;
        for (Folder subfolder : set) {
            ids[index++] = getIdfromFolder(subfolder);
        }

        return ids;
    }

    /**
     * Get email list from a folder
     *
     * @param folderId Id of folder
     * @return Array of string id's of all the messages inside our folder
     */
    public String[] getEmailList(String folderId) {
        Folder fldr = getFolderFromId(folderId);
        if (fldr == null) {
            return new String[0];
        }
        ArrayList<Message> set = fldr.getMessages();
        ArrayList<String> ids = new ArrayList<String>();

        Collections.sort(set, new Comparator<Message>() {
            @Override
            public int compare(Message m1, Message m2) {
                Summary a, b;
                a = new Summary(m2);
                b = new Summary(m1);
                Date da = new Date(0);
                Date db = new Date(0);

                try {
                    da = controllerDateFormat.parse(a.getDate());
                } catch (ParseException ex) {
                }
                try {
                    db = controllerDateFormat.parse(b.getDate());
                } catch (ParseException ex) {
                }
                try {
                    da = meetingDateFormat.parse(m2.getHeaderValue("MeetingDate"));
                } catch (ParseException ex) {
                }
                try {
                    db = meetingDateFormat.parse(m1.getHeaderValue("MeetingDate"));
                } catch (ParseException ex) {
                }
                
                return da.compareTo(db);                
            }
        });

        for (Message message : set) {
            ids.add(getIdfromMessage(message));
        }

        return ids.toArray(new String[ids.size()]);
    }

    /**
     *
     * @param folderId
     */
    public void deleteAllMails(String folderId) {
        Folder fldr = getFolderFromId(folderId);
        ArrayList<Message> set;
        set = fldr.getMessages();

        for (Message message : set) {
            String id = getIdfromMessage(message);
            if (folderId.equals(getTrashFolderId())) {
                delete(id);
            } else {
                moveMessageToFolder(id, getTrashFolderId());
            }
        }
    }

    /**
     * Get email content
     *
     * @param id
     * @return content
     */
    public String getEmailContent(String id) {
        try {
            Message message = getMessageFromId(id);
            return message.getContent();
        } catch (NullPointerException e) {
            return new String();
        }
    }

    /**
     * Set email content
     *
     * @param id
     * @param content
     */
    public void setEmailContent(String id, String content) {
        Message message = getMessageFromId(id);
        if (message == null) {
            return;
        }
        message.setContent(content);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);

    }

    /**
     * Get email summary
     *
     * @param messageId Id of Message
     * @return Summary of the Message
     */
    public Summary getEmailSummary(String messageId) {
        Message message = getMessageFromId(messageId);
        if (message == null) {
            return null;
        }


        Summary summary = new Summary(message);

        return summary;
    }

    /**
     * Set email header
     *
     * @param messageId Message Id
     * @param key Key
     * @param value Value
     */
    public void setEmailHeader(String messageId, String key, String value) {
        Message msg = getMessageFromId(messageId);
        if (msg == null) {
            return;
        }

        msg.setHeader(key, value);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);

    }

    /**
     * Get email header
     *
     * @param messageId Message Id
     * @param key Key for which we want a value
     * @return Value of particular key within message header
     */
    public String getEmailHeader(String messageId, String key) {
        Message msg = getMessageFromId(messageId);

        if (msg == null) {
            return "";
        }

        return msg.getHeaderValue(key);
    }

    /**
     * Mark message as read
     *
     * @param messageId Message Id
     */
    public void markRead(String messageId) {
        if (getEmailHeader(messageId, "X-Read").length() > 0) {
            return;
        }
        setEmailHeader(messageId, "X-Read", "FIXME: Set to NOW()");

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);
    }

    /**
     * Mark message as unread
     *
     * @param messageId Message Id
     */
    public void markUnread(String messageId) {
        String read = getEmailHeader(messageId, "X-Read");
        if (read == null || read.length() == 0) {
            return;
        }

        setEmailHeader(messageId, "X-Read", null);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);
    }

    /**
     * Delete message for given messageId
     *
     * @param messageId Message Id
     */
    public void delete(String messageId) {

        Message msg = this.getMessageFromId(messageId);

        // FIXME: we assume this is only getting called from the trash
        store.getTrash().deleteMessage(msg);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);

    }

    /**
     * Move message to specified destination folder
     *
     * @param messageId Message Id
     * @param destinationFolderId Destination Folder Id
     */
    public void moveMessageToFolder(String messageId, String destinationFolderId) {
        Message msg = getMessageFromId(messageId);
        Folder destination = getFolderFromId(destinationFolderId);
        destination.addMessage(msg);

        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);
    }

    /**
     * Copy given message to specified folder
     *
     * @param messageId
     * @param folderId
     */
    public void copyMessageToFolder(String messageId, String folderId) {
        Message msg = getMessageFromId(messageId);
        Folder destination = getFolderFromId(folderId);
        destination.addMessageCopy(msg);

        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);
    }

    /**
     * Compose a new message
     *
     * @return message
     */
    public String compose() {
        Message newMsg = new PlainTextMessage();
        UUID messageId = UUID.randomUUID();
        newMsg.setId(messageId.toString());

        String id = getIdfromMessage(newMsg);
        markRead(id);
        return id;
    }

    /**
     * function to say compose a mail from the given template
     *
     * @param template
     * @return message id of composed mail
     */
    public String composeFrom(String template) {
        String id = compose();

        Message temp = this.getMessageFromId(template);
        Message msg = this.getMessageFromId(id);

        msg.setContent(temp.getContent());
        msg.setHeader("Subject", temp.getHeaderValue("Subject"));
        msg.setHeader("To", temp.getHeaderValue("To"));

        return id;
    }

    /**
     * Update the date
     *
     * @param messageid
     */
    public void updateDate(String messageid) {
        Message msg = this.getMessageFromId(messageid);
        Date date = new Date();
        msg.setHeader("Date", controllerDateFormat.format(date));

        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);
    }

    /**
     * Create Reply content from the original message
     *
     * @param originalMessage
     * @return reply id
     */
    public String reply(String originalMessage) {
        // create a new message
        String replyid = compose();
        updateDate(replyid);

        Message replymsg = getMessageFromId(replyid);

        // get the original message and use it to create the reply content
        Message original = getMessageFromId(originalMessage);

        String replyContent = original.getContent();
        replyContent = "\r\n\r\n" + replyContent;
        replyContent = replyContent.replaceAll("\n", "\n> ");

        // get the headers based on the original message
        String to = original.getHeaderValue("From");
        String subject = original.getHeaderValue("Subject");

        // add RE to the subject if it is not already there
        if (subject.length() < 3 || !"RE:".equals(subject.substring(0, 3).toUpperCase())) {
            subject = "RE: " + subject;
        }

        // set the headers and content of the reply
        replymsg.setContent(replyContent);
        replymsg.setHeader("To", to);
        replymsg.setHeader("Subject", subject);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);

        return replyid;
    }

    /**
     * Create a forward content from current message
     *
     * @param currentMessage
     * @return
     */
    public String forward(String currentMessage) {
        // create a new message
        String forwardid = compose();
        updateDate(forwardid);

        Message forwardmsg = getMessageFromId(forwardid);

        // get the original message and use it to create the reply content
        Message original = getMessageFromId(currentMessage);

        String forwardContent = original.getContent();
        forwardContent = "\r\n\r\n" + forwardContent;
        forwardContent = forwardContent.replaceAll("\n", "\n> ");

        // get the headers based on the original message
        String subject = original.getHeaderValue("Subject");

        // add RE to the subject if it is not already there
        if (subject.length() < 4 || !"FWD:".equals(subject.substring(0, 4).toUpperCase())) {
            subject = "Fwd: " + subject;
        }

        // set the headers and content of the reply
        forwardmsg.setContent(forwardContent);
        forwardmsg.setHeader("Subject", subject);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);

        return forwardid;
    }

    // get particular folder ids
    /**
     * Get root folder id
     *
     * @return id
     */
    public String getRootFolderId() {
        return getIdfromFolder(store);
    }

    /**
     * Get drafts folder id
     *
     * @return id
     */
    public String getDraftsFolderId() {
        return getIdfromFolder(store.getDrafts());
    }

    /**
     * Get inbox folder id
     *
     * @return id
     */
    public String getInboxFolderId() {
        return getIdfromFolder(store.getInbox());
    }

    /**
     * Get outbox folder id
     *
     * @return id
     */
    public String getOutboxFolderId() {
        return getIdfromFolder(store.getOutbox());
    }

    /**
     * Get sent messages folder id
     *
     * @return id
     */
    public String getSentMessagesFolderId() {
        return getIdfromFolder(store.getSentMessages());
    }

    /**
     * Get trash folder id
     *
     * @return id
     */
    public String getTrashFolderId() {
        return getIdfromFolder(store.getTrash());
    }

    /**
     * Get template folder id
     *
     * @return id
     */
    public String getTemplateFolderId() {
        return getIdfromFolder(store.getTemplates());
    }

    /**
     * Get template folder id
     *
     * @return id
     */
    public String getMeetingFolderId() {
        return getIdfromFolder(store.getMeetings());
    }

    /**
     * Get the name of a folder
     *
     * @param folder
     * @return folderName
     */
    public String getFolderName(String folder) {
        return getFolderFromId(folder).getName();
    }

    /**
     * Create a new folder
     *
     * @param in
     * @param name
     */
    public void newFolder(String in, String name) {
        Folder folder = getFolderFromId(in);
        folder.createFolder(name);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.FOLDERS_AND_MESSAGES);
    }

    /**
     * Delete a folder selected
     *
     * @param selected
     */
    public void deletefolder(String selected) {
        Folder folder;
        folder = getFolderFromId(selected);
        store.deleteFolder(folder);

        this.setChanged();
        this.notifyObservers(UpdateType.FOLDERS_AND_MESSAGES);
    }

    /**
     * Move folder form source to destination
     *
     * @param sourcePath
     * @param destinationPath
     */
    public void moveFolder(String sourcePath, String destinationPath) {
        if (sourcePath.equals(destinationPath)) {
            return;
        }

        Folder source = this.getFolderFromId(sourcePath);
        Folder dest = this.getFolderFromId(destinationPath);
        source.moveFolder(dest);

        this.setChanged();
        this.notifyObservers(UpdateType.FOLDERS_AND_MESSAGES);
    }

    /**
     * Function to refresh for getting and sending messages
     */
    public void doSendRecieve() {
        Folder outbox = store.getOutbox();
        Folder sent = store.getSentMessages();
        Folder inbox = store.getInbox();

        String userId = store.getUserId();

        MessageTransfer transfer = MessageTransfer.getInstance();

        ArrayList<Message> outbound = store.getOutbox().getMessages();
        for (Message out : outbound) {
            out.setHeader("From", store.getUserId());
            out.setHeader("X-Read", null); // mark as unread for the recipient

            PlainTextMessage msg = (PlainTextMessage) out; // FIXME (this is pretty derpy)
            String content = msg.serialize();

            for (String to : out.getHeaderValue("To").split(",")) {
                to = to.trim();

                Pattern mailpat = Pattern.compile("<(.*)>");
                Matcher mailmat = mailpat.matcher(to);
                if (mailmat.find()) {
                    to = mailmat.group(1).trim();
                }

                Pattern userpat = Pattern.compile("([^@]+)@.*");
                Matcher usermat = userpat.matcher(to);

                if (usermat.find()) {
                    to = usermat.group(1).trim();
                }
                try {
                    transfer.sendMessageTo(to, content);
                } catch (IOException ex) {
                    // protocol or connection error here
                } catch (IllegalArgumentException ex) {
                    // send error caught here
                }
            }
            sent.addMessage(out);
            this.getIdfromMessage(out);
            this.setChanged();
        }

        try {
            while (transfer.MessageExistFor(userId)) {
                String message = transfer.getMessageFor(userId);
                Message newMsg = PlainTextMessage.parse(message);
                if (newMsg == null) {
                    continue;
                }
                //Is it a meeting?
                if (!"".equals(newMsg.getHeaderValue("X-MeetingId"))) {
                    //Is it a Response to a meeting?
                    if (!"".equals(newMsg.getHeaderValue("X-Response"))) {
                        newMsg.setId(generateNewId());
                        handleMeetingResponse(newMsg);
                    } else {
                        newMsg.setId(newMsg.getHeaderValue("X-MeetingId"));
                        //TODO What if a meeting with the same ID already exists?
                        //store.getMeetings().addMessage(newMsg);
                    }
                } else {
                    UUID messageId = UUID.randomUUID();
                    newMsg.setId(messageId.toString());
                }
                inbox.addMessage(newMsg);
                this.setChanged();
            }
        } catch (IOException ex) {
            // protocol or connection error here
        }

        store.sync();

        // update anyone waiting on updates
        this.notifyObservers(UpdateType.FOLDERS_AND_MESSAGES);
    }

    /**
     * Get the meetings folder id
     *
     * @return id
     */
    public String getMeetingsFolderId() {
        return getIdfromFolder(store.getMeetings());
    }

    /**
     * Get the templates folder id
     *
     * @return id
     */
    public String getTemplatesFolderId() {
        return getIdfromFolder(store.getTemplates());
    }

    /**
     * Create a new meeting
     *
     * @return meeting id
     */
    public String createMeeting() {
        Message newMeeting = new PlainTextMessage();
        UUID messageId = UUID.randomUUID();
        newMeeting.setId(messageId.toString());
        newMeeting.setHeader("X-MeetingId", messageId.toString());
        return getIdfromMessage(newMeeting);
    }

    /**
     * Get the rule from rule id
     *
     * @param ruleId
     * @return object of FilterRule
     */
    public FilterRule getRuleFromId(String ruleId) {
        return ruleLookup.get(ruleId);
    }

    /**
     * Get the id from given rule
     *
     * @param rule
     * @return id
     */
    public String getIdfromRule(FilterRule rule) {
        String id = rule.getRuleId();
        ruleLookup.put(id, rule);
        return id;
    }

    /**
     * Load rules
     */
    public void loadRules() {
        rules = Rules.getInstance();
    }

    /**
     * Add a given rule
     *
     * @param rule
     */
    public void addRule(FilterRule rule) {
        rules.addRule(rule);
    }

    /**
     * Delete a rule
     *
     * @param ruleId
     */
    public void deleteRule(int ruleId) {
        rules.deleteRule(ruleId);
    }

    /**
     * Get the rules list
     *
     * @return ids of all the rules in rules list
     */
    public String[] getRuleList() {
        String[] ids;

        try {

            ArrayList<FilterRule> set;
            set = rules.getListOfRules();

            ids = new String[set.size()];

            int index = 0;
            for (FilterRule rule : set) {
                ids[index++] = getIdfromRule(rule);
            }
        } catch (Exception ex) {
            ids = new String[0];
        }
        return ids;
    }

    /**
     * Get the rules count
     *
     * @return size
     */
    public int getRulesCount() {
        return rules.getListOfRules().size();
    }

    /**
     * Move a rule up one rule
     *
     * @param ruleId
     * @param selectedIndex
     */
    public void moveUpRule(String ruleId, int selectedIndex) {
        rules.moveUpRule(ruleId, selectedIndex);
    }

    /**
     * Move a rule down one rule
     *
     * @param ruleId
     * @param selectedIndex
     */
    public void moveDownRule(String ruleId, int selectedIndex) {
        rules.moveDownRule(ruleId, selectedIndex);
    }

    /**
     * Send a meeting request
     *
     * @param messageId
     */
    public void sendMeeting(String messageId) {
        moveMessageToFolder(messageId, getMeetingsFolderId());
        copyMessageToFolder(messageId, getOutboxFolderId());
    }

    /**
     * Get meeting summary of particular meeting message
     *
     * @param messageId
     * @return summary
     */
    public MeetingSummary getMeetingSummary(String messageId) {
        Message message = getMessageFromId(messageId);
        MeetingSummary summary = new MeetingSummary(message);

        return summary;
    }

    /**
     * Save the rules
     */
    public void saveRules() {
        rules.saveRules();
    }

    /**
     * Check given folder exists in file system
     *
     * @param folderPath
     * @return True/False
     */
    public boolean checkFolderExists(String folderPath) {
        return rules.isFolderExists(folderPath);
    }

    /**
     * Create a reply content for selected meeting
     *
     * @param originalMessage
     * @return meeting id
     */
    public String replyMeeting(String originalMessage) {
        // create a new message
        String replyid = createMeeting();
        updateDate(replyid);

        Message replymsg = getMessageFromId(replyid);

        // get the original message and use it to create the reply content
        Message original = getMessageFromId(originalMessage);

        String replyContent = original.getContent();
        //replyContent = "\r\n\r\n" + replyContent;
        //replyContent = replyContent.replaceAll("\n", "\n> ");

        // get the headers based on the original message
        String to = original.getHeaderValue("From");
        String subject = original.getHeaderValue("Subject");

        // add RE to the subject if it is not already there
        if (subject.length() < 3 || !"RE:".equals(subject.substring(0, 3).toUpperCase())) {
            subject = "RE: " + subject;
        }

        // set the headers and content of the reply
        replymsg.setContent(replyContent);
        replymsg.setHeader("To", to);
        replymsg.setHeader("Subject", subject);
        replymsg.setHeader("MeetingDate", original.getHeaderValue("MeetingDate"));
        replymsg.setHeader("MeetingStartTime", original.getHeaderValue("MeetingStartTime"));
        replymsg.setHeader("MeetingEndTime", original.getHeaderValue("MeetingEndTime"));
        replymsg.setHeader("X-MeetingId", original.getHeaderValue("X-MeetingId"));

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);

        return replyid;
    }

    /**
     * Create a forward content for selected meeting
     *
     * @param currentMessage
     * @return meeting id
     */
    public String forwardMeeting(String currentMessage) {
        // get the original message and use it to create the reply content
        Message original = getMessageFromId(currentMessage);

        // set the headers and content of the reply
        updateDate(currentMessage);

        // update anyone waiting on updates
        this.setChanged();
        this.notifyObservers(UpdateType.MESSAGES);

        return currentMessage;
    }
}
