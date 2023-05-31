import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.Vector;

public class ChatServer {

  static Vector<ClientHandler> clientlist = new Vector<>();

  public static void main(String args[]) throws IOException {

    //deafult port number
    int portNumber = 14001;

    //here I am parsing in the command line arguments for the server
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.equals("-csp") && i < args.length - 1) {
        String sPort = args[i + 1];
        try {
          portNumber = Integer.parseInt(sPort);
        } catch (NumberFormatException e){
          e.printStackTrace();
        }
      }
    }

    ServerSocket s1 = new ServerSocket(portNumber);
    // this i will be used to keep count of the number of clients
    int i = 0;

    // this is to get input to handle the exit case
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // this thread is ceated to handle the exit case so the server shuts down when
    // exit is typed on it and also closes down all the client sockets
    Thread closing = new Thread() {
      public void run() {
        String consoleinput;
        try {
          while ((consoleinput = input.readLine()) != null) {
            if (consoleinput.equalsIgnoreCase("exit")) {
              for (ClientHandler client : ChatServer.clientlist) {
                try {
                  client.s.close();
                } catch (IOException e) {

                }
                try {
                  s1.close();
                } catch (IOException e) {

                }
              }
              break;
            }
          }
        } catch (IOException e) {

        }
      }
    };

    closing.start();

    while (true) {
      try {
        Socket client = s1.accept();
        // System.out.println("test");

        // again for the input and output
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

        // create new clients
        ClientHandler mtch = new ClientHandler(client, "client " + i++, reader, writer);

        // Create a new Thread with this object.
        Thread t = new Thread(mtch);

        clientlist.add(mtch);

        // start the thread.
        t.start();

        // the date is printed so we know when the conversation happens (was also used
        // as a test when debugging)
        writer.println(new Date().toString());
      } catch (SocketException s) {
        break;
      }

    }
  }
}