// DeleteDialog.java - Complete implementation
package com.stockmanager.ui.stock;

import javax.swing.*;
import java.awt.*;

import com.stockmanager.controllers.StockController;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Dialog for confirming the deletion of a stock item
 */
public class DeleteDialog extends JDialog {
    private JLabel messageLabel;
    private JButton confirmButton;
    private JButton cancelButton;
    
    private String itemId;
    private StockController controller;
    
    public DeleteDialog(JFrame parent, String itemId, String itemName, StockController controller) {
        super(parent, "Confirm Deletion", true);
        this.itemId = itemId;
        this.controller = controller;
        
        // Initialize the UI
        initializeUI(itemName);
    }
    
    private void initializeUI(String itemName) {
        // Set up the dialog
        getContentPane().setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setSize(400, 200);
        setLocationRelativeTo(getParent());
        
        // Create message
        JPanel messagePanel = new JPanel(new BorderLayout(0, 20));
        messagePanel.setBackground(DraculaTheme.BACKGROUND);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));
        
        messageLabel = UIUtils.createStyledLabel(
            "<html>Are you sure you want to delete <b>" + itemName + "</b>?<br>This action cannot be undone.</html>");
        messageLabel.setForeground(DraculaTheme.ORANGE);
        
        messagePanel.add(iconLabel, BorderLayout.WEST);
        messagePanel.add(messageLabel, BorderLayout.CENTER);
        
        add(messagePanel, BorderLayout.CENTER);
        
        // Create buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        confirmButton = UIUtils.createStyledButton("Delete");
        confirmButton.setBackground(DraculaTheme.RED);
        cancelButton = UIUtils.createStyledButton("Cancel");
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Set action listeners
        confirmButton.addActionListener(e -> {
            controller.deleteStockItem(itemId);
            dispose();
        });
        
        cancelButton.addActionListener(e -> dispose());
    }
}