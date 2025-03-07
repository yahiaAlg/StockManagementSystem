````md
> For this task, I need to generate a PowerShell script that will create the project structure for a Swing-based stock management system with a Dracula purple theme. Let me plan out what this structure should look like.
>
> A typical Java project structure would include:
>
> 1. Main source directory (src)
> 2. Package structure for various components (UI, models, controllers, etc.)
> 3. Resource directories for assets (if needed)
> 4. Configuration files
>
> For a Swing application with the described requirements, I'll create a structure that follows best practices for Java application development.
>
> Here's what the project structure could look like:
>
> ```
> StockManagementSystem/
> ├── src/
> │   └── com/
> │       └── stockmanager/
> │           ├── Main.java                   # Application entry point
> │           ├── models/                     # Data models
> │           │   ├── StockItem.java          # Stock item model
> │           │   └── Supplier.java           # Supplier model
> │           ├── controllers/                # Business logic
> │           │   ├── StockController.java    # Stock CRUD operations
> │           │   └── AnalyticsController.java # Analytics operations
> │           ├── ui/                         # UI components
> │           │   ├── MainFrame.java          # Main application window
> │           │   ├── theme/                  # Theme-related classes
> │           │   │   └── DraculaTheme.java   # Theme implementation
> │           │   ├── dashboard/              # Dashboard components
> │           │   │   └── DashboardPanel.java # Dashboard view
> │           │   ├── stock/                  # Stock CRUD components
> │           │   │   ├── StockListPanel.java # View all stock items
> │           │   │   ├── StockFormPanel.java # Create/Update form
> │           │   │   └── DeleteDialog.java   # Delete confirmation
> │           │   └── analytics/              # Analytics components
> │           │       └── AnalyticsPanel.java # Analytics view
> │           └── utils/                      # Utility classes
> │               └── UIUtils.java            # UI helper methods
> ├── resources/                              # Resources (if needed)
> │   └── images/                             # Images for UI
> └── lib/                                    # External libraries (like JFreeChart)
> ```

Here's a PowerShell script that creates the complete project structure for your Swing-based stock management system:

