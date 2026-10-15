/* ---------- Q5: Temperature with static factories ---------- */
public class Temperature {
    private final double celsius;
    private Temperature(double celsius) { this.celsius = celsius; }

    static Temperature fromCelsius(double c)    { return new Temperature(c); }
    static Temperature fromFahrenheit(double f) { return new Temperature((f - 32) * 5 / 9); }

    double getCelsius()    { return celsius; }
    double getFahrenheit() { return celsius * 9 / 5 + 32; }
    @Override public String toString() { return String.format("%.1fC / %.1fF", getCelsius(), getFahrenheit()); }
}
