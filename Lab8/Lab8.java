import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lab8 {
    /* ---------- Q5 ---------- */
    static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Q1: ArrayList of 10 ints ===");
        List<Integer> nums = new ArrayList<>(List.of(14, 7, 32, 5, 21, 9, 40, 3, 18, 26));
        int sum = 0;
        for (int n : nums) sum += n;
        System.out.println("Sum = " + sum + ", Max = " + Collections.max(nums));
        List<Integer> reversed = new ArrayList<>(nums);
        Collections.reverse(reversed);
        System.out.println("Original: " + nums);
        System.out.println("Reversed: " + reversed);

        System.out.println("\n=== Q2: Remove duplicate names ===");
        List<String> names = List.of("Tariro", "Kuda", "Tariro", "Neo", "Kuda", "Chido");
        Set<String> unique = new LinkedHashSet<>(names);        // keeps first-seen order
        System.out.println(names + " -> " + unique);

        System.out.println("\n=== Q3: Generic Stack<T> ===");
        Stack<String> s = new Stack<>();
        s.push("a"); s.push("b"); s.push("c");
        System.out.println("peek=" + s.peek() + ", pop=" + s.pop() + ", pop=" + s.pop() + ", isEmpty=" + s.isEmpty());
        s.pop();
        System.out.println("isEmpty after last pop = " + s.isEmpty());
        try { s.pop(); } catch (EmptyStackException e) { System.out.println("pop on empty -> EmptyStackException"); }
        Stack<Integer> si = new Stack<>();   // same class, different type, type-safe
        si.push(42); System.out.println("Stack<Integer> peek = " + si.peek());

        System.out.println("\n=== Q4: Word frequency ===");
        String paragraph = "The cat saw the dog. The dog saw the cat, and the cat ran. A dog is a dog!";
        // (To read from the user instead: String paragraph = new java.util.Scanner(System.in).nextLine();)
        Map<String, Integer> freq = new HashMap<>();
        for (String w : paragraph.toLowerCase().split("[^a-z]+")) {
            if (!w.isEmpty()) freq.merge(w, 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> b.getValue() != a.getValue().intValue()
                ? b.getValue() - a.getValue()                 // frequency descending
                : a.getKey().compareTo(b.getKey()));          // tie: alphabetical
        for (Map.Entry<String, Integer> e : entries) System.out.println(e.getKey() + " : " + e.getValue());

        System.out.println("\n=== Q5: Generic bubbleSort ===");
        Integer[] ints = {5, 2, 9, 1, 7};
        String[] strs = {"pear", "apple", "mango", "banana"};
        bubbleSort(ints); bubbleSort(strs);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(strs));
        /* WHY int[] WILL NOT WORK: T must be a reference type that implements Comparable<T>.
           1. Generic type arguments cannot be primitives, so T can never be int.
           2. int[] is not a T[] for any T: it is an array of primitives, not an Object[] (Integer[] is).
           3. int has no compareTo method.
           bubbleSort(new int[]{...}) is therefore a compile error. Use Integer[] (autoboxing converts
           values) or write a separate non-generic overload bubbleSort(int[]). */
    }
}
