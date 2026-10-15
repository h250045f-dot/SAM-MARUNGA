import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lab10 {
    /* ---------- Q1 ---------- */
    static void saveAndRead(Scanner in) throws IOException {
        Path file = Path.of("notes.txt");
        List<String> lines = new ArrayList<>();
        System.out.println("Type five lines:");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + "> ");
            lines.add(in.hasNextLine() ? in.nextLine() : "(no input line " + i + ")");
        }
        Files.write(file, lines);
        System.out.println("Saved to " + file.toAbsolutePath() + ". Reading back:");
        for (String l : Files.readAllLines(file)) System.out.println("  " + l);
    }

    /* ---------- Q3 ---------- */
    static void countFile(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        int words = 0, chars = 0;
        for (String l : lines) {
            String t = l.trim();
            if (!t.isEmpty()) words += t.split("\\s+").length;
            chars += l.length();
        }
        chars += lines.size();     // the newline of each line (approximation: assumes one '\n' per line)
        System.out.println(file + ": lines=" + lines.size() + ", words=" + words + ", characters=" + chars);
    }

    /* ---------- Q5 ---------- */
    static String band(int marks) { return marks >= 70 ? "A" : marks >= 50 ? "B" : "C"; }

    static void learnerReports() throws IOException {
        Path csv = Path.of("learners.csv");
        if (!Files.exists(csv)) {                               // make a sample so the lab runs anywhere
            Files.write(csv, List.of("name,marks", "Anesu,88", "Blessing,54", "Chipo,71",
                                     "Dumisani,39", "Elton,65", "Fadzai,92", "Gamuchirai,49"));
        }
        List<Learner> learners = new ArrayList<>();
        for (String line : Files.readAllLines(csv).subList(1, Files.readAllLines(csv).size())) {   // skip header
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            learners.add(new Learner(p[0].trim(), Integer.parseInt(p[1].trim())));
        }
        Map<String, List<Learner>> byBand = learners.stream()
            .collect(Collectors.groupingBy(l -> band(l.marks()), TreeMap::new, Collectors.toList()));

        for (Map.Entry<String, List<Learner>> e : byBand.entrySet()) {
            List<String> report = new ArrayList<>();
            report.add("Band " + e.getKey() + " report (" + e.getValue().size() + " learners)");
            for (Learner l : e.getValue()) report.add(l.name() + " - " + l.marks());
            Path out = Path.of("report_" + e.getKey() + ".txt");
            Files.write(out, report);
            System.out.println("Wrote " + out + ": " + report);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("=== Q1: Save and read five lines ===");
        saveAndRead(new Scanner(System.in));

        System.out.println("\n=== Q2: Predicate + removeIf ===");
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Predicate<Integer> isEven = n -> n % 2 == 0;
        nums.removeIf(isEven);
        System.out.println("After removing evens: " + nums);

        System.out.println("\n=== Q3: Lines, words, characters ===");
        countFile(args.length > 0 ? Path.of(args[0]) : Path.of("notes.txt"));

        System.out.println("\n=== Q4: Above-average students ===");
        List<Student> students = List.of(new Student("Zoe", 78), new Student("Adam", 55),
            new Student("Mary", 91), new Student("Ben", 62), new Student("Carl", 84));
        double avg = students.stream().mapToDouble(Student::marks).average().orElse(0);
        System.out.println("Class average = " + avg);
        students.stream()
            .filter(s -> s.marks() > avg)
            .map(Student::name)
            .sorted()
            .forEach(n -> System.out.println("  " + n));

        System.out.println("\n=== Q5: Reports by grade band ===");
        learnerReports();
    }
}
