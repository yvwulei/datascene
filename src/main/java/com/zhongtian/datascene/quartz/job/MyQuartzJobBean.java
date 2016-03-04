package com.zhongtian.datascene.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zhongtian.datascene.elasticsearch.factory.TransportClientFactoryBean;

@PersistJobDataAfterExecution  
@DisallowConcurrentExecution// 不允许并发执行  
public class MyQuartzJobBean extends QuartzJobBean{
	protected static final Log logger = LogFactory.getLog(TransportClientFactoryBean.class);

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		logger.debug(sdf.format(new Date()));
	}
	
}
