import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

/** Core service: holds items and members in Maps keyed by id, and persists them to CSV. */
public class Library {
    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();

    // ---------- registration ----------
    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId())) throw new IllegalArgumentException("Duplicate item id: " + item.getId());
        items.put(item.getId(), item);
    }

    public void registerMember(Member m) {
        if (members.containsKey(m.getId())) throw new IllegalArgumentException("Duplicate member id: " + m.getId());
        members.put(m.getId(), m);
    }

    private LibraryItem findItem(String id) {
        LibraryItem i = items.get(id);
        if (i == null) throw new IllegalArgumentException("No such item: " + id);
        return i;
    }

    private Member findMember(String id) {
        Member m = members.get(id);
        if (m == null) throw new IllegalArgumentException("No such member: " + id);
        return m;
    }

    // ---------- borrow / return ----------
    public Loan borrow(String memberId, String itemId) throws ItemNotAvailableException, MemberLimitExceededException {
        return borrow(memberId, itemId, LocalDate.now());
    }

    public Loan borrow(String memberId, String itemId, LocalDate on)
            throws ItemNotAvailableException, MemberLimitExceededException {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);
        item.borrow(member);                       // availability + limit checks happen here
        Loan loan = new Loan(item, member, on);
        member.addLoan(loan);
        return loan;
    }

    public void giveBack(String itemId) {
        LibraryItem item = findItem(itemId);
        if (item.isAvailable()) throw new IllegalStateException("Item " + itemId + " is not on loan");
        for (Member m : members.values()) {
            for (Loan l : new ArrayList<>(m.getActiveLoans())) {
                if (l.getItem() == item) m.removeLoan(l);
            }
        }
        item.giveBack();
    }

    // ---------- queries (streams) ----------
    public List<Loan> overdueLoans(LocalDate today) {
        return members.values().stream()
            .flatMap(m -> m.getActiveLoans().stream())
            .filter(l -> l.isOverdue(today))
            .sorted(Comparator.comparing(Loan::getDueOn))
            .toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
            .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
            .sorted(Comparator.comparing(LibraryItem::getTitle))
            .toList();
    }

    public Collection<LibraryItem> allItems()  { return Collections.unmodifiableCollection(items.values()); }
    public Collection<Member> allMembers()     { return Collections.unmodifiableCollection(members.values()); }

    // ---------- CSV persistence ----------
    // items.csv   : BOOK,id,title,author,available | DVD,id,title,runtime,available
    // members.csv : id,name
    // loans.csv   : memberId,itemId,borrowedOn
    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);
        List<String> il = new ArrayList<>();
        for (LibraryItem i : items.values()) {
            String extra = (i instanceof Book b) ? b.getAuthor() : String.valueOf(((Dvd) i).getRuntimeMinutes());
            il.add(String.join(",", i.typeName(), i.getId(), i.getTitle(), extra));
        }
        Files.write(dir.resolve("items.csv"), il);

        List<String> ml = new ArrayList<>();
        members.values().forEach(m -> ml.add(m.getId() + "," + m.getName()));
        Files.write(dir.resolve("members.csv"), ml);

        List<String> ll = new ArrayList<>();
        members.values().stream().flatMap(m -> m.getActiveLoans().stream())
            .forEach(l -> ll.add(l.getMember().getId() + "," + l.getItem().getId() + "," + l.getBorrowedOn()));
        Files.write(dir.resolve("loans.csv"), ll);
    }

    public void load(Path dir) throws IOException {
        items.clear();
        members.clear();
        Path ip = dir.resolve("items.csv"), mp = dir.resolve("members.csv"), lp = dir.resolve("loans.csv");
        if (Files.exists(ip)) {
            for (String line : Files.readAllLines(ip)) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", -1);
                if (p[0].equals("BOOK")) addItem(new Book(p[1], p[2], p[3]));
                else if (p[0].equals("DVD")) addItem(new Dvd(p[1], p[2], Integer.parseInt(p[3])));
            }
        }
        if (Files.exists(mp)) {
            for (String line : Files.readAllLines(mp)) {
                if (line.isBlank()) continue;
                String[] p = line.split(",", 2);
                registerMember(new Member(p[0], p[1]));
            }
        }
        if (Files.exists(lp)) {
            for (String line : Files.readAllLines(lp)) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                try {
                    borrow(p[0], p[1], LocalDate.parse(p[2]));
                } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                    System.out.println("Skipped corrupt loan record: " + e.getMessage());
                }
            }
        }
    }
}
