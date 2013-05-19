package ui;

/**
 *
 * @author anasalkhatib
 */
public abstract class UserInterface {

    /**
     * Static Factory Method to get special UI using Swing
     *
     * @return UserInterface implemented in Swing
     */
    public static UserInterface getSwingUserInterface() {
        SwingUserInterface swingInterface = new SwingUserInterface();
        return swingInterface;
    }

    /**
     * Function to draw the user interface
     */
    abstract public void display();
    abstract public String getUserID();
}
