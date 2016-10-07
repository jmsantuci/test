/**
 * 
 */
package jms.jaas.security;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.spi.LoginModule;

/**
 * Classe base dos LoginModule implementados por esse componente.
 * 
 * @author jsantuci
 *
 */
public abstract class PoCBaseLoginModule implements LoginModule {

    private Subject subject;

    private PoCPrincipal pocPrincipal;

    private CallbackHandler callbackHandler;

    private String credential;

    private Map sharedState;

    private Map options;

    /**
     * Defualt Constructor.
     */
    public PoCBaseLoginModule() {
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
	 * @return the credential
	 */
	public String getCredential() {
		return this.credential;
	}

	/**
	 * @param credential the credential to set
	 */
	public void setCredential(String credential) {
		this.credential = credential;
	}

	/**
     * @return the sharedState
     */
    public Map getSharedState() {
        return this.sharedState;
    }

    /**
     * @param sharedState the sharedState to set
     */
    public void setSharedState(Map sharedState) {
        this.sharedState = sharedState;
    }

    /**
     * @return the options
     */
    public Map getOptions() {
        return this.options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(Map options) {
        this.options = options;
    }

    /**
     * Obtem o principal a partir das variáveis compartilhadas.
     */
    protected void initializePocCPrincipal() {
		this.setPocPrincipal((PoCPrincipal) this.getSharedState().get("javax.security.auth.login.name"));
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        System.out.println("Begin of PoCBaseLoginModule.initialize");

        this.setSubject(subject);
        this.setCallbackHandler(callbackHandler);
        this.setSharedState(sharedState);
        this.setOptions(options);

        this.initializePocCPrincipal();
        this.setCredential((String) this.getSharedState().get("javax.security.auth.login.password"));
//        this.getPocPrincipal().getValues().get(Constants.CREDENTIAL_KEY) // A senha pode ser obtida através do Principal também

        System.out.println("End of PoCBaseLoginModule.initialize");
    }

}
