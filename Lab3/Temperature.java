public class Temperature implements Reading {
    private final double kelvin;              // INTERNAL representation

    Temperature(double kelvin) {
        if (kelvin < 0) throw new IllegalArgumentException("Below absolute zero: " + kelvin + " K");
        this.kelvin = kelvin;
    }
    static Temperature ofCelsius(double c) { return new Temperature(c + 273.15); }

    public double getCelsius()    { return kelvin - 273.15; }
    public double getFahrenheit() { return getCelsius() * 9 / 5 + 32; }
}
