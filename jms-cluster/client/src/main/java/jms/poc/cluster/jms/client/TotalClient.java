/**
 * 
 */
package jms.poc.cluster.jms.client;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jms.poc.cluster.jms.consumer.Counter;

/**
 * @author jsantuci
 *
 */
public class TotalClient {

	/**
	 * 
	 */
	public TotalClient() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long total = 0;

		System.out.println("Reading configuration file");
		Hashtable<String, String> environment = new Hashtable<String, String>();
		ResourceBundle properties = ResourceBundle.getBundle("jndi-total");
		Enumeration<String> enumeration = properties.getKeys();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			environment.put(key, properties.getString(key));
		}

		String providerURL = properties.getString("java.naming.provider.url");
		String[] servers = providerURL.split(",");
		
		System.out.println("Calling servers ...\n");
		System.out.println("<server>:<port> = <total> ");
		System.out.println("--------------------------");
		for (String server : servers) {
			System.out.print(server.trim() + " = ");
			environment.put("java.naming.provider.url", server);
			Context context = null;
			try {
				context = new InitialContext(environment);
				Counter counter = (Counter) context.lookup("CounterBean/remote");
				long count = counter.getCount();
				System.out.println(count);
				total += count;
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (context != null) {
					try {
						context.close();
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("\n--------------------------");
		System.out.println("Total = " + total);

	}

}
