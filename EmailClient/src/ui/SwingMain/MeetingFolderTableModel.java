package ui.SwingMain;

import Email.MessageController;
import Meeting.MeetingSummary;
import ui.SwingMain.TableModelInterface;

/**
 *
 * @author chanman
 */
class MeetingFolderTableModel extends TableModelInterface {

    MessageController controller;
    protected String[] meetings;

    public MeetingFolderTableModel(String folderId) {
        this.controller = MessageController.getInstance();
        meetings = controller.getEmailList(folderId);
    }

    @Override
    public int getRowCount() {
        return meetings.length;
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
                return "Start Time";
            case 2:
                return "End Time";
            case 3:
                return "Subject";
            default:
                return new String();
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        MeetingSummary summary = controller.getMeetingSummary(meetings[row]);

        switch (col) {
            case -1:
                return meetings[row];
            case 0:
                return summary.getMeetingDate();
            case 1:
                return summary.getStartTime();
            case 2:
                return summary.getEndTime();
            case 3:
                return summary.getSubject();
            default:
                return new String();
        }

    }

    @Override
    public String getMessageId(int selected) {
        try {
            return meetings[selected];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
