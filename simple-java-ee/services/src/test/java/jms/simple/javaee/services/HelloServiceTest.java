/**
 * 
 */
package jms.simple.javaee.services;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import jms.simple.javaee.domain.Hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jsantuci
 *
 */
public class HelloServiceTest extends AbstractServicesTest {

    private HelloServiceLocal helloService = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.helloService = (HelloServiceLocal) context.lookup("ejb/HelloServiceBeanLocal");
        assertNotNull("HelloServiceBean was not found", this.helloService);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createMessage() throws Exception {
        Hello hello = new Hello();
        hello.setMessage("Hello World!");
        this.helloService.createMessage(hello);
    }
    
    @Test
    public void findMessage() throws Exception {
        Hello hello = this.helloService.findMessage(0L);
        assertNotNull("Hello object not found!", hello);
        assertEquals("First message is wrong", "Hello World!", hello.getMessage());

    }

    @Test
    public void findAll() throws Exception {
        List<Hello> helloList = this.helloService.findAll();
        assertNotNull("Result List is null", helloList);
        assertEquals("Result list size is not equals to 1", 1, helloList.size());
    }
}
