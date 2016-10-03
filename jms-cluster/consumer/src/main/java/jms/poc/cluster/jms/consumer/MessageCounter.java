package jms.poc.cluster.jms.consumer;

import javax.ejb.Remote;

import jms.poc.cluster.jms.model.MessageCount;

@Remote
public interface MessageCounter {

	/**
	 * @return the count
	 */
	public abstract long getTotalCount();

	/**
	 * @return the count
	 */
	public abstract long getTotalCount(int clientId);

	/**
	 * Add the value to instance variable.
	 * 
	 * @param value to add
	 */
	public abstract void add(MessageCount messageCount);

}