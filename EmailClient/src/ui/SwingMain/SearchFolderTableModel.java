/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.SwingMain;

import java.util.ArrayList;
import util.Util;

/**
 * Table model for search box
 * @author chanman
 */
public class SearchFolderTableModel extends FolderTableModel {

    SearchFolderTableModel(String folderId, String searchText) {
        super(folderId);
        ArrayList<String> filtered = recurse(folderId, searchText);
        
        messages = new String[filtered.size()];
       
        for (int i = 0; i < filtered.size(); i++) {
            messages[i] = filtered.get(i);
        }
    }

    private ArrayList<String> recurse(String folderId, String searchText) {
        ArrayList<String> found = Util.newArrayList();

        for (String msg : controller.getEmailList(folderId)) {
            if (filter(msg, searchText)) {
                found.add(msg);
            }
        }

        for (String sub : controller.getFolderList(folderId)) {
            found.addAll(recurse(sub, searchText));
        }

        return found;
    }

    /**
     * Checks a message has given searchText
     * @param messageid
     * @param searchText
     * @return True/False
     */
    protected boolean filter(String messageid, String searchText) {
        String to = controller.getEmailHeader(messageid, "To");
        String from = controller.getEmailHeader(messageid, "From");
        String subject = controller.getEmailHeader(messageid, "Subject");
        String content = controller.getEmailContent(messageid);

        if (to.toLowerCase().contains(searchText.toLowerCase())) {
            return true;
        }
        if (from.toLowerCase().contains(searchText.toLowerCase())) {
            return true;
        }
        if (subject.toLowerCase().contains(searchText.toLowerCase())) {
            return true;
        }
        if (content.toLowerCase().contains(searchText.toLowerCase())) {
            return true;
        }
        return false;
    }
}
