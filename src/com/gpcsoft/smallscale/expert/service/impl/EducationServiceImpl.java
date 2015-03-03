package com.gpcsoft.smallscale.expert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.smallscale.expert.dao.EducationDao;
import com.gpcsoft.smallscale.expert.domain.Education;
import com.gpcsoft.smallscale.expert.manager.EducationManager;
import com.gpcsoft.smallscale.expert.service.EducationService;

@Service 
public class EducationServiceImpl extends BaseGenericServiceImpl<Education> implements EducationService {

	@Autowired(required=true) @Qualifier("educationManagerImpl")
	private EducationManager educationManager;
	
	@Autowired(required=true)  @Qualifier("educationDaoHibernate")
	private EducationDao educationDaoHibernate;

	/** 
	 * Description : 保存教育经历 
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Education saveEducation(Education education){

		return educationManager.save(education);
	}
	
	/** 
	 * Description : 审核教育经历
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateEducationAuditStatus(String objIds, String auditStatus){
		return educationDaoHibernate.updateEducationAuditStatus(objIds, auditStatus);
	}
}
