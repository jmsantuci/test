/**
 * 
 */
package jms.socket.client.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author jsantuci
 *
 */
public class SocketClient {

    /**
     * Default constructor.
     */
    public SocketClient() {
        super();
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args)  {
        String serverHostname = new String ("127.0.0.1");

        if (args.length > 0) {
           serverHostname = args[0];
        }
        System.out.println ("Attemping to connect to host " +
        serverHostname + " on port 10007.");

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader stdIn = null;

        try {
            echoSocket = new Socket(serverHostname, 10007);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            
            // Get user input
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
        
            System.out.print ("input: ");
            try {
                while ((userInput = stdIn.readLine()) != null && (!"Bye-client".equals(userInput))) {
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                    System.out.print ("input: ");
                }
            } catch (IOException ioException) {
                System.err.println("Error getting user input");
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + serverHostname);
        } finally {
            if (out != null) {
                out.close();
            }
            try {
                if (in != null) {
                    in.close();
                }
                if (stdIn != null) {
                    stdIn.close();
                }
                if (echoSocket != null) {
                    echoSocket.close();
                }
            } catch (IOException ioException) {
                System.err.println("Could't close correctly I/O");
            }
        }

    }

}
