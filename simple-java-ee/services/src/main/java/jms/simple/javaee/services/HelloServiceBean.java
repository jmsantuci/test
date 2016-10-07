package jms.simple.javaee.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jms.simple.javaee.domain.Hello;


/**
 * Session Bean implementation class HelloServiceBean
 */
@Stateless
public class HelloServiceBean implements HelloServiceRemote, HelloServiceLocal {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public HelloServiceBean() {
        super();
    }

    /**
     * 
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
    
    /**
     * @see jms.simple.javaee.services.HelloService#createMessage(jms.simple.javaee.domain.Hello)
     */
    @Override
    public void createMessage(Hello hello) {
        this.getEntityManager().persist(hello);
    }

    /**
     * @see jms.simple.javaee.services.HelloService#findMessage(java.lang.Long)
     */
    @Override
    public Hello findMessage(Long code) {
    	Hello hello = this.getEntityManager().find(Hello.class, code);
    	this.getEntityManager().detach(hello);
        return hello;
    }

    /**
     * @see jms.simple.javaee.services.HelloService#sayHello()
     */
    @Override
    public String sayHello() {
        return "Hello World!";
    }

    /**
     * @see jms.simple.javaee.services.HelloService#findAll()
     */
	@Override
	public List<Hello> findAll() {
		Query query = this.getEntityManager().createNamedQuery("findAllHello");
		@SuppressWarnings("unchecked")
		List<Hello> helloList = (List<Hello>) query.getResultList(); 
		this.getEntityManager().detach(helloList);
		
		return helloList;
	}

    
}
