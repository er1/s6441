package mainWindow;
import javax.swing.*;

import java.awt.TextArea;
import java.awt.event.*;

public class Compose_Mail extends JFrame implements ActionListener {
 
	public JFrame writeMail()
	{
		JFrame cmwindow = new JFrame("New Mail");
		JPanel multiple = new JPanel();
		//cmwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel account_Name = new JLabel("To",JLabel.LEFT);
		//account_Name.setVerticalAlignment("top");
		JTextField to_mailadd = new JTextField("Mail Address",40);
		JLabel subject_mail = new JLabel("Subject");
		JTextField mail_Value = new JTextField("Subject",40);
		JLabel Body = new JLabel("Information");
		TextArea sub_mail = new TextArea("Enter the information");
		JButton send_mail = new JButton("Send");
		send_mail.setToolTipText("Sends mail to the corresponding To information");
		JButton discard_mail = new JButton("Draft");
		discard_mail.setToolTipText("Save mail in Draft folder, Does not send mail");
		JButton cancel_mail = new JButton("Cancel");
		cancel_mail.setToolTipText("Delete the mail and move it to recycle bin draft");
		multiple.add(account_Name);
		multiple.add(to_mailadd);
		multiple.add(subject_mail);
		multiple.add(mail_Value);
		multiple.add(Body);
		multiple.add(sub_mail);
		multiple.add(send_mail);
		multiple.add(discard_mail);
		multiple.add(cancel_mail);
		cmwindow.add(multiple);
		cmwindow.setSize(480,380);
		cmwindow.setVisible(true);
		return cmwindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}

