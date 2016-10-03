/**
 * 
 */
package jms.icmp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jsantuci
 *
 */
public class ICMP {

	/**
	 * Default constructor.
	 */
	public ICMP() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args != null && args.length > 0 && args[0].length() > 0) {
			try {
				InetAddress inetAddress = InetAddress.getByName(args[0]);
				System.out.println("Canonical host name: " + inetAddress.getCanonicalHostName());
				boolean isReachable = inetAddress.isReachable(10000);
				System.out.println("Address " + args[0] + " is reachable ? : " + isReachable);
				
				if (isReachable) {
					for (int i = 0; i < 5; i++) {
						long initialTime = System.currentTimeMillis();
						inetAddress.isReachable(10000);
						long timeToPing = System.currentTimeMillis() - initialTime;
						System.out.println("Ping time: " + timeToPing);
					}
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
