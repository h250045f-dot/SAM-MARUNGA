import java.util.Iterator;
import java.util.NoSuchElementException;

/* ---------- Q4 ---------- */
public class LinkedList<T> implements Iterable<T> {
    private static class Node<T> {                   // static nested: needs no outer instance
        T value; Node<T> next;
        Node(T value) { this.value = value; }
    }
    private Node<T> head, tail;
    private int size;

    void add(T value) {
        Node<T> n = new Node<>(value);
        if (head == null) head = tail = n; else { tail.next = n; tail = n; }
        size++;
    }
    int size() { return size; }

    @Override public Iterator<T> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<T> {   // inner class: reads outer 'head'
        private Node<T> current = head;
        @Override public boolean hasNext() { return current != null; }
        @Override public T next() {
            if (current == null) throw new NoSuchElementException();
            T v = current.value;
            current = current.next;
            return v;
        }
    }
}
