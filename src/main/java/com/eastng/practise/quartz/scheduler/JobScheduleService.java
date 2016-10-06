package com.eastng.practise.quartz.scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastng.practise.bean.DubboBean;
import com.eastng.practise.bean.JobBean;
import com.eastng.practise.bean.SimpleJobBean;
import com.eastng.practise.bean.SimpleTriggerBean;
import com.eastng.practise.bean.TriggerBean;
import com.eastng.practise.quartz.job.CommonJobBean;

@Service("jobScheduleService")
public class JobScheduleService {
    
    private Logger logger = Logger.getLogger(JobScheduleService.class);
    
    
    
    @Autowired
    private Scheduler scheduler;
    
    /**
     * 查询调度器所有任务
     * @return
     * @throws SchedulerException
     */
    public List<JobBean> getAllJob() throws SchedulerException{
        
        List<String> groupNames = this.scheduler.getJobGroupNames();
        if(groupNames == null){
            return null;
        }
        
        List<JobBean> jobBeans = new ArrayList<JobBean>();
        
        for(String groupName : groupNames){
            for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher
                    .jobGroupEquals(groupName))){
                
                JobDetail jobDetail = this.scheduler.getJobDetail(jobKey);
                JobBean jobBean = new JobBean();
                jobBean.setDescription(jobDetail.getDescription());
                jobBean.setName(jobKey.getName());
                jobBean.setGroup(jobKey.getGroup());
                jobBean.setJobData((DubboBean) jobDetail.getJobDataMap()
                        .get(CommonJobBean.JOB_DATA_KEY));
                
                List<CronTrigger> list = (List<CronTrigger>) scheduler
                        .getTriggersOfJob(jobKey);
                
                List<TriggerBean> triggerBeans = new ArrayList<TriggerBean>();
                for(CronTrigger trigger : list){
                    TriggerBean bean = new TriggerBean();
                    bean.setTriggerName(trigger.getKey().getName());
                    bean.setTriggerGroup(trigger.getKey().getGroup());
                    bean.setDescription(trigger.getDescription());
                    bean.setPriority(trigger.getPriority());
                    bean.setCronExpression(trigger.getCronExpression());
                    bean.setNextFireTime(trigger.getNextFireTime());
                    bean.setPreviousFireTime(trigger.getPreviousFireTime());
                    bean.setState(this.scheduler.getTriggerState(trigger.getKey()));
                    triggerBeans.add(bean);
                }
                jobBean.setTriggerBeans(triggerBeans);
                jobBeans.add(jobBean);
            }
        }
        return jobBeans;
    }
    
    /**
     * 查询调度中的所有任务
     * @return
     * @throws SchedulerException
     */
    public List<JobExecutionContext> getRuningJob() throws SchedulerException{
        return this.scheduler.getCurrentlyExecutingJobs();
    }

    /**
     * 查询任务
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public JobDetail getJob(String jobName,String jobGroup) throws SchedulerException{
        
        return scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
        
    }
    
    
    
    /**
     * 新增或修改任务
     * @param jobBean
     * @throws SchedulerException
     */
    public void addJob(SimpleJobBean jobBean) throws SchedulerException{
        
        JobDetail jobDetail = newJob(CommonJobBean.class).withIdentity(jobBean.getName(), jobBean.getGroup())
                .storeDurably(true).withDescription(jobBean.getDescription()).build();
        
        jobDetail.getJobDataMap().put(CommonJobBean.JOB_DATA_KEY, jobBean.getJobData());
        
        this.scheduler.addJob(jobDetail, jobBean.isReplace());
    }
    
    /**
     * 立即执行任务
     * @param jobBean
     * @throws SchedulerException
     */
    public void triggerJob(SimpleJobBean jobBean) throws SchedulerException{
    	JobKey jobKey = new JobKey(jobBean.getName(), jobBean.getGroup());
    	this.scheduler.triggerJob(jobKey);
    }
    
    /**
     * 调度任务
     * @param jobBean
     * @throws SchedulerException
     */
    public void scheduleJob(SimpleJobBean jobBean) throws SchedulerException{
        
        
        logger.info("------- Scheduling Job  -------------------");
        
        JobKey jobKey = new JobKey(jobBean.getName(), jobBean.getGroup());
        JobDetail jobDetail = newJob(CommonJobBean.class).withIdentity(jobKey)
                .storeDurably(true).withDescription(jobBean.getDescription())
                .build();
        
        logger.info("job已创建，jobId：" + jobDetail.getKey().toString());
        
        TriggerKey triggerKey = new TriggerKey(jobBean.getTriggerBean().getTriggerName(), jobBean.getGroup());
        
        CronTrigger trigger = newTrigger().withIdentity(triggerKey)
                .withSchedule(cronSchedule(jobBean.getTriggerBean().getCronExpression()))
                .withDescription(jobBean.getTriggerBean().getDescription())
                .withPriority(jobBean.getTriggerBean().getPriority())
                .build();
        
        jobDetail.getJobDataMap().put(CommonJobBean.JOB_DATA_KEY, jobBean.getJobData());
        
        this.scheduler.scheduleJob(jobDetail, trigger);
        
        logger.info("------- Scheduling Job Finish-------------------");
    }
    
    /**
     * 添加触发器并启动任务
     * @param bean
     * @throws SchedulerException 
     */
    public void scheduleJob(SimpleTriggerBean bean) throws SchedulerException{
        CronTrigger trigger = newTrigger().withIdentity(bean.getTriggerName(), bean.getJobGroup())
                .withSchedule(cronSchedule(bean.getCronExpression()))
                .withDescription(bean.getDescription())
                .forJob(bean.getJobName(), bean.getJobGroup())
                .build();
        
        this.scheduler.scheduleJob(trigger);
    }
    
    /**
     * 修改任务
     * @param jobBean
     * @throws SchedulerException
     */
    public void modifyJob(SimpleJobBean jobBean) throws SchedulerException{
        
        String jobName = jobBean.getName();
        
        String jobGroup = jobBean.getGroup();
        
        logger.info("------- Scheduling Job  -------------------");
        JobDetail jobDetail = newJob(CommonJobBean.class).withIdentity(jobName, jobGroup)
        		.withDescription(jobBean.getDescription()).build();
        
        logger.info("job已创建，jobId：" + jobDetail.getKey().toString());
        
        CronTrigger trigger = newTrigger().withIdentity(jobBean.getTriggerBean().getTriggerName(), jobGroup)
                .withSchedule(cronSchedule(jobBean.getTriggerBean().getCronExpression()))
                .withDescription(jobBean.getTriggerBean().getDescription())
                .withPriority(jobBean.getTriggerBean().getPriority())
                .build();
        
        jobDetail.getJobDataMap().put(CommonJobBean.JOB_DATA_KEY, jobBean.getJobData());
        
        if(scheduler.checkExists(jobDetail.getKey())){
            
            //删除原有job
            this.scheduler.deleteJob(jobDetail.getKey());
            //重新计划调度任务
            this.scheduler.scheduleJob(jobDetail, trigger);
            
        }
    }
    
    /**
     * 暂停job
     * @param jobBean
     * @throws SchedulerException
     */
    public void pauseJob(SimpleJobBean jobBean) throws SchedulerException{
    	JobKey jobKey = new JobKey(jobBean.getName(), jobBean.getGroup());
    	this.scheduler.pauseJob(jobKey);
    }
    
    /**
     * 恢复任务
     * @param jobBean
     * @throws SchedulerException
     */
    public void resumeJob(SimpleJobBean jobBean) throws SchedulerException{
    	JobKey jobKey = new JobKey(jobBean.getName(), jobBean.getGroup());
    	this.scheduler.resumeJob(jobKey);
    }
    
    /**
     * 删除任务
     * @param jobBean
     * @throws SchedulerException
     */
    public void removeJob(JobBean jobBean) throws SchedulerException{
        
        this.scheduler.deleteJob(new JobKey(jobBean.getName(), jobBean.getGroup()));
        logger.info("remove job finish!");
    }
    
    /**
     * 修改触发器(不能修改触发器名)
     * @param jobBean
     * @throws SchedulerException
     */
    public void rescheduleJob(SimpleTriggerBean bean) throws SchedulerException{
        
        JobKey jobKey = new JobKey(bean.getJobName(), bean.getJobGroup());
        
        TriggerKey oldTriggerKey = new TriggerKey(bean.getTriggerName(), bean.getTriggerGroup());
        
        CronTrigger newTrigger = newTrigger().withIdentity(oldTriggerKey)
                .withSchedule(cronSchedule(bean.getCronExpression()))
                .forJob(jobKey).build();
        
        this.scheduler.rescheduleJob(oldTriggerKey, newTrigger);
    }
    
    /**
     * 删除触发器
     * @param triggerName
     * @param triggerGroup
     * @throws SchedulerException
     */
    public void removeTrigger(String triggerName ,String triggerGroup) throws SchedulerException{
        
        TriggerKey key = new TriggerKey(triggerName, triggerGroup);
        this.scheduler.unscheduleJob(key);
        logger.info("remove trigger finish !");
    }
}
