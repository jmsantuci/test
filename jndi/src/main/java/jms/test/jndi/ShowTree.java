/**
 * 
 */
package jms.test.jndi;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/**
 * @author jsantuci
 *
 */
public class ShowTree {

	private Context context;
	
	/**
	 * Default constructor.
	 */
	public ShowTree() {
		super();
	}

	/**
	 * @param context
	 */
	public ShowTree(Context context) {
		super();
		this.setContext(context);
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return this.context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @param context
	 * @throws NamingException
	 */
	public void printJNDITree(String ident) throws NamingException {
		NamingEnumeration<Binding> namingEnumeration = this.getContext().listBindings("");
		while (namingEnumeration.hasMore()) {
			Binding binding = namingEnumeration.next();
			System.out.println(ident + binding.getName());
			Object object = binding.getObject();
			if (object instanceof Context) {
				ShowTree showTree = new ShowTree((Context) object);
				showTree.printJNDITree(ident + " ");
			}
		}
	}

	/**
	 * Show the JNDI tree through an address.
	 * @param args start point of three.
	 */
	public static void main(String[] args) {
		try {
			Context context = new InitialContext();
			if (args.length > 0) {
				System.out.println("Lookup: " + args[0]);
				try {
					Object object = context.lookup(args[0]); 
					System.out.println("Object found!");
					if (object instanceof Context) {
						System.out.println("-------------------------------------------------------");
						System.out.println("Using " + args[0] + " as root");
						System.out.println("-------------------------------------------------------");
						context = (Context) object;
					} else {
						System.out.println("The object found is not a Context instance. Using InitialContext");
					}
				} catch (NameNotFoundException nameNotFoundException) {
					nameNotFoundException.printStackTrace();
					System.out.println("Name {" + args[0] + "} not found. Using InitialContext");
				}
			}
			ShowTree showTree = new ShowTree(context);
			showTree.printJNDITree("");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

}
