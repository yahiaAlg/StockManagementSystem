// StockController.java (Updated)
package com.stockmanager.controllers;

import com.stockmanager.models.StockItem;
import com.stockmanager.models.Supplier;
import com.stockmanager.utils.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controller for managing stock items (CRUD operations)
 */
public class StockController {
    
    public StockController() {
        // No initialization needed as we're using database now
    }
    
    // Methods for stock items
    public List<StockItem> getAllStockItems() {
        List<StockItem> items = new ArrayList<>();
        String query = "SELECT i.*, s.name as supplier_name, s.contactInfo, s.address, " +
                       "s.email, s.phone FROM stock_items i " +
                       "LEFT JOIN suppliers s ON i.supplier_id = s.id";
        
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("supplier_id"),
                    rs.getString("supplier_name"),
                    rs.getString("contactInfo"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                
                StockItem item = new StockItem(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    supplier
                );
                
                items.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching stock items: " + e.getMessage());
            e.printStackTrace();
        }
        
        return items;
    }
    
    public StockItem getStockItemById(String id) {
        String query = "SELECT i.*, s.name as supplier_name, s.contactInfo, s.address, " +
                       "s.email, s.phone FROM stock_items i " +
                       "LEFT JOIN suppliers s ON i.supplier_id = s.id " +
                       "WHERE i.id = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Supplier supplier = new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("contactInfo"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone")
                    );
                    
                    return new StockItem(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        supplier
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching stock item: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void saveStockItem(StockItem item) {
        try (Connection conn = DatabaseHelper.getConnection()) {
            if (getStockItemById(item.getId()) == null) {
                // Insert new item
                String query = "INSERT INTO stock_items (id, name, description, price, quantity, supplier_id) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, item.getId());
                    stmt.setString(2, item.getName());
                    stmt.setString(3, item.getDescription());
                    stmt.setDouble(4, item.getPrice());
                    stmt.setInt(5, item.getQuantity());
                    stmt.setString(6, item.getSupplier().getId());
                    
                    stmt.executeUpdate();
                }
            } else {
                // Update existing item
                String query = "UPDATE stock_items SET name = ?, description = ?, price = ?, " +
                               "quantity = ?, supplier_id = ? WHERE id = ?";
                
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, item.getName());
                    stmt.setString(2, item.getDescription());
                    stmt.setDouble(3, item.getPrice());
                    stmt.setInt(4, item.getQuantity());
                    stmt.setString(5, item.getSupplier().getId());
                    stmt.setString(6, item.getId());
                    
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving stock item: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deleteStockItem(String id) {
        String query = "DELETE FROM stock_items WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error deleting stock item: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Methods for suppliers
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers";
        
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("contactInfo"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching suppliers: " + e.getMessage());
            e.printStackTrace();
        }
        
        return suppliers;
    }
    
    public Supplier getSupplierById(String id) {
        String query = "SELECT * FROM suppliers WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Supplier(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("contactInfo"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching supplier: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void saveSupplier(Supplier supplier) {
        try (Connection conn = DatabaseHelper.getConnection()) {
            if (getSupplierById(supplier.getId()) == null) {
                // Insert new supplier
                String query = "INSERT INTO suppliers (id, name, contactInfo, address, email, phone) " +
                               "VALUES (?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, supplier.getId());
                    stmt.setString(2, supplier.getName());
                    stmt.setString(3, supplier.getContactInfo());
                    stmt.setString(4, supplier.getAddress());
                    stmt.setString(5, supplier.getEmail());
                    stmt.setString(6, supplier.getPhone());
                    
                    stmt.executeUpdate();
                }
            } else {
                // Update existing supplier
                String query = "UPDATE suppliers SET name = ?, contactInfo = ?, address = ?, " +
                               "email = ?, phone = ? WHERE id = ?";
                
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, supplier.getName());
                    stmt.setString(2, supplier.getContactInfo());
                    stmt.setString(3, supplier.getAddress());
                    stmt.setString(4, supplier.getEmail());
                    stmt.setString(5, supplier.getPhone());
                    stmt.setString(6, supplier.getId());
                    
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving supplier: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deleteSupplier(String id) {
        // Check if the supplier is used by any stock items
        String checkQuery = "SELECT COUNT(*) FROM stock_items WHERE supplier_id = ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            
            checkStmt.setString(1, id);
            
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new SQLException("Cannot delete supplier: it is used by one or more stock items");
                }
            }
            
            // If not used, delete the supplier
            String deleteQuery = "DELETE FROM suppliers WHERE id = ?";
            
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                deleteStmt.setString(1, id);
                deleteStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error deleting supplier: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Search methods
    public List<StockItem> searchStockItems(String query) {
        List<StockItem> items = new ArrayList<>();
        String searchQuery = "SELECT i.*, s.name as supplier_name, s.contactInfo, s.address, " +
                             "s.email, s.phone FROM stock_items i " +
                             "LEFT JOIN suppliers s ON i.supplier_id = s.id " +
                             "WHERE i.name LIKE ? OR i.description LIKE ?";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(searchQuery)) {
            
            String searchPattern = "%" + query + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Supplier supplier = new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("contactInfo"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone")
                    );
                    
                    StockItem item = new StockItem(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        supplier
                    );
                    
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching stock items: " + e.getMessage());
            e.printStackTrace();
        }
        
        return items;
    }
}