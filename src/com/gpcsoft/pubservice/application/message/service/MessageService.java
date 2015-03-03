package com.gpcsoft.pubservice.application.message.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.message.domain.Message;

public interface MessageService extends BaseGenericService<Message> {
	/** 
	 * Description :  保存新建的站内信内容
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveMessage(Message message) throws Exception;
	
	/** 
	 * Description :  获得指定用户的【未读的和味提示的】站内信的数量
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Long> getMessageNum(String objId, Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  向指定的多个用户批量发送站内信
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveMessageBatch(Message message, String[] receiverObjIds) throws Exception;
	
	/** 
	 * Description :  向指定的类型的用户发送系统站内信
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveSystemMessage(Message message, String[] receiverObjIds, String sendType, String roleName) throws Exception;
	
	/** 
	 * Description :  获得指定用户的未提示的站内信
	 * Create Date: 2010-11-19上午09:13:32 by likg  Modified Date: 2010-11-19上午09:13:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Message> getNotTipMessage(String objId) throws Exception;
	
	/** 
	 * Description :  修改指定用户的未读的站内信的提示状态
	 * Create Date: 2010-11-19上午09:13:32 by likg  Modified Date: 2010-11-19上午09:13:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateMessageTipStatus(String receiverId) throws Exception;
}
