// StockController.java - Complete implementation
package com.stockmanager.controllers;

import com.stockmanager.models.StockItem;
import com.stockmanager.models.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for managing stock items (CRUD operations)
 */
public class StockController {
    // We'll use in-memory maps for this demonstration
    private Map<String, StockItem> stockItems;
    private Map<String, Supplier> suppliers;
    
    public StockController() {
        stockItems = new HashMap<>();
        suppliers = new HashMap<>();
        initializeSampleData();
    }
    
    // Methods for stock items
    public List<StockItem> getAllStockItems() {
        return new ArrayList<>(stockItems.values());
    }
    
    public StockItem getStockItemById(String id) {
        return stockItems.get(id);
    }
    
    public void saveStockItem(StockItem item) {
        stockItems.put(item.getId(), item);
    }
    
    public void deleteStockItem(String id) {
        stockItems.remove(id);
    }
    
    // Methods for suppliers
    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers.values());
    }
    
    public Supplier getSupplierById(String id) {
        return suppliers.get(id);
    }
    
    public void saveSupplier(Supplier supplier) {
        suppliers.put(supplier.getId(), supplier);
    }
    
    public void deleteSupplier(String id) {
        suppliers.remove(id);
    }
    
    // Search methods
    public List<StockItem> searchStockItems(String query) {
        String lowerQuery = query.toLowerCase();
        return stockItems.values().stream()
            .filter(item -> item.getName().toLowerCase().contains(lowerQuery) || 
                            item.getDescription().toLowerCase().contains(lowerQuery))
            .collect(Collectors.toList());
    }
    
    // Initialize with sample data for demonstration
    private void initializeSampleData() {
        // Create sample suppliers
        Supplier supplier1 = new Supplier("S001", "Tech Supplies Inc.", "Primary Electronics Supplier", 
                "123 Tech Ave, Silicon Valley", "info@techsupplies.com", "555-1234");
        Supplier supplier2 = new Supplier("S002", "Office Essentials", "Office Supplies Provider", 
                "456 Office Blvd, Business District", "contact@officeessentials.com", "555-5678");
        Supplier supplier3 = new Supplier("S003", "Furniture Warehouse", "Furniture and Fixtures", 
                "789 Warehouse Rd, Industrial Zone", "sales@furniturewarehouse.com", "555-9012");
        
        suppliers.put(supplier1.getId(), supplier1);
        suppliers.put(supplier2.getId(), supplier2);
        suppliers.put(supplier3.getId(), supplier3);
        
        // Create sample stock items
        StockItem item1 = new StockItem("I001", "Laptop", "High-performance business laptop", 1200.00, 15, supplier1);
        StockItem item2 = new StockItem("I002", "Desk Chair", "Ergonomic office chair", 250.00, 30, supplier3);
        StockItem item3 = new StockItem("I003", "Printer", "Color laser printer", 350.00, 10, supplier1);
        StockItem item4 = new StockItem("I004", "Paper Reams", "A4 printing paper, 500 sheets", 5.00, 200, supplier2);
        StockItem item5 = new StockItem("I005", "Desk", "Standard office desk", 300.00, 20, supplier3);
        StockItem item6 = new StockItem("I006", "Pen Set", "Set of 12 blue ballpoint pens", 3.50, 100, supplier2);
        StockItem item7 = new StockItem("I007", "Monitor", "27-inch 4K display", 400.00, 25, supplier1);
        StockItem item8 = new StockItem("I008", "Keyboard", "Wireless mechanical keyboard", 80.00, 40, supplier1);
        StockItem item9 = new StockItem("I009", "Filing Cabinet", "3-drawer steel filing cabinet", 150.00, 15, supplier3);
        StockItem item10 = new StockItem("I010", "Stapler", "Heavy-duty stapler", 15.00, 50, supplier2);
        
        stockItems.put(item1.getId(), item1);
        stockItems.put(item2.getId(), item2);
        stockItems.put(item3.getId(), item3);
        stockItems.put(item4.getId(), item4);
        stockItems.put(item5.getId(), item5);
        stockItems.put(item6.getId(), item6);
        stockItems.put(item7.getId(), item7);
        stockItems.put(item8.getId(), item8);
        stockItems.put(item9.getId(), item9);
        stockItems.put(item10.getId(), item10);
    }
}