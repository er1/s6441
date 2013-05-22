/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Labeled Text Field class returns text field with labels and tool tip 
 * inserted into a Container
 * @author KarthikKrishnan
 */
public class LabeledTextField extends Container {

    JLabel label;
    JTextField textField;
    BorderLayout layout;

    /**
     * constructor to initialize label and textField
     * @param label
     */
    public LabeledTextField(String label) {
        this.layout = new BorderLayout();
        this.label = new JLabel(label + ":");
        this.textField = new JTextField();

        this.setLayout(layout);

        this.add(this.label, BorderLayout.WEST);
        this.add(this.textField);
    }

    /**
     * Set text to textField
     * @param text
     */
    public void setText(String text) {
        textField.setText(text);
    }

    /**
     * Get text from textField
     * @return text
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Set tool tip 
     * @param tooltip
     * @return LabeledTextField object with updated tooltip
     */
    public LabeledTextField tooltip(String tooltip) {
        textField.setToolTipText(tooltip);
        return this;
    }
}
