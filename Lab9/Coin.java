import java.util.LinkedHashMap;
import java.util.Map;

/* ---------- Q5 ---------- */
public enum Coin {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
    private final int cents;
    Coin(int cents) { this.cents = cents; }
    int getCents() { return cents; }

    static Map<Coin, Integer> fewestCoins(int amount) {
        if (amount < 0) throw new IllegalArgumentException("negative amount");
        Map<Coin, Integer> result = new LinkedHashMap<>();
        Coin[] all = values();
        for (int i = all.length - 1; i >= 0; i--) {          // largest coin first
            int n = amount / all[i].cents;
            if (n > 0) { result.put(all[i], n); amount %= all[i].cents; }
        }
        return result;
    }
}
