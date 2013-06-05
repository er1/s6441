/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Meeting.MeetingSummary;
import Persist.PersistentStorage;
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

    public MessageControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        persistStore = PersistentStorage.getFileSystemStorage("msgCtrl");
        temp = (Mailbox) new FileSystemMailbox("msgCtrl");
        controller = MessageController.getInstance(temp);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mailBoxID = "test";
        this.persistStore = PersistentStorage.getFileSystemStorage(mailBoxID);
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
        System.out.println("getInstance");
        MessageController expResult = null;
        MessageController result = MessageController.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getFolderList method, of class MessageController.
     */
    @Test
    public void testGetFolderList() {
        System.out.println("getFolderList");
        String folderId = "";
        String[] expResult = null;
        String[] result = controller.getFolderList(folderId);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getEmailList method, of class MessageController.
     */
    @Test
    public void testGetEmailList() {
        System.out.println("getEmailList");
        String folderId = "";
        MessageController instance = null;
        String[] expResult = null;
        String[] result = controller.getEmailList(folderId);
        assertArrayEquals(expResult, result);
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
        System.out.println("delete");
        String messageId = "";
        MessageController instance = null;
        controller.delete(messageId);
    }

    /**
     * Test of moveMessageToFolder method, of class MessageController.
     */
    @Test
    public void testMoveMessageToFolder() {
        System.out.println("moveMessageToFolder");
        String messageId = "";
        String destinationFolderId = "";
        MessageController instance = null;
        controller.moveMessageToFolder(messageId, destinationFolderId);
    }

    /**
     * Test of copyMessageToFolder method, of class MessageController.
     */
    @Test
    public void testCopyMessageToFolder() {
        System.out.println("copyMessageToFolder");
        String messageId = "";
        String folderId = "";
        MessageController instance = null;
        controller.copyMessageToFolder(messageId, folderId);
    }

    /**
     * Test of compose method, of class MessageController.
     */
    @Test
    public void testCompose() {
        System.out.println("compose");
        MessageController instance = null;
        String expResult = "";
        String result = controller.compose();
        assertEquals(expResult, result);
    }

    /**
     * Test of composeFrom method, of class MessageController.
     */
    @Test
    public void testComposeFrom() {
        System.out.println("composeFrom");
        String template = "";
        MessageController instance = null;
        String expResult = "";
        String result = controller.composeFrom(template);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateDate method, of class MessageController.
     */
    @Test
    public void testUpdateDate() {
        System.out.println("updateDate");
        String messageid = "";
        MessageController instance = null;
        controller.updateDate(messageid);
    }

    /**
     * Test of reply method, of class MessageController.
     */
    @Test
    public void testReply() {
        System.out.println("reply");
        String originalMessage = "";
        MessageController instance = null;
        String expResult = "";
        String result = controller.reply(originalMessage);
        assertEquals(expResult, result);
    }

    /**
     * Test of forward method, of class MessageController.
     */
    @Test
    public void testForward() {
        System.out.println("forward");
        String currentMessage = "";
        MessageController instance = null;
        String expResult = "";
        String result = controller.forward(currentMessage);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRootFolderId method, of class MessageController.
     */
    @Test
    public void testGetRootFolderId() {
        System.out.println("getRootFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getRootFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDraftsFolderId method, of class MessageController.
     */
    @Test
    public void testGetDraftsFolderId() {
        System.out.println("getDraftsFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getDraftsFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInboxFolderId method, of class MessageController.
     */
    @Test
    public void testGetInboxFolderId() {
        System.out.println("getInboxFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getInboxFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOutboxFolderId method, of class MessageController.
     */
    @Test
    public void testGetOutboxFolderId() {
        System.out.println("getOutboxFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getOutboxFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSentMessagesFolderId method, of class MessageController.
     */
    @Test
    public void testGetSentMessagesFolderId() {
        System.out.println("getSentMessagesFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getSentMessagesFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTrashFolderId method, of class MessageController.
     */
    @Test
    public void testGetTrashFolderId() {
        System.out.println("getTrashFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getTrashFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemplateFolderId method, of class MessageController.
     */
    @Test
    public void testGetTemplateFolderId() {
        System.out.println("getTemplateFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getTemplateFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMeetingFolderId method, of class MessageController.
     */
    @Test
    public void testGetMeetingFolderId() {
        System.out.println("getMeetingFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getMeetingFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFolderName method, of class MessageController.
     */
    @Test
    public void testGetFolderName() {
        System.out.println("getFolderName");
        String folder = "";
        MessageController instance = null;
        String expResult = "";
        String result = controller.getFolderName(folder);
        assertEquals(expResult, result);
    }

    /**
     * Test of newFolder method, of class MessageController.
     */
    @Test
    public void testNewFolder() {
        System.out.println("newFolder");
        String in = "";
        String name = "";
        MessageController instance = null;
        controller.newFolder(in, name);
    }

    /**
     * Test of deletefolder method, of class MessageController.
     */
    @Test
    public void testDeletefolder() {
        System.out.println("deletefolder");
        String selected = "";
        MessageController instance = null;
        controller.deletefolder(selected);
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
        System.out.println("getMeetingsFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getMeetingsFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemplatesFolderId method, of class MessageController.
     */
    @Test
    public void testGetTemplatesFolderId() {
        System.out.println("getTemplatesFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = controller.getTemplatesFolderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of createMeeting method, of class MessageController.
     */
    @Test
    public void testCreateMeeting() {
        System.out.println("createMeeting");
        MessageController instance = null;
        String expResult = "";
        String result = controller.createMeeting();
        assertEquals(expResult, result);
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
        System.out.println("getIdfromRule");
        FilterRule rule = null;
        MessageController instance = null;
        String expResult = "";
        String result = controller.getIdfromRule(rule);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadRules method, of class MessageController.
     */
    @Test
    public void testLoadRules() {
        System.out.println("loadRules");
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
        System.out.println("getRuleList");
        MessageController instance = null;
        String[] expResult = null;
        String[] result = controller.getRuleList();
        assertArrayEquals(expResult, result);
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
        System.out.println("sendMeeting");
        String messageId = "";
        MessageController instance = null;
        controller.sendMeeting(messageId);
    }

    /**
     * Test of getMeetingSummary method, of class MessageController.
     */
    @Test
    public void testGetMeetingSummary() {
        System.out.println("getMeetingSummary");
        String messageId = "";
        MessageController instance = null;
        MeetingSummary expResult = null;
        MeetingSummary result = controller.getMeetingSummary(messageId);
        assertEquals(expResult, result);
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
        System.out.println("checkFolderExists");
        String folderPath = "";
        MessageController instance = null;
        boolean expResult = false;
        boolean result = controller.checkFolderExists(folderPath);
        assertEquals(expResult, result);
    }
}