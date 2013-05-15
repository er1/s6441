/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author anasalkhatib
 */
public abstract class UserInterface {

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
    abstract public void display();
}
