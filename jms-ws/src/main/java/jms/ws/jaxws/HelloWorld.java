/**
 * 
 */
package jms.ws.jaxws;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * @author jsantuci
 *
 */
@WebService
@SOAPBinding(style=Style.RPC)
@Remote
public interface HelloWorld {

	@WebMethod
	String getHelloWorld(String name);
	
}
