package com.eastng.practise.bean;

public class SimpleJobBean {
    
    private String name;
    
    private String group;
    
    private String description;
    
    private boolean durable;
    
    private boolean replace;
    
    private TriggerBean triggerBean;
    
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
    
    public boolean isReplace() {
		return replace;
	}

	public void setReplace(boolean replace) {
		this.replace = replace;
	}

	public TriggerBean getTriggerBean() {
        return triggerBean;
    }

    public void setTriggerBean(TriggerBean triggerBean) {
        this.triggerBean = triggerBean;
    }

    public DubboBean getJobData() {
        return jobData;
    }

    public void setJobData(DubboBean jobData) {
        this.jobData = jobData;
    }
    
}
