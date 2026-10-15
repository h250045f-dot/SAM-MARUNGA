import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    public static final int MAX_LOANS = 3;

    private final String id;
    private String name;
    private final List<Loan> activeLoans = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Member id required");
        this.id = id.trim();
        setName(name);
    }

    public String getId()   { return id; }
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Member name required");
        this.name = name.trim();
    }

    /** Defensive copy: callers cannot modify the real list. */
    public List<Loan> getActiveLoans() { return Collections.unmodifiableList(activeLoans); }

    void checkCanBorrow() throws MemberLimitExceededException {
        if (activeLoans.size() >= MAX_LOANS) {
            throw new MemberLimitExceededException(
                name + " already has " + MAX_LOANS + " loans (the maximum)", MAX_LOANS);
        }
    }

    void addLoan(Loan loan)    { activeLoans.add(loan); }
    void removeLoan(Loan loan) { activeLoans.remove(loan); }

    @Override
    public String toString() {
        return String.format("%-6s %-20s loans: %d/%d", id, name, activeLoans.size(), MAX_LOANS);
    }
}
