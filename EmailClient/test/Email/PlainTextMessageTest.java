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
 * @author chanman
 */
public class PlainTextMessageTest {
    
    public PlainTextMessageTest() {
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
     * Test of setId, getId methods, of class PlainTextMessage.
     */
    @Test
    public void testSetGetId() {
        PlainTextMessage instance = new PlainTextMessage();
        
        String expResult = "testid";
        instance.setId(expResult);
        String result = instance.getId();
        assertEquals(expResult, result);
    }


    /**
     * Test of setContent, getContent methods, of class PlainTextMessage.
     */
    @Test
    public void testSetGetContent() {
        PlainTextMessage instance = new PlainTextMessage();
        
        String expResult = "This is some test content";
        instance.setContent(expResult);
        String result = instance.getContent();
        assertEquals(expResult, result);
        
        expResult = "Hello, World\r\n";
        instance.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
    
        result = instance.getContent();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getHeaderValue method, of class PlainTextMessage.
     */
    @Test
    public void testGetHeaderValue() {
        PlainTextMessage instance = new PlainTextMessage();
        
        String expResult;
        String result;
        
        expResult = "Hello, World\r\n";
        instance.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
    
        expResult = "01 Jan 01 1970 GMT";
        result = instance.getHeaderValue("Date");
        assertEquals(expResult, result);
        expResult = "toor@example.com";
        result = instance.getHeaderValue("From");
        assertEquals(expResult, result);
        expResult = "alice@example.com";
        result = instance.getHeaderValue("To");
        assertEquals(expResult, result);
        expResult = "Hello";
        result = instance.getHeaderValue("Subject");
        assertEquals(expResult, result);
        expResult = "";
        result = instance.getHeaderValue("X-Not-A-Header");
        assertEquals(expResult, result);        
    }

    /**
     * Test of setHeader method, of class PlainTextMessage.
     */
    @Test
    public void testSetHeader() {
        PlainTextMessage instance = new PlainTextMessage();
        instance.setHeader("From", "postmaster");
        instance.setContent("Hello\r\n");
                
        String expResult = "From: postmaster\r\n"
                + ""
                + "Hello\r\n";
        String result = instance.serialize();
    }

    /**
     * Test of serialize method, of class PlainTextMessage.
     */
    @Test
    public void testSerialize() {
        PlainTextMessage instance = new PlainTextMessage();
        instance.setHeader("From", "postmaster");
        instance.setContent("Hello\r\n");
                
        String expResult = "From: postmaster\r\n"
                + ""
                + "Hello\r\n";
        String result = instance.serialize();
    }

    /**
     * Test of parse method, of class PlainTextMessage.
     */
    @Test
    public void testParse() {
        PlainTextMessage instance = new PlainTextMessage();
        
        String expResult;
        String result;
        
        expResult = "Hello, World\r\n";
        instance.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");
    
        expResult = "01 Jan 01 1970 GMT";
        result = instance.getHeaderValue("Date");
        assertEquals(expResult, result);
        expResult = "toor@example.com";
        result = instance.getHeaderValue("From");
        assertEquals(expResult, result);
        expResult = "alice@example.com";
        result = instance.getHeaderValue("To");
        assertEquals(expResult, result);
        expResult = "Hello";
        result = instance.getHeaderValue("Subject");
        assertEquals(expResult, result);
        expResult = "";
        result = instance.getHeaderValue("X-Not-A-Header");
        assertEquals(expResult, result);
        
        expResult = "Hello, World\r\n";
        result = instance.getContent();
        assertEquals(expResult, result);
    }
}