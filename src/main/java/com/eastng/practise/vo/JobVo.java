package com.eastng.practise.vo;

import java.util.List;

public class JobVo {
    
    private String name;
    
    private String group;
    
    private String description;
    
    private boolean replace;
    
    private DubboVo jobData;
    
    private String params;

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

	public boolean isReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }

    public DubboVo getJobData() {
		return jobData;
	}

	public void setJobData(DubboVo jobData) {
		this.jobData = jobData;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
