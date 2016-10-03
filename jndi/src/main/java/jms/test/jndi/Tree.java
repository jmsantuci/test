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
public class Tree {

	private InitialContext initialContext;
	private Context context;
	private String root;
	private String tree;

	/**
	 * Default constructor.
	 */
	public Tree() {
		super();
	}

	/**
	 * @param root
	 * @throws NamingException 
	 */
	public Tree(InitialContext initialContext, String root, String tree) throws NamingException {
		super();
		this.setInitialContext(initialContext);
		this.setRoot(root);
		this.setTree(tree);
		this.setContext((Context) initialContext.lookup(this.getRoot()));
	}

	/**
	 * @return the initialContext
	 */
	public InitialContext getInitialContext() {
		return this.initialContext;
	}

	/**
	 * @param initialContext the initialContext to set
	 */
	public void setInitialContext(InitialContext initialContext) {
		this.initialContext = initialContext;
	}

	public Context getContext() {
		return this.context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the root
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * @return the tree
	 */
	public String getTree() {
		return tree;
	}

	/**
	 * @param tree the tree to set
	 */
	public void setTree(String tree) {
		this.tree = tree;
	}

	public void build() throws NamingException {
		this.build(this.getTree());
	}
	/**
	 * @param args
	 * @param context
	 * @throws NamingException
	 */
	public void build(String tree) throws NamingException {
		Context newContext = null;
		String[] names = tree.split("/");
		for (String name : names) {
			newContext = this.getContext().createSubcontext(name);
			this.getContext().close();
			this.setContext(newContext);
		}
		this.getContext().close();
	}

	public boolean rootExist() {
		boolean result = false;
		try {
			this.getInitialContext().lookup(this.getRoot());
			result = true;
		} catch (NamingException e) {
		}
		return result;
	}

	public boolean exist() {
		boolean result = false;
		try {
			this.getInitialContext().lookup(this.getRoot() + "/" + this.getTree());
			result = true;
		} catch (NamingException e) {
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			try {
				
				InitialContext initialContext = new InitialContext();
				Object object = initialContext.lookup(args[0]);
				System.out.println("Object found!");
				if (object instanceof Context) {
					
					System.out.println("-------------------------------------------------------");
					System.out.println("Using " + args[0] + " as root");
					System.out.println("-------------------------------------------------------");
					Tree tree = new Tree(initialContext, args[0], args[1]);
					tree.build();
					if (tree.exist()) {
						System.out.println("-------------------------------------------------------");
						System.out.println("New tree found " + object);
						System.out.println("-------------------------------------------------------");
					}
				} else {
					System.out.println("The object found is not a Context instance.");
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
