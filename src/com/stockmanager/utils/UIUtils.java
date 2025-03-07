// UIUtils.java - Complete implementation
package com.stockmanager.utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Utility methods for UI operations
 */
public class UIUtils {
    /**
     * Creates a styled button with the Dracula theme
     */
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(DraculaTheme.BUTTON_BACKGROUND);
        button.setForeground(DraculaTheme.BUTTON_FOREGROUND);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFont(new Font("Dialog", Font.BOLD, 14));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_HOVER);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_BACKGROUND);
            }
            
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_PRESSED);
            }
            
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_HOVER);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a styled label with the Dracula theme
     */
    public static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(DraculaTheme.FOREGROUND);
        return label;
    }
    
    /**
     * Creates a styled header label with the Dracula theme
     */
    public static JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(DraculaTheme.PURPLE);
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        return label;
    }
    
    /**
     * Creates a styled text field with the Dracula theme
     */
    public static JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(DraculaTheme.CURRENT_LINE);
        textField.setForeground(DraculaTheme.FOREGROUND);
        textField.setCaretColor(DraculaTheme.FOREGROUND);
        textField.setBorder(createTextFieldBorder());
        return textField;
    }
    
    /**
     * Creates a styled text area with the Dracula theme
     */
    public static JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(DraculaTheme.CURRENT_LINE);
        textArea.setForeground(DraculaTheme.FOREGROUND);
        textArea.setCaretColor(DraculaTheme.FOREGROUND);
        textArea.setBorder(createTextFieldBorder());
        return textArea;
    }
    
    /**
     * Creates a styled combo box with the Dracula theme
     */
    public static <T> JComboBox<T> createStyledComboBox(T[] items) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setBackground(DraculaTheme.CURRENT_LINE);
        comboBox.setForeground(DraculaTheme.FOREGROUND);
        comboBox.setBorder(createTextFieldBorder());
        return comboBox;
    }
    
    /**
     * Creates a styled table with the Dracula theme
     */
    public static JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setBackground(DraculaTheme.BACKGROUND);
        table.setForeground(DraculaTheme.FOREGROUND);
        table.setGridColor(DraculaTheme.COMMENT);
        table.getTableHeader().setBackground(DraculaTheme.CURRENT_LINE);
        table.getTableHeader().setForeground(DraculaTheme.FOREGROUND);
        table.setRowHeight(25);
        table.setSelectionBackground(DraculaTheme.PURPLE);
        table.setSelectionForeground(DraculaTheme.FOREGROUND);
        return table;
    }
    
    /**
     * Creates a styled panel with the Dracula theme
     */
    public static JPanel createStyledPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(DraculaTheme.BACKGROUND);
        return panel;
    }
    
    /**
     * Creates a common border for text components
     */
    public static Border createTextFieldBorder() {
        Border lineBorder = new LineBorder(DraculaTheme.COMMENT, 1);
        Border emptyBorder = new EmptyBorder(5, 7, 5, 7);
        return new CompoundBorder(lineBorder, emptyBorder);
    }
    
    /**
     * Creates a panel with titled border
     */
    public static JPanel createPanelWithTitle(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(DraculaTheme.BACKGROUND);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DraculaTheme.COMMENT), 
            title, 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new Font("Dialog", Font.BOLD, 14),
            DraculaTheme.PURPLE));
        return panel;
    }
}