/**
 * 
 */
package jms.poc.tac.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * @author jsantuci
 *
 */
public class UserPassCallbackHandler implements CallbackHandler {

	private String name;
	private String password;
	
	/**
	 * @param name
	 * @param password
	 */
	public UserPassCallbackHandler(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
				nameCallback.setName(this.getName());
			} else if (callback instanceof PasswordCallback) {
				PasswordCallback passwordCallback = (PasswordCallback) callback;
				passwordCallback.setPassword(this.getPassword().toCharArray());
			}
		}
	}

}
