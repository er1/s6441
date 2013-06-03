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
   
    protected String[] messages,folderList;
   
    SearchFolderTableModel(MessageController controller, String folderId, String searchText) {
        super(controller, folderId);
        ArrayList<String> found = Util.newArrayList();
        folderList = controller.getFolderList(folderId);
        for (String folderIDs : folderList){
         messages = controller.getEmailList(folderIDs);
         for(String messageid : messages)
         {
            String to = controller.getEmailHeader(messageid, "To");
            String from = controller.getEmailHeader(messageid, "From");
            String subject = controller.getEmailHeader(messageid, "Subject");
            String content = controller.getEmailContent(messageid);
          /*System.out.println("The to of message ----> " + to);
            System.out.println("The from of message ----> " + from);
            System.out.println("The subject of message ----> " + subject);
            System.out.println("The content of message ----> " + content);
          */
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
        }// End of internal For loop
           
         }// End of outer for loop
         int n = found.size();
         for(int i = 0; i < n ; i++)
         System.out.println( found.get( i ) );

    }
}

     