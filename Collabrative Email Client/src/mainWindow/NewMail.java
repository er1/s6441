package mainWindow;
import javax.swing.*;
import java.awt.event.*;;

public class NewMail extends JFrame implements ActionListener {

	
	
	public JFrame newmail()
	{
		JFrame nmwindow = new JFrame("New Mail");
		JPanel multiple = new JPanel();
		//nmwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel account_Name = new JLabel("Account Name");
		JTextField first_Name = new JTextField("First",15);
		JTextField last_Name = new JTextField("Last",15);
		JLabel mail_Address = new JLabel("Mail Address");
		JTextField mail_Value = new JTextField(15);
		JLabel password = new JLabel("Enter Password");
		JPasswordField pass_value = new JPasswordField(15);
		JLabel rePassword = new JLabel("Re Enter Password");
		JPasswordField repassword = new JPasswordField(15);
		JButton save = new JButton("Continue");
		multiple.add(account_Name);
		multiple.add(first_Name);
		multiple.add(last_Name);
		multiple.add(mail_Address);
		multiple.add(mail_Value);
		multiple.add(password);
		multiple.add(pass_value);
		multiple.add(rePassword);
		multiple.add(repassword);
		multiple.add(save);
		nmwindow.add(multiple);
		nmwindow.setSize(250, 300);
		nmwindow.setVisible(true);
		return nmwindow;
	}
	public static void main(String args[])
	{
		NewMail obj_new = new NewMail();		
		obj_new.newmail();
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	 
}
