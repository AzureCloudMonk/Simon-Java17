package averages;

import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public Average merge(Average other) {
    return new Average(this.sum + other.sum, this.count + other.count);
  }

  public OptionalDouble get() {
    if (count > 0) {
      return OptionalDouble.of(this.sum / this.count);
    } else {
      return OptionalDouble.empty();
    }
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    Stream.generate(
        () -> ThreadLocalRandom.current().nextDouble(-1, +1))
        .parallel()
        .limit(3_000_000_000L)
        .map(x -> new Average(x, 1))
        .reduce(new Average(0, 0), (a, b) -> a.merge(b))
        .get()
        .ifPresentOrElse(a -> System.out.println("The mean is " + a),
            () -> System.out.println("oops, no numbers here!"));

    long time = System.nanoTime() - start;
    System.out.printf("Time taken: %7.3f\n", (time / 1_000_000_000.0));
  }
}
