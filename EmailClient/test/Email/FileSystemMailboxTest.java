/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Persist.PersistentStorage;
import java.util.HashMap;
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
public class FileSystemMailboxTest {
    
    private PersistentStorage storage;
    private String mailboxID;
    private HashMap<String, FileSystemFolder> topLevelFolders;
    
    public FileSystemMailboxTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.mailboxID = "test" ;
        this.storage = PersistentStorage.getFileSystemStorage(this.mailboxID);
        this.topLevelFolders = new HashMap<String, FileSystemFolder>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDrafts method, of class FileSystemMailbox.
     */
    @Test
    public void testGetDrafts() {
       
        FileSystemMailbox instance = new FileSystemMailbox(mailboxID);
        Folder result = instance.getDrafts();
        assertEquals("Drafts", result.getName());
        
    }

    /**
     * Test of getInbox method, of class FileSystemMailbox.
     */
    @Test
    public void testGetInbox() {
       
        FileSystemMailbox instance = new FileSystemMailbox(mailboxID);
        Folder result = instance.getInbox();
        assertEquals("Inbox", result.getName());
    }

    /**
     * Test of getOutbox method, of class FileSystemMailbox.
     */
    @Test
    public void testGetOutbox() {
       
        FileSystemMailbox instance = new FileSystemMailbox(mailboxID);
        Folder result = instance.getOutbox();
        assertEquals("Outbox", result.getName());
    }

    /**
     * Test of getSentMessages method, of class FileSystemMailbox.
     */
    @Test
    public void testGetSentMessages() {
       
        FileSystemMailbox instance = new FileSystemMailbox(mailboxID);;
        Folder result = instance.getSentMessages();
        assertEquals("Sent", result.getName());
    }

    /**
     * Test of getTrash method, of class FileSystemMailbox.
     */
    @Test
    public void testGetTrash() {
     
        FileSystemMailbox instance = new FileSystemMailbox(mailboxID);
        Folder result = instance.getTrash();
        assertEquals("Trash", result.getName());
    }
}