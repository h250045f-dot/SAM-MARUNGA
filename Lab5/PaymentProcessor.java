public class PaymentProcessor {
    void pay(Payment p) {
        if (p instanceof CardPayment c) {
            System.out.println("  Charging card " + c.cardNo() + " $" + c.amount());
        } else if (p instanceof MobileMoneyPayment m) {
            System.out.println("  Sending mobile money prompt to " + m.phone() + " for $" + m.amount());
        } else {
            throw new IllegalArgumentException("Unknown payment type: " + p);
        }
    }
}
