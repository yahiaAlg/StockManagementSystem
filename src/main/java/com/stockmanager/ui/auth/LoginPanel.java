// LoginPanel.java
package com.stockmanager.ui.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import com.stockmanager.controllers.AuthController;
import com.stockmanager.models.User;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for user login
 */
public class LoginPanel extends JPanel {
    private AuthController authController;
    private LoginListener loginListener;
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    
    /**
     * Interface for login event handling
     */
    public interface LoginListener {
        void onLoginSuccess(User user);
        void onSwitchToRegister();
    }
    
    public LoginPanel(AuthController authController, LoginListener listener) {
        this.authController = authController;
        this.loginListener = listener;
        
        // Initialize the UI
        initializeUI();
    }
    
    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Create header
        JLabel headerLabel = new JLabel("Stock Management System");
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        headerLabel.setForeground(DraculaTheme.PURPLE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Create login form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(DraculaTheme.BACKGROUND);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Form title
        JLabel formTitle = UIUtils.createHeaderLabel("Login");
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(formTitle, gbc);
        
        // Username field
        JLabel usernameLabel = UIUtils.createStyledLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(usernameLabel, gbc);
        
        usernameField = UIUtils.createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(usernameField, gbc);
        
        // Password field
        JLabel passwordLabel = UIUtils.createStyledLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField();
        passwordField.setBackground(DraculaTheme.CURRENT_LINE);
        passwordField.setForeground(DraculaTheme.FOREGROUND);
        passwordField.setCaretColor(DraculaTheme.FOREGROUND);
        passwordField.setBorder(UIUtils.createTextFieldBorder());
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(passwordField, gbc);
        
        // Login button
        loginButton = UIUtils.createStyledButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 10, 10);
        formPanel.add(loginButton, gbc);
        
        // Register link
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerPanel.setBackground(DraculaTheme.BACKGROUND);
        
        JLabel registerLabel = UIUtils.createStyledLabel("Don't have an account?");
        registerButton = new JButton("Register");
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(DraculaTheme.CYAN);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        registerPanel.add(registerLabel);
        registerPanel.add(registerButton);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(registerPanel, gbc);
        
        // Add components to main panel
        add(headerLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        
        // Set action listeners
        loginButton.addActionListener(this::handleLogin);
        passwordField.addActionListener(this::handleLogin);
        registerButton.addActionListener(e -> {
            if (loginListener != null) {
                loginListener.onSwitchToRegister();
            }
        });
    }
    
    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter both username and password.",
                "Login Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        User user = authController.login(username, password);
        
        if (user != null) {
            if (loginListener != null) {
                loginListener.onLoginSuccess(user);
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Invalid username or password.",
                "Login Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}