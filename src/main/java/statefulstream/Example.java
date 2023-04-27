package statefulstream;

import superiterable.SuperIterable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Example {
  public static void main(String[] args) {
    Function<String, String> upc = s -> {
      System.out.println("upc(" + s + ")");
      return s.toUpperCase();
    };

    Function<String, Integer> len = s -> {
      System.out.println("len(" + s + ")");
      return s.length();
    };

    List<String> names = List.of("Fred", "Jim", "Sheila");
    SuperIterable<String> sis1 = new SuperIterable<>(names);
    sis1
        .map(upc)
        .map(len);
    System.out.println("sis1 again!----------------");
    sis1.forEach(s -> System.out.println(s));

    System.out.println("----------------");
    Stream<String> ss = names.stream();
        ss.map(upc)
        .map(len)
        .forEach(x -> System.out.println("forEach: " + x));
    System.out.println("ss again!----------------");
//    ss.forEach(s -> System.out.println(s));


    SuperIterable<String> sis2 = new SuperIterable<>(names);
    sis2
        .map(upc); // here F is String

    sis2
        .map(len); // here F is Integer

  }
}
