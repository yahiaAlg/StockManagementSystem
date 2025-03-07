// Main.java (Updated)
package com.stockmanager;

import com.stockmanager.ui.MainFrame;

/**
 * Main entry point for the Stock Management System application
 */
public class Main {
    public static void main(String[] args) {
        // Load SQLite JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found");
            e.printStackTrace();
            return;
        }
        
        // Start the application on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}