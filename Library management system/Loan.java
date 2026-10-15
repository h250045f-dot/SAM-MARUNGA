import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private final LibraryItem item;
    private final Member member;
    private final LocalDate borrowedOn;
    private final LocalDate dueOn;

    public Loan(LibraryItem item, Member member, LocalDate borrowedOn) {
        this.item = item;
        this.member = member;
        this.borrowedOn = borrowedOn;
        this.dueOn = borrowedOn.plusDays(item.loanPeriodDays());   // polymorphic call
    }

    public LibraryItem getItem()    { return item; }
    public Member getMember()       { return member; }
    public LocalDate getBorrowedOn(){ return borrowedOn; }
    public LocalDate getDueOn()     { return dueOn; }

    public boolean isOverdue(LocalDate today) { return today.isAfter(dueOn); }
    public long daysOverdue(LocalDate today)  { return Math.max(0, ChronoUnit.DAYS.between(dueOn, today)); }

    @Override
    public String toString() {
        return item.getId() + " (" + item.getTitle() + ") -> " + member.getName() + ", due " + dueOn;
    }
}
