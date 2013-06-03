package ui.SwingMain;

import Email.MessageController;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.Action;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import ui.SwingMeeting.MeetingEditor;
import ui.SwingMessage.MessageEditor;
import ui.SwingRules.RulesEditor;

/**
 * Creating and handling tool bar menu
 *
 * @author anasalkhatib
 */
public class ToolRibbon extends JToolBar {

    JButton deleteButton;
    JButton forwardButton;
    JButton markUnreadButton;
    JButton inboxButton;
    JButton refreshButton;
    JButton composeButton;
    JButton replyButton;
    JButton ruleButton;
    JButton meetingsButton;
    MessageController controller;
    String currentMessage;
    String currentFolder;

    /**
     * Function to resize image icon
     *
     * @param original
     * @return resizedImage
     */
    public ImageIcon resizeImageIcon(ImageIcon original) {
        Image img = original.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null),
                img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, 64, 64, null);
        ImageIcon resizedImage = new ImageIcon(bi);
        return resizedImage;
    }

    private JButton makeButton(String IconPath, String tooltip, Dimension size) {
        JButton button = new JButton();
        button.setIcon(resizeImageIcon(new ImageIcon(IconPath)));
        button.setToolTipText(tooltip);
        button.setSize(size);
        button.setMinimumSize(size);
        return button;
    }

    /**
     * constructor for toolBar with toolTip and hotKeys
     */
    public ToolRibbon() {
        controller = MessageController.getInstance();

        Dimension size = new Dimension(64, 64);
        refreshButton = makeButton("resources/refresh.png", "Send/Receive messages (F5)", size);
        composeButton = makeButton("resources/compose.png", "Compose New Message (Alt + N)", size);
        replyButton = makeButton("resources/reply.png", "Reply to the selected message (Alt + R)", size);
        forwardButton = makeButton("resources/forward.png", "Forward the selected message (Alt + F)", size);
        deleteButton = makeButton("resources/delete.png", "Send the selected message to the trash (Alt + D)", size);
        meetingsButton = makeButton("resources/meetings.png", "Create a new meeting (Alt + M)", size);

        markUnreadButton = makeButton("resources/unread.png", "Mark this message as not read", size);
        ruleButton = makeButton("resources/rules.png", "Rules/filters (F6)", size);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doRefresh();
            }
        });

        Action refresh = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doRefresh();
            }
        };
        refreshButton.getInputMap().put(KeyStroke.getKeyStroke("F5"), "Refresh");
        refreshButton.getActionMap().put("Refresh", refresh);

        composeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doCompose();
            }
        });
        composeButton.setMnemonic(KeyEvent.VK_N);

        markUnreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doMarkUnread();
            }
        });


        replyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doReply();
            }
        });
        replyButton.setMnemonic(KeyEvent.VK_R);

        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doForward();
            }
        });
        forwardButton.setMnemonic(KeyEvent.VK_F);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doDelete();
            }
        });
        deleteButton.setMnemonic(KeyEvent.VK_D);

        ruleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doRule();
            }
        });

        Action rule = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doRule();
            }
        };

        ruleButton.getInputMap().put(KeyStroke.getKeyStroke("F6"), "Rules");
        ruleButton.getActionMap().put("Rules", rule);

        meetingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doCreateMeeting();
            }
        });
        meetingsButton.setMnemonic(KeyEvent.VK_M);

        this.setRollover(true);
        this.add(refreshButton);
        this.add(composeButton);
        this.add(replyButton);
        this.add(markUnreadButton);
        this.add(forwardButton);
        this.add(deleteButton);
        this.add(ruleButton);
        this.add(meetingsButton);

    }

    private void doRefresh() {
        controller.doSendRecieve();
    }

    private void doCompose() {
        String id = controller.compose();
        MessageEditor compose = new MessageEditor(id, MessageEditor.Type.COMPOSE);
        compose.init();
        compose.setVisible(true);
    }

    private void doCreateMeeting() {
        String id = controller.createMeeting();
        MeetingEditor meeting = new MeetingEditor(id);
        meeting.init();
        meeting.setVisible(true);
    }

    private void doReply() {
        String id = controller.reply(currentMessage);
        MessageEditor compose = new MessageEditor(id, MessageEditor.Type.COMPOSE);
        compose.init();
        compose.setVisible(true);
    }

    private void doMarkUnread() {
        controller.markUnread(currentMessage);
    }

    private void doForward() {
        String id = controller.forward(currentMessage);
        MessageEditor compose = new MessageEditor(id, MessageEditor.Type.COMPOSE);
        compose.init();
        compose.setVisible(true);
    }

    private void doDelete() {
        String trash = controller.getTrashFolderId();
        if (currentFolder.equals(trash)) {
            controller.delete(currentMessage);
        } else {
            controller.moveMessageToFolder(currentMessage, trash);
        }
    }

    private void doRule() {
        RulesEditor rules = new RulesEditor();
        rules.init();
        rules.setVisible(true);
    }

    public void setSelectedMessage(String messageid) {
        currentMessage = messageid;
    }

    public void setSelectedFolder(String folderid) {
        currentFolder = folderid;
    }
}
