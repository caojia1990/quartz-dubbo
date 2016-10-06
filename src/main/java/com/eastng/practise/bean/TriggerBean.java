package com.eastng.practise.bean;

import java.util.Date;

import org.quartz.Trigger.TriggerState;

public class TriggerBean {

    private String triggerName;
    
    private String triggerGroup;
    
    private Integer priority; 
    
    private String cronExpression;
    
    private String description;
    
    private TriggerState state;
    
    private Date nextFireTime;
    
    private Date previousFireTime;

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
    

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public TriggerState getState() {
        return state;
    }

    public void setState(TriggerState state) {
        this.state = state;
    }
    
    
}
