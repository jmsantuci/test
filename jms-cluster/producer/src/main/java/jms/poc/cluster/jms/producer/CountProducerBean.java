/**
 * 
 */
package jms.poc.cluster.jms.producer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import jms.poc.cluster.jms.model.Count;
import jms.poc.cluster.jms.model.MessageCount;

/**
 * @author jsantuci
 *
 */
@Stateless
public class CountProducerBean implements CountProducer {

	@Resource(mappedName= "conscluster/XAConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName="conscluster/queue/CountQueue")
	private Queue queue;

	private static String serverId;

	static {
		synchronized (CountProducerBean.class) {
			CountProducerBean.serverId = System.getProperty("serverId");
		}
	}
	/**
	 * Default constructor.
	 */
	public CountProducerBean() {
		super();
	}

	/**
	 * @see jms.poc.cluster.jms.producer.CountProducer#publish(jms.poc.cluster.jms.model.Count)
	 */
	@Override
	public void publish(Count count) throws JMSException {
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(queue);

			ObjectMessage message = session.createObjectMessage();

			Count countToSend = new Count(1);
			for (int i = 0; i < count.getCount(); i++) {
				message.setObject(countToSend);
				producer.send(message);
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}

	}

	/**
	 * @see jms.poc.cluster.jms.producer.CountProducer#publish(jms.poc.cluster.jms.model.MessageCount, int)
	 */
	@Override
	public void publish(MessageCount messageCounter, int count) throws JMSException {
		messageCounter.setProdId(CountProducerBean.serverId);

		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(queue);

			ObjectMessage message = session.createObjectMessage();

			for (int i = 0; i < count; i++) {
				messageCounter.setRepetition(i);
				message.setObject(messageCounter);
				producer.send(message);
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
