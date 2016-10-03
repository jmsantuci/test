/**
 * 
 */
package jms.poc.cluster.jms.consumer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author jsantuci
 *
 */
@Startup
@Singleton
public class CounterBean implements Counter {

	private long count;

	/**
	 * Default constructor
	 */
	public CounterBean() {
		super();
	}

	/**
	 * Initialize count instance variable with zero.
	 */
	@PostConstruct
	public void init() {
		this.count = 0;
	}

	/**
	 * @see jms.poc.cluster.jms.consumer.Counter#getCount()
	 */
	@Override
	public long getCount() {
		System.out.println("CounterBean.get: " + this.count);

		return this.count;
	}

	/**
	 * @see jms.poc.cluster.jms.consumer.Counter#add(int)
	 */
	@Override
	public void add(int value) {
		this.count += value;
	}
}
