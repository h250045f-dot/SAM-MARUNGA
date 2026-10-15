import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab7 {
    /* ---------- Q1 ---------- */
    static void divide(Scanner in) {
        try {
            System.out.print("Enter two integers: ");
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + " / " + b + " = " + (a / b));
        } catch (InputMismatchException e) {
            System.out.println("That was not a whole number.");
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        }
    }

    /* ---------- Q2 ---------- */
    static int safeGet(int[] arr, int index) {
        try {
            return arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index " + index + " is invalid for length " + arr.length);
            return -1;
        }
    }

    /* ---------- Q3 ---------- */
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) throw new InvalidAgeException("Age out of range 0-120: " + age);
    }

    /* ---------- Q5 ---------- */
    static String tryFinally() {
        try {
            System.out.println("  in try, about to return");
            return "returned from try";
        } finally {
            System.out.println("  in finally (still runs)");
        }
    }
    static int trap() {
        int x = 1;
        try {
            return x;            // value 1 is saved for return BEFORE finally runs
        } finally {
            x = 2;               // changes the variable, not the saved return value
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("=== Q1: Safe division (run it three times: 10 2 / abc / 5 0) ===");
        divide(in);

        System.out.println("\n=== Q2: Array index ===");
        int[] data = {10, 20, 30};
        System.out.println("data[1] = " + safeGet(data, 1));
        System.out.println("data[5] = " + safeGet(data, 5));

        System.out.println("\n=== Q3: Checked InvalidAgeException ===");
        for (int age : new int[]{25, -3, 130}) {
            try { validateAge(age); System.out.println(age + " is valid"); }
            catch (InvalidAgeException e) { System.out.println("Rejected: " + e.getMessage()); }
        }

        System.out.println("\n=== Q4: InsufficientFundsException ===");
        BankAccount acc = new BankAccount();
        acc.deposit(100);
        try { acc.withdraw(30);  System.out.println("Withdrew 30, balance " + acc.getBalance()); acc.withdraw(500); }
        catch (InsufficientFundsException e) { System.out.println(e.getMessage() + " (shortfall " + e.getShortfall() + ")"); }
        /* TRADE-OFFS
           Boolean return: simple and cheap, but callers can silently ignore false and carry on as if the
             withdrawal worked, and false cannot say WHY it failed.
           Checked exception: the compiler forces callers to handle failure, and the exception can carry
             details (shortfall); the normal path stays clean of if-checks. Costs: more boilerplate,
             exception creation is slower, and misuse for ordinary control flow hides real errors.
           Use exceptions for exceptional, must-handle situations; a boolean/Optional is fine for
             expected, frequent outcomes. */

        System.out.println("\n=== Q5: return inside try + finally ===");
        /* PREDICTION: prints "in try, about to return", then "in finally (still runs)", then
           the returned text. finally ALWAYS runs when the try block is exited, including via
           return, so cleanup (closing files, releasing locks) is guaranteed. The only ways it is
           skipped: System.exit(), a JVM crash, or an infinite loop/thread death before it. */
        System.out.println(tryFinally());
        System.out.println("trap() returns " + trap() + "  (not 2: the return value was captured before finally)");
    }
}
