import java.time.Year;

/* ---------- Q1 & Q2: Car ---------- */
public class Car {
    private String brand;
    private int year;
    private int mileage;

    Car(String brand, int year, int mileage) {
        this.brand = brand;
        this.year = year;
        this.mileage = mileage;
    }

    void display() {
        System.out.println("Car{brand=" + brand + ", year=" + year + ", mileage=" + mileage + " km}");
    }

    // Q2: antique when MORE than 25 years old
    boolean isAntique() {
        return Year.now().getValue() - year > 25;
    }
}
