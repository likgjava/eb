package com.gpcsoft.smallscale.expert.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.expert.domain.Experience;

public interface ExperienceDao extends BaseGenericDao<Experience> {

	/** 
	 * Description : 审核任职经历
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateExperienceAuditStatus(String objIds, String auditStatus);
}
