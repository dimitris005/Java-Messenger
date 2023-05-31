import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

  public static void main(String args[]) throws IOException {
    ServerSocket s1 = new ServerSocket(portNumber);
    
    while(true){
      Socket client = s1.accept();
      
      PrintWriter writer = PrintWriter(client.getOutputStream());
      Writer.println(new Date().toString());
      client.close();
    }
  }
}