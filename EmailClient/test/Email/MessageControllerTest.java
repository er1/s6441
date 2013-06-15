/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Meeting.MeetingSummary;
import Persist.PersistentStorage;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bargavi
 */
public class MessageControllerTest {
    private String mailBoxID;
    private static MessageController controller;
    private static PersistentStorage persistStore;
    private static Mailbox temp;
    public PlainTextMessage msg;
    
    public MessageControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        persistStore = PersistentStorage.getFileSystemStorage("msgCtrl");
        temp = (Mailbox) new FileSystemMailbox("msgCtrl");
        controller = MessageController.getInstance((FileSystemMailbox)temp);
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        msg = new PlainTextMessage();
        msg = PlainTextMessage.parse("MeetingStartTime: 12:39\r\n"
                        + "X-MeetingId: 1e6239bb-f8c6-4a27-96ab-05305d116009\r\n"
                        + "MeetingEndTime: 12:59\r\n"
                        + "MeetingDate: 06/06/2013\r\n"
                        + "Date: 01 Jan 01 1970 GMT\r\n"
                        + "From: toor@example.com\r\n"
                        + "To: alice@example.com\r\n"
                        + "Subject: Hello\r\n"
                        + "\r\n"
                        + "Pls attend the meeting\r\n");
       
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class MessageController.
     */
    @Test
    public void testGetInstance_Mailbox() {

        MessageController expResult = controller;
        MessageController result = MessageController.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstance method, of class MessageController.
     */
    @Test
    public void testGetInstance_0args() {
        MessageController result = MessageController.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getFolderList method, of class MessageController.
     */
    @Test
    public void testGetFolderList() {
        
        String folderId = controller.getInboxFolderId();
        String[] result = controller.getFolderList(folderId);
        assertNotNull(result);
    }

    /**
     * Test of getEmailList method, of class MessageController.
     */
    @Test
    public void testGetEmailList() {
        String folderId = controller.getInboxFolderId();
        String[] result = controller.getEmailList(folderId);
        assertNotNull(result);
    }

    /**
     * Test of getEmailContent method, of class MessageController.
     */
    @Test
    public void testGetEmailContent() {
        System.out.println("getEmailContent");
        String id = "";
        MessageController instance = null;
        String expResult = "";
        String result = controller.getEmailContent(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmailContent method, of class MessageController.
     */
    @Test
    public void testSetEmailContent() {
        System.out.println("setEmailContent");
        String id = "";
        String content = "";
        MessageController instance = null;
        controller.setEmailContent(id, content);
    }

    /**
     * Test of getEmailSummary method, of class MessageController.
     */
    @Test
    public void testGetEmailSummary() {
        System.out.println("getEmailSummary");
        String messageId = "";
        MessageController instance = null;
        Summary expResult = null;
        Summary result = controller.getEmailSummary(messageId);
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmailHeader method, of class MessageController.
     */
    @Test
    public void testSetEmailHeader() {
        System.out.println("setEmailHeader");
        String messageId = "";
        String key = "";
        String value = "";
        MessageController instance = null;
        controller.setEmailHeader(messageId, key, value);
    }

    /**
     * Test of getEmailHeader method, of class MessageController.
     */
    @Test
    public void testGetEmailHeader() {
        System.out.println("getEmailHeader");
        String messageId = "";
        String key = "";
        MessageController instance = null;
        String expResult = "";
        String result = controller.getEmailHeader(messageId, key);
        assertEquals(expResult, result);
    }

    /**
     * Test of markRead method, of class MessageController.
     */
    @Test
    public void testMarkRead() {
        System.out.println("markRead");
        String messageId = "";
        MessageController instance = null;
        controller.markRead(messageId);
    }

    /**
     * Test of markUnread method, of class MessageController.
     */
    @Test
    public void testMarkUnread() {
        System.out.println("markUnread");
        String messageId = "";
        MessageController instance = null;
        controller.markUnread(messageId);
    }

    /**
     * Test of delete method, of class MessageController.
     */
    @Test
    public void testDelete() {
        String messageId = controller.compose();
        msg.setId(messageId);
        controller.delete(messageId);
    }

    /**
     * Test of moveMessageToFolder method, of class MessageController.
     */
    @Test
    public void testMoveMessageToFolder() {
        String messageId = controller.compose();
        msg.setId(messageId);
        String destinationFolderId = controller.getInboxFolderId();
        controller.moveMessageToFolder(messageId, destinationFolderId);
        
    }

    /**
     * Test of copyMessageToFolder method, of class MessageController.
     */
    @Test
    public void testCopyMessageToFolder() {
        
        String messageId = controller.compose();
        msg.setId(messageId);
        String destinationFolderId = controller.getInboxFolderId();
        controller.copyMessageToFolder(messageId, destinationFolderId);
    }

    /**
     * Test of compose method, of class MessageController.
     */
    @Test
    public void testCompose() {
      
        String result = controller.compose();
        assertNotNull(result);
    }

    /**
     * Test of composeFrom method, of class MessageController.
     */
    @Test
    public void testComposeFrom() {
        String templateId = controller.compose();
        msg.setId(templateId);
        String result = controller.composeFrom(templateId);
        assertNotNull(result);
    }

    /**
     * Test of updateDate method, of class MessageController.
     */
    @Test
    public void testUpdateDate() {
        String messageId = controller.compose();
        msg.setId(messageId);
        controller.updateDate(messageId);
    }

    /**
     * Test of reply method, of class MessageController.
     */
    @Test
    public void testReply() {
        String messageId = controller.compose();
        msg.setId(messageId);
        String result = controller.reply(messageId);
        assertNotNull(result);
    }

    /**
     * Test of forward method, of class MessageController.
     */
    @Test
    public void testForward() {
        String messageId = controller.compose();
        msg.setId(messageId);
        String result = controller.forward(messageId);
        assertNotNull(result);
    }

    /**
     * Test of getRootFolderId method, of class MessageController.
     */
    @Test
    public void testGetRootFolderId() {

        String expResult = "msgCtrl";
        String result = controller.getRootFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDraftsFolderId method, of class MessageController.
     */
    @Test
    public void testGetDraftsFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Drafts";
        String result = controller.getDraftsFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInboxFolderId method, of class MessageController.
     */
    @Test
    public void testGetInboxFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Inbox";
        String result = controller.getInboxFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOutboxFolderId method, of class MessageController.
     */
    @Test
    public void testGetOutboxFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Outbox";
        String result = controller.getOutboxFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSentMessagesFolderId method, of class MessageController.
     */
    @Test
    public void testGetSentMessagesFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Sent";
        String result = controller.getSentMessagesFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTrashFolderId method, of class MessageController.
     */
    @Test
    public void testGetTrashFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Trash";
        String result = controller.getTrashFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemplateFolderId method, of class MessageController.
     */
    @Test
    public void testGetTemplateFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Templates";
        String result = controller.getTemplateFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMeetingFolderId method, of class MessageController.
     */
    @Test
    public void testGetMeetingFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Meetings";
        String result = controller.getMeetingFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFolderName method, of class MessageController.
     */
    @Test
    public void testGetFolderName() {
        
        String expResult = "msgCtrl";
        String result = controller.getFolderName("msgCtrl");
        assertEquals(expResult, result);
    }

    /**
     * Test of newFolder method, of class MessageController.
     */
    @Test
    public void testNewFolder() {
        
        String in = controller.getInboxFolderId();
        String name = "new";
       
        controller.newFolder(in, name);
        String result = "msgCtrl" + File.separator + "Inbox" + File.separator + name;
        boolean res = persistStore.isFolderExists(result);
        assertTrue(res);
    }

    /**
     * Test of deletefolder method, of class MessageController.
     */
    @Test
    public void testDeletefolder() {
        
        String in = controller.getInboxFolderId();
        controller.deletefolder(in);
        boolean res = persistStore.isFolderExists("msgCtrl" + File.separator + "Inbox");
        assertEquals(false,res);
    }

    /**
     * Test of moveFolder method, of class MessageController.
     */
    @Test
    public void testMoveFolder() {
        System.out.println("moveFolder");
        String sourcePath = "";
        String destinationPath = "";
        MessageController instance = null;
        controller.moveFolder(sourcePath, destinationPath);
    }

    /**
     * Test of doSendRecieve method, of class MessageController.
     */
    @Test
    public void testDoSendRecieve() {
        System.out.println("doSendRecieve");
        MessageController instance = null;
        controller.doSendRecieve();
    }

    /**
     * Test of getMeetingsFolderId method, of class MessageController.
     */
    @Test
    public void testGetMeetingsFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Meetings";
        String result = controller.getMeetingsFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemplatesFolderId method, of class MessageController.
     */
    @Test
    public void testGetTemplatesFolderId() {
        
        String expResult = "msgCtrl" + File.separator + "Templates";
        String result = controller.getTemplatesFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of createMeeting method, of class MessageController.
     */
    @Test
    public void testCreateMeeting() {
      
        String result = controller.createMeeting();
        assertNotNull(result);
    }

    /**
     * Test of getRuleFromId method, of class MessageController.
     */
    @Test
    public void testGetRuleFromId() {
        System.out.println("getRuleFromId");
        String ruleId = "";
        MessageController instance = null;
        FilterRule expResult = null;
        FilterRule result = controller.getRuleFromId(ruleId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdfromRule method, of class MessageController.
     */
    @Test
    public void testGetIdfromRule() {
        FilterRule rule = new FilterRule();
        rule.setRuleId("Rule1");
        String result = controller.getIdfromRule(rule);
        assertEquals("Rule1", result);
    }

    /**
     * Test of loadRules method, of class MessageController.
     */
    @Test
    public void testLoadRules() {
        MessageController instance = null;
        controller.loadRules();
    }

    /**
     * Test of addRule method, of class MessageController.
     */
    @Test
    public void testAddRule() {
        System.out.println("addRule");
        FilterRule rule = null;
        MessageController instance = null;
        controller.addRule(rule);
    }

    /**
     * Test of deleteRule method, of class MessageController.
     */
    @Test
    public void testDeleteRule() {
        System.out.println("deleteRule");
        int ruleId = 0;
        MessageController instance = null;
        controller.deleteRule(ruleId);
    }

    /**
     * Test of getRuleList method, of class MessageController.
     */
    @Test
    public void testGetRuleList() {
        String[] result = controller.getRuleList();
        assertNotNull(result);
    }

    /**
     * Test of getRulesCount method, of class MessageController.
     */
    @Test
    public void testGetRulesCount() {
        System.out.println("getRulesCount");
        MessageController instance = null;
        int expResult = 0;
        int result = controller.getRulesCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of sendMeeting method, of class MessageController.
     */
    @Test
    public void testSendMeeting() {
        String messageId = controller.compose();
        msg.setId(messageId);
        controller.sendMeeting(messageId);
    }

    /**
     * Test of getMeetingSummary method, of class MessageController.
     */
    @Test
    public void testGetMeetingSummary() {
        
        String messageId = controller.compose();
        msg.setId(messageId);
        MeetingSummary result = controller.getMeetingSummary(messageId);
        assertNotNull(result);
    }

    /**
     * Test of saveRules method, of class MessageController.
     */
    @Test
    public void testSaveRules() {
        System.out.println("saveRules");
        MessageController instance = null;
        controller.saveRules();
    }

    /**
     * Test of checkFolderExists method, of class MessageController.
     */
    @Test
    public void testCheckFolderExists() {

        String folderPath = "msgCtrl";
        boolean expResult = true;
        boolean result = controller.checkFolderExists(folderPath);
        assertEquals(true, result);
    }
}