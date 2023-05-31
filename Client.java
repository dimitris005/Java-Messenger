import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnkownHostException;

public class Client {
  
  public static void f (String args[]) throws UnknownHostException, IOException {
    int portNumber;
    portNumber = 14001;
    Scanner sc = new Scanner(System.in);
    Socket socket = new Socket("", portNumber);
    Scanner sc1 = new Scanner(s.getInputStream());
    
    PrintStream p = new PrintStream(s.getOutputStream());

  }

  public void go () throws IOException {
    String userInput = "";
    while (! userInput.equals ("quit") ) {
      userInput = input.readLine ();
      System.out.println ( userInput );
    }
  System.out.println ("Program Terminated");
  }
}
