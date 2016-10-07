/**
 * 
 */
package jms.jaas.client.core;

import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import jms.jaas.security.Constants;
import jms.jaas.security.PoCAuthenticator;
import jms.jaas.security.PoCPrincipal;

/**
 * @author jsantuci
 * @deprecated
 */
public class PoCClientLoginModule implements LoginModule {

	private Subject subject;

	private PoCPrincipal pocPrincipal;

	private Map<String, Object> sharedState;
	
	/**
	 * Default constructor
	 */
	public PoCClientLoginModule() {
		super();
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return this.subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
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
	 * @return the sharedState
	 */
	public Map<String, Object> getSharedState() {
		return this.sharedState;
	}

	/**
	 * @param sharedState the sharedState to set
	 */
	public void setSharedState(Map<String, Object> sharedState) {
		this.sharedState = sharedState;
	}

	private PoCPrincipal initializePocCPrincipal() {
		Set<PoCPrincipal> pocPrincipals = this.getSubject().getPrincipals(PoCPrincipal.class);
		// Get the first principal of set
		if (pocPrincipals.size() > 0) {
			this.setPocPrincipal(pocPrincipals.toArray(new PoCPrincipal[pocPrincipals.size()])[0]);
		}
		return this.getPocPrincipal();
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.setSubject(subject);
		this.setSharedState((Map<String, Object>) sharedState);
		this.initializePocCPrincipal();
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	@Override
	public boolean login() throws LoginException {

		boolean result = false;

		this.getSharedState().put("javax.security.auth.login.name", this.getPocPrincipal());
		this.getSharedState().put("javax.security.auth.login.password", this.getPocPrincipal().getValues().get(Constants.CREDENTIAL_KEY));

		if (this.getPocPrincipal() != null) {
			result = true;
		}
		return result;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	@Override
	public boolean commit() throws LoginException {
		this.getPocPrincipal().setSessionId(this.generateSessionId());
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	@Override
	public boolean logout() throws LoginException {
		boolean result = false;
		System.out.println("\n\n***** Begin of PoClientLoginModule.logout *****");
		Context context = null;
		PoCAuthenticator authenticator;
		try {
			context = new InitialContext();
			authenticator = (PoCAuthenticator) context.lookup("PoCAuthenticatorBean/remote");
			System.out.println("Calling PoCAutheticatorBean");
			result = authenticator.logout();
			System.out.println("PoCAuthenticator.logout: " + result);

			this.getPocPrincipal().setSessionId(null);
		} catch (NamingException namingException) {
			namingException.printStackTrace();
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException namingException2) {
					// TODO Auto-generated catch block
					namingException2.printStackTrace();
				}
			}
		}
		System.out.println("***** End of PoClientLoginModule.logout *****\n\n");
		return result;
	}

    /**
     * Generate the session ID through the current time.
     * @return
     */
    private String generateSessionId() {
        return "" + System.currentTimeMillis();
    }

}
