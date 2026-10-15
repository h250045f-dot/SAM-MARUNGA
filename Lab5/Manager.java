public class Manager extends Employee {
    private double salary, bonus;
    Manager(String n, double salary, double bonus) { super(n); this.salary = salary; this.bonus = bonus; }
    double monthlyPay() { return salary + bonus; }
}
