/**
 * 
 */
package jms.test.jee.restful;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jsantuci
 *
 */
@XmlRootElement(name="RequestOutput")
public class RequestOutput {

	/**
	 * Error code.
	 */
	private int errorCode;

	/**
	 * Output data.
	 */
	private String data;

	/**
	 * Default constructor.
	 */
	public RequestOutput() {
		super();
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	
}
