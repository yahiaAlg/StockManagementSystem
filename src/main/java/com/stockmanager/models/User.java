// User.java
package com.stockmanager.models;

import java.util.UUID;
import java.sql.Timestamp;

/**
 * Represents a user of the system
 */
public class User {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role;
    private Timestamp createdAt;
    
    // Default constructor
    public User() {
        this.id = UUID.randomUUID().toString();
    }
    
    // Constructor with basic parameters
    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.role = "user";
    }
    
    // Constructor with all parameters
    public User(String id, String username, String password, String fullName, 
                String email, String role, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return username;
    }
}