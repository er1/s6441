/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

/**
 *
 * @author anasalkhatib
 */
public class FileSystemStore extends FileSystemFolder implements MessageStore {

    FileSystemStore(String mailboxID) {
        //This is where we get the mailboxID
        //But this applies only to the main mailBoxFolder
        //How should the other folder classes get mailBoxId?
        super(mailboxID);

    }
    @Override
    public Folder getDrafts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Folder getInbox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Folder getOutbox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Folder getSentMessages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Folder getTrash() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
