package Persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageTransfer {

    static final Logger logger = Logger.getLogger(MessageTransfer.class.getName());

    static {
        logger.setParent(Logger.getLogger(MessageTransfer.class.getPackage().getName()));
    }
    static private MessageTransfer instance = null;

    private MessageTransfer() {
        File spool = new File(getMailSpool());
        if (!spool.exists()) {
            spool.mkdir();
        }
    }

    /**
     * Get the instance of MessageTransfer class
     * @return instance
     */
    static public MessageTransfer getInstance() {
        if (instance == null) {
            instance = new MessageTransfer();
        }
        return instance;
    }

    private String getMailSpool() {
        return System.getProperty("user.home") + File.separator + "_mailspool";
    }

    private String getMailSpoolFor(String userID) {
        return getMailSpool() + File.separator + userID;
    }

    /**
     * Check message exists for given userId
     * @param userID
     * @return True/False
     */
    public boolean MessageExistFor(String userID) {
        String userinboundPath = getMailSpoolFor(userID);
        File userinbound = new File(userinboundPath);

        // does this exist and is it a directory, if not, false
        if (!userinbound.exists() || !userinbound.isDirectory()) {
            return false;
        }

        // if this has files return true
        return userinbound.listFiles().length > 0;
    }

    /**
     * Get a message for given userId
     * @param userID
     * @return message
     */
    public String getMessageFor(String userID) {
        String userinboundPath = getMailSpoolFor(userID);
        File userinbound = new File(userinboundPath);

        if (!MessageExistFor(userID)) {
            return null;
        }

        File firstFilePicked = userinbound.listFiles()[0];

        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(firstFilePicked));
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append(NL);
            }
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        firstFilePicked.delete();

        return text.toString();

    }

    /**
     * Send message to userId
     * @param userID
     * @param content
     */
    public void sendMessageTo(String userID, String content) {
        if (userID.isEmpty()) {
            return;
        }

        String useroutboundPath = getMailSpoolFor(userID);
        File useroutbound = new File(useroutboundPath);
        if (!useroutbound.exists()) {
            useroutbound.mkdir();
        }

        File path;
        do {
            String name = UUID.randomUUID().toString();
            path = new File(useroutbound, name);
        } while (path.exists());

        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(path);
            outputStream.write(content.getBytes());

        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }
}
