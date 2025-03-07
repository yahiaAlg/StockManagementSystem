// MainFrame.java - Complete implementation
package com.stockmanager.ui;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.controllers.StockController;
import com.stockmanager.ui.dashboard.DashboardPanel;
import com.stockmanager.ui.stock.StockListPanel;
import com.stockmanager.ui.analytics.AnalyticsPanel;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Main application window with navigation and content panels
 */
public class MainFrame extends JFrame {
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Controllers
    private StockController stockController;
    private AnalyticsController analyticsController;
    
    // Panels
    private DashboardPanel dashboardPanel;
    private StockListPanel stockListPanel;
    private AnalyticsPanel analyticsPanel;
    
    public MainFrame() {
        // Initialize controllers
        stockController = new StockController();
        analyticsController = new AnalyticsController(stockController);
        
        // Initialize the UI
        initializeUI();
    }
    
    private void initializeUI() {
        // Apply Dracula theme
        DraculaTheme.apply();
        
        // Set up the main frame
        setTitle("Stock Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set up the main layout
        setLayout(new BorderLayout());
        
        // Create the navigation panel
        navigationPanel = createNavigationPanel();
        add(navigationPanel, BorderLayout.WEST);
        
        // Create the content panel with card layout
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setBackground(DraculaTheme.BACKGROUND);
        
        // Create the panels
        dashboardPanel = new DashboardPanel(stockController, analyticsController);
        stockListPanel = new StockListPanel(stockController);
        analyticsPanel = new AnalyticsPanel(analyticsController);
        
        // Add panels to card layout
        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(stockListPanel, "stock");
        contentPanel.add(analyticsPanel, "analytics");
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Show dashboard initially
        cardLayout.show(contentPanel, "dashboard");
    }
    
    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, getHeight()));
        panel.setBackground(DraculaTheme.CURRENT_LINE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // App title
        JLabel titleLabel = new JLabel("Stock Manager");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLabel.setForeground(DraculaTheme.PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        
        // Create navigation buttons
        JButton dashboardButton = createNavButton("Dashboard", "dashboard");
        JButton stockButton = createNavButton("Stock Management", "stock");
        JButton analyticsButton = createNavButton("Analytics", "analytics");
        
        // Add components to panel
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(dashboardButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(stockButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(analyticsButton);
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private JButton createNavButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(DraculaTheme.CURRENT_LINE);
        button.setForeground(DraculaTheme.FOREGROUND);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Dialog", Font.BOLD, 14));
        
        button.addActionListener(e -> cardLayout.show(contentPanel, cardName));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.PURPLE);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.CURRENT_LINE);
            }
        });
        
        return button;
    }
}