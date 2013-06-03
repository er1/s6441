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
        System.out.println("Came Here -------->" + controller + "  FolderID Here -------->"  + folderId + "  Text Here -------->" + searchText );
        ArrayList<String> found = Util.newArrayList();
        folderList = controller.getFolderList(folderId);
        for (String folderIDs : folderList){
        System.out.println("The list of folders in the folderID------->> "+ folderIDs);
        messages = controller.getEmailList(folderIDs);
         for(String messageid : messages)
         {
            System.out.println("The list of mails in the folderID------->> "+ messageid);
            String to = controller.getEmailHeader(messageid, "To");
            String from = controller.getEmailHeader(messageid, "From");
            String subject = controller.getEmailHeader(messageid, "Subject");
            String content = controller.getEmailContent(messageid);
            
            if (to.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                System.out.println("The search text in the to of mail in the folderID------->> "+ to);                
                continue;
            }
            if (from.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                System.out.println("The search text in the from of mail in the folderID------->> "+ from); 
                continue;
            }
            if (subject.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                System.out.println("The search text in the subject of mail in the folderID------->> "+ subject); 
                continue;
            }
            if (content.toLowerCase().contains(searchText.toLowerCase())) {
                found.add(messageid);
                System.out.println("The search text in the content mail in the folderID------->> "+ content); 
                continue;
            }
        }// End of internal For loop
      //  messages = (String[])found.toArray();
         }// End of outer for loop
    }
}

     