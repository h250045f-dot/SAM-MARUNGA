import java.util.Objects;

/* ---------- Q3 ---------- */
public class Book {
    private final String isbn, title;
    Book(String isbn, String title) { this.isbn = isbn; this.title = title; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book b)) return false;
        return isbn.equals(b.isbn);                 // identity of a book = its ISBN
    }
    @Override public int hashCode() { return Objects.hash(isbn); }   // consistent with equals
    @Override public String toString() { return title + " [" + isbn + "]"; }
}
