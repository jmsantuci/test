/**
 * 
 */
package jms.jaas.client.desktop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jms.jaas.security.Constants;


/**
 * @author jsantuci
 *
 */
public class ThreadHelloClient extends HelloClient implements Runnable {

	private boolean run = true;

	/**
	 * Autenticação via usuário e senha.
	 */
	public ThreadHelloClient(String domain, String name, String key, String value) {
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
		ThreadHelloClient helloClient = new ThreadHelloClient("poc-security-client", "jms", Constants.CREDENTIAL_KEY, "123");

		if (helloClient.login()) {
			Thread threadOne = new Thread(helloClient);
			Thread threadTwo = new Thread(helloClient);

			threadOne.start();
			threadTwo.start();

			System.out.println("Enter something to stop: ");

			try {
			    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			    bufferRead.readLine();
			    helloClient.setRun(false);
		 
			    System.out.println("Stopping...");

			    helloClient.logout();
			    
			} catch(IOException e) {
				e.printStackTrace();
			}

			try {
				threadOne.join(1000);
				threadTwo.join(1000);
			} catch (InterruptedException interruptedException) {
				// TODO Auto-generated catch block
				interruptedException.printStackTrace();
			}
		}
	}

}
