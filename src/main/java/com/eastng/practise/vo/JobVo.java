package com.eastng.practise.vo;

public class JobVo {
    
    private String name;
    
    private String group;
    
    private String description;
    
    private DubboVo jobData;

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

	public DubboVo getJobData() {
		return jobData;
	}

	public void setJobData(DubboVo jobData) {
		this.jobData = jobData;
	}

    
}
