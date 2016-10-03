/**
 * 
 */
package jms.socket.client.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * @author jsantuci
 *
 */
public class SendRequest implements Runnable {

	private Socket socket;

	private String request;

	/**
	 * Default constructor
	 */
	private SendRequest() {
		super();
	}

	/**
	 * Default constructor
	 */
	public SendRequest(Socket socket, String request) {
		this();
		this.setSocket(socket);
		this.setRequest(request);
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
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		try {
			out = new OutputStreamWriter(this.getSocket().getOutputStream(), "UTF-8");
			out.write(this.getRequest());
			out.write("Host: figo\r\n");
			out.write("Agent: ze-marcelo\r\n");
			out.write("\r\n");
			out.flush();
			
			in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("Request done! Waiting...");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
            	if (out != null) {
    				out.close();
                }
            } catch (IOException ioException) {
                System.err.println("Could't close correctly output");
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioException) {
                System.err.println("Could't close correctly input");
            }
		}
		

	}

}
