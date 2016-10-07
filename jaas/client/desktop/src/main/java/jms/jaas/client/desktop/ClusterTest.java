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
public class ClusterTest extends BaseClient implements Runnable {

	private boolean run = true;
	
	/**
	 * 
	 */
	public ClusterTest(String domain, String name, String key, String value) {
		super(domain, name, key, value);
	}

	/**
	 * @return the run
	 */
	public boolean isRun() {
		return this.run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(boolean run) {
		this.run = run;
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (this.login()) {
			while (this.isRun()) {
				// Print information about authentication
				this.printInfo();
				// Call hello bean
				this.callHelloBean();
			}
			this.logout();
		}
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ClusterTest clusterTest = new ClusterTest("poc-security-client", "jms", Constants.CREDENTIAL_KEY, "123");
		Thread thread = new Thread(clusterTest);
		thread.start();

		System.out.println("Enter something to stop: ");

		try {
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    bufferRead.readLine();
		    clusterTest.setRun(false);
	 
		    System.out.println("Stopping...");
		    
		} catch(IOException e) {
			e.printStackTrace();
		}

		try {
			thread.join();
		} catch (InterruptedException interruptedException) {
			// TODO Auto-generated catch block
			interruptedException.printStackTrace();
		}
	}
}
