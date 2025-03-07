// AnalyticsController.java - Complete implementation
package com.stockmanager.controllers;

import com.stockmanager.models.StockItem;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for analytics operations
 */
public class AnalyticsController {
    private StockController stockController;
    
    public AnalyticsController(StockController stockController) {
        this.stockController = stockController;
    }
    
    // Get sales data by supplier (for demonstration, based on stock value)
    public Map<String, Double> getSalesBySupplier() {
        return stockController.getAllStockItems().stream()
            .collect(Collectors.groupingBy(
                item -> item.getSupplier().getName(),
                Collectors.summingDouble(StockItem::getTotalValue)
            ));
    }
    
    // Get inventory value by category (for demo, we'll simulate categories)
    public Map<String, Double> getInventoryValueByCategory() {
        // This is a simplification - in a real app, items would have categories
        Map<String, Double> result = new HashMap<>();
        result.put("Electronics", 50000.0);
        result.put("Furniture", 30000.0);
        result.put("Office Supplies", 15000.0);
        result.put("Miscellaneous", 5000.0);
        return result;
    }
    
    // Get low stock items (items with quantity below threshold)
    public List<StockItem> getLowStockItems(int threshold) {
        return stockController.getAllStockItems().stream()
            .filter(item -> item.getQuantity() < threshold)
            .collect(Collectors.toList());
    }
    
    // Get total inventory value
    public double getTotalInventoryValue() {
        return stockController.getAllStockItems().stream()
            .mapToDouble(StockItem::getTotalValue)
            .sum();
    }
    
    // Get inventory levels for all items
    public Map<String, Integer> getInventoryLevels() {
        return stockController.getAllStockItems().stream()
            .collect(Collectors.toMap(
                StockItem::getName,
                StockItem::getQuantity
            ));
    }
    
    // Get monthly sales data (simulated)
    public Map<String, Double> getMonthlySalesData() {
        Map<String, Double> monthlySales = new LinkedHashMap<>();
        monthlySales.put("Jan", 5200.0);
        monthlySales.put("Feb", 6100.0);
        monthlySales.put("Mar", 5800.0);
        monthlySales.put("Apr", 6700.0);
        monthlySales.put("May", 7500.0);
        monthlySales.put("Jun", 8100.0);
        monthlySales.put("Jul", 7900.0);
        monthlySales.put("Aug", 8200.0);
        monthlySales.put("Sep", 8800.0);
        monthlySales.put("Oct", 9200.0);
        monthlySales.put("Nov", 9800.0);
        monthlySales.put("Dec", 10500.0);
        return monthlySales;
    }
}