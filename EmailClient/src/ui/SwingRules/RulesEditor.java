/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.SwingRules;

import Email.MessageController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.LabeledTextField;
import ui.SwingMain.FilterRule;

/**
 *
 * @author Bargavi
 */
public class RulesEditor extends JFrame{
    
    LabeledTextField fromField;
    LabeledTextField subjectField;
    LabeledTextField contentField;
    LabeledTextField moveToField;
    MessageController controller;
    
    public RulesEditor(MessageController controller) {
        super("Rules/Filters");
        this.controller = controller;
    }
    
    public void init() {
        fromField = new LabeledTextField("From contains");
        subjectField = new LabeledTextField("Subject contains");
        contentField = new LabeledTextField("Message text contains");
        moveToField = new LabeledTextField("Move to");
        
        JButton createButton = new JButton("Create");
        createButton.setToolTipText("create rule");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                createRule();
                setVisible(false);
                dispose();
            }   
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                dispose();
            }
        });
        
        JPanel headerPanel = new JPanel();
        BoxLayout headerLayout = new BoxLayout(headerPanel, BoxLayout.Y_AXIS);
        headerPanel.setLayout(headerLayout);
        
        headerPanel.add(fromField);
        headerPanel.add(subjectField);
        headerPanel.add(contentField);
        headerPanel.add(moveToField);
        
        JPanel footerPanel = new JPanel();
        BoxLayout footerLayout = new BoxLayout(footerPanel, BoxLayout.X_AXIS);
        footerPanel.setLayout(footerLayout);

        footerPanel.add(createButton);
        footerPanel.add(cancelButton);
        
        this.setLayout(new BorderLayout());

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(footerPanel, BorderLayout.SOUTH);
        this.setSize(500, 200);

    }
    
    void createRule() {
        
        FilterRule rule = new FilterRule(controller);
        
        rule.setFromField(fromField.getText());
        rule.setsubjectField(subjectField.getText());
        rule.setcontentField(contentField.getText());
        rule.setmoveToField(moveToField.getText());
        rule.createFilterRule();
        
    }
    
}
