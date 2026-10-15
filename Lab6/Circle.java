public class Circle extends Shape {
    private final double r;
    Circle(double r) { this.r = r; }
    double area()      { return Math.PI * r * r; }
    double perimeter() { return 2 * Math.PI * r; }
}
