import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void displayEmployee() {
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Salary: " + salary);
        System.out.println();
    }
}

public class Main {
    static Employee[] employees = new Employee[10];  
    static int employeeCount = 0;  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Display Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    updateEmployee(scanner);
                    break;
                case 3:
                    deleteEmployee(scanner);
                    break;
                case 4:
                    displayEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return; 
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }


    public static void addEmployee(Scanner scanner) {
        if (employeeCount >= employees.length) {
            System.out.println("Employee list is full!");
            return;
        }

        System.out.println("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  

        System.out.println("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();  


        employees[employeeCount++] = new Employee(id, name, salary);
        System.out.println("Employee added successfully.\n");
    }


    public static void updateEmployee(Scanner scanner) {
        System.out.println("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].id == id) {
                found = true;
                System.out.println("Enter new Employee Name: ");
                String name = scanner.nextLine();

                System.out.println("Enter new Employee Salary: ");
                double salary = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                employees[i].name = name;
                employees[i].salary = salary;
                System.out.println("Employee updated successfully.\n");
                break;
            }
        }
        if (!found) {
            System.out.println("Employee with ID " + id + " not found.\n");
        }
    }


    public static void deleteEmployee(Scanner scanner) {
        System.out.println("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].id == id) {
                found = true;
                for (int j = i; j < employeeCount - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--employeeCount] = null; 
                System.out.println("Employee deleted successfully.\n");
                break;
            }
        }
        if (!found) {
            System.out.println("Employee with ID " + id + " not found.\n");
        }
    }


    public static void displayEmployees() {
        if (employeeCount == 0) {
            System.out.println("No employees to display.\n");
            return;
        }

        System.out.println("Employee Details:");
        for (int i = 0; i < employeeCount; i++) {
            employees[i].displayEmployee();
        }
    }
}
