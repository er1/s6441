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

/**
 *
 * @author Bargavi
 */
public class SummaryTest {
    
    private Summary instance;
    private PlainTextMessage msg;
    
    public SummaryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        msg = new PlainTextMessage();
        msg.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
        this.instance = new Summary(msg);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Date method, of class Summary.
     */
    @Test
    public void testSetAndGetDate() {
        
        Summary expInstance = instance.Date(msg.getHeaderValue("Date"));
        String expResult = expInstance.getDate();
        String result = instance.getDate();
        assertEquals(expResult, result);
         
    }

    /**
     * Test of To method, of class Summary.
     */
    @Test
    public void testTo() {
        Summary expInstance = instance.To(msg.getHeaderValue("To"));
        String expResult = expInstance.getTo();
        String result = instance.getTo();
        assertEquals(expResult, result);
    }

    /**
     * Test of From method, of class Summary.
     */
    @Test
    public void testFrom() {
        Summary expInstance = instance.From(msg.getHeaderValue("From"));
        String expResult = expInstance.getFrom();
        String result = instance.getFrom();
        assertEquals(expResult, result);
    }

    /**
     * Test of CC method, of class Summary.
     */
    @Test
    public void testCC() {
        Summary expInstance = instance.CC(msg.getHeaderValue("Cc"));
        String expResult = expInstance.getCc();
        String result = instance.getCc();
        assertEquals(expResult, result);
    }

    /**
     * Test of BCC method, of class Summary.
     */
    @Test
    public void testBCC() {
        Summary expInstance = instance.BCC(msg.getHeaderValue("Bcc"));
        String expResult = expInstance.getBcc();
        String result = instance.getBcc();
        assertEquals(expResult, result);
    }

    /**
     * Test of Subject method, of class Summary.
     */
    @Test
    public void testSubject() {
        Summary expInstance = instance.Subject(msg.getHeaderValue("Subject"));
        String expResult = expInstance.getSubject();
        String result = instance.getSubject();
        assertEquals(expResult, result);
    }

    /**
     * Test of Read method, of class Summary.
     */
    @Test
    public void testRead() {
        Summary expInstance = instance.Read(msg.getHeaderValue("X-Read").length() > 0);
        boolean expResult = expInstance.isRead();
        boolean result = instance.isRead();
        assertEquals(expResult, result);
    }
}