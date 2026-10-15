import java.util.HashSet;
import java.util.Set;

public class Lab9 {
    static void advice(Level level) {
        switch (level) {
            case LOW    -> System.out.println("LOW: relax, nothing to do.");
            case MEDIUM -> System.out.println("MEDIUM: keep an eye on it.");
            case HIGH   -> System.out.println("HIGH: act immediately!");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Q1: enum + switch ===");
        for (Level l : Level.values()) advice(l);

        System.out.println("\n=== Q2: record ===");
        Student a = new Student("Ruvimbo", 20), b = new Student("Ruvimbo", 20), c = new Student("Sam", 21);
        System.out.println(a);                                        // auto toString
        System.out.println("a.equals(b) = " + a.equals(b) + ", a == b = " + (a == b) + ", a.equals(c) = " + a.equals(c));
        System.out.println("component: a.name() = " + a.name() + ", a.age() = " + a.age());

        System.out.println("\n=== Q3: Book equals/hashCode in a HashSet ===");
        Set<Book> books = new HashSet<>();
        System.out.println("add first   : " + books.add(new Book("978-0-13", "Effective Java")));
        System.out.println("add same ISBN, different object: " + books.add(new Book("978-0-13", "Effective Java (2nd copy)")));
        System.out.println("add new ISBN: " + books.add(new Book("978-1-49", "Head First Java")));
        System.out.println("Set size = " + books.size() + " -> " + books);
        Set<PlainBook> plain = new HashSet<>();
        plain.add(new PlainBook("1")); plain.add(new PlainBook("1"));
        System.out.println("Without equals/hashCode the same ISBN gives size = " + plain.size());

        System.out.println("\n=== Q4: Custom LinkedList in for-each ===");
        LinkedList<String> list = new LinkedList<>();
        list.add("alpha"); list.add("beta"); list.add("gamma");
        for (String s : list) System.out.println("  " + s);
        System.out.println("size = " + list.size());

        System.out.println("\n=== Q5: Fewest coins ===");
        for (int amt : new int[]{67, 41, 99, 5, 0}) System.out.println(amt + " cents -> " + Coin.fewestCoins(amt));
        /* The greedy method (take the biggest coin first) is optimal for this coin system. It is NOT
           optimal for every system, e.g. coins {1,3,4} and amount 6: greedy gives 4+1+1 (3 coins) but
           3+3 uses only 2. Then dynamic programming is needed. */
    }
}
