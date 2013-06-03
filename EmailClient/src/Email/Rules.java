/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Bargavi
 */
public class Rules {

    static private Rules rules;
    MessageController controller = MessageController.getInstance();
    ArrayList<FilterRule> listOfRules = new ArrayList<FilterRule>();

    public Rules() {

        Rules.rules = this;
        //createDummyRule();
        controller.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object o1) {
                applyRules(controller.getInboxFolderId());
            }
        });
    }
    
    private void createDummyRule(){
        FilterRule rule1 = new FilterRule();
        rule1.setRuleId("1");
        rule1.setFromField("Bob");
        rule1.setcontentField("hi");
        rule1.setsubjectField("hi");
        rule1.setmoveToField("test/Inbox/name");
        listOfRules.add(rule1);
    }
    public ArrayList getListOfRules() {
        return listOfRules;
    }
    
    public static Rules getInstance() {
        return rules;
    }

    public void applyRules(String folderId) {

        String[] messages = controller.getEmailList(folderId);

        for (String messageid : messages) {
            for (FilterRule rule : listOfRules) {
                if (rule.matches(messageid)) {
                    controller.moveMessageToFolder(messageid, rule.moveToFolder);
                    break;
                }
            }
        }
    }

    public void addRule(FilterRule rule) {
        listOfRules.add(rule);
    }
    
    public void deleteRule(int ruleId) {
        if(listOfRules.size() > ruleId)
            listOfRules.remove(ruleId);
    }

}
