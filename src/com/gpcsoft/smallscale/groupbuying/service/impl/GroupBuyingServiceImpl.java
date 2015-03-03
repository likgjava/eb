package com.gpcsoft.smallscale.groupbuying.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;
import com.gpcsoft.smallscale.groupbuying.manager.GroupBuyingManager;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingService;

@Service 
public class GroupBuyingServiceImpl extends BaseGenericServiceImpl<GroupBuying> implements GroupBuyingService {

	@Autowired(required=true) @Qualifier("groupBuyingManagerImpl")
	private GroupBuyingManager groupBuyingManager;
	
	/** 
	 * Description :  保存团购信息
	 * Create Date: 2011-6-21下午04:51:41 by likg  Modified Date: 2011-6-21下午04:51:41 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGroupBuying(GroupBuying groupBuying) throws Exception {
		groupBuying.setCurrentNumber(new BigDecimal(0)); //设置当前团购人数
		groupBuyingManager.save(groupBuying);
	}

	/** 
	 * Description :  修改团购信息使用状态
	 * Create Date: 2011-6-22下午02:15:44 by likg  Modified Date: 2011-6-22下午02:15:44 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateStatus(String objId, String useStatus) throws Exception {
		GroupBuying groupBuying = groupBuyingManager.get(objId);
		groupBuying.setUseStatus(useStatus);
	}

	/** 
	 * Description :  获取团购列表数据
	 * Create Date: 2011-6-23下午06:54:06 by likg  Modified Date: 2011-6-23下午06:54:06 by likg
	 * @param   page:分页信息		param:查询参数
	 * @return  
	 * @Exception   
	 */
	public Page<GroupBuying> getGroupBuyingList(Page<GroupBuying> page, Map<String, Object> param) throws Exception {
		return groupBuyingManager.getGroupBuyingList(page, param);
	}

}
