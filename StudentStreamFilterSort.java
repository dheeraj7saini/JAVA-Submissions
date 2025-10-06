import java.util.*;
import java.util.stream.*;

class Student {
    private String name;
    private double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }
    public double getMarks() { return marks; }
}

public class StudentStreamFilterSort {
    public static void main(String[] args) {
        // Step 1: Create a list of students
        List<Student> students = Arrays.asList(
                new Student("Dheeraj", 82.5),
                new Student("Priyanka", 91.2),
                new Student("Aarav", 68.4),
                new Student("Sneha", 77.9),
                new Student("Rohan", 59.3)
        );

        System.out.println("=== Students scoring above 75% (sorted by marks) ===");

        // Step 2: Filter, sort, and display using Streams
        students.stream()
                .filter(s -> s.getMarks() > 75)                      // Filtering
                .sorted(Comparator.comparingDouble(Student::getMarks)) // Sorting by marks (ascending)
                .map(Student::getName)                                // Extract only names
                .forEach(System.out::println);                        // Display names
    }
}
