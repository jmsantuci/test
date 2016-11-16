/**
 * 
 */
package jms.poc.jms.consumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author jsantuci
 *
 */
@MessageDriven(
		mappedName="/queue/TextQueue",
		activationConfig = { @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
				             @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/TextQueue")})
public class TextConsumerBean implements MessageListener {

	/**
	 * Default constructor
	 */
	public TextConsumerBean() {
		super();
	}

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				String messageText = textMessage.getText();
				System.out.println("Message: " + messageText);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
