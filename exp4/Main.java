import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class Main {
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Employee\n2. Update Employee\n3. Remove Employee\n4. Search Employee\n5. Display All Employees\n6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            if (choice == 1) addEmployee();
            else if (choice == 2) updateEmployee();
            else if (choice == 3) removeEmployee();
            else if (choice == 4) searchEmployee();
            else if (choice == 5) displayAllEmployees();
            else if (choice == 6) break;
            else System.out.println("Invalid choice.");
        }
    }

    static void addEmployee() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        employeeList.add(new Employee(id, name, salary));
        System.out.print("Employee added successfully");
    }

    static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt(); scanner.nextLine();
        employeeList.stream()
            .filter(e -> e.id == id)
            .findFirst()
            .ifPresentOrElse(e -> {
                System.out.print("New Name: ");
                e.name = scanner.nextLine();
                System.out.print("New Salary: ");
                e.salary = scanner.nextDouble();
            }, () -> System.out.println("Employee not found."));
    }

    static void removeEmployee() {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt(); scanner.nextLine();
        boolean removed = employeeList.removeIf(e -> e.id == id);
        if (!removed) System.out.println("Employee not found.");
    }

    static void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt(); scanner.nextLine();
        employeeList.stream()
            .filter(e -> e.id == id)
            .findFirst()
            .ifPresentOrElse(System.out::println, () -> System.out.println("Employee not found."));
    }

    static void displayAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees.");
        } else {
            employeeList.forEach(System.out::println);
        }
    }
}
