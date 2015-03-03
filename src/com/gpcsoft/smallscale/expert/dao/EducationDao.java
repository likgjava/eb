package com.gpcsoft.smallscale.expert.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.expert.domain.Education;

public interface EducationDao extends BaseGenericDao<Education> {

	/** 
	 * Description : 审核教育经历
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateEducationAuditStatus(String objIds, String auditStatus);
}
