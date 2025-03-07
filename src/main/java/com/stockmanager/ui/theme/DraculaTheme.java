// DraculaTheme.java - Complete implementation
package com.stockmanager.ui.theme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Implements the Dracula purple theme for the application
 */
public class DraculaTheme {
    // Dracula color palette
    public static final Color BACKGROUND = new Color(40, 42, 54);
    public static final Color CURRENT_LINE = new Color(68, 71, 90);
    public static final Color FOREGROUND = new Color(248, 248, 242);
    public static final Color COMMENT = new Color(98, 114, 164);
    public static final Color CYAN = new Color(139, 233, 253);
    public static final Color GREEN = new Color(80, 250, 123);
    public static final Color ORANGE = new Color(255, 184, 108);
    public static final Color PINK = new Color(255, 121, 198);
    public static final Color PURPLE = new Color(189, 147, 249);
    public static final Color RED = new Color(255, 85, 85);
    public static final Color YELLOW = new Color(241, 250, 140);
    
    // Derived colors
    public static final Color BUTTON_BACKGROUND = PURPLE;
    public static final Color BUTTON_FOREGROUND = FOREGROUND;
    public static final Color BUTTON_HOVER = new Color(159, 117, 219);
    public static final Color BUTTON_PRESSED = new Color(129, 87, 189);
    
    /**
     * Applies the Dracula theme to the UI
     */
    public static void apply() {
        try {
            // Set cross-platform look and feel as a base
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            
            // Set global UI colors
            UIManager.put("Panel.background", new ColorUIResource(BACKGROUND));
            UIManager.put("OptionPane.background", new ColorUIResource(BACKGROUND));
            UIManager.put("OptionPane.messageForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("Label.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextField.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("TextField.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextField.caretForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextArea.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("TextArea.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextArea.caretForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("ComboBox.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("ComboBox.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("ComboBox.selectionBackground", new ColorUIResource(PURPLE));
            UIManager.put("ComboBox.selectionForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("Button.background", new ColorUIResource(BUTTON_BACKGROUND));
            UIManager.put("Button.foreground", new ColorUIResource(BUTTON_FOREGROUND));
            UIManager.put("Button.select", new ColorUIResource(BUTTON_PRESSED));
            UIManager.put("Button.focus", new ColorUIResource(BUTTON_HOVER));
            UIManager.put("ScrollPane.background", new ColorUIResource(BACKGROUND));
            UIManager.put("ScrollBar.thumb", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.track", new ColorUIResource(BACKGROUND));
            UIManager.put("Table.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Table.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("Table.selectionBackground", new ColorUIResource(CURRENT_LINE));
            UIManager.put("Table.selectionForeground", new ColorUIResource(CYAN));
            UIManager.put("Table.gridColor", new ColorUIResource(COMMENT));
            UIManager.put("TableHeader.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("TableHeader.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("Dialog.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Frame.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Menu.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Menu.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("MenuItem.background", new ColorUIResource(BACKGROUND));
            UIManager.put("MenuItem.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("MenuItem.selectionBackground", new ColorUIResource(CURRENT_LINE));
            UIManager.put("MenuItem.selectionForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("MenuBar.background", new ColorUIResource(BACKGROUND));
            UIManager.put("MenuBar.foreground", new ColorUIResource(FOREGROUND));
            
            // Create and set a custom border
            Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
            UIManager.put("TextField.border", emptyBorder);
            UIManager.put("TextArea.border", emptyBorder);
            UIManager.put("ComboBox.border", emptyBorder);
            UIManager.put("Button.border", emptyBorder);
            
        } catch (Exception e) {
            System.err.println("Failed to apply Dracula theme: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a default table cell renderer with Dracula theme colors
     */
    public static DefaultTableCellRenderer createTableCellRenderer() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(BACKGROUND);
        renderer.setForeground(FOREGROUND);
        return renderer;
    }
}