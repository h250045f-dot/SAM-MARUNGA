import java.util.ArrayList;
import java.util.EmptyStackException;

/* ---------- Q3 ---------- */
public class Stack<T> {
    private final ArrayList<T> items = new ArrayList<>();
    void push(T x)    { items.add(x); }
    T pop()           { if (isEmpty()) throw new EmptyStackException(); return items.remove(items.size() - 1); }
    T peek()          { if (isEmpty()) throw new EmptyStackException(); return items.get(items.size() - 1); }
    boolean isEmpty() { return items.isEmpty(); }
}
