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

	/**
	 * Default constructor.
	 */
	public HTTPSocketClient() {
		super();
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("figo", 80);
		OutputStreamWriter out = null;
		BufferedReader in = null;
		
		try {
			out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.write("GET http://figo:80/http-socket/WaitServlet?time=500 HTTP/1.1\r\n");
			out.write("Host: figo\r\n");
			out.write("Agent: ze-marcelo\r\n");
			out.write("\r\n");
			out.flush();
			
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			if (out != null) {
                out.close();
            }
            try {
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ioException) {
                System.err.println("Could't close correctly I/O");
            }
		}
	}

}
