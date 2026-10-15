import java.util.ArrayList;

// Composition version: a Stack HAS-A list, and exposes only stack operations.
public class GoodStack<E> {
    private final ArrayList<E> items = new ArrayList<>();
    void push(E e) { items.add(e); }
    E pop() {
        if (items.isEmpty()) throw new IllegalStateException("stack empty");
        return items.remove(items.size() - 1);
    }
    E peek() { return items.get(items.size() - 1); }
    boolean isEmpty() { return items.isEmpty(); }
    int size() { return items.size(); }
    @Override public String toString() { return items.toString(); }
}
