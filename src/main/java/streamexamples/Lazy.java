package streamexamples;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lazy {
  public static void main(String[] args) {
    boolean allOdd =
    List.of(1,3,5,7,6,7,8).stream()
        .peek(n -> System.out.println("p: " + n))
        .allMatch(n -> n % 2 != 0);
//        .forEach(n -> System.out.println(n));
    System.out.println("all odd? " + allOdd);

    System.out.println("----------------");
//    IntStream.of(1,2,3,4,5,6,7,8,9,10)
//        .sum();

//    Optional<Integer> res =
        Stream.of(1,2,3,4,5,6,7,8,9,10)
        .reduce((a, b) -> a + b)
        .map(v -> "The sum is " + v)
        .ifPresent(m -> System.out.println(m));
//    System.out.println(res);
  }
}
