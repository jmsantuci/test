/**
 * 
 */
package jms.jaas.security;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

/**
 * LoginModule que faz autenticação a partir de um checksum.
 * 
 * Este LoginModule deve ser configurado como Sufficient.
 * 
 * @author jsantuci
 *
 */
public class PoCChecksumLoginModule extends PoCBaseLoginModule {

    private String checksum;

    /**
     * Defualt Constructor.
     */
    public PoCChecksumLoginModule() {
        super();
    }

    /**
	 * @return the credential
	 */
	public String getChecksum() {
		return this.checksum;
	}

	/**
	 * @param credential the credential to set
	 */
	public void setChecksum(String credential) {
		this.checksum = credential;
	}

    /**
     * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
     */
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        System.out.println("Begin of PoCChecksumLoginModule.initialize");

        super.initialize(subject, callbackHandler, sharedState, options);

        if (this.getPocPrincipal() != null) {
        	this.setChecksum(this.getPocPrincipal().getValues().get(Constants.CHECKSUM_KEY));
        }

        System.out.println("End of PoCChecksumLoginModule.initialize");
    }

	/**
     * @see javax.security.auth.spi.LoginModule#login()
     */
    @Override
    public boolean login() throws LoginException {
        System.out.println("PoCChecksumLoginModule.login");
        boolean result = false;

        
        if (this.getPocPrincipal() != null) {
        	// Checks checksum
        	if ("321".equals(this.getChecksum())) {
        		this.getSubject().getPrincipals().add(this.getPocPrincipal());
        		result = true;
        	}
        }
        return result;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    @Override
    public boolean commit() throws LoginException {
        System.out.println("PoCChecksumLoginModule.commit");
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#abort()
     */
    @Override
    public boolean abort() throws LoginException {
        System.out.println("PoCChecksumLoginModule.abort");
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    @Override
    public boolean logout() throws LoginException {
        System.out.println("PoCChecksumLoginModule.logout");
        this.getSubject().getPrincipals().clear();
        
        return true;
    }

}
