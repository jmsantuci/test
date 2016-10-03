/**
 * 
 */
package jms.ws.jaxws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * @author jsantuci
 *
 */
public class HelloWorldClient {

	/**
	 * Default constructor.
	 */
	public HelloWorldClient() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:9999/ws/hello?wsdl");

		QName qName = new QName("http://jaxws.ws.jms/", "HelloWorldImplService");
		Service service = Service.create(url, qName);
		HelloWorld helloWorld = service.getPort(HelloWorld.class);
		System.out.println(helloWorld.getHelloWorld("JMS - JAX-WS"));
	}

}
