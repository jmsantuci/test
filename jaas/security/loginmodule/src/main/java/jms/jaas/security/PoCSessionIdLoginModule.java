/**
 * 
 */
package jms.jaas.security;


import javax.security.auth.login.LoginException;

/**
 * LoginModule que faz autenticação a partir do usuário e senha.
 * 
 * Este LoginModule deve ser configurado como Sufficient.
 * 
 * @author jsantuci
 *
 */
public class PoCSessionIdLoginModule extends PoCBaseLoginModule {

    /**
     * Defualt Constructor.
     */
    public PoCSessionIdLoginModule() {
        super();
    }

    /**
     * @see javax.security.auth.spi.LoginModule#login()
     */
    @Override
    public boolean login() throws LoginException {
        System.out.println("PoCSessionIdLoginModule.login");
        boolean result = false;

        
        if (this.getPocPrincipal() != null) {
        	if (this.getPocPrincipal().isLogged()) {
        		// Recuperar pelo session id
        	}
        }
        return result;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    @Override
    public boolean commit() throws LoginException {
        System.out.println("PoCSessionIdLoginModule.commit");
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#abort()
     */
    @Override
    public boolean abort() throws LoginException {
        System.out.println("PoCSessionIdLoginModule.abort");
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    @Override
    public boolean logout() throws LoginException {
        System.out.println("PoCSessionIdLoginModule.logout");
        this.getSubject().getPrincipals().clear();
        return true;
    }

}
