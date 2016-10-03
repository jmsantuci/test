/**
 * 
 */
package jms.network.rules;

/**
 * @author jsantuci
 *
 */
public class RuleMessage {

	private String message;
	private MessageType type;

	/**
	 * Default contructor.
	 */
	public RuleMessage() {
		super();
	}


	/**
	 * Constructor using fields.
	 * 
	 * @param message rule message
	 * @param type rule type
	 */
	public RuleMessage(String message, MessageType type) {
		super();
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the type
	 */
	public MessageType getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

}
