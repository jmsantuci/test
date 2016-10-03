/**
 * 
 */
package jms.icmp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jsantuci
 *
 */
public class LocalHostName {

	/**
	 * 
	 */
	public LocalHostName() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println("Local host name: " + inetAddress.getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
