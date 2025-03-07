// AnalyticsPanel.java - Complete implementation
package com.stockmanager.ui.analytics;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for displaying analytics and data visualization
 */
public class AnalyticsPanel extends JPanel {
    private AnalyticsController controller;

    private JPanel chartPanel1;
    private JPanel chartPanel2;
    private JPanel chartPanel3;
    private JPanel chartPanel4;

    public AnalyticsPanel(AnalyticsController controller) {
        this.controller = controller;

        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create header
        JLabel headerLabel = UIUtils.createHeaderLabel("Analytics Dashboard");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        contentPanel.setBackground(DraculaTheme.BACKGROUND);

        // Create chart panels
        chartPanel1 = createMonthlySalesChart();
        chartPanel2 = createInventoryBySupplierChart();
        chartPanel3 = createInventoryByCategoryChart();
        chartPanel4 = createInventoryLevelsChart();

        contentPanel.add(chartPanel1);
        contentPanel.add(chartPanel2);
        contentPanel.add(chartPanel3);
        contentPanel.add(chartPanel4);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createMonthlySalesChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Monthly Sales");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the monthly sales
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> monthlySales = controller.getMonthlySalesData();

                // Find max value
                double maxValue = monthlySales.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);

                // Draw bars
                int barWidth = getWidth() / monthlySales.size() - 10;
                int maxHeight = getHeight() - 60;
                int x = 10;

                // Draw title and scale
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Sales by Month", 10, 20);

                // Format for currency
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

                // Draw scale
                g.setColor(DraculaTheme.COMMENT);
                g.drawString(currencyFormat.format(maxValue), 10, 40);
                g.drawString("$0", 10, getHeight() - 20);

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
                    g.drawString(month, x + barWidth/2 - 10, getHeight() - 10);

                    // Draw value label
                    if (height > 20) {  // Only draw if there's enough space
                        g.setColor(DraculaTheme.BACKGROUND);
                        String valueStr = currencyFormat.format(value);
                        g.drawString(valueStr, x + barWidth/2 - 20, y + height/2);
                    }

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

    private JPanel createInventoryBySupplierChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory by Supplier");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the supplier distribution
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> data = controller.getSalesBySupplier();

                // Find total value
                double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

                // Draw title
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Inventory Value by Supplier", 10, 20);

                // Draw pie chart
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int radius = Math.min(centerX, centerY) - 60;

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

                    // Calculate position for the label (outside the pie)
                    double radians = Math.toRadians(startAngle + angle/2);
                    int labelX = centerX + (int)((radius + 20) * Math.cos(radians));
                    int labelY = centerY + (int)((radius + 20) * Math.sin(radians));

                    // Draw line from pie to label
                    g.setColor(DraculaTheme.COMMENT);
                    g.drawLine(
                        centerX + (int)(radius * Math.cos(radians)),
                        centerY + (int)(radius * Math.sin(radians)),
                        labelX, labelY
                    );

                    // Draw legend item
                    int legendX = 10;
                    int legendY = getHeight() - 120 + (i * 20);
                    g.setColor(getColorForIndex(i));
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

    private JPanel createInventoryByCategoryChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory by Category");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the category distribution
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> data = controller.getInventoryValueByCategory();

                // Find total value
                double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

                // Draw title
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Inventory Distribution by Category", 10, 20);

                // Draw bars
                int barHeight = 30;
                int maxWidth = getWidth() - 200;
                int y = 50;

                // Format for currency
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
                NumberFormat percentFormat = NumberFormat.getPercentInstance();
                percentFormat.setMaximumFractionDigits(1);

                int i = 0;
                for (Map.Entry<String, Double> entry : data.entrySet()) {
                    String category = entry.getKey();
                    double value = entry.getValue();

                    // Calculate width proportional to value
                    int width = (int)((value / total) * maxWidth);

                    // Draw bar
                    g.setColor(getColorForIndex(i));
                    g.fillRect(150, y, width, barHeight);

                    // Draw category label
                    g.setColor(DraculaTheme.FOREGROUND);
                    g.drawString(category, 10, y + barHeight/2 + 5);

                    // Draw value label
                    String valueStr = currencyFormat.format(value) + " (" + percentFormat.format(value/total) + ")";
                    g.drawString(valueStr, 150 + width + 10, y + barHeight/2 + 5);

                    y += barHeight + 10;
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

    private JPanel createInventoryLevelsChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory Levels");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the inventory levels
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Integer> data = controller.getInventoryLevels();

                // Find max value
                int maxValue = data.values().stream().mapToInt(Integer::intValue).max().orElse(0);

                // Draw title
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Stock Quantity by Item", 10, 20);

                // Draw bars - we'll show top 10 items to keep it readable
                int barHeight = 25;
                int maxWidth = getWidth() - 200;
                int y = 50;

                int count = 0;
                for (Map.Entry<String, Integer> entry : data.entrySet()) {
                    if (count++ >= 10) break;

                    String item = entry.getKey();
                    int quantity = entry.getValue();

                    // Calculate width proportional to value
                    int width = (int)((double)quantity / maxValue * maxWidth);

                    // Choose color based on stock level
                    Color barColor;
                    if (quantity < 10) {
                        barColor = DraculaTheme.RED;  // Low stock
                    } else if (quantity < 20) {
                        barColor = DraculaTheme.ORANGE;  // Medium stock
                    } else {
                        barColor = DraculaTheme.GREEN;  // High stock
                    }

// Draw bar
                    g.setColor(barColor);
                    g.fillRect(150, y, width, barHeight);

                    // Draw item label
                    g.setColor(DraculaTheme.FOREGROUND);
                    g.drawString(item, 10, y + barHeight/2 + 5);

                    // Draw quantity label
                    g.drawString(String.valueOf(quantity) + " units", 150 + width + 10, y + barHeight/2 + 5);

                    y += barHeight + 10;
                }

                // Draw legend
                int legendY = getHeight() - 80;
                
                g.setColor(DraculaTheme.RED);
                g.fillRect(10, legendY, 15, 15);
                g.setColor(DraculaTheme.FOREGROUND);
                g.drawString("Low Stock (< 10)", 30, legendY + 12);
                
                legendY += 20;
                g.setColor(DraculaTheme.ORANGE);
                g.fillRect(10, legendY, 15, 15);
                g.setColor(DraculaTheme.FOREGROUND);
                g.drawString("Medium Stock (10-20)", 30, legendY + 12);
                
                legendY += 20;
                g.setColor(DraculaTheme.GREEN);
                g.fillRect(10, legendY, 15, 15);
                g.setColor(DraculaTheme.FOREGROUND);
                g.drawString("High Stock (> 20)", 30, legendY + 12);
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
    
    public void refreshData() {
        // Force a repaint to refresh the chart data
        chartPanel1.repaint();
        chartPanel2.repaint();
        chartPanel3.repaint();
        chartPanel4.repaint();
    }
}