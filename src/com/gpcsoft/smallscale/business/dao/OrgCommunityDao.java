package com.gpcsoft.smallscale.business.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.business.domain.OrgCommunity;

public interface OrgCommunityDao extends BaseGenericDao<OrgCommunity> {

	/** 
	 * Description :  加入社区
	 * Create Date: 2011-10-21上午11:16:28 by yucy  Modified Date: 2011-10-21上午11:16:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean createOrgCommunity(Map<String, Object> param) throws Exception;

}
