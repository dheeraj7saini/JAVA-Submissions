import java.io.*;

// Step 1: Create the Student class that implements Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L;  // For version control
    
    private int studentID;
    private String name;
    private String grade;

    // Constructor
    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    // Display method
    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }
}

public class StudentSerializationDemo {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Step 2: Create a Student object
        Student student1 = new Student(101, "Dheeraj Saini", "A+");

        // Step 3: Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(student1);
            System.out.println("✅ Student object has been serialized and saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 4: Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Student deserializedStudent = (Student) in.readObject();
            System.out.println("\n✅ Student object has been deserialized successfully!");
            deserializedStudent.displayInfo();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
