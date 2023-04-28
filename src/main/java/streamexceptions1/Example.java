package streamexceptions1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  static <A, R> Function<A, Optional<R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable t) {
        return Optional.empty();
      }
    };
  }
}

public class Example {
//  public static Stream<String> getLines(String fn) {
//    try {
//      return Files.lines(Path.of(fn));
//    } catch (IOException e) {
////      throw new RuntimeException(e);
//      System.out.println("**** Error opening " + e.getMessage());
//      return Stream.empty();
//    }
//  }

//  public static Optional<Stream<String>> getLines(String fn) {
//    try {
//      return Optional.of(Files.lines(Path.of(fn)));
//    } catch (IOException e) {
////      throw new RuntimeException(e);
//      System.out.println("**** Error opening " + e.getMessage());
//      return Optional.empty();
//    }
//  }

  public static void main(String[] args) {
    List.of("a.txt", "b.txt", "c.txt")
        .stream()
//        .map(fn -> getLines(fn))
        .map(ExFunction.wrap(fn -> Files.lines(Path.of(fn))))
        .peek(opt -> {
          if (opt.isEmpty()) {
            System.out.println("Uh oh something failed");
          }
        })
        .filter(opt -> opt.isPresent())
        .flatMap(opt -> opt.get())
        .forEach(s -> System.out.println(s));
  }
}
