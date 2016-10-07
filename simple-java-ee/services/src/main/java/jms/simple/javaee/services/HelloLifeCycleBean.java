/**
 * 
 */
package jms.simple.javaee.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author Ma
 *
 */
@Singleton
@Startup
public class HelloLifeCycleBean {

	/**
	 * Default constructor
	 */
	public HelloLifeCycleBean() {
		super();
	}

	@PostConstruct
	public void startup() {
		System.out.println("\n\n************************* Startup ******************************\n\n");
	}

	@PreDestroy
	public void tearDown() {
		System.out.println("\n\n************************* TearDown ******************************\n\n");
	}
}
