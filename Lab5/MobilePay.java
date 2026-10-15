public record MobilePay(String phone, double amount) implements ProcessablePayment {
    public void process() { System.out.println("  Sending mobile money prompt to " + phone + " for $" + amount); }
}
