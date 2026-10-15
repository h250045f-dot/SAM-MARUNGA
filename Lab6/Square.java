public class Square extends Shape {
    private final double side;
    Square(double side) { this.side = side; }
    double area()      { return side * side; }
    double perimeter() { return 4 * side; }
}
