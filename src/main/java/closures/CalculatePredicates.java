package closures;

import students.Student;

import java.util.List;
import java.util.function.Predicate;

public class CalculatePredicates {

  public static <E> Predicate<E> negate(Predicate<E> pred) {
    return e -> !pred.test(e);
  }

  public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
    return e -> first.test(e) && second.test(e);
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.9,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    System.out.println("Smart:");
    Predicate<Student> smart = s -> s.getGpa() > 3.7;
    roster.stream()
        .filter(smart)
        .forEach(s -> System.out.println(s));

    System.out.println("Not smart:");
//    Predicate<Student> notSmart = s -> !smart.test(s);
    Predicate<Student> notSmart = negate(smart);

    roster.stream()
        .filter(notSmart)
        .forEach(s -> System.out.println(s));

    System.out.println("not very smart, but somewhat smart");
    Predicate<Student> barelySmart = s -> s.getGpa() > 2.5;

    roster.stream()
        .filter(and(notSmart, barelySmart))
        .forEach(s -> System.out.println(s));
  }
}
