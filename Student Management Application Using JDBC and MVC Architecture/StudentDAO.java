// StudentDAO.java
import java.sql.*;
import java.util.*;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password_here";

    // Establish database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE - Add new student
    public void addStudent(Student s) {
        String query = "INSERT INTO student (studentID, name, department, marks) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }
    }

    // READ - Display all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection con = getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("studentID"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("marks")));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching students: " + e.getMessage());
        }
        return students;
    }

    // UPDATE - Update student details
    public void updateStudent(Student s) {
        String query = "UPDATE student SET name=?, department=?, marks=? WHERE studentID=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.setInt(4, s.getStudentID());
            int updated = ps.executeUpdate();
            if (updated > 0)
                System.out.println("✅ Student updated successfully!");
            else
                System.out.println("⚠️ No student found with ID " + s.getStudentID());
        } catch (SQLException e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
        }
    }

    // DELETE - Remove a student
    public void deleteStudent(int id) {
        String query = "DELETE FROM student WHERE studentID=?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted > 0)
                System.out.println("✅ Student deleted successfully!");
            else
                System.out.println("⚠️ No student found with ID " + id);
        } catch (SQLException e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
        }
    }
}
