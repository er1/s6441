package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Bargavi
 */
public class CreateNewFolder extends JFrame implements ActionListener {

    String folderName;
    JFrame newFdWindow;
    JPanel panel;
    JLabel nameLabel;
    JTextField nameText;
    JButton okButton;
    JButton cancelButton;

    public void newFolder() {

        newFdWindow = new JFrame("Create New Folder");
        panel = new JPanel(new MigLayout());
        nameLabel = new JLabel("Name:");
        nameText = new JTextField();

        okButton = new JButton("Ok");
        okButton.setActionCommand("Ok");
        okButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(this);

        panel.add(nameLabel, "wrap");
        panel.add(nameText, "span 2,grow,wrap");
        panel.add(okButton);
        panel.add(cancelButton, "span,growy,wrap");

        newFdWindow.add(panel);

        newFdWindow.pack();
        //newFdWindow.setSize(400, 250);
        newFdWindow.setVisible(true);
        newFdWindow.setResizable(true);
        System.out.println("foldername : " + folderName);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ok")) {
            System.out.println("Inside action : ");
            this.folderName = nameText.getText();
            System.out.println("foldername : " + this.folderName);
        }
        if (e.getActionCommand().equals("Cancel")) {
            folderName = null;
        }
    }

    public String getFolderName() {
        return folderName;
    }
}
