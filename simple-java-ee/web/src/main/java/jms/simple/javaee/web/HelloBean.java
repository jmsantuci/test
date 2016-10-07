/**
 * 
 */
package jms.simple.javaee.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import jms.simple.javaee.domain.Hello;
import jms.simple.javaee.services.HelloServiceLocal;

/**
 * @author jsantuci
 *
 */
@ManagedBean(name = "hello", eager = true)
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
        Hello hello = new Hello();
        hello.setMessage("Hello World!");

        this.helloService.createMessage(hello);

        String message = "Hello (default message)";
        hello = this.helloService.findMessage(0L);
        if (hello != null && hello.getMessage() != null) {
            message = hello.getMessage();
        }
        return message;
    }

}
