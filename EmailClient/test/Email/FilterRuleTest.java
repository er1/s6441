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
 * @author ChinnaChinni
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
     * Test of setFromField method, of class FilterRule.
     */
    @Test
    public void testFilterRule() {
        
        FilterRule rule1 = new FilterRule();
        rule1.setRuleId("1");
        rule1.setFromField("Bob");
        rule1.setcontentField("hi");
        rule1.setsubjectField("hi");
        rule1.setmoveToField("test/Inbox/name");
        
        assertEquals("1", rule1.getRuleId());
        assertEquals("Bob", rule1.getFromField());
        assertEquals("hi", rule1.getsubjectField());
        assertEquals("hi", rule1.getcontentField());
        assertEquals("test/Inbox/name", rule1.getmoveToField());
    }

   
}