// StockFormPanel.java - Complete implementation
package com.stockmanager.ui.stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import com.stockmanager.controllers.StockController;
import com.stockmanager.models.StockItem;
import com.stockmanager.models.Supplier;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for creating and updating stock items
 */
public class StockFormPanel extends JPanel {
    private StockController controller;
    
    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<Supplier> supplierComboBox;
    
    private JButton saveButton;
    private JButton cancelButton;
    
    private StockItem currentItem;
    
    public StockFormPanel(StockController controller) {
        this.controller = controller;
        
        // Initialize the UI
        initializeUI();
        
        // Clear the form to start
        clearForm();
    }
    
    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(0, 20));
        
        // Create form header
        JLabel headerLabel = UIUtils.createHeaderLabel("Item Details");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(headerLabel, BorderLayout.NORTH);
        
        // Create form fields panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(DraculaTheme.BACKGROUND);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // ID field (hidden from user but used for updates)
        idField = UIUtils.createStyledTextField();
        idField.setEditable(false);
        idField.setVisible(false);
        
        // Name field
        JLabel nameLabel = UIUtils.createStyledLabel("Name:");
        nameField = UIUtils.createStyledTextField();
        
        // Description field
        JLabel descriptionLabel = UIUtils.createStyledLabel("Description:");
        descriptionArea = UIUtils.createStyledTextArea();
        descriptionArea.setRows(3);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setBackground(DraculaTheme.CURRENT_LINE);
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Price field
        JLabel priceLabel = UIUtils.createStyledLabel("Price:");
        priceField = UIUtils.createStyledTextField();
        
        // Quantity field
        JLabel quantityLabel = UIUtils.createStyledLabel("Quantity:");
        quantityField = UIUtils.createStyledTextField();
        
        // Supplier combo box
        JLabel supplierLabel = UIUtils.createStyledLabel("Supplier:");
        Supplier[] suppliers = controller.getAllSuppliers().toArray(new Supplier[0]);
        supplierComboBox = UIUtils.createStyledComboBox(suppliers);
        
        // Add components to grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(descriptionLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        formPanel.add(descriptionScrollPane, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        formPanel.add(priceLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(priceField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(quantityLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(quantityField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(supplierLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(supplierComboBox, gbc);
        
        add(formPanel, BorderLayout.CENTER);
        
        // Create buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);
        
        saveButton = UIUtils.createStyledButton("Save");
        cancelButton = UIUtils.createStyledButton("Cancel");
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Set action listeners
        saveButton.addActionListener(this::saveItem);
        cancelButton.addActionListener(e -> clearForm());
    }
    
    public void setItem(StockItem item) {
        this.currentItem = item;
        
        idField.setText(item.getId());
        nameField.setText(item.getName());
        descriptionArea.setText(item.getDescription());
        priceField.setText(String.valueOf(item.getPrice()));
        quantityField.setText(String.valueOf(item.getQuantity()));
        
        // Select the correct supplier in the combo box
        Supplier itemSupplier = item.getSupplier();
        for (int i = 0; i < supplierComboBox.getItemCount(); i++) {
            Supplier supplier = supplierComboBox.getItemAt(i);
            if (supplier.getId().equals(itemSupplier.getId())) {
                supplierComboBox.setSelectedIndex(i);
                break;
            }
        }
        
        // Update button text
        saveButton.setText("Update");
    }
    
    public void clearForm() {
        this.currentItem = null;
        
        idField.setText("");
        nameField.setText("");
        descriptionArea.setText("");
        priceField.setText("");
        quantityField.setText("");
        
        if (supplierComboBox.getItemCount() > 0) {
            supplierComboBox.setSelectedIndex(0);
        }
        
        // Update button text
        saveButton.setText("Save");
    }
    
    private void saveItem(ActionEvent e) {
        // Validate inputs
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double price = 0;
        try {
            price = Double.parseDouble(priceField.getText().trim());
            if (price < 0) {
                throw new NumberFormatException("Price cannot be negative");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityField.getText().trim());
            if (quantity < 0) {
                throw new NumberFormatException("Quantity cannot be negative");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Get the selected supplier
        Supplier supplier = (Supplier) supplierComboBox.getSelectedItem();
        if (supplier == null) {
            JOptionPane.showMessageDialog(this, "Please select a supplier.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create or update the item
        if (currentItem == null) {
            // Create new item
            StockItem newItem = new StockItem(
                nameField.getText().trim(),
                descriptionArea.getText().trim(),
                price,
                quantity,
                supplier
            );
            
            controller.saveStockItem(newItem);
            JOptionPane.showMessageDialog(this, "Item saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Update existing item
            currentItem.setName(nameField.getText().trim());
            currentItem.setDescription(descriptionArea.getText().trim());
            currentItem.setPrice(price);
            currentItem.setQuantity(quantity);
            currentItem.setSupplier(supplier);
            
            controller.saveStockItem(currentItem);
            JOptionPane.showMessageDialog(this, "Item updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Clear the form
        clearForm();
        
        // Fire an event to notify that the items have changed
        // In a real app, we would use a proper event system
        // For this example, we'll just refresh the table when Save is clicked
        Component parent = getParent();
        while (parent != null && !(parent instanceof StockListPanel)) {
            parent = parent.getParent();
        }
        
        if (parent instanceof StockListPanel) {
            StockListPanel panel = (StockListPanel) parent;
            // This is a bit of a hack, but for the purpose of this example...
            panel.dispatchEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh"));
        }
    }
}