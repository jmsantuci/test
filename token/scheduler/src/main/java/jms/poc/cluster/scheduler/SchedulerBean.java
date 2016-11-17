package jms.poc.cluster.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import jms.poc.cluster.domain.SchedulerInfo;
import jms.poc.tac.security.AccessController;
import jms.poc.tac.security.Token;
import jms.poc.tac.security.UserPassCallbackHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EJB that makes and execute schedules.
 */
@Stateless
@LocalBean
public class SchedulerBean {
	
	/**
	 * LOGGER instance.
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(SchedulerBean.class);

	/**
	 * Date format that user uses
	 */
	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm");
	
	/**
	 * TimeService used to schedule.
	 */
	@Resource
	private TimerService timerService;

	/**
	 * AccessController is used to authenticate and check user status.
	 */
	@EJB
	private AccessController accessController;

	/**
	 * Bean that store schedule information.
	 */
	@EJB
	private ScheduledInfoBean scheduledInfoBean;

	/**
     * Default constructor. 
     */
    public SchedulerBean() {
    }

    /**
     * Auxiliar method to create a token.
     * 
     * @param user token user
     * @return a new token
     */
    private String createToken(String user) {
    	Token token = new Token(user);
    	return token.generateToken();
    }

    /**
     * Schedule a new task.
     * 
     * @param user task user
     * @param time schedule time
     */
    public void schedule(String user, String time) {
    	LOGGER.debug("Scheduling task. User: " + user + ".Timer: " + time);

    	String token = this.createToken(user);
    	SchedulerInfo schedulerInfo = new SchedulerInfo(user, token, time);

		try {
			
			Date date = this.formatter.parse(time);
			this.timerService.createSingleActionTimer(date, new TimerConfig(schedulerInfo, false));
			LOGGER.debug("Task scheduled");
		} catch (ParseException e) {
			LOGGER.error("Error on scheduling task");
		}
    		
    }

    /**
     * Task execution callback method.
     * 
     * @param timer schedule information.
     */
    @Timeout
    public void executeTask(Timer timer) {
    	LOGGER.debug("Executing the scheduled task");
    	SchedulerInfo schedulerInfo = (SchedulerInfo) timer.getInfo();

    	if (schedulerInfo != null) {
    		LoginContext loginContext = null;
    		String userName = schedulerInfo.getUser();
    		String key = schedulerInfo.getToken();
    		try {
    			LOGGER.debug("Try to log in user: " + userName);
				loginContext = this.createLoginContext(userName, key);
				loginContext.login();
				if (!this.accessController.isUserBlocked(userName)) {
					schedulerInfo.setExecutionTime(this.formatter.format(new Date()));
					this.scheduledInfoBean.addScheduledInfo(schedulerInfo);
					LOGGER.debug("Task executed");
				}
			} catch (LoginException loginException) {
				LOGGER.error("Error on logging in the execution of the scheduled task", loginException);
			} finally {
				if (loginContext != null) {
					try {
						loginContext.logout();
					} catch (LoginException loginException) {
						LOGGER.error("Error log out in the execution of the scheduled task", loginException);
					}
				}
			}
    	}
    }

    /**
     * Auxiliar method to create a LoginContext using web login security domain.
     * 
     * @param user user to authenticate
     * @param key user token
     * @return a new instance of LoginContext
     * @throws LoginException if authenticate fails
     */
    private LoginContext createLoginContext(String user, String key) throws LoginException {

		LoginContext loginContext = new LoginContext("jms-poc-tac-web-login-module", new UserPassCallbackHandler(user, key));
		return loginContext;
    }

//    /**
//     * Auxiliar method to create a LoginContext using programmatic configuration.
//     * 
//     * @param user user to authenticate
//     * @param key user token
//     * @return a new instance of LoginContext
//     * @throws LoginException if authenticate fails
//     */
//    private LoginContext createLoginContext(String user, String key) throws LoginException {
//    	final String configurationName = "jms-poc-tac-token-login-module"; 
//    	
//        Configuration config = new Configuration() { 
//        	 
//            Configuration config = new Configuration() { 
//            	 
//                @Override 
//                public AppConfigurationEntry[] getAppConfigurationEntry(String name) { 
//                    if (configurationName.equals(name) == false) { 
//                        throw new IllegalArgumentException("Unexpected configuration name '" + name + "'"); 
//                    } 
//                    Map<String, String> options = new HashMap<String, String>(); 
//                    options.put("multi-threaded", "true"); 
//                    options.put("restore-login-identity", "true"); 
//     
//                    AppConfigurationEntry clmEntry = new AppConfigurationEntry("", 
//                        AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options); 
//     
//                    return new AppConfigurationEntry[] { clmEntry }; 
//                } 
//            }; 
//
//			@Override
//			public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
//				// TODO Auto-generated method stub
//				return null;
//			} 
//        }; 
//
//        LoginContext loginContext = new LoginContext(configurationName, new Subject(), new UserPassCallbackHandler(user, key), config);
//		return loginContext;
//    }

}