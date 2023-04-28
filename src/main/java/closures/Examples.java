package closures;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Examples {
  public static BiFunction<Integer, Integer, Integer> getAdder() {
    return (a, b) -> a + b;
  }

  // sufficient since Java 8 for x to be "effectively final"
  public static Function<Integer, Integer> getIncrementer(/*final */int x) {
    int t = 100;
//    x++;
    return a -> a + x/*++*/;
  }

  public static void main(String[] args) {

    var adder = getAdder();
    System.out.println(adder.apply(1, 3));

    var addNinetyNine = getIncrementer(99);
    System.out.println(addNinetyNine.apply(4));

    var addTen = getIncrementer(10);
    System.out.println(addTen.apply(100));
  }
}
