package com.eastng.practise.bean;

import java.util.List;

public class JobBean {
	
	private String name;
	
	private String group;
	
	private String description;
	
	private boolean durable;
	
	private List<TriggerBean> triggerBeans;
	
	private DubboBean jobData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDurable() {
		return durable;
	}

	public void setDurable(boolean durable) {
		this.durable = durable;
	}

	public DubboBean getJobData() {
		return jobData;
	}

	public void setJobData(DubboBean jobData) {
		this.jobData = jobData;
	}

	public List<TriggerBean> getTriggerBeans() {
		return triggerBeans;
	}

	public void setTriggerBeans(List<TriggerBean> triggerBeans) {
		this.triggerBeans = triggerBeans;
	}
	
}
