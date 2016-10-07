package jms.camel.cdi.services;

import javax.ejb.Stateless;


/**
 * Session Bean implementation class HelloServiceBean
 */
@Stateless
public class HelloServiceBean implements HelloServiceRemote, HelloServiceLocal {

    /**
     * Default constructor. 
     */
    public HelloServiceBean() {
        super();
    }
   
    /**
     * @see jms.camel.cdi.services.HelloService#sayHello()
     */
    @Override
    public String sayHello(String message) {
        return ">> Hello " + message + " user.";
    }
    
}
