public class Lab4 {
    public static void main(String[] args) {
        System.out.println("=== Q1: Person and Student ===");
        Person p = new Person("Alice", 40);
        Student s = new Student("Brian", 19, "Computer Science");
        p.display();
        s.display();                                  // inherited display()
        System.out.println("   course: " + s.getCourse());

        System.out.println("\n=== Q2: Teacher shares Person code ===");
        Teacher t = new Teacher("Mrs Moyo", 45, "Maths");
        Person[] all = { s, t };
        for (Person x : all) x.display();             // same inherited method, two subclasses
        System.out.println("   teaches: " + t.getSubject());

        System.out.println("\n=== Q3: Constructor order ===");
        new ElectricCar();                            // superclass constructors run first

        System.out.println("\n=== Q4: Dog extends Animal ===");
        Dog d = new Dog("Rex");
        System.out.println("Dog compiled only because it calls super(name). Name = " + d.name);

        System.out.println("\n=== Q5: Stack by inheritance vs composition ===");
        BadStack<Integer> bad = new BadStack<>();
        bad.push(1); bad.push(2); bad.push(3);
        bad.add(0, 99);                               // inserts at the BOTTOM
        bad.remove(1);                                // removes from the MIDDLE
        System.out.println("BadStack after illegal operations: " + bad + "  (LIFO guarantee destroyed)");
        GoodStack<Integer> good = new GoodStack<>();
        good.push(1); good.push(2); good.push(3);
        System.out.println("GoodStack: " + good + ", pop -> " + good.pop() + ", peek -> " + good.peek());
        /* WHAT INHERITANCE WRONGLY EXPOSES: add(int,E), remove(int), get(int), set, clear,
           sort, subList, iterator removal... all of which let callers break the
           last-in-first-out rule. A Stack is not "an ArrayList with extras"; it merely
           USES a list. Inheritance also locks the design to ArrayList forever. Composition
           exposes only push/pop/peek and lets us swap the storage later. (java.util.Stack
           made exactly this mistake by extending Vector.) */
    }
}
