package mainWindow;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolRibbon extends JFrame implements ActionListener {

    JButton deleteButton = new JButton();
    JButton forwardButton = new JButton();
    JButton readButton = new JButton();
    JButton indoxButton = new JButton();
    JButton refereshButton = new JButton();
    JButton composeButton = new JButton();
    JButton replyButton = new JButton();
    mainWindow.ComposeMail obj_2 = new mainWindow.ComposeMail();

    public JToolBar toolbar() {

        //TODO: resize icons
        refereshButton.setIcon(new ImageIcon("resources/refresh.png"));
        refereshButton.setFocusable(false);
        refereshButton.setHorizontalTextPosition(SwingConstants.CENTER);
        refereshButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        refereshButton.setToolTipText("Downloads New Mail");

        composeButton.setIcon(new ImageIcon("resources/compose.png"));
        composeButton.setFocusable(false);
        composeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        composeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        composeButton.setToolTipText("Compose Mail");
        composeButton.addActionListener(this);

        replyButton.setIcon(new ImageIcon("resources/reply.png"));
        replyButton.setFocusable(false);
        replyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        replyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        replyButton.setToolTipText("Reply Mail");

        indoxButton.setIcon(new ImageIcon("resources/inbox.png"));
        indoxButton.setFocusable(false);
        indoxButton.setHorizontalTextPosition(SwingConstants.CENTER);
        indoxButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        indoxButton.setToolTipText("Inbox");

        readButton.setIcon(new ImageIcon("resources/unread.png"));
        readButton.setFocusable(false);
        readButton.setHorizontalTextPosition(SwingConstants.CENTER);
        readButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        readButton.setToolTipText("Unreaded Mail");

        forwardButton.setIcon(new ImageIcon("resources/forward.png"));
        forwardButton.setFocusable(false);
        forwardButton.setHorizontalTextPosition(SwingConstants.CENTER);
        forwardButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        forwardButton.setToolTipText("Forward Mail");

        deleteButton.setIcon(new ImageIcon("resources/delete.png"));
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        deleteButton.setToolTipText("Delete Mail");


        JToolBar tool_bar = new javax.swing.JToolBar();
        tool_bar.setRollover(true);
        tool_bar.add(refereshButton);
        tool_bar.add(composeButton);
        tool_bar.add(replyButton);
        tool_bar.add(indoxButton);
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
