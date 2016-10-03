/**
 * 
 */
package jms.poc.cluster.jms.model;

import java.io.Serializable;

/**
 * @author jsantuci
 *
 */
public class Count implements Serializable {

	/**
	 * Default serial version UID. 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Value of count
	 */
	private int count;

	public Count() {
		super();
	}

	/**
	 * Constructor that receives count.
	 * 
	 * @param count
	 */
	public Count(int count) {
		super();
		this.count = count;
	}

	/**
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	

}
