package jms.simple.javaee.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Hello
 *
 */
@NamedQuery(name="findAllHello", query="SELECT h FROM Hello h ")
@Entity(name = "Hello")
public class Hello extends HelloBase implements Serializable {
    
    private static final long serialVersionUID = 1L;

//    @ElementCollection(targetClass=HelloMsg.class)
	@OneToMany(mappedBy = "parentHello", targetEntity = HelloMsg.class, fetch = FetchType.LAZY)
    private List<HelloMsg> helloMsg = new ArrayList<HelloMsg>();
    
    @Column(name = "VERSION_ID", insertable=false, updatable=false)
    private Long versionId;

    public Hello() {
        super();
    }

	public Long getVersionId() {
		return this.versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	/**
	 * @return the helloMsg
	 */
	public List<HelloMsg> getHelloMsg() {
		return this.helloMsg;
	}

	/**
	 * @param helloMsg the helloMsg to set
	 */
	public void setHelloMsg(List<HelloMsg> helloMsg) {
		this.helloMsg = helloMsg;
	}

	public void addHelloMsg(HelloMsg helloMsg) {
        if (helloMsg != null) {
            this.getHelloMsg().add(helloMsg);
            helloMsg.setParentHello(this);;
        }
    }
}
