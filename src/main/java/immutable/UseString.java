package immutable;

public class UseString {
  public static final String s = "Hello";
  public static void main(String[] args) {
    System.out.println(s);
//    s.toUpperCase(); // useless!
    String s1 = s.toUpperCase();
//    s = s.toUpperCase();
//    System.out.println(s);
    System.out.println(s1);

    String s2 = "He";
    s2 = s2 + "llo";

    // Better: s2 = s2.intern(), which allows GC on the old value
    String s3 = s2.intern();

    System.out.println("s = " + s);
    System.out.println("s2 = " + s2);
    System.out.println("s == s2? " + (s == s2));
    System.out.println("s3 = " + s3);
    System.out.println("s == s3? " + (s == s3));
  }
}
