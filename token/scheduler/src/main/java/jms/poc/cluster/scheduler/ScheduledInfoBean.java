package jms.poc.cluster.scheduler;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import jms.poc.cluster.domain.SchedulerInfo;

/**
 * Session Bean implementation class ScheduledInfoBean
 */
@Singleton
@LocalBean
public class ScheduledInfoBean {

	private List<SchedulerInfo> scheduledInfoList = new ArrayList<SchedulerInfo>();
	
    /**
     * Default constructor. 
     */
    public ScheduledInfoBean() {
        super();
    }

    /**
     * Get the scheduled info list.
     * 
     * @return scheduled info list
     */
	public List<SchedulerInfo> getScheduledInfoList() {
		return this.scheduledInfoList;
	}

	/**
	 * Add a new ScheduleInfo element to the list.
	 * 
	 * @param schedulerInfo new ScheduleInfo list
	 */
	public void addScheduledInfo(SchedulerInfo schedulerInfo) {
		this.getScheduledInfoList().add(schedulerInfo);
	}
}
