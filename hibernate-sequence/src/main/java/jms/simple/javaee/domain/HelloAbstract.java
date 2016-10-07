/**
 * 
 */
package jms.simple.javaee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author jsantuci
 *
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "HelloAbstract")
//@DiscriminatorColumn(name="DISC", discriminatorType=DiscriminatorType.STRING, length=20)
@Table(name = "HELLO_ABSTRACT")
public class HelloAbstract {

	/**
	 * Message code.
	 */
	@Id
	@SequenceGenerator(name = "HELLO_ABSTRACT_SEQ", sequenceName = "HELLO_ABSTRACT_SEQ")
//	@SequenceGenerator(name = "HELLO_ABSTRACT_SEQ", sequenceName = "HELLO_ABSTRACT_SEQ", initialValue = 0, allocationSize = 3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HELLO_ABSTRACT_SEQ")
	private Long code;
	/**
	 * Message.
	 */
	@Column(length = 100)
	private String message;

	/**
	 * Default constructor
	 */
	public HelloAbstract() {
		super();
	}

	/**
	 * @return the code
	 */
	public Long getCode() {
	    return this.code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
	    this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
	    return this.message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
	    this.message = message;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HelloAbstract other = (HelloAbstract) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
