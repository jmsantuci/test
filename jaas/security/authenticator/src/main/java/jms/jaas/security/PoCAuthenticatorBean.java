package jms.jaas.security;

import java.util.Set;

import javax.ejb.Stateless;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;


/**
 * Session Bean implementation class AuthenticatorBean
 */
@Stateless
public class PoCAuthenticatorBean implements PoCAuthenticatorRemote {

    /**
     * Default constructor. 
     */
    public PoCAuthenticatorBean() {
        super();
    }

	/**
	 * @see jms.jaas.security.PoCAuthenticator#login()
	 */
	@Override
	public String login() {
		String result = null;
		PoCPrincipal pocPrincipal = this.getPoCPrincipal();
		if (pocPrincipal != null) {
			pocPrincipal.setLogged(true);
			// Gera um sessionId para retorno e para setar no principal 
//			result = this.generateSessionId();
//			pocPrincipal.setSessionId(result);
			result = pocPrincipal.getSessionId();
		}
		return result;
	}

	/**
	 * @see jms.jaas.security.PoCAuthenticator#getInfo()
	 */
	@Override
	public String getInfo() {
		StringBuilder result = new StringBuilder();

		Subject subject = this.getSubject();
		if (subject != null) {
			result.append("Subject by PolicyContext: ").append(subject);
			for (PoCPrincipal pocPrincipal : subject.getPrincipals(PoCPrincipal.class)) {
				result.append("\tPoCPrincipal: ").append(pocPrincipal);
				result.append('\n');
			}
		}
		return result.toString();
	}

	/**
	 * Remove o session id do Principal e força a remoção do Principal do cache de segurança do JBoss.
	 * 
	 * A remoção do cache foi colocada aqui para testes somente.
	 * 
	 * @see jms.jaas.security.PoCAuthenticator#logout()
	 */
	@Override
	public boolean logout() {
		boolean result = false;
		Subject subject = this.getSubject();
		if (subject != null) {
			System.out.println("PoCAuthenticatorBean.logout(). Subject is not null.");
			System.out.println("PoCAuthenticatorBean.logout(). Subject principals size: " + subject.getPrincipals().size());
			PoCPrincipal pocPrincipal = this.getPoCPrincipal(); 
			if (pocPrincipal != null) {
				pocPrincipal.setLogout(true);
				// JBoss specific code to force a call to LoginModule.logout
//				PoCJBossLoginModule.callLogout("poc-login-module", pocPrincipal);
//				PoCLoginModule.callLogout("poc-login-module", pocPrincipal.getName());
				result = true;
			}
		}
		return result;
	}

	/**
	 * Método auxiliar para retornar o Principal a partir do subject
	 * @return
	 */
	private PoCPrincipal getPoCPrincipal() {
		PoCPrincipal pocPrincipal = null;
		Set<PoCPrincipal> pocPrincipals = this.getSubject().getPrincipals(PoCPrincipal.class);
		if (pocPrincipals.size() > 0) {
			pocPrincipal = pocPrincipals.toArray(new PoCPrincipal[pocPrincipals.size()])[0];
		}
		return pocPrincipal;
	}

	/**
	 * Retorna o Subject a partir do contexto de segurança.
	 * @return
	 */
    protected Subject getSubject() {
    	Subject subject = null;
    	
    	try {
			subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
			e.printStackTrace();
		}

    	return subject;
    }

}
