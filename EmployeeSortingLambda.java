import java.util.*;

// Step 1: Create the Employee class
class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    // Display method
    public void display() {
        System.out.println("Name: " + name + " | Age: " + age + " | Salary: " + salary);
    }
}

public class EmployeeSortingLambda {
    public static void main(String[] args) {

        // Step 2: Create and store Employee objects in a List
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Dheeraj", 25, 50000));
        employees.add(new Employee("Priyanka", 22, 60000));
        employees.add(new Employee("Aarav", 28, 45000));
        employees.add(new Employee("Sneha", 24, 70000));

        System.out.println("=== Original Employee List ===");
        employees.forEach(Employee::display);

        // Step 3a: Sort by Name (Alphabetically)
        employees.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
        System.out.println("\n=== Sorted by Name (Alphabetical) ===");
        employees.forEach(Employee::display);

        // Step 3b: Sort by Age (Ascending)
        employees.sort((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println("\n=== Sorted by Age (Ascending) ===");
        employees.forEach(Employee::display);

        // Step 3c: Sort by Salary (Descending)
        employees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        System.out.println("\n=== Sorted by Salary (Descending) ===");
        employees.forEach(Employee::display);
    }
}
