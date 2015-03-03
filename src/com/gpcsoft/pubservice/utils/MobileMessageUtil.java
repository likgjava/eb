package com.gpcsoft.pubservice.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.exception.CustomerException;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.domain.MobileMessage;
import com.gpcsoft.pubservice.application.message.manager.MobileMessageManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.SysInfo;
import com.guoxin.business.sms.bean.SendingBean;
import com.guoxin.business.sms.service.SendMessage;

public class MobileMessageUtil {
	
	private static MobileMessageManager mobileMessageManager;
	
	private static FreeMarkerService freeMarkerService;
	
	private static SendMessage sendMessage;
	
	static {
		mobileMessageManager =  (MobileMessageManager) FrameBeanFactory.getBean("mobileMessageManagerImpl");
		freeMarkerService =  (FreeMarkerService) FrameBeanFactory.getBean("freeMarkerServiceImpl");
		sendMessage = SendMessage.getInstance();
	}
	
	/** 
	 * Description :  发送手机短信
	 * Create Date: 2011-7-20下午01:47:06 by yucy  Modified Date: 2011-7-20下午01:47:06 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("static-access")
	public static void sendMobileMessage(
			String receiveNumber,
			String sendUserId, 
			String sendUserName, 
			String receiveUserId,
			String receiveUserName, 
			String content, 
			String templateName, 
			Map<String, Object> contentModel
			) throws Exception{
		
		//判断系统是否开放短信功能
		if(! SysInfo.getInstance().isAbleMobileMessage() ){ return; }

		//接收人电话号码为空,则抛出异常
        if (!StringUtils.hasLength(receiveNumber)) throw new CustomerException("接收人电话号码不能为空！");
        
		SendingBean sendingBean = new SendingBean();
		
		MobileMessage mobileMessage = new MobileMessage();

		//手机号码 
		sendingBean.setReceiver(receiveNumber);
		mobileMessage.setMobileNumber(receiveNumber);
		
		//发送人姓名
		sendingBean.setSender(sendUserName);
		
		//ebid业务标示
		sendingBean.setAppid(SendMessage.APP_EBID);
		
		//发送内容
		String messageContent = StringUtils.hasLength(content)? content : freeMarkerService.getFreeMarkerContent( templateName, contentModel) ;
		if(!StringUtils.hasLength(messageContent) || messageContent.length()>70){
			throw new CustomerException("短信内容不符合规范！");
		}else{
			sendingBean.setContent(messageContent);
			mobileMessage.setContent(messageContent);
		}
		
		//插入时间
		sendingBean.setInserttime( DateUtil.format(new Date(), DateUtil.hour24HMSPattern) );
		mobileMessage.setCreateTime(new Date());
		
		//发送时间
		sendingBean.setSendtime( DateUtil.format(new Date(), DateUtil.hour24HMSPattern) );
		mobileMessage.setSendTime(new Date());
		
		//重试次数
		sendingBean.setRetrytimes(sendMessage.RETRYTIMES);
		
		List<SendingBean> sendingBeanList = new ArrayList<SendingBean>();
		
		sendingBeanList.add(sendingBean);
		
		mobileMessage.setSender(sendUserId);
		mobileMessage.setSenderName(sendUserName);
		mobileMessage.setCreUserId(AuthenticationHelper.getCurrentUser(true).getObjId());
		mobileMessage.setCreUserName(AuthenticationHelper.getCurrentUser(true).getUsername());
		mobileMessage.setReceiver(receiveUserId);
		mobileMessage.setReceiverName(receiveUserName);
		
		//保存
		mobileMessageManager.save(mobileMessage);
		
		try {
			//发送
			sendMessage.doSend(sendingBeanList);//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}