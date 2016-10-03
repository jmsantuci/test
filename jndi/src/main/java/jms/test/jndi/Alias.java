/**
 * 
 */
package jms.test.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.LinkRef;
import javax.naming.NamingException;

/**
 * @author jsantuci
 *
 */
public class Alias {

	Context context;

	private String link;
	private String target;

	/**
	 * Default constructor.
	 */
	public Alias() {
		super();
	}

	/**
	 * Constructor that initialize all instance attributes.
	 * 
	 * @param context JNDI context
	 * @param link link address
	 * @param target target address
	 */
	public Alias(Context context, String link, String target) {
		super();
		this.context = context;
		this.link = link;
		this.target = target;
	}

	/**
	 * Constructor that set context with InitialContext instance.
	 * 
	 * @param link link address
	 * @param target target address
	 * @throws NamingException 
	 */
	public Alias(String link, String target) throws NamingException {
		this(new InitialContext(), link, target);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String source) {
		this.link = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void createAlias() throws NamingException {
		this.getContext().bind(this.getLink(), new LinkRef(this.getTarget()));
	}
	
	/**
	 * The main method accepts two parameters: link and target object.
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			try {
				String link = args[0];
				String target = args[1];
				InitialContext context = new InitialContext();
				Alias alias = new Alias(context, link, target);
	
				System.out.println("Target: " + alias.getTarget());
				Object object = context.lookup(alias.getTarget());
				if (object != null) {
					System.out.println("Target found");
					alias.createAlias();
					System.out.println("Alias created");
					object = context.lookup(alias.getLink());
					if (object != null) {
						System.out.println("Link found");
						System.out.println("Link: " + alias.getLink());
					} else {
						System.out.println("Link not found: " + alias.getLink());
					}
				} else {
					System.out.println("Target not found: " + alias.getTarget());
				}
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
