/**
 * 
 */
package jms.poc.cluster.domain;

/**
 * @author jsantuci
 *
 */
public class User {

	/**
	 * User name.
	 */
	private String name;

	/**
	 * User password.
	 */
	private String password;

	/**
	 * User blocked status
	 */
	private boolean blocked;

	/**
	 * Default constructor.
	 */
	public User() {
		super();
	}

	/**
	 * Builds new User object using fields.
	 * 
	 * @param name user name
	 * @param password user password
	 * @param blocked user status
	 */
	public User(String name, String password, boolean blocked) {
		super();
		this.name = name;
		this.password = password;
		this.blocked = blocked;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the blocked
	 */
	public boolean isBlocked() {
		return this.blocked;
	}

	/**
	 * @param blocked the blocked to set
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

}
