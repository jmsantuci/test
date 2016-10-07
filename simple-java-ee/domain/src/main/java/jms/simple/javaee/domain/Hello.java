package jms.simple.javaee.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Hello
 *
 */
@NamedQuery(name="findAllHello", query="SELECT h FROM Hello h ")
@Entity(name = "Hello")
public class Hello implements Serializable {

    
    private static final long serialVersionUID = 1L;

    public Hello() {
        super();
    }

    /**
     * Message code.
     */
    @Id
    @SequenceGenerator(name = "HELLO_SEQ", sequenceName = "HELLO_SEQ", initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HELLO_SEQ")
    private Long code;

    /**
     * Message.
     */
    @Column(length = 100)
    private String message;

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

    
}
