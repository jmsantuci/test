package jms.poc.tac.security;

import java.util.Iterator;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import jms.poc.cluster.domain.UserBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session Bean implementation class AccessController
 */
@Stateless
@LocalBean
@PermitAll
public class AccessController {

	/**
	 * Logger instance
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(AccessController.class);

	/**
	 * Collection of system users. 
	 */
	@EJB
	private UserBean userBean;
	
    /**
     * Default constructor. 
     */
    public AccessController() {
        super();
    }

    /**
     * Checks if user is blocked or not.
     * 
     * @param userName user name
     * @return
     */
    public boolean isUserBlocked(String userName) {
    	LOGGER.debug("User logged. Checking status.");
    	boolean result = true;

    	if (userName != null && userName.length() > 0) {
	    	try {
				Subject subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
				if (subject != null) {
					UserPrincipal userPrincipal = null;
					Iterator<UserPrincipal> userPrincipalIterator = subject.getPrincipals(UserPrincipal.class).iterator();
					if (userPrincipalIterator.hasNext()) {
						userPrincipal = userPrincipalIterator.next();
						if (userPrincipal != null) {
							if (userName.equals(userPrincipal.getName())) {
								result = this.userBean.getUser(userName) == null;
							} else {
								LOGGER.error("User is not equals of authentication user");
							}
						} else {
							LOGGER.error("Invalid user in Principal");
						}
					}
				}
			} catch (PolicyContextException policyContextException) {
				LOGGER.error("Error on getting policy context", policyContextException);
			}
    	} else {
    		LOGGER.error("User name is not valid");
    	}

    	LOGGER.debug("Is user blocked ? " + result);
    	return result;
    }
}
