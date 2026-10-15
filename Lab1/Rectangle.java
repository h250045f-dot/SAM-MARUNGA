/* ---------- Q3 & Q4: Rectangle ---------- */
public class Rectangle {
    double width, height;          // package-visible so Q4 can show r2.width = ...

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double area()      { return width * height; }
    double perimeter() { return 2 * (width + height); }

    @Override public String toString() {
        return "Rectangle[" + width + " x " + height + ", area=" + area() + ", perimeter=" + perimeter() + "]";
    }
}
