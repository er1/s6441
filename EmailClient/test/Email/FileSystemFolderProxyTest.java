/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Persist.PersistentStorage;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author chanman
 */
public class FileSystemFolderProxyTest {

    FileSystemMailbox mailbox;

    public FileSystemFolderProxyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        PersistentStorage.getTestingStorage();
        mailbox = new FileSystemMailbox("TEST_MAILBOX");
        mailbox.createFolder("level1");
        mailbox.createFolder("level2");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class FileSystemFolderProxy.
     */
    @Test
    public void testGetId() {
        
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        String expResult = "TEST_MAILBOX" + File.separator + "level1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class FileSystemFolderProxy.
     */
    @Test
    public void testGetPath() {
        
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        String expResult = "TEST_MAILBOX" + File.separator + "level1";
        String result = instance.getPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class FileSystemFolderProxy.
     */
    @Test
    public void testGetName() {
        
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        String expResult = "level1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class FileSystemFolderProxy.
     */
    @Test
    public void testSetName() {
        
        String name = "";
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        instance.setName(name);
        
    }

    /**
     * Test of getMessages method, of class FileSystemFolderProxy.
     */
    @Test
    public void testGetMessages() {
        
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        ArrayList result = instance.getMessages();
        assertNotNull(result);
        
    }

    /**
     * Test of getSubfolders method, of class FileSystemFolderProxy.
     */
   @Test
    public void testGetSubfolders() {
        
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        ArrayList result = instance.getSubfolders();
        assertNotNull(result);
   }

    /**
     * Test of addMessage method, of class FileSystemFolderProxy.
     */
    @Test
    public void testAddMessage() {
        
        PlainTextMessage msg;
        msg = PlainTextMessage.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
                
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        instance.addMessage(msg);
    }

    /**
     * Test of addMessageCopy method, of class FileSystemFolderProxy.
     */
    @Test
    public void testAddMessageCopy() {
        PlainTextMessage msg;
        msg = PlainTextMessage.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
                
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        instance.addMessageCopy(msg);
    }

    /**
     * Test of deleteMessage method, of class FileSystemFolderProxy.
     */
    @Test
    public void testDeleteMessage() {
        PlainTextMessage msg;
        msg = PlainTextMessage.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
                
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        instance.addMessage(msg);
        instance.deleteMessage(msg);
    }

    /**
     * Test of addFolder method, of class FileSystemFolderProxy.
     */
    @Test
    public void testAddAndDeleteFolder() {
   
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        
        PersistentStorage.getTestingStorage();
        
        String testname = "zzz-test-zzz";
        
        Folder t = new FileSystemFolderProxy(testname, mailbox);
        
        instance.addFolder(t);
        
        instance.deleteFolder(t);
    }

    /**
     * Test of sync method, of class FileSystemFolderProxy.
     */
    @Test
    public void testSync() {
      
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        instance.sync();  
    }

    /**
     * Test of createFolder method, of class FileSystemFolderProxy.
     */
    @Test
    public void testCreateFolder() {
       
        String name = "level3";
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        instance.createFolder(name);
    }

    /**
     * Test of moveFolder method, of class FileSystemFolderProxy.
     */
    @Ignore
    public void testMoveFolder() {
        System.out.println("moveFolder");
        Folder dest = null;
        FileSystemFolderProxy instance = null;
        instance.moveFolder(dest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
