/**
 * 
 */
package jms.test.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

/**
 * @author jsantuci
 *
 */
public class Lookup {

	/**
	 * Default constructor
	 */
	public Lookup() {
		super();
	}

	/**
	 * Checks if an object is binded into JNDI namespace.
	 * 
	 * @param args address to lookup.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				Context context = new InitialContext();
				System.out.println("Lookup: " + args[0]);
				Object object = context.lookup(args[0]);
				System.out.println("Object: " + object);
			} catch (NameNotFoundException nameNotFoundException) {
				nameNotFoundException.printStackTrace();
				System.out.println("Name {" + args[0] + "} not found.");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

}
