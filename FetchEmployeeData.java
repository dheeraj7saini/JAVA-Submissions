import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        // Step 1: Database connection details
        String url = "jdbc:mysql://localhost:3306/companydb"; // Change to your DB name
        String user = "root";                                 // Your MySQL username
        String password = "password";                         // Your MySQL password

        // Step 2: SQL query
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        // Step 3: Establish connection and execute query
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to the MySQL database successfully!\n");

            // Create statement
            Statement stmt = con.createStatement();

            // Execute query
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("=== Employee Table Data ===");
            System.out.printf("%-10s %-20s %-10s%n", "EmpID", "Name", "Salary");
            System.out.println("-------------------------------------------");

            // Step 4: Process result set
            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.printf("%-10d %-20s %-10.2f%n", empId, name, salary);
            }

            // Step 5: Close resources
            rs.close();
            stmt.close();
            con.close();
            System.out.println("\n✅ Data fetched successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found! Please add the connector JAR file.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database connection or query error!");
            e.printStackTrace();
        }
    }
}
