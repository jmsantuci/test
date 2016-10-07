/**
 * 
 */
package jms.jaas.security;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.callback.SecurityAssociationCallback;

/**
 * LoginModule que trata as especificidades do JBoss. A idéia é obter o Principal usado no cliente
 * a partir das APIs do JBoss e deixá-lo preparado para o próximo LoginModule configurado.
 * 
 * Esta classe contém uma série de testes que foram usados para obter tal Principal, porém
 * eles foram descartados por não funcionarem, por serem APIs deprecated etc. 
 * 
 * Este módulo deve ser configurado como required
 * 
 * @author jsantuci
 * @deprecated
 */
public class PoCJBossLoginModule extends PoCBaseLoginModule {

    /**
     * Defualt Constructor.
     */
    public PoCJBossLoginModule() {
        super();
    }

    /**
     * Teste para obter o Principal a partir de uma callback do JBoss. O problema é que para pegar
     * o principal é necessário usar o método getPrincipal da própria callback, o que deixa o 
     * código acoplado com o JBoss.
     * 
     * @param callbackHandler
     */
    private void initializePocCPrincipal(CallbackHandler callbackHandler) {
    	SecurityAssociationCallback securityAssociationCallback = new SecurityAssociationCallback();
        Callback[] callbacks = new Callback[]{securityAssociationCallback};
        try {
            callbackHandler.handle(callbacks);
            System.out.println("Principal getted from CallbackHandler");
            Principal principal = securityAssociationCallback.getPrincipal();
            if (principal instanceof PoCPrincipal) {
            	this.setPocPrincipal((PoCPrincipal) principal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método contém uma sequencia de testes para obter o Principal que foi usada no LoginModule do cliente.
     * 
     */
    private void initializePocCPrincipalTest() {

//    	Set<PoCPrincipal> pocPrincipals = this.getSubject().getPrincipals(PoCPrincipal.class);
//        // Get the first principal of set
//        if (pocPrincipals.size() > 0) {
//            System.out.println("Getting principal from subject");
//            this.setPocPrincipal(pocPrincipals.toArray(new PoCPrincipal[pocPrincipals.size()])[0]);
//        }

//        Principal principal = null;
        // Retorna o SimplePrincipal
//        Principal principal = SecurityAssociation.getPrincipal();
//        System.out.println("Principal by: SecurityAssociation.getPrincipal(): " + principal);

        // Retorna o SimplePrincipal
//        principal = SecurityContextAssociation.getSecurityContext().getUtil().getUserPrincipal();
//        System.out.println("Principal by: SecurityContextAssociation.getSecurityContext().getUtil().getUserPrincipal(): " + principal);
//        if (principal != null) {
//            
//            Set<PoCPrincipal> pocPrincipals = subject.getPrincipals(PoCPrincipal.class);
//            if (pocPrincipals != null && pocPrincipals.size() > 0 && principal instanceof PoCPrincipal) {
//                // Segunda opção: adicionar o principal obtido via SecurityAssociation no subject
//                PoCPrincipal pocPrincipal = (PoCPrincipal) pocPrincipals.toArray()[0];
//                pocPrincipal.getValues().addAll(((PoCPrincipal) principal).getValues());
//            }
//        }

        // Retorna o PoCPrincipal
//        Set<PoCPrincipal> pocPrincipals = SecurityAssociation.getSubject().getPrincipals(PoCPrincipal.class);

//        if (this.pocPrincipal == null) {
//            System.out.println("PoCPrincipal is null :(");
//            SecurityContext securityContext = SecurityContextAssociation.getSecurityContext();
//            if (securityContext != null) {
//                Set<PoCPrincipal> pocPrincipals = securityContext.getUtil().getSubject().getPrincipals(PoCPrincipal.class);
//                if (pocPrincipals != null && pocPrincipals.size() > 0) {
//                    // Simple authentication
//                    result = true;
//                    String sessionId = this.generateSessionId();
//                    for (PoCPrincipal pocPrincipal : pocPrincipals) {
//                        pocPrincipal.setSessionId(sessionId);
//                        subject.getPrincipals().add(pocPrincipal);
//                    }
//                }
//            }
//        } else {
//            System.out.println("PoCPrincipal is not null :)");
//        }
    }

//  Este método foi uma iniciativa para obter o Principal a partir da callback do JBoss mas usando reflection,
//  para diminuir acoplamento
//  private void initializePocCPrincipal(CallbackHandler callbackHandler) {
//      Object callbackClass = this.getOptions().get("callbackClass");
//      if (callbackClass != null) {
//          String callbackClassName = callbackClass.toString();
//          if (callbackClassName.length() > 0) {
//              try {
//                  Callback[] callbacks = new Callback[]{(Callback) Class.forName(callbackClassName).newInstance()};
//                  try {
//                      callbackHandler.handle(callbacks);
//                      // TODO call getPrincipal and getCredential using reflection
//                      if (this.getPocPrincipal() != null) {
//                          System.out.println("Principal getted from CallbackHandler");
//                      }
//                  } catch (IOException e) {
//                      e.printStackTrace();
//                  } catch (UnsupportedCallbackException e) {
//                      e.printStackTrace();
//                  }
//              } catch (InstantiationException e) {
//                  e.printStackTrace();
//              } catch (IllegalAccessException e) {
//                  e.printStackTrace();
//              } catch (ClassNotFoundException e) {
//                  e.printStackTrace();
//              }
//          }
//      }
//  }
    
    /**
     * Este método obtém o Principal a partir do contexto de segurança do JBoss.
     * Esta foi a única forma de se obter o Principal que foi usado no cliente.
     * 
     */
    protected void initializePocCPrincipal() {

    	Object principal = this.getSharedState().get("javax.security.auth.login.name");
    	if (principal instanceof PoCPrincipal) {
    		this.setPocPrincipal((PoCPrincipal) principal);
    	} else { // It´s null or a not PoCPrincipal instance
	        SecurityContext securityContext = SecurityContextAssociation.getSecurityContext();
	        if (securityContext != null) {
	        	Set<PoCPrincipal> pocPrincipals = securityContext.getUtil().getSubject().getPrincipals(PoCPrincipal.class);
	            if (pocPrincipals.size() > 0) {
	                System.out.println("Getting principal from SecurityContextAssociation");
	                this.setPocPrincipal(pocPrincipals.toArray(new PoCPrincipal[pocPrincipals.size()])[0]);
	            }
	        } else {
	            System.out.println("SecurityContext is null :)");
	        }
    	}
    }

    private void initializeCredential() {
//      A credential retornada pelo subject é nula
//      Set<String> privateCredentials = this.subject.getPrivateCredentials(String.class);
//      Iterator<String> iterator = privateCredentials.iterator();
//      while (iterator.hasNext()) {
//          String credential = iterator.next();
//          System.out.println("Credential: " + credential);
//      }
    	
    	Object credential = SecurityContextAssociation.getSecurityContext().getUtil().getCredential();
    	this.setCredential((String) credential);
    }

    public void printInitialize(CallbackHandler callbackHandler) {
        System.out.println("Initialize info");
        System.out.println("\tSubject: " + this.getSubject());
        System.out.println("\tCallbackHandler.class: " + callbackHandler.getClass());
        System.out.println("\tsharedState: " + this.getSharedState());
        System.out.println("\toptions: " + this.getOptions());
        System.out.println("\tjavax.security.auth.login.name: " + this.getSharedState().get("javax.security.auth.login.name"));
        System.out.println("\tjavax.security.auth.login.password: " + this.getSharedState().get("javax.security.auth.login.password"));
    }

    /**
     * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
     */
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        System.out.println("Begin of PoCJBossLoginModule.initialize");

        this.setSubject(subject);
        this.setSharedState(sharedState);
        this.setOptions(options);

        this.printInitialize(callbackHandler);
        
        this.initializePocCPrincipal();
        this.initializeCredential();

        // Seta variáveis compartilhadas
        this.getSharedState().put("javax.security.auth.login.name", this.getPocPrincipal());
   		this.getSharedState().put("javax.security.auth.login.password", this.getCredential());

   		System.out.println("Begin of PoCJBossLoginModule.initialize");
    }

    /**
     * @see javax.security.auth.spi.LoginModule#login()
     */
    @Override
    public boolean login() throws LoginException {
        System.out.println("PoCJBossLoginModule.login");
        boolean result = false;

        if (this.getPocPrincipal() != null) {
            // Retorna ok somente se existir o Pricipal criado pelo cliente.
            result = true;
        }
        return result;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    @Override
    public boolean commit() throws LoginException {
        System.out.println("PoCJBossLoginModule.commit");
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#abort()
     */
    @Override
    public boolean abort() throws LoginException {
        System.out.println("PoCJBossLoginModule.abort");
        return true;
    }

    /**
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    @Override
    public boolean logout() throws LoginException {
        System.out.println("PoCJBossLoginModule.logout");
//        SecurityContextAssociation.clearSecurityContext();
//        SecurityContextAssociation.getSecurityContext().getSecurityManagement().
//        SecurityContextAssociation.getSecurityContext().getSecurityDomain().
//        SecurityContextAssociation.getSecurityContext().getUtil().
        
        return true;
    }

    public static void callLogout(String domain, String userName) {
        Principal user = new SimplePrincipal(userName);
        PoCJBossLoginModule.callLogout(domain, user);
        
    }

    public static void callLogout(String domain, Principal user) {
        ObjectName jaasMgr;
        try {
            jaasMgr = new ObjectName("jboss.security:service=JaasSecurityManager");
            Object[] params = {domain, user};
            String[] signature = {"java.lang.String", Principal.class.getName()};
            MBeanServer server = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
            try {
                server.invoke(jaasMgr, "flushAuthenticationCache", params, signature);
            } catch (InstanceNotFoundException e) {
                e.printStackTrace();
            } catch (ReflectionException e) {
                e.printStackTrace();
            } catch (MBeanException e) {
                e.printStackTrace();
            }
        } catch (MalformedObjectNameException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }
        
    }

}
