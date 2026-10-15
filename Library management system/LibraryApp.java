import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private static final Path DATA_DIR = Path.of("library-data");

    public static void main(String[] args) {
        Library lib = new Library();
        try {
            lib.load(DATA_DIR);
            System.out.println("Loaded " + lib.allItems().size() + " items and " + lib.allMembers().size() + " members.");
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not load data (" + e.getMessage() + "); starting empty.");
        }

        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = in.hasNextLine() ? in.nextLine().trim() : "0";
            try {
                switch (choice) {
                    case "1" -> addItem(lib, in);
                    case "2" -> registerMember(lib, in);
                    case "3" -> borrow(lib, in);
                    case "4" -> giveBack(lib, in);
                    case "5" -> listOverdue(lib);
                    case "6" -> search(lib, in);
                    case "7" -> lib.allItems().forEach(System.out::println);
                    case "8" -> lib.allMembers().forEach(System.out::println);
                    case "0" -> running = false;
                    default -> System.out.println("Unknown option.");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Cannot complete: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Invalid: " + e.getMessage());
            }
        }

        try {
            lib.save(DATA_DIR);
            System.out.println("Data saved to " + DATA_DIR + ". Goodbye.");
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Library Menu ===");
        System.out.println("1 Add item   2 Register member   3 Borrow   4 Return");
        System.out.println("5 Overdue    6 Search title      7 List items   8 List members   0 Exit");
        System.out.print("> ");
    }

    private static String ask(Scanner in, String prompt) {
        System.out.print(prompt + ": ");
        return in.nextLine().trim();
    }

    private static void addItem(Library lib, Scanner in) {
        String type = ask(in, "Type (book/dvd)").toLowerCase();
        String id = ask(in, "Item id");
        String title = ask(in, "Title");
        switch (type) {
            case "book" -> lib.addItem(new Book(id, title, ask(in, "Author")));
            case "dvd" -> {
                try {
                    lib.addItem(new Dvd(id, title, Integer.parseInt(ask(in, "Runtime (minutes)"))));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Runtime must be a whole number");
                }
            }
            default -> throw new IllegalArgumentException("Type must be book or dvd");
        }
        System.out.println("Item added.");
    }

    private static void registerMember(Library lib, Scanner in) {
        lib.registerMember(new Member(ask(in, "Member id"), ask(in, "Name")));
        System.out.println("Member registered.");
    }

    private static void borrow(Library lib, Scanner in)
            throws ItemNotAvailableException, MemberLimitExceededException {
        Loan l = lib.borrow(ask(in, "Member id"), ask(in, "Item id"));
        System.out.println("Loan created: " + l);
    }

    private static void giveBack(Library lib, Scanner in) {
        lib.giveBack(ask(in, "Item id"));
        System.out.println("Item returned.");
    }

    private static void listOverdue(Library lib) {
        LocalDate today = LocalDate.now();
        List<Loan> overdue = lib.overdueLoans(today);
        if (overdue.isEmpty()) System.out.println("No overdue items.");
        overdue.forEach(l -> System.out.println(l + "  [" + l.daysOverdue(today) + " days overdue]"));
    }

    private static void search(Library lib, Scanner in) {
        List<LibraryItem> hits = lib.searchByTitle(ask(in, "Search text"));
        if (hits.isEmpty()) System.out.println("No matches.");
        hits.forEach(System.out::println);
    }
}
