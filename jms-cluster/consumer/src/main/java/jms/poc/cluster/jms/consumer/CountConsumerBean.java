/**
 * 
 */
package jms.poc.cluster.jms.consumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import jms.poc.cluster.jms.model.Count;
import jms.poc.cluster.jms.model.MessageCount;

/**
 * @author jsantuci
 *
 */
@MessageDriven(
		mappedName="/queue/CountQueue",
		activationConfig = { @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
				             @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/CountQueue")})
public class CountConsumerBean implements MessageListener {

	private static String serverId;

	static {
		synchronized (CountConsumerBean.class) {
			CountConsumerBean.serverId = System.getProperty("serverId");
		}
	}
	
	@EJB
	private Counter counterBean;

	@EJB
	private MessageCounter messageCounter;

	/**
	 * Default constructor
	 */
	public CountConsumerBean() {
		super();
	}

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				Object object = objectMessage.getObject();
				if (object instanceof Count) {
					Count count = (Count) objectMessage.getObject();
					counterBean.add(count.getCount());
				} else if (object instanceof MessageCount) {
					MessageCount messageCount = (MessageCount) object;
					messageCount.setConsId(CountConsumerBean.serverId);
					messageCounter.add(messageCount);
				} else {
					System.out.println("Unknow message class");
				}

			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
