/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.SwingMain;

import javax.swing.table.AbstractTableModel;

/**
 * Table model interface
 * @author anasalkhatib
 */
public abstract class TableModelInterface extends AbstractTableModel {

    abstract String getMessageId(int selected);
}
