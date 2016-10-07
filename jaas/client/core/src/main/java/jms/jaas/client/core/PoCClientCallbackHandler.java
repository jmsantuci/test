/**
 * 
 */
package jms.jaas.client.core;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import jms.jaas.security.PoCPrincipal;


/**
 * @author jsantuci
 *
 */
public class PoCClientCallbackHandler implements CallbackHandler {

	private PoCPrincipal pocPrincipal;

	/**
	 * Default constructor.
	 */
	public PoCClientCallbackHandler() {
		super();
	}

	/**
	 * @param pocPrincipal
	 */
	public PoCClientCallbackHandler(PoCPrincipal pocPrincipal) {
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

	/**
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (Callback callback : callbacks) {
			if (callback instanceof NameCallback) {
				NameCallback nameCallback = (NameCallback) callback;
				nameCallback.setName(this.getPocPrincipal().getName());
			} else if (callback instanceof PasswordCallback) {
				PasswordCallback passwordCallback = (PasswordCallback) callback;
				passwordCallback.setPassword(this.getPocPrincipal().getValues().get("credential").toCharArray());
			}
		}

	}

}
