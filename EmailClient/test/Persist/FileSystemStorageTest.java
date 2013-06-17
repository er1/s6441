/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;

import Email.FileSystemFolderProxy;
import Email.FilterRule;
import Email.Folder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author anasalkhatib
 */
public class FileSystemStorageTest {
    private PersistentStorage instance;
    private String mailboxID;
    private String userHome;
    private String testDirectory;

    void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        if (!f.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + f);
        }
    }

    public FileSystemStorageTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mailboxID = "junit";
        userHome = System.getProperty("user.home") + File.separator;
        testDirectory = userHome + "_mailbox" + File.separator + mailboxID + File.separator;
        instance = PersistentStorage.getFileSystemStorage(mailboxID);
    }

    @After
    public void tearDown() {
        File junitDirectory = new File(testDirectory);
        try {
            delete(junitDirectory);
        } catch (IOException ex) {
            Logger.getLogger(FileSystemStorageTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("IO Problem");
        }
    }

    /**
     * Test of newFolder method, of class FileSystemStorage.
     */
    @Test
    public void testNewFolder() {
        boolean result = instance.newFolder(mailboxID + File.separator + "newFolder");
        assertEquals(true, result);
    }

    /**
     * Test of newFolderInMailbox method, of class FileSystemStorage.
     */
    @Test
    public void testNewFolderInMailbox() {
        String newFolderPath = mailboxID + File.separator + "Inbox" + File.separator + "junit";
        boolean result = instance.newFolderInMailbox(newFolderPath);
        assertEquals(true, result);
    }

    /**
     * Test of loadMessageListFromFolder method, of class FileSystemStorage.
     */
    @Test
    public void testLoadMessageListFromFolder() {
        System.out.println("loadMessageListFromFolder");
        String folder = "Inbox";
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.loadMessageListFromFolder(folder);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadSubfolders method, of class FileSystemStorage.
     */
    @Test
    public void testLoadSubfolders() {
        System.out.println("loadSubfolders");
        String folder = "Inbox";
        Set<String> emptySet = null;
        Set<String> result = (Set<String>) instance.loadSubfolders(folder);
        assertSame(emptySet, result);
    }

    /**
     * Test of deleteFolderAndAllContents method, of class FileSystemStorage.
     */
    @Test
    public void testDeleteFolder() {
        
        String folder = mailboxID;
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        inst.deleteFolderAndAllContents(folder + File.separator + "newFolder");
    }

    /**
     * Test of moveMessageToFolder method, of class FileSystemStorage.
     */
    @Test
    public void testMoveMessageToFolder() {
        
        String message = "";
        String folder = "";
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        inst.moveMessageToFolder(message, folder);
    }

    /**
     * Test of newMessage method, of class FileSystemStorage.
     */
    @Test
    public void testNewMessage() {
        
        String folder = "Hi";
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        inst.newMessage(folder);
        
    }

    /**
     * Test of saveMessage method, of class FileSystemStorage.
     */
    @Test
    public void testSaveMessage() {
        String message = "";
        String content = "";
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        inst.saveMessage(message, content);
    }

    /**
     * Test of loadMessage method, of class FileSystemStorage.
     */
    @Test
    public void testLoadMessage() {
        
        String message = "";
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        String expResult = "";
        String result = inst.loadMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteMessage method, of class FileSystemStorage.
     */
    @Test
    public void testDeleteMessage() {
        
        String message = "";
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        inst.deleteMessage(message);
    }

    /**
     * Test of moveFolder method, of class FileSystemStorage.
     */
    @Test
    public void testMoveFolder() {
        
        String folderToMove = "";
        String destinationFolder = "";
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        inst.moveFolder(folderToMove, destinationFolder);
    }

    /**
     * Test of getMailboxID method, of class FileSystemStorage.
     */
    @Test
    public void testGetMailboxID() {
        
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        String result = inst.getMailboxID();
        assertEquals(mailboxID, result);
        
    }

    /**
     * Test of newRuleFileInMailbox method, of class FileSystemStorage.
     */
    @Test
    public void testNewRuleFileInMailbox() {
        
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        String path = File.separator + mailboxID + File.separator + "rules.txt";
        boolean expResult = false;
        boolean result = inst.newRuleFileInMailbox(path);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteFolderAndAllContents method, of class FileSystemStorage.
     */
    @Test
    public void testDeleteFolderAndAllContents() {
        
        String folder = mailboxID;
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        boolean expResult = false;
        boolean result = inst.deleteFolderAndAllContents(folder + File.separator + "newFolder");
        assertEquals(expResult, result);
    }

    /**
     * Test of loadRulesFromFileSystem method, of class FileSystemStorage.
     */
    @Test
    public void testLoadRulesFromFileSystem() {
        
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        ArrayList expResult = new ArrayList();
        ArrayList result = instance.loadRulesFromFileSystem();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of saveRulesToFileSystem method, of class FileSystemStorage.
     */
    @Test
    public void testSaveRulesToFileSystem() {
        System.out.println("saveRulesToFileSystem");
        ArrayList<FilterRule> listOfRules = new ArrayList();
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        inst.saveRulesToFileSystem(listOfRules);
    }

    /**
     * Test of isFolderExists method, of class FileSystemStorage.
     */
    @Test
    public void testIsFolderExists() {
      
        String folderPath = "";
        FileSystemStorage inst = (FileSystemStorage) this.instance;
        boolean expResult = true;
        boolean result = inst.isFolderExists(folderPath);
        assertEquals(expResult, result);
        
    }
}