package ui;

import Persist.PersistentStorage;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author anasalkhatib
 */
public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());
    /**
     *
     * @param args Array of any command line arguments passed in
     */
    public static void main(String args[]) {
        UserInterface gui = UserInterface.getSwingUserInterface();
        String userID = gui.getUserID();
        logger.log(Level.INFO, "UserId: " + userID);
        PersistentStorage persistentStorage = PersistentStorage.getFileSystemStorage(userID);
        gui.display();
        //persistentStorage.load(); ???
    }
}
