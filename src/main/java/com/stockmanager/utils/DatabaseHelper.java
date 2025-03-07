// DatabaseHelper.java
package com.stockmanager.utils;

import java.sql.*;

/**
 * Helper class for SQLite database operations
 */
public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:stockmanager.db";
    private static Connection connection;
    
    /**
     * Get database connection
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }
    
    /**
     * Close the database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Initialize database with required tables
     */
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create User table
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id TEXT PRIMARY KEY, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL, " +
                "fullName TEXT, " +
                "email TEXT, " +
                "role TEXT DEFAULT 'user', " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                ")"
            );
            
            // Create Supplier table
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS suppliers (" +
                "id TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "contactInfo TEXT, " +
                "address TEXT, " +
                "email TEXT, " +
                "phone TEXT " +
                ")"
            );
            
            // Create StockItem table
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS stock_items (" +
                "id TEXT PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "description TEXT, " +
                "price REAL NOT NULL, " +
                "quantity INTEGER NOT NULL, " +
                "supplier_id TEXT, " +
                "FOREIGN KEY (supplier_id) REFERENCES suppliers(id) " +
                ")"
            );
            
            // Insert default admin user if not exists
            PreparedStatement checkAdmin = conn.prepareStatement(
                "SELECT COUNT(*) FROM users WHERE username = ?"
            );
            checkAdmin.setString(1, "admin");
            ResultSet rs = checkAdmin.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                PreparedStatement insertAdmin = conn.prepareStatement(
                    "INSERT INTO users (id, username, password, fullName, role) VALUES (?, ?, ?, ?, ?)"
                );
                insertAdmin.setString(1, "U001");
                insertAdmin.setString(2, "admin");
                insertAdmin.setString(3, "admin123"); // In a real app, this should be hashed
                insertAdmin.setString(4, "System Administrator");
                insertAdmin.setString(5, "admin");
                insertAdmin.executeUpdate();
            }
            
            // Insert sample data
            insertSampleData(conn);
            
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Insert sample data for demonstration purposes
     */
    private static void insertSampleData(Connection conn) throws SQLException {
        // Check if we already have suppliers
        PreparedStatement checkSuppliers = conn.prepareStatement(
            "SELECT COUNT(*) FROM suppliers"
        );
        ResultSet rs = checkSuppliers.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return; // We already have data, no need to insert samples
        }
        
        // Insert sample suppliers
        String[] supplierIds = {"S001", "S002", "S003"};
        String[] supplierNames = {"Tech Supplies Inc.", "Office Essentials", "Furniture Warehouse"};
        String[] contactInfos = {"Primary Electronics Supplier", "Office Supplies Provider", "Furniture and Fixtures"};
        String[] addresses = {
            "123 Tech Ave, Silicon Valley", 
            "456 Office Blvd, Business District", 
            "789 Warehouse Rd, Industrial Zone"
        };
        String[] emails = {
            "info@techsupplies.com", 
            "contact@officeessentials.com", 
            "sales@furniturewarehouse.com"
        };
        String[] phones = {"555-1234", "555-5678", "555-9012"};
        
        PreparedStatement insertSupplier = conn.prepareStatement(
            "INSERT INTO suppliers (id, name, contactInfo, address, email, phone) VALUES (?, ?, ?, ?, ?, ?)"
        );
        
        for (int i = 0; i < supplierIds.length; i++) {
            insertSupplier.setString(1, supplierIds[i]);
            insertSupplier.setString(2, supplierNames[i]);
            insertSupplier.setString(3, contactInfos[i]);
            insertSupplier.setString(4, addresses[i]);
            insertSupplier.setString(5, emails[i]);
            insertSupplier.setString(6, phones[i]);
            insertSupplier.executeUpdate();
        }
        
        // Insert sample stock items
        String[] itemIds = {"I001", "I002", "I003", "I004", "I005"};
        String[] itemNames = {"Laptop", "Desk Chair", "Printer", "Paper Reams", "Desk"};
        String[] descriptions = {
            "High-performance business laptop",
            "Ergonomic office chair",
            "Color laser printer",
            "A4 printing paper, 500 sheets",
            "Standard office desk"
        };
        double[] prices = {1200.00, 250.00, 350.00, 5.00, 300.00};
        int[] quantities = {15, 30, 10, 200, 20};
        String[] itemSupplierIds = {"S001", "S003", "S001", "S002", "S003"};
        
        PreparedStatement insertItem = conn.prepareStatement(
            "INSERT INTO stock_items (id, name, description, price, quantity, supplier_id) VALUES (?, ?, ?, ?, ?, ?)"
        );
        
        for (int i = 0; i < itemIds.length; i++) {
            insertItem.setString(1, itemIds[i]);
            insertItem.setString(2, itemNames[i]);
            insertItem.setString(3, descriptions[i]);
            insertItem.setDouble(4, prices[i]);
            insertItem.setInt(5, quantities[i]);
            insertItem.setString(6, itemSupplierIds[i]);
            insertItem.executeUpdate();
        }
    }
}