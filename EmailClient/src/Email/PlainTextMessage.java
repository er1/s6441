package Email;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import util.Util;

/**
 * Implementation of Message as something that can be stored as a String;
 *
 * @author chanman
 */
public class PlainTextMessage implements Message {

    private String content = new String();
    private HashMap<String, String> header;
    // ID used the the messagecontroller for bookeeping
    private String id = new String();

    /**
     * Constructor to initialize header
     */
    public PlainTextMessage() {
        this.header = Util.newHashMap();
    }

    /**
     * Get id
     *
     * @return id
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Set id
     *
     * @param id
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setContent(String newcontent) {
        content = newcontent;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getHeaderValue(String key) {
        String value = header.get(key);
        if (value == null) {
            value = "";
        }
        return value;
    }

    @Override
    public void setHeader(String key, String value) {
        // key and value should be striped of awkward whitespace and newlines
        if ((value == null) || "".equals(value)) {
            header.remove(key);
        } else {
            header.put(key, value);
        }
    }

    @Override
    public void appendToHeader(String key, String value) {
        // key and value should be striped of awkward whitespace and newlines
        if ((value == null) || "".equals(value)) {
            return;
        }
        if (header.containsKey(key)) {
            String orig = header.get(key);
            orig = orig + ", " + value;
            header.put(key, orig);
        } else {
            header.put(key, value);
        }
    }

    /**
     * Turn this Message into a String for storage
     *
     * @return raw message
     */
    public String serialize() {
        String msg = new String();

        // add headers
        for (String key : header.keySet()) {
            msg += key + ": " + this.getHeaderValue(key).replaceAll("\\s*\n\\s*", "\r\n    ") + "\r\n";
        }

        // blank line to separate headers from content
        msg += "\r\n";

        // add content
        msg += content;

        return msg;
    }

    /**
     * Parse a raw message from storage into this object
     *
     * @param rawmsg
     * @return object of PlainTextMessage
     */
    public static PlainTextMessage parse(String rawmsg) {
        PlainTextMessage msg = new PlainTextMessage();

        // Zero or more lines of header followed by an empty line
        Pattern headerlines = Pattern.compile("^([^\r\n]+\r?\n)*\r?\n");
        Matcher matcher = headerlines.matcher(rawmsg);

        if (!matcher.find()) {
            return null;
        }

        String allLines = rawmsg.substring(matcher.start(), matcher.end());
        String[] lines = allLines.split("\n");

        String lastKey = null;

        for (String line : lines) {
            int colon = line.indexOf(":");

            // colon not found, add to the last line
            if (colon < 0 && lastKey != null) {
                if (line.trim().length() > 0) {
                    msg.setHeader(lastKey, msg.getHeaderValue(lastKey) + "\n" + line.trim());
                }
                continue;
            }

            // extract key and value and trim whitespace
            String key = line.substring(0, colon).trim();
            String value = line.substring(colon + 1).trim();

            // set values
            if (key.length() > 0) {
                lastKey = key;
                msg.setHeader(key, value);
            }
        }

        msg.setContent(rawmsg.substring(matcher.end()));

        return msg;
    }
}
