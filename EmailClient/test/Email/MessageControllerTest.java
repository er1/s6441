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
    private MessageController controller;
    private PersistentStorage persistStore;
    
    public MessageControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFolderList method, of class MessageController.
     */
    @Test
    public void testGetFolderList() {
        System.out.println("getFolderList");
        String folderId = "";
        MessageController instance = null;
        String[] expResult = null;
        String[] result = instance.getFolderList(folderId);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String[] result = instance.getEmailList(folderId);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = instance.getEmailContent(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        instance.setEmailContent(id, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        Summary result = instance.getEmailSummary(messageId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        instance.setEmailHeader(messageId, key, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = instance.getEmailHeader(messageId, key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markRead method, of class MessageController.
     */
    @Test
    public void testMarkRead() {
        System.out.println("markRead");
        String messageId = "";
        MessageController instance = null;
        instance.markRead(messageId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markUnread method, of class MessageController.
     */
    @Test
    public void testMarkUnread() {
        System.out.println("markUnread");
        String messageId = "";
        MessageController instance = null;
        instance.markUnread(messageId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class MessageController.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String messageId = "";
        MessageController instance = null;
        instance.delete(messageId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        instance.moveMessageToFolder(messageId, destinationFolderId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        instance.copyMessageToFolder(messageId, folderId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compose method, of class MessageController.
     */
    @Test
    public void testCompose() {
        System.out.println("compose");
        MessageController instance = null;
        String expResult = "";
        String result = instance.compose();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = instance.composeFrom(template);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDate method, of class MessageController.
     */
    @Test
    public void testUpdateDate() {
        System.out.println("updateDate");
        String messageid = "";
        MessageController instance = null;
        instance.updateDate(messageid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = instance.reply(originalMessage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = instance.forward(currentMessage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRootFolderId method, of class MessageController.
     */
    @Test
    public void testGetRootFolderId() {
        System.out.println("getRootFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getRootFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDraftsFolderId method, of class MessageController.
     */
    @Test
    public void testGetDraftsFolderId() {
        System.out.println("getDraftsFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getDraftsFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInboxFolderId method, of class MessageController.
     */
    @Test
    public void testGetInboxFolderId() {
        System.out.println("getInboxFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getInboxFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutboxFolderId method, of class MessageController.
     */
    @Test
    public void testGetOutboxFolderId() {
        System.out.println("getOutboxFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getOutboxFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSentMessagesFolderId method, of class MessageController.
     */
    @Test
    public void testGetSentMessagesFolderId() {
        System.out.println("getSentMessagesFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getSentMessagesFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrashFolderId method, of class MessageController.
     */
    @Test
    public void testGetTrashFolderId() {
        System.out.println("getTrashFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getTrashFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplateFolderId method, of class MessageController.
     */
    @Test
    public void testGetTemplateFolderId() {
        System.out.println("getTemplateFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getTemplateFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMeetingFolderId method, of class MessageController.
     */
    @Test
    public void testGetMeetingFolderId() {
        System.out.println("getMeetingFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getMeetingFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = instance.getFolderName(folder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        instance.newFolder(in, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletefolder method, of class MessageController.
     */
    @Test
    public void testDeletefolder() {
        System.out.println("deletefolder");
        String selected = "";
        MessageController instance = null;
        instance.deletefolder(selected);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        instance.moveFolder(sourcePath, destinationPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doSendRecieve method, of class MessageController.
     */
    @Test
    public void testDoSendRecieve() {
        System.out.println("doSendRecieve");
        MessageController instance = null;
        instance.doSendRecieve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMeetingsFolderId method, of class MessageController.
     */
    @Test
    public void testGetMeetingsFolderId() {
        System.out.println("getMeetingsFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getMeetingsFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplatesFolderId method, of class MessageController.
     */
    @Test
    public void testGetTemplatesFolderId() {
        System.out.println("getTemplatesFolderId");
        MessageController instance = null;
        String expResult = "";
        String result = instance.getTemplatesFolderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMeeting method, of class MessageController.
     */
    @Test
    public void testCreateMeeting() {
        System.out.println("createMeeting");
        MessageController instance = null;
        String expResult = "";
        String result = instance.createMeeting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        FilterRule result = instance.getRuleFromId(ruleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        String result = instance.getIdfromRule(rule);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadRules method, of class MessageController.
     */
    @Test
    public void testLoadRules() {
        System.out.println("loadRules");
        MessageController instance = null;
        instance.loadRules();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRule method, of class MessageController.
     */
    @Test
    public void testAddRule() {
        System.out.println("addRule");
        FilterRule rule = null;
        MessageController instance = null;
        instance.addRule(rule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRule method, of class MessageController.
     */
    @Test
    public void testDeleteRule() {
        System.out.println("deleteRule");
        int ruleId = 0;
        MessageController instance = null;
        instance.deleteRule(ruleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRuleList method, of class MessageController.
     */
    @Test
    public void testGetRuleList() {
        System.out.println("getRuleList");
        MessageController instance = null;
        String[] expResult = null;
        String[] result = instance.getRuleList();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRulesCount method, of class MessageController.
     */
    @Test
    public void testGetRulesCount() {
        System.out.println("getRulesCount");
        MessageController instance = null;
        int expResult = 0;
        int result = instance.getRulesCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMeeting method, of class MessageController.
     */
    @Test
    public void testSendMeeting() {
        System.out.println("sendMeeting");
        String messageId = "";
        MessageController instance = null;
        instance.sendMeeting(messageId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        MeetingSummary result = instance.getMeetingSummary(messageId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveRules method, of class MessageController.
     */
    @Test
    public void testSaveRules() {
        System.out.println("saveRules");
        MessageController instance = null;
        instance.saveRules();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        boolean result = instance.checkFolderExists(folderPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}