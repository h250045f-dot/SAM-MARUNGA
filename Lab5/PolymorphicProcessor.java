// A new type (e.g. BankTransferPay) just implements process(); this class never changes.
public class PolymorphicProcessor {
    void pay(ProcessablePayment p) { p.process(); }
}