```powershell
# Create the main project directory
New-Item -Path "StockManagementSystem" -ItemType Directory -Force

# Create the source directories
New-Item -Path "StockManagementSystem\src\com\stockmanager" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\models" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\controllers" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\ui" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\ui\theme" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\ui\dashboard" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\ui\stock" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\ui\analytics" -ItemType Directory -Force
New-Item -Path "StockManagementSystem\src\com\stockmanager\utils" -ItemType Directory -Force

# Create the resource directories
New-Item -Path "StockManagementSystem\resources\images" -ItemType Directory -Force

# Create the lib directory for external dependencies
New-Item -Path "StockManagementSystem\lib" -ItemType Directory -Force

# Create and populate Java files
$fileContents = @{
    "src\com\stockmanager\Main.java" = @"
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
"@

    "src\com\stockmanager\models\StockItem.java" = @"
package com.stockmanager.models;

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

    // Constructors, getters, setters, and other methods
}
"@

    "src\com\stockmanager\models\Supplier.java" = @"
package com.stockmanager.models;

/**
 * Represents a supplier of stock items
 */
public class Supplier {
    private String id;
    private String name;
    private String contactInfo;

    // Constructors, getters, setters, and other methods
}
"@

    "src\com\stockmanager\controllers\StockController.java" = @"
package com.stockmanager.controllers;

import com.stockmanager.models.StockItem;
import java.util.List;

/**
 * Controller for managing stock items (CRUD operations)
 */
public class StockController {
    // Methods for creating, reading, updating, and deleting stock items

    public List<StockItem> getAllStockItems() {
        // Implementation
        return null;
    }

    public StockItem getStockItemById(String id) {
        // Implementation
        return null;
    }

    public void saveStockItem(StockItem item) {
        // Implementation
    }

    public void deleteStockItem(String id) {
        // Implementation
    }
}
"@

    "src\com\stockmanager\controllers\AnalyticsController.java" = @"
package com.stockmanager.controllers;

import java.util.Map;
import java.util.Date;

/**
 * Controller for analytics operations
 */
public class AnalyticsController {
    // Methods for generating analytics data

    public Map<String, Double> getSalesData(Date startDate, Date endDate) {
        // Implementation
        return null;
    }

    public Map<String, Integer> getInventoryLevels() {
        // Implementation
        return null;
    }
}
"@

    "src\com\stockmanager\ui\MainFrame.java" = @"
package com.stockmanager.ui;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.ui.dashboard.DashboardPanel;
import com.stockmanager.ui.stock.StockListPanel;
import com.stockmanager.ui.analytics.AnalyticsPanel;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Main application window with navigation and content panels
 */
public class MainFrame extends JFrame {
    private JPanel navigationPanel;
    private JPanel contentPanel;

    // Other UI components

    public MainFrame() {
        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Apply Dracula theme
        DraculaTheme.apply();

        // Set up the main frame
        setTitle("Stock Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and configure UI components
        // ...
    }
}
"@

    "src\com\stockmanager\ui\theme\DraculaTheme.java" = @"
package com.stockmanager.ui.theme;

import javax.swing.*;
import java.awt.*;

/**
 * Implements the Dracula purple theme for the application
 */
public class DraculaTheme {
    // Dracula color palette
    public static final Color BACKGROUND = new Color(40, 42, 54);
    public static final Color CURRENT_LINE = new Color(68, 71, 90);
    public static final Color FOREGROUND = new Color(248, 248, 242);
    public static final Color COMMENT = new Color(98, 114, 164);
    public static final Color CYAN = new Color(139, 233, 253);
    public static final Color GREEN = new Color(80, 250, 123);
    public static final Color ORANGE = new Color(255, 184, 108);
    public static final Color PINK = new Color(255, 121, 198);
    public static final Color PURPLE = new Color(189, 147, 249);
    public static final Color RED = new Color(255, 85, 85);
    public static final Color YELLOW = new Color(241, 250, 140);

    /**
     * Applies the Dracula theme to the UI
     */
    public static void apply() {
        // Set UI manager properties to apply the theme globally
        // ...
    }
}
"@

    "src\com\stockmanager\ui\dashboard\DashboardPanel.java" = @"
package com.stockmanager.ui.dashboard;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Main dashboard panel showing summary metrics
 */
public class DashboardPanel extends JPanel {
    // UI components

    public DashboardPanel() {
        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);

        // Create and configure UI components
        // ...
    }
}
"@

    "src\com\stockmanager\ui\stock\StockListPanel.java" = @"
package com.stockmanager.ui.stock;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import com.stockmanager.controllers.StockController;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Panel for displaying and managing the list of stock items
 */
public class StockListPanel extends JPanel {
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private StockController controller;

    // Other UI components

    public StockListPanel() {
        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);

        // Create and configure UI components
        // ...
    }
}
"@

    "src\com\stockmanager\ui\stock\StockFormPanel.java" = @"
package com.stockmanager.ui.stock;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.models.StockItem;
import com.stockmanager.controllers.StockController;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Panel for creating and updating stock items
 */
public class StockFormPanel extends JPanel {
    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<String> supplierComboBox;

    private JButton saveButton;
    private JButton cancelButton;

    private StockController controller;

    public StockFormPanel() {
        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);

        // Create and configure UI components
        // ...
    }
}
"@

    "src\com\stockmanager\ui\stock\DeleteDialog.java" = @"
package com.stockmanager.ui.stock;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.controllers.StockController;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Dialog for confirming the deletion of a stock item
 */
public class DeleteDialog extends JDialog {
    private JLabel messageLabel;
    private JButton confirmButton;
    private JButton cancelButton;

    private String itemId;
    private StockController controller;

    public DeleteDialog(JFrame parent, String itemId, String itemName) {
        super(parent, "Confirm Deletion", true);
        this.itemId = itemId;

        // Initialize the UI
        initializeUI(itemName);
    }

    private void initializeUI(String itemName) {
        // Set up the dialog
        getContentPane().setBackground(DraculaTheme.BACKGROUND);

        // Create and configure UI components
        // ...
    }
}
"@

    "src\com\stockmanager\ui\analytics\AnalyticsPanel.java" = @"
package com.stockmanager.ui.analytics;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Panel for displaying analytics and data visualization
 */
public class AnalyticsPanel extends JPanel {
    private JPanel chartsPanel;
    private JPanel filtersPanel;

    private AnalyticsController controller;

    public AnalyticsPanel() {
        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);

        // Create and configure UI components
        // ...
    }
}
"@

    "src\com\stockmanager\utils\UIUtils.java" = @"
package com.stockmanager.utils;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Utility methods for UI operations
 */
public class UIUtils {
    /**
     * Creates a styled button with the Dracula theme
     */
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(DraculaTheme.PURPLE);
        button.setForeground(DraculaTheme.FOREGROUND);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    /**
     * Creates a styled label with the Dracula theme
     */
    public static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(DraculaTheme.FOREGROUND);
        return label;
    }

    // Other utility methods
}
"@
}

# Create README.md
$readmeContent = @"
# Stock Management System

A modern, sleek stock management system with a Dracula purple theme built using Java Swing.

## Features

- Dashboard with summary metrics
- CRUD operations for managing stock items
- Analytics dashboard for data visualization
- Dark mode UI with Dracula color palette

## Project Structure

- `src/` - Source code
  - `com/stockmanager/` - Main package
    - `models/` - Data models
    - `controllers/` - Business logic
    - `ui/` - UI components
      - `theme/` - Theme implementation
      - `dashboard/` - Dashboard components
      - `stock/` - Stock CRUD components
      - `analytics/` - Analytics components
    - `utils/` - Utility classes
- `resources/` - Resources (images, etc.)
- `lib/` - External libraries

## Requirements

- Java 17 or higher
- Swing/AWT
- JFreeChart (for analytics visualization)

## Setup

1. Clone the repository
2. Open the project in your preferred IDE
3. Run `Main.java` to start the application
"@

# Create a basic build.gradle file
$buildGradleContent = @"
plugins {
    id 'java'
    id 'application'
}

group = 'com.stockmanager'
version = '1.0-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    // JFreeChart for data visualization
    implementation 'org.jfree:jfreechart:1.5.3'

    // FlatLaf for modern Swing look and feel
    implementation 'com.formdev:flatlaf:2.6'

    // Testing
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.2'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
}

application {
    mainClass = 'com.stockmanager.Main'
}

test {
    useJUnitPlatform()
}
"@

foreach ($file in $fileContents.Keys) {
    $filePath = "StockManagementSystem\$file"
    New-Item -Path $filePath -ItemType File -Force
    $fileContents[$file] | Out-File -FilePath $filePath -Encoding utf8
}

# Create README and build files
$readmeContent | Out-File -FilePath "StockManagementSystem\README.md" -Encoding utf8
$buildGradleContent | Out-File -FilePath "StockManagementSystem\build.gradle" -Encoding utf8

Write-Host "Stock Management System project structure created successfully!" -ForegroundColor Green
Write-Host "Navigate to the 'StockManagementSystem' directory to get started." -ForegroundColor Green
```

```

This script creates a complete project structure for your Swing-based stock management system with the Dracula purple theme. It includes:

1. Main source directory with properly organized packages
2. Placeholder Java files with basic class structures for all required components
3. A README.md file documenting the project
4. A build.gradle file for dependency management

The structure follows good software engineering practices with separation of concerns:

- Models for data representation (StockItem, Supplier)
- Controllers for business logic (StockController, AnalyticsController)
- UI components organized by feature (dashboard, stock management, analytics)
- Theme implementation for consistent styling (DraculaTheme)
- Utility classes for common UI operations

After running this script, you'll have a skeleton project ready for implementing the detailed UI components as specified in your requirements.
```
````

## Step 1: Implement the Dracula Theme

Let's first implement the theme to ensure a consistent look across the entire application:

