/**
 * 
 */
package jms.test.jndi;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author jsantuci
 *
 */
public class CreateAliases {


	private static InitialContext initialContext;
	private static List<Alias> aliases = new ArrayList<Alias>();
	private static List<Tree> trees = new ArrayList<Tree>();
	
	static {
		try {
			initialContext = new InitialContext();
			
			trees.add(new Tree(initialContext, "/jndi/tree"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Default constructor.
	 */
	public CreateAliases() {
		super();
	}

	private static void createTrees() throws NamingException {
		for (Tree tree : trees) {
			System.out.println("Root: " + tree.getRoot() + " Tree: " + tree.getTree());
			tree.build();
			System.out.println("Tree exist ? " + tree.exist());
		}
	}
	/**
	 * @throws NamingException
	 */
	private static void createAliases() throws NamingException {
		for (Alias alias : aliases) {
			alias.createAlias();
			System.out.println("Link: " + alias.getLink() + " -> To: " + alias.getTarget() + " created!");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createTrees();
			createAliases();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (initialContext != null) {
					initialContext.close();
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
