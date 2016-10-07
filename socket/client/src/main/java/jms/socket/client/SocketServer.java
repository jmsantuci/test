/**
 * 
 */
package jms.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jsantuci
 *
 */
public class SocketServer {

    /**
     * Default constructor.
     */
    public SocketServer() {
        super();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try { 
            serverSocket = new ServerSocket(10007);
            System.out.println ("Waiting for connection.....");
            try { 
                clientSocket = serverSocket.accept();
                System.out.println ("Connection successful");
                System.out.println ("Waiting for input.....");

                try {
	                out = new PrintWriter(clientSocket.getOutputStream(), true); 
	                in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream())); 
	
	                String inputLine; 
	
	                while (((inputLine = in.readLine()) != null) && (!inputLine.equals("Bye."))) { 
	                    System.out.println ("Server: " + inputLine); 
	                    out.println(inputLine); 
	                }
                } catch (IOException e) { 
                    System.err.println("Error on reading input."); 
                }
            } catch (IOException e) { 
                System.err.println("Accept failed."); 
            }
            
        } catch (IOException e) { 
            System.err.println("Could not listen on port: 10007."); 
        } finally {
        	if (out != null) {
                out.close();
            }
            try {
                if (in != null) {
                    in.close();
                }
                if (clientSocket != null) {
                	clientSocket.close();
                }
                if (serverSocket != null) {
                	serverSocket.close();
                }
            } catch (IOException ioException) {
                System.err.println("Could't close correctly I/O");
            }
        }

    }

}