```java
// DraculaTheme.java - Complete implementation
package com.stockmanager.ui.theme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Implements the Dracula purple theme for the application
 */
public class DraculaTheme {
    // Dracula color palette
    public static final Color BACKGROUND = new Color(40, 42, 54);
    public static final Color CURRENT_LINE = new Color(68, 71, 90);
    public static final Color FOREGROUND = new Color(248, 248, 242);
    public static final Color COMMENT = new Color(98, 114, 164);
    public static final Color CYAN = new Color(139, 233, 253);
    public static final Color GREEN = new Color(80, 250, 123);
    public static final Color ORANGE = new Color(255, 184, 108);
    public static final Color PINK = new Color(255, 121, 198);
    public static final Color PURPLE = new Color(189, 147, 249);
    public static final Color RED = new Color(255, 85, 85);
    public static final Color YELLOW = new Color(241, 250, 140);

    // Derived colors
    public static final Color BUTTON_BACKGROUND = PURPLE;
    public static final Color BUTTON_FOREGROUND = FOREGROUND;
    public static final Color BUTTON_HOVER = new Color(159, 117, 219);
    public static final Color BUTTON_PRESSED = new Color(129, 87, 189);

    /**
     * Applies the Dracula theme to the UI
     */
    public static void apply() {
        try {
            // Set cross-platform look and feel as a base
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            // Set global UI colors
            UIManager.put("Panel.background", new ColorUIResource(BACKGROUND));
            UIManager.put("OptionPane.background", new ColorUIResource(BACKGROUND));
            UIManager.put("OptionPane.messageForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("Label.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextField.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("TextField.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextField.caretForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextArea.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("TextArea.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("TextArea.caretForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("ComboBox.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("ComboBox.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("ComboBox.selectionBackground", new ColorUIResource(PURPLE));
            UIManager.put("ComboBox.selectionForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("Button.background", new ColorUIResource(BUTTON_BACKGROUND));
            UIManager.put("Button.foreground", new ColorUIResource(BUTTON_FOREGROUND));
            UIManager.put("Button.select", new ColorUIResource(BUTTON_PRESSED));
            UIManager.put("Button.focus", new ColorUIResource(BUTTON_HOVER));
            UIManager.put("ScrollPane.background", new ColorUIResource(BACKGROUND));
            UIManager.put("ScrollBar.thumb", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(COMMENT));
            UIManager.put("ScrollBar.track", new ColorUIResource(BACKGROUND));
            UIManager.put("Table.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Table.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("Table.selectionBackground", new ColorUIResource(CURRENT_LINE));
            UIManager.put("Table.selectionForeground", new ColorUIResource(CYAN));
            UIManager.put("Table.gridColor", new ColorUIResource(COMMENT));
            UIManager.put("TableHeader.background", new ColorUIResource(CURRENT_LINE));
            UIManager.put("TableHeader.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("Dialog.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Frame.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Menu.background", new ColorUIResource(BACKGROUND));
            UIManager.put("Menu.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("MenuItem.background", new ColorUIResource(BACKGROUND));
            UIManager.put("MenuItem.foreground", new ColorUIResource(FOREGROUND));
            UIManager.put("MenuItem.selectionBackground", new ColorUIResource(CURRENT_LINE));
            UIManager.put("MenuItem.selectionForeground", new ColorUIResource(FOREGROUND));
            UIManager.put("MenuBar.background", new ColorUIResource(BACKGROUND));
            UIManager.put("MenuBar.foreground", new ColorUIResource(FOREGROUND));

            // Create and set a custom border
            Border emptyBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
            UIManager.put("TextField.border", emptyBorder);
            UIManager.put("TextArea.border", emptyBorder);
            UIManager.put("ComboBox.border", emptyBorder);
            UIManager.put("Button.border", emptyBorder);

        } catch (Exception e) {
            System.err.println("Failed to apply Dracula theme: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates a default table cell renderer with Dracula theme colors
     */
    public static DefaultTableCellRenderer createTableCellRenderer() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(BACKGROUND);
        renderer.setForeground(FOREGROUND);
        return renderer;
    }
}
```

## Step 2: Implement the Model Classes

Let's develop our data models:

```java
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
```

```java
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
```

## Step 3: Implement the Controllers

Let's implement basic controller functionality:

```java
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
```

```java
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
```

## Step 4: Implement the UI Utils

```java
// UIUtils.java - Complete implementation
package com.stockmanager.utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.stockmanager.ui.theme.DraculaTheme;

/**
 * Utility methods for UI operations
 */
public class UIUtils {
    /**
     * Creates a styled button with the Dracula theme
     */
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(DraculaTheme.BUTTON_BACKGROUND);
        button.setForeground(DraculaTheme.BUTTON_FOREGROUND);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFont(new Font("Dialog", Font.BOLD, 14));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_BACKGROUND);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_PRESSED);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.BUTTON_HOVER);
            }
        });

        return button;
    }

    /**
     * Creates a styled label with the Dracula theme
     */
    public static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(DraculaTheme.FOREGROUND);
        return label;
    }

    /**
     * Creates a styled header label with the Dracula theme
     */
    public static JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(DraculaTheme.PURPLE);
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        return label;
    }

    /**
     * Creates a styled text field with the Dracula theme
     */
    public static JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(DraculaTheme.CURRENT_LINE);
        textField.setForeground(DraculaTheme.FOREGROUND);
        textField.setCaretColor(DraculaTheme.FOREGROUND);
        textField.setBorder(createTextFieldBorder());
        return textField;
    }

    /**
     * Creates a styled text area with the Dracula theme
     */
    public static JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(DraculaTheme.CURRENT_LINE);
        textArea.setForeground(DraculaTheme.FOREGROUND);
        textArea.setCaretColor(DraculaTheme.FOREGROUND);
        textArea.setBorder(createTextFieldBorder());
        return textArea;
    }

    /**
     * Creates a styled combo box with the Dracula theme
     */
    public static <T> JComboBox<T> createStyledComboBox(T[] items) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setBackground(DraculaTheme.CURRENT_LINE);
        comboBox.setForeground(DraculaTheme.FOREGROUND);
        comboBox.setBorder(createTextFieldBorder());
        return comboBox;
    }

    /**
     * Creates a styled table with the Dracula theme
     */
    public static JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setBackground(DraculaTheme.BACKGROUND);
        table.setForeground(DraculaTheme.FOREGROUND);
        table.setGridColor(DraculaTheme.COMMENT);
        table.getTableHeader().setBackground(DraculaTheme.CURRENT_LINE);
        table.getTableHeader().setForeground(DraculaTheme.FOREGROUND);
        table.setRowHeight(25);
        table.setSelectionBackground(DraculaTheme.PURPLE);
        table.setSelectionForeground(DraculaTheme.FOREGROUND);
        return table;
    }

    /**
     * Creates a styled panel with the Dracula theme
     */
    public static JPanel createStyledPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(DraculaTheme.BACKGROUND);
        return panel;
    }

    /**
     * Creates a common border for text components
     */
    public static Border createTextFieldBorder() {
        Border lineBorder = new LineBorder(DraculaTheme.COMMENT, 1);
        Border emptyBorder = new EmptyBorder(5, 7, 5, 7);
        return new CompoundBorder(lineBorder, emptyBorder);
    }

    /**
     * Creates a panel with titled border
     */
    public static JPanel createPanelWithTitle(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(DraculaTheme.BACKGROUND);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(DraculaTheme.COMMENT),
            title,
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 14),
            DraculaTheme.PURPLE));
        return panel;
    }
}
```

