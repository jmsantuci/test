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
				name="findAllCables",
				query="SELECT cbl FROM Cable cbl"),
		@NamedQuery(
				name="findCableByCode",
				query="SELECT cbl FROM Cable cbl WHERE cbl.code = :code"),
		@NamedQuery(
				name="findCableByName",
				query="SELECT cbl FROM Cable cbl WHERE cbl.name = :name")})
@Entity(name="Cable")
public class Cable extends RootEntity {

	private String code;
	private String name;
	private int type;

	@OneToMany(mappedBy="cable")
	private List<Counting> countings = new ArrayList<Counting>();

	@OneToMany(mappedBy="cable")
	private List<Splice> splices = new ArrayList<Splice>();

	/**
	 * Default constructor.
	 */
	public Cable() {
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
		counting.setCable(this);
	}

	public List<Splice> getSplices() {
		return this.splices;
	}

	public void setSplices(List<Splice> splices) {
		this.splices = splices;
	}

	public void addSplice(Splice splice) {
		this.getSplices().add(splice);
		splice.setCable(this);
	}
}
