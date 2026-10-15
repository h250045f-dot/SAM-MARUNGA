import java.util.ArrayList;
import java.util.List;

public class Lab3 {
    static void report(Reading r) {            // caller only knows the getters
        System.out.printf("  %.2fC  %.2fF%n", r.getCelsius(), r.getFahrenheit());
    }

    public static void main(String[] args) {
        System.out.println("=== Q1 & Q2: Student ===");
        Student s = new Student("Nyasha", 82);
        System.out.println(s.getName() + " " + s.getMarks() + " grade " + s.getGrade());
        s.setMarks(55);
        System.out.println("After setMarks(55): grade " + s.getGrade());
        try { s.setMarks(120); } catch (IllegalArgumentException e) { System.out.println("Rejected: " + e.getMessage()); }
        System.out.println("Marks unchanged: " + s.getMarks());

        System.out.println("\n=== Q3: BankAccount history ===");
        BankAccount acc = new BankAccount();
        acc.deposit(500); acc.withdraw(120); acc.withdraw(1000);
        List<String> h = acc.getHistory();
        h.forEach(x -> System.out.println("  " + x));
        try { h.add("HACK"); } catch (UnsupportedOperationException e) { System.out.println("History is read-only: UnsupportedOperationException"); }

        System.out.println("\n=== Q4: ImmutablePerson ===");
        List<String> hobbies = new ArrayList<>(List.of("chess", "football"));
        ImmutablePerson p = new ImmutablePerson("Tino", 20, hobbies);
        hobbies.add("sneaky");                       // does not affect p
        System.out.println(p);
        ImmutablePerson older = p.withAge(21);
        System.out.println(p + "  ->  " + older);

        System.out.println("\n=== Q5: Temperature in kelvin ===");
        report(Temperature.ofCelsius(100));
        report(new Temperature(0));
        try { new Temperature(-5); } catch (IllegalArgumentException e) { System.out.println("Rejected: " + e.getMessage()); }
        System.out.println("Same caller code, internal unit switched to Celsius:");
        report(new TemperatureCelsiusInside(100));
        System.out.println("Callers depend on getCelsius()/getFahrenheit(), not on the field, so nothing breaks.");
    }
}
