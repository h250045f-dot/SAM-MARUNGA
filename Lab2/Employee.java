/* ---------- Q3: Employee ---------- */
public class Employee {
    private static int nextId = 1;
    private final int employeeId;
    private String name;
    private double salary;
    private String department;

    Employee(String name)                              { this(name, 0.0); }
    Employee(String name, double salary)               { this(name, salary, "Unassigned"); }
    Employee(String name, double salary, String dept)  {
        this.employeeId = nextId++;
        this.name = name;
        this.salary = salary;
        this.department = dept;
    }
    // Copy constructor: copies the data, but the copy is a NEW employee so it gets a NEW id.
    Employee(Employee other) { this(other.name, other.salary, other.department); }

    @Override public String toString() {
        return "Employee#" + employeeId + "{" + name + ", $" + salary + ", " + department + "}";
    }
}
