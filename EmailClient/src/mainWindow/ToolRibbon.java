package mainWindow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class ToolRibbon extends JFrame implements ActionListener {

    JButton deleteButton = new JButton();
    JButton forwardButton = new JButton();
    JButton readButton = new JButton();
    JButton inboxButton = new JButton();
    JButton refreshButton = new JButton();
    JButton composeButton = new JButton();
    JButton replyButton = new JButton();
    mainWindow.ComposeMail obj_2 = new mainWindow.ComposeMail();

    public ImageIcon resizeImageIcon(ImageIcon original) {
        Image img = original.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null),
                img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, 64, 64, null);
        ImageIcon resizedImage = new ImageIcon(bi);
        return resizedImage;
    }

    public JToolBar toolbar() {

        //TODO: resize icons
        refreshButton.setIcon(resizeImageIcon(new ImageIcon("resources/refresh.png")));
        refreshButton.setFocusable(false);
        //refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //refreshButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        refreshButton.setToolTipText("Downloads New Mail");
        refreshButton.setSize(64, 64);

        composeButton.setIcon(resizeImageIcon(new ImageIcon("resources/compose.png")));
        composeButton.setFocusable(false);
        //composeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //composeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        composeButton.setToolTipText("Compose Mail");
        composeButton.addActionListener(this);
        composeButton.setSize(64, 64);

        replyButton.setIcon(resizeImageIcon(new ImageIcon("resources/reply.png")));
        replyButton.setFocusable(false);
        //replyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //replyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        replyButton.setToolTipText("Reply Mail");
        replyButton.setSize(64, 64);

        inboxButton.setIcon(resizeImageIcon(new ImageIcon("resources/inbox.png")));
        inboxButton.setFocusable(false);
        //indoxButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //indoxButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inboxButton.setToolTipText("Inbox");
        inboxButton.setSize(64, 64);

        readButton.setIcon(resizeImageIcon(new ImageIcon("resources/unread.png")));
        readButton.setFocusable(false);
        //readButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //readButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        readButton.setToolTipText("Unreaded Mail");
        readButton.setSize(64, 64);

        forwardButton.setIcon(resizeImageIcon(new ImageIcon("resources/forward.png")));
        forwardButton.setFocusable(false);
        //forwardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //forwardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        forwardButton.setToolTipText("Forward Mail");
        forwardButton.setSize(64, 64);

        deleteButton.setIcon(resizeImageIcon(new ImageIcon("resources/delete.png")));
        deleteButton.setFocusable(false);
        //deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        //deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        deleteButton.setToolTipText("Delete Mail");
        deleteButton.setSize(64, 64);

        JToolBar tool_bar = new javax.swing.JToolBar();
        //tool_bar.setLayout((new MigLayout()));
        tool_bar.setRollover(true);
        tool_bar.add(refreshButton);
        tool_bar.add(composeButton);
        tool_bar.add(replyButton);
        tool_bar.add(inboxButton);
        tool_bar.add(readButton);
        tool_bar.add(forwardButton);
        tool_bar.add(deleteButton);
        return tool_bar;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == composeButton) {
            JFrame cmwindow = new JFrame();
            cmwindow = obj_2.writeMail();
        }
    }
}