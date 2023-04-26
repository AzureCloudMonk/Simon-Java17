package equality;

public class Tire {
  private int diameter;
  private int width;

  public Tire(int diameter, int width) {
    this.diameter = diameter;
    this.width = width;
  }

  @Override
  public boolean equals(Object o) {
//    if (o instanceof Tire) {
//      Tire other = (Tire)o;
//      return this.width == other.width
//          && this.diameter == other.diameter;
//    }
//    return false;

    if (o.getClass() == Tire.class) {
      Tire other = (Tire)o;
      return this.width == other.width
          && this.diameter == other.diameter;
    }
    return false;

    // only works since ?? by Java 17
//    return o instanceof Tire other
//        && this.width == other.width
//        && this.diameter == other.diameter;
  }

  @Override
  public String toString() {
    return "Tire{" +
        "diameter=" + diameter +
        ", width=" + width +
        '}';
  }
}
