/**
 * 
 */
package jms.ws.jaxws;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * @author jsantuci
 *
 */
@Stateless
@WebService(endpointInterface="jms.ws.jaxws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	/**
	 * Default constructor.
	 */
	public HelloWorldImpl() {
		super();
	}

	/**
	 * @see jms.ws.jaxws.HelloWorld#getHelloWorld(java.lang.String)
	 */
	public String getHelloWorld(String name) {
		return "Hello World " + name;
	}

}
