/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LabeledTextField extends Container {

    JLabel label;
    JTextField textField;
    BorderLayout layout;

    public LabeledTextField(String label) {
        this.layout = new BorderLayout();
        this.label = new JLabel(label + ":");
        this.textField = new JTextField();

        this.setLayout(layout);

        this.add(this.label, BorderLayout.WEST);
        this.add(this.textField);
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public String getText() {
        return textField.getText();
    }

    public LabeledTextField tooltip(String tooltip) {
        textField.setToolTipText(tooltip);
        return this;
    }
}
