/**
 * 
 */
package jms.camel.cdi.services;

import org.apache.camel.Body;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * User: charlesmoulliard
 * Date: 17/02/12
 */
@Named
public class HelloWorld {
	
	@EJB
	private HelloServiceLocal helloServiceLocal;

    public String sayHello(@Body String message) {
    	String result = " **** Ops, Defaul Message ***";
        result = this.helloServiceLocal.sayHello(message);
//		try {
//			InitialContext initialContext = new InitialContext();
//			HelloServiceLocal helloServiceLocal = (HelloServiceLocal) initialContext.lookup("java:module/HelloServiceBean!jms.camel.cdi.services.HelloServiceLocal");
//			result = helloServiceLocal.sayHello(message);
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return result;
    }
}
