/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Meeting.MeetingSummary;
import Persist.PersistentStorage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Implementation of rules
 * @author Bargavi
 */
public class Rules {

    static private Rules rules;
    MessageController controller = MessageController.getInstance();
    PersistentStorage persistStore = PersistentStorage.getInstance();
    ArrayList<FilterRule> listOfRules = new ArrayList<FilterRule>();

    private Rules() {
        listOfRules = persistStore.loadRulesFromFileSystem();
        controller.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object o1) {
                applyRules(controller.getInboxFolderId());
            }
        });
    }

    /**
     * Get the list of Rules
     * @return listOfRules array list
     */
    public ArrayList getListOfRules() {
        return listOfRules;
    }

    /**
     * Get the instance of Rules
     * @return Rules instance 
     */
    public static Rules getInstance() {
        if (rules == null) {
            rules = new Rules();
        }
        return rules;
    }

    /**
     * Apply rules to the folder
     * @param folderId
     */
    public void applyRules(String folderId) {

        String[] messages = controller.getEmailList(folderId);

        for (String messageid : messages) {
            // if its a meeting message, dont apply rules
            MeetingSummary meetingSummary = controller.getMeetingSummary(messageid);
            if(! meetingSummary.getMeetingId().isEmpty()) {
                continue;
            }
            for (FilterRule rule : listOfRules) {
                if (rule.matches(messageid)) {
                    controller.moveMessageToFolder(messageid, rule.moveToFolder);
                    break;
                }
            }
        }
    }

    /**
     * Add a rule in the listOfRules
     * @param rule
     */
    public void addRule(FilterRule rule) {
        listOfRules.add(rule);
    }

    /**
     * Delete a rule in the listOfRules
     * @param ruleId
     */
    public void deleteRule(int ruleId) {
        if (listOfRules.size() > ruleId) {
            listOfRules.remove(ruleId);
        }
    }
    /**
     * Save rules in the persistStore
     */
    public void saveRules() {
        persistStore.saveRulesToFileSystem(listOfRules);
    }

    /**
     * Checks a folder exists or not
     * @param folderPath
     * @return True/False
     */
    public boolean isFolderExists(String folderPath) {
        return persistStore.isFolderExists(folderPath);
    }
}
