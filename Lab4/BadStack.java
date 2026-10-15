import java.util.ArrayList;

/* ---------- Q5 ---------- */
// Inheritance version: a Stack IS-A ArrayList, so it inherits everything.
public class BadStack<E> extends ArrayList<E> {
    void push(E e) { add(e); }
    E pop() { return remove(size() - 1); }
}
