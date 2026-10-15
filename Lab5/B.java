public class B extends A {
    String name = "field of B";                       // HIDES, does not override
    @Override String who() { return "method of B"; }  // OVERRIDES
}
