package com.gpcsoft.smallscale.groupbuying.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;

public interface GroupBuyingService extends BaseGenericService<GroupBuying> {

	/** 
	 * Description :  保存团购信息
	 * Create Date: 2011-6-21下午04:51:41 by likg  Modified Date: 2011-6-21下午04:51:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveGroupBuying(GroupBuying groupBuying) throws Exception;

	/** 
	 * Description :  修改团购信息使用状态
	 * Create Date: 2011-6-22下午02:15:44 by likg  Modified Date: 2011-6-22下午02:15:44 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateStatus(String objId, String useStatus) throws Exception;

	/** 
	 * Description :  获取团购列表数据
	 * Create Date: 2011-6-23下午06:54:06 by likg  Modified Date: 2011-6-23下午06:54:06 by likg
	 * @param   page:分页信息		param:查询参数
	 * @return  
	 * @Exception   
	 */
	Page<GroupBuying> getGroupBuyingList(Page<GroupBuying> page, Map<String, Object> param) throws Exception;

}
