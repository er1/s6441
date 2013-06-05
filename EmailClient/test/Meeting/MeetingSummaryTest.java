/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Meeting;

import Email.PlainTextMessage;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author KarthikKrishnan
 */
public class MeetingSummaryTest {
    private MeetingSummary instance;
    private PlainTextMessage msg;
    public MeetingSummaryTest() {
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
        this.instance = new MeetingSummary(msg);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Date method, of class MeetingSummary.
     */
    @Test
    public void testDate() {
        MeetingSummary expInstance = instance.Date(msg.getHeaderValue("Date"));
        String expResult = expInstance.getDate();
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of To method, of class MeetingSummary.
     */
    @Test
    public void testTo() {
        MeetingSummary expInstance = instance.To(msg.getHeaderValue("To"));
        String expResult = expInstance.getTo();
        String result = instance.getTo();
        assertEquals(expResult, result);
    }

    /**
     * Test of From method, of class MeetingSummary.
     */
    @Test
    public void testFrom() {
        MeetingSummary expInstance = instance.From(msg.getHeaderValue("From"));
        String expResult = expInstance.getFrom();
        String result = instance.getFrom();
        assertEquals(expResult, result);
    }

    /**
     * Test of CC method, of class MeetingSummary.
     */
    @Test
    public void testCC() {
        MeetingSummary expInstance = instance.CC(msg.getHeaderValue("Cc"));
        String expResult = expInstance.getCc();
        String result = instance.getCc();
        assertEquals(expResult, result);
    }

    /**
     * Test of BCC method, of class MeetingSummary.
     */
    @Test
    public void testBCC() {
        MeetingSummary expInstance = instance.BCC(msg.getHeaderValue("Bcc"));
        String expResult = expInstance.getBcc();
        String result = instance.getBcc();
        assertEquals(expResult, result);
    }

    /**
     * Test of Subject method, of class MeetingSummary.
     */
    @Test
    public void testSubject() {
        MeetingSummary expInstance = instance.Subject(msg.getHeaderValue("Subject"));
        String expResult = expInstance.getSubject();
        String result = instance.getSubject();
        assertEquals(expResult, result);
    }

    /**
     * Test of Read method, of class MeetingSummary.
     */
    @Test
    public void testRead() {
       MeetingSummary expInstance = instance.Read(msg.getHeaderValue("X-Read").length() > 0);
        boolean expResult = expInstance.isRead();
        boolean result = instance.isRead();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class MeetingSummary.
     */
    @Test
    public void testGetDate() {
        MeetingSummary expInstance = instance.Date(msg.getHeaderValue("Date"));
        String expResult = expInstance.getDate();
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTo method, of class MeetingSummary.
     */
    @Test
    public void testGetTo() {
         MeetingSummary expInstance = instance.To(msg.getHeaderValue("To"));
        String expResult = expInstance.getTo();
        String result = instance.getTo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFrom method, of class MeetingSummary.
     */
    @Test
    public void testGetFrom() {
         MeetingSummary expInstance = instance.From(msg.getHeaderValue("From"));
        String expResult = expInstance.getFrom();
        String result = instance.getFrom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCc method, of class MeetingSummary.
     */
    @Test
    public void testGetCc() {
        MeetingSummary expInstance = instance.CC(msg.getHeaderValue("Cc"));
        String expResult = expInstance.getCc();
        String result = instance.getCc();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBcc method, of class MeetingSummary.
     */
    @Test
    public void testGetBcc() {
        MeetingSummary expInstance = instance.BCC(msg.getHeaderValue("Bcc"));
        String expResult = expInstance.getBcc();
        String result = instance.getBcc();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubject method, of class MeetingSummary.
     */
    @Test
    public void testGetSubject() {
        MeetingSummary expInstance;
        expInstance = instance.Date(msg.getContent());
        String expResult = expInstance.getDate();
        String result = instance.getSubject();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartTime method, of class MeetingSummary.
     */
    @Test
    public void testGetStartTime() {
        MeetingSummary expInstance = instance.Date(msg.getContent());
        String expResult = expInstance.getDate();
        String result = instance.getStartTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndTime method, of class MeetingSummary.
     */
    @Test
    public void testGetEndTime() {
        MeetingSummary expInstance = instance.Date(msg.getContent());
        String expResult = expInstance.getDate();
        String result = instance.getEndTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMeetingDate method, of class MeetingSummary.
     */
    @Test
    public void testGetMeetingDate() {
       MeetingSummary expInstance = instance.Date(msg.getId());
        String expResult = expInstance.getDate();
        String result = instance.getMeetingDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMeetingId method, of class MeetingSummary.
     */
    @Test
    public void testGetMeetingId() {
       MeetingSummary expInstance = instance.Date(msg.getId());
        String expResult = expInstance.getDate();
        String result = instance.getMeetingId();
        assertEquals(expResult, result);
    }

    /**
     * Test of isRead method, of class MeetingSummary.
     */
    @Test
    public void testIsRead() {
        MeetingSummary expInstance = instance.Date(msg.getContent());
        String expResult = expInstance.getDate();
        String result = instance.getFrom();
        assertEquals(expResult, result);
    }

    /**
     * Test of StartTime method, of class MeetingSummary.
     */
    @Test
    public void testStartTime() {
        MeetingSummary expInstance = instance.Date(msg.getContent());
        String expResult = expInstance.getDate();
        String result = instance.getStartTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of EndTime method, of class MeetingSummary.
     */
    @Test
    public void testEndTime() {
        MeetingSummary expInstance = instance.Date(msg.getContent());
        String expResult = expInstance.getDate();
        String result = instance.getEndTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of MeetingDate method, of class MeetingSummary.
     */
    @Test
    public void testMeetingDate() {
        MeetingSummary expInstance = instance.Date(msg.getContent());
        String expResult = expInstance.getDate();
        String result = instance.getMeetingId();
        assertEquals(expResult, result);
    }

    /**
     * Test of MeetingID method, of class MeetingSummary.
     */
    @Test
    public void testMeetingID() {
        MeetingSummary expInstance = instance.Date(msg.getId());
        String expResult = expInstance.getDate();
        String result = instance.getMeetingId();
        assertEquals(expResult, result);
       
    }
}