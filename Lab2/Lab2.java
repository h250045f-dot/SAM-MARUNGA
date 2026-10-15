public class Lab2 {
    public static void main(String[] args) {
        System.out.println("=== Q1 & Q2: Circle ===");
        System.out.println("Circles so far: " + Circle.getCount());
        Circle a = new Circle(2.5), b = new Circle();
        System.out.println(a + " and " + b);
        System.out.println("Circles now: " + Circle.getCount());

        System.out.println("\n=== Q3: Employee ===");
        Employee e1 = new Employee("Chipo");
        Employee e2 = new Employee("Farai", 1200);
        Employee e3 = new Employee("Tafara", 2500, "IT");
        Employee e4 = new Employee(e3);
        System.out.println(e1); System.out.println(e2); System.out.println(e3); System.out.println(e4);

        System.out.println("\n=== Q4: Init blocks ===");
        /* PREDICTION:
           "main reached" is printed first. The class is not loaded until first use.
           Then static block 1, static block 2 (once only, in textual order).
           Then for EVERY object: instance block 1, instance block 2, constructor body.
           Instance blocks run before the constructor body, in textual order, even though
           the constructor is written between them. */
        System.out.println("main reached");
        System.out.println("-- first object");
        new InitDemo();
        System.out.println("-- second object");
        new InitDemo();

        System.out.println("\n=== Q5: Static factories ===");
        System.out.println(Temperature.fromCelsius(100));
        System.out.println(Temperature.fromFahrenheit(98.6));
        /* WHY FACTORIES ARE CLEARER:
           1. They have names. new Temperature(25.0) does not say whether 25 is Celsius or
              Fahrenheit, and two constructors taking a double cannot even coexist
              (same signature). fromCelsius(25) / fromFahrenheit(77) are self-documenting.
           2. They can validate, cache or return a subclass; a constructor must always create
              a new object of exactly that class.
           3. The private constructor means callers cannot bypass the named entry points. */
    }
}
