// RegisterPanel.java
package com.stockmanager.ui.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import com.stockmanager.controllers.AuthController;
import com.stockmanager.models.User;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for user registration
 */
public class RegisterPanel extends JPanel {
    private AuthController authController;
    private RegisterListener registerListener;
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton cancelButton;
    
    /**
     * Interface for register event handling
     */
    public interface RegisterListener {
        void onRegisterSuccess(User user);
        void onCancel();
    }
    
    public RegisterPanel(AuthController authController, RegisterListener listener) {
        this.authController = authController;
        this.registerListener = listener;
        
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
        
        // Create registration form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(DraculaTheme.BACKGROUND);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Form title
        JLabel formTitle = UIUtils.createHeaderLabel("Create Account");
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(formTitle, gbc);
        
        // Username field
        JLabel usernameLabel = UIUtils.createStyledLabel("Username*:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(usernameLabel, gbc);
        
        usernameField = UIUtils.createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(usernameField, gbc);
        
        // Password field
        JLabel passwordLabel = UIUtils.createStyledLabel("Password*:");
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
        
        // Confirm Password field
        JLabel confirmPasswordLabel = UIUtils.createStyledLabel("Confirm Password*:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(confirmPasswordLabel, gbc);
        
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBackground(DraculaTheme.CURRENT_LINE);
        confirmPasswordField.setForeground(DraculaTheme.FOREGROUND);
        confirmPasswordField.setCaretColor(DraculaTheme.FOREGROUND);
        confirmPasswordField.setBorder(UIUtils.createTextFieldBorder());
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(confirmPasswordField, gbc);
        
        // Full Name field
        JLabel fullNameLabel = UIUtils.createStyledLabel("Full Name:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(fullNameLabel, gbc);
        
        fullNameField = UIUtils.createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(fullNameField, gbc);
        
        // Email field
        JLabel emailLabel = UIUtils.createStyledLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(emailLabel, gbc);
        
        emailField = UIUtils.createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(emailField, gbc);
        
        // Required fields note
        JLabel requiredNote = UIUtils.createStyledLabel("* Required fields");
        requiredNote.setForeground(DraculaTheme.COMMENT);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(requiredNote, gbc);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);
        
        registerButton = UIUtils.createStyledButton("Register");
        cancelButton = UIUtils.createStyledButton("Cancel");
        
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(30, 10, 10, 10);
        formPanel.add(buttonPanel, gbc);
        
        // Add components to main panel
        add(headerLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        
        // Set action listeners
        registerButton.addActionListener(this::handleRegister);
        cancelButton.addActionListener(e -> {
            if (registerListener != null) {
                registerListener.onCancel();
            }
        });
    }
    
    private void handleRegister(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        
        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Username and password are required.",
                "Registration Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(
                this,
                "Passwords do not match.",
                "Registration Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Attempt registration
        User user = authController.register(username, password, fullName, email);
        
        if (user != null) {
            JOptionPane.showMessageDialog(
                this,
                "Registration successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            if (registerListener != null) {
                registerListener.onRegisterSuccess(user);
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Registration failed. Username may already be taken.",
                "Registration Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}