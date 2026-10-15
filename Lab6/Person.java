public class Person implements Walker, Talker {
    // Without this override the class does NOT compile (inherits unrelated defaults).
    @Override public void hello() {
        Walker.super.hello();
        Talker.super.hello();
        System.out.println("Hello from Person");
    }
}
