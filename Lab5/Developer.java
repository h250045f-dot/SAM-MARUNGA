public class Developer extends Employee {
    private double salary;
    Developer(String n, double salary) { super(n); this.salary = salary; }
    double monthlyPay() { return salary; }
}
