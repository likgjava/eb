package com.gpcsoft.smallscale.groupbuying.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.groupbuying.dao.GroupBuyingDao;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;
import com.gpcsoft.smallscale.groupbuying.manager.GroupBuyingManager;

@Repository
public class GroupBuyingManagerImpl extends BaseManagerImpl<GroupBuying> implements GroupBuyingManager {

	@Autowired(required=true)  @Qualifier("groupBuyingDaoHibernate")
	private GroupBuyingDao groupBuyingDaoHibernate;

	/** 
	 * Description :  更新团购信息
	 * Create Date: 2011-6-23上午08:58:42 by likg  Modified Date: 2011-6-23上午08:58:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateGroupBuying(Map<String, Object> param) throws Exception {
		String objId = (String) param.get("objId");
		BigDecimal addNumber = (BigDecimal) param.get("addNumber");
		
		//获取团购信息
		GroupBuying groupBuying = groupBuyingDaoHibernate.get(objId);
		
		//更新团购数量
		groupBuying.setCurrentNumber(groupBuying.getCurrentNumber().add(addNumber));
		
		//更新达到最低团购数时间
		if(groupBuying.getCurrentNumber().compareTo(groupBuying.getMinNumber()) >= 0) {
			groupBuying.setMeetNumberTime(new Date());
		}
		
		//检查是否达到最大团购数，如果达到最大团购数，则结束团购
		if(groupBuying.getMaxNumber()!=null && groupBuying.getCurrentNumber().compareTo(groupBuying.getMaxNumber())>=0) {
			groupBuying.setEndTime(new Date());
		}
		
	}

	/** 
	 * Description :  获取团购列表数据
	 * Create Date: 2011-6-23下午06:54:06 by likg  Modified Date: 2011-6-23下午06:54:06 by likg
	 * @param   page:分页信息		param:查询参数
	 * @return  
	 * @Exception   
	 */
	public Page<GroupBuying> getGroupBuyingList(Page<GroupBuying> page, Map<String, Object> param) throws Exception {
		return groupBuyingDaoHibernate.getGroupBuyingList(page, param);
	}

}
