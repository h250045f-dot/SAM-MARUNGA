public class Lab1 {
    public static void main(String[] args) {
        System.out.println("=== Q1 & Q2: Cars ===");
        Car[] cars = {
            new Car("Toyota", 2018, 64000),
            new Car("Mazda", 1995, 210000),
            new Car("Ford", 1968, 95000)
        };
        for (Car c : cars) {
            c.display();
            System.out.println("   antique? " + c.isAntique());
        }

        System.out.println("\n=== Q3: Largest rectangle ===");
        Rectangle[] rs = {
            new Rectangle(2, 3), new Rectangle(5, 4), new Rectangle(10, 1),
            new Rectangle(6, 6), new Rectangle(3, 7)
        };
        Rectangle largest = rs[0];
        for (Rectangle r : rs) if (r.area() > largest.area()) largest = r;
        System.out.println("Largest: " + largest);

        System.out.println("\n=== Q4: Reference assignment ===");
        /* PREDICTION: prints 99.0. `Rectangle r2 = r1;` copies the REFERENCE, not the object.
           r1 and r2 are two variables pointing at the SAME object on the heap, so a change
           made through r2 is visible through r1. To get an independent copy you must create
           a new object: new Rectangle(r1.width, r1.height). */
        Rectangle r1 = new Rectangle(4, 5);
        Rectangle r2 = r1;
        r2.width = 99;
        System.out.println("r1.width = " + r1.width + "   (same object as r2: " + (r1 == r2) + ")");
        Rectangle r3 = new Rectangle(r1.width, r1.height);   // true copy
        r3.width = 1;
        System.out.println("After editing a real copy r3, r1.width is still " + r1.width);

        System.out.println("\n=== Q5: Chained dot expression and null ===");
        Person withAddress = new Person("Tendai", new Address("Harare"));
        System.out.println(withAddress.name + " lives in " + withAddress.address.city);

        Person noAddress = new Person("Rudo", null);
        try {
            System.out.println(noAddress.address.city);        // address is null -> NPE
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        }
        // Safe version: check each link in the chain before using it.
        String city = (noAddress.address != null) ? noAddress.address.city : "unknown";
        System.out.println("Safe access gives: " + city);
    }
}
