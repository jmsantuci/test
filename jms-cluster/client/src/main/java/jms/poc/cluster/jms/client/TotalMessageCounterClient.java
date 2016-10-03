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

import jms.poc.cluster.jms.consumer.MessageCounter;

/**
 * @author jsantuci
 *
 */
public class TotalMessageCounterClient {

	/**
	 * 
	 */
	public TotalMessageCounterClient() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String total = "";
		int clientId = -1;
		
		if (args.length > 0) {
			clientId = Integer.parseInt(args[0]);
		}

		System.out.println("Reading configuration file");
		Hashtable<String, String> environment = new Hashtable<String, String>();
		ResourceBundle properties = ResourceBundle.getBundle("jndi-total");
		Enumeration<String> enumeration = properties.getKeys();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			environment.put(key, properties.getString(key));
		}

		Context context = null;
		try {
			context = new InitialContext(environment);
			MessageCounter counter = (MessageCounter) context.lookup("MessageCounterBean/remote");
			if (clientId > -1) {
				total = "Total - Client: " + clientId + " = " + counter.getTotalCount(clientId);
			} else {
				total = "Total = " + counter.getTotalCount();
			}
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
		System.out.println("\n--------------------------");
		System.out.println(total);

	}

}
