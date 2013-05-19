package ui;

import javax.swing.JFrame;
import ui.SwingMain.MainWindow;

/**
 *
 * @author anasalkhatib
 */
class SwingUserInterface extends UserInterface {

    @Override
    public void display() {
        JFrame window = new MainWindow();
        window.setVisible(true);
    }

    @Override
    public String getUserID() {
        return "testUser";
    }
    
    
}
