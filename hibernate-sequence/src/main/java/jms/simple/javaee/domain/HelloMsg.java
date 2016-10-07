/**
 * 
 */
package jms.simple.javaee.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author jsantuci
 *
 */
@Entity(name = "HelloMsg")
public class HelloMsg extends HelloBase {

	@ManyToOne(targetEntity = Hello.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "HELLO_ID")
	private Hello parentHello;

	/**
	 * Default constructor
	 */
	public HelloMsg() {
		super();
	}

	/**
	 * @return the parentHello
	 */
	public Hello getParentHello() {
		return this.parentHello;
	}

	/**
	 * @param parentHello the parentHello to set
	 */
	public void setParentHello(Hello parentHello) {
		this.parentHello = parentHello;
	}

}
