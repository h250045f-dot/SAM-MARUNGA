import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 1000.00, 200.00, 0.03));
        accounts.add(new CurrentAccount("CUR-001", 500.00, 300.00, 15.00));
        accounts.add(new SavingsAccount("SAV-002", 250.00, 100.00, 0.02));
        accounts.add(new CurrentAccount("CUR-002", 100.00, 200.00, 10.00));

        System.out.println("=== Normal withdrawals (polymorphic loop) ===");
        for (Account acc : accounts) {
            acc.withdraw(100.00);   // correct overridden version runs for each object
        }

        System.out.println("\n===  Edge Case 1: SavingsAccount withdrawal rejected ===");
        Account savings = accounts.get(0);       // SAV-001, balance 900, minimum 200
        savings.withdraw(800.00);                // would leave 100 < 200 -> rejected

        System.out.println("\n=== Edge Case 2: CurrentAccount overdraft within limit ===");
        Account current = accounts.get(1);       // CUR-001, balance 400, limit 300
        current.withdraw(600.00);                // balance -200, within 300 limit
        current.withdraw(200.00);                // would be -400 -> rejected (bonus)

        System.out.println("\n=== Invalid deposit ===");
        savings.deposit(-50.00);

        System.out.println("\n=== End of month processing (polymorphic loop) ===");
        for (Account acc : accounts) {
            acc.endOfMonth();
        }

        System.out.println("\n=== Final balances ===");
        for (Account acc : accounts) {
            System.out.printf("%s: %.2f%n", acc.accountNumber, acc.getBalance());
        }
    }
}
