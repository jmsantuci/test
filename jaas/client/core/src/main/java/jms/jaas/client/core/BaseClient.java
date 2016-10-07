/**
 * 
 */
package jms.jaas.client.core;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.security.SecurityContextAssociation;

import jms.jaas.Hello;
import jms.jaas.security.Constants;
import jms.jaas.security.PoCAuthenticator;
import jms.jaas.security.PoCPrincipal;

//import org.jboss.security.client.SecurityClient;
//import org.jboss.security.client.SecurityClientFactory;

/**
 * @author jsantuci
 *
 */
public class BaseClient {

	private Subject subject;
	private PoCPrincipal pocCPrincipal;
	private LoginContext loginContext;

	/**
	 * Construtor para autenticação que pode receber uma senha ou um checksum.
	 */
	public BaseClient(String domain, String identity, String key, String value) {
		super();

		Set<String> pubCredentials = new HashSet<String>(0);
		Set<String> privCredentials= new HashSet<String>(1);
		Set<PoCPrincipal> principals = new HashSet<PoCPrincipal>(1);

		// Public credential
		pubCredentials.add("Public Credential");

		// Private credential
		if (Constants.CREDENTIAL_KEY.equals(key)) {
			privCredentials.add(value);
		}

		// Principal one
		PoCPrincipal pocPrincipal = new PoCPrincipal();
		pocPrincipal.getValues().put(key, value);
		pocPrincipal.setDomain(domain);
		pocPrincipal.setIdentity(identity);
		principals.add(pocPrincipal);

		this.setPocCPrincipal(pocPrincipal);
		this.setSubject(new Subject(false, principals, pubCredentials, privCredentials));
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
	 * @return the pocCPrincipal
	 */
	public PoCPrincipal getPocCPrincipal() {
		return this.pocCPrincipal;
	}

	/**
	 * @param pocCPrincipal the pocCPrincipal to set
	 */
	public void setPocCPrincipal(PoCPrincipal pocCPrincipal) {
		this.pocCPrincipal = pocCPrincipal;
	}

	/**
	 * @return the loginContext
	 */
	public LoginContext getLoginContext() {
		return this.loginContext;
	}

	/**
	 * @param loginContext the loginContext to set
	 */
	public void setLoginContext(LoginContext loginContext) {
		this.loginContext = loginContext;
	}

	public boolean login() {
		System.out.println("\n\n***** Begin of login *****");

		boolean result = false;

		PoCClientCallbackHandler pocClientCallbackHandler = new PoCClientCallbackHandler(this.getPocCPrincipal());

		try {
			this.setLoginContext(new LoginContext(this.getPocCPrincipal().getValues().get(Constants.DOMAIN_KEY), this.getSubject(), pocClientCallbackHandler));
			this.getLoginContext().login();
			this.setSubject(this.getLoginContext().getSubject());

			// Call authenticator bean
			Context context = null;
			PoCAuthenticator authenticator;
			try {
				context = new InitialContext();
				authenticator = (PoCAuthenticator) context.lookup("PoCAuthenticatorBean/remote");
				System.out.println("Calling PoCAutheticatorBean.login");
				String sessionId = authenticator.login();
				System.out.println("PoCAuthenticator.login sessionId: " + sessionId);
				if (sessionId != null && sessionId.length() > 0) {
					Set<PoCPrincipal> pocPrincipals = this.getSubject().getPrincipals(PoCPrincipal.class);
					// Get the first principal of set
					if (pocPrincipals.size() > 0) {
						PoCPrincipal pocPrincipal = pocPrincipals.toArray(new PoCPrincipal[pocPrincipals.size()])[0];
						pocPrincipal.setLogged(true);
					}
					result = true;
				}
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
		} catch (LoginException loginException) {
			loginException.printStackTrace();
			if (this.getLoginContext() != null) {
				try {
					// Logout from JAAS
					this.getLoginContext().logout();
				} catch (LoginException loginException2) {
					// TODO Auto-generated catch block
					loginException2.printStackTrace();
				}
			}
		} catch (EJBAccessException ejbAccessException) {
			Object objectException = SecurityContextAssociation.getSecurityContext().getData().get("org.jboss.security.exception");
			if (objectException != null) {
				objectException.toString();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			
		}
		
		System.out.println("***** End of login *****\n\n");
		return result;
	}

	public void logout() {
		System.out.println("\n\n***** Begin of logout *****");
		try {
			this.getLoginContext().logout();
		} catch (LoginException loginContext) {
			loginContext.printStackTrace();
		}
		System.out.println("***** End of logout *****\n\n");
	}

	public void printInfo() {
		System.out.println("\n\n***** Begin of printInfo *****");
		Context context = null;
		PoCAuthenticator authenticator;
		try {
			context = new InitialContext();
			authenticator = (PoCAuthenticator) context.lookup("PoCAuthenticatorBean/remote");
			System.out.println("Calling PoCAutheticatorBean");
			System.out.println("PoCAuthenticator.info: " + authenticator.getInfo());
		} catch (NamingException namingException) {
			namingException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
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
		System.out.println("***** End of printInfo *****\n\n");
	}

	public void callHelloBean() {
		System.out.println("\n\n***** Begin of callHelloBean *****");
		Context context = null;
		Hello hello;
		try {
			context = new InitialContext();
			hello = (Hello) context.lookup("HelloBean/remote");
			System.out.println("Calling HelloBean");
			System.out.println("HelloBean says: " + hello.sayHello());
			try {
				System.out.println("HelloBean says: " + hello.sayHelloByPrincipalValues());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} catch (NamingException namingException) {
			// TODO Auto-generated catch block
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
		System.out.println("***** End of callHelloBean *****\n\n");
	}

}
