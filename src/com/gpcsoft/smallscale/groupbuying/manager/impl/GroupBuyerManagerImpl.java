package com.gpcsoft.smallscale.groupbuying.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.groupbuying.dao.GroupBuyerDao;
import com.gpcsoft.smallscale.groupbuying.manager.GroupBuyerManager;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyer;

@Repository
public class GroupBuyerManagerImpl extends BaseManagerImpl<GroupBuyer> implements GroupBuyerManager {

	@Autowired(required=true)  @Qualifier("groupBuyerDaoHibernate")
	private GroupBuyerDao groupBuyerDaoHibernate;
	
	/** 
	 * Description :  修改支付状态
	 * Create Date: 2011-6-24上午11:27:13 by likg  Modified Date: 2011-6-24上午11:27:13 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String objId, String payStatus) throws Exception {
		return groupBuyerDaoHibernate.updatePayStatus(objId, payStatus);
	}

}
