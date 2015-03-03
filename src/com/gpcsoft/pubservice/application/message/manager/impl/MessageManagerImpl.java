package com.gpcsoft.pubservice.application.message.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.manager.MessageManager;
import com.gpcsoft.pubservice.application.message.dao.MessageDao;
import com.gpcsoft.pubservice.application.message.domain.Message;
import com.gpcsoft.pubservice.application.message.enumeration.EnumMessage;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class MessageManagerImpl extends BaseManagerImpl<Message> implements MessageManager {

	@Autowired(required=true)  @Qualifier("messageDaoHibernate")
	private MessageDao messageDaoHibernate;

	/** 
	 * Description :  保存新建的私人站内信内容
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveMessage(Message message) throws Exception 
	{
		message.setSender(AuthenticationHelper.getCurrentUser(true).getObjId());
		message.setCreateTime(new Date());
		message.setIsRead(false);
		//message.setResSendTime(new Date());
		message.setSendTime(new Date());
		
		message.setSenderName(AuthenticationHelper.getCurrentUser(true).getUsername());
		
		if(message.getIsSave())
		{
			//此条信息为发送者的发件箱中的信息
			messageDaoHibernate.save(message);
		}
		
		Message newMessage = new Message(message);
		newMessage.setIsSave(false);
		//此条信息为接收者的收件箱中的信息
		messageDaoHibernate.save(newMessage);
	}

	/** 
	 * Description :  获得指定用户的【未读的和味提示的】站内信的数量
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Long> getMessageNum(String objId, Map<String, Object> param) throws Exception 
	{
		return messageDaoHibernate.getMessageNum(objId, param);
	}

	/** 
	 * Description :  向指定的多个用户批量发送站内信
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveMessageBatch(Message message, String[] receiverObjIds) throws Exception 
	{
		//如果message类型是不是系统信息或者当前登录用户为超级管理员
		if(!EnumMessage.SYSTEM_MESSAGE.equals(message.getType()) ||
				(AuthenticationHelper.getCurrentUser() != null && User.USER_TYPE_ADMIN.equals(AuthenticationHelper.getCurrentUser(true).getUsrIsAdmin())) ){
			message.setSender(AuthenticationHelper.getCurrentUser(true).getObjId());
			message.setSenderName(AuthenticationHelper.getCurrentUser(true).getUsername());
		}
		else{
			message.setSenderName("系统消息");
		}
		message.setCreateTime(new Date());
		message.setIsRead(false);
		message.setSendTime(new Date());
		
		long mm = new Date().getTime();
		if(null != message.getIsSave() && message.getIsSave())
		{
			//此条信息为发送者的发件箱中的信息
			messageDaoHibernate.saveMessageBatch(message, receiverObjIds, mm);
		}
		message.setIsSave(false);
		//此条信息为接收者的收件箱中的信息
		messageDaoHibernate.saveMessageBatch(message, receiverObjIds, mm+1);
	}
	
	/** 
	 * Description :  向指定的类型的用户发送系统站内信
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveSystemMessage(Message message, String[] receiverObjIds, String sendType, String roleName) throws Exception 
	{
		message.setSender(AuthenticationHelper.getCurrentUser(true).getObjId());
		message.setSenderName(AuthenticationHelper.getCurrentUser(true).getUsername());
		message.setCreateTime(new Date());
		message.setIsRead(false);
		//message.setResSendTime(new Date());
		message.setSendTime(new Date());
		
		long mm = new Date().getTime();
		if("to_all".equals(sendType)) // 发送到所有人
		{
			Message newMessage = new Message(message);
			newMessage.setReceiverName("所有人");
			newMessage.setIsSave(true);
			// 此条信息为发送者的发件箱中的信息
			messageDaoHibernate.save(newMessage);
		}
		else if("to_role".equals(sendType)) // 发送到指定角色的用户
		{
			Message newMessage = new Message(message);
			newMessage.setReceiverName(roleName);
			newMessage.setIsSave(true);
			// 此条信息为发送者的发件箱中的信息
			messageDaoHibernate.save(newMessage);
		}
		else if("to_user".equals(sendType)) // 发送到指定的用户
		{
			Message newMessage = new Message(message);
			newMessage.setIsSave(true);
			// 此条信息为发送者的发件箱中的信息
			messageDaoHibernate.saveMessageBatch(newMessage, receiverObjIds, mm);
		}
		
		message.setIsSave(false);
		// 此条信息为接收者的收件箱中的信息
		messageDaoHibernate.saveMessageBatch(message, receiverObjIds, mm+1);
	}

	/** 
	 * Description :  获得指定用户的未提示的站内信
	 * Create Date: 2010-11-19上午09:13:32 by likg  Modified Date: 2010-11-19上午09:13:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Message> getNotTipMessage(String objId) throws Exception 
	{
		return messageDaoHibernate.getNotTipMessage(objId);
	}

	/** 
	 * Description :  修改指定用户的未读的站内信的提示状态
	 * Create Date: 2010-11-19上午09:13:32 by likg  Modified Date: 2010-11-19上午09:13:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateMessageTipStatus(String receiverId) throws Exception 
	{
		messageDaoHibernate.updateMessageTipStatus(receiverId);
	}

}
