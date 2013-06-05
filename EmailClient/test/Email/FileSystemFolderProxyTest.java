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
        System.out.println("getId");
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);
        String expResult = "TEST_MAILBOX";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class FileSystemFolderProxy.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
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
        System.out.println("getName");
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
        System.out.println("setName");
        String name = "";
        FileSystemFolderProxy instance = new FileSystemFolderProxy("level1", mailbox);;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
    }

}