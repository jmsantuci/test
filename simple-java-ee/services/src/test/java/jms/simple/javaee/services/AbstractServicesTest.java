package jms.simple.javaee.services;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.AfterClass;
import org.junit.BeforeClass;


public abstract class AbstractServicesTest {

    /**
     * Persistence context
     */
    protected static Context context;

    /**
     * Creates an initial context using default properties
     * 
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new InitialContext();
    }


    /**
     * Closes the initial context
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }


}
