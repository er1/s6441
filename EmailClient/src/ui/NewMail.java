package ui;

import javax.swing.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

;

public class NewMail extends JFrame implements ActionListener {

    public JFrame newmail() {
        JFrame nmwindow = new JFrame("New Mail");
        JPanel multiple = new JPanel(new MigLayout());
        JLabel accountNameLabel = new JLabel("Account Name");
        JTextField firstNameField = new JTextField("First", 15);
        JTextField lastNameField = new JTextField("Last", 15);
        JLabel emailAddressLabel = new JLabel("Mail Address");
        JTextField emailAddressField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter Password");
        JPasswordField passwordField = new JPasswordField();
        JLabel reenterPasswordLabel = new JLabel("Re Enter Password");
        JPasswordField reenterPasswordField = new JPasswordField();
        JButton saveButton = new JButton("Continue");
        multiple.add(accountNameLabel);
        multiple.add(firstNameField);
        multiple.add(lastNameField, "span, growy, wrap");
        multiple.add(emailAddressLabel);
        multiple.add(emailAddressField, "span,grow,wrap");
        multiple.add(passwordLabel);
        multiple.add(passwordField, "span,grow,wrap");
        multiple.add(reenterPasswordLabel);
        multiple.add(reenterPasswordField, "span,grow,wrap");
        multiple.add(saveButton, "tag ok");
        nmwindow.add(multiple);
        nmwindow.setSize(400, 250);
        nmwindow.setVisible(true);
        return nmwindow;
    }

    public void actionPerformed(ActionEvent e) {
    }
}
