/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

/**
 *
 * @author chanman
 */
public class DummyStore extends TemporaryFolder implements MessageStore {

    private Folder inbox = new TemporaryFolder();
    private Folder outbox = new TemporaryFolder();
    private Folder sent = new TemporaryFolder();

    DummyStore() {
        this.addFolder(inbox);
        this.addFolder(outbox);
        this.addFolder(sent);

        PlainTextMessage msg = new PlainTextMessage();
        msg.parse("Date: 01 Jan 01 1970 GMT\r\n"
                + "From: toor@example.com\r\n"
                + "To: alice@example.com\r\n"
                + "Subject: Hello\r\n"
                + "\r\n"
                + "Hello, World\r\n");

        inbox.addMessage(msg);
    }

    @Override
    public Folder getInbox() {
        return inbox;
    }

    @Override
    public Folder getOutbox() {
        return outbox;
    }

    @Override
    public Folder getSentMessages() {
        return sent;
    }
}
