/**
 * 
 */
package jms.jaas.client.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jms.jaas.client.core.BaseClient;
import jms.jaas.security.Constants;


/**
 * @author jsantuci
 *
 */
public class HelloClient extends BaseClient implements Runnable {

	private boolean run = true;

	/**
	 * Autenticação via usuário e senha.
	 */
	public HelloClient(String domain, String name, String key, String value) {
		super(domain, name, key, value);
	}


	/**
	 * @return the stop
	 */
	public boolean isRun() {
		return this.run;
	}

	/**
	 * @param stop the stop to set
	 */
	public void setRun(boolean stop) {
		this.run = stop;
	}


	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (this.isRun()) {
			this.printInfo();
			this.callHelloBean();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Login test using password
		HelloClient helloClient = new HelloClient("poc-security-client", "jms", Constants.CREDENTIAL_KEY, "1234");
		if (helloClient.login()) {
			// Print information about authentication
			helloClient.printInfo();

			// Call hello bean
			helloClient.callHelloBean();

			// Logout from application
			helloClient.logout();

			// Failure: user is not authenticated
			helloClient.printInfo();

			// Ok: HelloBean does not have security associated
			helloClient.callHelloBean();
		}

		// Login test using checksum
		helloClient = new HelloClient("poc-security-client", "jms", Constants.CHECKSUM_KEY, "321");
		if (helloClient.login()) {
			// Print information about authentication
			helloClient.printInfo();

			// Call hello bean
			helloClient.callHelloBean();

			// Logout from application
			helloClient.logout();
		}

	}

}
