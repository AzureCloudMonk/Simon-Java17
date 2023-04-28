package streamlab1;

import students.Student;

import java.util.List;

public class Answers {
  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.9,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    System.out.println("------------ all students:");
    roster.stream()
        .forEach(s -> System.out.println(s));

    System.out.println("------------ all nicely formatted:");
    roster.stream()
        .map(s -> String.format("%s has a gpa %3.1f and takes %s",
            s.getName(), s.getGpa(), s.getCourses()))
        .forEach(s -> System.out.println(s));

    System.out.println("------------ smart students:");
    roster.stream()
        .filter(s -> s.getGpa() > 3.0)
        .map(s -> String.format("%s is smart", s.getName()))
        .forEach(s -> System.out.println(s));

    System.out.println("------------ unenthusiastic:");
    roster.stream()
        .filter(s -> s.getCourses().size() < 2)
        .map(s -> String.format("%s only takes %d courses",
            s.getName(), s.getCourses().size()))
        .forEach(s -> System.out.println(s));

    System.out.println("------------ all courses:");
    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .forEach(s -> System.out.println(s));

    System.out.println("------------ distinct courses:");
    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .forEach(s -> System.out.println(s));

    System.out.println("------------ ordered distinct courses:");
    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .sorted()
        .forEach(s -> System.out.println(s));

    System.out.println("------------ student-course pairs:");
    roster.stream()
        .flatMap((Student s) -> {
          return s.getCourses().stream()
              .map(c -> String.format("%s takes %s", s.getName(), c))   ;
        })
        .forEach(s -> System.out.println(s));
  }
}
