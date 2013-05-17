package ui.SwingMain;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

/**
 * Email list Display
 */
public class EmailListDisplayPane extends JPanel {

    /**
     * Constructor
     */
    public EmailListDisplayPane() {
        this.setLayout(new MigLayout());
        String[][] data;
        data = new String[5][2];
        data[0][0] = "email1@test.com";
        data[0][1] = "Subject Line 1";
        data[1][0] = "email2@test.com";
        data[1][1] = "Subject Line 2";
        data[2][0] = "email3@test.com";
        data[2][1] = "Subject Line 3";
        data[3][0] = "email4@test.com";
        data[3][1] = "Subject Line 4";
        data[4][0] = "email5@test.com";
        data[4][1] = "Subject Line 5";

        JTable emailList = new JTable(data, new String[]{"From", "Subject"});
        setSize(200, 400);
        add(new JScrollPane(emailList), "grow");
    }
}
