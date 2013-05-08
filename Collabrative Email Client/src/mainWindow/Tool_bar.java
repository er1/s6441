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

public class Tool_bar extends JFrame implements ActionListener {

    JButton delete_tool = new JButton();
    JButton forward_tool = new JButton();
    JButton read_tool = new JButton();
    JButton indox_tool = new JButton();
    JButton referesh_tool = new JButton();
    JButton compose_tool = new JButton();
    JButton reply_tool = new JButton();
    mainWindow.Compose_Mail obj_2 = new mainWindow.Compose_Mail();

    public JToolBar toolbar() {
        JToolBar tool_bar = new javax.swing.JToolBar();
        tool_bar.setRollover(true);
        //TODO: resize icons
        referesh_tool.setIcon(new ImageIcon("resources/refresh.png"));
        referesh_tool.setFocusable(false);
        referesh_tool.setHorizontalTextPosition(SwingConstants.CENTER);
        referesh_tool.setVerticalTextPosition(SwingConstants.BOTTOM);
        referesh_tool.setToolTipText("Downloads New Mail");
        tool_bar.add(referesh_tool);
        compose_tool.setIcon(new ImageIcon("resources/compose.png"));
        compose_tool.setFocusable(false);
        compose_tool.setHorizontalTextPosition(SwingConstants.CENTER);
        compose_tool.setVerticalTextPosition(SwingConstants.BOTTOM);
        compose_tool.setToolTipText("Compose Mail");
        compose_tool.addActionListener(this);
        tool_bar.add(compose_tool);
        reply_tool.setIcon(new ImageIcon("resources/reply.png"));
        reply_tool.setFocusable(false);
        reply_tool.setHorizontalTextPosition(SwingConstants.CENTER);
        reply_tool.setVerticalTextPosition(SwingConstants.BOTTOM);
        reply_tool.setToolTipText("Reply Mail");
        tool_bar.add(reply_tool);
        indox_tool.setIcon(new ImageIcon("resources/inbox.png"));
        indox_tool.setFocusable(false);
        indox_tool.setHorizontalTextPosition(SwingConstants.CENTER);
        indox_tool.setVerticalTextPosition(SwingConstants.BOTTOM);
        indox_tool.setToolTipText("Inbox");
        tool_bar.add(indox_tool);
        read_tool.setIcon(new ImageIcon("resources/unread.png"));
        read_tool.setFocusable(false);
        read_tool.setHorizontalTextPosition(SwingConstants.CENTER);
        read_tool.setVerticalTextPosition(SwingConstants.BOTTOM);
        read_tool.setToolTipText("Unreaded Mail");
        tool_bar.add(read_tool);
        forward_tool.setIcon(new ImageIcon("resources/forward.png"));
        forward_tool.setFocusable(false);
        forward_tool.setHorizontalTextPosition(SwingConstants.CENTER);
        forward_tool.setVerticalTextPosition(SwingConstants.BOTTOM);
        forward_tool.setToolTipText("Forward Mail");
        tool_bar.add(forward_tool);
        delete_tool.setIcon(new ImageIcon("resources/delete.png"));
        delete_tool.setFocusable(false);
        delete_tool.setHorizontalTextPosition(SwingConstants.CENTER);
        delete_tool.setVerticalTextPosition(SwingConstants.BOTTOM);
        delete_tool.setToolTipText("Delete Mail");
        tool_bar.add(delete_tool);
        return tool_bar;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compose_tool) {
            JFrame cmwindow = new JFrame();
            cmwindow = obj_2.writeMail();
        }
    }
}
