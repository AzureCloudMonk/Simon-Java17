package equality;

public class TruckTire extends Tire {
  private int loadRating;

  public TruckTire(int diameter, int width, int loadRating) {
    super(diameter, width);
    this.loadRating = loadRating;
  }

  @Override
  public boolean equals(Object o) {
//    return o instanceof TruckTire tt
//        && tt.loadRating == this.loadRating
//        && super.equals(o);
    if (o.getClass() == TruckTire.class) {
      TruckTire tt = (TruckTire) o;
      return tt.loadRating == this.loadRating
          && super.equals(o);
    }
    return false;
  }

  @Override
  public String toString() {
    return "TruckTire{" +
        "loadRating=" + loadRating +
        '}' + super.toString();
  }
}
