/* ---------- Q1 & Q2: Student ---------- */
public class Student {
    private String name;
    private int marks;

    Student(String name, int marks) { this.name = name; setMarks(marks); }

    String getName() { return name; }
    int getMarks()   { return marks; }

    void setMarks(int marks) {
        if (marks < 0 || marks > 100)
            throw new IllegalArgumentException("Marks must be between 0 and 100, got " + marks);
        this.marks = marks;
    }

    // Derived on demand: no 'grade' field that could go out of sync with marks.
    char getGrade() {
        if (marks >= 75) return 'A';
        if (marks >= 60) return 'B';
        if (marks >= 50) return 'C';
        return 'F';
    }
}
