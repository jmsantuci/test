/**
 * 
 */
package jms.jaas.security;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * @author jsantuci
 * @deprecated
 */
public class PoCCallbackHandler implements CallbackHandler, Serializable {

	private Principal principal;

	private Object credential;

	/**
	 * Serial version identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PoCCallbackHandler() {
		super();
	}

	/**
	 * @param principal
	 * @param credential
	 */
	public PoCCallbackHandler(Principal principal, Object credential) {
		super();
		this.setPrincipal(principal);
        this.setCredential(credential);
	}



	public void setSecurityInfo(Principal principal, Object credential) {
        this.setPrincipal(principal);
        this.setCredential(credential);
	}
	
	/**
	 * @return the principal
	 */
	public Principal getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	/**
	 * @return the credential
	 */
	public Object getCredential() {
		return credential;
	}

	/**
	 * @param credential the credential to set
	 */
	public void setCredential(Object credential) {
		this.credential = credential;
	}

	/**
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (Callback callback : callbacks) {
			if (callback instanceof PoCCallback && this.getPrincipal() instanceof PoCPrincipal) {
				((PoCCallback) callback).setPocPrincipal((PoCPrincipal) this.getPrincipal());
			}
		}

	}

}
