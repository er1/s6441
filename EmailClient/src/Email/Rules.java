/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import util.Util;

/**
 *
 * @author Bargavi
 */
public class Rules {
    
      MessageController controller = MessageController.getInstance();
      ArrayList<FilterRule> listOfRules = new ArrayList<FilterRule>();
    
      public Rules() {
          
            controller.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object o1) {
                applyRules(controller.getInboxFolderId());
            }
        });
                  
      }
      
      public void applyRules(String folderId) {
            
        String[] messages = controller.getEmailList(folderId);
        ArrayList<String> found = Util.newArrayList();
        
        for (String messageid : messages) {
            for (FilterRule rule : listOfRules) {
                if (rule.matches(messageid)) {
                    controller.moveMessageToFolder(messageid, rule.moveToFolder);
                    break;
                }
            }
        }
      }
      
      public void addRule(FilterRule rule)
      {
          listOfRules.add(rule);
      }   
}
