/**
 * 
 */
package jms.jaas;

import java.io.Serializable;

/**
 * @author jsantuci
 *
 */
public interface Hello extends Serializable {

	String sayHello();

	String sayHelloByPrincipalValues();
}
