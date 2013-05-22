package ui;

import Persist.PersistentStorage;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Main Class initiates the entire operation
 * @author anasalkhatib
 */
public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());
    /**
     * main method to call gui interface and persistent storage
     * @param args Array of any command line arguments passed in
     */
    public static void main(String args[]) {
        UserInterface gui = UserInterface.getSwingUserInterface();
        String userID = gui.getUserID();
        logger.log(Level.INFO, "UserId: {0}", userID);
        PersistentStorage persistentStorage = PersistentStorage.getFileSystemStorage(userID);
        gui.display();
        //persistentStorage.load(); ???
    }
}
