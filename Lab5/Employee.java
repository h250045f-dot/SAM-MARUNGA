/* ---------- Q3: Employee hierarchy ---------- */
public abstract class Employee {
    protected String name;
    Employee(String name) { this.name = name; }
    abstract double monthlyPay();
}