## Step 5: Implement the Main Frame

```java
// MainFrame.java - Complete implementation
package com.stockmanager.ui;

import javax.swing.*;
import java.awt.*;
import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.controllers.StockController;
import com.stockmanager.ui.dashboard.DashboardPanel;
import com.stockmanager.ui.stock.StockListPanel;
import com.stockmanager.ui.analytics.AnalyticsPanel;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Main application window with navigation and content panels
 */
public class MainFrame extends JFrame {
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    // Controllers
    private StockController stockController;
    private AnalyticsController analyticsController;

    // Panels
    private DashboardPanel dashboardPanel;
    private StockListPanel stockListPanel;
    private AnalyticsPanel analyticsPanel;

    public MainFrame() {
        // Initialize controllers
        stockController = new StockController();
        analyticsController = new AnalyticsController(stockController);

        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Apply Dracula theme
        DraculaTheme.apply();

        // Set up the main frame
        setTitle("Stock Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the main layout
        setLayout(new BorderLayout());

        // Create the navigation panel
        navigationPanel = createNavigationPanel();
        add(navigationPanel, BorderLayout.WEST);

        // Create the content panel with card layout
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setBackground(DraculaTheme.BACKGROUND);

        // Create the panels
        dashboardPanel = new DashboardPanel(stockController, analyticsController);
        stockListPanel = new StockListPanel(stockController);
        analyticsPanel = new AnalyticsPanel(analyticsController);

        // Add panels to card layout
        contentPanel.add(dashboardPanel, "dashboard");
        contentPanel.add(stockListPanel, "stock");
        contentPanel.add(analyticsPanel, "analytics");

        add(contentPanel, BorderLayout.CENTER);

        // Show dashboard initially
        cardLayout.show(contentPanel, "dashboard");
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, getHeight()));
        panel.setBackground(DraculaTheme.CURRENT_LINE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // App title
        JLabel titleLabel = new JLabel("Stock Manager");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        titleLabel.setForeground(DraculaTheme.PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        // Create navigation buttons
        JButton dashboardButton = createNavButton("Dashboard", "dashboard");
        JButton stockButton = createNavButton("Stock Management", "stock");
        JButton analyticsButton = createNavButton("Analytics", "analytics");

        // Add components to panel
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(dashboardButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(stockButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(analyticsButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JButton createNavButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(DraculaTheme.CURRENT_LINE);
        button.setForeground(DraculaTheme.FOREGROUND);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Dialog", Font.BOLD, 14));

        button.addActionListener(e -> cardLayout.show(contentPanel, cardName));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.PURPLE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(DraculaTheme.CURRENT_LINE);
            }
        });

        return button;
    }
}
```

## Step 6: Implement the Dashboard Panel

