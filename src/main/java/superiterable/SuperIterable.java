package superiterable;

import students.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> filter(
      SuperIterable<E> this, Predicate<E> crit) {
    List<E> res = new ArrayList<>();
//    for (E s : this.self) {
//      if (crit.test(s)) {
//        res.add(s);
//      }
//    }

    this.self.forEach(e -> {
      if (crit.test(e)) res.add(e);
    });
    return new SuperIterable<>(res);
  }

  // this type of bucket, with a "map" operation is called:
  // Functor
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
//    for (E e : self) {
//      F f = op.apply(e);
//      res.add(f);
//    }

    self.forEach(e -> res.add(op.apply(e)));
    return new SuperIterable<>(res);
  }

  // things that have a flatMap operation like this are called
  // Monad
  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> res = new ArrayList<>();

//    for (E e : self) {
//      SuperIterable<F> fs = op.apply(e);
//      for (F f : fs) {
//        res.add(f);
//      }
//    }

    self.forEach(e -> op.apply(e).forEach(f -> res.add(f)));
    return new SuperIterable<>(res);
  }


  public void doToAll(Consumer<E> op) {
    for (E e : self) {
      op.accept(e);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}

class TrySuperIterator {
  public static void main(String[] args) {
    SuperIterable<String> names = new SuperIterable<>(
        List.of("Fred", "Jim", "Sheila")
    );

//    for (String s : names) {
//      System.out.println(">> " + s);
//    }
//    names.doToAll(s -> System.out.println(s));
    names.forEach(s -> System.out.println(s));

    System.out.println("long strings are:");
//    SuperIterable<String> longNames = names.filter(s -> s.length() > 4);
//    for (String s : longNames) {
//      System.out.println(">> " + s);
//    }
    names
        .filter(s -> s.length() > 4)
        .forEach(s -> System.out.println("long: " + s));
//        .doToAll(s -> System.out.println("long: " + s));

    System.out.println("Uppercase:");
    names
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println(s));

    System.out.println("Lengths:");
    names
        .map(s -> s.length())
        .forEach(s -> System.out.println(s));

    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.9,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    System.out.println("courses...");
    // this prints a set of courses on each of three lines
//    new SuperIterable<>(roster)
//        .map(s -> s.getCourses())
//        .forEach(x -> System.out.println(x));

    // this prints one course on each of 7 lines
    new SuperIterable<>(roster)
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .forEach(x -> System.out.println(x));

    System.out.println("using stream");
    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .forEach(x -> System.out.println(x));

    System.out.println("Infinite");
    Stream.iterate(0, x -> x + 1)
        .forEach(x -> System.out.println(x));


  }
}
