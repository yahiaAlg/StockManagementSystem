// DashboardPanel.java - Complete implementation
package com.stockmanager.ui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.controllers.StockController;
import com.stockmanager.models.StockItem;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Main dashboard panel showing summary metrics
 */
public class DashboardPanel extends JPanel {
    private StockController stockController;
    private AnalyticsController analyticsController;
    
    private JLabel totalItemsLabel;
    private JLabel totalValueLabel;
    private JLabel lowStockCountLabel;
    
    private JPanel summaryPanel;
    private JPanel lowStockPanel;
    
    public DashboardPanel(StockController stockController, AnalyticsController analyticsController) {
        this.stockController = stockController;
        this.analyticsController = analyticsController;
        
        // Initialize the UI
        initializeUI();
        
        // Load data
        refreshData();
    }
    
    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create header
        JLabel headerLabel = UIUtils.createHeaderLabel("Dashboard");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);
        
        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        contentPanel.setBackground(DraculaTheme.BACKGROUND);
        
        // Create summary metrics panel
        summaryPanel = createSummaryPanel();
        
        // Create low stock panel
        lowStockPanel = createLowStockPanel();
        
        // Create monthly sales panel
        JPanel monthlySalesPanel = createMonthlySalesPanel();
        
        // Create supplier distribution panel
        JPanel supplierPanel = createSupplierPanel();
        
        // Add panels to content panel
        contentPanel.add(summaryPanel);
        contentPanel.add(lowStockPanel);
        contentPanel.add(monthlySalesPanel);
        contentPanel.add(supplierPanel);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JPanel createSummaryPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Summary");
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        
        // Create labels
        JLabel totalItemsCaption = UIUtils.createStyledLabel("Total Items:");
        totalItemsLabel = UIUtils.createStyledLabel("0");
        totalItemsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        totalItemsLabel.setForeground(DraculaTheme.CYAN);
        
        JLabel totalValueCaption = UIUtils.createStyledLabel("Total Inventory Value:");
        totalValueLabel = UIUtils.createStyledLabel("$0.00");
        totalValueLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        totalValueLabel.setForeground(DraculaTheme.GREEN);
        
        JLabel lowStockCaption = UIUtils.createStyledLabel("Low Stock Items:");
        lowStockCountLabel = UIUtils.createStyledLabel("0");
        lowStockCountLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        lowStockCountLabel.setForeground(DraculaTheme.ORANGE);
        
        // Add labels to panel
        panel.add(totalItemsCaption);
        panel.add(totalItemsLabel);
        panel.add(totalValueCaption);
        panel.add(totalValueLabel);
        panel.add(lowStockCaption);
        panel.add(lowStockCountLabel);
        
        return panel;
    }
    
    private JPanel createLowStockPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Low Stock Items");
        panel.setLayout(new BorderLayout());
        
        // This panel will be populated in refreshData()
        return panel;
    }
    
    private JPanel createMonthlySalesPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Monthly Sales");
        panel.setLayout(new BorderLayout());
        
        // In a real application, this would be a chart
        // For this example, we'll use a simple panel with colored bars
        
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Get the data
                Map<String, Double> monthlySales = analyticsController.getMonthlySalesData();
                
                // Find max value
                double maxValue = monthlySales.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);
                
                // Draw bars
                int barWidth = getWidth() / monthlySales.size() - 10;
                int maxHeight = getHeight() - 50;
                int x = 10;
                
                int i = 0;
                for (Map.Entry<String, Double> entry : monthlySales.entrySet()) {
                    String month = entry.getKey();
                    double value = entry.getValue();
                    
                    // Calculate height proportional to value
                    int height = (int)((value / maxValue) * maxHeight);
                    int y = getHeight() - height - 30;
                    
                    // Draw bar
                    g.setColor(getColorForIndex(i));
                    g.fillRect(x, y, barWidth, height);
                    
                    // Draw month label
                    g.setColor(DraculaTheme.FOREGROUND);
                    g.drawString(month, x, getHeight() - 10);
                    
                    x += barWidth + 10;
                    i++;
                }
            }
            
            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK, 
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.RED,
                    DraculaTheme.YELLOW, DraculaTheme.PURPLE, DraculaTheme.CYAN, 
                    DraculaTheme.PINK, DraculaTheme.GREEN, DraculaTheme.ORANGE
                };
                return colors[index % colors.length];
            }
        };
        
        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createSupplierPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory by Supplier");
        panel.setLayout(new BorderLayout());
        
        // In a real application, this would be a pie chart
        // For this example, we'll use a simple panel with colored rectangles
        
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Get the data
                Map<String, Double> data = analyticsController.getSalesBySupplier();
                
                // Find total value
                double total = data.values().stream().mapToDouble(Double::doubleValue).sum();
                
                // Draw rectangles
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int radius = Math.min(centerX, centerY) - 20;
                
                double startAngle = 0;
                int i = 0;
                
                for (Map.Entry<String, Double> entry : data.entrySet()) {
                    String supplier = entry.getKey();
                    double value = entry.getValue();
                    
                    // Calculate angle
                    double angle = 360.0 * (value / total);
                    
                    // Draw arc
                    g.setColor(getColorForIndex(i));
                    g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, (int)startAngle, (int)angle);
                    
                    // Draw legend item
                    int legendX = 10;
                    int legendY = 20 + (i * 20);
                    g.fillRect(legendX, legendY, 15, 15);
                    
                    g.setColor(DraculaTheme.FOREGROUND);
                    NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
                    g.drawString(supplier + " - " + fmt.format(value), legendX + 25, legendY + 12);
                    
                    startAngle += angle;
                    i++;
                }
            }
            
            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK, 
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.YELLOW
                };
                return colors[index % colors.length];
            }
        };
        
        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void refreshData() {
        // Update summary metrics
        List<StockItem> allItems = stockController.getAllStockItems();
        totalItemsLabel.setText(String.valueOf(allItems.size()));
        
        double totalValue = analyticsController.getTotalInventoryValue();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        totalValueLabel.setText(currencyFormat.format(totalValue));
        
        List<StockItem> lowStockItems = analyticsController.getLowStockItems(10);
        lowStockCountLabel.setText(String.valueOf(lowStockItems.size()));
        
        // Update low stock panel
        lowStockPanel.removeAll();
        lowStockPanel.setLayout(new BorderLayout());
        
        // Create a list to display low stock items
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (StockItem item : lowStockItems) {
            listModel.addElement(item.getName() + " - " + item.getQuantity() + " left");
        }
        
        JList<String> list = new JList<>(listModel);
        list.setBackground(DraculaTheme.BACKGROUND);
        list.setForeground(DraculaTheme.FOREGROUND);
        list.setSelectionBackground(DraculaTheme.PURPLE);
        list.setSelectionForeground(DraculaTheme.FOREGROUND);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBackground(DraculaTheme.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        lowStockPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Repaint
        revalidate();
        repaint();
    }
}