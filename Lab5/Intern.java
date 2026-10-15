public class Intern extends Employee {                       // NEW class: payroll loop untouched
    private double stipend;
    Intern(String n, double stipend) { super(n); this.stipend = stipend; }
    double monthlyPay() { return stipend; }
}
