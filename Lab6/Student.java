/* ---------- Q4 ---------- */
public class Student implements Comparable<Student> {
    final String name;
    final int marks;
    Student(String name, int marks) { this.name = name; this.marks = marks; }
    @Override public int compareTo(Student o) { return name.compareTo(o.name); }   // natural order: name
    @Override public String toString() { return name + "(" + marks + ")"; }
}
