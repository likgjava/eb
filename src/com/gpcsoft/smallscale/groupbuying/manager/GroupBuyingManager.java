package com.gpcsoft.smallscale.groupbuying.manager;

import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;

public interface GroupBuyingManager extends BaseManager<GroupBuying> {

	/** 
	 * Description :  更新团购信息
	 * Create Date: 2011-6-23上午08:58:42 by likg  Modified Date: 2011-6-23上午08:58:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateGroupBuying(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取团购列表数据
	 * Create Date: 2011-6-23下午06:54:06 by likg  Modified Date: 2011-6-23下午06:54:06 by likg
	 * @param   page:分页信息		param:查询参数
	 * @return  
	 * @Exception   
	 */
	Page<GroupBuying> getGroupBuyingList(Page<GroupBuying> page, Map<String, Object> param) throws Exception;

}
