/* ---------- Q3 ---------- */
public abstract class Account {
    protected double balance;
    void deposit(double amt) {                       // concrete: shared by all accounts
        if (amt <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amt;
    }
    abstract double monthlyFee();                    // each type decides
    void applyMonthEnd() { balance -= monthlyFee(); }
    double getBalance()  { return balance; }
}
