package mainWindow;

import javax.swing.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

;

public class NewMail extends JFrame implements ActionListener {

    public JFrame newmail() {
        JFrame nmwindow = new JFrame("New Mail");
        JPanel multiple = new JPanel(new MigLayout());
        //nmwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel account_Name = new JLabel("Account Name");
        JTextField first_Name = new JTextField("First", 15);
        JTextField last_Name = new JTextField("Last", 15);
        JLabel mail_Address = new JLabel("Mail Address");
        JTextField mail_Value = new JTextField();
        JLabel password = new JLabel("Enter Password");
        JPasswordField pass_value = new JPasswordField();
        JLabel rePassword = new JLabel("Re Enter Password");
        JPasswordField repassword = new JPasswordField();
        JButton save = new JButton("Continue");
        multiple.add(account_Name);
        multiple.add(first_Name);
        multiple.add(last_Name, "span, growy, wrap");
        multiple.add(mail_Address);
        multiple.add(mail_Value, "span,grow,wrap");
        multiple.add(password);
        multiple.add(pass_value, "span,grow,wrap");
        multiple.add(rePassword);
        multiple.add(repassword, "span,grow,wrap");
        multiple.add(save);
        nmwindow.add(multiple);
        nmwindow.setSize(400, 250);
        nmwindow.setVisible(true);
        return nmwindow;
    }

    public static void main(String args[]) {
        NewMail obj_new = new NewMail();
        obj_new.newmail();
    }

    public void actionPerformed(ActionEvent e) {
    }
}
