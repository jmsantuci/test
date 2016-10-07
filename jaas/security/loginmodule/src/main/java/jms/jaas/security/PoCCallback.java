/**
 * 
 */
package jms.jaas.security;

import javax.security.auth.callback.Callback;

/**
 * @author jsantuci
 * @deprecated
 *
 */
public class PoCCallback implements Callback {

	private PoCPrincipal pocPrincipal;

	/**
	 * Default constructor.
	 */
	public PoCCallback() {
		super();
	}

	/**
	 * @return the pocPrincipal
	 */
	public PoCPrincipal getPocPrincipal() {
		return this.pocPrincipal;
	}

	/**
	 * @param pocPrincipal the pocPrincipal to set
	 */
	public void setPocPrincipal(PoCPrincipal pocPrincipal) {
		this.pocPrincipal = pocPrincipal;
	}

}
