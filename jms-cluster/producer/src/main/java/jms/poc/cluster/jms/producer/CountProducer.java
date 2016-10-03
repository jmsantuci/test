/**
 * 
 */
package jms.poc.cluster.jms.producer;

import javax.ejb.Remote;
import javax.jms.JMSException;

import jms.poc.cluster.jms.model.Count;
import jms.poc.cluster.jms.model.MessageCount;

/**
 * @author jsantuci
 *
 */
@Remote
public interface CountProducer {

	void publish(Count count) throws JMSException;

	void publish(MessageCount messageCounter, int repeat) throws JMSException;
}
