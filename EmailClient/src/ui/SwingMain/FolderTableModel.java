package ui.SwingMain;

import Email.MessageController;
import Email.Summary;

/**
 * Table model according to particular folder
 * @author chanman
 */
class FolderTableModel extends TableModelInterface {

    MessageController controller;
    protected String[] messages;
    boolean isOutbound = false;

    public FolderTableModel(String folderId) {
        this.controller = MessageController.getInstance();
        messages = controller.getEmailList(folderId);

        if ((folderId.equals(controller.getOutboxFolderId()))
                || (folderId.equals(controller.getSentMessagesFolderId()))
                || (folderId.equals(controller.getDraftsFolderId()))) {
            isOutbound = true;

        }
    }

    @Override
    public int getRowCount() {
        return messages.length;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Date";
            case 1:
                if (isOutbound) {
                    return "To";
                } else {
                    return "From";
                }
            case 2:
                return "Subject";
            case 3:
                return "Read";
            default:
                return new String();
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Summary summary = controller.getEmailSummary(messages[row]);

        switch (col) {
            case -1:
                return messages[row];

            case 0:
                return summary.getDate();
            case 1:
                if (isOutbound) {
                    return summary.getTo();
                } else {
                    return summary.getFrom();
                }

            case 2:
                return summary.getSubject();
            case 3:
                if (summary.isRead()) {
                    return "";
                } else {
                    return "Unread";
                }
            default:
                return new String();
        }

    }

    @Override
    public String getMessageId(int selected) {
        try {
            return messages[selected];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
