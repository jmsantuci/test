/**
 * 
 */
package jms.network.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;

/**
 * @author jsantuci
 *
 */
@NamedQueries(value={
		@NamedQuery(
				name="findAllCountings",
				query="SELECT cnt FROM Counting cnt")})
@Entity(name="Counting")
public class Counting extends RootEntity {

	private int initialValue;
	private int finalValue;

	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="EQUIPMENT_ID")
	private Equipment equipment;

	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="CABLE_ID")
	private Cable cable;

	/**
	 * Default constructor.
	 */
	public Counting() {
		super();
	}

	public int getInitialValue() {
		return this.initialValue;
	}

	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}

	public int getFinalValue() {
		return this.finalValue;
	}

	public void setFinalValue(int finalValue) {
		this.finalValue = finalValue;
	}

	/**
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return this.equipment;
	}

	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the cable
	 */
	public Cable getCable() {
		return this.cable;
	}

	/**
	 * @param cable the cable to set
	 */
	public void setCable(Cable cable) {
		this.cable = cable;
	}

}
