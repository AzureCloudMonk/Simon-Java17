package students;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//interface CriterionOfStudent {
//interface Predicate<E> {
//  boolean test(E s);
//}

//class SmartStudentCriterion implements CriterionOfStudent {
class SmartStudentCriterion implements Predicate<Student> {
  private double threshold;

  public SmartStudentCriterion(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(Student s) {
    return s.getGpa() > threshold;
  }
}

class EnthusiasticStudentCriterion implements Predicate<Student> {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

class FirstHalfNameStudentCriterion implements Predicate<Student> {
  @Override
  public boolean test(Student s) {
    return s.getName().charAt(0) <= 'M';
  }
}

public class Main {
  // generic type params like this do NOT distinguish valid overloads
//  void doStuff(List<String> ls) {}
//  void doStuff(List<StringBuilder> ls) {}

  public static <E> void showAll(List<E> students) {
    for (E s : students) {
      System.out.println(s);
    }
    System.out.println("---------------------");
  }

  // List<?> means I have no clue what type this is supposed to contain
  // therefore I can't add anything to it, but anything I take out
  // must (obviously) be assignable to Object.
//  public static void showAll(List<?> students) {
//    for (Object s : students) {
//      System.out.println(s);
//    }
//    System.out.println("---------------------");
//  }

//  private static double threshold = 3.0;
//
//  public static void setThreshold(double t) {
//    // check caller credentials?
//    threshold = t;
//  }

//  public static void showAllSmart(List<Student> students, double threshold) {
////  public static void showAllSmart(List<Student> students) {
//    for (Student s : students) {
//      if (s.getGpa() > threshold) {
//        System.out.println(s);
//      }
//    }
//    System.out.println("---------------------");
//  }
//

  // argument types constrain the *caller* and callers benefit from generality
  // return type is a constraint on the *implementation* while
  //  callers benefit (potentially) from specificity
  public static <E> List<E> getByCriterion(
      Iterable<E> students, Predicate<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : students) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
//    List<List<String>> lls = null;
//    lls.get(0)

    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.9,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );
    showAll(roster);
    System.out.println("smart:");
//    showAllSmart(roster, 3.0);
//    showAllSmart(roster);
    showAll(getByCriterion(roster, new SmartStudentCriterion(3.0)));

    System.out.println("very smart:");
//    showAllSmart(roster, 3.7);
//    setThreshold(3.7);
    showAll(getByCriterion(roster, new SmartStudentCriterion(3.7)));

//    showAllSmart(roster);
    System.out.println("marketing smart:");
//    showAllSmart(roster, 2.0);
    Predicate<Student> fairlySmart = new SmartStudentCriterion(2.0);
    showAll(getByCriterion(roster, fairlySmart));

    System.out.println("enthusiastic:"); // taking more than n classes
//    showAllEnthusiastic(roster, 3);
    showAll(getByCriterion(roster, new EnthusiasticStudentCriterion()));

    System.out.println("first half of alphabet names:");
    showAll(getByCriterion(roster, new Predicate<Student>() {
      @Override
      public boolean test(Student s) {
        return s.getName().charAt(0) <= 'M';
      }
    }));

//    CriterionOfStudent cs = new CriterionOfStudent() {
//      @Override
//      public boolean test(Student s) {
//        return s.getName().charAt(0) <= 'M';
//      }
//    };
//    System.out.println(cs.getClass().getName());

    // ??? demands an OBJECT (expression)
    // that object must implement an INTERFACE
    // and that interface MUST declare EXACTLY ONE abstract method
    // further, we must ONLY be interested in implementing that one method
//    showAll(getByCriterion(roster, ???));
    System.out.println("second half:");
    showAll(getByCriterion(roster,
//        (Student s) -> { return s.getName().charAt(0) > 'M'; }
//        (@NotNull var s) -> { return s.getName().charAt(0) > 'M'; }
//        (s) -> { return s.getName().charAt(0) > 'M'; }
//        s -> { return s.getName().charAt(0) > 'M'; }
        s -> s.getName().charAt(0) > 'M'
    ));

    List<String> names = List.of(
        "Fred", "Alice", "Bob", "Maverick", "Jim", "Sheila");
    showAll(getByCriterion(names, s -> s.length() > 4));

  }
}
