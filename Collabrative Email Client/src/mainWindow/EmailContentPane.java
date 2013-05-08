/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 *
 * @author anasalkhatib
 */
public class EmailContentPane extends JPanel{

    public EmailContentPane() {
        JTextArea emailContent = new JTextArea("Content of email", 20, 20);
        add(emailContent);
        setBorder(new LineBorder(Color.BLACK));
        setSize(200,200);
    }
    
}
