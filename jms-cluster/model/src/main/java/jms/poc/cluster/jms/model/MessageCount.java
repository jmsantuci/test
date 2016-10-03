/**
 * 
 */
package jms.poc.cluster.jms.model;

import java.io.Serializable;

/**
 * @author jsantuci
 *
 */
public class MessageCount implements Serializable {

	/**
	 * Serial version identification.
	 */
	private static final long serialVersionUID = 1L;

	private int clientId;
	private long messageId;
	private int repetition;
	private String prodId;
	private String consId;
	
	/**
	 * Default constructor.
	 */
	public MessageCount() {
		super();
	}

	/**
	 * Constructor receiving all parameters.
	 * 
	 * @param clientId
	 * @param messageId
	 * @param repetition
	 * @param prodId
	 * @param consId
	 */
	public MessageCount(int clientId, long messageId, int repetition, String prodId, String consId) {
		super();
		this.clientId = clientId;
		this.messageId = messageId;
		this.repetition = repetition;
		this.prodId = prodId;
		this.consId = consId;
	}

	/**
	 * Constructor that receives clientId and messageId.
	 * 
	 * @param clientId
	 * @param messageId
	 * @param repetition
	 * @param prodId
	 * @param consId
	 */
	public MessageCount(int clientId, long messageId) {
		super();
		this.clientId = clientId;
		this.messageId = messageId;
	}
	
	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return this.clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the messageId
	 */
	public long getMessageId() {
		return this.messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the repeat
	 */
	public int getRepetition() {
		return this.repetition;
	}

	/**
	 * @param repetition the repeat to set
	 */
	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	/**
	 * @return the prodId
	 */
	public String getProdId() {
		return this.prodId;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
	 * @return the consId
	 */
	public String getConsId() {
		return this.consId;
	}

	/**
	 * @param consId the consId to set
	 */
	public void setConsId(String consId) {
		this.consId = consId;
	}

}
