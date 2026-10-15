public class Rectangle extends Shape {
    private final double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    double area()      { return w * h; }
    double perimeter() { return 2 * (w + h); }
}
