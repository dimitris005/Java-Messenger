import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

  public static void main(String args[]) throws UnknownHostException, IOException {
    // here i specify what the port number and adress the client is going to connect
    // to is going to be
    int portNumber = 14001;
    String address = "localhost";

    //here I am parsing in the command line arguments for the client
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.equals("-ccp") && i < args.length - 1) {
        String sPort = args[i + 1];
        try {
          portNumber = Integer.parseInt(sPort);
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
      } else if (arg.equals("-cca") && i < args.length - 1){
        address = args[i + 1];
      }
    }

    Scanner sc = new Scanner(System.in);

    Socket socket = new Socket(address, portNumber);
    // here i use the bufferedreader and the printwriter to get input and output for
    // the client
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

    // a new thread is created so i can get the message of the client and print it
    // out
    Thread recieve = new Thread() {
      public void run() {
        String msg;
        try {
          while ((msg = reader.readLine()) != null) {
            System.out.println(msg);
          }
        } catch (IOException e) {

        }
      }
    };

    // in this thread i also check if the message of the client is logout and if it
    // is I break and this client can no longer send messages
    Thread send = new Thread() {
      public void run() {
        String userInput;
        while ((userInput = sc.nextLine()) != null) {
          writer.println(userInput);
          if (userInput.equals("logout")) {
            break;
          }
        }
      }
    };

    send.setDaemon(true);

    // start the two threads with the correct order
    recieve.start();
    send.start();

    // this is done to avoid one blocking the other
    try {
      recieve.join();
    } catch (InterruptedException e) {
    }

    try {
      socket.close();
    } catch (IOException e) {

    }

    System.out.println("Program Terminated");
  }
}
