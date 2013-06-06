/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

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
public class FilterRuleTest {
    
    public FilterRuleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of matches method, of class FilterRule.
     */
    @Ignore
    public void testMatches() {
        System.out.println("matches");
        String messageid = "";
        FilterRule instance = new FilterRule();
        boolean expResult = false;
        boolean result = instance.matches(messageid);
        assertEquals(expResult, result);
    }

    /**
     * Test of setAndGetRuleId method, of class FilterRule.
     */
    
    @Test
    public void testGetAndSetRuleId() {
        FilterRule rule = new FilterRule();
        rule.setRuleId("Rule1");
        assertEquals("Rule1", rule.getRuleId());
    }
    @Test
    public void testGetAndSetFromField() {
        FilterRule rule1 = new FilterRule();
        rule1.setFromField("Bob");
        assertEquals("Bob", rule1.getFromField());
    }
    @Test
    public void testGetAndSetSubjectField() {
        FilterRule rule1 = new FilterRule();
        rule1.setsubjectField("Hello");
        assertEquals("Hello", rule1.getsubjectField());
    }
    @Test
    public void testGetAndSetcontentField() {
        FilterRule rule1 = new FilterRule();
        rule1.setcontentField("This is a message");
        assertEquals("This is a message", rule1.getcontentField());
    }
    @Test
    public void testGetAndSetMoveToField() {
        FilterRule rule1 = new FilterRule();
        rule1.setmoveToField("test/Inbox/name");
        assertEquals("test/Inbox/name", rule1.getmoveToField());
    }
}