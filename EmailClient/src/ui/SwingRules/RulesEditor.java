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
import Email.FilterRule;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bargavi
 */
public class RulesEditor extends JFrame{
    
    RulesTableModel model;
    JTable rulesTable;
    
    LabeledTextField fromField;
    LabeledTextField subjectField;
    LabeledTextField contentField;
    LabeledTextField moveToField;
    
    FilterRule selectedRule;
    
    public RulesEditor() {
        super("Rules/Filters");
    }
    
    public void init() {
        
        JPanel rulesPanel = new JPanel();
        
        rulesPanel.setLayout(new GridLayout());
        
        model = new RulesTableModel(MessageController.getInstance());
        model.fireTableDataChanged();
        
        rulesTable = new JTable(model);
        rulesTable.setRowSelectionAllowed(true);
        rulesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        rulesPanel.add(new JScrollPane(rulesTable));
       /* 
        ListSelectionModel lsm = rulesTable.getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                 selectedRule = (FilterRule) model.getValueAt(rulesTable.getSelectedRow(), -1);
            }
        });
        */
        JPanel buttonPanel = new JPanel();
        BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
        buttonPanel.setLayout(buttonLayout);

        JButton createButton = new JButton("Create");
        createButton.setToolTipText("create a new rule");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                createRuleWindow();
            }   
        });
        
        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit a selected rule");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //editRule();
            }   
        });
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Delete a selected rule");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                deleteRule(rulesTable.getSelectedRow());
                model = new RulesTableModel(MessageController.getInstance());
                model.fireTableDataChanged();
                rulesTable.setModel(model);
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
        
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        
        this.setLayout(new BorderLayout());
        this.add(new JLabel("List Of Rules:"), BorderLayout.NORTH);
        this.add(rulesPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(500, 200);
        

    }
    
    void deleteRule(int ruleId) {
        if(ruleId != -1) {
            MessageController.getInstance().deleteRule(ruleId);
        }
    }
    void createRule() {
        
        FilterRule rule = new FilterRule();
        
        String ruleId = Integer.toString(MessageController.getInstance().getRulesCount() + 1); 
        
        rule.setRuleId(ruleId);
        rule.setFromField(fromField.getText());
        rule.setsubjectField(subjectField.getText());
        rule.setcontentField(contentField.getText());
        rule.setmoveToField(moveToField.getText());
        
        MessageController.getInstance().addRule(rule);
    }
    
    void createRuleWindow() {
        final JFrame createFrame = new JFrame ("Create New Rule");
        fromField = new LabeledTextField("From contains");
        subjectField = new LabeledTextField("Subject contains");
        contentField = new LabeledTextField("Message text contains");
        moveToField = new LabeledTextField("Move to");
        
        JButton createButton = new JButton("Create");
        createButton.setToolTipText("create a new rule");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                createRule();
                createFrame.setVisible(false);
                createFrame.dispose();
                model = new RulesTableModel(MessageController.getInstance());
                model.fireTableDataChanged();
                rulesTable.setModel(model);
            }   
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                createFrame.setVisible(false);
                createFrame.dispose();
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
        
        createFrame.setLayout(new BorderLayout());

        createFrame.add(headerPanel, BorderLayout.NORTH);
        createFrame.add(footerPanel, BorderLayout.SOUTH);
        createFrame.setSize(500, 200);
        createFrame.setVisible(true);
        
    }
    
    public static class RulesTableModel extends AbstractTableModel {

        String[] listOfRules;
        MessageController controller;
        String[] columnNames = {"From","Subject","Content","MoveTo.."};
        
        public RulesTableModel(MessageController controller) {
          
            this.controller = controller;
            listOfRules = controller.getRuleList();
        }
        
        @Override
        public int getRowCount() {
            return listOfRules.length;
        }

        @Override
        public int getColumnCount() {   
            return 4;
        }

        @Override
        public Object getValueAt(int row, int col) {
            
            FilterRule rule = controller.getRuleFromId(listOfRules[row]);
            switch (col) {
                case -1:
                    return listOfRules[row];

                case 0:
                    return rule.getFromField();
                
                case 1:
                    return rule.getsubjectField();

                case 2:
                    return rule.getcontentField();
                    
                case 3:
                    return rule.getmoveToField();
                
                default:
                    return new String();
            }
        }
        
        @Override
        public String getColumnName(int col) {
             return columnNames[col];
        }
        
    }
    
    
}
