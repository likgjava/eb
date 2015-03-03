package com.gpcsoft.smallscale.business.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.business.domain.OrgCommunity;

public interface OrgCommunityService extends BaseGenericService<OrgCommunity> {

	/** 
	 * Description :  加入社区
	 * Create Date: 2011-10-21上午11:16:28 by yucy  Modified Date: 2011-10-21上午11:16:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean createOrgCommunity(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  脱离社区
	 * Create Date: 2011-10-21上午11:29:09 by yucy  Modified Date: 2011-10-21上午11:29:09 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean deleteOrgCommunity(Map<String, Object> param) throws Exception;

}
