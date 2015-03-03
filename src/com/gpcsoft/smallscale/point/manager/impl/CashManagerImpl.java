package com.gpcsoft.smallscale.point.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.point.dao.CashDao;
import com.gpcsoft.smallscale.point.domain.Cash;
import com.gpcsoft.smallscale.point.manager.CashManager;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class CashManagerImpl extends BaseManagerImpl<Cash> implements CashManager {

    @Autowired(required=true)  @Qualifier("cashDaoHibernate")
	private CashDao cashDaoHibernate;
    
    /**
	 * 查询用户兑现的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryCashIntegral(User user)throws Exception{
		return cashDaoHibernate.queryCashIntegral(user);
	}

}