```java
// DashboardPanel.java - Complete implementation
package com.stockmanager.ui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.controllers.StockController;
import com.stockmanager.models.StockItem;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Main dashboard panel showing summary metrics
 */
public class DashboardPanel extends JPanel {
    private StockController stockController;
    private AnalyticsController analyticsController;

    private JLabel totalItemsLabel;
    private JLabel totalValueLabel;
    private JLabel lowStockCountLabel;

    private JPanel summaryPanel;
    private JPanel lowStockPanel;

    public DashboardPanel(StockController stockController, AnalyticsController analyticsController) {
        this.stockController = stockController;
        this.analyticsController = analyticsController;

        // Initialize the UI
        initializeUI();

        // Load data
        refreshData();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create header
        JLabel headerLabel = UIUtils.createHeaderLabel("Dashboard");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        contentPanel.setBackground(DraculaTheme.BACKGROUND);

        // Create summary metrics panel
        summaryPanel = createSummaryPanel();

        // Create low stock panel
        lowStockPanel = createLowStockPanel();

        // Create monthly sales panel
        JPanel monthlySalesPanel = createMonthlySalesPanel();

        // Create supplier distribution panel
        JPanel supplierPanel = createSupplierPanel();

        // Add panels to content panel
        contentPanel.add(summaryPanel);
        contentPanel.add(lowStockPanel);
        contentPanel.add(monthlySalesPanel);
        contentPanel.add(supplierPanel);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createSummaryPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Summary");
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Create labels
        JLabel totalItemsCaption = UIUtils.createStyledLabel("Total Items:");
        totalItemsLabel = UIUtils.createStyledLabel("0");
        totalItemsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        totalItemsLabel.setForeground(DraculaTheme.CYAN);

        JLabel totalValueCaption = UIUtils.createStyledLabel("Total Inventory Value:");
        totalValueLabel = UIUtils.createStyledLabel("$0.00");
        totalValueLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        totalValueLabel.setForeground(DraculaTheme.GREEN);

        JLabel lowStockCaption = UIUtils.createStyledLabel("Low Stock Items:");
        lowStockCountLabel = UIUtils.createStyledLabel("0");
        lowStockCountLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        lowStockCountLabel.setForeground(DraculaTheme.ORANGE);

        // Add labels to panel
        panel.add(totalItemsCaption);
        panel.add(totalItemsLabel);
        panel.add(totalValueCaption);
        panel.add(totalValueLabel);
        panel.add(lowStockCaption);
        panel.add(lowStockCountLabel);

        return panel;
    }

    private JPanel createLowStockPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Low Stock Items");
        panel.setLayout(new BorderLayout());

        // This panel will be populated in refreshData()
        return panel;
    }

    private JPanel createMonthlySalesPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Monthly Sales");
        panel.setLayout(new BorderLayout());

        // In a real application, this would be a chart
        // For this example, we'll use a simple panel with colored bars

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> monthlySales = analyticsController.getMonthlySalesData();

                // Find max value
                double maxValue = monthlySales.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);

                // Draw bars
                int barWidth = getWidth() / monthlySales.size() - 10;
                int maxHeight = getHeight() - 50;
                int x = 10;

                int i = 0;
                for (Map.Entry<String, Double> entry : monthlySales.entrySet()) {
                    String month = entry.getKey();
                    double value = entry.getValue();

                    // Calculate height proportional to value
                    int height = (int)((value / maxValue) * maxHeight);
                    int y = getHeight() - height - 30;

                    // Draw bar
                    g.setColor(getColorForIndex(i));
                    g.fillRect(x, y, barWidth, height);

                    // Draw month label
                    g.setColor(DraculaTheme.FOREGROUND);
                    g.drawString(month, x, getHeight() - 10);

                    x += barWidth + 10;
                    i++;
                }
            }

            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK,
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.RED,
                    DraculaTheme.YELLOW, DraculaTheme.PURPLE, DraculaTheme.CYAN,
                    DraculaTheme.PINK, DraculaTheme.GREEN, DraculaTheme.ORANGE
                };
                return colors[index % colors.length];
            }
        };

        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSupplierPanel() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory by Supplier");
        panel.setLayout(new BorderLayout());

        // In a real application, this would be a pie chart
        // For this example, we'll use a simple panel with colored rectangles

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> data = analyticsController.getSalesBySupplier();

                // Find total value
                double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

                // Draw rectangles
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int radius = Math.min(centerX, centerY) - 20;

                double startAngle = 0;
                int i = 0;

                for (Map.Entry<String, Double> entry : data.entrySet()) {
                    String supplier = entry.getKey();
                    double value = entry.getValue();

                    // Calculate angle
                    double angle = 360.0 * (value / total);

                    // Draw arc
                    g.setColor(getColorForIndex(i));
                    g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, (int)startAngle, (int)angle);

                    // Draw legend item
                    int legendX = 10;
                    int legendY = 20 + (i * 20);
                    g.fillRect(legendX, legendY, 15, 15);

                    g.setColor(DraculaTheme.FOREGROUND);
                    NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
                    g.drawString(supplier + " - " + fmt.format(value), legendX + 25, legendY + 12);

                    startAngle += angle;
                    i++;
                }
            }

            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK,
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.YELLOW
                };
                return colors[index % colors.length];
            }
        };

        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private void refreshData() {
        // Update summary metrics
        List<StockItem> allItems = stockController.getAllStockItems();
        totalItemsLabel.setText(String.valueOf(allItems.size()));

        double totalValue = analyticsController.getTotalInventoryValue();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        totalValueLabel.setText(currencyFormat.format(totalValue));

        List<StockItem> lowStockItems = analyticsController.getLowStockItems(10);
        lowStockCountLabel.setText(String.valueOf(lowStockItems.size()));

        // Update low stock panel
        lowStockPanel.removeAll();
        lowStockPanel.setLayout(new BorderLayout());

        // Create a list to display low stock items
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (StockItem item : lowStockItems) {
            listModel.addElement(item.getName() + " - " + item.getQuantity() + " left");
        }

        JList<String> list = new JList<>(listModel);
        list.setBackground(DraculaTheme.BACKGROUND);
        list.setForeground(DraculaTheme.FOREGROUND);
        list.setSelectionBackground(DraculaTheme.PURPLE);
        list.setSelectionForeground(DraculaTheme.FOREGROUND);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBackground(DraculaTheme.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        lowStockPanel.add(scrollPane, BorderLayout.CENTER);

        // Repaint
        revalidate();
        repaint();
    }
}
```

## Step 7: Implement the Stock List Panel

```java
// StockListPanel.java - Complete implementation
package com.stockmanager.ui.stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.stockmanager.controllers.StockController;
import com.stockmanager.models.StockItem;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for displaying and managing the list of stock items
 */
public class StockListPanel extends JPanel {
    private StockController controller;

    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private StockFormPanel formPanel;

    public StockListPanel(StockController controller) {
        this.controller = controller;

        // Initialize the UI
        initializeUI();

        // Load data
        loadStockItems();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create header
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(DraculaTheme.BACKGROUND);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel headerLabel = UIUtils.createHeaderLabel("Stock Management");
        headerPanel.add(headerLabel, BorderLayout.WEST);

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(DraculaTheme.BACKGROUND);

        searchField = UIUtils.createStyledTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        JButton searchButton = UIUtils.createStyledButton("Search");

        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        headerPanel.add(searchPanel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Create main split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(700);
        splitPane.setDividerSize(1);
        splitPane.setBorder(null);

        // Create table panel
        JPanel tablePanel = createTablePanel();

        // Create form panel
        formPanel = new StockFormPanel(controller);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        splitPane.setLeftComponent(tablePanel);
        splitPane.setRightComponent(formPanel);

        add(splitPane, BorderLayout.CENTER);

        // Set action listeners
        searchButton.addActionListener(this::searchStockItems);
        searchField.addActionListener(this::searchStockItems);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(DraculaTheme.BACKGROUND);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);

        JButton addButton = UIUtils.createStyledButton("Add New");
        JButton editButton = UIUtils.createStyledButton("Edit");
        JButton deleteButton = UIUtils.createStyledButton("Delete");
        JButton refreshButton = UIUtils.createStyledButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        panel.add(buttonPanel, BorderLayout.NORTH);

        // Create table
        String[] columnNames = {"ID", "Name", "Description", "Price", "Quantity", "Supplier", "Total Value"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        stockTable = UIUtils.createStyledTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(stockTable);
        scrollPane.setBackground(DraculaTheme.BACKGROUND);
        scrollPane.getViewport().setBackground(DraculaTheme.BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane, BorderLayout.CENTER);

        // Set action listeners
        addButton.addActionListener(e -> formPanel.clearForm());
        editButton.addActionListener(e -> editSelectedItem());
        deleteButton.addActionListener(e -> deleteSelectedItem());
        refreshButton.addActionListener(e -> loadStockItems());

        return panel;
    }

    private void loadStockItems() {
        List<StockItem> items = controller.getAllStockItems();
        updateTableWithItems(items);
    }

    private void searchStockItems(ActionEvent e) {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            loadStockItems();
        } else {
            List<StockItem> items = controller.searchStockItems(query);
            updateTableWithItems(items);
        }
    }

    private void updateTableWithItems(List<StockItem> items) {
        // Clear the table
        tableModel.setRowCount(0);

        // Format for currency
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        // Add items to the table
        for (StockItem item : items) {
            tableModel.addRow(new Object[] {
                item.getId(),
                item.getName(),
                item.getDescription(),
                currencyFormat.format(item.getPrice()),
                item.getQuantity(),
                item.getSupplier().getName(),
                currencyFormat.format(item.getTotalValue())
            });
        }

        // Adjust column widths
        stockTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        stockTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        stockTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        stockTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        stockTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        stockTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        stockTable.getColumnModel().getColumn(6).setPreferredWidth(100);
    }

    private void editSelectedItem() {
        int selectedRow = stockTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            StockItem item = controller.getStockItemById(id);
            if (item != null) {
                formPanel.setItem(item);
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Please select an item to edit.",
                "No Selection",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void deleteSelectedItem() {
        int selectedRow = stockTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);

            DeleteDialog dialog = new DeleteDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                id,
                name,
                controller
            );

            dialog.setVisible(true);

            // Refresh the table after deletion
            loadStockItems();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Please select an item to delete.",
                "No Selection",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
```

