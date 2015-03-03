package com.gpcsoft.pubservice.utils;

import java.util.Map;

import com.gpcsoft.bizplatform.base.exception.CustomerException;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.pubservice.application.message.domain.Message;
import com.gpcsoft.pubservice.application.message.enumeration.EnumMessage;
import com.gpcsoft.pubservice.application.message.manager.MessageManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.SysInfo;

public class MessageUtil {
	
	private static MessageManager messageManager;
	private static FreeMarkerService freeMarkerService;
	
	static {
		messageManager =  (MessageManager) FrameBeanFactory.getBean("messageManagerImpl");
		freeMarkerService =  (FreeMarkerService) FrameBeanFactory.getBean("freeMarkerServiceImpl");
	}
	
	/** 
	 * Description :  发送个人站内信
	 * Create Date: 2011-7-22上午09:47:08 by yucy  Modified Date: 2011-7-22上午09:47:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static void sendMessagePrivete(String[] receiverUserIds, String title ,String content, String templateName, Map<String, Object> contentModel , Boolean isSave ) throws Exception{
		sendMessage(receiverUserIds, title , content, templateName, contentModel , isSave,	EnumMessage.PRIVATE_MESSAGE  ) ;
	}

	/** 
	 * Description :  发送系统站内信
	 * Create Date: 2011-7-22上午09:37:12 by yucy  Modified Date: 2011-7-22上午09:37:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static void sendMessageSystem(String[] receiverUserIds, String title ,String content, String templateName, Map<String, Object> contentModel , Boolean isSave  ) throws Exception{
		sendMessage(receiverUserIds, title , content, templateName, contentModel , isSave,	EnumMessage.SYSTEM_MESSAGE  ) ;
	}
	
	/** 
	 * Description :  发送站内信
	 * Create Date: 2011-7-20下午01:44:38 by yucy  Modified Date: 2011-7-20下午01:44:38 by yucy
	 * @param   receiverObjIds 接受人
	 * @return  
	 * @Exception   
	 */
	 private static void sendMessage(String[] receiverUserIds, String title ,String content, String templateName, Map<String, Object> contentModel , Boolean isSave, String MessageType ) throws Exception{
		
		//判断系统是否开放站内信功能
		if(!SysInfo.getInstance().isAbleMessage()){ return; }
		
		//接收人地址为空,则抛出异常
        if (receiverUserIds==null || receiverUserIds.length==0 ) throw new CustomerException("接收人地址不能为空！");
		
		// 发送站内信
		Message message = new Message();
		message.setTitle(title);
		message.setContent(StringUtils.hasLength(content)? content : freeMarkerService.getFreeMarkerContent( templateName, contentModel) );
		message.setIsSave( isSave );
		message.setType(MessageType);// 属于个人信件
		messageManager.saveMessageBatch(message, receiverUserIds );
	}
}