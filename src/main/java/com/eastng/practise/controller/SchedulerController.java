package com.eastng.practise.controller;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastng.practise.bean.JobBean;
import com.eastng.practise.quartz.scheduler.JobScheduleService;

@Controller
public class SchedulerController {

    @Autowired
    private JobScheduleService jobScheduleService;
    
    @RequestMapping(value="getAllJobs")
    @ResponseBody
    public List<JobBean> getAllJobs() throws SchedulerException{
        return this.jobScheduleService.getAllJob();
    }
    
}
