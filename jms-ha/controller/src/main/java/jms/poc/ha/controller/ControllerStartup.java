package jms.poc.ha.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Session Bean implementation class ControllerStartup
 */
@Singleton
@LocalBean
@Startup
public class ControllerStartup {

    /**
     * Default constructor. 
     */
    public ControllerStartup() {
        super();
    }

    @PostConstruct
    public void startup() {
    	System.out.println("Controller Startup Bean - startup");
    }

    @PreDestroy
    public void finished() {
    	System.out.println("Controller Startup Bean - finished");
    }
}
