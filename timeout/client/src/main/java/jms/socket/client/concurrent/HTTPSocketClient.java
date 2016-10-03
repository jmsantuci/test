/**
 * 
 */
package jms.socket.client.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jsantuci
 *
 */
public class HTTPSocketClient {
	
	private List<Thread> threadToWait = new ArrayList<Thread>();
	
	private Socket socket;

	private Thread response;

	
	/**
	 * Default constructor.
	 */
	private HTTPSocketClient() {
		super();
	}

	public HTTPSocketClient(String host, int port) throws UnknownHostException, IOException {
		this();
		this.setSocket(new Socket(host, port));
	}

	/**
	 * @return the threadToWait
	 */
	public List<Thread> getThreadToWait() {
		return this.threadToWait;
	}

	/**
	 * @param threadToWait the threadToWait to set
	 */
	public void setThreadToWait(List<Thread> threadToWait) {
		this.threadToWait = threadToWait;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return the response
	 */
	public Thread getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Thread response) {
		this.response = response;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		HTTPSocketClient httpSocketClient = new HTTPSocketClient("figo", 80);
		BufferedReader stdIn = null;

//		httpSocketClient.readResponse();

		try {
			
			// Get user input
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
        
            System.out.print ("input: ");
            try {
                while ((userInput = stdIn.readLine()) != null && (!"q".equals(userInput))) {
                	if ("k".equals(userInput)) {
                		httpSocketClient.sendKeepAlive();
                	} else if ("w".equals(userInput)) {
                		httpSocketClient.sendWait();
                	} else if ("r".equals(userInput)) {
                		httpSocketClient.readResponse();
                	}
                    System.out.print("input: ");
                }
            } catch (IOException ioException) {
                System.err.println("Error getting user input");
            } finally {
            	httpSocketClient.waitThreads();
            	if (httpSocketClient.getResponse() != null) {
            		httpSocketClient.getResponse().interrupt();
            	}
            }
		} finally {
            try {
                if (httpSocketClient.getSocket() != null) {
                	httpSocketClient.getSocket().close();
                }
            } catch (IOException ioException) {
                System.err.println("Could't close correctly I/O");
            }
		}	

	}

	public Thread sendWait() {
		Thread send = new Thread(new SendRequest(this.getSocket(), "GET http://figo:80/timeout-vivo/WaitServlet?time=30000 HTTP/1.1\r\n"));
		send.start();
		this.getThreadToWait().add(send);
		return send;
	}

	public Thread sendKeepAlive() {
		Thread keepAlive = new Thread(new SendRequest(this.getSocket(), "GET http://figo:80/timeout-vivo/WaitServlet?keepAlive=true HTTP/1.1\r\n"));
		keepAlive.start();
		return keepAlive;
	}

	public Thread readResponse() {
		Thread response = new Thread(new WaitResponse(this.getSocket()));
		response.start();
		this.setResponse(response);
		return response;
	}

	public void waitThreads() {
		for (Thread thread : this.getThreadToWait()) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
