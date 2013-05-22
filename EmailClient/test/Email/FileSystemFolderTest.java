/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Persist.PersistentStorage;
import java.util.ArrayList;
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
public class FileSystemFolderTest {
    
    private String id, name;
    private ArrayList<Message> messages;
    private ArrayList<Folder> folders;
    private PersistentStorage persistStore;
    
    public FileSystemFolderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.id = "JUnit4";
        this.messages = new ArrayList<Message> ();
        this.folders = new ArrayList<Folder> ();
        this.persistStore = PersistentStorage.getFileSystemStorage(id);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId and setId method, of class FileSystemFolder.
     */
    @Test
    public void testSetGetId() {
        
        FileSystemFolder instance = new FileSystemFolder(id);
        
        String expResult = "id";
        instance.setId(expResult);
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName and setName method, of class FileSystemFolder.
     */
    @Test
    public void testSetGetName() {
        FileSystemFolder instance = new FileSystemFolder(id);
        
        String expResult = "id";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of addMessages and getMessages method, of class FileSystemFolder.
     */
    @Test
      public void testAddAndGetMessages() {
        PlainTextMessage msg = new PlainTextMessage();
        msg.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
        ArrayList<Message> expResult = new ArrayList<Message> ();
        expResult.add((Message)msg);
                
        FileSystemFolder instance = new FileSystemFolder(id);
        instance.addMessage(msg);
        
        ArrayList result = instance.getMessages();
        //assertEquals(expResult, result);   
    }

    /**
     * Test of getSubfolders method, of class FileSystemFolder.
     */
    @Test
     public void testGetSubfolders() {
        
        FileSystemFolder instance = new FileSystemFolder(id);
        ArrayList expResult = null;
        ArrayList result = instance.getSubfolders();
        //assertEquals(expResult, result);
    }

    /**
     * Test of addMessageCopy method, of class FileSystemFolder.
     */
    @Test
    public void testAddMessageCopy() {
        
        PlainTextMessage msg = new PlainTextMessage();
        msg.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
        
        ArrayList<Message> expResult = new ArrayList<Message> ();
        expResult.add((Message)msg);
        
        FileSystemFolder instance = new FileSystemFolder(id);
        instance.addMessageCopy(msg);
        
        ArrayList result = instance.getMessages();
        //assertEquals(expResult, result);
        
    }

    /**
     * Test of addMessage and deleteMessage method, of class FileSystemFolder.
     */
    @Test
    public void testAddAndDeleteMessage() {
        System.out.println("deleteMessage");
        
        PlainTextMessage msg = new PlainTextMessage();
        msg.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
                
        FileSystemFolder instance = new FileSystemFolder(id);
        instance.addMessage(msg);
        
        instance.deleteMessage(msg);
        
    }

    /**
     * Test of addAndFolder method, of class FileSystemFolder.
     */
    @Test
    public void testAddAndDeleteFolder() {
        Folder folder = new TemporaryFolder("name");
        FileSystemFolder instance = new FileSystemFolder(id);
        instance.addFolder(folder);
        
        instance.deleteFolder(folder);
        
    }

    /**
     * Test of sync method, of class FileSystemFolder.
     */
    @Test
    public void testSync() {
        
        FileSystemFolder instance = new FileSystemFolder(id);
        instance.sync();
        
    }
}