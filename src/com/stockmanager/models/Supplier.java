// Supplier.java - Complete implementation
package com.stockmanager.models;

import java.util.UUID;

/**
 * Represents a supplier of stock items
 */
public class Supplier {
    private String id;
    private String name;
    private String contactInfo;
    private String address;
    private String email;
    private String phone;
    
    // Default constructor
    public Supplier() {
        this.id = UUID.randomUUID().toString();
    }
    
    // Constructor with parameters
    public Supplier(String name, String contactInfo) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    // Constructor with all parameters
    public Supplier(String id, String name, String contactInfo, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContactInfo() {
        return contactInfo;
    }
    
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return name;
    }
}