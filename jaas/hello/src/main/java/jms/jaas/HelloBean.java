package jms.jaas;

import java.security.AccessController;
import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

/**
 * Session Bean implementation class HelloBean
 */
@Stateless
@LocalBean
public class HelloBean implements HelloBeanRemote, HelloBeanLocal {

    /**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private SessionContext sessionContext;

	/**
     * Default constructor. 
     */
    public HelloBean() {
        super();
    }

	/**
	 * @see jms.jaas.Hello#sayHello()
	 */
	@Override
	public String sayHello() {
		return "Hello !!";
	}

	/**
	 * @see jms.jaas.Hello#sayHelloByPrincipalValues()
	 */
	@Override
	public String sayHelloByPrincipalValues() {
		StringBuilder result = new StringBuilder();

		Principal callerPrincipal = this.sessionContext.getCallerPrincipal();
		if (callerPrincipal != null) {
			result.append("Principal by SessionContext: ").append(callerPrincipal).append('\n');
		} else {
			result.append("Principal by SessionContext is null\n");
		}

		Subject subject = Subject.getSubject(AccessController.getContext());
		if (subject != null) {
			for (Principal principal : subject.getPrincipals()) {
				result.append(principal);
				result.append(", ");
			}
		} else {
			result.append("Subject by 'Subject.getSubject(AccessController.getContext()' is null\n");
		}

		try {
			subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
			result.append("Subject by PolicyContext: ").append(subject);
		} catch (PolicyContextException e) {
			result.append("PolycyContextException").append(e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}

    
}
