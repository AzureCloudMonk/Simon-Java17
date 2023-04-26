package students;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// recommend: always make every new class final by default
// it's easy to remove if you really want to, but really hard to add
// if anyone already subclassed!
public final class Student {
  // maybe make these fields final (we don't plan to change them)
  private String name;
  private double gpa;
  private Set<String> courses;

  // this is not good, caller can pass in mutable objects...
//  public Student(double gpa, String name, Set<String> courses) {

  // varargs, caller can provide an array, and could change that array!
  // much nicer calling syntax
  private Student(String name, double gpa, Set<String> courses) {
    if (!isValid(name, gpa, courses)) {
      throw new IllegalArgumentException("Bad student values");
    }

    this.gpa = gpa;
    this.name = name;
    this.courses = courses;
  }

  public static Student of(String name, double gpa, String ... courses) {
  // prior to Java 9, this is harder to achieve
    // for Lists, there is Arrays.asList(), but it's not strictly unmodifiable
    return new Student(name, gpa, Set.of(courses)); // rejects duplicates with exception
  }

  public final static class Builder {
    private String name;
    private double gpa;
    private Set<String> courses = new HashSet<>();

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder gpa(double gpa) {
      this.gpa = gpa;
      return this;
    }

    public Builder course(String course) {
      if (!courses.add(course)) {
        throw new IllegalArgumentException("duplicate course");
      }
      return this;
    }

    public Student build() {
      // validate...
      return new Student(name, gpa, courses);
    }
  }


  public static Builder builder() {
    return new Builder();
  }

  public double getGpa() {
    return gpa;
  }

  public String getName() {
    return name;
  }

  // can the caller do: s1.getCourses().add(xxx) ??
  // maybe:
  // - make courses an unmodifiable set of immutable things
  // - or return a "defensive copy"
  //   (is this worse than duplication when making changes?)
  // - or we can return a "proxy"
  public Set<String> getCourses() {
    // defensive copy version:
//    return new HashSet<>(courses);

    // return a proxy:
//    return Collections.unmodifiableSet(courses);

    // courses was created "unmodifiable" using Set.of :)
    return courses;
  }

  public static boolean isValid(String name, double gpa, Set<String> courses) {
    return name != null && courses != null
        && name.length() > 0
        && gpa >= 0 && gpa <= 5.0;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}

class TryMyBuilder {
  public static void main(String[] args) {
    Student.Builder sb = Student.builder()
        .course("Physics")
        .name("Fred")
        .gpa(3.2)
        .course("Math");

    Student s = sb.build();
    System.out.println("Student is " + s);

    sb.name("Alice");
    sb.course("Statistics");
    Student s2 = sb.build();
    System.out.println("Student is " + s2);
  }
}