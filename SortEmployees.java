import java.util.*;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return id + " - " + name + " - " + salary;
    }
}

public class SortEmployees {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(101, "Ravi", 45000),
            new Employee(102, "Aman", 55000),
            new Employee(103, "Neha", 40000),
            new Employee(104, "Suman", 60000)
        );

        System.out.println("Sorting by Name:");
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        employees.forEach(System.out::println);

        System.out.println("\nSorting by Salary (Descending):");
        employees.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        employees.forEach(System.out::println);
    }
}
