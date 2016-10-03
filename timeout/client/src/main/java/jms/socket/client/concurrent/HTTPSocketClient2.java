/**
 * 
 */
package jms.socket.client.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jsantuci
 *
 */
public class HTTPSocketClient2 {
	
	private List<Thread> threadToWait = new ArrayList<Thread>();
	
	private Socket socket;

	private OutputStreamWriter out = null;

	private BufferedReader in = null;
	
	/**
	 * Default constructor.
	 */
	private HTTPSocketClient2() {
		super();
	}

	public HTTPSocketClient2(String host, int port) throws UnknownHostException, IOException {
		this();
		this.setSocket(new Socket(host, port));
		this.setOut(new OutputStreamWriter(this.getSocket().getOutputStream(), "UTF-8"));
		this.setIn(new BufferedReader(new InputStreamReader(this.getSocket().getInputStream())));
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
	 * @return the out
	 */
	public OutputStreamWriter getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(OutputStreamWriter out) {
		this.out = out;
	}

	/**
	 * @return the in
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		HTTPSocketClient2 httpSocketClient = new HTTPSocketClient2("figo", 9000);
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
                	}
                    System.out.print ("input: ");
                }
            } catch (IOException ioException) {
                System.err.println("Error getting user input");
            } finally {
            	httpSocketClient.waitThreads();
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
		System.out.println("Bye!!");
	}

	public Thread sendWait() {
		Thread send = new Thread(
				new SendReceive(
						this.getSocket(),
						"GET http://figo:9000/timeout-vivo/WaitServlet?time=30000 HTTP/1.1\r\n",
						this.getOut(),
						this.getIn()));
		send.start();
		this.getThreadToWait().add(send);
		return send;
	}

	public Thread sendKeepAlive() {
		Thread keepAlive = new Thread(
				new SendReceive(
						this.getSocket(),
						"GET http://figo:9000/timeout-vivo/WaitServlet?keepAlive=true HTTP/1.1\r\n",
						this.getOut(),
						this.getIn()));
		keepAlive.start();
		this.getThreadToWait().add(keepAlive);
		return keepAlive;
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
