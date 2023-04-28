package annotations;

//@RunMe -- not permitted for Target(ElementType.METHOD :)
public class UnitUnderTest {
  String name = "Unknown";

//  @RunMe(value = "Franklin", count = 20)
  @RunMe("Franklin")
  public void doesItWork() {
    System.out.println("Testing if it works...");
  }

  @RunMe(value = "Albert")
  public void doesThatFunction() {
    System.out.println("Testing if it functions");
  }

  private void supportMethod() {
    System.out.println("helping a test...");
  }

  private void dontRunThis(int x) {}

  @Override
  public String toString() {
    return "Whoo hoo, I'm a UnitUnderTest";
  }
}

