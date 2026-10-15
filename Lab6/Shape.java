/* ---------- Q1 ---------- */
public abstract class Shape {
    abstract double area();
    abstract double perimeter();
    @Override public String toString() {
        return String.format("%-9s area=%.2f perimeter=%.2f", getClass().getSimpleName(), area(), perimeter());
    }
}
