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
    @Ignore
    public void testSetName() {
        
        String name = "";
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);;
        instance.setName(name);
        
    }

    /**
     * Test of getMessages method, of class FileSystemFolderProxy.
     */
    @Ignore
    public void testGetMessages() {
        System.out.println("getMessages");
        FileSystemFolderProxy instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubfolders method, of class FileSystemFolderProxy.
     */
   @Ignore
    public void testGetSubfolders() {
        System.out.println("getSubfolders");
        FileSystemFolderProxy instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getSubfolders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMessage method, of class FileSystemFolderProxy.
     */
    @Ignore
    public void testAddMessage() {
        System.out.println("addMessage");
        Message msg = null;
        FileSystemFolderProxy instance = null;
        instance.addMessage(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMessageCopy method, of class FileSystemFolderProxy.
     */
    @Ignore
    public void testAddMessageCopy() {
        System.out.println("addMessageCopy");
        Message msg = null;
        FileSystemFolderProxy instance = null;
        instance.addMessageCopy(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMessage method, of class FileSystemFolderProxy.
     */
    @Ignore
    public void testDeleteMessage() {
        System.out.println("deleteMessage");
        Message msg = null;
        FileSystemFolderProxy instance = null;
        instance.deleteMessage(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFolder method, of class FileSystemFolderProxy.
     */
    @Test
    public void testAddAndDeleteFolder() {
   
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);;
        
        PersistentStorage.getTestingStorage();
        
        String testname = "zzz-test-zzz";
        
        Folder t = new FileSystemFolderProxy(testname, mailbox);
        
        instance.addFolder(t);
        
        instance.deleteFolder(t);
    }

    /**
     * Test of sync method, of class FileSystemFolderProxy.
     */
    @Ignore
    public void testSync() {
        System.out.println("sync");
        FileSystemFolderProxy instance = null;
        instance.sync();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFolder method, of class FileSystemFolderProxy.
     */
    @Ignore
    public void testCreateFolder() {
        System.out.println("createFolder");
        String name = "";
        FileSystemFolderProxy instance = null;
        instance.createFolder(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
