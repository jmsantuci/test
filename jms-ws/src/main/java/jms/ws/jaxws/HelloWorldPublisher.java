/**
 * 
 */
package jms.ws.jaxws;

import javax.xml.ws.Endpoint;

/**
 * @author jsantuci
 *
 */
public class HelloWorldPublisher {

	/**
	 * Default constructor.
	 */
	public HelloWorldPublisher() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());
	}

}
