import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lab6 {
    public static void main(String[] args) {
        System.out.println("=== Q1: Shapes ===");
        List<Shape> shapes = List.of(new Circle(2), new Rectangle(3, 4), new Square(5));
        shapes.forEach(System.out::println);

        System.out.println("\n=== Q2: Flyable ===");
        Flyable[] things = { new Bird(), new Aeroplane() };
        for (Flyable f : things) f.fly();

        System.out.println("\n=== Q3: Accounts ===");
        Account[] accs = { new SavingsAccount(), new CurrentAccount() };
        for (Account a : accs) {
            a.deposit(300);
            a.applyMonthEnd();
            System.out.println(a.getClass().getSimpleName() + ": fee=" + a.monthlyFee() + " balance=" + a.getBalance());
        }

        System.out.println("\n=== Q4: Comparable and Comparator ===");
        List<Student> list = new ArrayList<>(List.of(
            new Student("Zodwa", 65), new Student("Anesu", 88), new Student("Memory", 72)));
        Collections.sort(list);
        System.out.println("By name:  " + list);
        list.sort(Comparator.comparingInt((Student s) -> s.marks).reversed());   // highest marks first
        System.out.println("By marks: " + list);

        System.out.println("\n=== Q5: Diamond default methods ===");
        new Person().hello();
        /* RULE: if a class inherits two default methods with the same signature from
           unrelated interfaces, the compiler cannot choose, so the class MUST override the
           method. Inside it you may pick one with InterfaceName.super.method(). (Other
           rules: a class's own/superclass method always beats an interface default; and a
           more specific sub-interface default beats the one it extends.) */
    }
}
