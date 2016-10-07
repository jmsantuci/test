/**
 * 
 */
package jms.jaas.security;

import java.io.IOException;
import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;

/**
 * LoginModule que faz autenticação a partir do usuário e senha, sendo que o usuário é codificado
 * da seguinte forma:
 * 
 * <b>user@domain</b>
 * 
 * Este LoginModule deve ser configurado como sufficient.
 * 
 * @author jsantuci
 *
 */
public class PoCDecoderLoginModule extends PoCBaseLoginModule {

    /**
     * Default Constructor.
     */
    public PoCDecoderLoginModule() {
        super();
    }

    /**
     * Cria um PoCPrincipal a partir de uma string que deve ter o seguinte formato: user@domain.
     * 
     * @param identity
     * @return
     */
    public PoCPrincipal createPoCPrincipal(String identity) {
        PoCPrincipal pocPrincipal = new PoCPrincipal(identity);
        return pocPrincipal;
    }

    /**
     * @see jms.jaas.security.PoCBaseLoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
     */
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
            Map<String, ?> sharedState, Map<String, ?> options) {
        super.initialize(subject, callbackHandler, sharedState, options);
    }

    /**
     * @see javax.security.auth.spi.LoginModule#login()
     */
    @Override
    public boolean login() throws LoginException {
        System.out.println("PoCDecoderLoginModule.login");
        boolean result = false;
        
        if(this.getPocPrincipal() == null) {
            if (this.getCallbackHandler() != null) {
                NameCallback nc = new NameCallback("User name: ", "guest");
                PasswordCallback pc = new PasswordCallback("Password: ", false);
                Callback[] callbacks = {nc, pc};
                String identity = null;
                char[] password = null;
                try {
                   this.getCallbackHandler().handle(callbacks);
    
                   // Get user
                   identity = nc.getName();
                   this.setPocPrincipal(this.createPoCPrincipal(identity));
                   this.getPocPrincipal().decode();

                   // Get password
                   char[] tmpPassword = pc.getPassword();
                   if(tmpPassword != null) {
                      password = new char[tmpPassword.length];
                      System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
                      pc.clearPassword();
                      this.setCredential(new String(password));
                   }
                   if (this.getPocPrincipal() != null && this.getPocPrincipal().getSessionId() != null) {
                       this.getSharedState().put("javax.security.auth.login.name", this.getPocPrincipal());
                       this.getSharedState().put("javax.security.auth.login.password", this.getCredential());
                       result = true;
                       throw new LoginException("Test Exception");
                   } 
                } catch(IOException e) {
                   LoginException le = new LoginException("Failed to get username/password");
                   le.initCause(e);
                   throw le;
                } catch(UnsupportedCallbackException e) {
                   LoginException le = new LoginException("CallbackHandler does not support: " + e.getCallback());
                   le.initCause(e);
                   throw le;
                }
            } else {
                throw new LoginException("Error: no CallbackHandler available to collect authentication information");
            }
        }
        return result;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    @Override
    public boolean commit() throws LoginException {
        System.out.println("PoCDecoderLoginModule.commit");
        this.getSubject().getPrincipals().add(this.getPocPrincipal());
//        Group group = new SimpleGroup("Roles"); // TODO: JBoss needs a group named "Roles", why ?
//        group.addMember(new SimplePrincipal("WebAppUser"));
//        this.getSubject().getPrincipals().add(group);
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#abort()
     */
    @Override
    public boolean abort() throws LoginException {
        System.out.println("PoCDecoderLoginModule.abort");
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    @Override
    public boolean logout() throws LoginException {
        System.out.println("PoCDecoderLoginModule.logout");
        this.getSubject().getPrincipals().clear();
        return true;
    }

}
