public class Student extends Person {
    private String course;
    Student(String name, int age, String course) { super(name, age); this.course = course; }
    String getCourse() { return course; }
}
