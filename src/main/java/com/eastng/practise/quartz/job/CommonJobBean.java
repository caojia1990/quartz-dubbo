package com.eastng.practise.quartz.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.eastng.practise.bean.DubboBean;
import com.eastng.practise.manage.DubboServiceFactory;

public class CommonJobBean extends QuartzJobBean {
	
	static Logger logger = Logger.getLogger(CommonJobBean.class);
	
	public static final String JOB_DATA_KEY = "jobDateKey";
	
	private static final String APPLICATION_CONTEXT_KEY = "applicationContextKey";
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		
		logger.info("---------------------------执行job-------------------------");
		
		logger.info("jobKey:"+context.getJobDetail().getKey()+new Date());
			//ApplicationContext applicationContext = this.getApplicatContext(context);
		DubboBean bean = (DubboBean) dataMap.get(JOB_DATA_KEY);
		
		DubboServiceFactory dubboService = (DubboServiceFactory) 
				getApplicatContext(context).getBean("dubboService");
		
		try {
			dubboService.invoke(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取spring上下文
	 * @param context
	 * @return
	 * @throws JobExecutionException
	 */
	private ApplicationContext getApplicatContext(JobExecutionContext context) throws JobExecutionException{
		 
		ApplicationContext cxt = null;
		try {
			cxt = (ApplicationContext) context.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
		} catch (SchedulerException e) {
			logger.error("获取SchedulerContext失败",e);
			throw new JobExecutionException("获取SchedulerContext失败");
		}
		
		if(cxt == null){
			logger.error("获取spring上下文失败,APPLICATION_CONTEXT_KEY:"+APPLICATION_CONTEXT_KEY);
			throw new JobExecutionException("获取spring上下文失败,APPLICATION_CONTEXT_KEY:"+APPLICATION_CONTEXT_KEY);
		}
		
		return cxt;
	}
}
