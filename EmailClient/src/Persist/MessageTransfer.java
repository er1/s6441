package Persist;

import Email.Message;
import Email.PlainTextMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;

public class MessageTransfer {

    static final Logger logger = Logger.getLogger(MessageTransfer.class.getName());

    static {
        logger.setParent(Logger.getLogger(MessageTransfer.class.getPackage().getName()));
    }
    static private MessageTransfer instance = null;

    private MessageTransfer() {
    }

    /**
     * Get the instance of MessageTransfer class
     *
     * @return instance
     */
    static public MessageTransfer getInstance() {
        if (instance == null) {
            instance = new MessageTransfer();
        }
        return instance;
    }
    Queue<String> messageQueue = new LinkedList<String>();

    private ArrayList<String> fetchMessages(String user, String pass) throws IOException {
        ArrayList<String> messages = new ArrayList<String>();
        Socket socket = SocketFactory.getDefault().createSocket("localhost", 60110);
        InputStream sin = socket.getInputStream();
        OutputStream sout = socket.getOutputStream();
        Scanner rin = new Scanner(sin);
        PrintStream rout = new PrintStream(sout, true);

        ArrayList<String> messageLines = new ArrayList<String>();

        String str;

        rin.hasNextLine();
        str = rin.nextLine();
        logger.log(Level.INFO, str);
        if (str.matches("\\+OK.*")) {
        }

        rout.println("USER " + user);

        rin.hasNextLine();
        str = rin.nextLine();
        logger.log(Level.INFO, str);
        if (str.matches("\\+OK.*")) {
        }

        rout.println("PASS " + pass);

        rin.hasNextLine();
        str = rin.nextLine();
        logger.log(Level.INFO, str);
        if (str.matches("\\+OK.*")) {
        }

        rout.println("LIST");

        rin.hasNextLine();
        str = rin.nextLine();
        logger.log(Level.INFO, str);
        if (str.matches("\\+OK.*")) {
        }

        while (true) {
            rin.hasNextLine();
            str = rin.nextLine();
            if (str.matches(".")) {
                break;
            } else {
                messageLines.add(str);
            }
        }

        for (String messageLine : messageLines) {

            rout.print("RETR ");
            rout.println(messageLine.split(" ")[0]);

            rin.hasNextLine();
            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("\\+OK.*")) {
            }

            String content = "";
            while (true) {
                rin.hasNextLine();
                str = rin.nextLine();
                if (str.matches(".")) {
                    break;
                } else {
                    content += (str + "\r\n");
                }
            }

            messages.add(content);

            rout.print("DELE ");
            rout.println(messageLine.split(" ")[0]);

            rin.hasNextLine();
            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("\\+OK.*")) {
            }
        }

        rout.println("QUIT");
        rout.flush();
        socket.close();

        return messages;
    }

    /**
     * Check message exists for given userId
     *
     * @param userID
     * @return True/False
     */
    public boolean MessageExistFor(String userID) throws IOException {
        if (messageQueue.isEmpty()) {
            ArrayList<String> messages = fetchMessages(userID, userID);
            messageQueue.addAll(messages);
        }
        return messageQueue.size() > 0;
    }

    /**
     * Get a message for given userId
     *
     * @param userID
     * @return message
     */
    public String getMessageFor(String userID) {
        return messageQueue.poll();
    }

    /**
     * Send message to userId
     *
     * @param userID
     * @param content
     */
    public void sendMessageTo(String userID, String content) throws IOException {
        {
            if (userID.isEmpty()) {
                return;
            }

            Message msg = PlainTextMessage.parse(content);

            Socket socket;
            socket = SocketFactory.getDefault().createSocket("localhost", 60025);

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            Scanner rin = new Scanner(sin);
            PrintStream rout = new PrintStream(sout, true);

            String str;

            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("220.*")) {
            }

            rout.println("HELO nxhost");

            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("250.*")) {
            } else {
                throw new IOException("SMTP Handshake Error");
            }

            String from = msg.getHeaderValue("From");
            from = from.trim();

            rout.println("MAIL FROM: " + from);

            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("250.*")) {
            } else {
                throw new IllegalArgumentException("Bad sender");
            }

            rout.println("RCPT TO: " + userID);

            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("250.*")) {
            } else {
                throw new IllegalArgumentException("Bad recipient: " + userID);
            }

            rout.println("DATA");

            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("354.*")) {
            } else {
                throw new IOException("Bad state");
            }

            rout.println(content);

            rout.println(".");

            str = rin.nextLine();
            logger.log(Level.INFO, str);
            if (str.matches("250.*")) {
            } else {
                throw new IllegalArgumentException("Bad content");
            }

            rout.println("QUIT");
        }
    }
}

