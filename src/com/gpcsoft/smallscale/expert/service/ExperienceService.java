package com.gpcsoft.smallscale.expert.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.expert.domain.Experience;

public interface ExperienceService extends BaseGenericService<Experience> {

	/** 
	 * Description : 保存任职经历 
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Experience saveExperience(Experience experience);
	
	/** 
	 * Description : 审核任职经历
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateExperienceAuditStatus(String objIds, String auditStatus);
}