## Step 8: Implement the Stock Form Panel

```java
// StockFormPanel.java - Complete implementation
package com.stockmanager.ui.stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import com.stockmanager.controllers.StockController;
import com.stockmanager.models.StockItem;
import com.stockmanager.models.Supplier;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for creating and updating stock items
 */
public class StockFormPanel extends JPanel {
    private StockController controller;

    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<Supplier> supplierComboBox;

    private JButton saveButton;
    private JButton cancelButton;

    private StockItem currentItem;

    public StockFormPanel(StockController controller) {
        this.controller = controller;

        // Initialize the UI
        initializeUI();

        // Clear the form to start
        clearForm();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(0, 20));

        // Create form header
        JLabel headerLabel = UIUtils.createHeaderLabel("Item Details");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Create form fields panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(DraculaTheme.BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // ID field (hidden from user but used for updates)
        idField = UIUtils.createStyledTextField();
        idField.setEditable(false);
        idField.setVisible(false);

        // Name field
        JLabel nameLabel = UIUtils.createStyledLabel("Name:");
        nameField = UIUtils.createStyledTextField();

        // Description field
        JLabel descriptionLabel = UIUtils.createStyledLabel("Description:");
        descriptionArea = UIUtils.createStyledTextArea();
        descriptionArea.setRows(3);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setBackground(DraculaTheme.CURRENT_LINE);
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Price field
        JLabel priceLabel = UIUtils.createStyledLabel("Price:");
        priceField = UIUtils.createStyledTextField();

        // Quantity field
        JLabel quantityLabel = UIUtils.createStyledLabel("Quantity:");
        quantityField = UIUtils.createStyledTextField();

        // Supplier combo box
        JLabel supplierLabel = UIUtils.createStyledLabel("Supplier:");
        Supplier[] suppliers = controller.getAllSuppliers().toArray(new Supplier[0]);
        supplierComboBox = UIUtils.createStyledComboBox(suppliers);

        // Add components to grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        formPanel.add(descriptionScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        formPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(supplierLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(supplierComboBox, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);

        saveButton = UIUtils.createStyledButton("Save");
        cancelButton = UIUtils.createStyledButton("Cancel");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Set action listeners
        saveButton.addActionListener(this::saveItem);
        cancelButton.addActionListener(e -> clearForm());
    }

    public void setItem(StockItem item) {
        this.currentItem = item;

        idField.setText(item.getId());
        nameField.setText(item.getName());
        descriptionArea.setText(item.getDescription());
        priceField.setText(String.valueOf(item.getPrice()));
        quantityField.setText(String.valueOf(item.getQuantity()));

        // Select the correct supplier in the combo box
        Supplier itemSupplier = item.getSupplier();
        for (int i = 0; i < supplierComboBox.getItemCount(); i++) {
            Supplier supplier = supplierComboBox.getItemAt(i);
            if (supplier.getId().equals(itemSupplier.getId())) {
                supplierComboBox.setSelectedIndex(i);
                break;
            }
        }

        // Update button text
        saveButton.setText("Update");
    }

    public void clearForm() {
        this.currentItem = null;

        idField.setText("");
        nameField.setText("");
        descriptionArea.setText("");
        priceField.setText("");
        quantityField.setText("");

        if (supplierComboBox.getItemCount() > 0) {
            supplierComboBox.setSelectedIndex(0);
        }

        // Update button text
        saveButton.setText("Save");
    }

    private void saveItem(ActionEvent e) {
        // Validate inputs
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = 0;
        try {
            price = Double.parseDouble(priceField.getText().trim());
            if (price < 0) {
                throw new NumberFormatException("Price cannot be negative");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityField.getText().trim());
            if (quantity < 0) {
                throw new NumberFormatException("Quantity cannot be negative");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the selected supplier
        Supplier supplier = (Supplier) supplierComboBox.getSelectedItem();
        if (supplier == null) {
            JOptionPane.showMessageDialog(this, "Please select a supplier.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create or update the item
        if (currentItem == null) {
            // Create new item
            StockItem newItem = new StockItem(
                nameField.getText().trim(),
                descriptionArea.getText().trim(),
                price,
                quantity,
                supplier
            );

            controller.saveStockItem(newItem);
            JOptionPane.showMessageDialog(this, "Item saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Update existing item
            currentItem.setName(nameField.getText().trim());
            currentItem.setDescription(descriptionArea.getText().trim());
            currentItem.setPrice(price);
            currentItem.setQuantity(quantity);
            currentItem.setSupplier(supplier);

            controller.saveStockItem(currentItem);
            JOptionPane.showMessageDialog(this, "Item updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        // Clear the form
        clearForm();

        // Fire an event to notify that the items have changed
        // In a real app, we would use a proper event system
        // For this example, we'll just refresh the table when Save is clicked
        Component parent = getParent();
        while (parent != null && !(parent instanceof StockListPanel)) {
            parent = parent.getParent();
        }

        if (parent instanceof StockListPanel) {
            StockListPanel panel = (StockListPanel) parent;
            // This is a bit of a hack, but for the purpose of this example...
            panel.dispatchEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh"));
        }
    }
}
```

