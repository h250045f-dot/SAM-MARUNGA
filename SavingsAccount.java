public class SavingsAccount extends Account {
    private final double minimumBalance;
    private final double interestRate; 

    public SavingsAccount(String accountNumber, double initialBalance,
                          double minimumBalance, double interestRate) {
        super(accountNumber, initialBalance);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal rejected: amount must be positive.");
            return;
        }
        if (balance - amount < minimumBalance) {
            System.out.printf("[Savings %s] Withdrawal of %.2f rejected: balance cannot fall below the minimum of %.2f.%n",
                    accountNumber, amount, minimumBalance);
            return;
        }
        balance -= amount;
        System.out.printf("[Savings %s] Withdrew %.2f. New balance: %.2f%n", accountNumber, amount, balance);
    }

    @Override
    public void endOfMonth() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.printf("[Savings %s] Interest of %.2f applied. Balance: %.2f%n", accountNumber, interest, balance);
    }
}
