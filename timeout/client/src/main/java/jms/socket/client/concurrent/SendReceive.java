/**
 * 
 */
package jms.socket.client.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author jsantuci
 *
 */
public class SendReceive implements Runnable {

	private Socket socket;

	private String request;

	private OutputStreamWriter out = null;

	private BufferedReader in = null;
	
	/**
	 * Default constructor
	 */
	private SendReceive() {
		super();
	}

	/**
	 * Default constructor
	 */
	public SendReceive(Socket socket, String request, OutputStreamWriter out, BufferedReader in) {
		this();
		this.setSocket(socket);
		this.setRequest(request);
		this.setOut(out);
		this.setIn(in);
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
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
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
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {

			this.getOut().write(this.getRequest());
			this.getOut().write("Host: figo\r\n");
			this.getOut().write("Agent: ze-marcelo\r\n");
			this.getOut().write("\r\n");
			this.getOut().flush();
			
			System.out.println("Request done! Waiting...");
			String line = null;
			while ((line = this.getIn().readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
