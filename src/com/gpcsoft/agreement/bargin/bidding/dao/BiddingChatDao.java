package com.gpcsoft.agreement.bargin.bidding.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingChat;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface BiddingChatDao extends BaseGenericDao<BiddingChat> {
	
	/** 
	 * Description :  定时查询新聊天内容
	 * Create Date: 2010-10-24下午01:53:58 by liangxj  Modified Date: 2010-10-24下午01:53:58 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> findNewChatContent(Map<String, Object> params);
		
	/** 
	 * Description :  查找聊天内容 
	 * Create Date: 2010-10-24下午01:53:58 by liangxj  Modified Date: 2010-10-24下午01:53:58 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingChat> findChatContent(Map<String, Object> params);

	/** 
	 * Description :  修改阅读状态
	 * Create Date: 2010-10-29下午04:14:53 by yucy  Modified Date: 2010-10-29下午04:14:53 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateChatRecord(Map<String, Object> params) throws Exception;
}
