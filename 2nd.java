import java.io.*;
import java.util.*;

public class NimbusTask {

    // Part A: Sum of Integers using Autoboxing and Unboxing
    public static void sumOfIntegers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter integers separated by space to calculate the sum (or type 'exit' to skip):");
        
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            return;
        }

        String[] inputs = input.split(" ");
        List<Integer> numbers = new ArrayList<>();

        // Autoboxing: Converting String inputs to Integer and adding them to the list
        for (String str : inputs) {
            try {
                int num = Integer.parseInt(str);
                numbers.add(num);  // Autoboxing occurs here
            } catch (NumberFormatException e) {
                System.out.println("Invalid number: " + str);
            }
        }

        // Calculating sum using Unboxing
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;  // Unboxing occurs here
        }

        System.out.println("The sum of the integers is: " + sum);
    }

    // Part B: Serialization and Deserialization of a Student Object
    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;
        private int studentID;
        private String name;
        private String grade;

        public Student(int studentID, String name, String grade) {
            this.studentID = studentID;
            this.name = name;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "StudentID: " + studentID + ", Name: " + name + ", Grade: " + grade;
        }
    }

    public static void serializeStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student details to serialize:");

        System.out.print("Enter Student ID: ");
        int studentID = sc.nextInt();
        sc.nextLine();  // consume newline

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Grade: ");
        String grade = sc.nextLine();

        Student student = new Student(studentID, name, grade);

        // Serialize the student object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(student);
            System.out.println("Student data serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error serializing student: " + e.getMessage());
        }
    }

    public static void deserializeStudent() {
        // Deserialize the student object
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student student = (Student) ois.readObject();
            System.out.println("Deserialized Student: " + student);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing student: " + e.getMessage());
        }
    }

    // Part C: Employee Management System Using File Handling
    static class Employee {
        private String name;
        private String empID;
        private String designation;
        private double salary;

        public Employee(String name, String empID, String designation, double salary) {
            this.name = name;
            this.empID = empID;
            this.designation = designation;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee ID: " + empID + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
        }
    }

    public static void addEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee details:");

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Employee ID: ");
        String empID = sc.nextLine();

        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine(); // consume newline

        Employee employee = new Employee(name, empID, designation, salary);

        // Append employee details to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
            writer.write(employee.toString());
            writer.newLine();
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing employee data: " + e.getMessage());
        }
    }

    public static void displayEmployees() {
        System.out.println("Displaying all employees:");

        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading employee data: " + e.getMessage());
        }
    }

    // Main menu for employee management system
    public static void employeeMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting Employee Management System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Nimbus Task ===");
            System.out.println("1. Sum of Integers using Autoboxing and Unboxing");
            System.out.println("2. Serialize Student");
            System.out.println("3. Deserialize Student");
            System.out.println("4. Employee Management System");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    sumOfIntegers();
                    break;
                case 2:
                    serializeStudent();
                    break;
                case 3:
                    deserializeStudent();
                    break;
                case 4:
                    employeeMenu();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
