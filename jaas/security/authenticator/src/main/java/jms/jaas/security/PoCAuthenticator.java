/**
 * 
 */
package jms.jaas.security;

/**
 * @author jsantuci
 *
 */
public interface PoCAuthenticator {

	String login();
	String getInfo();
	boolean logout();

}
