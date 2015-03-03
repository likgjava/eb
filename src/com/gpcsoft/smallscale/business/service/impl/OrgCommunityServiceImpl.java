package com.gpcsoft.smallscale.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.smallscale.business.dao.OrgCommunityDao;
import com.gpcsoft.smallscale.business.domain.OrgCommunity;
import com.gpcsoft.smallscale.business.manager.OrgCommunityManager;
import com.gpcsoft.smallscale.business.service.OrgCommunityService;

@Service 
public class OrgCommunityServiceImpl extends BaseGenericServiceImpl<OrgCommunity> implements OrgCommunityService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("orgCommunityManagerImpl")
	private OrgCommunityManager orgCommunityManager;
	
	@Autowired(required=true) @Qualifier("orgCommunityDaoHibernate")
	private OrgCommunityDao orgCommunityDao;

	/** 
	 * Description :  加入社区
	 * Create Date: 2011-10-21上午11:16:28 by yucy  Modified Date: 2011-10-21上午11:16:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean createOrgCommunity(Map<String, Object> param) throws Exception {
		return orgCommunityDao.createOrgCommunity(param);
	}

	/** 
	 * Description :  脱离社区
	 * Create Date: 2011-10-21上午11:29:09 by yucy  Modified Date: 2011-10-21上午11:29:09 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteOrgCommunity(Map<String, Object> param) throws Exception {
		remove((String[])param.get("minusIds"), OrgCommunity.class);
		return true;
	}
}
