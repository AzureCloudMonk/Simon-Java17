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
  public static List<Student> getByCriterion(
      List<Student> students, CriterionOfStudent crit) {
    List<Student> res = new ArrayList<>();
    for (Student s : students) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
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
  }
}
