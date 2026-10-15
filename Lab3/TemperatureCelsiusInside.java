// Same public behaviour, DIFFERENT internal unit (Celsius).
public class TemperatureCelsiusInside implements Reading {
    private final double celsius;
    TemperatureCelsiusInside(double c) {
        if (c < -273.15) throw new IllegalArgumentException("Below absolute zero");
        this.celsius = c;
    }
    public double getCelsius()    { return celsius; }
    public double getFahrenheit() { return celsius * 9 / 5 + 32; }
}
