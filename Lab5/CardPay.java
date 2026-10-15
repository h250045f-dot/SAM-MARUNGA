public record CardPay(String cardNo, double amount) implements ProcessablePayment {
    public void process() { System.out.println("  Charging card " + cardNo + " $" + amount); }
}
