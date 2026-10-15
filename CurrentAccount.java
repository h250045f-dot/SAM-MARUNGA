public class CurrentAccount extends Account {
    private final double overdraftLimit;
    private final double monthlyFee;

    public CurrentAccount(String accountNumber, double initialBalance,
                          double overdraftLimit, double monthlyFee) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
        this.monthlyFee = monthlyFee;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal rejected: amount must be positive.");
            return;
        }
        if (balance - amount < -overdraftLimit) {
            System.out.printf("[Current %s] Withdrawal of %.2f rejected: exceeds overdraft limit of %.2f.%n",
                    accountNumber, amount, overdraftLimit);
            return;
        }
        balance -= amount;
        if (balance < 0) {
            System.out.printf("[Current %s] Withdrew %.2f. Account is overdrawn! Balance: %.2f%n",
                    accountNumber, amount, balance);
        } else {
            System.out.printf("[Current %s] Withdrew %.2f. New balance: %.2f%n", accountNumber, amount, balance);
        }
    }

    @Override
    public void endOfMonth() {
        balance -= monthlyFee;
        System.out.printf("[Current %s] Maintenance fee of %.2f deducted. Balance: %.2f%n",
                accountNumber, monthlyFee, balance);
    }
}