## Step 9: Implement the Delete Dialog

```java
// DeleteDialog.java - Complete implementation
package com.stockmanager.ui.stock;

import javax.swing.*;
import java.awt.*;

import com.stockmanager.controllers.StockController;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Dialog for confirming the deletion of a stock item
 */
public class DeleteDialog extends JDialog {
    private JLabel messageLabel;
    private JButton confirmButton;
    private JButton cancelButton;

    private String itemId;
    private StockController controller;

    public DeleteDialog(JFrame parent, String itemId, String itemName, StockController controller) {
        super(parent, "Confirm Deletion", true);
        this.itemId = itemId;
        this.controller = controller;

        // Initialize the UI
        initializeUI(itemName);
    }

    private void initializeUI(String itemName) {
        // Set up the dialog
        getContentPane().setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setSize(400, 200);
        setLocationRelativeTo(getParent());

        // Create message
        JPanel messagePanel = new JPanel(new BorderLayout(0, 20));
        messagePanel.setBackground(DraculaTheme.BACKGROUND);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.warningIcon"));

        messageLabel = UIUtils.createStyledLabel(
            "<html>Are you sure you want to delete <b>" + itemName + "</b>?<br>This action cannot be undone.</html>");
        messageLabel.setForeground(DraculaTheme.ORANGE);

        messagePanel.add(iconLabel, BorderLayout.WEST);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.CENTER);

        // Create buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(DraculaTheme.BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        confirmButton = UIUtils.createStyledButton("Delete");
        confirmButton.setBackground(DraculaTheme.RED);
        cancelButton = UIUtils.createStyledButton("Cancel");

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Set action listeners
        confirmButton.addActionListener(e -> {
            controller.deleteStockItem(itemId);
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());
    }
}
```

## Step 10: Implement the Analytics Panel

