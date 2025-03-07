// UserProfilePanel.java
package com.stockmanager.ui.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import com.stockmanager.controllers.AuthController;
import com.stockmanager.models.User;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for viewing and editing user profile
 */
public class UserProfilePanel extends JPanel {
    private AuthController authController;
    
    private JTextField usernameField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField roleField;
    private JTextField createdAtField;
    
    private JButton updateProfileButton;
    private JButton changePasswordButton;
    
    public UserProfilePanel(AuthController authController) {
        this.authController = authController;
        
        // Initialize the UI
        initializeUI();
        
        // Load user data
        loadUserData();
    }
    
    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create header
        JLabel headerLabel = UIUtils.createHeaderLabel("User Profile");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);
        
        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(DraculaTheme.BACKGROUND);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username field (read-only)
        JLabel usernameLabel = UIUtils.createStyledLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        
        usernameField = UIUtils.createStyledTextField();
        usernameField.setEditable(false);
        usernameField.setBackground(DraculaTheme.COMMENT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        formPanel.add(usernameField, gbc);
        
        // Full Name field
        JLabel fullNameLabel = UIUtils.createStyledLabel("Full Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        formPanel.add(fullNameLabel, gbc);
        
        fullNameField = UIUtils.createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        formPanel.add(fullNameField, gbc);
        
        // Email field
        JLabel emailLabel = UIUtils.createStyledLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        formPanel.add(emailLabel, gbc);
        
        emailField = UIUtils.createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        formPanel.add(emailField, gbc);
        
        // Role field (read-only)
        JLabel roleLabel = UIUtils.createStyledLabel("Role:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        formPanel.add(roleLabel, gbc);
        
        roleField = UIUtils.createStyledTextField();
        roleField.setEditable(false);
        roleField.setBackground(DraculaTheme.COMMENT);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        formPanel.add(roleField, gbc);
        
        // Created At field (read-only)
        JLabel createdAtLabel = UIUtils.createStyledLabel("Member Since:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        formPanel.add(createdAtLabel, gbc);
        
        createdAtField = UIUtils.createStyledTextField();
        createdAtField.setEditable(false);
        createdAtField.setBackground(DraculaTheme.COMMENT);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        formPanel.add(createdAtField, gbc);
        
        // Add form panel to center
        add(formPanel, BorderLayout.CENTER);
        
        // Create buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);
        
        updateProfileButton = UIUtils.createStyledButton("Update Profile");
        changePasswordButton = UIUtils.createStyledButton("Change Password");
        
        buttonPanel.add(updateProfileButton);
        buttonPanel.add(changePasswordButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Set action listeners
        updateProfileButton.addActionListener(this::handleUpdateProfile);
        changePasswordButton.addActionListener(this::handleChangePassword);
    }
    
    private void loadUserData() {
        User user = authController.getCurrentUser();
        if (user != null) {
            usernameField.setText(user.getUsername());
            fullNameField.setText(user.getFullName());
            emailField.setText(user.getEmail());
            roleField.setText(user.getRole());
            
            // Format created date if available
            if (user.getCreatedAt() != null) {
                createdAtField.setText(user.getCreatedAt().toString());
            } else {
                createdAtField.setText("N/A");
            }
        }
    }
    
    private void handleUpdateProfile(ActionEvent e) {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        
        boolean success = authController.updateProfile(fullName, email);
        
        if (success) {
            JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Failed to update profile.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void handleChangePassword(ActionEvent e) {
        // Show change password dialog
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBackground(DraculaTheme.BACKGROUND);
        
        JLabel currentPassLabel = UIUtils.createStyledLabel("Current Password:");
        JLabel newPassLabel = UIUtils.createStyledLabel("New Password:");
        JLabel confirmPassLabel = UIUtils.createStyledLabel("Confirm Password:");
        
        JPasswordField currentPassField = new JPasswordField();
        JPasswordField newPassField = new JPasswordField();
        JPasswordField confirmPassField = new JPasswordField();
        
        currentPassField.setBackground(DraculaTheme.CURRENT_LINE);
        currentPassField.setForeground(DraculaTheme.FOREGROUND);
        newPassField.setBackground(DraculaTheme.CURRENT_LINE);
        newPassField.setForeground(DraculaTheme.FOREGROUND);
        confirmPassField.setBackground(DraculaTheme.CURRENT_LINE);
        confirmPassField.setForeground(DraculaTheme.FOREGROUND);
        
        panel.add(currentPassLabel);
        panel.add(currentPassField);
        panel.add(newPassLabel);
        panel.add(newPassField);
        panel.add(confirmPassLabel);
        panel.add(confirmPassField);
        
        int result = JOptionPane.showConfirmDialog(
            this, panel, "Change Password", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String currentPass = new String(currentPassField.getPassword());
            String newPass = new String(newPassField.getPassword());
            String confirmPass = new String(confirmPassField.getPassword());
            
            if (currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "All fields are required.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(
                    this,
                    "New passwords do not match.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            boolean success = authController.changePassword(currentPass, newPass);
            
            if (success) {
                JOptionPane.showMessageDialog(
                    this,
                    "Password changed successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Failed to change password. Make sure your current password is correct.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}