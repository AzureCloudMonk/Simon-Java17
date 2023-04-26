package equality;

public class TestTires {
  public static void main(String[] args) {
    Tire t1 = new Tire(14, 155);
    Tire t2 = new Tire(14, 155);
    Tire t3 = new Tire(15, 155);
    Tire t4 = new TruckTire(14, 155, 100);

    System.out.println(t1 == t2);
    System.out.println(t1.equals(t2));
    System.out.println(t1.equals(t3));
    System.out.println(t1.equals(t4));
    System.out.println(t4.equals(t1));
  }
}
