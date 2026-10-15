import java.nio.file.*;
import java.time.LocalDate;

/** Plain-Java test runner (no JUnit needed). Run: java LibraryTest */
public class LibraryTest {
    static int passed = 0, failed = 0;

    static void check(String name, boolean ok) {
        System.out.println((ok ? "PASS  " : "FAIL  ") + name);
        if (ok) passed++; else failed++;
    }

    public static void main(String[] args) throws Exception {
        Library lib = new Library();
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Java Concurrency", "Brian Goetz"));
        lib.addItem(new Book("B4", "Refactoring", "Martin Fowler"));
        lib.addItem(new Dvd("D1", "The Pragmatic Programmer Talk", 90));
        lib.registerMember(new Member("M1", "Tendai"));
        lib.registerMember(new Member("M2", "Rudo"));

        // polymorphism
        check("Book loan period is 21", lib.searchByTitle("clean").get(0).loanPeriodDays() == 21);
        check("Dvd loan period is 7", lib.searchByTitle("pragmatic").get(0).loanPeriodDays() == 7);

        // borrow success
        Loan l = lib.borrow("M1", "B1");
        check("Borrow marks item unavailable", !lib.searchByTitle("clean").get(0).isAvailable());
        check("Due date = today + 21", l.getDueOn().equals(LocalDate.now().plusDays(21)));

        // item not available
        try { lib.borrow("M2", "B1"); check("Double borrow rejected", false); }
        catch (ItemNotAvailableException e) { check("Double borrow rejected", true); }

        // member limit
        lib.borrow("M1", "B2");
        lib.borrow("M1", "B3");
        try { lib.borrow("M1", "B4"); check("4th loan rejected", false); }
        catch (MemberLimitExceededException e) { check("4th loan rejected (limit 3)", e.getLimit() == 3); }
        check("Rejected loan leaves item available", lib.searchByTitle("refactoring").get(0).isAvailable());

        // return
        lib.giveBack("B3");
        check("Return frees item", lib.searchByTitle("concurrency").get(0).isAvailable());
        check("Return frees member slot", lib.borrow("M1", "B4") != null);

        // validation
        try { new Member("", "x"); check("Blank member id rejected", false); }
        catch (IllegalArgumentException e) { check("Blank member id rejected", true); }
        try { lib.borrow("NOPE", "B1"); check("Unknown member rejected", false); }
        catch (IllegalArgumentException e) { check("Unknown member rejected", true); }

        // overdue (stream)
        lib.borrow("M2", "D1", LocalDate.now().minusDays(10));
        check("Dvd borrowed 10 days ago is overdue", lib.overdueLoans(LocalDate.now()).size() == 1);
        check("Overdue loan is the DVD", lib.overdueLoans(LocalDate.now()).get(0).getItem().getId().equals("D1"));

        // search
        check("Search is case-insensitive", lib.searchByTitle("EFFECTIVE").size() == 1);

        // persistence round trip
        Path dir = Files.createTempDirectory("libtest");
        lib.save(dir);
        Library lib2 = new Library();
        lib2.load(dir);
        check("Reload keeps item count", lib2.allItems().size() == 5);
        check("Reload keeps member count", lib2.allMembers().size() == 2);
        check("Reload restores loans", lib2.overdueLoans(LocalDate.now()).size() == 1);
        check("Reload restores availability", !lib2.searchByTitle("effective").get(0).isAvailable());

        System.out.println("\n" + passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }
}
