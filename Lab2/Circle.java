/* ---------- Q1 & Q2: Circle ---------- */
public class Circle {
    private static int count = 0;          // shared by ALL circles
    private double radius;

    Circle()              { this(1.0); }   // delegates to the other constructor
    Circle(double radius) { this.radius = radius; count++; }

    static int getCount() { return count; }
    double area()         { return Math.PI * radius * radius; }
    @Override public String toString() { return "Circle(r=" + radius + ")"; }
}
