/**
 * 
 */
package jms.poc.cluster.jms.scheduler;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.DependsOn;
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
		mappedName="/queue/HQQueue",
		activationConfig = { @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
				             @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/HQQueue")})
@DependsOn("ControllerStartup")
public class HQConsumerBean implements MessageListener {

	/**
	 * Default constructor
	 */
	public HQConsumerBean() {
		super();
	}

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		String textMessage = null;
		if (message instanceof TextMessage) {
			TextMessage objectMessage = (TextMessage) message;
			try {
				textMessage = objectMessage.getText();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			textMessage = message.toString();
		}
		System.out.println("*** Message: " + textMessage);
	}

}
