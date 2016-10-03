/**
 * 
 */
package jms.network.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

/**
 * Root entity of this domain.
 * 
 * @author José Marcelo Santuci
 *
 */
@Inheritance(strategy=InheritanceType.JOINED)
@Entity
public abstract class RootEntity {

	/**
	 * Object identification.
	 */
	@Id
	@SequenceGenerator(name="ROOT_ENTITY_SEQ", sequenceName="ROOT_ENTITY_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROOT_ENTITY_SEQ")
	private Long id;

	/**
	 * No-arg constructor.
	 */
	public RootEntity() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/** TODO improve this method
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RootEntity other = (RootEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
