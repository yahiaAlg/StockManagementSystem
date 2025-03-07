// StockItem.java - Complete implementation
package com.stockmanager.models;

import java.util.UUID;

/**
 * Represents a stock item in the inventory
 */
public class StockItem {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Supplier supplier;
    
    // Default constructor
    public StockItem() {
        this.id = UUID.randomUUID().toString();
    }
    
    // Constructor with parameters
    public StockItem(String name, String description, double price, int quantity, Supplier supplier) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }
    
    // Constructor with all parameters including ID
    public StockItem(String id, String name, String description, double price, int quantity, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public Supplier getSupplier() {
        return supplier;
    }
    
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public double getTotalValue() {
        return price * quantity;
    }
    
    @Override
    public String toString() {
        return name;
    }
}