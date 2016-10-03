/**
 * 
 */
package jms.network.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author jsantuci
 *
 */
@NamedQueries(value={
		@NamedQuery(
				name="findAllEquipments",
				query="SELECT eq FROM Equipment eq"),
		@NamedQuery(
				name="findEquipmentByCode",
				query="SELECT eq FROM Equipment eq WHERE eq.code = :code"),
		@NamedQuery(
				name="findEquipmentByName",
				query="SELECT eq FROM Equipment eq WHERE eq.name = :name"),
		@NamedQuery(
				name="findEquipmentByCodeAndCable",
				query="SELECT eq FROM Equipment eq JOIN eq.countings cnt JOIN cnt.cable cbl WHERE eq.code = :code and cbl = :cable")})
@Entity(name="Equipment")
public class Equipment extends RootEntity {

	private String code;
	private String name;
	private int type;

	@OneToMany(mappedBy="equipment")
	private List<Counting> countings = new ArrayList<Counting>();

	/**
	 * Default constructor.
	 */
	public Equipment() {
		super();
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the countings
	 */
	public List<Counting> getCountings() {
		return this.countings;
	}

	/**
	 * @param countings the countings to set
	 */
	public void setCountings(List<Counting> countings) {
		this.countings = countings;
	}

	public void addCounting(Counting counting) {
		this.getCountings().add(counting);
		counting.setEquipment(this);
	}
	
}
