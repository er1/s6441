package ui.SwingMain;

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
import ui.SwingMessage.ComposeMail;

/**
 *
 * @author anasalkhatib
 */
public class ToolRibbon extends JToolBar implements ActionListener {

    JButton deleteButton;
    JButton forwardButton;
    JButton markUnreadButton;
    JButton inboxButton;
    JButton refreshButton;
    JButton composeButton;
    JButton replyButton;

    /**
     *
     * @param original
     * @return
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
     *
     * @return
     */
    public ToolRibbon() {
        Dimension size = new Dimension(64, 64);
        refreshButton = makeButton("resources/refresh.png", "Send/Receive messages (F5)", size);
        composeButton = makeButton("resources/compose.png", "Compose New Message (Alt + N)", size);
        replyButton = makeButton("resources/reply.png", "Reply to the selected message (Alt + R)", size);
        forwardButton = makeButton("resources/forward.png", "Forward the selected message (Alt + F)", size);
        deleteButton = makeButton("resources/delete.png", "Send the selected message to the trash (Alt + D)", size);

        markUnreadButton = makeButton("resources/unread.png", "Mark this message as not read", size);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doRefresh();
            }
        });
        
        Action refresh = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
                System.out.println("refresh");
                doRefresh();
            }
        };
        refreshButton.getInputMap().put(KeyStroke.getKeyStroke("F5"),"Refresh");
        refreshButton.getActionMap().put("Refresh",refresh);
        
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

        this.setRollover(true);
        this.add(refreshButton);
        this.add(composeButton);
        this.add(replyButton);
        this.add(markUnreadButton);
        this.add(forwardButton);
        this.add(deleteButton);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == composeButton) {
            ComposeMail cmwindow = new ComposeMail();
            cmwindow.setVisible(true);
        }
    }

    private void doRefresh() {
    }

    private void doCompose() {
        ComposeMail compose = new ComposeMail();
        compose.setVisible(true);
    }

    private void doReply() {
    }

    private void doMarkUnread() {
    }

    private void doForward() {
    }

    private void doDelete() {
    }
}
