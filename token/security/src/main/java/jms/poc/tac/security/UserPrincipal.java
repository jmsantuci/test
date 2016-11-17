/**
 * 
 */
package jms.poc.tac.security;

import java.io.Serializable;
import java.security.Principal;

/**
 * @author jsantuci
 *
 */
public class UserPrincipal implements Principal, Serializable {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * User name.
	 */
	private String name ;

	/**
	 * User password.
	 */
	private String password;
	
	/**
	 * Default constructor
	 */
	public UserPrincipal() {
		super();
	}

	/**
	 * Construct UserPrincipal thought user name and password.
	 * 
	 * @param name user name
	 * @param password user password
	 */
	public UserPrincipal(String name, String password) {
		super();
		this.name = name;
		this.password = password;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
		int result = 0;
		if (this.getName() != null) {
			result = this.getName().hashCode();;
		}
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		UserPrincipal other = (UserPrincipal) obj;
//		if (name == null) {
//			if (other.name != null) {
//				return false;
//			}
//		} else if (!name.equals(other.name)) {
//			return false;
//		}
//		return true;
		boolean result = false;

		if (this == other) {
			result = true;
		} else if (other != null) {
            if (other.getClass() == this.getClass()) {
            	Principal otherPrincipal = (Principal) other;
            	if (this.getName() != null) {
            		result = this.getName().equals(otherPrincipal.getName());
            	}
            }
		}
		return result;
	}

	
	
}
