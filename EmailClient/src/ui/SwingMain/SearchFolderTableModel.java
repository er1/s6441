/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.SwingMain;

import Email.MessageController;
import java.util.ArrayList;
import util.Util;

/**
 *
 * @author chanman
 */
public class SearchFolderTableModel extends FolderTableModel {
    
    SearchFolderTableModel(MessageController controller, String folderId, FilterRule rule) {
        super(controller, folderId);

        ArrayList<String> found = Util.newArrayList();

        for (String messageid : messages) {
            if (rule.matches(messageid)) {
                messages = (String[]) found.toArray();
            }
        }
        messages = (String[])found.toArray();
    }
    
    SearchFolderTableModel(MessageController controller, String folderId, String searchText) {
        super(controller, folderId);
    
        ArrayList<String> found = Util.newArrayList();
        
        for (String messageid : messages) {
            String to = controller.getEmailHeader(messageid, "To");
            String from = controller.getEmailHeader(messageid, "From");
            String subject = controller.getEmailHeader(messageid, "Subject");
            String content = controller.getEmailContent(messageid);
            
            if (to.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                continue;
            }
            if (from.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                continue;
            }
            if (subject.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                continue;
            }
            if (content.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                continue;
            }
        }
        
        messages = (String[])found.toArray();
    }
    
}