```java
// AnalyticsPanel.java - Complete implementation
package com.stockmanager.ui.analytics;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import com.stockmanager.controllers.AnalyticsController;
import com.stockmanager.ui.theme.DraculaTheme;
import com.stockmanager.utils.UIUtils;

/**
 * Panel for displaying analytics and data visualization
 */
public class AnalyticsPanel extends JPanel {
    private AnalyticsController controller;

    private JPanel chartPanel1;
    private JPanel chartPanel2;
    private JPanel chartPanel3;
    private JPanel chartPanel4;

    public AnalyticsPanel(AnalyticsController controller) {
        this.controller = controller;

        // Initialize the UI
        initializeUI();
    }

    private void initializeUI() {
        // Set up the panel
        setBackground(DraculaTheme.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create header
        JLabel headerLabel = UIUtils.createHeaderLabel("Analytics Dashboard");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        contentPanel.setBackground(DraculaTheme.BACKGROUND);

        // Create chart panels
        chartPanel1 = createMonthlySalesChart();
        chartPanel2 = createInventoryBySupplierChart();
        chartPanel3 = createInventoryByCategoryChart();
        chartPanel4 = createInventoryLevelsChart();

        contentPanel.add(chartPanel1);
        contentPanel.add(chartPanel2);
        contentPanel.add(chartPanel3);
        contentPanel.add(chartPanel4);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createMonthlySalesChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Monthly Sales");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the monthly sales
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> monthlySales = controller.getMonthlySalesData();

                // Find max value
                double maxValue = monthlySales.values().stream().mapToDouble(Double::doubleValue).max().orElse(0);

                // Draw bars
                int barWidth = getWidth() / monthlySales.size() - 10;
                int maxHeight = getHeight() - 60;
                int x = 10;

                // Draw title and scale
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Sales by Month", 10, 20);

                // Format for currency
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

                // Draw scale
                g.setColor(DraculaTheme.COMMENT);
                g.drawString(currencyFormat.format(maxValue), 10, 40);
                g.drawString("$0", 10, getHeight() - 20);

                int i = 0;
                for (Map.Entry<String, Double> entry : monthlySales.entrySet()) {
                    String month = entry.getKey();
                    double value = entry.getValue();

                    // Calculate height proportional to value
                    int height = (int)((value / maxValue) * maxHeight);
                    int y = getHeight() - height - 30;

                    // Draw bar
                    g.setColor(getColorForIndex(i));
                    g.fillRect(x, y, barWidth, height);

                    // Draw month label
                    g.setColor(DraculaTheme.FOREGROUND);
                    g.drawString(month, x + barWidth/2 - 10, getHeight() - 10);

                    // Draw value label
                    if (height > 20) {  // Only draw if there's enough space
                        g.setColor(DraculaTheme.BACKGROUND);
                        String valueStr = currencyFormat.format(value);
                        g.drawString(valueStr, x + barWidth/2 - 20, y + height/2);
                    }

                    x += barWidth + 10;
                    i++;
                }
            }

            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK,
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.RED,
                    DraculaTheme.YELLOW, DraculaTheme.PURPLE, DraculaTheme.CYAN,
                    DraculaTheme.PINK, DraculaTheme.GREEN, DraculaTheme.ORANGE
                };
                return colors[index % colors.length];
            }
        };

        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInventoryBySupplierChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory by Supplier");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the supplier distribution
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> data = controller.getSalesBySupplier();

                // Find total value
                double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

                // Draw title
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Inventory Value by Supplier", 10, 20);

                // Draw pie chart
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int radius = Math.min(centerX, centerY) - 60;

                double startAngle = 0;
                int i = 0;

                for (Map.Entry<String, Double> entry : data.entrySet()) {
                    String supplier = entry.getKey();
                    double value = entry.getValue();

                    // Calculate angle
                    double angle = 360.0 * (value / total);

                    // Draw arc
                    g.setColor(getColorForIndex(i));
                    g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, (int)startAngle, (int)angle);

                    // Calculate position for the label (outside the pie)
                    double radians = Math.toRadians(startAngle + angle/2);
                    int labelX = centerX + (int)((radius + 20) * Math.cos(radians));
                    int labelY = centerY + (int)((radius + 20) * Math.sin(radians));

                    // Draw line from pie to label
                    g.setColor(DraculaTheme.COMMENT);
                    g.drawLine(
                        centerX + (int)(radius * Math.cos(radians)),
                        centerY + (int)(radius * Math.sin(radians)),
                        labelX, labelY
                    );

                    // Draw legend item
                    int legendX = 10;
                    int legendY = getHeight() - 120 + (i * 20);
                    g.setColor(getColorForIndex(i));
                    g.fillRect(legendX, legendY, 15, 15);

                    g.setColor(DraculaTheme.FOREGROUND);
                    NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
                    g.drawString(supplier + " - " + fmt.format(value), legendX + 25, legendY + 12);

                    startAngle += angle;
                    i++;
                }
            }

            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK,
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.YELLOW
                };
                return colors[index % colors.length];
            }
        };

        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInventoryByCategoryChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory by Category");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the category distribution
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Double> data = controller.getInventoryValueByCategory();

                // Find total value
                double total = data.values().stream().mapToDouble(Double::doubleValue).sum();

                // Draw title
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Inventory Distribution by Category", 10, 20);

                // Draw bars
                int barHeight = 30;
                int maxWidth = getWidth() - 200;
                int y = 50;

                // Format for currency
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
                NumberFormat percentFormat = NumberFormat.getPercentInstance();
                percentFormat.setMaximumFractionDigits(1);

                int i = 0;
                for (Map.Entry<String, Double> entry : data.entrySet()) {
                    String category = entry.getKey();
                    double value = entry.getValue();

                    // Calculate width proportional to value
                    int width = (int)((value / total) * maxWidth);

                    // Draw bar
                    g.setColor(getColorForIndex(i));
                    g.fillRect(150, y, width, barHeight);

                    // Draw category label
                    g.setColor(DraculaTheme.FOREGROUND);
                    g.drawString(category, 10, y + barHeight/2 + 5);

                    // Draw value label
                    String valueStr = currencyFormat.format(value) + " (" + percentFormat.format(value/total) + ")";
                    g.drawString(valueStr, 150 + width + 10, y + barHeight/2 + 5);

                    y += barHeight + 10;
                    i++;
                }
            }

            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK,
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.YELLOW
                };
                return colors[index % colors.length];
            }
        };

        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInventoryLevelsChart() {
        JPanel panel = UIUtils.createPanelWithTitle("Inventory Levels");
        panel.setLayout(new BorderLayout());

        // Create a chart panel for the inventory levels
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the data
                Map<String, Integer> data = controller.getInventoryLevels();

                // Find max value
                int maxValue = data.values().stream().mapToInt(Integer::intValue).max().orElse(0);

                // Draw title
                g.setColor(DraculaTheme.FOREGROUND);
                g.setFont(new Font("Dialog", Font.BOLD, 12));
                g.drawString("Stock Quantity by Item", 10, 20);

                // Draw bars - we'll show top 10 items to keep it readable
                int barHeight = 25;
                int maxWidth = getWidth() - 200;
                int y = 50;

                int count = 0;
                for (Map.Entry<String, Integer> entry : data.entrySet()) {
                    if (count++ >= 10) break;

                    String item = entry.getKey();
                    int quantity = entry.getValue();

                    // Calculate width proportional to value
                    int width = (int)((double)quantity / maxValue * maxWidth);

                    // Choose color based on stock level
                    Color barColor;
                    if (quantity < 10) {
                        barColor = DraculaTheme.RED;  // Low stock
                    } else if (quantity < 20) {
                        barColor = DraculaTheme.ORANGE;  // Medium stock
                    } else {
                        barColor = DraculaTheme.GREEN;  // High stock
                    }

                    // Draw bar
                    g.setColor(barColor);
                    g.fillRect(150, y, width, barHeight);
// Draw bar
                    g.setColor(barColor);
                    g.fillRect(150, y, width, barHeight);

                    // Draw item label
                    g.setColor(DraculaTheme.FOREGROUND);
                    g.drawString(item, 10, y + barHeight/2 + 5);

                    // Draw quantity label
                    g.drawString(String.valueOf(quantity) + " units", 150 + width + 10, y + barHeight/2 + 5);

                    y += barHeight + 10;
                }

                // Draw legend
                int legendY = getHeight() - 80;

                g.setColor(DraculaTheme.RED);
                g.fillRect(10, legendY, 15, 15);
                g.setColor(DraculaTheme.FOREGROUND);
                g.drawString("Low Stock (< 10)", 30, legendY + 12);

                legendY += 20;
                g.setColor(DraculaTheme.ORANGE);
                g.fillRect(10, legendY, 15, 15);
                g.setColor(DraculaTheme.FOREGROUND);
                g.drawString("Medium Stock (10-20)", 30, legendY + 12);

                legendY += 20;
                g.setColor(DraculaTheme.GREEN);
                g.fillRect(10, legendY, 15, 15);
                g.setColor(DraculaTheme.FOREGROUND);
                g.drawString("High Stock (> 20)", 30, legendY + 12);
            }

            private Color getColorForIndex(int index) {
                Color[] colors = {
                    DraculaTheme.PURPLE, DraculaTheme.CYAN, DraculaTheme.PINK,
                    DraculaTheme.GREEN, DraculaTheme.ORANGE, DraculaTheme.YELLOW
                };
                return colors[index % colors.length];
            }
        };

        chartPanel.setBackground(DraculaTheme.BACKGROUND);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    public void refreshData() {
        // Force a repaint to refresh the chart data
        chartPanel1.repaint();
        chartPanel2.repaint();
        chartPanel3.repaint();
        chartPanel4.repaint();
    }
}

```
