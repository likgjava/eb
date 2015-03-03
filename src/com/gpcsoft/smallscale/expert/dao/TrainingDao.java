package com.gpcsoft.smallscale.expert.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.expert.domain.Training;

public interface TrainingDao extends BaseGenericDao<Training> {

	/** 
	 * Description : 审核陪训信息
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateTrainingAuditStatus(String objIds, String auditStatus);
}
