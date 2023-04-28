package annotations;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestHarness {
  public static void main(String[] args) throws Throwable {
    Properties properties = new Properties();
    properties.load(new FileReader("ClassesToTest.properties"));
    String className = properties.getProperty("test");
    System.out.println("About to load class " + className);

    Class<?> cl = Class.forName(className);

    Constructor<?> cons = cl.getConstructor(); // ask for zero arg constructor
    Object target = cons.newInstance();
    System.out.println("Made target: " + target);

//    Method[] methods = cl.getMethods();
    Method[] methods = cl.getDeclaredMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
      RunMe annot = m.getAnnotation(RunMe.class);
      int paramCount = m.getParameterCount();
      if (paramCount != 0) {
        System.out.println("that method has parameters, so I can't use it");
      } else if (annot != null) {
        System.out.println("***** annotated with @RunMe, name is "
            + annot.value());
        m.invoke(target);
      }
    }
  }
}
