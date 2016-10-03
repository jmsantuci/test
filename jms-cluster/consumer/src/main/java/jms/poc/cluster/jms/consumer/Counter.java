package jms.poc.cluster.jms.consumer;

import javax.ejb.Remote;

@Remote
public interface Counter {

	/**
	 * @return the count
	 */
	public abstract long getCount();

	/**
	 * Add the value to instance variable.
	 * 
	 * @param value to add
	 */
	public abstract void add(int value);

}