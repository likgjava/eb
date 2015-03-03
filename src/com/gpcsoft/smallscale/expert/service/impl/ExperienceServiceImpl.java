package com.gpcsoft.smallscale.expert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.smallscale.expert.dao.ExperienceDao;
import com.gpcsoft.smallscale.expert.domain.Experience;
import com.gpcsoft.smallscale.expert.manager.ExperienceManager;
import com.gpcsoft.smallscale.expert.service.ExperienceService;

@Service 
public class ExperienceServiceImpl extends BaseGenericServiceImpl<Experience> implements ExperienceService {

	@Autowired(required=true) @Qualifier("experienceManagerImpl")
	private ExperienceManager experienceManager;
	
	@Autowired(required=true)  @Qualifier("experienceDaoHibernate")
	private ExperienceDao experienceDaoHibernate;


	/** 
	 * Description : 保存任职经历 
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Experience saveExperience(Experience experience){
		return experienceManager.save(experience);
	}
	
	/** 
	 * Description : 审核任职经历
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateExperienceAuditStatus(String objIds, String auditStatus){
		return experienceDaoHibernate.updateExperienceAuditStatus(objIds, auditStatus);
	}
}
