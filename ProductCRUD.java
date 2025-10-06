import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/companydb"; // Change DB name
    private static final String USER = "root";                                 // Your MySQL username
    private static final String PASSWORD = "password";                         // Your MySQL password
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver Loaded.");

            // Establish database connection
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
                con.setAutoCommit(false); // Transaction control enabled
                System.out.println("✅ Connected to Database: companydb");

                int choice;
                do {
                    System.out.println("\n===== PRODUCT MANAGEMENT MENU =====");
                    System.out.println("1. Add Product");
                    System.out.println("2. View All Products");
                    System.out.println("3. Update Product");
                    System.out.println("4. Delete Product");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();

                    switch (choice) {
                        case 1 -> addProduct(con);
                        case 2 -> viewProducts(con);
                        case 3 -> updateProduct(con);
                        case 4 -> deleteProduct(con);
                        case 5 -> System.out.println("Exiting program... Goodbye!");
                        default -> System.out.println("⚠ Invalid choice! Try again.");
                    }
                } while (choice != 5);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found! Please add MySQL Connector JAR.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= CREATE =================
    private static void addProduct(Connection con) {
        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter Product Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);

            int rows = ps.executeUpdate();
            con.commit();
            System.out.println("✅ Product added successfully! (" + rows + " row(s) affected)");
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.out.println("❌ Failed to add product. Transaction rolled back.");
        }
    }

    // ================= READ =================
    private static void viewProducts(Connection con) {
        String sql = "SELECT * FROM Product";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n=== Product Table ===");
            System.out.printf("%-10s %-20s %-10s %-10s%n", "ProductID", "ProductName", "Price", "Quantity");
            System.out.println("----------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10d %-20s %-10.2f %-10d%n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving products.");
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    private static void updateProduct(Connection con) {
        String sql = "UPDATE Product SET ProductName = ?, Price = ?, Quantity = ? WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter Product ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter New Product Name: ");
            String name = sc.nextLine();
            System.out.print("Enter New Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter New Quantity: ");
            int qty = sc.nextInt();

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product updated successfully!");
            } else {
                System.out.println("⚠ Product not found!");
            }
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.out.println("❌ Failed to update product. Transaction rolled back.");
        }
    }

    // ================= DELETE =================
    private static void deleteProduct(Connection con) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter Product ID to Delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product deleted successfully!");
            } else {
                System.out.println("⚠ Product not found!");
            }
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.out.println("❌ Failed to delete product. Transaction rolled back.");
        }
    }
}
