import java.util.Arrays;

public class Lab5 {
    /* ---------- Q1: overloading ---------- */
    static void print(int x)      { System.out.println("int: " + x); }
    static void print(double x)   { System.out.println("double: " + x); }
    static void print(String x)   { System.out.println("String: " + x); }
    static void print(int[] x)    { System.out.println("int[]: " + Arrays.toString(x)); }

    public static void main(String[] args) {
        System.out.println("=== Q1: Overloading ===");
        print(5); print(3.14); print("hello"); print(new int[]{1, 2, 3});

        System.out.println("\n=== Q2: Animals ===");
        Animal[] zoo = { new Cat(), new Cow(), new Duck() };
        for (Animal a : zoo) System.out.println(a.getClass().getSimpleName() + " says " + a.sound());

        System.out.println("\n=== Q3: Payroll ===");
        Employee[] staff = {
            new Manager("Grace", 3000, 500),
            new Developer("Hannah", 2200),
            new Developer("Ian", 2100),
            new Intern("Joy", 400)
        };
        double total = 0;
        for (Employee e : staff) total += e.monthlyPay();      // this loop never changes
        System.out.println("Total payroll: $" + total);

        System.out.println("\n=== Q4: Field vs method through superclass reference ===");
        A ref = new B();
        System.out.println("ref.who()  -> " + ref.who());
        System.out.println("ref.name   -> " + ref.name);
        System.out.println("((B) ref).name -> " + ((B) ref).name);
        /* Methods are chosen at RUN TIME from the real object (B) = dynamic dispatch -> "method of B".
           Fields are chosen at COMPILE TIME from the declared type of the reference (A) -> "field of A".
           Fields are never overridden; B's field only hides A's. Lesson: don't redeclare fields. */

        System.out.println("\n=== Q5: PaymentProcessor ===");
        System.out.println("Version 1 (instanceof):");
        PaymentProcessor v1 = new PaymentProcessor();
        v1.pay(new CardPayment("4111-1111", 50));
        v1.pay(new MobileMoneyPayment("0771234567", 20));
        System.out.println("Version 2 (polymorphic process()):");
        PolymorphicProcessor v2 = new PolymorphicProcessor();
        v2.pay(new CardPay("4111-1111", 50));
        v2.pay(new MobilePay("0771234567", 20));
        /* COMPARISON
           instanceof design: all payment logic in one place and the payment classes stay plain data,
             but every new payment type means editing pay() (easy to forget a branch; the compiler
             will not warn you) -> violates the open/closed principle.
           polymorphic design: each type owns its behaviour; adding BankTransferPay needs no change to
             the processor, and a missing process() is a compile error. Downside: payment classes now
             contain behaviour (and maybe dependencies), and logic is spread over several classes.
           Use instanceof when you can't modify the types (e.g. library classes) or for a closed set
           (sealed types); prefer polymorphism when you control the hierarchy and expect growth. */
    }
}
