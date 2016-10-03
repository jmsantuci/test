/**
 * 
 */
package jms.socket.client.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * @author jsantuci
 *
 */
public class WaitResponse implements Runnable {

	private Socket socket;

	/**
	 * Default constructor
	 */
	private WaitResponse() {
		super();
	}

	/**
	 * Default constructor
	 */
	public WaitResponse(Socket socket) {
		this();
		this.setSocket(socket);
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
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioException) {
                System.err.println("Could't close correctly I/O");
            }
		}
		

	}

}
