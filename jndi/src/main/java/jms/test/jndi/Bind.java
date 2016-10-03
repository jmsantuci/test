/**
 * 
 */
package jms.test.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author jsantuci
 *
 */
public class Bind {

	/**
	 * Default constructor.
	 */
	public Bind() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Context context = null;
		try {
			context = new InitialContext();
			context.bind("/test-jms", "JMS - Bind JNDI");
			System.out.println("Address bound");
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

}
