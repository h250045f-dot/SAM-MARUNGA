/** Abstract base for anything the library lends. Implements Borrowable. */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;
    private Member currentBorrower;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Item id required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title required");
        this.id = id.trim();
        this.title = title.trim();
    }

    public abstract int loanPeriodDays();   // polymorphic: differs per subclass
    public abstract String typeName();      // "BOOK" / "DVD", used for CSV and display

    public String getId()          { return id; }
    public String getTitle()       { return title; }
    public boolean isAvailable()   { return available; }
    void setAvailable(boolean a)   { this.available = a; }

    @Override
    public void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException {
        if (!available) throw new ItemNotAvailableException(typeName() + " " + id + " (" + title + ") is already on loan");
        member.checkCanBorrow();
        available = false;
        currentBorrower = member;
    }

    @Override
    public void giveBack() {
        available = true;
        currentBorrower = null;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-6s %-30s %s", typeName(), id, title, available ? "available" : "on loan");
    }
}
