package com.gpcsoft.smallscale.point.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.point.dao.ConsumeDao;
import com.gpcsoft.smallscale.point.domain.Consume;
import com.gpcsoft.smallscale.point.manager.ConsumeManager;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class ConsumeManagerImpl extends BaseManagerImpl<Consume> implements ConsumeManager {

	@Autowired(required=true)  @Qualifier("consumeDaoHibernate")
	private ConsumeDao consumeDaoHibernate;
	
	/**
	 * 查询用户消费的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryConsumeIntegral(User user)throws Exception{
		return consumeDaoHibernate.queryConsumeIntegral(user);
	}

}
