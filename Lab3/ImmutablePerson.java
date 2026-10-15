import java.util.List;

/* ---------- Q4: ImmutablePerson ---------- */
public final class ImmutablePerson {                 // final class: no subclass can add mutability
    private final String name;                // final fields: assigned once, in constructor
    private final int age;
    private final List<String> hobbies;       // mutable type -> needs defensive copies

    ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = List.copyOf(hobbies);  // copy IN: caller's later changes don't leak in
    }

    String getName() { return name; }
    int getAge()     { return age; }
    List<String> getHobbies() { return hobbies; }   // already unmodifiable: safe to return

    // "Change" = new object
    ImmutablePerson withAge(int newAge) { return new ImmutablePerson(name, newAge, hobbies); }

    @Override public String toString() { return name + " (" + age + ") " + hobbies; }
}
