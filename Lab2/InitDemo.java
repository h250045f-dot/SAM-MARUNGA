/* ---------- Q4: Initialisation blocks ---------- */
public class InitDemo {
    static { System.out.println("static block 1"); }
    { System.out.println("  instance block 1"); }
    InitDemo() { System.out.println("  constructor"); }
    static { System.out.println("static block 2"); }
    { System.out.println("  instance block 2"); }
}
