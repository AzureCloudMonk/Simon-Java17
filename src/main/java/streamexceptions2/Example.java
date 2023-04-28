package streamexceptions2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  static <A, R> Function<A, Either<R, Throwable>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Either.success(op.apply(a));
      } catch (Throwable t) {
        return Either.failure(t);
      }
    };
  }
}

// Classes like this Either can be found in function libraries for Java
// e.g. VAVR (formerly JavaSlang).
// there are others too...
class Either<S, F> {
  private S success;
  private F failure;

  private Either(S success, F failure) {
    this.success = success;
    this.failure = failure;
  }

  public static <S, F> Either<S, F> success(S s) {
    return new Either<>(s, null);
  }

  public static <S, F> Either<S, F> failure(F f) {
    return new Either<>(null, f);
  }

  public boolean isSuccess() {
    return failure == null;
  }

  public boolean isFailure() {
    return failure != null;
  }

  public S getSuccess() {
    if (isSuccess()) {
      return success;
    } else {
      throw new IllegalStateException("attempt to get success from a failure!");
    }
  }

  public F getFailure() {
    if (isSuccess()) {
      throw new IllegalStateException("attempt to get failure from a success!");
    } else {
      return failure;
    }
  }

  public void ifFailure(Consumer<F> op) {
    if (isFailure()) {
      op.accept(failure);
    }
  }

  // valid, but not likely very useful
  public Either<S, F> map(Function<Either<S, F>, Either<S, F>> op) {
    return op.apply(this);
  }

  public Either<S, F> recover(Function<Either<S, F>, Either<S, F>> op) {
    if (isFailure()) {
      return op.apply(this);
    } else {
      return this;
    }
  }
}

public class Example {
  public static void main(String[] args) {

    Function<String, Either<Stream<String>, Throwable>> getLines =
        ExFunction.wrap(fn -> Files.lines(Path.of(fn)));

    Function<Either<Stream<String>, Throwable>,
        Either<Stream<String>, Throwable>> recoverB = e -> {
      String failedFile = e.getFailure().getMessage();
      System.out.println("Failed opening file " + failedFile);
      return getLines.apply("d.txt");
    };

    List.of("a.txt", "b.txt", "c.txt")
        .stream()
        .map(getLines)
        .peek(e -> e.ifFailure(f -> System.out.println("uh oh, failed: " + f)))
        .map(e -> e.recover(recoverB))
        .peek(e -> e.ifFailure(f -> System.out.println("uh oh, failed: " + f)))
        .filter(e -> e.isSuccess())
        .flatMap(e -> e.getSuccess())
        .forEach(s -> System.out.println(s));
  }
}
