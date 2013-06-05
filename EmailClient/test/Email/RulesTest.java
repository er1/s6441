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
 * @author Bargavi
 */
public class RulesTest {
    private static String userHome;
    private static String testDirectory;
    static private String mailboxID;
    
    MessageController controller;
    Rules instance;
    ArrayList<FilterRule> listOfRules;
    PersistentStorage persistStore;
    private String id;
    
    public RulesTest() {
        
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
        this.id = "JUnit4";
        userHome = System.getProperty("user.home") + File.separator;
        testDirectory = userHome + "_mailbox" + File.separator + mailboxID + File.separator;
        
        this.persistStore = PersistentStorage.getFileSystemStorage(id);
        
        controller = MessageController.getInstance(new FileSystemMailbox(mailboxID));
        controller.loadRules();       
        instance = Rules.getInstance();
        //listOfRules = persistStore.loadRulesFromFileSystem();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getListOfRules method, of class Rules.
     */
    @Test
    public void testGetListOfRules() {
        instance = Rules.getInstance();
        ArrayList result = instance.getListOfRules();
        assertEquals(instance.getListOfRules(), result);
        
    }

    /**
     * Test of getInstance method, of class Rules.
     */
    @Test
    public void testGetInstance() {
        Rules expResult = instance;
        Rules result = Rules.getInstance();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of applyRules method, of class Rules.
     */
    @Test
    public void testApplyRules() {
        System.out.println("applyRules");
        String folderId = controller.getInboxFolderId();
        instance.applyRules(folderId);   
    }

    /**
     * Test of addRule method, of class Rules.
     */
    @Test
    public void testAddRule() {
        
        FilterRule rule1 = new FilterRule();
        rule1.setRuleId("1");
        rule1.setFromField("Bob");
        rule1.setcontentField("hi");
        rule1.setsubjectField("hi");
        rule1.setmoveToField("test/Inbox/name");
        
        Rules instance = this.instance;
        instance.addRule(rule1);
        //assertEquals(expResult, result);
    }

    /**
     * Test of deleteRule method, of class Rules.
     */
    @Test
    public void testDeleteRule() {
        
        Rules instance = this.instance;
        FilterRule rule1 = new FilterRule();
        rule1.setRuleId("1");
        rule1.setFromField("Bob");
        rule1.setcontentField("hi");
        rule1.setsubjectField("hi");
        rule1.setmoveToField("test/Inbox/name");
        instance.addRule(rule1);
        
        instance.deleteRule(1);
        
    }   
}