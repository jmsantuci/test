/**
 * 
 */
package jms.poc.cluster.jms.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jms.poc.cluster.jms.model.Count;
import jms.poc.cluster.jms.producer.CountProducer;

/**
 * @author jsantuci
 *
 */
public class CountProducerClient implements Runnable {

	private boolean run = true;

	private int count = 10;

	/**
	 * Default constructor.
	 */
	public CountProducerClient() {
		super();
	}

	@Override
	public void run() {
		Context context = null;
		try {
			context = new InitialContext();
			CountProducer countProducer = (CountProducer) context.lookup("CountProducerBean/remote");
			System.out.println("Lookup ok!");
			System.out.println("Publishing ");

			int index = 0;
			while (this.run) {
                try {
					countProducer.publish(new Count(this.count));
					index++;
					System.out.print('.');
				} catch (JMSException e) {
					e.printStackTrace();
				}
				// To balancer ???
				countProducer = (CountProducer) context.lookup("CountProducerBean/remote");
			}

			System.out.println("\n\nStopped!!");
			System.out.println("***********************");
			System.out.println("\tTotal: " + (index * this.count));
			System.out.println("\n\n");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CountProducerClient clientProducer = new CountProducerClient();
		if (args.length > 0) {
			clientProducer.count = Integer.parseInt(args[0]);
		}

		Thread thread = new Thread(clientProducer);
		thread.start();

		System.out.println("Enter something to stop: ");
			 
		try {
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    bufferRead.readLine();
	 
		    System.out.println("Stopping...");
		    clientProducer.run = false;
		} catch(IOException e) {
			e.printStackTrace();
		}

		try {
			thread.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
