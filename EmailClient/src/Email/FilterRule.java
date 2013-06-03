/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Email.MessageController;
import java.util.ArrayList;

/**
 *
 * @author Bargavi
 */
public class FilterRule {
    
    String fromText;
    String subjectText;
    String contentText;
    String moveToFolder;
   
    public boolean matches(String messageid) {
        
        MessageController controller = MessageController.getInstance();
        String from = controller.getEmailHeader(messageid, "From");
        String subject = controller.getEmailHeader(messageid, "Subject");
        String content = controller.getEmailContent(messageid);
        
        if ((from.toLowerCase().contains(fromText.toLowerCase())) &&
            (subject.toLowerCase().contains(subjectText.toLowerCase())) &&    
            (content.toLowerCase().contains(contentText.toLowerCase()))) {
                return true;
            }
        
        return false;

    }
    
    public void setFromField(String fromText) {
        this.fromText = fromText;
    }

    public void setsubjectField(String subjectText) {
        this.subjectText = subjectText;
    }

    public void setcontentField(String contentText) {
        this.contentText = contentText;
    }

    public void setmoveToField(String moveToFolder) {
        this.moveToFolder = moveToFolder;
    }
       
}