/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

/**
 * Object to represent a rule
 * @author Bargavi
 */
public class FilterRule {

    String ruleId;
    String fromText;
    String subjectText;
    String contentText;
    String moveToFolder;

    /**
     * Function to match the rules given the message id
     * @param messageid
     * @return True/False
     */
    public boolean matches(String messageid) {

        MessageController controller = MessageController.getInstance();
        if (controller.getEmailHeader(messageid, "X-MeetingId") != "") {
            return false;
        }
        String from = controller.getEmailHeader(messageid, "From");
        String subject = controller.getEmailHeader(messageid, "Subject");
        String content = controller.getEmailContent(messageid);

        if ((from.toLowerCase().contains(fromText.toLowerCase()))
                && (subject.toLowerCase().contains(subjectText.toLowerCase()))
                && (content.toLowerCase().contains(contentText.toLowerCase()))) {
            return true;
        }
        return false;
    }

    /**
     * Set the From Field 
     * @param fromText
     */
    public void setFromField(String fromText) {
        this.fromText = fromText;
    }

    /**
     * Set the Subject Field
     * @param subjectText
     */
    public void setsubjectField(String subjectText) {
        this.subjectText = subjectText;
    }

    /**
     * Set the message content field
     * @param contentText
     */
    public void setcontentField(String contentText) {
        this.contentText = contentText;
    }

    /**
     * Set move to field
     * @param moveToFolder
     */
    public void setmoveToField(String moveToFolder) {
        this.moveToFolder = moveToFolder;
    }

    /**
     * Get the from field
     * @return from field
     */
    public String getFromField() {
        return this.fromText;
    }

    /**
     * Get the subject field
     * @return subject field
     */
    public String getsubjectField() {
        return this.subjectText;
    }

    /**
     * Get the message content field
     * @return content field
     */
    public String getcontentField() {
        return this.contentText;
    }

    /**
     * Get the move to field
     * @return moveTo field
     */
    public String getmoveToField() {
        return this.moveToFolder;
    }

    /**
     * Get the rule id
     * @return id
     */
    public String getRuleId() {
        return this.ruleId;
    }

    /**
     * Set the rule id  
     * @param ruleId 
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}
