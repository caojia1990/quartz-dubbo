package com.eastng.practise.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eastng.practise.bean.DubboBean;
import com.eastng.practise.bean.JobBean;
import com.eastng.practise.bean.SimpleJobBean;
import com.eastng.practise.bean.SimpleTriggerBean;
import com.eastng.practise.quartz.scheduler.JobScheduleService;
import com.eastng.practise.vo.TriggerParamVo;
import com.eastng.practise.vo.DubboVo;
import com.eastng.practise.vo.JobVo;
import com.eastng.practise.vo.ParamVo;

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
    public void addJob(@RequestBody JobVo parameterVo) throws SchedulerException, ClassNotFoundException{
    	logger.info("创建任务入参：" + JSON.toJSONString(parameterVo));
        
    	DubboVo dubboVo = parameterVo.getJobData();
    	
    	DubboBean dubboBean = new DubboBean();
    	BeanUtils.copyProperties(dubboVo, dubboBean);
    	
    	//封装dubbo接口参数
    	String paramStr = parameterVo.getParams();
    	if(!StringUtils.isEmpty(paramStr)){
    		JSONArray jsonArray = JSON.parseArray(paramStr);
    		
    		List<String> typeList = new ArrayList<String>();
    		List<Object> valueList = new ArrayList<Object>();
    		for (Object jsonObject : jsonArray) {
    			ParamVo paramVo = (ParamVo) JSON.parseObject(jsonObject.toString(),ParamVo.class);
    			Class<?> clazz = Class.forName(paramVo.getParamType());
    			Object o = JSON.parseObject(paramVo.getParamValue(), clazz);
    			typeList.add(paramVo.getParamType());
    			valueList.add(o);
    		}
    		dubboBean.setParameterType(typeList.toArray(new String[0]));
    		dubboBean.setParameterValue(valueList.toArray());
    	}
    	
        SimpleJobBean jobBean = new SimpleJobBean();
        BeanUtils.copyProperties(parameterVo, jobBean);
        jobBean.setJobData(dubboBean);
        
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
    
    @RequestMapping(value="pauseJob")
    @ResponseBody
    public void pauseJob(@RequestParam(value="name") String name, @RequestParam(value="group") String group) throws SchedulerException{
        SimpleJobBean simpleJobBean = new SimpleJobBean();
        simpleJobBean.setName(name);
        simpleJobBean.setGroup(group);
        this.jobScheduleService.pauseJob(simpleJobBean);
    }
    
    @RequestMapping(value="resumeJob")
    @ResponseBody
    public void resumeJob(@RequestParam(value="name") String name, @RequestParam(value="group") String group) throws SchedulerException{
        SimpleJobBean simpleJobBean = new SimpleJobBean();
        simpleJobBean.setName(name);
        simpleJobBean.setGroup(group);
        this.jobScheduleService.resumeJob(simpleJobBean);
    }
    
    @RequestMapping(value="removeJob")
    @ResponseBody
    public void removeJob(@RequestParam(value="name") String name, @RequestParam(value="group") String group) throws SchedulerException{
        SimpleJobBean simpleJobBean = new SimpleJobBean();
        simpleJobBean.setName(name);
        simpleJobBean.setGroup(group);
        this.jobScheduleService.removeJob(simpleJobBean);
    }
    
    @RequestMapping(value="addTrigger")
    @ResponseBody
    public void addTrigger(@RequestBody TriggerParamVo paramVo) throws SchedulerException{
    	logger.info("添加触发器入参"+JSON.toJSONString(paramVo));
    	SimpleTriggerBean simpleTriggerBean = new SimpleTriggerBean();
    	BeanUtils.copyProperties(paramVo, simpleTriggerBean);
    	this.jobScheduleService.scheduleJob(simpleTriggerBean);
    }
    
    @RequestMapping(value="rescheduleJob")
    @ResponseBody
    public void rescheduleJob(@RequestBody TriggerParamVo paramVo) throws SchedulerException{
    	logger.info("修改触发器入参"+JSON.toJSONString(paramVo));
    	SimpleTriggerBean simpleTriggerBean = new SimpleTriggerBean();
    	BeanUtils.copyProperties(paramVo, simpleTriggerBean);
    	this.jobScheduleService.rescheduleJob(simpleTriggerBean);
    }
    
    @RequestMapping(value="removeTrigger")
    @ResponseBody
    public void removeTrigger(@RequestParam(value="triggerName") String triggerName, 
    		@RequestParam(value="triggerGroup") String triggerGroup) throws SchedulerException{
    	this.jobScheduleService.removeTrigger(triggerName, triggerGroup);
    	
    }
}
