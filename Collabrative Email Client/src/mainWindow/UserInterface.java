/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

/**
 *
 * @author anasalkhatib
 */
public class UserInterface {

    public enum UserInterfaceEnum {

        SWING
    }

    /**
     * * Factory method to get special UI
     */
    public static UserInterface getSwingUserInterface() {
        SwingUserInterface swingInterface = new SwingUserInterface();
        return swingInterface;
    }

    /**
     * Function to draw the user interface
     */
    public void display() {
        new MainWindow();
    }
}
