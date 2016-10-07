/**
 * 
 */
package jms.simple.javaee.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.Query;

import jms.simple.javaee.domain.Hello;

import org.junit.Test;



/**
 * @author jsantuci
 *
 */
public class HelloTest extends AbstractDomainTest {

    /**
     * Construtor sem argumentos.
     */
    public HelloTest() {
        super();
    }

//    @Test
//    public void persist() throws Exception {
//        Hello hello = new Hello();
//        hello.setMessage("Hello World");
//        entityManager.persist(hello);
//        assertTrue("The entity does not belong to the persistence context", entityManager.contains(hello));
//        assertNotNull("Entidade não encontrada", entityManager.find(Hello.class, 50L));
//    }

    @Test
    public void sequence() throws Exception {
//    	long start = 5200;
    	for (int i = 0; i < 2; i++) {
	        Hello hello = new Hello();
	        hello.setMessage("Hello World: " + i);
	        entityManager.persist(hello);
	        System.out.println("Hello, i=" + i + ", code=" + hello.getCode());
	        for (int j = 0; j < 51; j++) {
	        	HelloMsg helloMsg = new HelloMsg();
	        	helloMsg.setMessage("Hello message: " + j);
	        	hello.addHelloMsg(helloMsg);
	        	entityManager.persist(helloMsg);
	        	System.out.println("HelloMsg, j=" + j + ", code=" + helloMsg.getCode());
	        }
//	        assertTrue("The entity does not belong to the persistence context", entityManager.contains(hello));
//	        assertNotNull("Entidade não encontrada", entityManager.find(Hello.class, start + i));
    	}
    }
    
//    @SuppressWarnings("unchecked")
//	@Test
//    public void findAll() throws Exception {
//        Query query = entityManager.createNamedQuery("findAllHello");
//        List<Hello> helloList = (List<Hello>) query.getResultList();
//        assertNotNull("Result List is null", helloList);
//        assertEquals("Result list size is not equals to 1", 1, helloList.size());
//    }
}
