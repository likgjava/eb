package com.gpcsoft.pubservice.application.message.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.message.domain.Message;

public interface MessageDao extends BaseGenericDao<Message> {
	
	/** 
	 * Description :  获得指定用户的【未读的和味提示的】站内信的数量
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Long> getMessageNum(String objId, Map<String, Object> param) throws Exception ;
	
	/** 
	 * Description :  向指定的多个用户批量发送站内信
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveMessageBatch(Message message, String[] receiverObjIds, long time) throws Exception ;
	
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
