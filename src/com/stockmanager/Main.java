package com.stockmanager;

import com.stockmanager.ui.MainFrame;

/**
 * Main entry point for the Stock Management System application
 */
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
