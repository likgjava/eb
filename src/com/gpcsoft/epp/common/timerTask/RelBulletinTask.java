package com.gpcsoft.epp.common.timerTask;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.epp.bulletin.service.BulletinService;

public class RelBulletinTask extends TimerTask{
	 
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	 protected final Log logger = LogFactory.getLog(this.getClass());
	
	private static boolean isRunning = false;//当前是否正在执行的状态标志

	public void run() { 
	    if (!isRunning) { 
	        isRunning = true; 
	        try {
				bulletinService.relBulletin();//调用发布公告任务
			} catch (Exception e) {
				logger.error("调用发布公告任务", e);
			}
	        isRunning = false; 
	    }else { 
	    	logger.info("正在执行任务。。。。。。");
	    } 
	 } 
} 
