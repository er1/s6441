package ui;

import Persist.PersistentStorage;

/**
 *
 * @author anasalkhatib
 */
public class Main {

    public static void main(String args[]) {
        UserInterface gui = UserInterface.getSwingUserInterface();
        gui.display();
        PersistentStorage persistentStorage = PersistentStorage.getFileSystemStorage();
        persistentStorage.load();
    }
}
