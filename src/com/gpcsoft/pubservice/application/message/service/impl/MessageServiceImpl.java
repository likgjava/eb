package com.gpcsoft.pubservice.application.message.service.impl;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.pubservice.application.message.manager.MessageManager;
import com.gpcsoft.pubservice.application.message.service.MessageService;
import com.gpcsoft.pubservice.application.message.domain.Message;

@Service 
public class MessageServiceImpl extends BaseGenericServiceImpl<Message> implements MessageService 
{
	@Autowired(required=true) @Qualifier("messageManagerImpl")
	private MessageManager messageManager;
	
	/** 
	 * Description :  保存新建的私人站内信内容
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveMessage(Message message) throws Exception 
	{
		messageManager.saveMessage(message);
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
		return messageManager.getMessageNum(objId, param);
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
		messageManager.saveMessageBatch(message, receiverObjIds);
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
		messageManager.saveSystemMessage(message, receiverObjIds, sendType, roleName);
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
		return messageManager.getNotTipMessage(objId);
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
		messageManager.updateMessageTipStatus(receiverId);
	}
}
