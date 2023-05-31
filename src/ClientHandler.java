import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private String name;
    final BufferedReader bIn;
    final PrintWriter pOut;
    Socket s;
    boolean isloggedin;

    public ClientHandler(Socket s, String name, BufferedReader bIn, PrintWriter pOut) {
        this.bIn = bIn;
        this.pOut = pOut;
        this.name = name;
        this.s = s;
        this.isloggedin = true;
    }

    public void run() {

        //this piece of code is dependent on the client being logged in
        while (isloggedin) {
            String message;
            try {
                // receive the string
                message = bIn.readLine();

                System.out.println(message);

                //if the message is logout then the boolean is set to false
                if (message.equals("logout")) {
                    this.isloggedin = false;
                    break;
                }

                for (ClientHandler client : ChatServer.clientlist) {
                    // if the recipient is found, write on its
                    // output stream
                    if (client.isloggedin) {
                        client.pOut.println(this.name + " : " + message);
                    }
                }
            } catch (IOException e) {
                isloggedin = false;
                break;
            }
        }

        try {
            s.close();
        } catch (IOException e) {

        }
    }
}
