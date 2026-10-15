public class BankAccount {
    private double balance;
    void deposit(double amt) { balance += amt; }
    void withdraw(double amt) throws InsufficientFundsException {
        if (amt > balance) throw new InsufficientFundsException(amt - balance);
        balance -= amt;
    }
    double getBalance() { return balance; }
}
