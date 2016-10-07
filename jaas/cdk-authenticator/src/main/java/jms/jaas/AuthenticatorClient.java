/**
 * 
 */
package jms.jaas;

import java.rmi.RemoteException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;

import br.com.cpqd.accesscontrol.services.AuthenticatorRemote;
import br.com.cpqd.sagre.security.argus.principal.RealmPrincipal;

/**
 * @author jsantuci
 *
 */
public class AuthenticatorClient {

	/**
	 * Default constructor.
	 */
	public AuthenticatorClient() {
		super();
	}

	public static HashSet<RealmPrincipal> createPrincipals() {
		HashSet<RealmPrincipal> principals = new HashSet<RealmPrincipal>();

        RealmPrincipal rp = new RealmPrincipal("DM_ITCONTROL_JMS@des102"); // Domain

//        rp.putAll(rContext); A princ�pio est� vazio o rContext
        rp.put(RealmPrincipal.DOMAIN, "DM_ITCONTROL_JMS@des102");
        rp.put(RealmPrincipal.IDENTITY, "jms");
        rp.put(RealmPrincipal.CREDENTIAL, "cpqdcpqd");

//        if (applName != null) {
//            rp.loadProductInfo(applName);
//        }
        String solutionName = "CPqD-ITControl";
        String solutionVersion = "8.21.1.0";
        String applName = "WebDeskMapReport";
        String applVersion = "8.21.1.0";
        String productContext = solutionName + "/" + solutionVersion + "/";

        rp.put("solutionName",  solutionName);
        rp.put("solutionVersion",  solutionVersion);
        
//        rp.put("applName", "WebDeskMapReport");
//        rp.put("applVersion", "8.21.1.0");
//        rp.put("moduleId", new Long("83"));
//        productContext = productContext + applName + "/" + applVersion + "/";
        
        rp.put("productContext", productContext);

        principals.add(rp);
		return principals;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		try {
//			SecurityClient client = SecurityClientFactory.getSecurityClient();
//			client.setSimple("test", "test");
//			client.login();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		Set<String> pubCredentials = new HashSet<String>();
		Set<String> privCredentials= new HashSet<String>();
		Set<RealmPrincipal> principals = AuthenticatorClient.createPrincipals();

		Subject subject = new Subject(false, principals, pubCredentials, privCredentials);
		SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler("jms", "cpqdcpqd");
		LoginContext loginContext = null;
		try {
//			loginContext = new LoginContext("cdk-security-client", simpleCallbackHandler);
//			loginContext.login();
			
			Context context = null;
			try {
				context = new InitialContext();
				AuthenticatorRemote authenticatorRemote = (AuthenticatorRemote) context.lookup("cpqd-itcontrol-security-ear/cpqd-security-accesscontrol-ejb/AuthenticatorBean!br.com.cpqd.accesscontrol.services.AuthenticatorRemote");
				authenticatorRemote.checkCountLockedOrExpiredPassword("jms", "IT_CONTROL_EAP_62@des102");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (loginContext != null) {
				try {
					loginContext.logout();
				} catch (LoginException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
