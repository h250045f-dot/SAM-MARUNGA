public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Concrete: shared by all account types
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit rejected: amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited %.2f into %s. New balance: %.2f%n", amount, accountNumber, balance);
    }

    public double getBalance() {
        return balance;
    }

    // Abstract: each account type decides its own withdrawal rules
    public abstract void withdraw(double amount);

    // Abstract hook: each account type decides its own month-end processing
    public abstract void endOfMonth();
}
