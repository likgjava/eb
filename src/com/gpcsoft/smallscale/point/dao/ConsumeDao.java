package com.gpcsoft.smallscale.point.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.point.domain.Consume;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ConsumeDao extends BaseGenericDao<Consume> {
	/**
	 * 查询用户消费的积分
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public long queryConsumeIntegral(User user)throws Exception;
	
	/**
	 * 根据项目ID取的消费记录
	 * @param projectId
	 * @return
	 */
	public Consume getByProjectId(String projectId);
}
