// StockListPanel.java - Complete implementation
package com.stockmanager.ui.stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.stockmanager.controllers.StockController;
import com.stockmanager.models.StockItem;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for displaying and managing the list of stock items
 */
public class StockListPanel extends JPanel {
    private StockController controller;
    
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    
    private StockFormPanel formPanel;
    
    public StockListPanel(StockController controller) {
        this.controller = controller;
        
        // Initialize the UI
        initializeUI();
        
        // Load data
        loadStockItems();
    }
    
    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create header
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(DraculaTheme.BACKGROUND);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel headerLabel = UIUtils.createHeaderLabel("Stock Management");
        headerPanel.add(headerLabel, BorderLayout.WEST);
        
        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(DraculaTheme.BACKGROUND);
        
        searchField = UIUtils.createStyledTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        JButton searchButton = UIUtils.createStyledButton("Search");
        
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        headerPanel.add(searchPanel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);
        
        // Create main split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(700);
        splitPane.setDividerSize(1);
        splitPane.setBorder(null);
        
        // Create table panel
        JPanel tablePanel = createTablePanel();
        
        // Create form panel
        formPanel = new StockFormPanel(controller);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        splitPane.setLeftComponent(tablePanel);
        splitPane.setRightComponent(formPanel);
        
        add(splitPane, BorderLayout.CENTER);
        
        // Set action listeners
        searchButton.addActionListener(this::searchStockItems);
        searchField.addActionListener(this::searchStockItems);
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(DraculaTheme.BACKGROUND);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);
        
        JButton addButton = UIUtils.createStyledButton("Add New");
        JButton editButton = UIUtils.createStyledButton("Edit");
        JButton deleteButton = UIUtils.createStyledButton("Delete");
        JButton refreshButton = UIUtils.createStyledButton("Refresh");
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        
        // Create table
        String[] columnNames = {"ID", "Name", "Description", "Price", "Quantity", "Supplier", "Total Value"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        stockTable = UIUtils.createStyledTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(stockTable);
        scrollPane.setBackground(DraculaTheme.BACKGROUND);
        scrollPane.getViewport().setBackground(DraculaTheme.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Set action listeners
        addButton.addActionListener(e -> formPanel.clearForm());
        editButton.addActionListener(e -> editSelectedItem());
        deleteButton.addActionListener(e -> deleteSelectedItem());
        refreshButton.addActionListener(e -> loadStockItems());
        
        return panel;
    }
    
    private void loadStockItems() {
        List<StockItem> items = controller.getAllStockItems();
        updateTableWithItems(items);
    }
    
    private void searchStockItems(ActionEvent e) {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            loadStockItems();
        } else {
            List<StockItem> items = controller.searchStockItems(query);
            updateTableWithItems(items);
        }
    }
    
    private void updateTableWithItems(List<StockItem> items) {
        // Clear the table
        tableModel.setRowCount(0);
        
        // Format for currency
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        
        // Add items to the table
        for (StockItem item : items) {
            tableModel.addRow(new Object[] {
                item.getId(),
                item.getName(),
                item.getDescription(),
                currencyFormat.format(item.getPrice()),
                item.getQuantity(),
                item.getSupplier().getName(),
                currencyFormat.format(item.getTotalValue())
            });
        }
        
        // Adjust column widths
        stockTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        stockTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        stockTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        stockTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        stockTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        stockTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        stockTable.getColumnModel().getColumn(6).setPreferredWidth(100);
    }
    
    private void editSelectedItem() {
        int selectedRow = stockTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            StockItem item = controller.getStockItemById(id);
            if (item != null) {
                formPanel.setItem(item);
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Please select an item to edit.",
                "No Selection",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    private void deleteSelectedItem() {
        int selectedRow = stockTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            
            DeleteDialog dialog = new DeleteDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                id,
                name,
                controller
            );
            
            dialog.setVisible(true);
            
            // Refresh the table after deletion
            loadStockItems();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Please select an item to delete.",
                "No Selection",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}