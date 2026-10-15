/* ---------- Q1 & Q2 ---------- */
public class Person {
    protected String name;
    protected int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    void display() { System.out.println(getClass().getSimpleName() + ": " + name + ", age " + age); }
}
