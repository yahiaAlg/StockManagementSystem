// AuthDialog.java
package com.stockmanager.ui.auth;

import javax.swing.*;
import java.awt.*;

import com.stockmanager.controllers.AuthController;
import com.stockmanager.models.User;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Dialog for user authentication (login and registration)
 */
public class AuthDialog extends JDialog {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    
    private AuthController authController;
    private AuthListener authListener;
    
    /**
     * Interface for auth event handling
     */
    public interface AuthListener {
        void onAuthSuccess(User user);
    }
    
    public AuthDialog(JFrame parent, AuthController authController, AuthListener listener) {
        super(parent, "Authentication", true);
        this.authController = authController;
        this.authListener = listener;
        
        // Initialize the UI
        initializeUI();
    }
    
    private void initializeUI() {
        // Set up the dialog
        getContentPane().setBackground(DraculaTheme.BACKGROUND);
        setSize(600, 600);
        setLocationRelativeTo(getOwner());
        setResizable(false);
        
        // Create card layout for switching between login and register
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(DraculaTheme.BACKGROUND);
        
        // Create login panel
        loginPanel = new LoginPanel(authController, new LoginPanel.LoginListener() {
            @Override
            public void onLoginSuccess(User user) {
                if (authListener != null) {
                    authListener.onAuthSuccess(user);
                }
                dispose();
            }
            
            @Override
            public void onSwitchToRegister() {
                cardLayout.show(cardPanel, "register");
            }
        });
        
        // Create register panel
        registerPanel = new RegisterPanel(authController, new RegisterPanel.RegisterListener() {
            @Override
            public void onRegisterSuccess(User user) {
                if (authListener != null) {
                    authListener.onAuthSuccess(user);
                }
                dispose();
            }
            
            @Override
            public void onCancel() {
                cardLayout.show(cardPanel, "login");
            }
        });
        
        // Add panels to card layout
        cardPanel.add(loginPanel, "login");
        cardPanel.add(registerPanel, "register");
        
        // Show login panel by default
        cardLayout.show(cardPanel, "login");
        
        getContentPane().add(cardPanel);
    }
}