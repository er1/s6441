/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persist;

import Email.FilterRule;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author chanman
 */
public class TestStorage extends PersistentStorage {

    HashMap<String, String> content = new HashMap<String, String>();

    @Override
    public String getMailboxID() {
        return "TEST_MAILBOX";
    }

    @Override
    public boolean newFolder(String fullPath) {
        content.put(fullPath + File.separator, "");
        return true;
    }

    @Override
    public boolean newFolderInMailbox(String newFolderPath) {
        content.put(newFolderPath + File.separator, "");
        return true;
    }

    @Override
    public boolean newRuleFileInMailbox(String path) {
        return true;
    }

    /**
     * Load rules from file system when startup
     * @return listOfRules
     */
    @Override
    public ArrayList<FilterRule> loadRulesFromFileSystem() {
        return new ArrayList<FilterRule>();
    }

    /**
     * Store rules to file system
     * @param listOfRules
     */
    @Override
    public void saveRulesToFileSystem(ArrayList<FilterRule> listOfRules) {
    }

    @Override
    public ArrayList<String> loadMessageListFromFolder(String folder) {
        Set<String> keys = content.keySet();
        ArrayList<String> ret = new ArrayList<String>();

        for (String k : keys) {
            if (k.indexOf(folder) == 0) {
                String n = k.substring(folder.length() + 1);
                if (n.indexOf(File.separator) == n.lastIndexOf(File.separator) && n.lastIndexOf(File.separator) != n.length() - 1) {
                    ret.add(k);
                }
            }
        }

        return ret;
    }

    @Override
    public ArrayList<String> loadSubfolders(String folder) {
        Set<String> keys = content.keySet();
        ArrayList<String> ret = new ArrayList<String>();

        for (String k : keys) {
            if (k.indexOf(folder) == 0) {
                String n = k.substring(folder.length() + 1);
                if (n.indexOf(File.separator) == n.lastIndexOf(File.separator) && n.lastIndexOf(File.separator) == n.length() - 1) {
                    ret.add(k);
                }
            }
        }

        return ret;
    }

    @Override
    public boolean deleteFolderAndAllContents(String folder) {
        Set<String> keys = content.keySet();

        for (String k : keys) {
            if (k.indexOf(folder) == 0) {
                content.remove(k);
            }
        }

        return true;
    }

    @Override
    public boolean moveMessageToFolder(String message, String folder) {
        String nk = folder + message.substring(message.lastIndexOf(File.separator));

        String c = content.get(message);
        content.remove(message);
        content.put(nk, c);
        return true;
    }

    @Override
    public boolean newMessage(String folder) {
        return true;
    }

    @Override
    public boolean saveMessage(String message, String content) {
        this.content.put(message, content);
        return true;
    }

    @Override
    public String loadMessage(String message) {
        return content.get(message);
    }

    @Override
    public boolean deleteMessage(String message) {
        content.remove(message);
        return true;
    }

    @Override
    public boolean moveFolder(String folderToMove, String destinationFolder) {
        Set<String> keys = content.keySet();

        String name = folderToMove.substring(folderToMove.lastIndexOf(File.separator) + 1);

        for (String k : keys) {
            if (k.indexOf(folderToMove) == 0) {
                String nk = destinationFolder + File.pathSeparator + name + k.substring(folderToMove.length());

                String c = content.get(k);
                content.remove(k);
                content.put(nk, c);
            }
        }

        return true;
    }

    /**
     * Check folder exists or not
     * @param folderPath
     * @return True/False
     */
    @Override
    public boolean isFolderExists(String folderPath) {
        return content.containsKey(folderPath + File.separator);
    }
}
