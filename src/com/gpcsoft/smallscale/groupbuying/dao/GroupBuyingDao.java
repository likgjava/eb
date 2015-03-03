package com.gpcsoft.smallscale.groupbuying.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;

public interface GroupBuyingDao extends BaseGenericDao<GroupBuying> {

	/** 
	 * Description :  获取团购列表数据
	 * Create Date: 2011-6-23下午06:54:06 by likg  Modified Date: 2011-6-23下午06:54:06 by likg
	 * @param   page:分页信息		param:查询参数
	 * @return  
	 * @Exception   
	 */
	Page<GroupBuying> getGroupBuyingList(Page<GroupBuying> page, Map<String, Object> param) throws Exception;

}
