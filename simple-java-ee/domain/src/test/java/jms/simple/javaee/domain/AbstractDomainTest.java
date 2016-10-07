package jms.simple.javaee.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public abstract class AbstractDomainTest {

	/**
     * EntityManagerFactory
     */
    protected static EntityManagerFactory entityManagerFactory;

    /**
     * EntityManager
     */
    protected static EntityManager entityManager;

    /**
     * Construtor sem argumentos.
     */
    public AbstractDomainTest() {
        super();
    }

    /**
     * Cria o Entity Manager Factory
     * 
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	@SuppressWarnings("unused")
		Object test = Persistence.createEntityManagerFactory("simple-test-pu");
        entityManagerFactory = Persistence.createEntityManagerFactory("simple-test-pu");
    }

    /**
     * Fecha o EntityManagerFactory
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        entityManagerFactory.close();
    }

    /**
     * Cria o Entity Manager e inicia a transação
     * 
     */
    @Before
    public void setUp() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    /**
     * Comita a transação e fecha o EntityManager.
     * 
     */
    @After
    public void tearDown() throws Exception {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
