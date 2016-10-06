package com.eastng.practise.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.eastng.practise.bean.JobBean;
import com.eastng.practise.bean.SimpleJobBean;
import com.eastng.practise.quartz.scheduler.JobScheduleService;
import com.eastng.practise.vo.JobVo;

@Controller
public class SchedulerController {
	
	private static Logger logger = Logger.getLogger(SchedulerController.class);

    @Autowired
    private JobScheduleService jobScheduleService;
    
    @RequestMapping(value="getAllJobs")
    @ResponseBody
    public List<JobBean> getAllJobs() throws SchedulerException{
        return this.jobScheduleService.getAllJob();
    }
    
    @RequestMapping(value="addJob")
    @ResponseBody
    public void addJob(@RequestBody JobVo parameterVo) throws SchedulerException{
    	logger.info("创建任务入参：" + JSON.toJSONString(parameterVo));
        
        SimpleJobBean jobBean = new SimpleJobBean();
        BeanUtils.copyProperties(parameterVo, jobBean);
        
        logger.info("创建任务：" + JSON.toJSONString(jobBean));
        
        this.jobScheduleService.addJob(jobBean);
    }
    
    @RequestMapping(value="triggerJob")
    @ResponseBody
    public void triggerJob(@RequestParam(value="name") String name, @RequestParam(value="group") String group) throws SchedulerException{
    	SimpleJobBean simpleJobBean = new SimpleJobBean();
    	simpleJobBean.setName(name);
    	simpleJobBean.setGroup(group);
    	this.jobScheduleService.triggerJob(simpleJobBean);
    }
}
