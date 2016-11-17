/**
 * 
 */
package jms.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author jsantuci
 *
 */
public class HTTPSocketClient {
	
	private static final int NUMBER_OF_PARAMETERS = 4;

	private String host;
	
	private int port;
	
	private String pathQueryString;

	private String targetNode;

	private Socket socket;

	private OutputStreamWriter out = null;

	private BufferedReader in = null;
	
	/**
	 * Default constructor.
	 */
	public HTTPSocketClient() {
		super();
	}

	public HTTPSocketClient(String host, int port, String pathQueryString, String targetNode) throws UnknownHostException, IOException {
		this();
		this.setHost(host);
		this.setPort(port);
		this.setPathQueryString(pathQueryString);
		this.setTargetNode(targetNode);
		this.setSocket(new Socket(this.getHost(), this.getPort()));
		this.setOut(new OutputStreamWriter(this.getSocket().getOutputStream(), "UTF-8"));
		this.setIn(new BufferedReader(new InputStreamReader(this.getSocket().getInputStream())));
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the pathQueryString
	 */
	public String getPathQueryString() {
		return this.pathQueryString;
	}

	/**
	 * @param pathQueryString the pathQueryString to set
	 */
	public void setPathQueryString(String pathQueryString) {
		this.pathQueryString = pathQueryString;
	}

	/**
	 * @return the targetNode
	 */
	public String getTargetNode() {
		return this.targetNode;
	}

	/**
	 * @param targetNode the targetNode to set
	 */
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return this.socket;
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
		return this.out;
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
		return this.in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}

	/**
	 * Show help message.
	 */
	private static void showHelpMessage() {
		System.out.println("\n\nProgram arguments:");
		System.out.println("\n\t<host name> <port> <path>/<query string> <target node>");
		System.out.println("\n\nExample:");
		System.out.println("\ts1.com.br 80 jms-wait/WaitServlet?time=900000 node1-00\n\n");
	}

	/**
	 * @return
	 */
	private String getRequest() {
		StringBuilder request = new StringBuilder();
		request.append("GET /");
		request.append(this.getPathQueryString());
		request.append(" HTTP/1.1");
		request.append("\n");
		
		String requestGET = request.toString();
		return requestGET;
	}

	private void showParameters() {
		System.out.println("\n\nParameters:");
		System.out.println("\tHost: " + this.getHost());
		System.out.println("\tPort: " + this.getPort());
		System.out.println("\tPath & Query String: " + this.getPathQueryString());
		System.out.println("\tTarget Node: " + this.getTargetNode());
	}

	public void sendWait() throws IOException {
		
		String request = this.getRequest(); 
		System.out.println("\n\nRequest: " + request);
		
		this.getOut().write(request);
		this.getOut().write("Host: " + this.getHost() + "\n");
		this.getOut().write("User-Agent: jms-KA-test\n");
		this.getOut().write("Cookie: JSESSIONID=AB12CD34." + this.getTargetNode() + "\n");
		this.getOut().write("\n");
		this.getOut().flush();
		
		System.out.println("\n\nRequest made! Waiting for server response...\n");
		String line = null;
		while ((line = this.getIn().readLine()) != null) {
			System.out.println(line);
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {

		if (args == null || args.length == 0) {
			System.out.println("\n\n*** No arguments.");
			showHelpMessage();
		} else if (args.length != NUMBER_OF_PARAMETERS) {
			System.out.println("\n\n*** The number of arguments is different of " + NUMBER_OF_PARAMETERS + ".");
			showHelpMessage();
		} else {

			HTTPSocketClient httpSocketClient = new HTTPSocketClient(args[0], Integer.parseInt(args[1]), args[2], args[3]);

			httpSocketClient.showParameters();

			try {
           		httpSocketClient.sendWait();
			} finally {
				if (httpSocketClient.getOut() != null) {
					try {
						httpSocketClient.getOut().close();
					} catch (IOException e) {
						System.err.println("Could't close correctly output stream");
						e.printStackTrace();
					}
				}
				if (httpSocketClient.getIn() != null) {
					try {
						httpSocketClient.getIn().close();
					} catch (IOException e) {
						System.err.println("Could't close correctly input stream");
						e.printStackTrace();
					}
				}
	            try {
	                if (httpSocketClient.getSocket() != null) {
	                	httpSocketClient.getSocket().close();
	                }
	            } catch (IOException ioException) {
	                System.err.println("Could't close correctly I/O");
	                ioException.printStackTrace();
	            }
			}	
			System.out.println("Bye!!\n\n");
		}
	}

}
