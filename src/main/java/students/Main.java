package students;

import java.util.ArrayList;
import java.util.List;

interface CriterionOfStudent {
  boolean test(Student s);
}

class SmartStudentCriterion implements CriterionOfStudent {
  private double threshold;

  public SmartStudentCriterion(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(Student s) {
    return s.getGpa() > threshold;
  }
}

class EnthusiasticStudentCriterion implements CriterionOfStudent {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

class FirstHalfNameStudentCriterion implements CriterionOfStudent {
  @Override
  public boolean test(Student s) {
    return s.getName().charAt(0) <= 'M';
  }
}

public class Main {
  public static void showAll(List<Student> students) {
    for (Student s : students) {
      System.out.println(s);
    }
    System.out.println("---------------------");
  }

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
  public static List<Student> getByCriterion(
      Iterable<Student> students, CriterionOfStudent crit) {
    List<Student> res = new ArrayList<>();
    for (Student s : students) {
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
    CriterionOfStudent fairlySmart = new SmartStudentCriterion(2.0);
    showAll(getByCriterion(roster, fairlySmart));

    System.out.println("enthusiastic:"); // taking more than n classes
//    showAllEnthusiastic(roster, 3);
    showAll(getByCriterion(roster, new EnthusiasticStudentCriterion()));

    System.out.println("first half of alphabet names:");
    showAll(getByCriterion(roster, new CriterionOfStudent() {
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

  }
}
