package ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        final JFrame frame = new JFrame("Mailbox ID");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JOptionPane getMailBoxIDDialog = new JOptionPane(
                this,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);
        Object result = getMailBoxIDDialog.showInputDialog(
                frame,
                "Hi! Input your mailbox ID:",
                "Email Client 0.1",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                "test");
        frame.setVisible(true);
        String inputString = null;
        if (result == null) {
            // Code to use when CANCEL is PRESSED.
            System.out.println("Selected Option is Cancel");
            System.exit(0);
        } else {
            // Code to use when Input detected.
            inputString = (String) result;
            if (inputString.isEmpty()) {
                frame.dispose();
                inputString = getUserID();
            }
        }

        frame.dispose();
        return inputString;
    }


}
