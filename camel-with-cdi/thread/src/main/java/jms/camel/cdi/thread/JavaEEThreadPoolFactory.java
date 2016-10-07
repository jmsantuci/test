/**
 * 
 */
package jms.camel.cdi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.camel.spi.ThreadPoolFactory;
import org.apache.camel.spi.ThreadPoolProfile;

/**
 * @author jsantuci
 *
 */
public class JavaEEThreadPoolFactory implements ThreadPoolFactory {

	/**
	 * JNDI initial context.
	 */
	private InitialContext initialContext = null;
	
	/**
	 * Construtor default.
	 */
	public JavaEEThreadPoolFactory() {
		super();
		try {
			this.setInitialContext(new InitialContext());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the initialContext
	 */
	public InitialContext getInitialContext() {
		return this.initialContext;
	}

	/**
	 * @param initialContext the initialContext to set
	 */
	public void setInitialContext(InitialContext initialContext) {
		this.initialContext = initialContext;
	}

	/**
	 * @see org.apache.camel.spi.ThreadPoolFactory#newCachedThreadPool(java.util.concurrent.ThreadFactory)
	 */
	@Override
	public ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
		ExecutorService executorService = null;
		try {
			executorService = (ExecutorService) this.getInitialContext().lookup("java:jboss/ee/concurrency/executor/default");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executorService;
	}

	/**
	 * @see org.apache.camel.spi.ThreadPoolFactory#newScheduledThreadPool(org.apache.camel.spi.ThreadPoolProfile, java.util.concurrent.ThreadFactory)
	 */
	@Override
	public ScheduledExecutorService newScheduledThreadPool(
			ThreadPoolProfile threadPoolProfile, ThreadFactory threadFactory) {
		ScheduledExecutorService scheduledExecutorService = null;
		try {
			scheduledExecutorService = (ScheduledExecutorService) this.getInitialContext().lookup("java:jboss/ee/concurrency/scheduler/default");
			scheduledExecutorService = new ScheduledExecutorServiceWrapper(scheduledExecutorService);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scheduledExecutorService;
	}

	/**
	 * @see org.apache.camel.spi.ThreadPoolFactory#newThreadPool(org.apache.camel.spi.ThreadPoolProfile, java.util.concurrent.ThreadFactory)
	 */
	@Override
	public ExecutorService newThreadPool(ThreadPoolProfile threadPoolProfile, ThreadFactory threadFactory) {
		return this.newCachedThreadPool(threadFactory);
	}

}
