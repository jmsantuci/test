/**
 * 
 */
package jms.poc.tac.security;

import java.io.IOException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import jms.poc.cluster.domain.User;
import jms.poc.cluster.domain.UserBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jsantuci
 *
 */
public class UserLoginModule implements LoginModule {
	
	private static Logger LOGGER = LoggerFactory.getLogger(LoginModule.class);

	/**
	 * Subject provided by the standard login context.
	 */
	protected Subject subject;

	/**
	 * User authenticated principal.
	 */
	protected UserPrincipal userPrincipal;

	/**
	 * CallbackHandler used to extract user name and password.
	 */
	protected CallbackHandler callbackHandler;

	/**
	 * Init flag.
	 */
	protected boolean init = false;

	/**
	 * Login flag.
	 */
	protected boolean loginOK = false;

	/**
	 * Login shared state map.
	 */
	protected Map<String, ?> sharedState;

	/**
	 * default options and othere info obtained from configuration
	 */
	protected Map<String, ?> options;

	
	
	/**
	 * Default constructor.
	 */
	public UserLoginModule() {
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
	 * @return the userPrincipal
	 */
	public UserPrincipal getUserPrincipal() {
		return this.userPrincipal;
	}

	/**
	 * @param userPrincipal the userPrincipal to set
	 */
	public void setUserPrincipal(UserPrincipal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}

	/**
	 * @return the callbackHandler
	 */
	public CallbackHandler getCallbackHandler() {
		return this.callbackHandler;
	}

	/**
	 * @param callbackHandler the callbackHandler to set
	 */
	public void setCallbackHandler(CallbackHandler callbackHandler) {
		this.callbackHandler = callbackHandler;
	}

	/**
	 * @return the init
	 */
	public boolean isInit() {
		return this.init;
	}

	/**
	 * @param init the init to set
	 */
	public void setInit(boolean init) {
		this.init = init;
	}

	/**
	 * @return the loginOK
	 */
	public boolean isLoginOK() {
		return this.loginOK;
	}

	/**
	 * @param loginOK the loginOK to set
	 */
	public void setLoginOK(boolean loginOK) {
		this.loginOK = loginOK;
	}

	/**
	 * @return the sharedState
	 */
	public Map<String, ?> getSharedState() {
		return this.sharedState;
	}

	/**
	 * @param sharedState the sharedState to set
	 */
	public void setSharedState(Map<String, ?> sharedState) {
		this.sharedState = sharedState;
	}

	/**
	 * @return the options
	 */
	public Map<String, ?> getOptions() {
		return this.options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(Map<String, ?> options) {
		this.options = options;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		LOGGER.debug("Logging by user and password");

		this.setSubject(subject);
		this.setSharedState(sharedState);
		this.setOptions(options);

		if (callbackHandler != null) {
			this.setCallbackHandler(callbackHandler);

			NameCallback nc = new NameCallback("User name: ", "guest");
			PasswordCallback pc = new PasswordCallback("Password: ", false);
			Callback[] callbacks = { nc, pc };
			char[] password = null;

			try {
				this.getCallbackHandler().handle(callbacks);

				// Get user@domain
				String name = nc.getName();

				if (name != null && name.length() > 0) {

					// Get password
					char[] tmpPassword = pc.getPassword();
					if (tmpPassword != null) {
						password = new char[tmpPassword.length];
						System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
						pc.clearPassword();
						this.setUserPrincipal(new UserPrincipal(name, new String(password)));
						this.setInit(true);
						LOGGER.debug("User login initialized. User: " + name);
					}
				}
			} catch (IOException ioException) {
				LOGGER.error("Error on getting login information", ioException);
			} catch (UnsupportedCallbackException unsupportedCallbackException) {
				LOGGER.error("Error on getting login information", unsupportedCallbackException);
			}

		}
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	@Override
	public boolean login() throws LoginException {
		if (this.isInit()) {
			// Checks if password contains only numbers
//			this.setLoginOK(this.getUserPrincipal().getPassword().matches("\\d+"));
			try {
				Context context = new InitialContext();
				UserBean userBean = (UserBean) context.lookup("java:app/jms-poc-tac-domain-1.0-SNAPSHOT/UserBean");
				User user = userBean.getUser(this.getUserPrincipal().getName());
				if (user != null) {
					this.setLoginOK(user.getPassword().equals(this.getUserPrincipal().getPassword()));
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LOGGER.debug("User authentication: " + this.isLoginOK());
		return this.isLoginOK();
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	@Override
	public boolean commit() throws LoginException {
		this.getSubject().getPrincipals().add(this.getUserPrincipal());
		return this.isLoginOK();
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	@Override
	public boolean logout() throws LoginException {
		return true;
	}

}
