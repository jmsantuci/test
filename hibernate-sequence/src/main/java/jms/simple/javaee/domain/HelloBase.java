/**
 * 
 */
package jms.simple.javaee.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author jsantuci
 *
 */
@Entity(name = "HelloBase")
@Table(name = "HELLO_BASE")
public class HelloBase extends HelloAbstract {

	/**
	 * Default constructor.
	 */
	public HelloBase() {
		super();
	}

}
