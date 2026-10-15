import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* ---------- Q3: BankAccount with history ---------- */
public class BankAccount {
    private double balance;
    private final List<String> history = new ArrayList<>();

    void deposit(double amt) {
        if (amt <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amt;
        history.add("DEPOSIT " + amt + " -> balance " + balance);
    }

    boolean withdraw(double amt) {
        if (amt <= 0 || amt > balance) {
            history.add("FAILED WITHDRAW " + amt);
            return false;
        }
        balance -= amt;
        history.add("WITHDRAW " + amt + " -> balance " + balance);
        return true;
    }

    double getBalance() { return balance; }

    // Snapshot copy, wrapped read-only: callers can neither change our list
    // nor see later changes sneak into a list they already hold.
    List<String> getHistory() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }
}
