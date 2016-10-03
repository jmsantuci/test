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

import jms.poc.cluster.jms.model.MessageCount;
import jms.poc.cluster.jms.producer.CountProducer;

/**
 * @author jsantuci
 *
 */
public class MessageCounterProducerClient implements Runnable {

	private boolean run = true;

	private int count = 10;

	private int clientId = 1;

	/**
	 * Default constructor.
	 */
	public MessageCounterProducerClient() {
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
					countProducer.publish(new MessageCount(this.clientId, index), this.count);
					index++;
					System.out.print('.');
				} catch (JMSException e) {
					e.printStackTrace();
				}
                
			}

			System.out.println("\n\nStopped!!");
			System.out.println("***********************");
			System.out.println("\tTotal Published: " + (index * this.count));
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

		MessageCounterProducerClient messageCounterProducerClient = new MessageCounterProducerClient();
		if (args.length > 0) {
			messageCounterProducerClient.count = Integer.parseInt(args[0]);
			if (args.length > 1) {
				messageCounterProducerClient.clientId = Integer.parseInt(args[1]);
			}
		}

		Thread thread = new Thread(messageCounterProducerClient);
		thread.start();

		System.out.println("Enter something to stop: ");
			 
		try {
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    bufferRead.readLine();
	 
		    System.out.println("Stopping...");
		    messageCounterProducerClient.run = false;
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
