/* DOES NOT COMPILE:
   class Dog extends Animal { }
   error: constructor Animal in class Animal cannot be applied to given types
   WHY: if a subclass writes no constructor, the compiler inserts Dog() { super(); }.
   Animal has no no-argument constructor (writing Animal(String) removed the free default one),
   so super() cannot be resolved. Every subclass constructor MUST call a superclass constructor
   that exists. FIX: */
public class Dog extends Animal {
    Dog(String name) { super(name); }       // explicit call to Animal(String)
}
