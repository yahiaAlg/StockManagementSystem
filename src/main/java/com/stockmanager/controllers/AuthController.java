// AuthController.java
package com.stockmanager.controllers;

import com.stockmanager.models.User;
import com.stockmanager.utils.DatabaseHelper;

import java.sql.*;
import java.util.UUID;

/**
 * Controller for user authentication operations
 */
public class AuthController {
    private User currentUser;
    
    /**
     * Attempt to login with the provided credentials
     * @return User object if successful, null otherwise
     */
    public User login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password); // In real app, would compare hashed passwords
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    currentUser = new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                    );
                    return currentUser;
                }
            }
        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Register a new user
     * @return User object if successful, null otherwise
     */
    public User register(String username, String password, String fullName, String email) {
        String query = "INSERT INTO users (id, username, password, fullName, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Check if username already exists
            PreparedStatement checkStmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM users WHERE username = ?"
            );
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                return null; // Username already exists
            }
            
            String userId = "U" + UUID.randomUUID().toString().substring(0, 8);
            
            stmt.setString(1, userId);
            stmt.setString(2, username);
            stmt.setString(3, password); // In real app, should hash password
            stmt.setString(4, fullName);
            stmt.setString(5, email);
            stmt.setString(6, "user"); // Default role
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                currentUser = new User(userId, username, password, fullName, email, "user", new Timestamp(System.currentTimeMillis()));
                return currentUser;
            }
        } catch (SQLException e) {
            System.err.println("Registration error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Logout the current user
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Get the currently logged in user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if a user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Check if current user has admin role
     */
    public boolean isAdmin() {
        return currentUser != null && "admin".equals(currentUser.getRole());
    }
    
    /**
     * Update user profile
     */
    public boolean updateProfile(String fullName, String email) {
        if (currentUser == null) {
            return false;
        }
        
        String query = "UPDATE users SET fullName = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, currentUser.getId());
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                currentUser.setFullName(fullName);
                currentUser.setEmail(email);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Profile update error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Change user password
     */
    public boolean changePassword(String currentPassword, String newPassword) {
        if (currentUser == null || !currentPassword.equals(currentUser.getPassword())) {
            return false;
        }
        
        String query = "UPDATE users SET password = ? WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, newPassword); // In real app, should hash password
            stmt.setString(2, currentUser.getId());
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                currentUser.setPassword(newPassword);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Password change error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}