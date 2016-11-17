/**
 * 
 */
package jms.poc.tac.security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jsantuci
 *
 */
public class TokenLoginModule implements LoginModule {

	/**
	 * Logger instance
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(TokenLoginModule.class);
	
	/**
	 * Subject provided by the standard login context.
	 */
	private Subject subject;

	/**
	 * User authenticated principal.
	 */
	private KeyPrincipal keyPrincipal;

	/**
	 * Token used in authentication.
	 */
	private Token token;
	
	/**
	 * CallbackHandler used to extract user name and password.
	 */
	private CallbackHandler callbackHandler;

	/**
	 * Init flag.
	 */
	private boolean init = false;

	/**
	 * Login flag.
	 */
	private boolean loginOK = false;

	/**
	 * Login shared state map.
	 */
	private Map<String, ?> sharedState;

	/**
	 * default options and othere info obtained from configuration
	 */
	private Map<String, ?> options;

	/**
	 * Default constructor.
	 */
	public TokenLoginModule() {
		super();
	}

	/**
	 * @return the subject
	 */
	protected Subject getSubject() {
		return this.subject;
	}

	/**
	 * @param subject the subject to set
	 */
	protected void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the keyPrincipal
	 */
	public KeyPrincipal getKeyPrincipal() {
		return this.keyPrincipal;
	}

	/**
	 * @param keyPrincipal the keyPrincipal to set
	 */
	public void setKeyPrincipal(KeyPrincipal keyPrincipal) {
		this.keyPrincipal = keyPrincipal;
	}

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
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
	protected boolean isInit() {
		return this.init;
	}

	/**
	 * @param init the init to set
	 */
	protected void setInit(boolean init) {
		this.init = init;
	}

	/**
	 * @return the loginOK
	 */
	protected boolean isLoginOK() {
		return this.loginOK;
	}

	/**
	 * @param loginOK the loginOK to set
	 */
	protected void setLoginOK(boolean loginOK) {
		this.loginOK = loginOK;
	}

	/**
	 * @return the sharedState
	 */
	protected Map<String, ?> getSharedState() {
		return this.sharedState;
	}

	/**
	 * @param sharedState the sharedState to set
	 */
	protected void setSharedState(Map<String, ?> sharedState) {
		this.sharedState = sharedState;
	}

	/**
	 * @return the options
	 */
	protected Map<String, ?> getOptions() {
		return this.options;
	}

	/**
	 * @param options the options to set
	 */
	protected void setOptions(Map<String, ?> options) {
		this.options = options;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		LOGGER.debug("Logging by user and token");

		if (callbackHandler != null) {
			this.setCallbackHandler(callbackHandler);
			NameCallback nc = new NameCallback("User name: ", "guest");
			PasswordCallback pc = new PasswordCallback("Password: ", false);
			Callback[] callbacks = { nc, pc };
			char[] password = null;
			try {
				this.getCallbackHandler().handle(callbacks);

				String name = nc.getName();

				if (name != null && name.length() > 0) {

					// Get password
					char[] tmpPassword = pc.getPassword();
					if (tmpPassword != null) {
						password = new char[tmpPassword.length];
						System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
						pc.clearPassword();
						this.setKeyPrincipal(new KeyPrincipal(name, new String(password)));
						this.setInit(true);
						LOGGER.debug("Token login initialized. User: " + name);
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
			// TODO genereate key
			Token token = new Token(this.getKeyPrincipal().getName());
			this.setLoginOK(token.checkToken(this.getKeyPrincipal().getKey()));
		}
		LOGGER.debug("Token authentication: " + this.isLoginOK());
		return this.isLoginOK();
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	@Override
	public boolean commit() throws LoginException {
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
