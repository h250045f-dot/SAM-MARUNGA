/* ---------- Q4 ---------- */
public class InsufficientFundsException extends Exception {
    private final double shortfall;
    InsufficientFundsException(double shortfall) {
        super("Insufficient funds, short by " + shortfall);
        this.shortfall = shortfall;
    }
    double getShortfall() { return shortfall; }
}
