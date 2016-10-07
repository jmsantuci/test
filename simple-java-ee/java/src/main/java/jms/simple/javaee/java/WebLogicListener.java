/**
 * 
 */
package jms.simple.javaee.java;

import weblogic.application.ApplicationLifecycleEvent;
import weblogic.application.ApplicationLifecycleListener;

/**
 * @author Ma
 *
 */
public class WebLogicListener extends ApplicationLifecycleListener {

	/**
	 * Default constructor 
	 */
	public WebLogicListener() {
		System.out.println("**** Default constructor.");
	}

	@Override
	public void postStart(ApplicationLifecycleEvent evt) {
		System.out.println("**** postStart.");
	}

	@Override
	public void postStop(ApplicationLifecycleEvent evt) {
		System.out.println("**** postStop.");
	}

	@Override
	public void preStart(ApplicationLifecycleEvent evt) {
		System.out.println("**** preStart.");
	}

	@Override
	public void preStop(ApplicationLifecycleEvent evt) {
		System.out.println("**** preStop.");
	}

	
	
}
