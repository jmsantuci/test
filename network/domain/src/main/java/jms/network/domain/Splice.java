/**
 * 
 */
package jms.network.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jsantuci
 *
 */
@NamedQueries(value={
		@NamedQuery(
				name="findAllSplices",
				query="SELECT spl FROM Splice spl"),
		@NamedQuery(
				name="findSpliceByCodeAndZone",
				query="SELECT spl FROM Splice spl WHERE spl.code = :code and spl.zone = :zone")})
@Table(name="T_SPLICE")
@Entity(name="Splice")
public class Splice extends RootEntity {

	private String code;
	private String type;
	private String zone;

	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="CABLE_ID")
	private Cable cable;
	
	/**
	 * Default constructor.
	 */
	public Splice() {
		super();
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Cable getCable() {
		return this.cable;
	}

	public void setCable(Cable cable) {
		this.cable = cable;
	}

}
