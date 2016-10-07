package com.eastng.practise;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.eastng.practise.base.BaseJunitTest;
import com.eastng.practise.bean.DubboBean;
import com.eastng.practise.bean.JobBean;
import com.eastng.practise.bean.SimpleJobBean;
import com.eastng.practise.bean.TriggerBean;
import com.eastng.practise.quartz.scheduler.JobScheduleService;

public class JobTest extends BaseJunitTest{
	
	static Logger logger = Logger.getLogger(JobTest.class);

	@Resource
	private Scheduler scheduler;
	
	@Resource
	private JobScheduleService jobScheduleService;
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void addJob() throws Exception {
		
		System.out.println("------- Initializing ----------------------");
		
		SimpleJobBean jobBean = new SimpleJobBean();
		
		TriggerBean triggerBean = new TriggerBean();
		triggerBean.setCronExpression("0/20 * * * * ?");
		triggerBean.setDescription("触发器");
		triggerBean.setPriority(3);
		triggerBean.setTriggerGroup("第一组");
		triggerBean.setTriggerName("触发器01");
		jobBean.setTriggerBean(triggerBean);
		
		jobBean.setName("jobname1");
		jobBean.setGroup("jobGroup1");
		jobBean.setDescription("test");
		
		DubboBean dubboBean = new DubboBean();
		dubboBean.setAddress("localhost");
		dubboBean.setProtocol("zookeeper");
		dubboBean.setPort(2181);
		dubboBean.setInterfaceName("com.eastng.practise.example1.HelloService");
		dubboBean.setMethodName("sayHello");
		jobBean.setJobData(dubboBean);
		
		this.jobScheduleService.scheduleJob(jobBean);
		//this.jobScheduleService.modifyJob(jobBean);
	
	    // wait long enough so that the scheduler as an opportunity to
	    // run the job!
	    /*logger.info("------- Waiting 65 seconds... -------------");
	    try {
	      // wait 65 seconds to show job
	      Thread.sleep(65L * 1000L);
	      // executing...
	    } catch (Exception e) {
	      //
	    }*/
	    //scheduler.shutdown(true);
	    
	    logger.info("------------------------调度结束--------------------------");
	}

	
	@Test
	public void getRuningJob() throws SchedulerException{
		this.jobScheduleService.getRuningJob();
		
		logger.info("获取执行中的job完成！");
		
		synchronized (this.getClass()) {
			while(true){
				try {
					this.getClass().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void removeJob() throws SchedulerException{
		SimpleJobBean jobBean = new SimpleJobBean();
		jobBean.setName("name1");
		jobBean.setGroup("group1");
		this.jobScheduleService.removeJob(jobBean);
	}
	
	@Test
	public void getAllJob() throws SchedulerException{
		this.jobScheduleService.getAllJob();
	}
}
