public class SavingsAccount extends Account {
    double monthlyFee() { return balance >= 500 ? 0 : 2; }   // waived for high balances
}
