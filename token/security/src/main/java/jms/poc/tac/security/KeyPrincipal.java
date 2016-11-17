/**
 * 
 */
package jms.poc.tac.security;

import java.security.Principal;

/**
 * @author jsantuci
 *
 */
public class KeyPrincipal implements Principal {

	/**
	 * User name.
	 */
	private String name ;

	/**
	 * User key.
	 */
	private String key;
	
	/**
	 * Default constructor
	 */
	public KeyPrincipal() {
		super();
	}

	/**
	 * Construct UserPrincipal thought user name and key.
	 * 
	 * @param name user name
	 * @param key user key
	 */
	public KeyPrincipal(String name, String password) {
		super();
		this.name = name;
		this.key = password;
	}

	/**
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Set the new value of param name.
	 * 
	 * @param name user name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * @param key the key to set
	 */
	public void setkey(String key) {
		this.key = key;
	}

}
