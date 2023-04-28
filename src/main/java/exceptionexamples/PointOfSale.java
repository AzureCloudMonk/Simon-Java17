package exceptionexamples;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.sql.SQLException;

class ModemDidNotConnectException extends Exception {}

class ModemDriver {
  private static boolean useModem = false;

  public static void dialModem()
      throws ModemDidNotConnectException, IOException {
    if (useModem) {
      // dial, work the hardware
      if (Math.random() > 0.5) {
        // pretend we got a busy signal
        throw new ModemDidNotConnectException();
      }
    } else {
      Socket s = new Socket("127.0.0.1", 9999);
    }
  }
}

public class PointOfSale {
  public static void sellStuff() throws SQLException {
    // calculate total for billing
    // decide how they're paying
    // if credit card...
    boolean success = false;
    while (!success) {
      try {
        System.out.println("initializing");
        ModemDriver.dialModem();
        if (Math.random() > 0.8) {
          // uh oh, something else, really bad, in the database, broke
          throw new SQLException();
        }
        // happy path, the try block describes what we *hope* will happen
        System.out.println("completed OK");
        success = true;
      } catch (ModemDidNotConnectException | IOException me) {
        // prompt user to wiggle the wires
        System.out.println("oops, connection failed");
//      } catch (IOException ioe) {
//        System.out.println("oops, connection failed");
      }

//      finally {
//        // this code executes "always"
//        // originally intended for closing files and similar
//      }
      // the rest of the method...
    }
  }
}
