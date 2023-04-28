package exceptionexamples;

import java.io.*;

public class YukkyClosing {
  public static void main(String[] args) {
    BufferedReader input = null;
    try {
      input = new BufferedReader(new FileReader("input.txt"));
      PrintWriter output = new PrintWriter(new FileWriter("output.txt"));
      String line;
      while ((line = input.readLine()) != null) {
        output.println(line);
      }
      // might never get here!
//      output.close();
//      input.close();
    } catch (IOException ioe) {
      System.out.println("oops!");
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException ioe) {
          System.out.println("yikes");
        }
      }
//      output.close(); // this too!
    }
  }
}
