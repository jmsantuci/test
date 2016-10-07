/**
 * 
 */
package jms.camel.cdi.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import jms.camel.cdi.services.HelloServiceLocal;

/**
 * @author jsantuci
 *
 */
@Named(value = "hello")
public class HelloBean {

    @EJB
    private HelloServiceLocal helloService;

    /**
     * Non-argument constructor.
     */
    public HelloBean() {
        super();
    }

    public String getMessage() {
        String message = this.helloService.sayHello("Web Bean");
        return message;
    }

}
