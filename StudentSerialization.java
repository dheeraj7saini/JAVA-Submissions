import java.io.*;

// Step 1: Create Student class implementing Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int studentID;
    private String name;
    private String grade;

    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }
}

// Step 2: Main class
public class StudentSerialization {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Create Student object
        Student s1 = new Student(101, "Dheeraj Saini", "A+");

        // --- Serialization ---
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(s1);
            System.out.println("✅ Student object serialized and saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Deserialization ---
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Student s2 = (Student) in.readObject();
            System.out.println("\n✅ Student object deserialized successfully!");
            s2.displayInfo();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
