package exceptionexamples;

import java.io.*;

public class BetterClosing {
  public static void main(String[] args) {
    try (
      BufferedReader input = new BufferedReader(new FileReader("input.txt"));
      PrintWriter output = new PrintWriter(new FileWriter("output.txt"));
    ) {
      String line;
      while ((line = input.readLine()) != null) {
        output.println(line);
      }
    } catch (IOException ioe) {
      System.out.println("oops");
    }
  }
}
