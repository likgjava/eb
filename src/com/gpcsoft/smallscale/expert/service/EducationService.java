package com.gpcsoft.smallscale.expert.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.expert.domain.Education;

public interface EducationService extends BaseGenericService<Education> {

	/** 
	 * Description : 保存教育经历 
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Education saveEducation(Education education);
	
	/** 
	 * Description : 审核教育经历
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateEducationAuditStatus(String objIds, String auditStatus);
}
