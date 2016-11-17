/**
 * 
 */
package jms.poc.cluster.domain;

import java.io.Serializable;

/**
 * @author jsantuci
 *
 */
public class SchedulerInfo implements Serializable {

	/**
	 * Default serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * User name that will be used to execute the task.
	 */
	private String user;

	/**
	 * Key to authenticate user.
	 */
	private String token;

	/**
	 * Schedule time.
	 */
	private String scheduleTime;

	/**
	 * Schedule executed time.
	 */
	private String executionTime;

	/**
	 * Default constructor.
	 */
	public SchedulerInfo() {
		super();
	}

	public SchedulerInfo(String user, String key, String time) {
		this.setUser(user);
		this.setToken(key);
		this.setScheduleTime(time);
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String key) {
		this.token = key;
	}

	/**
	 * @return the time
	 */
	public String getScheduleTime() {
		return this.scheduleTime;
	}

	/**
	 * @param time the time to set
	 */
	public void setScheduleTime(String time) {
		this.scheduleTime = time;
	}

	/**
	 * @return the scheduledTime
	 */
	public String getExecutionTime() {
		return this.executionTime;
	}

	/**
	 * @param scheduledTime the scheduledTime to set
	 */
	public void setExecutionTime(String scheduledTime) {
		this.executionTime = scheduledTime;
	}

	@Override
	public String toString() {
		return "SchedulerInfo: [user=" + user + ", scheduleTime=" + scheduleTime
				+ ", executionTime=" + executionTime + "]";
	}

}
