// MainFrame.java (Updated)
package com.stockmanager.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.controllers.AuthController;
import com.stockmanager.controllers.StockController;
import com.stockmanager.models.User;
import com.stockmanager.ui.analytics.AnalyticsPanel;
import com.stockmanager.ui.auth.AuthDialog;
import com.stockmanager.ui.dashboard.DashboardPanel;
import com.stockmanager.ui.stock.StockListPanel;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.ui.user.UserProfilePanel;
import com.stockmanager.utils.DatabaseHelper;
import com.stockmanager.utils.UIUtils;

/**
 * Main application window with navigation and content panels
 */
public class MainFrame extends JFrame {
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Controllers
    private AuthController authController;
    private StockController stockController;
    private AnalyticsController analyticsController;
    
    // Panels
    private DashboardPanel dashboardPanel;
    private StockListPanel stockListPanel;
    private AnalyticsPanel analyticsPanel;
    private UserProfilePanel userProfilePanel;
    
    // User info components
    private JLabel userNameLabel;
    private JButton logoutButton;
    
    public MainFrame() {
        // Initialize database
        DatabaseHelper.initializeDatabase();
        
        // Initialize controllers
        authController = new AuthController();
        stockController = new StockController();
        analyticsController = new AnalyticsController(stockController);
        
        // Initialize the UI
        initializeUI();
        
        // Show login dialog on startup
        showLoginDialog();
        
        // Add window closing listener to close database connection
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseHelper.closeConnection();
            }
        });
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
        userProfilePanel = new UserProfilePanel(authController);
        
        // Add panels to card layout
        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(stockListPanel, "stock");
        contentPanel.add(analyticsPanel, "analytics");
        contentPanel.add(userProfilePanel, "profile");
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Show dashboard initially
        cardLayout.show(contentPanel, "dashboard");
    }
    
    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, getHeight()));
        panel.setBackground(DraculaTheme.CURRENT_LINE);
        panel.setLayout(new BorderLayout());
        
        // Top section with app title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(DraculaTheme.CURRENT_LINE);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Stock Manager");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLabel.setForeground(DraculaTheme.PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        
        topPanel.add(titleLabel);
        
        // Navigation buttons
        JPanel navButtonsPanel = new JPanel();
        navButtonsPanel.setBackground(DraculaTheme.CURRENT_LINE);
        navButtonsPanel.setLayout(new BoxLayout(navButtonsPanel, BoxLayout.Y_AXIS));
        
        JButton dashboardButton = createNavButton("Dashboard", "dashboard");
        JButton stockButton = createNavButton("Stock Management", "stock");
        JButton analyticsButton = createNavButton("Analytics", "analytics");
        JButton profileButton = createNavButton("Profile", "profile");
        
        navButtonsPanel.add(dashboardButton);
        navButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navButtonsPanel.add(stockButton);
        navButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navButtonsPanel.add(analyticsButton);
        navButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navButtonsPanel.add(profileButton);
        navButtonsPanel.add(Box.createVerticalGlue());
        
        // Bottom section with user info and logout
        JPanel userPanel = new JPanel();
        userPanel.setBackground(DraculaTheme.CURRENT_LINE);
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        
        userNameLabel = new JLabel("Not logged in");
        userNameLabel.setForeground(DraculaTheme.FOREGROUND);
        userNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        logoutButton = UIUtils.createStyledButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(180, 40));
        logoutButton.addActionListener(e -> handleLogout());
        
        userPanel.add(userNameLabel);
        userPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        userPanel.add(logoutButton);
        
        // Assemble navigation panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(navButtonsPanel, BorderLayout.CENTER);
        panel.add(userPanel, BorderLayout.SOUTH);
        
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
    
    private void showLoginDialog() {
        AuthDialog authDialog = new AuthDialog(this, authController, new AuthDialog.AuthListener() {
            @Override
            public void onAuthSuccess(User user) {
                updateUserInfo(user);
            }
        });
        
        authDialog.setVisible(true);
        
        // If after dialog closes, user is still not logged in, exit the application
        if (!authController.isLoggedIn()) {
            System.exit(0);
        }
    }
    
    private void handleLogout() {
        int option = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION
        );
        
        if (option == JOptionPane.YES_OPTION) {
            authController.logout();
            showLoginDialog();
        }
    }
    
    private void updateUserInfo(User user) {
        if (user != null) {
            userNameLabel.setText(user.getUsername());
            
            // Refresh user profile panel
            userProfilePanel = new UserProfilePanel(authController);
            contentPanel.remove(contentPanel.getComponent(3));  // Remove existing profile panel
            contentPanel.add(userProfilePanel, "profile");
        }
    }
}